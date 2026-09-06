package ucu.edu.aed.ejercicio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import ucu.edu.aed.implementaciones.ArbolBinario;

public class Main {

    public static void main(String[] args) throws IOException {
        ArbolBinario<Integer> arbol = new ArbolBinario<>();
        int[] claves = {12, 25, 14, 1, 33, 88, 45, 2, 7, 66, 5, 99};
        for (int clave : claves) {
            arbol.insertar(clave);
        }

        try (BufferedReader lector = new BufferedReader(new FileReader("consultasPrueba.txt"));
             BufferedWriter escritor = new BufferedWriter(new FileWriter("resultadoConsultas.txt"))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                int clave = Integer.parseInt(linea.trim());
                int nivel = arbol.esVacio() ? -1 : arbol.obtenerRaiz().obtenerNivel(clave);
                escritor.write(clave + "," + nivel);
                escritor.newLine();
            }
        }
    }
}
