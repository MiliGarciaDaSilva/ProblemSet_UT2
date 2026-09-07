package ucu.edu.aed.implementaciones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import ucu.edu.aed.tda.TDALista;

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

    public void testCantidadHojasEnArbolVacio(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertEquals(0, vacio.cantidadHojas());
    }

    public void testCantidadHojasConUnSoloNodo(){
        ArbolBinario<Integer> unSoloNodo = new ArbolBinario<>();
        unSoloNodo.insertar(1);
        assertEquals(1, unSoloNodo.cantidadHojas());
    }

    public void testCantidadHojasRamaDegenerada(){
        ArbolBinario<Integer> ramaIzquierda = new ArbolBinario<>();
        int[] claves = {10, 8, 6, 4, 2};
        for (int clave : claves) {
            ramaIzquierda.insertar(clave);
        }
        assertEquals(1, ramaIzquierda.cantidadHojas());
    }

    public void testCantidadHojasLuegoDeEliminar(){
        assertTrue(arbol.eliminar(4));
        assertEquals(3, arbol.cantidadHojas());
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

    public void testObtenerNivelRaiz(){
        assertEquals(0, arbol.obtenerNivel(8));
    }

    public void testObtenerNivelHijosDirectos(){
        assertEquals(1, arbol.obtenerNivel(3));
        assertEquals(1, arbol.obtenerNivel(10));
    }

    public void testObtenerNivelNietos(){
        assertEquals(2, arbol.obtenerNivel(1));
        assertEquals(2, arbol.obtenerNivel(6));
        assertEquals(2, arbol.obtenerNivel(14));
    }

    public void testObtenerNivelBisnietos(){
        assertEquals(3, arbol.obtenerNivel(4));
        assertEquals(3, arbol.obtenerNivel(7));
        assertEquals(3, arbol.obtenerNivel(13));
    }

    public void testObtenerNivelClaveInexistente(){
        assertEquals(-1, arbol.obtenerNivel(99));
    }

    public void testObtenerNivelEnArbolVacio(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertEquals(-1, vacio.obtenerNivel(5));
    }

    public void testAltura(){
        assertEquals(4, arbol.altura());
    }

    public void testAlturaConUnSoloNodo(){
        ArbolBinario<Integer> unSoloNodo = new ArbolBinario<>();
        unSoloNodo.insertar(1);
        assertEquals(1, unSoloNodo.altura());
    }

    public void testAlturaEnArbolVacio(){
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
    }

    public void testAlturaLuegoDeEliminarNivelMasProfundo(){
        // 4, 7 y 13 son las tres hojas del cuarto nivel
        arbol.eliminar(4);
        arbol.eliminar(7);
        arbol.eliminar(13);
        assertEquals(3, arbol.altura());
    }

    public void testAlturaLuegoDeVaciarElArbol(){
        int[] claves = {8, 3, 10, 1, 6, 14, 4, 7, 13};
        for (int clave : claves) {
            arbol.eliminar(clave);
        }
        assertTrue(arbol.esVacio());
        assertEquals(0, arbol.altura());
    }

    public void testCompletos(){
        TDALista<Integer> completos = arbol.completos();
        assertEquals(3, completos.tamaño());
        assertTrue(completos.contiene(8));
        assertTrue(completos.contiene(3));
        assertTrue(completos.contiene(6));
    }

    public void testCompletosEnArbolVacio(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertEquals(0, vacio.completos().tamaño());
    }

    public void testCompletosSinNodosCompletos(){
        ArbolBinario<Integer> ramaIzquierda = new ArbolBinario<>();
        int[] claves = {10, 8, 6, 4, 2};
        for (int clave : claves) {
            ramaIzquierda.insertar(clave);
        }
        assertEquals(0, ramaIzquierda.completos().tamaño());
    }

    public void testEnNivelRaiz(){
        TDALista<Integer> nivel0 = arbol.enNivel(0);
        assertEquals(1, nivel0.tamaño());
        assertEquals(Integer.valueOf(8), nivel0.obtener(0));
    }

    public void testEnNivelUno(){
        TDALista<Integer> nivel1 = arbol.enNivel(1);
        assertEquals(2, nivel1.tamaño());
        assertTrue(nivel1.contiene(3));
        assertTrue(nivel1.contiene(10));
    }

    public void testEnNivelDos(){
        TDALista<Integer> nivel2 = arbol.enNivel(2);
        assertEquals(3, nivel2.tamaño());
        assertTrue(nivel2.contiene(1));
        assertTrue(nivel2.contiene(6));
        assertTrue(nivel2.contiene(14));
    }

    public void testEnNivelTres(){
        TDALista<Integer> nivel3 = arbol.enNivel(3);
        assertEquals(3, nivel3.tamaño());
        assertTrue(nivel3.contiene(4));
        assertTrue(nivel3.contiene(7));
        assertTrue(nivel3.contiene(13));
    }

    public void testEnNivelInexistente(){
        assertEquals(0, arbol.enNivel(4).tamaño());
    }

    public void testEnNivelEnArbolVacio(){
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        assertEquals(0, vacio.enNivel(0).tamaño());
    }
}
