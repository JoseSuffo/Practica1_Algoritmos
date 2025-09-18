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
    //private ArrayList<CartaInglesa> cartas = new ArrayList<>();
    private Pila<CartaInglesa> cartas = new Pila<CartaInglesa>(1000);

    public Mazo() {
        llenar(); // crea todas las cartas, excluyendo Jokers
        mezclar();
    }

    /**
     * Obtiene todas las cartas del mazo.
     * @return
     */
//    public ArrayList<CartaInglesa> getCartas() {
//        return cartas;
//    }

    public Pila<CartaInglesa> getCartas() {
        return cartas;
    }

//    public CartaInglesa obtenerUnaCarta() {
//        if (cartas.size() > 0) {
//            return cartas.remove(0);
//        }
//        return null;
//    }

    public CartaInglesa obtenerUnaCarta(){
        if (cartas.getTamaño() > 0) {
            return cartas.pop();
        }
        return null;
    }
//    private void mezclar() {
//        Collections.shuffle(cartas);
//    }

    private void mezclar() {
        // Extraer todas las cartas de la pila interna
        ArrayList<CartaInglesa> cartasTemporales = new ArrayList<>();

        while (!cartas.pilaVacia()) {
            cartasTemporales.add(cartas.pop());
        }

        // Mezclar las cartas
        Collections.shuffle(cartasTemporales);

        // Insertar nuevamente en la pila (en orden inverso para mantener el tope correcto)
        for (int i = cartasTemporales.size() - 1; i >= 0; i--) {
            cartas.push(cartasTemporales.get(i));
        }
    }

//    private void llenar() {
//        for (int i = 2; i <=14 ; i++) {
//            for (Palo palo : Palo.values()) {
//                CartaInglesa c = new CartaInglesa(i,palo, palo.getColor());
//                cartas.add(c);
//            }
//        }
//    }

    private void llenar() {
        for (int i = 2; i <=14 ; i++) {
            for (Palo palo : Palo.values()) {
                CartaInglesa c = new CartaInglesa(i,palo, palo.getColor());
                cartas.push(c);
            }
        }
    }

//    public void ordenar() {
//        Collections.sort(cartas);
//    }

    public void ordenar() {
        // Extraer todas las cartas de la pila
        ArrayList<CartaInglesa> cartasTemporales = new ArrayList<>();

        while (!cartas.pilaVacia()) {
            cartasTemporales.add(cartas.pop());
        }

        // Ordenar las cartas (requiere que CartaInglesa implemente Comparable)
        Collections.sort(cartasTemporales);

        // Insertar nuevamente en la pila (en orden inverso para mantener el tope correcto)
        for (int i = cartasTemporales.size() - 1; i >= 0; i--) {
            cartas.push(cartasTemporales.get(i));
        }
    }

    @Override
    public String toString() {
        return cartas.toString();
    }
}
