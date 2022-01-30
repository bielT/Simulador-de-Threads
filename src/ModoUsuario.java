
import java.awt.Canvas;
import java.awt.Dimension;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Bielt
 */
public class ModoUsuario extends Canvas implements Runnable {

    private static final int FWIDTH = 800, FHEIGHT = 600;
    private final int processo;
    private final ArrayList<Integer> thrProcesso;

    public ModoUsuario(int processo, ArrayList<Integer> thrProcesso) {
        this.processo =processo;
        this.thrProcesso = thrProcesso;

        Dimension dimension = new Dimension(FWIDTH, FHEIGHT);
        this.setPreferredSize(dimension);
    }

    @Override
    public void run() {
         while (true) {
            update();
            render();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        
        
    }

    private void render() {
        
    }

}
