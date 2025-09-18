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
//        cartas.addAll(nuevas);
        for(int i=0; i<cartas.getTamaño(); i++){
            CartaInglesa carta = nuevas.pop();
            cartas.push(carta);
        }
    }

    public Pila<CartaInglesa> emptyPile() {
        Pila<CartaInglesa> pile = new Pila<CartaInglesa>(1000);
        if (!cartas.pilaVacia()) {
            for(int i=0; i<cartas.getTamaño(); i++) {
                CartaInglesa carta = cartas.pop();
                pile.push(carta);
            }
//            pile.addAll(cartas);
            cartas = new Pila<CartaInglesa>(1000);
        }
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

    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();
        if (cartas.pilaVacia()) {
            stb.append("---");
        } else {
            CartaInglesa regresar = cartas.pop();
            regresar.makeFaceUp();
            stb.append(regresar.toString());
        }
        return stb.toString();
    }

    public boolean hayCartas() {
        return !cartas.pilaVacia();
    }
}
