package sample.nodes;


import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class SortNode extends Rectangle {
    public int value;

    public SortNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public TranslateTransition moveX(float x) {
        TranslateTransition t = new TranslateTransition();
        t.setNode(this);
        t.setDuration(Duration.millis(100));
        t.setByX(x);

        return t;
    }
}
