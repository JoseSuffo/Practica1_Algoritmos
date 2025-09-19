package DeckOfCards;
/**
 * Write a description of class Mazo here.
 *
 * @author (Cecilia Curlango Rosas)
 * @version (2025-2)
 */
import Pila.Pila;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
    private Pila<CartaInglesa> cartas = new Pila<CartaInglesa>(1000);

    public Mazo() {
        llenar(); // crea todas las cartas, excluyendo Jokers
        mezclar();
    }

    /**
     * Obtiene todas las cartas del mazo.
     * @return
     */

    public Pila<CartaInglesa> getCartas() {
        return cartas;
    }

    private void mezclar() {
        ArrayList<CartaInglesa> cartasTemporales = new ArrayList<>();
        while (!cartas.pilaVacia()) {
            cartasTemporales.add(cartas.pop());
        }
        Collections.shuffle(cartasTemporales);
        for (int i = cartasTemporales.size() - 1; i >= 0; i--) {
            cartas.push(cartasTemporales.get(i));
        }
    }

    private void llenar() {
        for (int i = 2; i <=14 ; i++) {
            for (Palo palo : Palo.values()) {
                CartaInglesa c = new CartaInglesa(i,palo, palo.getColor());
                cartas.push(c);
            }
        }
    }

    @Override
    public String toString() {
        return cartas.toString();
    }
}
