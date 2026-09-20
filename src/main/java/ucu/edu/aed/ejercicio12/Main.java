package ucu.edu.aed.ejercicio12;

import ucu.edu.aed.tda.TDALista;

public class Main {

    public static void main(String[] args) {
        Grimorio grimorio = new Grimorio();
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

        TDALista<Hechizo> prohibidos = grimorio.hechizosProhibidos();
        System.out.println("Hechizos prohibidos:");
        for (int i = 0; i < prohibidos.tamaño(); i++) {
            System.out.println(prohibidos.obtener(i));
        }

        System.out.println("Cantico: " + grimorio.cantico());
    }
}
