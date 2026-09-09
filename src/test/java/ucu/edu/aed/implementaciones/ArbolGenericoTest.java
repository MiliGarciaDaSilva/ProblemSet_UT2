package ucu.edu.aed.implementaciones;

import junit.framework.TestCase;
import ucu.edu.aed.tda.TDALista;

/**
 * Pruebas del árbol genérico sobre la estructura de un libro:
 *
 * <pre>
 *                            Libro
 *          ______________/__/__|__\__\______________
 *         1        2      3      4      5           6
 *      /__|__\                 /  \            /__/ \__\
 *    1.1 1.2 1.3             4.1  4.2       6.1 6.2 6.3 6.4
 *      /__|__\
 * 1.3.1 ... 1.3.4
 * </pre>
 *
 * <p>Los capítulos 2, 3 y 5 se modelan como hojas: en el diagrama original sus secciones
 * están representadas con puntos suspensivos, no omitidas por error.</p>
 */
public class ArbolGenericoTest extends TestCase {

    private ArbolGenerico<String> libro;

    protected void setUp(){
        libro = new ArbolGenerico<>();
        libro.insertarRaiz("Libro");

        libro.insertar("Libro", "1");
        libro.insertar("Libro", "2");
        libro.insertar("Libro", "3");
        libro.insertar("Libro", "4");
        libro.insertar("Libro", "5");
        libro.insertar("Libro", "6");

        libro.insertar("1", "1.1");
        libro.insertar("1", "1.2");
        libro.insertar("1", "1.3");

        libro.insertar("1.3", "1.3.1");
        libro.insertar("1.3", "1.3.2");
        libro.insertar("1.3", "1.3.3");
        libro.insertar("1.3", "1.3.4");

        libro.insertar("4", "4.1");
        libro.insertar("4", "4.2");

        libro.insertar("6", "6.1");
        libro.insertar("6", "6.2");
        libro.insertar("6", "6.3");
        libro.insertar("6", "6.4");
    }

    private String comoTexto(TDALista<String> lista){
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < lista.tamaño(); i++){
            resultado.append(lista.obtener(i)).append(",");
        }
        if (resultado.length() > 0){
            resultado.setLength(resultado.length() - 1);
        }
        return resultado.toString();
    }

    // ---------- estructura vacía ----------

    public void testArbolNuevoEsVacio(){
        ArbolGenerico<String> vacio = new ArbolGenerico<>();
        assertTrue(vacio.esVacio());
        assertNull(vacio.obtenerRaiz());
        assertEquals(0, vacio.cantidadNodos());
        assertEquals(0, vacio.cantidadHojas());
        assertEquals(0, vacio.cantidadNodosInternos());
        assertEquals(0, vacio.altura());
        assertEquals(0, vacio.grado());
    }

    public void testRecorridosEnArbolVacio(){
        ArbolGenerico<String> vacio = new ArbolGenerico<>();
        assertEquals("", vacio.preOrderString());
        assertEquals("", vacio.postOrderString());
        assertEquals("", vacio.porNivelesString());
        assertEquals(0, vacio.enNivel(0).tamaño());
    }

    public void testOperacionesSobreArbolVacio(){
        ArbolGenerico<String> vacio = new ArbolGenerico<>();
        assertNull(vacio.buscar("1"));
        assertFalse(vacio.eliminar("1"));
        assertFalse(vacio.insertar("1", "1.1"));
        assertEquals(-1, vacio.obtenerNivel("1"));
        assertEquals(0, vacio.hijosDe("1").tamaño());
        assertEquals(0, vacio.descendientesDe("1").tamaño());
        assertEquals(0, vacio.hojasDe("1").tamaño());
    }

    // ---------- un único nodo ----------

    public void testUnUnicoNodo(){
        ArbolGenerico<String> unico = new ArbolGenerico<>();
        assertTrue(unico.insertarRaiz("Libro"));
        assertFalse(unico.esVacio());
        assertEquals(1, unico.cantidadNodos());
        assertEquals(1, unico.cantidadHojas());
        assertEquals(0, unico.cantidadNodosInternos());
        assertEquals(1, unico.altura());
        assertEquals(0, unico.grado());
        assertEquals("Libro", unico.preOrderString());
        assertEquals("Libro", unico.postOrderString());
        assertEquals("Libro", unico.porNivelesString());
        // una hoja es hoja de sí misma
        assertEquals("Libro", comoTexto(unico.hojasDe("Libro")));
        assertEquals(0, unico.descendientesDe("Libro").tamaño());
    }

    public void testInsertarRaizDosVeces(){
        ArbolGenerico<String> unico = new ArbolGenerico<>();
        assertTrue(unico.insertarRaiz("Libro"));
        assertFalse(unico.insertarRaiz("Otro"));
        assertEquals("Libro", unico.obtenerRaiz().getDato());
        assertEquals(1, unico.cantidadNodos());
    }

    // ---------- árbol degenerado ----------

    public void testArbolDegenerado(){
        ArbolGenerico<String> cadena = new ArbolGenerico<>();
        cadena.insertarRaiz("A");
        cadena.insertar("A", "B");
        cadena.insertar("B", "C");
        cadena.insertar("C", "D");

        assertEquals(4, cadena.cantidadNodos());
        assertEquals(1, cadena.cantidadHojas());
        assertEquals(3, cadena.cantidadNodosInternos());
        assertEquals(4, cadena.altura());     // altura == cantidad de nodos
        assertEquals(1, cadena.grado());      // un hijo por nodo
        // en un degenerado el recorrido por niveles saca un nodo por nivel
        assertEquals("A,B,C,D", cadena.porNivelesString());
        assertEquals("A,B,C,D", cadena.preOrderString());
        assertEquals("D,C,B,A", cadena.postOrderString());
        assertEquals(3, cadena.obtenerNivel("D"));
    }

    // ---------- estructura del libro ----------

    public void testCantidadNodos(){
        assertEquals(20, libro.cantidadNodos());
    }

    public void testCantidadHojas(){
        // 1.1, 1.2, 2, 3, 4.1, 4.2, 5, 6.1..6.4 y 1.3.1..1.3.4
        assertEquals(15, libro.cantidadHojas());
    }

    public void testCantidadNodosInternos(){
        // Libro, 1, 1.3, 4 y 6
        assertEquals(5, libro.cantidadNodosInternos());
    }

    public void testHojasMasInternosDanElTotal(){
        assertEquals(libro.cantidadNodos(),
                     libro.cantidadHojas() + libro.cantidadNodosInternos());
    }

    public void testAltura(){
        // Libro / capítulo / sección / subsección
        assertEquals(4, libro.altura());
    }

    public void testGrado(){
        // el nodo más subdividido es Libro, con 6 capítulos
        assertEquals(6, libro.grado());
    }

    // ---------- recorridos ----------

    public void testPreOrderString(){
        assertEquals("Libro,1,1.1,1.2,1.3,1.3.1,1.3.2,1.3.3,1.3.4,2,3,4,4.1,4.2,5,6,6.1,6.2,6.3,6.4",
                     libro.preOrderString());
    }

    public void testPostOrderString(){
        assertEquals("1.1,1.2,1.3.1,1.3.2,1.3.3,1.3.4,1.3,1,2,3,4.1,4.2,4,5,6.1,6.2,6.3,6.4,6,Libro",
                     libro.postOrderString());
    }

    public void testPorNivelesString(){
        // primero el libro, después todos los capítulos, después todas las secciones
        assertEquals("Libro,1,2,3,4,5,6,1.1,1.2,1.3,4.1,4.2,6.1,6.2,6.3,6.4,1.3.1,1.3.2,1.3.3,1.3.4",
                     libro.porNivelesString());
    }

    public void testPorNivelesEsLaConcatenacionDeLosNiveles(){
        StringBuilder porNiveles = new StringBuilder();
        for (int nivel = 0; nivel < libro.altura(); nivel++){
            String delNivel = comoTexto(libro.enNivel(nivel));
            if (porNiveles.length() > 0){
                porNiveles.append(",");
            }
            porNiveles.append(delNivel);
        }
        assertEquals(libro.porNivelesString(), porNiveles.toString());
    }

    // ---------- enNivel ----------

    public void testEnNivelRaiz(){
        assertEquals("Libro", comoTexto(libro.enNivel(0)));
    }

    public void testEnNivelCapitulos(){
        assertEquals("1,2,3,4,5,6", comoTexto(libro.enNivel(1)));
    }

    public void testEnNivelSecciones(){
        assertEquals("1.1,1.2,1.3,4.1,4.2,6.1,6.2,6.3,6.4", comoTexto(libro.enNivel(2)));
    }

    public void testEnNivelSubsecciones(){
        assertEquals("1.3.1,1.3.2,1.3.3,1.3.4", comoTexto(libro.enNivel(3)));
    }

    public void testEnNivelFueraDeRango(){
        assertEquals(0, libro.enNivel(4).tamaño());
        assertEquals(0, libro.enNivel(-1).tamaño());
    }

    // ---------- consultas por subárbol ----------

    public void testHijosDe(){
        assertEquals("1.1,1.2,1.3", comoTexto(libro.hijosDe("1")));
        assertEquals("6.1,6.2,6.3,6.4", comoTexto(libro.hijosDe("6")));
    }

    public void testHijosDeUnaHoja(){
        assertEquals(0, libro.hijosDe("2").tamaño());
    }

    public void testHijosDeNodoInexistente(){
        assertEquals(0, libro.hijosDe("99").tamaño());
    }

    public void testDescendientesDe(){
        assertEquals("1.1,1.2,1.3,1.3.1,1.3.2,1.3.3,1.3.4", comoTexto(libro.descendientesDe("1")));
    }

    public void testHojasDe(){
        // los "códigos" que cuelgan del capítulo 1, sin enumerarlos a mano
        assertEquals("1.1,1.2,1.3.1,1.3.2,1.3.3,1.3.4", comoTexto(libro.hojasDe("1")));
        assertEquals("6.1,6.2,6.3,6.4", comoTexto(libro.hojasDe("6")));
    }

    public void testPostOrderDesde(){
        StringBuilder recorrido = new StringBuilder();
        libro.postOrderDesde("1", dato -> recorrido.append(dato).append(","));
        assertEquals("1.1,1.2,1.3.1,1.3.2,1.3.3,1.3.4,1.3,1,", recorrido.toString());
    }

    public void testPreOrderDesde(){
        StringBuilder recorrido = new StringBuilder();
        libro.preOrderDesde("4", dato -> recorrido.append(dato).append(","));
        assertEquals("4,4.1,4.2,", recorrido.toString());
    }

    public void testRecorridoDesdeNodoInexistenteNoHaceNada(){
        StringBuilder recorrido = new StringBuilder();
        libro.preOrderDesde("99", dato -> recorrido.append(dato));
        assertEquals("", recorrido.toString());
    }

    // ---------- búsquedas ----------

    public void testBuscarExistente(){
        assertEquals("1.3.4", libro.buscar("1.3.4"));
        assertEquals("Libro", libro.buscar("Libro"));
    }

    public void testBuscarInexistente(){
        assertNull(libro.buscar("7"));
    }

    public void testObtenerNivel(){
        assertEquals(0, libro.obtenerNivel("Libro"));
        assertEquals(1, libro.obtenerNivel("2"));
        assertEquals(2, libro.obtenerNivel("1.3"));
        assertEquals(3, libro.obtenerNivel("1.3.4"));
    }

    public void testObtenerNivelInexistente(){
        assertEquals(-1, libro.obtenerNivel("99"));
    }

    // ---------- inserciones ----------

    public void testInsertarRespetaElOrdenDeInsercion(){
        libro.insertar("2", "2.1");
        libro.insertar("2", "2.2");
        assertEquals("2.1,2.2", comoTexto(libro.hijosDe("2")));
    }

    public void testInsertarBajoPadreInexistente(){
        assertFalse(libro.insertar("99", "99.1"));
        assertEquals(20, libro.cantidadNodos());
    }

    public void testInsertarDatoDuplicado(){
        assertFalse(libro.insertar("2", "1.1"));
        assertEquals(20, libro.cantidadNodos());
    }

    public void testInsertarEnUnaHojaLaConvierteEnInterna(){
        assertTrue(libro.insertar("5", "5.1"));
        assertEquals(21, libro.cantidadNodos());
        assertEquals(6, libro.cantidadNodosInternos());
        assertEquals(2, libro.obtenerNivel("5.1"));
    }

    // ---------- eliminaciones (en cascada) ----------

    public void testEliminarCapituloSeLlevaSusSecciones(){
        assertTrue(libro.eliminar("1"));
        // se van 1, 1.1, 1.2, 1.3 y 1.3.1..1.3.4 = 8 nodos
        assertEquals(12, libro.cantidadNodos());
        assertNull(libro.buscar("1"));
        assertNull(libro.buscar("1.3"));
        assertNull(libro.buscar("1.3.2"));
        assertEquals("Libro,2,3,4,4.1,4.2,5,6,6.1,6.2,6.3,6.4", libro.preOrderString());
    }

    public void testEliminarSeccionIntermedia(){
        assertTrue(libro.eliminar("1.3"));
        // se van 1.3 y sus cuatro subsecciones
        assertEquals(15, libro.cantidadNodos());
        assertEquals("1.1,1.2", comoTexto(libro.hijosDe("1")));
        // el árbol pierde un nivel: ya no hay subsecciones
        assertEquals(3, libro.altura());
    }

    public void testEliminarPrimerHijo(){
        assertTrue(libro.eliminar("1.1"));
        assertEquals("1.2,1.3", comoTexto(libro.hijosDe("1")));
    }

    public void testEliminarHijoDelMedio(){
        assertTrue(libro.eliminar("1.2"));
        assertEquals("1.1,1.3", comoTexto(libro.hijosDe("1")));
    }

    public void testEliminarUltimoHijo(){
        assertTrue(libro.eliminar("6.4"));
        assertEquals("6.1,6.2,6.3", comoTexto(libro.hijosDe("6")));
    }

    public void testEliminarHoja(){
        assertTrue(libro.eliminar("2"));
        assertEquals(19, libro.cantidadNodos());
        assertEquals("1,3,4,5,6", comoTexto(libro.enNivel(1)));
    }

    public void testEliminarLaRaizVaciaElArbol(){
        assertTrue(libro.eliminar("Libro"));
        assertTrue(libro.esVacio());
        assertEquals(0, libro.cantidadNodos());
    }

    public void testEliminarInexistente(){
        assertFalse(libro.eliminar("99"));
        assertEquals(20, libro.cantidadNodos());
    }

}
