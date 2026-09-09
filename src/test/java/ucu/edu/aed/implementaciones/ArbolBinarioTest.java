package ucu.edu.aed.implementaciones;

import junit.framework.TestCase;

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
        assertEquals("1,2,4,6,3,5,7", arbol.preOrderString());
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
        assertEquals("1,2,4,6,3,5,7", arbol.preOrderString());
    }

    public void testInOrderString(){
        assertEquals("4,2,6,1,5,3,7", arbol.inOrderString());
    }

    public void testPostOrderString(){
        assertEquals("4,6,2,5,7,3,1", arbol.postOrderString());
    }

    public void testPreOrderStringEnArbolVacio(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertEquals("", vacio.preOrderString());
    }

    public void testCompletosEnArbolVacio(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertEquals(0, vacio.completos().tamaño());
    }

    public void testEnNivelEnArbolVacio(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertEquals(0, vacio.enNivel(0).tamaño());
    }

    public void testBuscarElementoExistente(){
        assertEquals(Integer.valueOf(6), arbol.buscar(6));
        assertEquals(Integer.valueOf(1), arbol.buscar(1));
    }

    public void testBuscarElementoInexistente(){
        assertNull(arbol.buscar(99));
    }

    public void testBuscarEnArbolVacio(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertEquals(0, vacio.altura());
    }

    /* tests de clave menor (ejercicio 11) */
    public void testClaveMenorConUnSoloNodo(){
        ArbolBinario<Integer> unSoloNodo = new ArbolBinario<>();
        unSoloNodo.insertar(5);
        assertEquals(Integer.valueOf(5), unSoloNodo.claveMenor());
    }

    public void testClaveMenorArbolDesbalanceadoADerecha(){
        ArbolBinario<Integer> a = new ArbolBinario<>();
        a.insertar(5);
        a.insertar(7);
        a.insertar(6);
        a.insertar(8);
        assertEquals(Integer.valueOf(5), a.claveMenor());
    }
    
    /* tests de clave mayor (ejercicio 11)*/
    
    public void testAlturaRamaDegeneradaIzquierda(){
        ArbolBinario<Integer> ramaIzquierda = new ArbolBinario<>();
        int[] claves = {10, 8, 6, 4, 2};
        for (int clave : claves) {
            ramaIzquierda.insertar(clave);
        }
        assertEquals(5, ramaIzquierda.altura());
    }

    public void testAlturaRamaDegeneradaDerecha(){
        ArbolBinario<Integer> ramaDerecha = new ArbolBinario<>();
        int[] claves = {2, 4, 6, 8, 10};
        for (int clave : claves) {
            ramaDerecha.insertar(clave);
        }
        assertEquals(5, ramaDerecha.altura());
    }

    public void testAlturaConDuplicadoNoCambia(){
        ArbolBinario<Integer> arbolDuplicado = new ArbolBinario<>();
        arbolDuplicado.insertar(5);
        arbolDuplicado.insertar(5);
        assertEquals(1, arbolDuplicado.altura());
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

    public void testAlturaRamaMasLargaIzquierda(){
        ArbolBinario<Integer> ramaLargaIzq = new ArbolBinario<>();
        int[] claves = {10, 5, 20, 3, 1};
        for (int clave : claves) {
            ramaLargaIzq.insertar(clave);
        }
        // el subárbol izquierdo tiene altura 3 y el derecho 1
        assertEquals(4, ramaLargaIzq.altura());
    }

    public void testAlturaRamaMasLargaDerecha(){
        ArbolBinario<Integer> ramaLargaDer = new ArbolBinario<>();
        int[] claves = {10, 5, 20, 25, 30};
        for (int clave : claves) {
            ramaLargaDer.insertar(clave);
        }
        // caso espejo del anterior: el subárbol derecho es el más alto
        assertEquals(4, ramaLargaDer.altura());
    }

    public void testAlturaNoModificaElArbol(){
        String inordenAntes = arbol.inOrderString();
        int primera = arbol.altura();
        int segunda = arbol.altura();
        assertEquals(primera, segunda);
        assertEquals(inordenAntes, arbol.inOrderString());
        assertEquals(9, arbol.cantidadNodos());
        assertNull(vacio.buscar(5));
    }

    public void testEliminarHoja(){
        assertTrue(arbol.eliminar(4));
        assertEquals(6, arbol.cantidadNodos());
        assertNull(arbol.buscar(4));
    }

    public void testEliminarRaizConDosHijos(){
        assertTrue(arbol.eliminar(1));
        assertEquals(6, arbol.cantidadNodos());
        assertEquals("6,2,4,3,5,7", arbol.preOrderString());
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
