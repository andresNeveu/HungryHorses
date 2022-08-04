package views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Knight;

import java.util.Objects;

public class Tile extends StackPane {
    private Integer[] place;
    private ImageView imageView = new ImageView();
    private Integer item;

    public Tile(Integer item, Integer[] place) {
        this.place = place;
        this.item = item;
        switch (item) {
            case 1 -> {
                Knight blackChess = new Knight("black",place);
                Rectangle rectangle = new Rectangle(50, 50, Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                getChildren().addAll(rectangle, blackChess);
            }
            case 2 -> {
                Knight whiteChess = new Knight("white",place);
                Rectangle rectangle = new Rectangle(50, 50, Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                getChildren().addAll(rectangle, whiteChess);
            }
            case 3 -> {
                Image apple = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/apple.png")));
                setImage(apple);
            }
            case 4 -> {
                Image grass = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/grass.png")));
                setImage(grass);
            }
            case 5 -> {
                Image flower = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/flower.png")));
                setImage(flower);
            }
        }
        Rectangle rectangle = new Rectangle(50, 50, Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);
        getChildren().addAll(rectangle, imageView);
    }

    public void setPlace(Integer[] place) {
        this.place = place;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setImage(Image image) {
        imageView.setImage(image);
    }

    public Integer[] getPlace() {
        return place;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
