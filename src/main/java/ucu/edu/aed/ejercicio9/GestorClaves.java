package ucu.edu.aed.ejercicio9;

import ucu.edu.aed.implementaciones.ArbolBinarioBusqueda;

import java.io.*;

public class GestorClaves {

    private ArbolBinarioBusqueda<Integer> arbol;

    public GestorClaves() {
        this.arbol = new ArbolBinarioBusqueda<>();
    }

    public void cargarClaves(String rutaArchivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    int clave = Integer.parseInt(linea);
                    arbol.insertar(clave);
                }
            }
        }
    }

    public boolean buscarClave(int clave) {
        Integer encontrado = arbol.buscar(clave);
        return encontrado != null;
    }

    public String obtenerPreorden() {
        StringBuilder sb = new StringBuilder();
        arbol.preOrder(dato -> sb.append(dato).append(" "));
        return sb.toString().trim();
    }

    public String obtenerInorden() {
        StringBuilder sb = new StringBuilder();
        arbol.inOrder(dato -> sb.append(dato).append(" "));
        return sb.toString().trim();
    }

    public String obtenerPostorden() {
        StringBuilder sb = new StringBuilder();
        arbol.postOrder(dato -> sb.append(dato).append(" "));
        return sb.toString().trim();
    }

    public void guardarRecorridos(String rutaSalida) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaSalida))) {
            bw.write("Preorden: " + obtenerPreorden());
            bw.newLine();
            bw.write("Inorden: " + obtenerInorden());
            bw.newLine();
            bw.write("Postorden: " + obtenerPostorden());
            bw.newLine();
        }
    }
}