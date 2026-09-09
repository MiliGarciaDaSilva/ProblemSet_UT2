package ucu.edu.aed.implementaciones;

import java.util.function.Consumer;

import ucu.edu.aed.tda.TDAArbolBinario;
import ucu.edu.aed.tda.TDAElemento;
import ucu.edu.aed.tda.TDALista;

public class ArbolBinario<T extends Comparable<T>> implements TDAArbolBinario<T> {

    protected TDAElemento<T> raiz;

    public int getContador(){
        return 0;
    }

    @Override
    public int altura(){
    if (raiz == null){
        return 0;
    }
    return raiz.altura();
    }

    @Override
    public T buscar(Comparable<T> predicate){
        return buscarEnNodo(raiz, predicate);
    }

    private T buscarEnNodo(TDAElemento<T> nodo, Comparable<T> predicate){
        if (nodo == null){
            return null;
        }
        if (predicate.compareTo(nodo.getDato()) == 0){
            return nodo.getDato();
        }
        T resultado = buscarEnNodo(nodo.getHijoIzquierdo(), predicate);
        if (resultado != null){
            return resultado;
        }
        return buscarEnNodo(nodo.getHijoDerecho(), predicate);
    }

    @Override
    public TDAElemento<T> obtenerRaiz(){
        return raiz;
    }

    @Override
    public boolean eliminar(Comparable<T> criterioBusqueda){
        if (buscar(criterioBusqueda) == null){
            return false;
        }
        raiz = eliminarEnNodo(raiz, criterioBusqueda);
        return true;
    }

    private TDAElemento<T> eliminarEnNodo(TDAElemento<T> nodo, Comparable<T> criterioBusqueda){
        if (nodo == null){
            return null;
        }
        if (criterioBusqueda.compareTo(nodo.getDato()) == 0){
            return quitarNodo(nodo);
        }
        nodo.setHijoIzquierdo(eliminarEnNodo(nodo.getHijoIzquierdo(), criterioBusqueda));
        nodo.setHijoDerecho(eliminarEnNodo(nodo.getHijoDerecho(), criterioBusqueda));
        return nodo;
    }

    private TDAElemento<T> quitarNodo(TDAElemento<T> nodo){
        if (nodo.getHijoIzquierdo() == null){
            return nodo.getHijoDerecho();
        }
        if (nodo.getHijoDerecho() == null){
            return nodo.getHijoIzquierdo();
        }
        TDAElemento<T> elHijo = nodo.getHijoIzquierdo();
        TDAElemento<T> elPadre = nodo;
        while (elHijo.getHijoDerecho() != null){
            elPadre = elHijo;
            elHijo = elHijo.getHijoDerecho();
        }
        if (elPadre != nodo){
            elPadre.setHijoDerecho(elHijo.getHijoIzquierdo());
            elHijo.setHijoIzquierdo(nodo.getHijoIzquierdo());
        }
        elHijo.setHijoDerecho(nodo.getHijoDerecho());
        return elHijo;
    }

    @Override
    public boolean insertar(T dato){
        raiz = insertarEnNodo(raiz, dato);
        return true;
    }

    private TDAElemento<T> insertarEnNodo(TDAElemento<T> nodo, T dato){
        if (nodo == null){
            return new Elemento<>(dato);
        }
        if (nodo.getHijoIzquierdo() == null){
            nodo.setHijoIzquierdo(new Elemento<>(dato));
        }
        else if (nodo.getHijoDerecho() == null){
            nodo.setHijoDerecho(new Elemento<>(dato));
        }
        else if (nodo.getHijoIzquierdo().cantidadNodos() <= nodo.getHijoDerecho().cantidadNodos()){
            nodo.setHijoIzquierdo(insertarEnNodo(nodo.getHijoIzquierdo(), dato));
        }
        else{
            nodo.setHijoDerecho(insertarEnNodo(nodo.getHijoDerecho(), dato));
        }
        return nodo;
    }

    @Override
    public void inOrder(Consumer<T> consumidor){
        if (raiz == null){
            return;
        }
        raiz.inOrder(nodo -> consumidor.accept(nodo.getDato()));
    }

    @Override
    public void preOrder(Consumer<T> consumidor){
        if (raiz == null){
            return;
        }
        raiz.preOrder(nodo -> consumidor.accept(nodo.getDato()));
    }

    @Override
    public String preOrderString(){
        StringBuilder resultado = new StringBuilder();
        preOrder(dato -> resultado.append(dato).append(","));
        if (resultado.length() > 0){
            resultado.setLength(resultado.length() - 1);
        }
        return resultado.toString();
    }


    @Override
    public void postOrder(Consumer<T> consumidor){
        if (raiz == null){
            return;
        }
        raiz.postOrder(nodo -> consumidor.accept(nodo.getDato()));
    }

    @Override
    public String postOrderString(){
        StringBuilder resultado = new StringBuilder();
        postOrder(dato -> resultado.append(dato).append(","));
        if (resultado.length() > 0){
            resultado.setLength(resultado.length() - 1);
        }
        return resultado.toString();
    }

    @Override
    public String inOrderString(){
        StringBuilder resultado = new StringBuilder();
        inOrder(dato -> resultado.append(dato).append(","));
        if (resultado.length() > 0){
            resultado.setLength(resultado.length() - 1);
        }
        return resultado.toString();
    }


    @Override
    public boolean esVacio(){
        return raiz == null;
    }

    @Override
    public int cantidadNodos(){
        if (raiz == null){
            return 0;
        }
        return raiz.cantidadNodos();
    }


    @Override
    public int cantidadHojas(){
        if (raiz == null){
            return 0;
        }
        return raiz.cantidadHojas();
    }


    @Override
    public int cantidadNodosInternos(){
        if (raiz == null){
            return 0;
        }
        return raiz.cantidadNodosInternos();
    }

    @Override
    public int obtenerNivel(Comparable<T> criterioBusqueda){
        return -1;
    }
    
    // devuleve clave menor del árbol
    public T claveMenor(){
        if (raiz == null){
            return null;
        }
        return raiz.claveMenor();
    }

    // devuelve clave mayor del árbol
    public T claveMayor(){
        if (raiz == null) {
            return null;
        }
        return raiz.claveMayor();
    }

    // devuelve clave anterior a la ingredaden orden lexicográfico, si no tiene clave anterior devuelve null
    public T claveAnterior(Comparable<T> clave) {
        if (raiz == null) {
            return null;
        }
        return raiz.claveAnterior(clave);
    }

    public int cantidadNodosEnNivel(int nivel){
        if (raiz == null || nivel < 0){
            return 0;
        }
        return raiz.cantidadNodosEnNivel(nivel);
    }

    public TDALista<String> hojasConNivel(){
        if (raiz == null){
            return new ListaEnlazada<>();
        }
        return raiz.hojasConNivel(0);
    }

    public boolean esArbolDeBusqueda(){
        if (raiz == null){
            return true;
        }
        return raiz.esArbolDeBusqueda(null, null);
    }

    @Override
    public TDALista<T> completos(){
        if (raiz == null){
            return new ListaEnlazada<>();
        }
        return raiz.completos();
    }

    @Override
    public TDALista<T> enNivel(int nivel){
        if (raiz == null){
            return new ListaEnlazada<>();
        }
        return raiz.enNivel(nivel);
    }

}
