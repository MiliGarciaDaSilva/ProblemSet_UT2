package ucu.edu.aed.implementaciones;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String ARCHIVO_CLAVES = "clavesPrueba.txt";
    private static final String ARCHIVO_SALIDA = "resultadoInserciones.txt";

    public static void main(String[] args) {

        ArbolBinario<Integer> arbol = new ArbolBinario<>();

        try {
            // Cada elemento de la lista es una línea del archivo, sin el salto de línea.
            List<String> lineas = Files.readAllLines(Paths.get(ARCHIVO_CLAVES));

            // Acá vas armando las líneas que después se escriben al archivo de salida.
            List<String> salida = new ArrayList<>();

            // ==================== TU CICLO ACÁ ====================

            // ======================================================

            Files.write(Paths.get(ARCHIVO_SALIDA), salida);
            System.out.println("Listo. Salida escrita en " + ARCHIVO_SALIDA);

        } catch (IOException e) {
            System.out.println("Error leyendo o escribiendo archivos: " + e.getMessage());
        }
    }
}
