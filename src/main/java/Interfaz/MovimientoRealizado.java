package Interfaz;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MovimientoRealizado {
    public VBox izquierda, derecha;
    public HBox inferior, superior;
    public StackPane drawPile, wastePile;
    public StackPane[] foundations;
    public TableroGUI tableroGUI;

    public MovimientoRealizado(VBox izquierda, VBox derecha, HBox inferior, HBox superior
            , StackPane drawPile, StackPane wastePile, StackPane[] foundations, TableroGUI tableroGUI) {
        this.izquierda = izquierda;
        this.derecha = derecha;
        this.inferior = inferior;
        this.superior = superior;
        this.drawPile = drawPile;
        this.wastePile = wastePile;
        this.foundations = foundations;
        this.tableroGUI = tableroGUI;
    }

    public VBox getIzquierda() {
        return izquierda;
    }

    public VBox getDerecha() {
        return derecha;
    }

    public HBox getInferior() {
        return inferior;
    }

    public HBox getSuperior() {
        return superior;
    }

    public StackPane getWastePile() {
        return wastePile;
    }

    public StackPane getDrawPile() {
        return drawPile;
    }

    public StackPane[] getFoundations() {
        return foundations;
    }

    public TableroGUI getTableroGUI() {
        return tableroGUI;
    }
}