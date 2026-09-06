package ucu.edu.aed.implementaciones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

public class ArbolBinarioTest extends TestCase {

    private ArbolBinario<Integer> arbol;

    protected void setUp(){
        arbol = new ArbolBinario<>();
        arbol.insertar(8);
        arbol.insertar(3);
        arbol.insertar(10);
        arbol.insertar(1);
        arbol.insertar(6);
        arbol.insertar(14);
        arbol.insertar(4);
        arbol.insertar(7);
        arbol.insertar(13);
    }

    public void testEsVacioEnArbolNuevo(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertTrue(vacio.esVacio());
    }

    public void testEsVacioFalseConDatos(){
        assertFalse(arbol.esVacio());
    }

    public void testInsertarEnArbolVacio(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertTrue(vacio.insertar(8));
        assertEquals(Integer.valueOf(8), vacio.obtenerRaiz().getDato());
        assertEquals(1, vacio.cantidadNodos());
    }

    public void testInsertarNuevoDato(){
        assertTrue(arbol.insertar(20));
        assertEquals(10, arbol.cantidadNodos());
        assertEquals(Integer.valueOf(20), arbol.buscar(20));
    }

    public void testInsertarDuplicadoNoAgrega(){
        assertFalse(arbol.insertar(6));
        assertEquals(9, arbol.cantidadNodos());
    }

    public void testBuscarElementoExistente(){
        assertEquals(Integer.valueOf(6), arbol.buscar(6));
        assertEquals(Integer.valueOf(8), arbol.buscar(8));
        assertEquals(Integer.valueOf(13), arbol.buscar(13));
    }

    public void testBuscarElementoInexistente(){
        assertNull(arbol.buscar(99));
    }

    public void testBuscarEnArbolVacio(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertNull(vacio.buscar(5));
    }

    public void testEliminarHoja(){
        assertTrue(arbol.eliminar(4));
        assertEquals(8, arbol.cantidadNodos());
        assertNull(arbol.buscar(4));
        assertEquals(Integer.valueOf(7), arbol.buscar(7));
    }

    public void testEliminarNodoConUnHijo(){
        assertTrue(arbol.eliminar(10));
        assertEquals(8, arbol.cantidadNodos());
        assertNull(arbol.buscar(10));
        assertEquals(Integer.valueOf(14), arbol.buscar(14));
        assertEquals(Integer.valueOf(13), arbol.buscar(13));
    }

    public void testEliminarNodoConDosHijos(){
        assertTrue(arbol.eliminar(6));
        assertEquals(8, arbol.cantidadNodos());
        assertNull(arbol.buscar(6));
        assertEquals(Integer.valueOf(4), arbol.buscar(4));
        assertEquals(Integer.valueOf(7), arbol.buscar(7));
    }

    public void testEliminarClaveInexistente(){
        assertFalse(arbol.eliminar(99));
        assertEquals(9, arbol.cantidadNodos());
    }

    public void testEliminarEnArbolVacio(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertFalse(vacio.eliminar(5));
    }

    public void testCantidadNodos(){
        assertEquals(9, arbol.cantidadNodos());
    }

    public void testCantidadNodosEnArbolVacio(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertEquals(0, vacio.cantidadNodos());
    }

    public void testCantidadHojas(){
        assertEquals(4, arbol.cantidadHojas());
    }

    public void testCantidadNodosInternos(){
        assertEquals(5, arbol.cantidadNodosInternos());
    }

    public void testObtenerRaiz(){
        assertEquals(Integer.valueOf(8), arbol.obtenerRaiz().getDato());
    }

    public void testPreOrder(){
        List<Integer> resultado = new ArrayList<>();
        arbol.preOrder(dato -> resultado.add(dato));
        assertEquals(Arrays.asList(8, 3, 1, 6, 4, 7, 10, 14, 13), resultado);
    }

    public void testInOrder(){
        List<Integer> resultado = new ArrayList<>();
        arbol.inOrder(dato -> resultado.add(dato));
        assertEquals(Arrays.asList(1, 3, 4, 6, 7, 8, 10, 13, 14), resultado);
    }

    public void testPostOrder(){
        List<Integer> resultado = new ArrayList<>();
        arbol.postOrder(dato -> resultado.add(dato));
        assertEquals(Arrays.asList(1, 4, 7, 6, 3, 13, 14, 10, 8), resultado);
    }

    public void testInOrderString(){
        assertEquals("1,3,4,6,7,8,10,13,14", arbol.inOrderString());
    }

    public void testPostOrderString(){
        assertEquals("1,4,7,6,3,13,14,10,8", arbol.postOrderString());
    }

    public void testInOrderStringEnArbolVacio(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertEquals("", vacio.inOrderString());
    }

    public void testPostOrderStringEnArbolVacio(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertEquals("", vacio.postOrderString());
    }
}
