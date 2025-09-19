package Pila;

import DeckOfCards.CartaInglesa;

import java.util.ArrayList;

public class Pila <T>{
    int tope, valorMaximo;
    T[] pila;

    public Pila(int valorMaximo){
        tope = -1;
        this.valorMaximo = valorMaximo;
        pila = (T[]) new Object[valorMaximo];
    }

    public boolean pilaLlena(){
        return tope == valorMaximo-1;
    }

    public boolean pilaVacia(){
        return tope == -1;
    }

    public void push(T dato){
        if(pilaLlena()){
            System.out.println("Desbordamiento");
        }else{
            tope++;
            pila[tope] = dato;
        }
    }

    public T pop(){
        if(pilaVacia()){
            System.out.println("Subdesbordamiento");
        }else{
            T dato = pila[tope];
            tope--;
            return dato;
        }
        return null;
    }

    public int getTamaño(){
        return tope + 1;
    }

    public T peek(){
        if(pilaVacia()){
            System.out.println("Subdesbordamiento");
            return null;
        }else{
            return pila[tope];
        }
    }

    public void clear(){
        while(!pilaVacia()){
            pop();
        }
    }

    public ArrayList<T> toList() {
        ArrayList<T> lista = new ArrayList<>();
        for (int i = 0; i <= tope; i++) {
            lista.add(pila[i]);
        }
        return lista;
    }
}