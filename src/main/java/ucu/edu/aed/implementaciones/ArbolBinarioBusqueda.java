package ucu.edu.aed.implementaciones;

import ucu.edu.aed.tda.TDAArbolBinarioBusqueda;
import ucu.edu.aed.tda.TDAElemento;

public class ArbolBinarioBusqueda<T extends Comparable<T>> extends ArbolBinario<T> implements TDAArbolBinarioBusqueda<T> {

    private int contador;

    @Override
    public T buscar(Comparable<T> predicate){
        if (raiz == null){
            return null;
        }
        else {
            TDAElemento<T> resultado = raiz.buscar(predicate);
            if (resultado == null){
                return null;
            }
            else{
                return resultado.getDato();
            }
        }
    }

    @Override
    public boolean eliminar(Comparable<T> criterioBusqueda){
        if (raiz == null){
            return false;
        }
        if (raiz.buscar(criterioBusqueda) == null){
            return false;
        }
        else {
            raiz = raiz.eliminar(criterioBusqueda);
            return true;
        }
    }

    @Override
    public boolean insertar(T dato){
        if (raiz == null){
            raiz = new Elemento<>(dato);
            contador = 1;
        }
        else{
            contador = raiz.insertarContando(dato);
        }
        System.out.println("contador = " + contador);
        return contador > 0;
    }

    @Override
    public int obtenerNivel(Comparable<T> criterioBusqueda){
        if (raiz == null){
            return -1;
        }
        return raiz.obtenerNivel(criterioBusqueda);
    }

    @Override
    public int getContador(){
        return contador;
    }

    @Override
    public T claveMenor(){
        if (raiz == null){
            return null;
        }
        return raiz.claveMenor();
    }
}
