package Interfaz;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import solitaire.DrawPile;

import java.io.IOException;

public class ControladorMenu {
    @FXML private Button botonJugar;
    @FXML private Button botonSalir;
    @FXML private Button botonCreditos;
    @FXML private ImageView imagen;

    @FXML
    public void botonJugar(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/tablero.fxml"));
            Parent root = fxmlLoader.load();
            ControladorTablero controladorTablero = fxmlLoader.getController();
            DrawPile drawPile = new DrawPile();
            controladorTablero.inicializarJuego(drawPile);

            Stage stage = new Stage();
            stage.setTitle("Solitario");
            stage.setScene(new Scene(root));
            stage.show();

            Stage actual = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            actual.close();
        }catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo iniciar el juego");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void botonCreditos(ActionEvent actionEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/creditos.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Creditos");
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void botonSalir(javafx.event.ActionEvent actionEvent) {
        System.exit(0);
    }
}