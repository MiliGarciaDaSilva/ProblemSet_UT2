package ucu.edu.aed.implementaciones;
import java.util.NoSuchElementException;

import ucu.edu.aed.tda.TDALista;
import ucu.edu.aed.tda.TDAPila;

public class Pila<T> extends ListaEnlazada<T> implements TDAPila<T> {

    @Override
    public T tope(){
        if (cabeza == null){
            throw new NoSuchElementException();
        }
        return cabeza.getDato();
    }

    @Override
    public T saca(){
        if (cabeza == null){
            throw new NoSuchElementException();
        }
        Nodo<T> nodoRemovido = cabeza;
        Nodo<T> nodoSiguiente = cabeza.siguiente;
        cabeza = null;
        cabeza = nodoSiguiente;
        tamanio--;
        return nodoRemovido.getDato();
    }

    @Override
    public void mete(T dato){
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        nuevoNodo.siguiente = cabeza;
        cabeza = nuevoNodo;
        tamanio++;
    }
}
