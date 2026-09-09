package ucu.edu.aed.tda;

import java.util.function.Consumer;

/**
 * Modela un nodo de un árbol genérico (n-ario).
 *
 * <p>La representación elegida es <b>hijo izquierdo - hermano derecho</b>: cada nodo
 * guarda una única referencia a su primer hijo y otra a su siguiente hermano. Los hijos
 * de un nodo forman entonces una cadena enlazada que arranca en {@code primerHijo} y se
 * recorre saltando de hermano en hermano. De esa manera un nodo puede tener n hijos
 * usando siempre dos punteros, igual que un nodo de árbol binario.</p>
 *
 * <pre>
 *            (A)
 *             |            primerHijo:      A -&gt; B,   B -&gt; E
 *            (B) - (C) - (D)                hermanoDerecho: B -&gt; C -&gt; D
 *             |
 *            (E) - (F)
 * </pre>
 *
 * <p>Salvo que se aclare lo contrario, cada operación trabaja sobre el subárbol que
 * tiene a este nodo como raíz: <b>no</b> incluye a sus hermanos.</p>
 *
 * <p>El {@code Comparable} recibido en las operaciones se usa únicamente como criterio
 * de igualdad ({@code compareTo(...) == 0}), nunca como criterio de orden: en un árbol
 * genérico la posición de un nodo la decide el padre indicado al insertar.</p>
 *
 * @param <T> el tipo de los datos almacenados en el árbol
 */
public interface TDAElementoGenerico<T> {

    /**
     * Actualiza el dato del nodo actual.
     */
    void setDato(T dato);

    /**
     * Devuelve el dato del nodo actual.
     */
    T getDato();

    /**
     * Asigna el primer hijo del nodo actual. Puede ser nulo.
     */
    void setPrimerHijo(TDAElementoGenerico<T> primerHijo);

    /**
     * Devuelve el primer hijo del nodo actual. El valor es nulo si el nodo es hoja.
     */
    TDAElementoGenerico<T> getPrimerHijo();

    /**
     * Asigna el hermano derecho del nodo actual. Puede ser nulo.
     */
    void setHermanoDerecho(TDAElementoGenerico<T> hermanoDerecho);

    /**
     * Devuelve el hermano derecho del nodo actual, es decir el siguiente hijo del
     * mismo padre. El valor es nulo si es el último hermano.
     */
    TDAElementoGenerico<T> getHermanoDerecho();

    /**
     * Agrega un nodo al final de la cadena de hijos del nodo actual.
     *
     * <p>Se agrega al final para respetar el orden en que se fueron insertando.</p>
     */
    void agregarHijo(TDAElementoGenerico<T> nuevoHijo);

    /**
     * Busca dentro del subárbol un nodo que coincida con el criterio de búsqueda.
     * Si no se encuentra, retorna nulo.
     */
    TDAElementoGenerico<T> buscar(Comparable<T> criterioBusqueda);

    /**
     * Elimina del subárbol el nodo que coincide con el criterio de búsqueda,
     * junto con todos sus descendientes.
     *
     * @return el subárbol ya modificado, o {@code null} si el nodo eliminado
     * es este mismo nodo
     */
    TDAElementoGenerico<T> eliminar(Comparable<T> criterioBusqueda);

    /**
     * Recorre el subárbol en pre-order: primero el nodo y después cada hijo,
     * de izquierda a derecha.
     * {@snippet :
     * elemento.preOrder(nodo ->{
     *     // procesar nodo
     * });
     *}
     */
    void preOrder(Consumer<TDAElementoGenerico<T>> consumidor);

    /**
     * Recorre el subárbol en post-order: primero cada hijo de izquierda a derecha
     * y al final el nodo.
     * {@snippet :
     * elemento.postOrder(nodo ->{
     *     // procesar nodo
     * });
     *}
     */
    void postOrder(Consumer<TDAElementoGenerico<T>> consumidor);

    /**
     * Retorna true si el nodo no tiene hijos.
     */
    boolean esHoja();

    /**
     * Retorna la cantidad de hijos directos del nodo actual.
     */
    int cantidadHijos();

    /**
     * Retorna la cantidad de nodos del subárbol, incluido este nodo.
     */
    int cantidadNodos();

    /**
     * Retorna la cantidad de nodos del subárbol que son hojas.
     */
    int cantidadHojas();

    /**
     * Retorna la cantidad de nodos del subárbol que NO son hojas.
     */
    int cantidadNodosInternos();

    /**
     * Retorna la altura del subárbol. Una hoja tiene altura 1.
     */
    int altura();

    /**
     * Retorna el grado del subárbol, es decir la mayor cantidad de hijos que
     * tiene alguno de sus nodos.
     */
    int grado();

    /**
     * Retorna el nivel relativo del nodo que coincide con el criterio de búsqueda,
     * tomando este nodo como nivel 0. Si no se encuentra, retorna -1.
     */
    int obtenerNivel(Comparable<T> criterioBusqueda);

    /**
     * Retorna los datos de los hijos directos del nodo, de izquierda a derecha.
     */
    TDALista<T> hijos();

    /**
     * Retorna los datos de los nodos que están en el nivel indicado,
     * tomando este nodo como nivel 0.
     */
    TDALista<T> enNivel(int nivel);

    /**
     * Versión de {@link #enNivel(int)} que va acumulando sobre una misma lista.
     *
     * <p>Existe para que la recursión no tenga que crear una lista por nodo y
     * copiarla en el padre: sobre una lista enlazada el acceso por índice es O(i),
     * así que esa copia volvería cuadrático el recorrido.</p>
     */
    void enNivel(int nivel, TDALista<T> acumulador);
}
