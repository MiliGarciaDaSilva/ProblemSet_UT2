package ucu.edu.aed.implementaciones;

import java.util.NoSuchElementException;

import ucu.edu.aed.tda.TDACola;

public class Cola<T> extends ListaEnlazada<T> implements TDACola<T> {

    @Override
    public T frente(){
        if (esVacio()) {
            throw new NoSuchElementException();
        } else {
            return obtener(0);
        }
    };

    @Override
    public boolean poneEnCola(T dato){
        agregar(dato);
        return true;
    }

    @Override
    public T quitaDeCola(){
        if (esVacio()) {
            throw new NoSuchElementException();
        } else {
            return remover(0);
        }
    }
}
