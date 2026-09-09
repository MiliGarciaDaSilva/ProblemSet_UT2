package ucu.edu.aed.implementaciones;

import java.util.function.Consumer;

import ucu.edu.aed.tda.TDAArbolGenerico;
import ucu.edu.aed.tda.TDAElementoGenerico;
import ucu.edu.aed.tda.TDALista;

/**
 * Árbol genérico (n-ario) implementado con nodos hijo izquierdo - hermano derecho.
 *
 * <p>Igual que en el árbol binario, la clase árbol es una cáscara sobre la raíz y la
 * recursión vive en el elemento.</p>
 */
public class ArbolGenerico<T extends Comparable<T>> implements TDAArbolGenerico<T> {

    protected TDAElementoGenerico<T> raiz;

    @Override
    public boolean insertarRaiz(T dato){
        if (raiz != null){
            return false;
        }
        raiz = new ElementoGenerico<>(dato);
        return true;
    }

    @Override
    public boolean insertar(Comparable<T> criterioPadre, T dato){
        if (raiz == null){
            return false;
        }
        if (buscar(dato) != null){
            // el dato ya existe, no se agrega
            return false;
        }
        TDAElementoGenerico<T> padre = raiz.buscar(criterioPadre);
        if (padre == null){
            return false;
        }
        padre.agregarHijo(new ElementoGenerico<>(dato));
        return true;
    }

    @Override
    public T buscar(Comparable<T> criterioBusqueda){
        TDAElementoGenerico<T> resultado = buscarElemento(criterioBusqueda);
        if (resultado == null){
            return null;
        }
        return resultado.getDato();
    }

    private TDAElementoGenerico<T> buscarElemento(Comparable<T> criterioBusqueda){
        if (raiz == null){
            return null;
        }
        return raiz.buscar(criterioBusqueda);
    }

    @Override
    public TDAElementoGenerico<T> obtenerRaiz(){
        return raiz;
    }

    @Override
    public boolean eliminar(Comparable<T> criterioBusqueda){
        if (raiz == null){
            return false;
        }
        if (raiz.buscar(criterioBusqueda) == null){
            return false;
        }
        raiz = raiz.eliminar(criterioBusqueda);
        return true;
    }

    @Override
    public void preOrder(Consumer<T> consumidor){
        if (raiz == null){
            return;
        }
        raiz.preOrder(nodo -> consumidor.accept(nodo.getDato()));
    }

    @Override
    public void postOrder(Consumer<T> consumidor){
        if (raiz == null){
            return;
        }
        raiz.postOrder(nodo -> consumidor.accept(nodo.getDato()));
    }

    /**
     * Recorrido por niveles: la lista se usa como cola (FIFO).
     *
     * <p>Es el único recorrido que no sale solo con recursión, porque va a lo ancho y no
     * a lo hondo. La cola guarda los nodos ya visitados cuyos hijos todavía no se
     * visitaron; como los hijos se encolan detrás de lo que falta del nivel actual,
     * recién salen cuando ese nivel terminó.</p>
     *
     * <p>Sobre la {@code ListaEnlazada} las dos operaciones son O(1): {@code agregar} usa
     * el puntero a la cola y {@code remover(0)} desengancha la cabeza.</p>
     */
    @Override
    public void porNiveles(Consumer<T> consumidor){
        if (raiz == null){
            return;
        }
        TDALista<TDAElementoGenerico<T>> pendientes = new ListaEnlazada<>();
        pendientes.agregar(raiz);
        while (!pendientes.esVacio()){
            TDAElementoGenerico<T> actual = pendientes.remover(0);
            consumidor.accept(actual.getDato());
            TDAElementoGenerico<T> hijoActual = actual.getPrimerHijo();
            while (hijoActual != null){
                pendientes.agregar(hijoActual);
                hijoActual = hijoActual.getHermanoDerecho();
            }
        }
    }

    @Override
    public void preOrderDesde(Comparable<T> criterioSubarbol, Consumer<T> consumidor){
        TDAElementoGenerico<T> nodo = buscarElemento(criterioSubarbol);
        if (nodo == null){
            return;
        }
        nodo.preOrder(elemento -> consumidor.accept(elemento.getDato()));
    }

    @Override
    public void postOrderDesde(Comparable<T> criterioSubarbol, Consumer<T> consumidor){
        TDAElementoGenerico<T> nodo = buscarElemento(criterioSubarbol);
        if (nodo == null){
            return;
        }
        nodo.postOrder(elemento -> consumidor.accept(elemento.getDato()));
    }

    @Override
    public TDALista<T> descendientesDe(Comparable<T> criterioSubarbol){
        TDALista<T> resultado = new ListaEnlazada<>();
        TDAElementoGenerico<T> nodo = buscarElemento(criterioSubarbol);
        if (nodo == null){
            return resultado;
        }
        nodo.preOrder(elemento -> {
            // el nodo no es descendiente de sí mismo
            if (elemento != nodo){
                resultado.agregar(elemento.getDato());
            }
        });
        return resultado;
    }

    @Override
    public TDALista<T> hojasDe(Comparable<T> criterioSubarbol){
        TDALista<T> resultado = new ListaEnlazada<>();
        TDAElementoGenerico<T> nodo = buscarElemento(criterioSubarbol);
        if (nodo == null){
            return resultado;
        }
        nodo.preOrder(elemento -> {
            if (elemento.esHoja()){
                resultado.agregar(elemento.getDato());
            }
        });
        return resultado;
    }

    @Override
    public TDALista<T> hijosDe(Comparable<T> criterioPadre){
        TDAElementoGenerico<T> padre = buscarElemento(criterioPadre);
        if (padre == null){
            return new ListaEnlazada<>();
        }
        return padre.hijos();
    }

    @Override
    public TDALista<T> enNivel(int nivel){
        if (raiz == null){
            return new ListaEnlazada<>();
        }
        return raiz.enNivel(nivel);
    }

    @Override
    public String preOrderString(){
        StringBuilder resultado = new StringBuilder();
        preOrder(dato -> resultado.append(dato).append(","));
        if (resultado.length() > 0){
            resultado.setLength(resultado.length() - 1);
        }
        return resultado.toString();
    }

    @Override
    public String postOrderString(){
        StringBuilder resultado = new StringBuilder();
        postOrder(dato -> resultado.append(dato).append(","));
        if (resultado.length() > 0){
            resultado.setLength(resultado.length() - 1);
        }
        return resultado.toString();
    }

    @Override
    public String porNivelesString(){
        StringBuilder resultado = new StringBuilder();
        porNiveles(dato -> resultado.append(dato).append(","));
        if (resultado.length() > 0){
            resultado.setLength(resultado.length() - 1);
        }
        return resultado.toString();
    }

    @Override
    public boolean esVacio(){
        return raiz == null;
    }

    @Override
    public int altura(){
        if (raiz == null){
            return 0;
        }
        return raiz.altura();
    }

    @Override
    public int cantidadNodos(){
        if (raiz == null){
            return 0;
        }
        return raiz.cantidadNodos();
    }

    @Override
    public int cantidadHojas(){
        if (raiz == null){
            return 0;
        }
        return raiz.cantidadHojas();
    }

    @Override
    public int cantidadNodosInternos(){
        if (raiz == null){
            return 0;
        }
        return raiz.cantidadNodosInternos();
    }

    @Override
    public int grado(){
        if (raiz == null){
            return 0;
        }
        return raiz.grado();
    }

    @Override
    public int obtenerNivel(Comparable<T> criterioBusqueda){
        if (raiz == null){
            return -1;
        }
        return raiz.obtenerNivel(criterioBusqueda);
    }

}
