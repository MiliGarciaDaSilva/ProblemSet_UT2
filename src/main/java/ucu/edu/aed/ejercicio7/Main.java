package ucu.edu.aed.ejercicio7;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        ElementoExpresion a = new ElementoExpresion("a");
        ElementoExpresion tres = new ElementoExpresion("3");
        ElementoExpresion suma = new ElementoExpresion("+", a, tres);

        ElementoExpresion b = new ElementoExpresion("b");
        ElementoExpresion dos = new ElementoExpresion("2");
        ElementoExpresion resta = new ElementoExpresion("-", b, dos);

        ElementoExpresion raiz = new ElementoExpresion("*", suma, resta);

        Map<String, Double> valores = new HashMap<>();
        valores.put("a", 5.0);
        valores.put("b", 10.0);

        raiz.sustituirVariables(valores);
        System.out.println("Resultado: " + raiz.evaluar());
    }
}
