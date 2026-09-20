package ucu.edu.aed.ejercicio13;

import ucu.edu.aed.implementaciones.AVLImpl;
import ucu.edu.aed.implementaciones.ListaEnlazada;
import ucu.edu.aed.tda.TDAElemento;
import ucu.edu.aed.tda.TDALista;

public class RegistroFlota {

    private AVLImpl<Nave> arbol;

    public RegistroFlota(){
        arbol = new AVLImpl<>();
    }

    public boolean registrarNave(Nave nave){
        return arbol.insertar(nave);
    }

    public TDALista<Integer> navesExploradoras(){
        TDALista<Integer> resultado = new ListaEnlazada<>();
        agregarExploradoras(arbol.obtenerRaiz(), resultado);
        return resultado;
    }

    private void agregarExploradoras(TDAElemento<Nave> nodo, TDALista<Integer> resultado){
        if (nodo == null){
            return;
        }
        agregarExploradoras(nodo.getHijoIzquierdo(), resultado);
        if (nodo.getDato().getClase().equals("Explorador")){
            resultado.agregar(nodo.getDato().getCodigo());
        }
        agregarExploradoras(nodo.getHijoDerecho(), resultado);
    }

    public double combustiblePromedioExploradoras(){
        TDALista<Integer> codigos = navesExploradoras();
        if (codigos.esVacio()){
            return 0.0;
        }
        int suma = 0;
        for (int i = 0; i < codigos.tamaño(); i++){
            Nave nave = arbol.buscar(new Nave(codigos.obtener(i), null, 0));
            suma += nave.getCombustible();
        }
        return (double) suma / codigos.tamaño();
    }
}
