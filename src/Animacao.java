
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferStrategy;
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
public class Animacao extends Canvas implements Runnable {

    private static final int fWIDTH = 800, fHEIGHT = 600;
    private final int processo;
    private final ArrayList<Integer> thrProcesso;
    private final int thread;
    private Spanner spawner;
    private final int modo;

    public Animacao(int processo, ArrayList<Integer> thrProcesso, int thread, int modo) {
        this.processo = processo;
        this.thrProcesso = thrProcesso;
        this.thread = thread;
        this.modo = modo;
        Dimension dimension = new Dimension(fWIDTH, fHEIGHT);
        this.setPreferredSize(dimension);
        spawner = new Spanner(processo, thrProcesso, thread,modo);
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
        spawner.update();
        if (modo == 1) {
            spawner.updateK();
        } else if (modo == 2) {
        }
    }

    private void render() {

        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
         spawner.render(g);
        if (modo == 1) {
            spawner.renderK(g);
        } else if (modo == 2) {
            spawner.renderU(g) ;
        }
       
        bs.show();

    }

}
