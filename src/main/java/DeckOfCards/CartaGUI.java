package DeckOfCards;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CartaGUI {
    //Atributo de la clase CartaGUI
    CartaInglesa cartaInglesa;

    //Constructor de la clase CartaGUI
    public CartaGUI(CartaInglesa carta) {
        this.cartaInglesa = carta;
    }

    //Metodo que crea una imagen en base a una ruta de la carta para regresarla como StackPane
    public StackPane getPane() {
        ImageView imagenCarta = new ImageView(new Image(obtenerRuta()));
        imagenCarta.setFitWidth(50);
        imagenCarta.setFitHeight(100);
        imagenCarta.setPreserveRatio(false);
        return new StackPane(imagenCarta);
    }

    /*Metodo que regresa la ruta de una carta en base a su valor y palo, si esta volteada
    regresa la ruta correspondiente a la carta volteada*/
    public String obtenerRuta() {
        if (!isFaceup()) {
            return "/ImagenesCartas/cartaVolteada.png";
        }

        String nombreArchivo = switch (getValor()) {
            case 11 -> "J" + getPalo() + ".png";
            case 12 -> "Q" + getPalo() + ".png";
            case 13 -> "K" + getPalo() + ".png";
            case 14 -> "A" + getPalo() + ".png";
            default -> Integer.toString(getValor()) + getPalo() + ".png";
        };

        return "/ImagenesCartas/" + nombreArchivo;
    }

    //Getter de los valores de la carta inglesa recibida
    public boolean isFaceup() {
        return cartaInglesa.isFaceup();
    }

    public Palo getPalo() {
        return cartaInglesa.getPalo();
    }

    public int getValor() {
        return cartaInglesa.getValor();
    }

    public void voltearCarta() {
        if (cartaInglesa != null) {
            cartaInglesa.makeFaceDown();
        }
    }
}
