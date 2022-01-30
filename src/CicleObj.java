
import java.awt.Color;
import java.util.Random;
import javafx.scene.shape.Circle;

class CicleObj extends Circle {

    private Color color;

    public CicleObj(double x, double y, double radius) {
        super(x, y, radius);
        color = new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
    }

    public Color getColor() {
        return color;
    }

    public void update() {
    }

}
