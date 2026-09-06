package ucu.edu.aed.ejercicio3;

import junit.framework.TestCase;

import ucu.edu.aed.implementaciones.ArbolBinario;

public class TestEjercicio3 extends TestCase {

    private ArbolBinario<Integer> arbol;

    protected void setUp(){
        arbol = new ArbolBinario<>();
        int[] claves = {12, 25, 14, 1, 33, 88, 45, 2, 7, 66, 5, 99};
        for (int clave : claves) {
            arbol.insertar(clave);
        }
    }

    public void testInordenLuegoDeInsertar(){
        System.out.println("Inorden: " + arbol.inOrderString());
        assertEquals("1,2,5,7,12,14,25,33,45,66,88,99", arbol.inOrderString());
    }

    public void testEliminarSecuencia(){
        assertTrue(arbol.eliminar(99));
        System.out.println("Inorden: " + arbol.inOrderString());
        System.out.println("Preorden: " + arbol.preOrderString());
        System.out.println("Postorden: " + arbol.postOrderString());
        assertEquals("1,2,5,7,12,14,25,33,45,66,88", arbol.inOrderString());
        assertEquals("12,1,2,7,5,25,14,33,88,45,66", arbol.preOrderString());
        assertEquals("5,7,2,1,14,66,45,88,33,25,12", arbol.postOrderString());

        assertFalse(arbol.eliminar(15));
        assertEquals("1,2,5,7,12,14,25,33,45,66,88", arbol.inOrderString());
        assertEquals("12,1,2,7,5,25,14,33,88,45,66", arbol.preOrderString());
        assertEquals("5,7,2,1,14,66,45,88,33,25,12", arbol.postOrderString());

        assertTrue(arbol.eliminar(2));
        System.out.println("Inorden: " + arbol.inOrderString());
        System.out.println("Preorden: " + arbol.preOrderString());
        System.out.println("Postorden: " + arbol.postOrderString());
        assertEquals("1,5,7,12,14,25,33,45,66,88", arbol.inOrderString());
        assertEquals("12,1,7,5,25,14,33,88,45,66", arbol.preOrderString());
        assertEquals("5,7,1,14,66,45,88,33,25,12", arbol.postOrderString());

        assertTrue(arbol.eliminar(12));
        System.out.println("Inorden: " + arbol.inOrderString());
        System.out.println("Preorden: " + arbol.preOrderString());
        System.out.println("Postorden: " + arbol.postOrderString());
        assertEquals("1,5,7,14,25,33,45,66,88", arbol.inOrderString());
        assertEquals("7,1,5,25,14,33,88,45,66", arbol.preOrderString());
        assertEquals("5,1,14,66,45,88,33,25,7", arbol.postOrderString());

        assertFalse(arbol.eliminar(77));
        assertEquals("1,5,7,14,25,33,45,66,88", arbol.inOrderString());
        assertEquals("7,1,5,25,14,33,88,45,66", arbol.preOrderString());
        assertEquals("5,1,14,66,45,88,33,25,7", arbol.postOrderString());

        assertTrue(arbol.eliminar(33));
        System.out.println("Inorden: " + arbol.inOrderString());
        System.out.println("Preorden: " + arbol.preOrderString());
        System.out.println("Postorden: " + arbol.postOrderString());
        assertEquals("1,5,7,14,25,45,66,88", arbol.inOrderString());
        assertEquals("7,1,5,25,14,88,45,66", arbol.preOrderString());
        assertEquals("5,1,14,66,45,88,25,7", arbol.postOrderString());
    }
}
