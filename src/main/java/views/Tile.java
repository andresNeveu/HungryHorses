package views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Tile extends StackPane {
    public Tile(Integer color) {
        ImageView imageView = new ImageView();
        switch (color) {
            case 1 -> {
                Image blackChess = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/blackChess.png")));
                imageView.setImage(blackChess);
            }
            case 2 -> {
                Image whiteChess = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/whiteChess.png")));
                imageView.setImage(whiteChess);
            }
            case 3 -> {
                Image apple = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/apple.png")));
                imageView.setImage(apple);
            }
            case 4 -> {
                Image grass = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/grass.png")));
                imageView.setImage(grass);
            }
            case 5 -> {
                Image flower = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/flower.png")));
                imageView.setImage(flower);
            }
        }
        Rectangle rectangle = new Rectangle(50,50,Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        getChildren().addAll(rectangle,imageView);
    }
}
