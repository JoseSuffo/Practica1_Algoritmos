package Interfaz;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;

public class ControladorMenu {
    @FXML private Button botonJugar;
    @FXML private Button botonSalir;
    @FXML private Button botonCreditos;
    @FXML private ImageView imagen;

    @FXML
    public void botonJugar(ActionEvent actionEvent) {
        System.out.println("Botón Jugar");
    }

    @FXML
    public void botonCreditos(ActionEvent actionEvent) {
        System.out.println("Boton Creditos");
    }

    @FXML
    public void botonSalir(javafx.event.ActionEvent actionEvent) {
        System.exit(0);
    }
}