package ucu.edu.aed.implementaciones;

import java.util.Comparator;
import java.util.function.Predicate;

import ucu.edu.aed.tda.TDALista;

public class ListaArray<T> implements TDALista<T> {

    private static final int CAPACIDAD_INICIAL = 10;

    protected Object[] datos;
    protected int tamanio;

    public ListaArray(){
        datos = new Object[CAPACIDAD_INICIAL];
        tamanio = 0;
    }
    
    public ListaArray(int capacidadInicial){
        datos = new Object[capacidadInicial];
        tamanio = 0;
    }

    private void asegurarCapacidad(){
        if (tamanio == datos.length){
            Object[] nuevo = new Object[datos.length * 2];
            for (int i = 0; i < tamanio; i++){
                nuevo[i] = datos[i];
            }
            datos = nuevo;
        }
    }

    public int capacidad(){
    return datos.length;
}

    @Override
    public void agregar(T elem){
        asegurarCapacidad();
        datos[tamanio] = elem;
        tamanio++;
    }

    @Override
    public void agregar(int index, T elem){
        if (index < 0 || index > tamanio){
            throw new IndexOutOfBoundsException();
        }
        asegurarCapacidad();
        for (int i = tamanio; i > index; i--){
            datos[i] = datos[i - 1];
        }
        datos[index] = elem;
        tamanio++;
    }

    @Override
    public T obtener(int index){
        if (index < 0 || index >= tamanio){
            throw new IndexOutOfBoundsException();
        }
        return (T) datos[index];
    }

    /**
     * Reemplaza el elemento de la posición indicada y devuelve el que estaba.
     *
     * <p>A diferencia de agregar(index, elem), no desplaza nada: la lista conserva
     * su tamaño y sólo cambia el contenido de esa posición.</p>
     *
     * <p>No está declarado en TDALista para no modificar la interfaz que usa el resto
     * del equipo.</p>
     */
    public T establecer(int index, T elem){
        if (index < 0 || index >= tamanio){
            throw new IndexOutOfBoundsException();
        }
        T anterior = (T) datos[index];
        datos[index] = elem;
        return anterior;
    }

    @Override
    public T remover(int index){
        if (index < 0 || index >= tamanio){
            throw new IndexOutOfBoundsException();
        }
        T removido = (T) datos[index];
        for (int i = index; i < tamanio - 1; i++){
            datos[i] = datos[i + 1];
        }
        datos[tamanio - 1] = null;
        tamanio--;
        return removido;
    }

    @Override
    public boolean remover(T elem){
        int index = indiceDe(elem);
        if (index == -1){
            return false;
        }
        remover(index);
        return true;
    }

    @Override
    public boolean contiene(T elem){
        return indiceDe(elem) != -1;
    }


    @Override
    public int indiceDe(T elem){
        for (int i = 0; i < tamanio; i++){
            if (((T) datos[i]).equals(elem)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public T buscar(Predicate<T> criterio){
        for (int i = 0; i < tamanio; i++){
            T dato = (T) datos[i];
            if (criterio.test(dato)){
                return dato;
            }
        }
        return null;
    }

    @Override
    public TDALista<T> ordenar(Comparator<T> comparator){
        ListaArray<T> resultado = new ListaArray<>();
        for (int i = 0; i < tamanio; i++){
            T dato = (T) datos[i];
            int pos = 0;
            while (pos < resultado.tamanio && comparator.compare(dato, (T) resultado.datos[pos]) >= 0){
                pos++;
            }
            resultado.agregar(pos, dato);
        }
        return resultado;
    }

    @Override
    public int tamaño(){
        return tamanio;
    }

    @Override
    public boolean esVacio(){
        return tamanio == 0;
    }

    @Override
    public void vaciar(){
        for (int i = 0; i < tamanio; i++){
            datos[i] = null;
        }
        tamanio = 0;
    }

    public void ampliarCapacidad(int cantidadAdicional){
        if (cantidadAdicional <= 0){
            throw new IllegalArgumentException("La cantidad a ampliar debe ser mayor a 0");
        }
        Object[] nuevo = new Object[datos.length + cantidadAdicional];
        for (int i = 0; i < tamanio; i++){
            nuevo[i] = datos[i];
        }
        datos = nuevo;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamanio; i++){
            sb.append(datos[i]);
            if (i < tamanio - 1){
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
