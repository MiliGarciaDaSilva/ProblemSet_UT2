package ucu.edu.aed.tda;

/**
 * Define un Tipo de Dato Abstracto (TDA) Heap binario, usado como cola de prioridad.
 *
 * <p>Un heap es un árbol binario que mantiene dos invariantes después de cada
 * operación:</p>
 * <ul>
 *   <li><b>Orden:</b> todo padre es menor o igual que sus hijos. Es una relación
 *       local padre-hijo; no hay ningún orden entre hermanos. Por transitividad,
 *       el mínimo de todo el heap queda en la raíz.</li>
 *   <li><b>Forma:</b> el árbol es completo, es decir, todos los niveles están llenos
 *       salvo el último, que se llena de izquierda a derecha sin dejar huecos.</li>
 * </ul>
 *
 * <p>A diferencia del árbol binario de búsqueda, el heap sostiene un orden
 * <i>parcial</i>: lo único que garantiza es el acceso al mínimo. Sus datos no están
 * ordenados y no existe ningún recorrido que los devuelva ordenados. Ese compromiso
 * más débil es justamente lo que lo hace barato de mantener.</p>
 *
 * <p>Como consecuencia, este TDA no ofrece búsqueda por clave: encontrar un elemento
 * cualquiera dentro de un heap obliga a recorrerlo entero, O(n). Si hace falta buscar,
 * la estructura adecuada es un árbol de búsqueda, no un heap.</p>
 *
 * <p>El orden de salida no se materializa en la estructura: se produce de a un
 * elemento, en cada llamada a {@link #eliminar()}.</p>
 *
 * @param <T> el tipo de los elementos almacenados, que debe ser comparable porque
 *            la prioridad se decide con su {@code compareTo}
 */
public interface TDAHeap<T extends Comparable<T>> {

    /**
     * Agrega el dato al heap. O(log n).
     *
     * <p>No hay búsqueda de posición: el dato entra en la última posición libre y
     * después se repara el invariante subiéndolo mientras sea menor que su padre.</p>
     *
     * @return {@code false} si el dato es {@code null}
     */
    boolean insertar(T dato);

    /**
     * Saca y retorna el elemento de mayor prioridad, que es el mínimo. O(log n).
     *
     * @return el mínimo, o {@code null} si el heap está vacío
     */
    T eliminar();

    /**
     * Retorna el elemento de mayor prioridad sin sacarlo. O(1), porque por el
     * invariante de orden siempre está en la raíz.
     *
     * @return el mínimo, o {@code null} si el heap está vacío
     */
    T minimo();

    /**
     * Devuelve true si el heap no tiene elementos.
     */
    boolean esVacio();

    /**
     * Devuelve la cantidad de elementos almacenados.
     */
    int cantidad();
}
