package DeckOfCards;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CartaGUI {
    static CartaInglesa cartaInglesa;
    public CartaGUI(CartaInglesa carta) {
        this.cartaInglesa = carta;
    }

    public static StackPane getPane() {
        ImageView imagenCarta = new ImageView(new Image(obtenerRuta()));
        imagenCarta.setFitWidth(100);
        imagenCarta.setPreserveRatio(true);
        StackPane pane = new StackPane(imagenCarta);
        return pane;
    }

    public static String obtenerRuta() {
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

    public static boolean isFaceup() {
        return cartaInglesa.isFaceup();
    }

    public static Palo getPalo() {
        return cartaInglesa.getPalo();
    }

    public static int getValor() {
        return cartaInglesa.getValor();
    }
}
