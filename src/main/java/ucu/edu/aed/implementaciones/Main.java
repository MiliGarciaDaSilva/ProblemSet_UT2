package ucu.edu.aed.implementaciones;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String CARPETA = "src/main/java/ucu/edu/aed/implementaciones/";
    private static final String ARCHIVO_CLAVES = CARPETA + "clavesPrueba.txt";
    private static final String ARCHIVO_SALIDA = CARPETA + "resultadoInserciones.txt";

    public static void main(String[] args) {

        ArbolBinario<Integer> arbol = new ArbolBinario<>();

        try {
            // Cada elemento de la lista es una línea del archivo, sin el salto de línea.
            List<String> lineas = Files.readAllLines(Paths.get(ARCHIVO_CLAVES));

            List<String> salida = new ArrayList<>();

            for (String linea : lineas) {
                String claveTexto = linea.trim();
                if (claveTexto.isEmpty()) {
                    continue;
                }
                int clave = Integer.parseInt(claveTexto);
                arbol.insertar(clave);
                salida.add(clave + " " + arbol.getContador());
            }

            Files.write(Paths.get(ARCHIVO_SALIDA), salida);
            System.out.println("Listo, Salida escrita en " + ARCHIVO_SALIDA);

        } catch (IOException e) {
            System.out.println("Error leyendo o escribiendo archivos: " + e.getMessage());
        }
    }
}
