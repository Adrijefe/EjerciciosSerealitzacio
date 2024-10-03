package Ej2;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static class Asignaturas implements Serializable {
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
                System.out.println("Les notes han de ser entre 0 i 10.");
            }
        }

        public String toString() {
            return "La asignatura es:  " + asignatura + " y la nota es: " + nota;
        }


    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Double> asignaturas = new HashMap<>();
        String[] nombres = {"Programacion", "Base de Datos", "Lenguaje de Marcas", "Entornos", "Sistemas Informaticos", "Fol"};
        for (String nombre : nombres) {
            System.out.print("Ingrese la nota de la asignatura de " +nombre + ": ");
            double nota = sc.nextDouble();
            asignaturas.put(nombre, nota);
        }


        try
                (ObjectOutput oos = new ObjectOutputStream(new FileOutputStream("asignaturas.txt"))) {
            oos.writeObject(asignaturas);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("asignaturas.txt"))){
            HashMap <String, Double> deserealizado = (HashMap<String, Double>) ois.readObject();
            double suma = 0;
            for (String assignatura : deserealizado.keySet()) {
                double nota = deserealizado.get(assignatura);
                System.out.println(assignatura + ", Nota: " + nota);
                suma += nota;
            }
            double media = suma / deserealizado.size();
            System.out.println("La media es: " + media);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }






    }


}
