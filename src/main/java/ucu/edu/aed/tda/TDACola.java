package ucu.edu.aed.tda;

/**
 * Define un Tipo de Dato Abstracto (TDA) Cola genérica.
 *
 * <p>Una cola es una estructura de datos lineal que sigue la política
 * FIFO ({@code First In, First Out}), es decir, el primer elemento en ingresar
 * es el primero en salir.</p>
 *
 * <p>Esta interfaz extiende {@link TDALista}, por lo que además hereda las
 * operaciones generales de una lista. Sin embargo, las operaciones propias
 * de la cola modelan el acceso restringido a sus extremos: inserción al final
 * y extracción desde el frente.</p>
 *
 * @param <T> el tipo de los elementos almacenados en la cola
 */
public interface TDACola<T> extends TDALista<T> {

    /**
     * Retorna el elemento ubicado al frente de la cola, sin removerlo.
     *
     * @return el elemento ubicado al frente de la cola
     * @throws java.util.NoSuchElementException si la cola está vacía
     */
    T frente();

    /**
     * Inserta un elemento al final de la cola.
     *
     * @param dato el elemento a insertar
     * @return {@code true} si el elemento fue agregado correctamente;
     *         {@code false} en caso contrario
     */
    boolean poneEnCola(T dato);

    /**
     * Remueve y retorna el elemento ubicado al frente de la cola.
     *
     * <p>Luego de la operación, el siguiente elemento, si existe,
     * pasa a ocupar el nuevo frente.</p>
     *
     * @return el elemento removido del frente de la cola
     * @throws java.util.NoSuchElementException si la cola está vacía
     */
    T quitaDeCola();
}