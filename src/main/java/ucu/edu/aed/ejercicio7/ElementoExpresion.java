package ucu.edu.aed.ejercicio7;

import java.util.Map;

public class ElementoExpresion {

    private String dato;
    private ElementoExpresion hijoIzquierdo;
    private ElementoExpresion hijoDerecho;

    public ElementoExpresion(String dato){
        this.dato = dato;
    }

    public ElementoExpresion(String operador, ElementoExpresion hijoIzquierdo, ElementoExpresion hijoDerecho){
        this.dato = operador;
        this.hijoIzquierdo = hijoIzquierdo;
        this.hijoDerecho = hijoDerecho;
    }

    public boolean esHoja(){
        return hijoIzquierdo == null && hijoDerecho == null;
    }

    public String getDato(){
        return dato;
    }

    public void sustituirVariables(Map<String, Double> valores){
        if (esHoja()){
            if (valores.containsKey(dato)){
                dato = String.valueOf(valores.get(dato));
            }
            return;
        }
        hijoIzquierdo.sustituirVariables(valores);
        hijoDerecho.sustituirVariables(valores);
    }

    public double evaluar(){
        if (esHoja()){
            return Double.parseDouble(dato);
        }
        double valorIzquierdo = hijoIzquierdo.evaluar();
        double valorDerecho = hijoDerecho.evaluar();
        switch (dato){
            case "+":
                return valorIzquierdo + valorDerecho;
            case "-":
                return valorIzquierdo - valorDerecho;
            case "*":
                return valorIzquierdo * valorDerecho;
            case "/":
                return valorIzquierdo / valorDerecho;
            default:
                throw new IllegalStateException("Operador desconocido: " + dato);
        }
    }
}
