
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

public class RectObj extends Rectangle {
    private Color color;

    public RectObj(int x, int y,int width,int height){
        super(x,y,width, height);

        color = new Color (new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255));
   }
    
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    
    public void update() {

    }

    @Override
    public String toString() {
        return "RectObj{" + "color=" + color + "x=" +super.x+"y=" +super.y+"width=" +super.width+"height=" +super.height+'}';
    }
    
}
