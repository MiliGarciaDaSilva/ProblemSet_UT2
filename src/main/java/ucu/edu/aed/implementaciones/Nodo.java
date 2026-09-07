package ucu.edu.aed.implementaciones;

public class Nodo<T> {
    private T dato;
    Nodo<T> siguiente;

    Nodo(T dato) {
        this.dato = dato;
    }

    public T getDato(){
        return dato;
    }

}