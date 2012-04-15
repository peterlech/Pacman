import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.awt.Canvas;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Pacman extends Canvas implements stage, KeyListener{
public long usedTime;
public BufferStrategy strategia;
private SpiritCache spriteCache;
private ArrayList actors;
private ArrayList walls;

private Pac p;
// randomowy komentarz
public Pacman() {
    spriteCache = new SpiritCache();
    JFrame okno = new JFrame(".: Pacman :.");
    JPanel panel = (JPanel)okno.getContentPane();
    setBounds(0,0,stage.SZEROKOSC,stage.WYSOKOSC);
    panel.setPreferredSize(new Dimension(stage.SZEROKOSC,stage.WYSOKOSC));
    panel.setLayout(null);
    panel.add(this);
    okno.setBounds(0,0,stage.SZEROKOSC,stage.WYSOKOSC);
    setBackground(Color.BLACK);
    okno.setVisible(true);
    okno.addWindowListener( new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
    }
    });
    
    okno.setResizable(true);
    createBufferStrategy(2);
    strategia = getBufferStrategy();
    requestFocus();
    addKeyListener(this);
 }
    public void initWorld() {
    actors = new ArrayList();
    walls = new ArrayList();
    for (int i = 0; i < 10; i++){
    Monster m = new Monster(this);
    m.setX( (int)(Math.random()*stage.SZEROKOSC) );
    m.setY( i*20 );
    m.setVx( (int)(Math.random()*3)+1 );
    actors.add(m);
    }
    for (int j = 0; j < 10; j++){
    Wall w = new Wall(this); 
    walls.add(w);
    w.setX(2);
    w.setY(j*35);
    walls.add(w); 
    }
    p = new Pac(this);
     p.setX( SZEROKOSC/2 );
     p.setY( WYSOKOSC/2);
    // p.setVx(5);
    }
    
    public void paintWorld() {
    Graphics2D g = (Graphics2D)strategia.getDrawGraphics();
    g.setColor(Color.black);
    g.fillRect(0,0,getWidth(),getHeight());
        for (int i = 0; i < actors.size(); i++) {
        Actor m = (Actor)actors.get(i);
        m.paint(g);
        }
        for (int j = 0; j < walls.size(); j++) {
        Wall w = (Wall)walls.get(j);
        w.paint(g);
        }
    p.paint(g);
    g.setColor(Color.white);
    
    if (usedTime > 0)
    g.drawString(String.valueOf(1000/usedTime)+" fps",0,stage.WYSOKOSC-50);
    else
    g.drawString("--- fps",0,stage.WYSOKOSC-50);
    strategia.show();
    }
    
    public void updateWorld() {
    for (int i = 0; i < actors.size(); i++) {
    Actor m = (Actor)actors.get(i);
    m.act();
    }
    p.act();
    }
    
    public SpiritCache getSpriteCache() {
    return spriteCache;
    }
    
    public void game() {
    usedTime=1000;
    initWorld();
    while (isVisible()) {
    long startTime = System.currentTimeMillis();
    updateWorld();
    paintWorld();
    usedTime = System.currentTimeMillis()-startTime;
    
    try {
    Thread.sleep(stage.ODSWIEZENIE);
    } catch (InterruptedException e) {}
    }
    }
    public void keyPressed(KeyEvent e) {
    p.keyPressed(e);
    }
    public void keyReleased(KeyEvent e) {
    p.keyReleased(e);
    }
    public void keyTyped(KeyEvent e) {}

    
    public static void main(String[] args) {
    Pacman inv = new Pacman();
    inv.game();
    }
}
