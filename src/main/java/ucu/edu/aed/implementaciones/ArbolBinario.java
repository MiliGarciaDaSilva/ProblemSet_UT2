package ucu.edu.aed.implementaciones;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

import ucu.edu.aed.tda.TDAArbolBinario;
import ucu.edu.aed.tda.TDAElemento;
import ucu.edu.aed.tda.TDALista;

public class ArbolBinario<T extends Comparable<T>> implements TDAArbolBinario<T> {

    protected TDAElemento<T> raiz;
    protected int cantidadNodos;

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
        cantidadNodos--;
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
        if (raiz == null){
            raiz = new Elemento<>(dato);
            cantidadNodos++;
            return true;
        }
        Queue<TDAElemento<T>> cola = new LinkedList<>();
        cola.add(raiz);
        while (!cola.isEmpty()){
            TDAElemento<T> actual = cola.poll();
            if (actual.getHijoIzquierdo() == null){
                actual.setHijoIzquierdo(new Elemento<>(dato));
                cantidadNodos++;
                return true;
            }
            cola.add(actual.getHijoIzquierdo());
            if (actual.getHijoDerecho() == null){
                actual.setHijoDerecho(new Elemento<>(dato));
                cantidadNodos++;
                return true;
            }
            cola.add(actual.getHijoDerecho());
        }
        return false;
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
        return cantidadNodos;
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

    @Override
    public T claveMenor(){
        return null;
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
