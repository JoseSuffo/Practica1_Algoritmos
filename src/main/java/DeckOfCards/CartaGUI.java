package DeckOfCards;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CartaGUI {
    private CartaInglesa cartaInglesa;
    private ImageView imagenCarta;
    private StackPane contenedor;

    public CartaGUI(CartaInglesa carta) {
        this.cartaInglesa = carta;
        imagenCarta = new ImageView();
        imagenCarta.setFitWidth(50);
        imagenCarta.setFitHeight(100);
        imagenCarta.setPreserveRatio(false);
        contenedor = new StackPane(imagenCarta);
        actualizarImagen();
    }

    public StackPane getPane() {
        return contenedor;
    }

    public void actualizarImagen() {
        imagenCarta.setImage(new Image(obtenerRuta()));
    }

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
            actualizarImagen();
        }
    }
}