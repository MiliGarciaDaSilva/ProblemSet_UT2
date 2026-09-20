package ucu.edu.aed.ejercicio13;

public class Nave implements Comparable<Nave> {

    private int codigo;
    private String clase;
    private int combustible;

    public Nave(int codigo, String clase, int combustible){
        this.codigo = codigo;
        this.clase = clase;
        this.combustible = combustible;
    }

    public int getCodigo(){
        return codigo;
    }

    public String getClase(){
        return clase;
    }

    public int getCombustible(){
        return combustible;
    }

    @Override
    public int compareTo(Nave otra){
        return Integer.compare(this.codigo, otra.codigo);
    }

    @Override
    public String toString(){
        return codigo + " - " + clase + " - " + combustible;
    }
}
