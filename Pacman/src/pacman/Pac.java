import java.awt.event.KeyEvent;
public class Pac extends Actor {
protected int vx;
protected int vy;
protected static final int PAC_SPEED = 3;
private boolean up, down, right, left;


public Pac(stage stage) {
super(stage);
setSpriteName("pacman.png");
}

public void act() {
    super.act();
    x+=vx;
    y+=vy;
    if (x < 0 || x > stage.SZEROKOSC-width)
    vx = -vx;
    if (y < 0 || y > stage.WYSOKOSC-height)
    vy = -vy;
    }
    public int getVx() { return vx; }
    public void setVx(int i) {vx = i; }
    public int getVy() { return vy; }
    public void setVy(int i) {vy = i; }
protected void updateSpeed(){
    vx=0; vy=0;
    if (down) vy=PAC_SPEED;
    if (up) vy=-PAC_SPEED;
    if (right) vx=+PAC_SPEED;
    if (left) vx=-PAC_SPEED;
    }

protected void keyReleased(KeyEvent e) {
    switch (e.getKeyCode()){
        case KeyEvent.VK_DOWN : down = false;break;
        case KeyEvent.VK_UP : up = false;break;
        case KeyEvent.VK_LEFT : left = false;break;
        case KeyEvent.VK_RIGHT : right = false;break;
    }
    updateSpeed();
}
protected void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()){
        case KeyEvent.VK_DOWN : down = true;break;
        case KeyEvent.VK_UP : up = true;break;
        case KeyEvent.VK_LEFT : left = true;break;
        case KeyEvent.VK_RIGHT : right = true;break;
    }
    updateSpeed();
}
}



