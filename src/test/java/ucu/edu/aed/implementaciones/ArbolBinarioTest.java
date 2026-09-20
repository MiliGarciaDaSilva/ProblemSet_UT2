package ucu.edu.aed.implementaciones;

import junit.framework.TestCase;

import ucu.edu.aed.tda.TDALista;

public class ArbolBinarioTest extends TestCase {

    private ArbolBinario<Integer> arbol;

    protected void setUp(){
        arbol = new ArbolBinario<>();
        int[] claves = {1, 2, 3, 4, 5, 6, 7};
        for (int clave : claves) {
            arbol.insertar(clave);
        }
    }

    public void testEsVacioEnArbolNuevo(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertTrue(vacio.esVacio());
    }

    public void testEsVacioFalseConDatos(){
        assertFalse(arbol.esVacio());
    }

    public void testObtenerRaizEnArbolVacio(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertNull(vacio.obtenerRaiz());
    }

    public void testInsertarEnArbolVacio(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertTrue(vacio.insertar(10));
        assertEquals(Integer.valueOf(10), vacio.obtenerRaiz().getDato());
        assertEquals(1, vacio.cantidadNodos());
    }

    public void testInsertarRepartaEntreRamas(){
        assertEquals(7, arbol.cantidadNodos());
        assertEquals("1,2,4,5,3,6,7", arbol.preOrderString());
    }

    public void testCantidadNodos(){
        assertEquals(7, arbol.cantidadNodos());
    }

    public void testCantidadNodosEnArbolVacio(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertEquals(0, vacio.cantidadNodos());
    }

    public void testCantidadHojas(){
        assertEquals(4, arbol.cantidadHojas());
    }

    public void testCantidadNodosInternos(){
        assertEquals(3, arbol.cantidadNodosInternos());
    }

    public void testAltura(){
        assertEquals(3, arbol.altura());
    }

    public void testAlturaEnArbolVacio(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertEquals(0, vacio.altura());
    }

    public void testPreOrderString(){
        assertEquals("1,2,4,5,3,6,7", arbol.preOrderString());
    }

    public void testInOrderString(){
        assertEquals("4,2,5,1,6,3,7", arbol.inOrderString());
    }

    public void testPostOrderString(){
        assertEquals("4,5,2,6,7,3,1", arbol.postOrderString());
    }

    public void testPreOrderStringEnArbolVacio(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertEquals("", vacio.preOrderString());
    }

    public void testCompletos(){
        TDALista<Integer> completos = arbol.completos();
        assertEquals(3, completos.tamaño());
        assertTrue(completos.contiene(1));
        assertTrue(completos.contiene(2));
        assertTrue(completos.contiene(3));
    }

    public void testCompletosEnArbolVacio(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertEquals(0, vacio.completos().tamaño());
    }

    public void testEnNivel(){
        assertEquals(1, arbol.enNivel(0).tamaño());
        assertEquals(2, arbol.enNivel(1).tamaño());
        assertEquals(4, arbol.enNivel(2).tamaño());
        assertEquals(0, arbol.enNivel(3).tamaño());
    }

    public void testEnNivelEnArbolVacio(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertEquals(0, vacio.enNivel(0).tamaño());
    }

    public void testCantidadNodosDeNodo(){
        assertEquals(7, arbol.obtenerRaiz().cantidadNodos());
        assertEquals(3, arbol.obtenerRaiz().getHijoIzquierdo().cantidadNodos());
        assertEquals(1, arbol.obtenerRaiz().getHijoIzquierdo().getHijoIzquierdo().cantidadNodos());
    }

    public void testBuscarElementoExistente(){
        assertEquals(Integer.valueOf(7), arbol.buscar(7));
        assertEquals(Integer.valueOf(1), arbol.buscar(1));
    }

    public void testBuscarElementoInexistente(){
        assertNull(arbol.buscar(99));
    }

    public void testBuscarEnArbolVacio(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertNull(vacio.buscar(5));
    }

    public void testAlturaLuegoDeEliminar(){
        ArbolBinario<Integer> arbolChico = new ArbolBinario<>();
        arbolChico.insertar(8);
        arbolChico.insertar(3);
        assertEquals(2, arbolChico.altura());
        arbolChico.eliminar(3);
        assertEquals(1, arbolChico.altura());
    }

    public void testAlturaArbolCompleto(){
        ArbolBinario<Integer> completo = new ArbolBinario<>();
        int[] claves = {4, 2, 6, 1, 3, 5, 7};
        for (int clave : claves) {
            completo.insertar(clave);
        }
        assertEquals(3, completo.altura());
    }

    public void testAlturaNoModificaElArbol(){
        String inordenAntes = arbol.inOrderString();
        int primera = arbol.altura();
        int segunda = arbol.altura();
        assertEquals(primera, segunda);
        assertEquals(inordenAntes, arbol.inOrderString());
        assertEquals(7, arbol.cantidadNodos());
    }

    public void testEliminarHoja(){
        assertTrue(arbol.eliminar(4));
        assertEquals(6, arbol.cantidadNodos());
        assertNull(arbol.buscar(4));
    }

    public void testEliminarRaizConDosHijos(){
        assertTrue(arbol.eliminar(1));
        assertEquals(6, arbol.cantidadNodos());
        assertEquals("5,2,4,3,6,7", arbol.preOrderString());
    }

    public void testEliminarClaveInexistente(){
        assertFalse(arbol.eliminar(99));
        assertEquals(7, arbol.cantidadNodos());
    }

    public void testEliminarEnArbolVacio(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertFalse(vacio.eliminar(5));
    }

    public void testObtenerNivelNoImplementado(){
        assertEquals(-1, arbol.obtenerNivel(5));
    }

    public void testClaveMenorNoImplementado(){
        assertNull(arbol.claveMenor());
    }

    public void testGetContadorNoImplementado(){
        assertEquals(0, arbol.getContador());
    }
}
