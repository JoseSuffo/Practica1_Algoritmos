package Interfaz;

import DeckOfCards.CartaInglesa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import solitaire.DrawPile;
import solitaire.SolitaireGame;
import solitaire.TableauDeck;

import java.io.InputStream;
import java.util.ArrayList;

public class ControladorTablero {
    @FXML private ImageView wastePile;
    @FXML private ImageView drawPile;
    @FXML private Pane foundation1;
    @FXML private Pane foundation2;
    @FXML private Pane foundation3;
    @FXML private Pane foundation4;
    @FXML private StackPane tableu1;
    @FXML private StackPane tableu2;
    @FXML private StackPane tableu3;
    @FXML private StackPane tableu4;
    @FXML private StackPane tableu5;
    @FXML private StackPane tableu6;
    @FXML private StackPane tableu7;
    @FXML private Button botonMover1;
    @FXML private Button botonMover2;
    @FXML private Button botonMover3;
    @FXML private Button botonMover4;
    @FXML private Button botonMover5;
    @FXML private Button botonMover6;
    @FXML private Button botonMover7;
    @FXML private Button botonMover8;
    @FXML private Button botonMover9;
    @FXML private Button botonMover10;
    @FXML private Button botonMover11;
    @FXML private Button botonTomar;
    @FXML private Button botonRegresar;

    private final ArrayList<TableauDeck> tableauDecks = new ArrayList<>();
    private final ArrayList<StackPane> vistasTableau = new ArrayList<>();
    private SolitaireGame juego;
    private Integer origenSeleccionado;

    public void renderizarMonton(StackPane destino, ArrayList<CartaInglesa> cartas) {
        destino.getChildren().clear();

        for (int i = 0; i < cartas.size(); i++) {
            CartaInglesa carta = cartas.get(i);
            Image imagenCarta;

            imagenCarta = new Image(getClass().getResourceAsStream(carta.obtenerRuta()));

            ImageView vistaCarta = new ImageView(imagenCarta);
            vistaCarta.setFitWidth(80);
            vistaCarta.setPreserveRatio(true);

            vistaCarta.setTranslateY(i * 25);

            destino.getChildren().add(vistaCarta);
        }
    }

    public void inicializarJuego(DrawPile drawPile) {
        juego = new SolitaireGame();
        inicializarBotones();

        // Asociar los StackPane del FXML
        vistasTableau.clear();
        vistasTableau.add(tableu1);
        vistasTableau.add(tableu2);
        vistasTableau.add(tableu3);
        vistasTableau.add(tableu4);
        vistasTableau.add(tableu5);
        vistasTableau.add(tableu6);
        vistasTableau.add(tableu7);

        tableauDecks.clear();

        for (int i = 0; i < 7; i++) {
            TableauDeck tableauDeck = new TableauDeck();
            tableauDeck.inicializar(drawPile.getCartas(i + 1)); // Asignar i+1 cartas
            tableauDecks.add(tableauDeck);

            // Renderizar en la vista
            renderizarMonton(vistasTableau.get(i), tableauDeck.getCards());
        }
    }

    @FXML
    public void actualizarWastePile() {
        if (juego == null || juego.getWastePile() == null) return;

        CartaInglesa carta = juego.getWastePile().verCarta(); // no la remueve
        if (carta != null) {
            InputStream stream = getClass().getResourceAsStream(carta.obtenerRuta());
            if (stream == null) {
                stream = getClass().getResourceAsStream("/ImagenesCartas/cartaVolteada.png");
            }
            wastePile.setImage(new Image(stream));
        } else {
            InputStream stream = getClass().getResourceAsStream("/ImagenesCartas/cartaVolteada.png");
            if (stream != null) {
                wastePile.setImage(new Image(stream));
            } else {
                wastePile.setImage(null);
            }
        }
    }

    @FXML
    public void actualizarDrawPile() {
        if (juego.getDrawPile().hayCartas()) {
            InputStream stream = getClass().getResourceAsStream("/ImagenesCartas/cartaVolteada.png");
            drawPile.setImage(new Image(stream));
        } else {
            drawPile.setImage(null);
        }
    }

    @FXML
    public void inicializarBotones(){
        botonMover5.setUserData(0);
        botonMover6.setUserData(1);
        botonMover7.setUserData(2);
        botonMover8.setUserData(3);
        botonMover9.setUserData(4);
        botonMover10.setUserData(5);
        botonMover11.setUserData(6);
        botonMover1.setUserData(7);
        botonMover2.setUserData(8);
        botonMover3.setUserData(9);
        botonMover4.setUserData(10);
    }

    @FXML
    public void botonTomar(){
        juego.drawCards();
        actualizarWastePile();
        actualizarDrawPile();
    }

    @FXML
    public void moverDesdeTableau(ActionEvent event) {
        Button boton = (Button) event.getSource();
        int index = (int) boton.getUserData();

        System.out.println("Index: " + index);

        if (origenSeleccionado == null) {
            origenSeleccionado = index;
            boton.setStyle("-fx-background-color: #808080");
        } else {
            if (juego.moveTableauToTableau(origenSeleccionado, index)) {
                renderizarMonton(vistasTableau.get(origenSeleccionado), tableauDecks.get(origenSeleccionado).getCards());
                renderizarMonton(vistasTableau.get(index), tableauDecks.get(index).getCards());
            }else {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Movimiento no válido");
                alerta.setHeaderText("ERROR DE MOVIMIENTO");
                alerta.setContentText("No puedes mover esa carta al lugar elegido. Intenta nuevamente.");
                alerta.showAndWait();
            }
            origenSeleccionado = null;
            resetearCoLoresBotones();
        }
    }

    public void resetearCoLoresBotones() {
        botonMover1.setStyle("");
        botonMover2.setStyle("");
        botonMover3.setStyle("");
        botonMover4.setStyle("");
        botonMover5.setStyle("");
        botonMover6.setStyle("");
        botonMover7.setStyle("");
        botonMover8.setStyle("");
        botonMover9.setStyle("");
        botonMover10.setStyle("");
        botonMover11.setStyle("");
    }
}