package ucu.edu.aed.ejercicio13;

import junit.framework.TestCase;

import ucu.edu.aed.tda.TDALista;

public class RegistroFlotaTest extends TestCase {

    private RegistroFlota registro;

    protected void setUp(){
        registro = new RegistroFlota();
        registro.registrarNave(new Nave(10, "Explorador", 0));
        registro.registrarNave(new Nave(20, "Destructor", 90));
        registro.registrarNave(new Nave(30, "Médica", 100));
        registro.registrarNave(new Nave(40, "Explorador", 50));
        registro.registrarNave(new Nave(50, "Carguero", 20));
        registro.registrarNave(new Nave(60, "Destructor", 28));
        registro.registrarNave(new Nave(70, "Explorador", 14));
        registro.registrarNave(new Nave(80, "Médica", 7));
        registro.registrarNave(new Nave(90, "Carguero", 23));
        registro.registrarNave(new Nave(100, "Explorador", 26));
    }

    public void testNavesExploradoras(){
        TDALista<Integer> codigos = registro.navesExploradoras();
        assertEquals(4, codigos.tamaño());
        assertEquals(Integer.valueOf(10), codigos.obtener(0));
        assertEquals(Integer.valueOf(40), codigos.obtener(1));
        assertEquals(Integer.valueOf(70), codigos.obtener(2));
        assertEquals(Integer.valueOf(100), codigos.obtener(3));
    }

    public void testNavesExploradorasEnRegistroVacio(){
        RegistroFlota vacio = new RegistroFlota();
        assertEquals(0, vacio.navesExploradoras().tamaño());
    }

    public void testCombustiblePromedioExploradoras(){
        assertEquals(22.5, registro.combustiblePromedioExploradoras(), 0.0001);
    }

    public void testCombustiblePromedioSinExploradoras(){
        RegistroFlota soloDestructores = new RegistroFlota();
        soloDestructores.registrarNave(new Nave(1, "Destructor", 50));
        soloDestructores.registrarNave(new Nave(2, "Destructor", 30));
        assertEquals(0.0, soloDestructores.combustiblePromedioExploradoras(), 0.0001);
    }

    public void testCombustiblePromedioEnRegistroVacio(){
        RegistroFlota vacio = new RegistroFlota();
        assertEquals(0.0, vacio.combustiblePromedioExploradoras(), 0.0001);
    }
}
