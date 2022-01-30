
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Spanner {

    private static final int fWIDTH = 800, fHEIGHT = 600;
    private final List<CicleObj> Cicle = new ArrayList<>();
    private final List<RectObj> rectangles = new ArrayList<>();
    private final List<LinesObj> linesU = new ArrayList<>();
    private final List<LinesObj> linesU2 = new ArrayList<>();

    private final List<LinesObj> linesK = new ArrayList<>();

    private final List<String> strin = new ArrayList<>();
    private final List<String> strinAux = new ArrayList<>();
    private final ArrayList<Integer> thrProcesso;
    private ArrayList<Integer> thrProc;
    private final int processo;
    private final int thread;

    public Spanner(int processo, ArrayList<Integer> thrProcesso, int thread, int modo) {
        this.processo = processo;
        this.thrProcesso = thrProcesso;
        this.thread = thread;

        //tela basica
        basic();
        if (modo == 1) {//modo Kernel
            Kernel();
        } else if (modo == 2) {//modo usario
            Usuario();
        }

    }

    public void basic() {
        for (int i = 1; i <= thread + 1; i++) {

            int a = (i - 1) * 160 + 60;
            RectObj an = new RectObj(a, 415, 40, 100);
            rectangles.add(an);

        }

        int co = 0;

        for (int n = processo - 1; n >= 0; n--) {
            for (int i = 0; i < thrProcesso.get(n); i++) {
                int a = (co * 160) + 20 + (i * 25);
                RectObj an = new RectObj(a, 170, 10, 50);
                rectangles.add(an);
                //System.out.println(an);
            }
            co++;
        }
    }

    public void Usuario() {

        for (int i = 0; i < processo; i++) {
            int a = i * 160;
            CicleObj c = new CicleObj(a, 125, 75);
            Cicle.add(c);
            int b = (i * 160) + 45;
            RectObj an = new RectObj(b, 250, 60, 10);
            an.setColor(c.getColor());
            rectangles.add(an);

        }
        //linhas
        int co = 0;
        for (int n = processo - 1; n >= 0; n--) {
            int Cli = (co * 160) + 80;
            for (int i = 0; i < thrProcesso.get(n); i++) {
                double x1 = (co * 160) + 25 + (i * 25);
                double x2 = (co * 160) + 50 + (i * 10);
                LinesObj an = new LinesObj(x1, 220.00, x2, 250);
                linesU.add(an);
            }
            LinesObj an = new LinesObj(80, 415.00, Cli, 275);
            linesU2.add(an);
            co++;
        }

    }

    public void renderU(Graphics g) {
        int temp = 0;
        Graphics2D g2 = (Graphics2D) g;
        int co = 0, co1 = 0;
        int n = 0, aux = 0;

        for (int i = processo - 1; i >= 0; i--) {
            LinesObj current;
            current = linesU2.get(co);
            aux += thrProcesso.get(i);
            while (n < aux) {
                co1++;
                g2.setColor(new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)));
                g2.drawLine((int) current.getStartX(), (int) current.getStartY(), (int) current.getEndX(), (int) current.getEndY());
                while (temp < 1000) {
                    LinesObj current1 = linesU.get(n);
                    g2.drawLine((int) current1.getStartX(), (int) current1.getStartY(), (int) current1.getEndX(), (int) current1.getEndY());

                    g2.setColor(new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)));

                    temp++;

                }

                System.out.println("Processo :" + (co + 1) + " Thrend:" + (co1));
                if (strin.size() < 26) {
                    strin.add("{P" + (co + 1) + " T:" + (co1) + "}");
                }

                n++;
                temp = 0;
            }
            co1 = 0;
            try {
                Thread.sleep(1000 / 40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            co++;
        }

    }

    public void updateU() {

    }

    public void render(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, fWIDTH, fHEIGHT);
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < Cicle.size(); i++) {
            CicleObj current = Cicle.get(i);

            g2.setColor(current.getColor());
            g2.drawOval((int) current.getCenterX(), (int) current.getCenterY(), (int) current.getRadius() * 2, (int) current.getRadius() * 2);
        }

        for (int i = 0; i < rectangles.size(); i++) {
            RectObj current = rectangles.get(i);
            g2.setColor(current.getColor());
            g2.fillRect(current.x, current.y, current.width, current.height);
            g2.setColor(Color.WHITE);
            g2.drawRect(current.x, current.y, current.width, current.height);
        }
        for (int i = 0; i < strin.size(); i++) {
            String current = strin.get(i);
            g2.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
            g.drawString(current, i * 55, 560);

        }

        g.drawLine(0, 400, fWIDTH, fHEIGHT - 200);

    }

    public void update() {
        if (strin.size() > 0) {
            strin.add(strin.remove(0));
        }

    }

    public void Kernel() {
        thrProc = new ArrayList();
        for (int i = 0; i < processo; i++) {
            int a = i * 160;
            CicleObj c = new CicleObj(a, 125, 75);
            Cicle.add(c);
        }

        for (int n = processo - 1; n >= 0; n--) {
            if (thrProcesso.size() > 0) {
                thrProc.add(thrProcesso.get(n));
            }

        }
        int a = -1, b = -1;

        for (int n = 0; n < thrProc.size(); n++) {
            for (int i = 0; i < thrProc.get(n); i++) {
                a = circ(a);
                double x1 = (n * 160) + 25 + (i * 25);
                double x2 = (a * 160) + 80;
                LinesObj an = new LinesObj(x1, 220.00, x2, 415);
                linesK.add(an);
                strin.add("{P" + (n + 1) + " T:" + (i + 1) + "}");
            }
        }
        Collections.shuffle(strin);
     
        if (thrProc.size() > 0) {
            a = circ(a);
            double x1 = (thrProc.size() - 1 * 160) + 25 + (thrProc.get(thrProc.size() - 1) * 25);
            double x2 = (a * 160) + 80;
            LinesObj an = new LinesObj(x1, 220.00, x2, 415);
            linesK.add(an);
        }

    }

    private int circ(int a) {
        return thread == a ? 0 : ++a;
    }

   
    public void updateK() {
        for (int i = 0; i < linesK.size() - 1; i++) {
            System.out.println(strin.get(i));
        }
    }

    public void renderK(Graphics g) {

        int temp = 0;
        Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i < linesK.size() - 1; i++) {

            LinesObj current = linesK.get(i);
            while (temp < 1000) {

                g2.setColor(new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)));
                g2.drawLine((int) current.getStartX(), (int) current.getStartY(), (int) current.getEndX(), (int) current.getEndY());
                temp++;

            }
            temp = 0;
            try {
                Thread.sleep(1000 / 20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
