package ucu.edu.aed.ejercicio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import ucu.edu.aed.implementaciones.ArbolBinario;

public class Main {

    public static void main(String[] args) {
        ArbolBinario<Integer> arbol = new ArbolBinario<>();

        try (BufferedReader lectorClaves = new BufferedReader(new FileReader("src/main/java/ucu/edu/aed/ejercicio2/clavesPrueba.txt"));
             BufferedWriter escritorInserciones = new BufferedWriter(new FileWriter("src/main/java/ucu/edu/aed/ejercicio2/resultadoInserciones.txt"))) {
            String linea;
            while ((linea = lectorClaves.readLine()) != null) {
                String claveTexto = linea.trim();
                if (claveTexto.isEmpty()) {
                    continue;
                }
                int clave = Integer.parseInt(claveTexto);
                arbol.insertar(clave);
                escritorInserciones.write(clave + " " + arbol.getContador());
                escritorInserciones.newLine();
            }

            try (BufferedReader lectorConsultas = new BufferedReader(new FileReader("src/main/java/ucu/edu/aed/ejercicio2/consultasPrueba.txt"));
                 BufferedWriter escritorConsultas = new BufferedWriter(new FileWriter("src/main/java/ucu/edu/aed/ejercicio2/resultadoConsultas.txt"))) {
                String lineaConsulta;
                while ((lineaConsulta = lectorConsultas.readLine()) != null) {
                    String claveTexto = lineaConsulta.trim();
                    if (claveTexto.isEmpty()) {
                        continue;
                    }
                    int clave = Integer.parseInt(claveTexto);
                    int nivel = arbol.obtenerNivel(clave);
                    escritorConsultas.write(clave + "," + nivel);
                    escritorConsultas.newLine();
                }
            }

            System.out.println("Listo. Salida escrita en resultadoInserciones.txt y resultadoConsultas.txt");

        } catch (IOException e) {
            System.out.println("Error leyendo o escribiendo archivos: " + e.getMessage());
        }
    }
}
