package Interfaz;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import solitaire.TableauDeck;

import java.util.ArrayList;

public class TableroGUI {
    public HBox tablero = new HBox(16);
    public TableuGUI[] tableaus = new TableuGUI[7];

    public HBox getHBox() {
        return tablero;
    }

    public TableroGUI() {
        for(int i = 0; i < tableaus.length; i++){
            TableuGUI tableu = new TableuGUI();
            tableaus[i] = tableu;
            tablero.getChildren().add(tableu.obtenerTableu());
        }

        tablero.setAlignment(Pos.TOP_LEFT);
        tablero.setFillHeight(false);
        tablero.setPadding(new Insets(10,10,10,10));
    }

    public Pane getPane(int index){
        return tableaus[index].obtenerTableu();
    }

    public StackPane[] dibujar(ArrayList<TableauDeck> tableau){
        StackPane[] pane = new StackPane[7];
        for(int i = 0; i < 7 && i < tableau.size(); i++){
            var cartas = tableau.get(i);
            pane[i] = tableaus[i].getTableu(cartas.getCards());
        }
        return pane;
    }
}