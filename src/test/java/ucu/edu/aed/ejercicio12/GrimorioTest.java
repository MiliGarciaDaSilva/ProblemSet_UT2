package ucu.edu.aed.ejercicio12;

import junit.framework.TestCase;

import ucu.edu.aed.tda.TDALista;

public class GrimorioTest extends TestCase {

    private Grimorio grimorio;

    protected void setUp(){
        grimorio = new Grimorio();
        grimorio.agregarHechizo(new Hechizo(42, "Fireball"));
        grimorio.agregarHechizo(new Hechizo(17, "Ice Lance"));
        grimorio.agregarHechizo(new Hechizo(58, "Thunder"));
        grimorio.agregarHechizo(new Hechizo(9, "Invisibility"));
        grimorio.agregarHechizo(new Hechizo(31, "Levitate"));
        grimorio.agregarHechizo(new Hechizo(73, "Summon"));
        grimorio.agregarHechizo(new Hechizo(25, "Heal"));
        grimorio.agregarHechizo(new Hechizo(50, "Teleport"));
        grimorio.agregarHechizo(new Hechizo(65, "Shield"));
        grimorio.agregarHechizo(new Hechizo(88, "Curse"));
    }

    public void testHechizosProhibidos(){
        TDALista<Hechizo> prohibidos = grimorio.hechizosProhibidos();
        assertEquals(6, prohibidos.tamaño());
        assertEquals(9, prohibidos.obtener(0).getId());
        assertEquals(17, prohibidos.obtener(1).getId());
        assertEquals(25, prohibidos.obtener(2).getId());
        assertEquals(31, prohibidos.obtener(3).getId());
        assertEquals(65, prohibidos.obtener(4).getId());
        assertEquals(73, prohibidos.obtener(5).getId());
    }

    public void testHechizosProhibidosEnGrimorioVacio(){
        Grimorio vacio = new Grimorio();
        assertEquals(0, vacio.hechizosProhibidos().tamaño());
    }

    public void testCantico(){
        assertEquals("Invisibility - Ice Lance - Heal - Levitate - Shield - Summon", grimorio.cantico());
    }

    public void testCanticoEnGrimorioVacio(){
        Grimorio vacio = new Grimorio();
        assertEquals("", vacio.cantico());
    }

    public void testCanticoSinHechizosProhibidos(){
        Grimorio soloPares = new Grimorio();
        soloPares.agregarHechizo(new Hechizo(2, "Spark"));
        soloPares.agregarHechizo(new Hechizo(4, "Frost"));
        assertEquals("", soloPares.cantico());
    }
}
