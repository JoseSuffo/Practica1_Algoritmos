package Interfaz;

import DeckOfCards.CartaGUI;
import DeckOfCards.CartaInglesa;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class TableuGUI {
    public Pane tableu = new Pane();
    public double anchoCarta, altoCarta;
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

    public Pane obtenerTableu(){
        return tableu;
    }
}