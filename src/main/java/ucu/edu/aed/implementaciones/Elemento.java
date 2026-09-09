package ucu.edu.aed.implementaciones;

import java.util.function.Consumer;

import ucu.edu.aed.tda.TDAElemento;
import ucu.edu.aed.tda.TDALista;

public class Elemento<T extends Comparable<T>> implements TDAElemento<T>{

    private T dato;
    private TDAElemento<T> hijoIzq;
    private TDAElemento<T> hijoDer;

    public Elemento(T datoElemento){
        this.dato = datoElemento;
        hijoDer = null;
        hijoIzq = null;
    }

    @Override
    public void setHijoIzquierdo(TDAElemento<T> hijoIzquierdo){
        this.hijoIzq = hijoIzquierdo;
    }

    /**
     * Asigna el nodo derecho del nodo actual. Puede ser nulo.
     */
    @Override
    public void setHijoDerecho(TDAElemento<T> hijoDerecho){
        this.hijoDer = hijoDerecho;
    }

    /**
     * Devuelve el hijo derecho del nodo actual. El valor es nulo si no tiene hijo derecho.
     */
    @Override
    public TDAElemento<T> getHijoIzquierdo(){
        return hijoIzq;
    }

    /**
     * Devuelve el hijo izquierdo del nodo actual. El valor es nulo si no tiene hijo izquierdo.
     */
    @Override
    public TDAElemento<T> getHijoDerecho(){
        return hijoDer;
    }

    /**
     * Actualiza el dato del nodo actual.
     */
    @Override
    public void setDato(T datoNuevo){
        dato = datoNuevo;
    }

    /**
     * devuelve el dato del nodo actual.
     */
    @Override
    public T getDato(){
        return dato;
    }

    /**
     * Busca un nodo por un criterio de búsqueda.
     * Si no se encuentra, retorna nulo.
     */
    @Override
    public TDAElemento<T> buscar(Comparable<T> criterioBusqueda){
        TDAElemento<T> resultado = null;
        if (criterioBusqueda.compareTo(this.dato) == 0){
            resultado = this;
        }
        else{
            if (criterioBusqueda.compareTo(this.dato) < 0){
                // Si se cumple al condición el valor que buscamos es menor al actual.
                if (hijoIzq != null){
                resultado = hijoIzq.buscar(criterioBusqueda);
                }
            }
            else{
                // Si se llega hasta acá el valor que buscamos es mayor al actual.
                if (hijoDer != null){
                    resultado = hijoDer.buscar(criterioBusqueda);
                }
            }
        }
        return resultado;
    }
    
    /**
     * Elimina un nodo del árbol según el criterio de búsqueda.
     * Si se encuentra, se retorna el nodo borrado. En otro caso retornar null.
     */
    @Override
    public TDAElemento<T> eliminar(Comparable<T> criterioBusqueda){
        if (criterioBusqueda.compareTo(dato) < 0){
            //está del lado izquierdo
            if (this.hijoIzq != null){
                this.hijoIzq = this.hijoIzq.eliminar(criterioBusqueda);
            }
            return this;
        }
        else{
            if (criterioBusqueda.compareTo(dato) > 0){
                //está del lado derecho
                if (this.hijoDer != null){
                    this.hijoDer = this.hijoDer.eliminar(criterioBusqueda);
                }
                return this;
            }
        }
        return quitarNodo();
    }

    private TDAElemento<T> quitarNodo(){
        if (this.hijoIzq == null){
            return this.hijoDer;
        }
        else{
            if (this.hijoDer == null){
                return this.hijoIzq;
            }
            else{
                //es un nodo completo
                TDAElemento<T> elHijo = this.hijoIzq;
                TDAElemento<T> elPadre = this;
                while (elHijo.getHijoDerecho() != null){
                    elPadre = elHijo;
                    elHijo = elHijo.getHijoDerecho();
                }
                if (elPadre != this){
                    elPadre.setHijoDerecho(elHijo.getHijoIzquierdo());
                    elHijo.setHijoIzquierdo(this.hijoIzq);  
                }
                elHijo.setHijoDerecho(hijoDer);
                return elHijo;
            }
        }
    }
    
    /**
     * Agrega un nuevo elemento al árbol
     * Si el nuevoDato existe, no se agrega
     */
    @Override
    public boolean insertar(T nuevoDato){
        if (nuevoDato.compareTo(this.dato) > 0){
            // NuevoDatos es mayor que el elemento actual
            if (hijoDer == null){
                hijoDer = new Elemento<>(nuevoDato);
                return true;
            }
            else{
                return hijoDer.insertar(nuevoDato);
            }
        }
        else{
            if (nuevoDato.compareTo(this.dato) < 0){
                // nuevoDato es menor al elemento actual
                if (hijoIzq == null){
                    hijoIzq = new Elemento<>(nuevoDato);
                    return true;
                }
                else{
                    return hijoIzq.insertar(nuevoDato);
                }
            }
        }
        return false;
    }
    
    /**
    * Inserta un nuevo dato contando las invocaciones recursivas.
    * Retorna la cantidad de invocaciones si se insertó, o 0 si el dato ya existía.
    */
    @Override
    public int insertarContando(T nuevoDato){
    int dato = nuevoDato.compareTo(this.dato);

    // el dato ya está, no se inserta
    if (dato == 0){
        return 0;
    }

    if (dato > 0){
        // nuevoDato es mayor, va del lado derecho
        if (hijoDer == null){
            hijoDer = new Elemento<>(nuevoDato);
            return 1;
        }
        int contadorHijo = hijoDer.insertarContando(nuevoDato);
        if (contadorHijo == 0){
            return 0;
        }
        return 1 + contadorHijo;
    }
    else{
        // nuevoDato es menor, va del lado izquierdo
        if (hijoIzq == null){
            hijoIzq = new Elemento<>(nuevoDato);
            return 1;
        }
        int contadorHijo = hijoIzq.insertarContando(nuevoDato);
        if (contadorHijo == 0){
            return 0;
        }
        return 1 + contadorHijo;
        }
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
    @Override
    public void inOrder(Consumer<TDAElemento<T>> consumidor){
        if (this.hijoIzq != null){
            this.hijoIzq.inOrder(consumidor);
        }
        consumidor.accept(this);
        if (this.hijoDer != null){
            this.hijoDer.inOrder(consumidor);
        }
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
    @Override
    public void preOrder(Consumer<TDAElemento<T>> consumidor){
        consumidor.accept(this);
        if (hijoIzq != null){
            this.hijoIzq.preOrder(consumidor);
        }
        if (hijoDer != null){
            this.hijoDer.preOrder(consumidor);
        }
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
    @Override
    public void postOrder(Consumer<TDAElemento<T>> consumidor){
        if (hijoIzq != null){
            this.hijoIzq.postOrder(consumidor);
        }
        if (hijoDer != null){
            this.hijoDer.postOrder(consumidor);
        }
        consumidor.accept(this);
    }

    /**
     * retornar true si el nodo es hoja
     */
    @Override
    public boolean esHoja(){
        if (hijoDer == null && hijoIzq == null){
            return true;
        }
        return false;
    }
    
    /**
     * retorna la cantidad de nodos que son hijas
     */
    @Override
    public int cantidadHojas(){
        // si no tiene hijos, es una hoja
        if (this.hijoIzq == null && this.hijoDer == null){
            return 1;
        }
        int contadorIzq = 0;
        int contadorDer = 0;
        // si tiene hijo izquierdo, contar las hojas de ese lado
        if (this.hijoIzq != null){
            contadorIzq = this.hijoIzq.cantidadHojas();
        }
        // si tiene hijo derecho, contar las hojas de ese lado
        if (this.hijoDer != null){
            contadorDer = this.hijoDer.cantidadHojas();
        }
        return contadorIzq + contadorDer;
    }
    
    /**
     * retorna la cantidad de nodos que no son hojas
     */
    @Override
    public int cantidadNodosInternos(){
        // si es hoja, no es interno
        if (this.hijoIzq == null && this.hijoDer == null){
            return 0;
        }
        int contadorIzq = 0;
        int contadorDer = 0;
        if (this.hijoIzq != null){
            contadorIzq = this.hijoIzq.cantidadNodosInternos();
        }
        if (this.hijoDer != null){
            contadorDer = this.hijoDer.cantidadNodosInternos();
        }

        // este nodo sí es interno, por eso el +1
        return 1 + contadorIzq + contadorDer;
    }
    
    /**
     * retorna la cantidad de nodos que los compone
     */
    @Override
    public int cantidadNodos(){
        int contadorIzq = 0;
        int contadorDer = 0;
        if (this.hijoIzq != null){
            contadorIzq = this.hijoIzq.cantidadNodos();
        }
        if (this.hijoDer != null){
            contadorDer = this.hijoDer.cantidadNodos();
        }
        // +1 por este nodo
        return 1 + contadorIzq + contadorDer;
    }
    
    /**
     * retorna la altura de este nodo
     */
    @Override
    public int altura(){
        // hoja: altura 1
        if (this.hijoIzq == null && this.hijoDer == null){
            return 1;
        }
        int alturaIzq = 0;
        int alturaDer = 0;
        if (this.hijoIzq != null){
            alturaIzq = this.hijoIzq.altura();
        }
        if (this.hijoDer != null){
            alturaDer = this.hijoDer.altura();
        }
        // me quedo con el camino más largo y sumo este nodo
        return 1 + Math.max(alturaIzq, alturaDer);
    }
    
    /**
     * retornar el nivel relativo del nodo que coincide con el criterio de búsqueda
     * si no se encuentra, retorna -1
     */
    @Override
    public int obtenerNivel(Comparable<T> criterioBusqueda){
        // este nodo es el buscado
        if (criterioBusqueda.compareTo(dato) == 0){
            return 0;
        }
        // buscar en el subárbol izquierdo
        if (criterioBusqueda.compareTo(dato) < 0){
            if (this.hijoIzq != null){
                int nivel = this.hijoIzq.obtenerNivel(criterioBusqueda);
                if (nivel != -1){
                    return 1 + nivel;
                }
            }
        }
        else{
            // buscar en el subárbol derecho
            if (this.hijoDer != null){
                int nivel = this.hijoDer.obtenerNivel(criterioBusqueda);
                if (nivel != -1){
                    return 1 + nivel;
                }
            }
        }
        // no se encontró
        return -1;
    }
    
    /*
    Devuelve la menor clave del subárbol que tiene como raíz este nodo
    */
    @Override
    public T claveMenor(){
        TDAElemento<T> elementoActual = this;
        while (elementoActual.getHijoIzquierdo() != null){
            elementoActual = elementoActual.getHijoIzquierdo();
        }
        return elementoActual.getDato();
    }

    /*
    Devuelve la mayor clave del subárbol que tiene como raíz este nodo
    */
    public T claveMayor(){
        TDAElemento<T> elementoActual = this;
        while (elementoActual.getHijoDerecho() != null){
            elementoActual = elementoActual.getHijoDerecho();
        }
        return elementoActual.getDato();
    }

    /*
    Devuelve clave anterior en orden lexicográfico, si el nodo no tiene clave anterior devuelve null
    */
    public T claveAnterior(Comparable<T> clave){
        TDAElemento<T> candidato = null;
        TDAElemento<T> elementoActual = this;

        while (clave.compareTo(elementoActual.getDato()) != 0) {
            if (clave.compareTo(elementoActual.getDato()) < 0) {
                elementoActual = elementoActual.getHijoIzquierdo();
            } else if (clave.compareTo(elementoActual.getDato()) > 0){
                candidato = elementoActual; // guardamos el candidato antecesor
                elementoActual = elementoActual.getHijoDerecho();
            }
        }

        if (clave.compareTo(elementoActual.getDato()) == 0) { // encontramos la clave buscada
            if (elementoActual.getHijoIzquierdo() == null) {
                if (candidato != null) {
                    return candidato.getDato();
                } else {
                    return null;
                }
            } else {
                elementoActual = elementoActual.getHijoIzquierdo(); // realizamos la busqueda de la calve mayor del subárbol izquierdo
                while (elementoActual.getHijoDerecho() != null) {
                    elementoActual = elementoActual.getHijoDerecho();
                }
                return elementoActual.getDato();
            }
        }
        return null;
    }

    public int cantidadNodosEnNivel(int nivel){
        if (nivel == 0){
            return 1;
        }
        int cantidadIzq = (hijoIzq != null) ? hijoIzq.cantidadNodosEnNivel(nivel - 1) : 0;
        int cantidadDer = (hijoDer != null) ? hijoDer.cantidadNodosEnNivel(nivel - 1) : 0;
        return cantidadIzq + cantidadDer;
    }

    public TDALista<String> hojasConNivel(int nivelActual){
        TDALista<String> resultado = new ListaEnlazada<>();
        if (this.esHoja()){
            resultado.agregar(this.dato + " (nivel " + nivelActual + ")");
        } else {
            if (hijoIzq != null){
                TDALista<String> hojasIzq = hijoIzq.hojasConNivel(nivelActual + 1);
                for (int i = 0; i < hojasIzq.tamaño(); i++){
                    resultado.agregar(hojasIzq.obtener(i));
                }
            }
            if (hijoDer != null){
                TDALista<String> hojasDer = hijoDer.hojasConNivel(nivelActual + 1);
                for (int i = 0; i < hojasDer.tamaño(); i++){
                    resultado.agregar(hojasDer.obtener(i));
                }
            }
        }
        return resultado;
    }

    public boolean esArbolDeBusqueda(T minPermitido, T maxPermitido){
        if (minPermitido != null && this.dato.compareTo(minPermitido) <= 0){
            return false;
        }
        if (maxPermitido != null && this.dato.compareTo(maxPermitido) >= 0){
            return false;
        }
        boolean izqOk = (hijoIzq == null) || hijoIzq.esArbolDeBusqueda(minPermitido, this.dato);
        boolean derOk = (hijoDer == null) || hijoDer.esArbolDeBusqueda(this.dato, maxPermitido);
        return izqOk && derOk;
    }

    @Override
    public TDALista<T> completos(){
        TDALista<T> resultado = new ListaEnlazada<>();
        if (this.hijoIzq != null && this.hijoDer != null){
            resultado.agregar(this.dato);
        }
        if (this.hijoIzq != null){
            TDALista<T> completosIzq = this.hijoIzq.completos();
            for (int i = 0; i < completosIzq.tamaño(); i++){
                resultado.agregar(completosIzq.obtener(i));
            }
        }
        if (this.hijoDer != null){
            TDALista<T> completosDer = this.hijoDer.completos();
            for (int i = 0; i < completosDer.tamaño(); i++){
                resultado.agregar(completosDer.obtener(i));
            }
        }
        return resultado;
    }

    @Override
    public TDALista<T> enNivel(int nivel){
        TDALista<T> resultado = new ListaEnlazada<>();
        if (nivel == 0){
            resultado.agregar(this.dato);
            return resultado;
        }
        if (this.hijoIzq != null){
            TDALista<T> izq = this.hijoIzq.enNivel(nivel - 1);
            for (int i = 0; i < izq.tamaño(); i++){
                resultado.agregar(izq.obtener(i));
            }
        }
        if (this.hijoDer != null){
            TDALista<T> der = this.hijoDer.enNivel(nivel - 1);
            for (int i = 0; i < der.tamaño(); i++){
                resultado.agregar(der.obtener(i));
            }
        }
        return resultado;
    }

}
