package ucu.edu.aed.tda;

import java.util.function.Consumer;

/**
 * Define un Tipo de Dato Abstracto (TDA) Árbol Genérico (n-ario).
 *
 * <p>A diferencia del árbol binario, un nodo puede tener cualquier cantidad de hijos,
 * y esa cantidad no se conoce de antemano.</p>
 *
 * <p>A diferencia del árbol binario de búsqueda, no hay criterio de orden que decida
 * la posición de un dato nuevo: la jerarquía es el dato, por lo que al insertar hay que
 * indicar bajo qué padre va. El {@code Comparable} que reciben las operaciones se usa
 * sólo como criterio de igualdad, nunca de orden.</p>
 *
 * @param <T> el tipo de los elementos almacenados en el árbol
 */
public interface TDAArbolGenerico<T> {

    /**
     * Crea la raíz del árbol.
     *
     * @return {@code false} si el árbol ya tenía raíz
     */
    boolean insertarRaiz(T dato);

    /**
     * Agrega el dato como último hijo del nodo que coincide con el criterio.
     *
     * @return {@code false} si el árbol está vacío, si no existe ese padre,
     * o si el dato ya estaba en el árbol
     */
    boolean insertar(Comparable<T> criterioPadre, T dato);

    /**
     * Retorna el dato que coincide con el criterio, o {@code null} si no está.
     */
    T buscar(Comparable<T> criterioBusqueda);

    /**
     * Retorna el elemento raíz del árbol, o {@code null} si el árbol está vacío.
     */
    TDAElementoGenerico<T> obtenerRaiz();

    /**
     * Elimina el nodo que coincide con el criterio y todos sus descendientes.
     *
     * <p>El borrado es en cascada: los descendientes no tienen existencia propia sin
     * su padre (un capítulo que se elimina se lleva sus secciones).</p>
     *
     * @return {@code true} si se eliminó algo
     */
    boolean eliminar(Comparable<T> criterioBusqueda);

    /**
     * Recorre todo el árbol en pre-order.
     */
    void preOrder(Consumer<T> consumidor);

    /**
     * Recorre todo el árbol en post-order.
     */
    void postOrder(Consumer<T> consumidor);

    /**
     * Recorre el árbol nivel por nivel, de arriba hacia abajo y de izquierda a derecha.
     */
    void porNiveles(Consumer<T> consumidor);

    /**
     * Recorre en pre-order el subárbol que cuelga del nodo indicado, incluido ese nodo.
     * Si el nodo no existe, no invoca al consumidor.
     */
    void preOrderDesde(Comparable<T> criterioSubarbol, Consumer<T> consumidor);

    /**
     * Recorre en post-order el subárbol que cuelga del nodo indicado, incluido ese nodo.
     *
     * <p>Es el recorrido adecuado para responder preguntas del estilo "¿puedo cerrar
     * esto?", porque procesa a los descendientes antes que al nodo.</p>
     */
    void postOrderDesde(Comparable<T> criterioSubarbol, Consumer<T> consumidor);

    /**
     * Retorna todos los descendientes del nodo indicado, en pre-order y sin incluirlo.
     * Lista vacía si el nodo no existe.
     */
    TDALista<T> descendientesDe(Comparable<T> criterioSubarbol);

    /**
     * Retorna las hojas del subárbol que cuelga del nodo indicado.
     *
     * <p>Si el nodo es hoja, se retorna a sí mismo. Lista vacía si no existe.</p>
     */
    TDALista<T> hojasDe(Comparable<T> criterioSubarbol);

    /**
     * Retorna los datos de los hijos directos del nodo indicado, de izquierda a derecha.
     * Lista vacía si el nodo no existe.
     */
    TDALista<T> hijosDe(Comparable<T> criterioPadre);

    /**
     * Retorna los datos de los nodos que están en el nivel indicado.
     * La raíz es el nivel 0.
     */
    TDALista<T> enNivel(int nivel);

    String preOrderString();

    String postOrderString();

    String porNivelesString();

    /**
     * Devuelve true si el árbol es vacío.
     */
    boolean esVacio();

    /**
     * Devuelve la altura del árbol. Un árbol vacío tiene altura 0 y uno de un
     * solo nodo tiene altura 1.
     */
    int altura();

    /**
     * Devuelve la cantidad de nodos del árbol.
     */
    int cantidadNodos();

    /**
     * Devuelve la cantidad de nodos que son hojas.
     */
    int cantidadHojas();

    /**
     * Devuelve la cantidad de nodos que NO son hojas.
     */
    int cantidadNodosInternos();

    /**
     * Devuelve la mayor cantidad de hijos que tiene alguno de los nodos del árbol.
     */
    int grado();

    /**
     * Devuelve el nivel del nodo buscado, siendo la raíz el nivel 0.
     * Si no se encuentra, retorna -1.
     */
    int obtenerNivel(Comparable<T> criterioBusqueda);
}
