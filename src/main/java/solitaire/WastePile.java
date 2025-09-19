package solitaire;

import DeckOfCards.CartaInglesa;
import Pila.Pila;

import java.util.ArrayList;
/**
 * Modela el montículo donde se colocan las cartas
 * que se extraen de Draw pile.
 *
 * @author (Cecilia Curlango Rosas)
 * @version (2025-2)
 */
public class WastePile {
    private Pila<CartaInglesa> cartas;

    public WastePile() {
        cartas = new Pila<CartaInglesa>(1000);
    }

    public void addCartas(Pila<CartaInglesa> nuevas) {
        Pila<CartaInglesa> temp = new Pila<CartaInglesa>(nuevas.getTamaño());
        while (!nuevas.pilaVacia()) {
            temp.push(nuevas.pop());
        }
        while (!temp.pilaVacia()) {
            cartas.push(temp.pop());
        }
    }

    public Pila<CartaInglesa> emptyPile() {
        Pila<CartaInglesa> pile = new Pila<CartaInglesa>(1000);
        while (!cartas.pilaVacia()) {
            pile.push(cartas.pop());
        }
        cartas = new Pila<CartaInglesa>(1000);
        return pile;
    }

    /**
     * Obtener la última carta sin removerla.
     * @return Carta que está encima. Si está vacía, es null.
     */
    public CartaInglesa verCarta() {
        CartaInglesa regresar = null;
        if (!cartas.pilaVacia()) {
            regresar = cartas.peek();
        }
        return regresar;
    }
    public CartaInglesa getCarta() {
        CartaInglesa regresar = null;
        if (!cartas.pilaVacia()) {
            regresar = cartas.pop();
        }
        return regresar;
    }

    public boolean hayCartas() {
        return !cartas.pilaVacia();
    }

    public int getTamaño(){
        return cartas.getTamaño();
    }

    public Pila<CartaInglesa> getCartas() {
        return cartas;
    }

    public void setCartas(ArrayList<CartaInglesa> cartasAnteriores){
        cartas = new Pila<CartaInglesa>(1000);
        for(CartaInglesa carta : cartasAnteriores){
            cartas.push(carta);
        }
    }
}
