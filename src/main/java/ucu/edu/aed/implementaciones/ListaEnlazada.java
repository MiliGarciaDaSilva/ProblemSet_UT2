package ucu.edu.aed.implementaciones;

import java.util.Comparator;
import java.util.function.Predicate;

import ucu.edu.aed.tda.TDALista;

public class ListaEnlazada<T> implements TDALista<T> {

    protected Nodo<T> cabeza;
    protected Nodo<T> cola;
    protected int tamanio;

    @Override
    public void agregar(T elem){
        Nodo<T> nuevoNodo = new Nodo<>(elem);
        if (cabeza == null){
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        }
        else{
            cola.siguiente = nuevoNodo;
            cola = nuevoNodo;
        }
        tamanio++;
    }

    @Override
    public void agregar(int index, T elem){
        if (index < 0 || index > tamanio){
            throw new IndexOutOfBoundsException();
        }
        Nodo<T> nuevoNodo = new Nodo<>(elem);
        if (index == 0){
            nuevoNodo.siguiente = cabeza;
            cabeza = nuevoNodo;
            if (tamanio == 0){
                cola = nuevoNodo;
            }
        }
        else{
            int contador = 0;
            Nodo<T> actual = cabeza;
            while (contador < index - 1){
                actual = actual.siguiente;
                contador++;
            }
            nuevoNodo.siguiente = actual.siguiente;
            actual.siguiente = nuevoNodo;
            if (nuevoNodo.siguiente == null){
                cola = nuevoNodo;
            }
        }
        tamanio++;
    }

    @Override
    public T obtener(int index){
        if (index < 0 || index >= tamanio){
            throw new IndexOutOfBoundsException();
        }
        Nodo<T> actual = cabeza;
        int contador = 0;
        while (contador != index){
            actual = actual.siguiente;
            contador++;
        }
        return actual.getDato();
    }

    @Override
    public T remover(int index){
        if (index < 0 || index >= tamanio){
            throw new IndexOutOfBoundsException();
        }
        if (index == 0){
            Nodo<T> nodoRemovido = cabeza;
            cabeza = cabeza.siguiente;
            nodoRemovido.siguiente = null;
            if (cabeza == null){
                cola = null;
            }
            tamanio--;
            return nodoRemovido.getDato();
        }
        Nodo<T> actual = cabeza;
        int contador = 0;
        while (contador < index - 1){
            actual = actual.siguiente;
            contador++;
        }
        Nodo<T> nodoASacar = actual.siguiente;
        actual.siguiente = actual.siguiente.siguiente;
        nodoASacar.siguiente = null;
        if (actual.siguiente == null){
            cola = actual;
        }
        tamanio--;
        return nodoASacar.getDato();
    }

    @Override
    public boolean remover(T elem){
        if (cabeza == null){
            return false;
        }
        if (cabeza.getDato().equals(elem)){
            Nodo<T> nodoRemovido = cabeza;
            cabeza = cabeza.siguiente;
            nodoRemovido.siguiente = null;
            if (cabeza == null){
                cola = null;
            }
            tamanio--;
            return true;
        }
        Nodo<T> actual = cabeza;
        while (actual.siguiente != null){
            if (actual.siguiente.getDato().equals(elem)){
                Nodo<T> nodoRemovido = actual.siguiente;
                actual.siguiente = actual.siguiente.siguiente;
                nodoRemovido.siguiente = null;
                if (actual.siguiente == null){
                    cola = actual;
                }
                tamanio--;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public boolean contiene(T elem){
        Nodo<T> actual = cabeza;
        while (actual != null){
            if (actual.getDato().equals(elem)){
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public int indiceDe(T elem){
        Nodo<T> actual = cabeza;
        int contador = 0;
        while (actual != null){
            if (actual.getDato().equals(elem)){
                return contador;
            }
            contador++;
            actual = actual.siguiente;
        }
        return -1;
    }

    @Override
    public T buscar(Predicate<T> criterio){
        Nodo<T> actual = cabeza;
        while (actual != null){
            if (criterio.test(actual.getDato())){
                return actual.getDato();
            }
            actual = actual.siguiente;
        }
        return null;
    }

    @Override
    public TDALista<T> ordenar(Comparator<T> comparator){
        ListaEnlazada<T> resultado = new ListaEnlazada<>();
        Nodo<T> actual = cabeza;

        while (actual != null){
            T dato = actual.getDato();

            if (resultado.cabeza == null || comparator.compare(dato, resultado.cabeza.getDato()) < 0){
                Nodo<T> nuevoNodo = new Nodo<>(dato);
                nuevoNodo.siguiente = resultado.cabeza;
                resultado.cabeza = nuevoNodo;
                if (resultado.cola == null){
                    resultado.cola = nuevoNodo;
                }
            }
            else{
                Nodo<T> actualResultado = resultado.cabeza;
                while (actualResultado.siguiente != null &&
                    comparator.compare(dato, actualResultado.siguiente.getDato()) >= 0){
                    actualResultado = actualResultado.siguiente;
                }
                Nodo<T> nuevoNodo = new Nodo<>(dato);
                nuevoNodo.siguiente = actualResultado.siguiente;
                actualResultado.siguiente = nuevoNodo;
                if (nuevoNodo.siguiente == null){
                    resultado.cola = nuevoNodo;
                }
            }

            actual = actual.siguiente;
        }

        resultado.tamanio = this.tamanio;
        return resultado;
    }

    @Override
    public int tamaño(){
        return tamanio;
    }

    @Override
    public boolean esVacio(){
        return cabeza == null;
    }

    @Override
    public void vaciar(){
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

}
