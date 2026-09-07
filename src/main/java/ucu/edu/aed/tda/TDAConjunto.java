package ucu.edu.aed.tda;

/**
 * Define un Tipo de Dato Abstracto (TDA) Conjunto genérico.
 *
 * <p>Un conjunto es una colección de elementos sin duplicados, donde
 * no importa el orden de almacenamiento.</p>
 *
 * <p>Las operaciones principales permiten agregar, remover, consultar
 * pertenencia y realizar operaciones clásicas de teoría de conjuntos
 * como unión, intersección y diferencia.</p>
 *
 * @param <T> el tipo de los elementos almacenados en el conjunto
 */
public interface TDAConjunto<T> extends TDALista<T> {
    /**
     * Retorna un nuevo conjunto que representa la unión entre este conjunto
     * y el conjunto recibido como parámetro.
     *
     * @param otro el otro conjunto
     * @return un nuevo conjunto con todos los elementos de ambos conjuntos
     */
    TDAConjunto<T> union(TDAConjunto<T> otro);

    /**
     * Retorna un nuevo conjunto que representa la intersección entre este conjunto
     * y el conjunto recibido como parámetro.
     *
     * @param otro el otro conjunto
     * @return un nuevo conjunto con los elementos comunes a ambos conjuntos
     */
    TDAConjunto<T> interseccion(TDAConjunto<T> otro);

    /**
     * Retorna un nuevo conjunto que representa la diferencia entre este conjunto
     * y el conjunto recibido como parámetro.
     *
     * <p>El resultado contiene los elementos que pertenecen a este conjunto
     * pero no al conjunto dado.</p>
     *
     * @param otro el otro conjunto
     * @return un nuevo conjunto con la diferencia entre ambos conjuntos
     */
    TDAConjunto<T> diferencia(TDAConjunto<T> otro);

    /**
     * Determina si este conjunto es subconjunto del conjunto dado.
     *
     * @param otro el otro conjunto
     * @return {@code true} si todos los elementos de este conjunto pertenecen también a {@code otro};
     * {@code false} en caso contrario
     */
    boolean esSubconjuntoDe(TDAConjunto<T> otro);
}
