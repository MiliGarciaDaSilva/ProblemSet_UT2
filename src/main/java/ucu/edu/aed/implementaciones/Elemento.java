package ucu.edu.aed.implementaciones;

import java.util.function.Consumer;

import ucu.edu.aed.tda.TDAElemento;

public class Elemento<T extends Comparable<T>> implements TDAElemento<T>{

        private T dato;
        private TDAElemento<T> hijoIzq;
        private TDAElemento<T> hijoDer;

        public void setHijoIzquierdo(TDAElemento<T> hijoIzquierdo){

        }
    
        /**
         * Asigna el nodo derecho del nodo actual. Puede ser nulo.
         */
        public void setHijoDerecho(TDAElemento<T> hijoDerecho){

        }
    
        /**
         * Devuelve el hijo derecho del nodo actual. El valor es nulo si no tiene hijo derecho.
         */
        public TDAElemento<T> getHijoIzquierdo(){
            return hijoIzq;
        }
    
        /**
         * Devuelve el hijo izquierdo del nodo actual. El valor es nulo si no tiene hijo izquierdo.
         */
        public TDAElemento<T> getHijoDerecho(){
            return hijoDer;
        }
    
        /**
         * Actualiza el dato del nodo actual.
         */
        public void setDato(T dato){

        }
    
        /**
         * devuelve el dato del nodo actual.
         */
        public T getDato(){
            return dato;
        }
    
        /**
         * Busca un nodo por un criterio de búsqueda.
         * Si no se encuentra, retorna nulo.
         */
        public TDAElemento<T> buscar(Comparable<T> criterioBusqueda){
            return hijoIzq;
        }
    
        /**
         * Elimina un nodo del árbol según el criterio de búsqueda.
         * Si se encuentra, se retorna el nodo borrado. En otro caso retornar null.
         */
        public TDAElemento<T> eliminar(Comparable<T> criterioBusqueda){
            return hijoIzq;
        }
    
        /**
         * Agrega un nuevo elemento al árbol
         * Si el nuevoDato existe, no se agrega
         */
        public boolean insertar(T nuevoDato){
            return true;
        }
    
        /**
         * {@snippet :
         * // ejemplo de uso
         * elemento.inOrder(dato ->{
         *     // procesar dato
         *     // esta función se llama tantas veces como nodos halla en el árbol
         * });
         *}
         */
        public void inOrder(Consumer<TDAElemento<T>> consumidor){

        }
    
        /**
         * {@snippet :
         * // ejemplo de uso
         * elemento.preOrder(dato ->{
         *     // procesar dato
         *     // esta función se llama tantas veces como nodos halla en el árbol
         * });
         *}
         */
        public void preOrder(Consumer<TDAElemento<T>> consumidor){

        }
    
        /**
         * {@snippet :
         * // ejemplo de uso
         * elemento.postOrder(dato ->{
         *     // procesar dato
         *     // esta función se llama tantas veces como nodos halla en el árbol
         * });
         *}
         */
        public void postOrder(Consumer<TDAElemento<T>> consumidor){

        }
    
        /**
         * retornar true si el nodo es hoja
         */
        public boolean esHoja(){
            return true;
        }
    
        /**
         * retorna la cantidad de nodos que son hijas
         */
        public int cantidadHojas(){
            return 0;
        }
    
        /**
         * retorna la cantidad de nodos que no son hojas
         */
        public int cantidadNodosInternos(){
            return 0;
        }
    
        /**
         * retorna la cantidad de nodos que los compone
         */
        public int cantidadNodos(){
            return 0;
        }
    
        /**
         * retorna la altura de este nodo
         */
        public int altura(){
            return 0;
        }
    
        /**
         * retornar el nivel relativo del nodo que coincide con el criterio de búsqueda
         * si no se encuentra, retorna -1
         */
        public int obtenerNivel(Comparable<T> criterioBusqueda){
            return 0;
        }
    
}
