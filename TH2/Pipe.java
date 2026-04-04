import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Pipe {
    public int x, y, width = 64, height;
    public boolean passed = false;
    private BufferedImage img;
    public boolean isTop;
    private final int VELOCITY_X = -4;

    public Pipe(int x, int y, int height, boolean isTop) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.isTop = isTop;
        try {
            String file = isTop ? "toppipe.png" : "bottompipe.png";
            img = ImageIO.read(new File(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        x += VELOCITY_X;
    }

    public boolean isOffScreen() {
        return x + width < 0;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        if (img != null)
            g.drawImage(img, x, y, width, height, null);
        else {
            g.setColor(Color.GREEN);
            g.fillRect(x, y, width, height);
        }
    }
}
