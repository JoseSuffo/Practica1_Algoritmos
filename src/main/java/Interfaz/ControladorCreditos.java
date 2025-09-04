package Interfaz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ControladorCreditos {
    @FXML private Text creditos;
    @FXML private Button botonRegresar;

    public static void cerrarVentana(ActionEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void botonRegresar(ActionEvent actionEvent) {
        cerrarVentana(actionEvent);
    }
}