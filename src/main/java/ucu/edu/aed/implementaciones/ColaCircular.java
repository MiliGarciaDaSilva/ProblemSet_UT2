package ucu.edu.aed.implementaciones;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import ucu.edu.aed.tda.TDACola;
import ucu.edu.aed.tda.TDALista;

public class ColaCircular<T> implements TDACola<T> {

  private Object[] vector;
  private int front;
  private int rear;
  private int cantidad;
  private int capacidad;

  public ColaCircular() {
    this(10);
  }

  public ColaCircular(int capacidad) {
    this.capacidad = capacidad;
    this.vector = new Object[capacidad];

    this.front = 0;
    this.rear = 0;
    this.cantidad = 0;
  }

  private int posicionFisica(int indiceLogico) {
    return (front + indiceLogico) % capacidad;
  }

  @Override
  public boolean poneEnCola(T dato) {
    if (cantidad == capacidad) {
      return false;
    }
    vector[rear] = dato;
    rear = (rear + 1) % capacidad;
    cantidad++;
    return true;
  }

  @Override
  public T frente() {
    if (esVacio()) {
      throw new NoSuchElementException("La cola está vacía");
    }
    return (T) vector[front];
  }

  @Override
  public T quitaDeCola() {
    if (esVacio()) {
      throw new NoSuchElementException("La cola está vacía");
    }
    T dato = (T) vector[front];
    vector[front] = null;
    front = (front + 1) % capacidad;
    cantidad--;
    return dato;
  }

  @Override
  public boolean esVacio() {
    return cantidad == 0;
  }

  @Override
  public int tamaño() {
    return cantidad;
  }

  @Override
  public void vaciar() {
    Arrays.fill(vector, null);
    front = 0;
    rear = 0;
    cantidad = 0;
  }

  @Override
  public void agregar(T elem) {
    if (!poneEnCola(elem)) {
      throw new IllegalStateException("La cola circular está llena");
    }
  }

  @Override
  public void agregar(int index, T elem) {
    if (index < 0 || index > cantidad) {
      throw new IndexOutOfBoundsException();
    }
    if (cantidad == capacidad) {
      throw new IllegalStateException("La cola circular está llena");
    }
    for (int i = cantidad; i > index; i--) {
      vector[posicionFisica(i)] = vector[posicionFisica(i - 1)];
    }
    vector[posicionFisica(index)] = elem;
    rear = (rear + 1) % capacidad;
    cantidad++;
  }

  @Override
  public T obtener(int index) {
    if (index < 0 || index >= cantidad) {
      throw new IndexOutOfBoundsException();
    }
    return (T) vector[posicionFisica(index)];
  }

  @Override
  public T remover(int index) {
    if (index < 0 || index >= cantidad) {
      throw new IndexOutOfBoundsException();
    }
    T dato = (T) vector[posicionFisica(index)];
    for (int i = index; i < cantidad - 1; i++) {
      vector[posicionFisica(i)] = vector[posicionFisica(i + 1)];
    }
    vector[posicionFisica(cantidad - 1)] = null;
    rear = (rear - 1 + capacidad) % capacidad;
    cantidad--;
    return dato;
  }

  @Override
  public boolean remover(T elem) {
    int index = indiceDe(elem);
    if (index == -1) {
      return false;
    }
    remover(index);
    return true;
  }

  @Override
  public boolean contiene(T elem) {
    return indiceDe(elem) != -1;
  }

  @Override
  public int indiceDe(T elem) {
    for (int i = 0; i < cantidad; i++) {
      Object dato = vector[posicionFisica(i)];
      if (dato != null && dato.equals(elem)) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public T buscar(Predicate<T> criterio) {
    for (int i = 0; i < cantidad; i++) {
      T dato = (T) vector[posicionFisica(i)];
      if (criterio.test(dato)) {
        return dato;
      }
    }
    return null;
  }

  @Override
  public TDALista<T> ordenar(Comparator<T> comparator) {
    ColaCircular<T> resultado = new ColaCircular<>(capacidad);
    for (int i = 0; i < cantidad; i++) {
      T dato = obtener(i);
      int posicion = 0;
      while (posicion < resultado.cantidad && comparator.compare(dato, resultado.obtener(posicion)) >= 0) {
        posicion++;
      }
      resultado.agregar(posicion, dato);
    }
    return resultado;
  }

}
