import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Bird {
    public int x, y, width = 34, height = 24;
    private BufferedImage img;
    public double velocityY = 0;
    private final double GRAVITY = 0.5;
    private final double JUMP = -9;

    public Bird(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            img = ImageIO.read(new File("flappybird.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        velocityY += GRAVITY;
        y += (int) velocityY;
    }

    public void jump() {
        velocityY = JUMP;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        if (img != null)
            g.drawImage(img, x, y, width, height, null);
        else {
            g.setColor(Color.YELLOW);
            g.fillOval(x, y, width, height);
        }
    }
}
