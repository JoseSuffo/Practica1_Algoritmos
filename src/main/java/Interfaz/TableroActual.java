package Interfaz;

import DeckOfCards.CartaInglesa;
import Pila.Pila;
import solitaire.FoundationDeck;
import solitaire.SolitaireGame;
import solitaire.TableauDeck;

import java.util.ArrayList;

public class TableroActual {
    private ArrayList<ArrayList<CartaInglesa>> tableau;
    private ArrayList<ArrayList<CartaInglesa>> foundation;
    private ArrayList<CartaInglesa> drawPile;
    private ArrayList<CartaInglesa> wastePile;

    public TableroActual(SolitaireGame juego) {
        this.tableau = clonarTableaux(juego.getTableau());
        this.foundation = clonarFoundations(juego.foundation);
        this.drawPile = clonarPila(juego.getDrawPile().getCartas());
        this.wastePile = clonarPila(juego.getWastePile().getCartas());
    }

    // Métodos auxiliares para clonar defensivamente
//    private ArrayList<ArrayList<CartaInglesa>> clonarTableaux(ArrayList<TableauDeck> originales) {
//        ArrayList<ArrayList<CartaInglesa>> copia = new ArrayList<>();
//        for (TableauDeck deck : originales) {
//            copia.add(new ArrayList<>(deck.getCards()));
//        }
//        return copia;
//    }

    private ArrayList<ArrayList<CartaInglesa>> clonarTableaux(ArrayList<TableauDeck> originales) {
        ArrayList<ArrayList<CartaInglesa>> copia = new ArrayList<>();
        for (TableauDeck deck : originales) {
            ArrayList<CartaInglesa> clonadas = new ArrayList<>();
            for (CartaInglesa carta : deck.getCards()) {
                clonadas.add(carta.clonar());
            }
            copia.add(clonadas);
        }
        return copia;
    }

    private ArrayList<ArrayList<CartaInglesa>> clonarFoundations(ArrayList<FoundationDeck> originales) {
        ArrayList<ArrayList<CartaInglesa>> copia = new ArrayList<>();
        for (FoundationDeck deck : originales) {
            ArrayList<CartaInglesa> clonadas = new ArrayList<>();
            for(CartaInglesa carta : deck.getCartas()){
                clonadas.add(carta.clonar());
            }
            copia.add(clonadas);
        }
        return copia;
    }

    private ArrayList<CartaInglesa> clonarPila(Pila<CartaInglesa> pilaOriginal) {
        ArrayList<CartaInglesa> copia = new ArrayList<>();
        for (CartaInglesa carta : pilaOriginal.toList()) {
            copia.add(carta.clonar()); // clonado profundo
        }
        return copia;
    }

    // Getters
    public ArrayList<ArrayList<CartaInglesa>> getTableau() { return tableau; }
    public ArrayList<ArrayList<CartaInglesa>> getFoundation() { return foundation; }
    public ArrayList<CartaInglesa> getDrawPile() { return drawPile; }
    public ArrayList<CartaInglesa> getWastePile() { return wastePile; }
}