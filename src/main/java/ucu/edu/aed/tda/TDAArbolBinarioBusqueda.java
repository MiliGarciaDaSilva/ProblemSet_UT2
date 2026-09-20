package ucu.edu.aed.tda;

public interface TDAArbolBinarioBusqueda<T extends Comparable<T>> extends TDAArbolBinario<T> {

    int getContador();

    T claveMenor();

    T claveMayor();

    T claveAnterior(Comparable<T> clave);

    int cantidadNodosEnNivel(int nivel);

    TDALista<String> hojasConNivel();

    boolean esArbolDeBusqueda();
}
