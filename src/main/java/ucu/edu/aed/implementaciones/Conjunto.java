package ucu.edu.aed.implementaciones;

import ucu.edu.aed.tda.TDAConjunto;

public class Conjunto<T> extends ListaEnlazada<T> implements TDAConjunto<T> {
        @Override
    public void agregar(T elem) {
        if (!contiene(elem)) {
            super.agregar(elem);
        }
    }
    
        @Override
    public void agregar(int index, T elem) {
        if (!contiene(elem)) {
            super.agregar(index, elem);
        }
    }

    @Override
    public TDAConjunto<T> union(TDAConjunto<T> otro) {
        Conjunto<T> resultado = new Conjunto<>();

        for (int i = 0; i < this.tamanio; i++) {
            resultado.agregar(this.obtener(i));
        }

        for (int i = 0; i < otro.tamaño(); i++) {
            resultado.agregar(otro.obtener(i));
        }

        return resultado;
    }

    @Override
    public TDAConjunto<T> interseccion(TDAConjunto<T> otro) {
        Conjunto<T> resultado = new Conjunto<>();

        for (int i = 0; i < this.tamanio; i++) {
            T elemento = this.obtener(i);

            if (otro.contiene(elemento)) {
                resultado.agregar(elemento);
            }
        }

        return resultado;
    }
    @Override
    public TDAConjunto<T> diferencia(TDAConjunto<T> otro) {
        Conjunto<T> resultado = new Conjunto<>();

        for (int i = 0; i < this.tamanio; i++) {
            T elemento = this.obtener(i);

            if (!otro.contiene(elemento)) {
                resultado.agregar(elemento);
            }
        }

        return resultado;
    }
    @Override
    public boolean esSubconjuntoDe(TDAConjunto<T> otro) {
        for (int i = 0; i < this.tamanio; i++) {
            if (!otro.contiene(this.obtener(i))) {
                return false;
            }
        }

        return true;
    }
}
