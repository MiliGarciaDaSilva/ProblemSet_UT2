package ucu.edu.aed.implementaciones;

import java.util.function.Consumer;

import ucu.edu.aed.tda.TDAArbolBinario;
import ucu.edu.aed.tda.TDAElemento;

public class ArbolBinario<T extends Comparable<T>> implements TDAArbolBinario<T> {

    private TDAElemento<T> raiz;

    /**
     * Busca y retorna el primer elemento que cumple con el predicado dado.
     *
     * <p>El recorrido del árbol para la búsqueda queda sujeto a la implementación.</p>
     *
     * @param predicate el predicado que define el criterio de búsqueda
     * @return el primer elemento que cumple el criterio, o {@code null}
     * si no existe ninguno
     */
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

    /**
     * Retorna el elemento raíz del árbol.
     *
     * @return el elemento raíz del árbol, o {@code null} si el árbol está vacío
     */
    public TDAElemento<T> obtenerRaiz(){
        return raiz;
    }

    /**
     * Elimina el o los nodos según el criterio de búsqueda.
     *
     * @param criterioBusqueda el predicado que define qué elementos deben ser eliminados
     * @return {@code true} si al menos un elemento fue eliminado;
     * {@code false} en caso contrario
     */
    public boolean eliminar(Comparable<T> criterioBusqueda){
        if (raiz == null){
            return false;
        }
        if (raiz.buscar(criterioBusqueda) == null)
        {
            return false;
        }
        else {
            raiz = raiz.eliminar(criterioBusqueda);
            return true;
        }
    }

    /**
     * Agrega un dato al árbol.
     *
     * <p>Si el dato ya existe en el árbol, no se agrega nuevamente.</p>
     *
     * @param dato el elemento a insertar
     * @return {@code true} si el elemento fue agregado correctamente;
     * {@code false} si el elemento ya existía y no fue agregado
     */
    public boolean insertar(T dato){
        if (raiz == null){
            raiz = new Elemento<>(dato);
            return true;
        }
        else{
            return raiz.insertar(dato);
        }
        }

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
    public void inOrder(Consumer<T> consumidor){
    if (raiz == null){
        return;
    }
    raiz.inOrder(nodo -> consumidor.accept(nodo.getDato()));
    }

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
    public void preOrder(Consumer<T> consumidor){
    if (raiz == null){
        return;
    }
    raiz.inOrder(nodo -> consumidor.accept(nodo.getDato()));
    }

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
    public void postOrder(Consumer<T> consumidor){
    if (raiz == null){
        return;
    }
    raiz.inOrder(nodo -> consumidor.accept(nodo.getDato()));
    }

    /**
     * Devuelve true si el árbol es vacío
     */
    public boolean esVacio(){
        return raiz == null;
    }

    /**
     * Devuelve la cantidad de nodos del árbol
     **/
    public int cantidadNodos(){
    if (raiz == null){
        return 0;
    }
    return raiz.cantidadNodos();
    }

    /**
     * Devuelve la cantidad de nodos que son hojas
     */
    public int cantidadHojas(){
    if (raiz == null){
        return 0;
    }
    return raiz.cantidadHojas();
    }
    

    /**
     * Devuelve la cantidad de nodos que NO son hojas
     */
    public int cantidadNodosInternos(){
    if (raiz == null){
        return 0;
    }
    return raiz.cantidadNodosInternos();
    }
    
}
