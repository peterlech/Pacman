import java.awt.image.ImageObserver;



/**
 *
 * @author Lechnio
 */
public interface  stage extends ImageObserver{
    public static final int SZEROKOSC = 800;
    public static final int WYSOKOSC = 600;
    public static final int ODSWIEZENIE = 10;
    public SpiritCache getSpriteCache();
}
