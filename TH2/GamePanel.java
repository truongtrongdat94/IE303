import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private static final int WIDTH = 360, HEIGHT = 640;
    private static final int PIPE_INTERVAL = 1500; // ms
    private static final int GAP = 160;

    private BufferedImage bg;
    private Bird bird;
    private ArrayList<Pipe> pipes;
    private Timer gameTimer, pipeTimer;
    private int score = 0;
    private boolean gameOver = false;
    private Random rand = new Random();

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        addKeyListener(this);

        try {
            bg = ImageIO.read(new File("flappybirdbg.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        initGame();
    }

    private void initGame() {
        bird = new Bird(60, HEIGHT / 2);
        pipes = new ArrayList<>();
        score = 0;
        gameOver = false;

        if (gameTimer != null) gameTimer.stop();
        if (pipeTimer != null) pipeTimer.stop();

        // Game loop: ~60fps
        gameTimer = new Timer(1000 / 60, this);
        gameTimer.start();

        // Spawn pipes
        pipeTimer = new Timer(PIPE_INTERVAL, e -> spawnPipes());
        pipeTimer.start();
        spawnPipes();
    }

    private void spawnPipes() {
        int minHeight = 60;
        int maxHeight = HEIGHT - GAP - minHeight;
        int topHeight = minHeight + rand.nextInt(maxHeight - minHeight + 1);
        int bottomY = topHeight + GAP;
        int bottomHeight = HEIGHT - bottomY;

        pipes.add(new Pipe(WIDTH, 0, topHeight, true));
        pipes.add(new Pipe(WIDTH, bottomY, bottomHeight, false));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            bird.update();

            // Update pipes
            for (int i = pipes.size() - 1; i >= 0; i--) {
                Pipe p = pipes.get(i);
                p.update();
                if (p.isOffScreen()) {
                    pipes.remove(i);
                }
            }

            // Score: count once per top pipe passed
            for (Pipe p : pipes) {
                if (!p.passed && p.isTop && p.x + p.width < bird.x) {
                    p.passed = true;
                    score++;
                }
            }

            // Collision with ground/ceiling
            if (bird.y + bird.height >= HEIGHT || bird.y <= 0) {
                triggerGameOver();
            }

            // Collision with pipes
            for (Pipe p : pipes) {
                if (bird.getBounds().intersects(p.getBounds())) {
                    triggerGameOver();
                    break;
                }
            }
        }
        repaint();
    }

    private void triggerGameOver() {
        gameOver = true;
        gameTimer.stop();
        pipeTimer.stop();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Background
        if (bg != null)
            g.drawImage(bg, 0, 0, WIDTH, HEIGHT, null);
        else {
            g.setColor(Color.CYAN);
            g.fillRect(0, 0, WIDTH, HEIGHT);
        }

        // Pipes
        for (Pipe p : pipes) p.draw(g);

        // Bird
        bird.draw(g);

        // Score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        g.drawString(String.valueOf(score), WIDTH / 2 - 10, 50);

        // Game Over
        if (gameOver) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Game Over", WIDTH / 2 - 100, HEIGHT / 2 - 40);
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Score: " + score, WIDTH / 2 - 50, HEIGHT / 2);
            g.drawString("Press SPACE to restart", WIDTH / 2 - 110, HEIGHT / 2 + 40);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_ENTER) {
            if (gameOver) {
                initGame();
            } else {
                bird.jump();
            }
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
