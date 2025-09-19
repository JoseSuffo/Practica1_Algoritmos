package solitaire;

import DeckOfCards.CartaInglesa;
import Pila.Pila;

import java.util.ArrayList;

/**
 * Modela un mazo de cartas de solitario.
 * @author Cecilia Curlango
 * @version 2025
 */
public class DrawPile {
    private Pila<CartaInglesa> cartas;
    private int cuantasCartasSeEntregan = 3;

    public DrawPile(Pila<CartaInglesa> cartasPila) {
        cartas = cartasPila;
        setCuantasCartasSeEntregan(3);
    }

    /**
     * Establece cuantas cartas se sacan cada vez.
     * Puede ser 1 o 3 normalmente.
     *
     * @param cuantasCartasSeEntregan
     */
    public void setCuantasCartasSeEntregan(int cuantasCartasSeEntregan) {
        this.cuantasCartasSeEntregan = cuantasCartasSeEntregan;
    }

    /**
     * Retirar una cantidad de cartas. Este método se utiliza al inicio
     * de una partida para cargar las cartas de los tableaus.
     * Si se tratan de remover más cartas de las que hay,
     * se provocará un error.
     *
     * @param cantidad de cartas que se quieren a retirar
     * @return cartas retiradas
     */
    public ArrayList<CartaInglesa> getCartas(int cantidad) {
        ArrayList<CartaInglesa> retiradas = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
//            retiradas.add(cartas.remove(0));
            retiradas.add(cartas.pop());
        }
        return retiradas;
    }

    /**
     * Retira y entrega las cartas del monton. La cantidad que retira
     * depende de cuántas cartas quedan en el montón y serán hasta el máximo
     * que se configuró inicialmente.
     * @return Cartas retiradas.
     */
    public Pila<CartaInglesa> retirarCartas(){
        Pila<CartaInglesa> retiradas = new Pila<CartaInglesa>(1000);
        int maximoARetirar = cartas.getTamaño() < cuantasCartasSeEntregan ? cartas.getTamaño() : cuantasCartasSeEntregan;
        for (int i = 0; i < maximoARetirar; i++) {
            CartaInglesa retirada = cartas.pop();
            if (retirada != null) {
                retirada.makeFaceUp();
                retiradas.push(retirada);
            }
        }
        return retiradas;
    }

    /**
     * Indica si aún quedan cartas para entregar.
     * @return true si hay cartas, false si no.
     */
    public boolean hayCartas() {
        return cartas.getTamaño() > 0;
    }

    public CartaInglesa verCarta() {
        CartaInglesa regresar = null;
        if (!cartas.pilaVacia()) {
            regresar = cartas.peek();
        }
        return regresar;
    }
    /**
     * Agrega las cartas recibidas al monton y las voltea
     * para que no se vean las caras.
     * @param cartasAgregar cartas que se agregan
     */
    public void recargar(Pila<CartaInglesa> cartasAgregar) {
        cartas = new Pila<CartaInglesa>(1000);

        Pila<CartaInglesa> temporal = new Pila<CartaInglesa>(cartasAgregar.getTamaño());
        while (!cartasAgregar.pilaVacia()) {
            CartaInglesa carta = cartasAgregar.pop();
            if (carta != null) {
                carta.makeFaceDown();
                temporal.push(carta);
            }
        }

        while (!temporal.pilaVacia()) {
            cartas.push(temporal.pop());
        }
    }

    public int getTamaño(){
        return cartas.getTamaño();
    }

    public Pila<CartaInglesa> getCartas() {
        return cartas;
    }

    public void setCartas(ArrayList<CartaInglesa> cartasAnteriores){
        cartas = new Pila<CartaInglesa>(1000);
        for (CartaInglesa carta : cartasAnteriores) {
            cartas.push(carta);
        }
    }
}
