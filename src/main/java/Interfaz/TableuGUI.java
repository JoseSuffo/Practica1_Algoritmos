package Interfaz;

import DeckOfCards.CartaGUI;
import DeckOfCards.CartaInglesa;
import javafx.scene.Cursor;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class TableuGUI {
    //Atributos de la clase TableuGUI
    public Pane tableu = new Pane();
    public double anchoCarta, altoCarta;

    //Constructor de la clase TableuGUI
    public TableuGUI(){
        anchoCarta = 50;
        altoCarta = 100;

        tableu.setPrefSize(anchoCarta, altoCarta+6*30);
        tableu.setMinWidth(anchoCarta);
        tableu.setMaxWidth(anchoCarta);
        tableu.setMinSize(anchoCarta, altoCarta);
        tableu.setPickOnBounds(true);
        tableu.setCursor(Cursor.HAND);
    }

    //Metodo que se encarga de regresar la interfaz gráfica de las cartas en un StackPane
    //Recibe como argumento un arraylist de CartaInglesa para poder generar así su imagen
    public StackPane getTableu(ArrayList<CartaInglesa> cartas){
        tableu.getChildren().clear();
        int y=0;
        StackPane stackCartas = new StackPane();

        for(int i=0;i<cartas.size();i++){
            CartaInglesa carta = cartas.get(i);
            CartaGUI cartaGUI = new CartaGUI(carta);
            boolean cartaPosicion = cartaGUI.isFaceup();
            StackPane cartaPane = cartaGUI.getPane();
            cartaPane.setTranslateY(y);
            stackCartas.getChildren().add(cartaPane);
            if(cartaPosicion){
                y+=25;
            }else{
                y+=30;
            }

            if (i == cartas.size() - 1 && !cartaPosicion){
                stackCartas = cartaPane;
            }
        }
        tableu.getChildren().add(stackCartas);
        return stackCartas;
    }

    //Este metodo nos regresa un solo tableu en su versión de Pane
    public Pane obtenerTableu(){
        return tableu;
    }
}