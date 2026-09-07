package ucu.edu.aed.tda;

import java.util.Comparator;
import java.util.function.Predicate;

/**
 * Define un Tipo de Dato Abstracto (TDA) Lista genérica.
 *
 * <p>Una lista permite almacenar elementos en una secuencia ordenada por posición,
 * admitiendo inserciones, accesos, eliminaciones, búsquedas y operaciones de ordenamiento.</p>
 *
 * <p>Las posiciones de los elementos se interpretan mediante índices enteros.
 * Salvo que la implementación indique lo contrario, se asume indexación basada en 0.</p>
 *
 * @param <T> el tipo de los elementos almacenados en la lista
 */
public interface TDALista<T> {

    /**
     * Agrega un elemento al final de la lista.
     *
     * @param elem el elemento a agregar
     */
    void agregar(T elem);

    /**
     * Agrega un elemento en la posición indicada.
     *
     * <p>Los elementos ubicados desde esa posición en adelante
     * desplazan su índice una posición hacia la derecha.</p>
     *
     * @param index la posición en la que se insertará el elemento
     * @param elem el elemento a agregar
     * @throws IndexOutOfBoundsException si el índice está fuera de rango
     */
    void agregar(int index, T elem);

    /**
     * Obtiene el elemento almacenado en la posición indicada.
     *
     * @param index la posición del elemento a recuperar
     * @return el elemento ubicado en la posición indicada
     * @throws IndexOutOfBoundsException si el índice está fuera de rango
     */
    T obtener(int index);

    /**
     * Remueve y devuelve el elemento almacenado en la posición indicada.
     *
     * <p>Los elementos posteriores, si existen, desplazan su índice
     * una posición hacia la izquierda.</p>
     *
     * @param index la posición del elemento a remover
     * @return el elemento removido
     * @throws IndexOutOfBoundsException si el índice está fuera de rango
     */
    T remover(int index);

    /**
     * Remueve la primera ocurrencia del elemento indicado en la lista.
     *
     * <p>La comparación del elemento queda sujeta al criterio definido
     * por la implementación, normalmente mediante {@code equals}.</p>
     *
     * @param elem el elemento a remover
     * @return {@code true} si el elemento fue encontrado y removido;
     *         {@code false} en caso contrario
     */
    boolean remover(T elem);

    /**
     * Determina si la lista contiene el elemento indicado.
     *
     * <p>La comparación del elemento queda sujeta al criterio definido
     * por la implementación, normalmente mediante {@code equals}.</p>
     *
     * @param elem el elemento a buscar
     * @return {@code true} si el elemento está presente en la lista;
     *         {@code false} en caso contrario
     */
    boolean contiene(T elem);

    /**
     * Retorna el índice de la primera ocurrencia del elemento indicado.
     *
     * <p>La comparación del elemento queda sujeta al criterio definido
     * por la implementación, normalmente mediante {@code equals}.</p>
     *
     * @param elem el elemento a buscar
     * @return el índice de la primera ocurrencia del elemento, o {@code -1}
     *         si el elemento no se encuentra en la lista
     */
    int indiceDe(T elem);

    /**
     * Busca y retorna el primer elemento que cumple con el criterio dado.
     *
     * @param criterio el predicado que define la condición de búsqueda
     * @return el primer elemento que cumple el criterio, o {@code null}
     *         si no existe ninguno
     */
    T buscar(Predicate<T> criterio);

    /**
     * Retorna una nueva lista con los elementos ordenados según el comparador dado.
     *
     * <p>El criterio de orden está determinado por el objeto {@link Comparator}
     * recibido como parámetro.</p>
     *
     * @param comparator el comparador que define el orden de los elementos
     * @return una lista ordenada según el criterio indicado
     */
    TDALista<T> ordenar(Comparator<T> comparator);

    /**
     * Retorna la cantidad de elementos almacenados en la lista.
     *
     * @return la cantidad de elementos de la lista
     */
    int tamaño();

    /**
     * Determina si la lista no contiene elementos.
     *
     * @return {@code true} si la lista está vacía;
     *         {@code false} en caso contrario
     */
    boolean esVacio();

    /**
     * Elimina todos los elementos de la lista.
     *
     * <p>Luego de invocar este método, la lista queda vacía.</p>
     */
    void vaciar();
}