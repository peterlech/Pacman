public class Monster extends Actor {
protected int vx;
public Monster(stage stage) {
super(stage);
setSpriteName("duch0.gif");
}
public void act() {
x+=vx;
if (x < 0 || x > stage.SZEROKOSC)
vx = -vx;
}
public int getVx() {return vx; }
public void setVx(int i) {vx = i; }
public void collision(Actor a){}
}