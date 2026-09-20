package ucu.edu.aed.ejercicio12;

import ucu.edu.aed.implementaciones.ArbolBinarioBusqueda;
import ucu.edu.aed.implementaciones.ListaEnlazada;
import ucu.edu.aed.tda.TDAElemento;
import ucu.edu.aed.tda.TDALista;

public class Grimorio {

    private ArbolBinarioBusqueda<Hechizo> arbol;

    public Grimorio(){
        arbol = new ArbolBinarioBusqueda<>();
    }

    public boolean agregarHechizo(Hechizo hechizo){
        return arbol.insertar(hechizo);
    }

    public TDALista<Hechizo> hechizosProhibidos(){
        TDALista<Hechizo> resultado = new ListaEnlazada<>();
        agregarProhibidos(arbol.obtenerRaiz(), resultado);
        return resultado;
    }

    private void agregarProhibidos(TDAElemento<Hechizo> nodo, TDALista<Hechizo> resultado){
        if (nodo == null){
            return;
        }
        agregarProhibidos(nodo.getHijoIzquierdo(), resultado);
        if (nodo.getDato().getId() % 2 != 0){
            resultado.agregar(nodo.getDato());
        }
        agregarProhibidos(nodo.getHijoDerecho(), resultado);
    }

    public String cantico(){
        TDALista<Hechizo> prohibidos = hechizosProhibidos();
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < prohibidos.tamaño(); i++){
            resultado.append(prohibidos.obtener(i).getNombre());
            if (i < prohibidos.tamaño() - 1){
                resultado.append(" - ");
            }
        }
        return resultado.toString();
    }
}
