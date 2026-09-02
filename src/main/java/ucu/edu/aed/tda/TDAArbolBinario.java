package ucu.edu.aed.tda;

import java.util.function.Consumer;

/**
 * Define un Tipo de Dato Abstracto (TDA) Árbol Binario genérico.
 *
 * <p>Un árbol binario es una estructura de datos jerárquica en la que cada nodo
 * puede tener como máximo dos hijos: un hijo izquierdo y un hijo derecho.</p>
 *
 * <p>Esta interfaz proporciona operaciones para insertar, buscar, eliminar elementos,
 * así como diferentes formas de recorrido del árbol (in-order, pre-order, post-order)
 * y métodos para obtener información sobre la estructura del árbol.</p>
 *
 * @param <T> el tipo de los elementos almacenados en el árbol
 */
public interface TDAArbolBinario<T> {
    /**
     * Busca y retorna el primer elemento que cumple con el predicado dado.
     *
     * <p>El recorrido del árbol para la búsqueda queda sujeto a la implementación.</p>
     *
     * @param predicate el predicado que define el criterio de búsqueda
     * @return el primer elemento que cumple el criterio, o {@code null}
     * si no existe ninguno
     */
    T buscar(Comparable<T> predicate);

    /**
     * Retorna el elemento raíz del árbol.
     *
     * @return el elemento raíz del árbol, o {@code null} si el árbol está vacío
     */
    TDAElemento<T> obtenerRaiz();

    /**
     * Elimina el o los nodos según el criterio de búsqueda.
     *
     * @param criterioBusqueda el predicado que define qué elementos deben ser eliminados
     * @return {@code true} si al menos un elemento fue eliminado;
     * {@code false} en caso contrario
     */
    boolean eliminar(Comparable<T> criterioBusqueda);

    /**
     * Agrega un dato al árbol.
     *
     * <p>Si el dato ya existe en el árbol, no se agrega nuevamente.</p>
     *
     * @param dato el elemento a insertar
     * @return {@code true} si el elemento fue agregado correctamente;
     * {@code false} si el elemento ya existía y no fue agregado
     */
    boolean insertar(Comparable<T> dato);

    /**
     * Recorre el árbol en in-order
     * {@snippet :
     * // ejemplo de uso
     * elemento.inOrder(dato ->{
     *     // procesar dato
     *     // esta función se llama tantas veces como nodos halla en el árbol
     * });
     *}
     */
    void inOrder(Consumer<T> consumidor);

    /**
     * Recorre el árbol en pre-order
     * {@snippet :
     * // ejemplo de uso
     * elemento.preOrder(dato ->{
     *     // procesar dato
     *     // esta función se llama tantas veces como nodos halla en el árbol
     * });
     *}
     */
    void preOrder(Consumer<T> consumidor);

    /**
     * Recorre el árbol en post-order
     * {@snippet :
     * // ejemplo de uso
     * elemento.postOrder(dato ->{
     *     // procesar dato
     *     // esta función se llama tantas veces como nodos halla en el árbol
     * });
     *}
     */
    void postOrder(Consumer<T> consumidor);

    /**
     * Devuelve true si el árbol es vacío
     */
    boolean esVacio();

    /**
     * Devuelve la cantidad de nodos del árbol
     **/
    int cantidadNodos();

    /**
     * Devuelve la cantidad de nodos que son hojas
     */
    int cantidadHojas();

    /**
     * Devuelve la cantidad de nodos que NO son hojas
     */
    int cantidadNodosInternos();
}
