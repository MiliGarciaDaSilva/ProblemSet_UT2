package ucu.edu.aed.implementaciones;

import junit.framework.TestCase;
import ucu.edu.aed.tda.TDAHeap;

/**
 * Pruebas del min-heap usado como cola de prioridad.
 *
 * <p>La idea que atraviesa casi todos los casos es que el heap <b>no</b> mantiene sus
 * datos ordenados: solo garantiza que el mínimo está en la raíz. El orden de prioridad
 * no está materializado en la estructura, se produce de a un elemento en cada
 * {@code eliminar()}. Por eso muchos tests verifican la <i>secuencia de salida</i> y no
 * el estado interno.</p>
 */
public class HeapTest extends TestCase {

    private Heap<Integer> heap;

    protected void setUp(){
        heap = new Heap<>();
    }

    /**
     * Vacía el heap extrayendo de a uno y devuelve la secuencia de salida.
     */
    private <E extends Comparable<E>> String extraerTodo(TDAHeap<E> unHeap){
        StringBuilder resultado = new StringBuilder();
        while (!unHeap.esVacio()){
            if (resultado.length() > 0){
                resultado.append(",");
            }
            resultado.append(unHeap.eliminar());
        }
        return resultado.toString();
    }

    // ---------- estructura vacía ----------

    public void testHeapNuevoEsVacio(){
        assertTrue(heap.esVacio());
        assertEquals(0, heap.cantidad());
        assertNull(heap.minimo());
        assertEquals("", heap.toString());
    }

    public void testOperacionesSobreHeapVacio(){
        assertNull(heap.eliminar());
        assertNull(heap.eliminar());
        assertTrue(heap.esVacio());
        assertEquals(0, heap.cantidad());
    }

    // ---------- un único elemento ----------

    public void testUnUnicoElemento(){
        assertTrue(heap.insertar(7));
        assertFalse(heap.esVacio());
        assertEquals(1, heap.cantidad());
        assertEquals(Integer.valueOf(7), heap.minimo());
    }

    public void testEliminarElUnicoElementoDejaElHeapVacio(){
        heap.insertar(7);
        assertEquals(Integer.valueOf(7), heap.eliminar());
        assertTrue(heap.esVacio());
        assertEquals(0, heap.cantidad());
        assertNull(heap.minimo());
        assertNull(heap.eliminar());
    }

    // ---------- el mínimo emerge sin ordenar ----------

    /**
     * El caso de la guardia: llega un paciente leve, después uno medio y último el
     * crítico. Aunque el más urgente llegó al final, sale primero.
     */
    public void testElOrdenDeLlegadaNoDeterminaElDeSalida(){
        heap.insertar(5);   // leve
        heap.insertar(3);   // medio
        heap.insertar(1);   // crítico
        assertEquals(Integer.valueOf(1), heap.minimo());
        assertEquals("1,3,5", extraerTodo(heap));
    }

    /**
     * Con esas mismas tres inserciones el array queda [1, 5, 3]: el 5 antes que el 3.
     * Está desordenado y es un heap perfectamente válido, porque el invariante solo
     * habla de la relación padre-hijo, no de hermanos.
     */
    public void testElHeapNoQuedaOrdenadoInternamente(){
        heap.insertar(5);
        heap.insertar(3);
        heap.insertar(1);
        assertEquals("1,5,3", heap.toString());
        assertEquals(Integer.valueOf(1), heap.minimo());
    }

    public void testDistintosOrdenesDeInsercionDanLaMismaSalida(){
        Heap<Integer> ascendente = new Heap<>();
        Heap<Integer> descendente = new Heap<>();
        Heap<Integer> desordenado = new Heap<>();

        for (int i = 1; i <= 7; i++){
            ascendente.insertar(i);
        }
        for (int i = 7; i >= 1; i--){
            descendente.insertar(i);
        }
        int[] mezclado = {4, 1, 7, 3, 6, 2, 5};
        for (int valor : mezclado){
            desordenado.insertar(valor);
        }

        assertEquals("1,2,3,4,5,6,7", extraerTodo(ascendente));
        assertEquals("1,2,3,4,5,6,7", extraerTodo(descendente));
        assertEquals("1,2,3,4,5,6,7", extraerTodo(desordenado));
    }

    // ---------- inserción ----------

    public void testInsertarNullNoHaceNada(){
        assertFalse(heap.insertar(null));
        assertTrue(heap.esVacio());
        assertEquals(0, heap.cantidad());
    }

    public void testInsertarUnNuevoMinimoLoLlevaALaRaiz(){
        heap.insertar(10);
        heap.insertar(20);
        heap.insertar(30);
        assertEquals(Integer.valueOf(10), heap.minimo());
        heap.insertar(1);
        assertEquals(Integer.valueOf(1), heap.minimo());
        assertEquals(4, heap.cantidad());
    }

    public void testInsertarUnMaximoNoAlteraLaRaiz(){
        heap.insertar(10);
        heap.insertar(20);
        heap.insertar(99);
        assertEquals(Integer.valueOf(10), heap.minimo());
    }

    // ---------- eliminación y hundido ----------

    /**
     * Caso que caza el error clásico de hundir: cuando los <b>dos</b> hijos son menores
     * que el elemento que baja, hay que intercambiarlo con el más chico de los dos.
     *
     * <p>Después de insertar 1, 2, 6, 9 el array es [1, 2, 6, 9]. Al eliminar el 1, el
     * 9 sube a la raíz y queda [9, 2, 6]: sus dos hijos son menores que él. Si se
     * eligiera el 6 en vez del 2, quedaría [6, 2, 9], con el 6 de padre del 2.</p>
     */
    public void testAlHundirSeEligeElMenorDeLosDosHijos(){
        heap.insertar(1);
        heap.insertar(2);
        heap.insertar(6);
        heap.insertar(9);

        assertEquals(Integer.valueOf(1), heap.eliminar());
        assertEquals(Integer.valueOf(2), heap.minimo());
        assertEquals("2,9,6", heap.toString());
        assertEquals("2,6,9", extraerTodo(heap));
    }

    /**
     * El elemento que sube a la raíz puede tener que bajar varios niveles, no uno solo.
     */
    public void testHundirRecorreVariosNiveles(){
        for (int i = 1; i <= 7; i++){
            heap.insertar(i);
        }
        assertEquals("1,2,3,4,5,6,7", heap.toString());

        // sale el 1, sube el 7 a la raíz y baja dos niveles hasta la posición 3
        assertEquals(Integer.valueOf(1), heap.eliminar());
        assertEquals("2,4,3,7,5,6", heap.toString());
        assertEquals("2,3,4,5,6,7", extraerTodo(heap));
    }

    public void testEliminarDejaElSiguienteMinimoEnLaRaiz(){
        int[] valores = {8, 3, 5, 1, 9, 2};
        for (int valor : valores){
            heap.insertar(valor);
        }
        assertEquals(Integer.valueOf(1), heap.eliminar());
        assertEquals(Integer.valueOf(2), heap.minimo());
        assertEquals(Integer.valueOf(2), heap.eliminar());
        assertEquals(Integer.valueOf(3), heap.minimo());
        assertEquals(4, heap.cantidad());
    }

    // ---------- duplicados ----------

    public void testElementosRepetidos(){
        heap.insertar(3);
        heap.insertar(1);
        heap.insertar(3);
        heap.insertar(1);
        heap.insertar(2);
        assertEquals(5, heap.cantidad());
        assertEquals("1,1,2,3,3", extraerTodo(heap));
    }

    // ---------- uso intercalado, como una guardia real ----------

    public void testInsertarYEliminarIntercalados(){
        heap.insertar(5);
        heap.insertar(3);
        assertEquals(Integer.valueOf(3), heap.eliminar());

        heap.insertar(8);
        heap.insertar(1);
        assertEquals(Integer.valueOf(1), heap.eliminar());
        assertEquals(Integer.valueOf(5), heap.eliminar());

        heap.insertar(2);
        assertEquals(Integer.valueOf(2), heap.eliminar());
        assertEquals(Integer.valueOf(8), heap.eliminar());
        assertTrue(heap.esVacio());
    }

    // ---------- crecimiento del array ----------

    public void testCreceMasAlladeLaCapacidadInicial(){
        // 100 inserciones sobre una capacidad inicial de 16, cada una en el peor caso:
        // al entrar en orden descendente, todo elemento nuevo flota hasta la raíz
        for (int i = 100; i >= 1; i--){
            heap.insertar(i);
        }
        assertEquals(100, heap.cantidad());
        assertEquals(Integer.valueOf(1), heap.minimo());

        StringBuilder esperado = new StringBuilder();
        for (int i = 1; i <= 100; i++){
            if (i > 1){
                esperado.append(",");
            }
            esperado.append(i);
        }
        assertEquals(esperado.toString(), extraerTodo(heap));
    }

    public void testCapacidadInicialMinima(){
        Heap<Integer> chico = new Heap<>(1);
        for (int i = 5; i >= 1; i--){
            chico.insertar(i);
        }
        assertEquals(5, chico.cantidad());
        assertEquals("1,2,3,4,5", extraerTodo(chico));
    }

    public void testCapacidadInicialInvalidaSeAjusta(){
        Heap<Integer> raro = new Heap<>(0);
        assertTrue(raro.insertar(4));
        assertTrue(raro.insertar(2));
        assertEquals("2,4", extraerTodo(raro));
    }

    /**
     * Con entrada arbitraria, la salida siempre es no decreciente. Es la propiedad que
     * define a la cola de prioridad, sin depender de ningún caso particular.
     */
    public void testLaSalidaSiempreEsNoDecreciente(){
        for (int i = 0; i < 50; i++){
            heap.insertar((i * 37) % 101);
        }
        assertEquals(50, heap.cantidad());

        int anterior = Integer.MIN_VALUE;
        int extraidos = 0;
        while (!heap.esVacio()){
            int actual = heap.eliminar();
            assertTrue("salió " + actual + " después de " + anterior, actual >= anterior);
            anterior = actual;
            extraidos++;
        }
        assertEquals(50, extraidos);
    }

    // ---------- prioridad sobre objetos ----------

    /**
     * Paciente de triage: urgencia más baja significa más urgente, y a igual urgencia
     * desempata el orden de llegada.
     */
    private static class Paciente implements Comparable<Paciente> {

        private final String nombre;
        private final int urgencia;
        private final int llegada;

        Paciente(String nombre, int urgencia, int llegada){
            this.nombre = nombre;
            this.urgencia = urgencia;
            this.llegada = llegada;
        }

        public int compareTo(Paciente otro){
            if (urgencia != otro.urgencia){
                return urgencia - otro.urgencia;
            }
            return llegada - otro.llegada;
        }

        public String toString(){
            return nombre;
        }
    }

    public void testColaDePrioridadDePacientes(){
        Heap<Paciente> guardia = new Heap<>();
        guardia.insertar(new Paciente("Ana", 5, 1));      // leve, llega primero
        guardia.insertar(new Paciente("Bruno", 3, 2));    // medio
        guardia.insertar(new Paciente("Carla", 1, 3));    // crítica, llega última

        assertEquals("Carla", guardia.minimo().toString());
        assertEquals("Carla,Bruno,Ana", extraerTodo(guardia));
    }

    /**
     * A igual urgencia, el desempate por hora de llegada da el FIFO que espera una
     * guardia. Sin ese desempate el heap no garantizaría ningún orden entre iguales.
     */
    public void testAIgualUrgenciaSaleElQueLlegoPrimero(){
        Heap<Paciente> guardia = new Heap<>();
        guardia.insertar(new Paciente("Tercero", 3, 3));
        guardia.insertar(new Paciente("Primero", 3, 1));
        guardia.insertar(new Paciente("Segundo", 3, 2));

        assertEquals("Primero,Segundo,Tercero", extraerTodo(guardia));
    }

    public void testUnCriticoSeAdelantaAUnaColaDeLeves(){
        Heap<Paciente> guardia = new Heap<>();
        for (int i = 1; i <= 20; i++){
            guardia.insertar(new Paciente("Leve" + i, 5, i));
        }
        guardia.insertar(new Paciente("Grave", 1, 21));

        assertEquals("Grave", guardia.eliminar().toString());
        assertEquals("Leve1", guardia.eliminar().toString());
        assertEquals(19, guardia.cantidad());
    }
}
