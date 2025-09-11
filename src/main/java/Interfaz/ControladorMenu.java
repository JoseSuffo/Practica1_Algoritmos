package Interfaz;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ControladorMenu {
    @FXML private Button botonJugar;
    @FXML private Button botonSalir;
    @FXML private Button botonCreditos;

    @FXML
    public void botonJugar(ActionEvent actionEvent) {
        BorderPane ventana = new BorderPane();
        ControladorTablero controlador = new ControladorTablero(ventana);

        Scene juego = new Scene(ventana, 800, 600);
        Stage stage = new Stage();
        stage.setScene(juego);
        stage.setResizable(false);
        stage.setTitle("Solitario Guasavito");
        stage.show();
    }

    @FXML
    public void botonCreditos(ActionEvent actionEvent){
        Alert creditos = new Alert(Alert.AlertType.INFORMATION);
        creditos.setTitle("Creditos del programa");
        creditos.setHeaderText("Creditos del programa");
        creditos.setContentText("Hecho por José Ramón Suffo Peimbert.");
        creditos.show();
    }

    @FXML
    public void botonSalir(javafx.event.ActionEvent actionEvent) {
        System.exit(0);
    }
}