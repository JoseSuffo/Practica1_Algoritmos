package Interfaz;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

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