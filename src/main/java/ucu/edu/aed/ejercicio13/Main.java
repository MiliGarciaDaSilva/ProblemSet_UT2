package ucu.edu.aed.ejercicio13;

import ucu.edu.aed.tda.TDALista;

public class Main {

    public static void main(String[] args) {
        RegistroFlota registro = new RegistroFlota();
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

        TDALista<Integer> codigos = registro.navesExploradoras();
        System.out.println("Naves exploradoras:");
        for (int i = 0; i < codigos.tamaño(); i++) {
            System.out.println(codigos.obtener(i));
        }

        System.out.println("Combustible promedio: " + registro.combustiblePromedioExploradoras());
    }
}
