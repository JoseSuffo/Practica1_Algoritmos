package Interfaz;

import DeckOfCards.Carta;
import DeckOfCards.CartaGUI;
import DeckOfCards.CartaInglesa;
import DeckOfCards.Palo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import solitaire.SolitaireGame;

import java.util.Optional;

public class ControladorTablero {
    SolitaireGame juegoSolitario = new SolitaireGame();

    boolean seSeleccionoCarta;
    String seleccionOrigen;
    int tableuSeleccionado;
    StackPane cartaSeleccionada;

    BorderPane ventana;
    VBox seccionIzquierda = new VBox(10);
    VBox seccionDerecha = new VBox(10);
    HBox seccionInferior = new HBox(16);
    HBox seccionSuperior = new HBox(16);
    StackPane drawPile = new StackPane();
    StackPane wastePile = new StackPane();
    StackPane[] foundations = new StackPane[4];

    Button salir = new Button("Salir");
    Button reiniciarJuego = new Button("Reiniciar Juego");
    Button reiniciarMazo = new Button("Reiniciar Mazo");
    Button deshacerAccion = new Button("Deshacer Accion (stand by)");

    TableroGUI tableroGUI;

    public ControladorTablero(BorderPane ventana) {
        seSeleccionoCarta = false;
        seleccionOrigen = "";
        tableuSeleccionado = -1;
        cartaSeleccionada = new StackPane();
        this.ventana = ventana;
        tableroGUI = new TableroGUI();
        crearGUI();
        actualizarGUI();
    }

    public void actualizarGUI(){
        generarDrawPile();
        generarWastePile();
        actualizarReciclar();
        generarTablero();
        generarFoundations();
    }

    public void generarTablero(){
        StackPane[] tableus = tableroGUI.dibujar(juegoSolitario.getTableau());

        for(int i=0; i<tableus.length; i++){
            final int index = i+1;
            StackPane carta = tableus[i];
            if(carta==null){
                continue;
            }
            carta.setCursor(Cursor.HAND);

            carta.setOnMouseClicked(event -> {
               if(event.getButton() != MouseButton.PRIMARY){
                   return;
               }
               seleccionarColumna(index);
               event.consume();
            });
        }
    }

    public void generarFoundations(){
        for(int i=0; i<foundations.length; i++){
            StackPane carta = foundations[i];
            carta.getChildren().removeIf(n -> n.getUserData() != null);
            var foundation = juegoSolitario.obtenerFoundation(i);
            if(foundation == null){
                continue;
            }
            CartaInglesa cartaInglesa = foundation.getUltimaCarta();
            if(cartaInglesa != null){
                CartaGUI cardGUI = new  CartaGUI(cartaInglesa);
                StackPane card = cardGUI.getPane();
                card.setUserData("carta");
                carta.getChildren().add(card);
            }
        }
    }

    public void generarDrawPile(){
        drawPile.getChildren().removeIf(n -> n.getUserData() != null);
        if(juegoSolitario.getDrawPile().hayCartas()){
            CartaInglesa cartaInglesa = juegoSolitario.getDrawPile().verCarta();
            CartaGUI cardGUI = new CartaGUI(cartaInglesa);
            cardGUI.voltearCarta();
            StackPane carta = cardGUI.getPane();
            carta.setUserData("carta");
            drawPile.getChildren().add(carta);
        }
    }

    public void generarWastePile(){
        wastePile.getChildren().removeIf(n -> n.getUserData() != null);
        if(juegoSolitario.getWastePile().hayCartas()){
            CartaInglesa cartaSup = juegoSolitario.getWastePile().verCarta();
            CartaGUI cardGUI = new  CartaGUI(cartaSup);
            StackPane card = cardGUI.getPane();
            card.setUserData("carta");

            card.setOnMouseClicked(event -> {
               if(event.getButton() != MouseButton.PRIMARY){
                   return;
               }
               if(seSeleccionoCarta){
                   return;
               }
               tableuSeleccionado = -1;
               seleccionOrigen = "WASTE PILE";
               seSeleccionoCarta = true;
            });
            wastePile.getChildren().add(card);
        }
    }

    public void generarEspacioTableu(StackPane tableu, String nombre){
        tableu.setPrefSize(50,100);
        Rectangle rectangle = new Rectangle(50,100);
        rectangle.setArcHeight(5);
        rectangle.setArcWidth(5);
        rectangle.setFill(Color.color(1, 0, 0, 0.12));
        rectangle.setStroke(Color.color(1, 0, 0, 0.35));
        rectangle.setStrokeWidth(1.5);
        tableu.getChildren().add(rectangle);

        Label nombreSeccion = new Label(nombre);
        nombreSeccion.setTextFill(Color.WHITE);
        StackPane.setAlignment(nombreSeccion, Pos.TOP_LEFT);
        StackPane.setMargin(nombreSeccion, new Insets(4, 0, 0, 6));
        tableu.getChildren().add(nombreSeccion);

        tableu.setCursor(Cursor.HAND);
    }

    public void crearGUI(){
        ventana.setStyle("-fx-background-color: linear-gradient(from 0% 50% to 100% 50%, rgba(12,89,2,1) 0%, rgba(31,148,80,1) 50%, rgba(12,89,2,1) 100%);");

        seccionIzquierda.setPadding(new Insets(16));
        seccionIzquierda.setAlignment(Pos.TOP_LEFT);
        seccionIzquierda.setFillWidth(false);

        seccionDerecha.setPadding(new Insets(16));
        seccionDerecha.setAlignment(Pos.BOTTOM_CENTER);
        seccionDerecha.setFillWidth(false);

        seccionInferior.setPadding(new Insets(16));
        seccionInferior.setAlignment(Pos.CENTER);
        seccionInferior.setFillHeight(false);

        seccionSuperior.setPadding(new Insets(16));
        seccionSuperior.setAlignment(Pos.CENTER);
        seccionSuperior.setFillHeight(false);

        generarEspacioTableu(drawPile, "Draw Pile");
        generarEspacioTableu(wastePile, "Waste Pile");

        drawPile.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY){
                if(juegoSolitario.getDrawPile().hayCartas()){
                    juegoSolitario.drawCards();
                    actualizarSeleccion();
                    actualizarGUI();
                }
            }
        });

        deshacerAccion.setOnAction(event -> {

        });
        deshacerAccion.setStyle("-fx-background-color: linear-gradient(to bottom, #90CAF9, #1976D2);\n");
        deshacerAccion.setTextFill(Color.WHITE);

        salir.setOnAction(event -> {
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Salir");
            confirmacion.setHeaderText("Confirmación de salida");
            confirmacion.setContentText("¿Estás seguro de salir?");
            Optional<ButtonType> resultado = confirmacion.showAndWait();
            if(resultado.get() == ButtonType.OK){
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }else if(resultado.get() == ButtonType.CANCEL){
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("El juego sigue");
                mensaje.setHeaderText("¡Sigue con la partida!");
                mensaje.setContentText("La partida no ha sido reiniciada");
                mensaje.showAndWait();
            }
        });
        salir.setStyle("-fx-background-color: linear-gradient(to bottom, #90CAF9, #1976D2);\n");
        salir.setTextFill(Color.WHITE);

        reiniciarJuego.setOnAction(event -> {
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Reiniciar juego");
            confirmacion.setHeaderText("Confirmación de reinicio");
            confirmacion.setContentText("¿Estás seguro de reiniciar tu juego?");
            Optional<ButtonType> resultado = confirmacion.showAndWait();
            if(resultado.get() == ButtonType.OK){
                juegoSolitario = new SolitaireGame();
                actualizarSeleccion();
                actualizarGUI();
            }else if(resultado.get() == ButtonType.CANCEL){
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("El juego sigue");
                mensaje.setHeaderText("¡Sigue con la partida!");
                mensaje.setContentText("La partida no ha sido reiniciada");
                mensaje.showAndWait();
            }
        });
        reiniciarJuego.setStyle("-fx-background-color: linear-gradient(to bottom, #90CAF9, #1976D2);\n");
        reiniciarJuego.setTextFill(Color.WHITE);

        reiniciarMazo.setOnAction(event -> {
            juegoSolitario.reloadDrawPile();
            actualizarSeleccion();
            actualizarGUI();
        });
        reiniciarMazo.setStyle("-fx-background-color: linear-gradient(to bottom, #90CAF9, #1976D2);\n");
        reiniciarMazo.setTextFill(Color.WHITE);

        for(int i=0; i<foundations.length; i++){
            foundations[i] = new StackPane();
            generarEspacioTableu(foundations[i], Palo.values()[i].getFigura());

            foundations[i].setOnMouseClicked(event -> {
                if(event.getButton() != MouseButton.PRIMARY) {
                    return;
                }
                if(!seSeleccionoCarta){
                    return;
                }
                boolean seMovioCarta = false;
                switch(seleccionOrigen){
                    case "WASTE PILE":
                        seMovioCarta = juegoSolitario.moveWasteToFoundation();
                        break;
                    case "TABLEU":
                        seMovioCarta = juegoSolitario.moveTableauToFoundation(tableuSeleccionado);
                        break;
                }
                actualizarSeleccion();
                if(seMovioCarta){
                    actualizarGUI();
                }
            });
        }

        seccionIzquierda.getChildren().addAll(wastePile, drawPile, reiniciarMazo);
        seccionDerecha.getChildren().addAll(foundations[0], foundations[1]
        , foundations[2], foundations[3]);
        seccionSuperior.getChildren().addAll();
        seccionInferior.getChildren().addAll(deshacerAccion, salir, reiniciarJuego);

        ventana.setTop(seccionSuperior);
        ventana.setBottom(seccionInferior);
        ventana.setLeft(seccionIzquierda);
        ventana.setRight(seccionDerecha);
        ventana.setCenter(tableroGUI.getHBox());

        for(int i=0; i<7; i++){
            final int index = i+1;
            var tableu = tableroGUI.getPane(i);
            tableu.setOnMouseClicked(event -> {
                if(event.getButton() != MouseButton.PRIMARY){
                    return;
                }
                seleccionarColumna(index);
            });
            tableu.setCursor(Cursor.HAND);
        }
    }

    public void seleccionarColumna(int index){
        if(!seSeleccionoCarta){
            tableuSeleccionado = index;
            seSeleccionoCarta = true;
            seleccionOrigen = "TABLEU";
        }else{
            boolean seMovioCarta = false;
            if("WASTE PILE".equals(seleccionOrigen)){
                seMovioCarta = juegoSolitario.moveWasteToTableau(index);
            }else if("TABLEU".equals(seleccionOrigen)){
                seMovioCarta = juegoSolitario.moveTableauToTableau(tableuSeleccionado, index);
            }else{
                actualizarSeleccion();
            }
            actualizarSeleccion();
            if(seMovioCarta){
                actualizarGUI();
            }
        }
    }

    public void actualizarSeleccion(){
        seSeleccionoCarta = false;
        seleccionOrigen = "";
        tableuSeleccionado = -1;
    }

    public void actualizarReciclar(){
        boolean cartasEnDrawPile = !juegoSolitario.getDrawPile().hayCartas();
        boolean cartasEnWastePile = juegoSolitario.getWastePile().hayCartas();
        reiniciarMazo.setDisable(!(cartasEnDrawPile && cartasEnWastePile));
    }
}