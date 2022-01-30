
import java.awt.Color;
import java.util.Random;
import javafx.scene.shape.Line;


class LinesObj extends Line {
    private Color color;
    public LinesObj(double d, double d1, double d2, double d3) {
        super(d, d1, d2, d3);
        color = new Color (new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255));

    }
    
        
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public void update(){        
        color = new Color (new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255));
        
    }

    @Override
    public String toString() {
        return "RectObj{" + "color=" + color + "x=" +super.getStartX()+"y=" +super.getStartY()+"x2=" +super.getEndX()+"y2=" +super.getEndY()+'}';
    }
}
