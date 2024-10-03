package Ejercicio1;

import java.io.*;
import java.util.Scanner;

/*
Realitza un programa on es demanen les teues notes de les assignatures de
1r de DAM i les guarde en un fitxer. Asignaturas continuació, el programa llegirà el fitxer
i calcularà la nota mitjana del curs.

Asignaturas més, aquest exercici requereix que les notes i assignatures es serialitzen
i deserialitzen utilitzant objectes. És a dir, les dades de les assignatures i
 les teues notes es guardaran en un fitxer mitjançant la serialització d’objectes i
  es recuperaran mitjançant la deserialització dels mateixos.

 */

class Asignaturas implements Serializable {
    String asignatura;
    double nota;

    Asignaturas(String asignatura) {
        this.asignatura = asignatura;

    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        if (nota >= 0 && nota <= 10) {
            this.nota = nota;
        } else {
            System.out.println("Las notas tienen que ser entre 0 y 10.");
        }
    }

    public String toString() {
        return "La asignatura es " + asignatura + " y la nota es: " + nota;
    }


}

public class main {

        public static void main(String[] args) {
            Asignaturas[] asignaturas = new Asignaturas[6];
            Scanner sc = new Scanner(System.in);
            String[] nombre = {"Programacion", "Lenguaje", "Entornos", " Base Datos", "Sistemas", "Fol"};

            for (int i = 0; i < asignaturas.length; i++) {
                asignaturas[i] = new Asignaturas(nombre[i]);
                System.out.print("Introduce la nota de la asignatura " + nombre[i] + ": ");
                double nota = sc.nextDouble();
                asignaturas[i].setNota(nota);
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Asignaturas.txt"))) {
                oos.writeObject(asignaturas);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Asignaturas.txt"))){
                Asignaturas[] deserializado = (Asignaturas[]) ois.readObject();
                double suma = 0;
                for (Asignaturas asignatura : deserializado) {
                    System.out.println(asignatura.toString());
                    suma += asignatura.getNota();
                }

                double media = suma / deserializado.length;
                System.out.println("La media es: " + media);

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }

