package Interfaz;

import DeckOfCards.CartaInglesa;
import Pila.Pila;
import solitaire.FoundationDeck;
import solitaire.SolitaireGame;
import solitaire.TableauDeck;

import java.util.ArrayList;

public class TableroActual {
    //Atributos de la clase TableroActual
    private ArrayList<ArrayList<CartaInglesa>> tableau;
    private ArrayList<ArrayList<CartaInglesa>> foundation;
    private ArrayList<CartaInglesa> drawPile;
    private ArrayList<CartaInglesa> wastePile;

    //Constructor de la clase TableroActual que recibe como parámetro una instancia
    //del Solitario para poder trabajar con sus elementos.
    public TableroActual(SolitaireGame juego) {
        this.tableau = clonarTableaux(juego.getTableau());
        this.foundation = clonarFoundations(juego.foundation);
        this.drawPile = clonarPila(juego.getDrawPile().getCartas());
        this.wastePile = clonarPila(juego.getWastePile().getCartas());
    }

    //Se crea una copia de los tableaus actuales del solitario y se devuelven para guardarlos.
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

    //Se crea una copia de los foundations actuales del solitario y se devuelven para guardarlos.
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

    //Se crea una copia de la pila actual del solitario y se devuelve para guardarla.
    private ArrayList<CartaInglesa> clonarPila(Pila<CartaInglesa> pilaOriginal) {
        ArrayList<CartaInglesa> copia = new ArrayList<>();
        for (CartaInglesa carta : pilaOriginal.toList()) {
            copia.add(carta.clonar());
        }
        return copia;
    }

    //Getters de los atributos de la clase
    public ArrayList<ArrayList<CartaInglesa>> getTableau() { return tableau; }
    public ArrayList<ArrayList<CartaInglesa>> getFoundation() { return foundation; }
    public ArrayList<CartaInglesa> getDrawPile() { return drawPile; }
    public ArrayList<CartaInglesa> getWastePile() { return wastePile; }
}