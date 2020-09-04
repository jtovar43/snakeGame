import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener {
  public static Snake snake;
  public JFrame jframe;
  public RenderPanel renderPanel;
  public Timer timer;
  public ArrayList<Point> snakeParts;
  public static final int UP = 0;
  public static final int DOWN = 1;
  public static final int LEFT = 2;
  public static final int RIGHT = 3;
  public static final int SCALE = 10;
  
  public Snake() {
    this.timer = new Timer(20, this);
    this.snakeParts = new ArrayList<>();
    this.ticks = 0; this.direction = 1; this.tailLength = 10;
    this.over = false;
    this.dim = Toolkit.getDefaultToolkit().getScreenSize();
    this.jframe = new JFrame("Snake");
    this.jframe.setVisible(true);
    this.jframe.setSize(805, 700);
    this.jframe.setResizable(false);
    this.jframe.setLocation(this.dim.width / 2 - this.jframe.getWidth() / 2, this.dim.height / 
        2 - this.jframe.getHeight() / 2);
    this.jframe.add(this.renderPanel = new RenderPanel());
    this.jframe.setDefaultCloseOperation(3);
    this.jframe.addKeyListener(this);
    startGame();
  }
  public int ticks; public int direction; public int score; public int tailLength; public int time; public Point head; public Point cherry; public Random random; public boolean over; public boolean paused; public Dimension dim;
  public void startGame() {
    this.over = false;
    this.paused = false;
    this.time = 0;
    this.score = 0;
    this.tailLength = 1;
    this.ticks = 0;
    this.direction = 1;
    this.head = new Point(0, -1);
    this.random = new Random();
    this.snakeParts.clear();
    this.cherry = new Point(this.random.nextInt(79), this.random.nextInt(66));
    this.timer.start();
  }

  
  public void actionPerformed(ActionEvent arg0) {
    this.renderPanel.repaint();
    this.ticks++;
    if (this.ticks % 2 == 0 && this.head != null && !this.over && !this.paused) {
      this.time++;
      this.snakeParts.add(new Point(this.head.x, this.head.y));
      if (this.direction == 0)
        if (this.head.y - 1 >= 0 && noTailAt(this.head.x, this.head.y - 1)) {
          this.head = new Point(this.head.x, this.head.y - 1);
        } else {
          this.over = true;
        }   if (this.direction == 1)
        if (this.head.y + 1 < 67 && noTailAt(this.head.x, this.head.y + 1)) {
          this.head = new Point(this.head.x, this.head.y + 1);
        } else {
          this.over = true;
        }   if (this.direction == 2)
        if (this.head.x - 1 >= 0 && noTailAt(this.head.x - 1, this.head.y)) {
          this.head = new Point(this.head.x - 1, this.head.y);
        } else {
          this.over = true;
        }   if (this.direction == 3)
        if (this.head.x + 1 < 80 && noTailAt(this.head.x + 1, this.head.y)) {
          this.head = new Point(this.head.x + 1, this.head.y);
        } else {
          this.over = true;
        }   if (this.snakeParts.size() > this.tailLength)
        this.snakeParts.remove(0); 
      if (this.cherry != null && 
        this.head.equals(this.cherry)) {
        this.score += 10;
        this.tailLength++;
        this.cherry.setLocation(this.random.nextInt(79), this.random.nextInt(66));
      } 
    } 
  }

  
  public boolean noTailAt(int x, int y) {
    for (Point point : this.snakeParts) {
      if (point.equals(new Point(x, y))) {
        return false;
      }
    } 
    return true;
  }

  
  public static void main(String[] args) { snake = new Snake(); }


  
  public void keyPressed(KeyEvent e) {
    int i = e.getKeyCode();
    if ((i == 65 || i == 37) && this.direction != 3)
      this.direction = 2; 
    if ((i == 68 || i == 39) && this.direction != 2)
      this.direction = 3; 
    if ((i == 87 || i == 38) && this.direction != 1)
      this.direction = 0; 
    if ((i == 83 || i == 40) && this.direction != 0)
      this.direction = 1; 
    if (i == 32)
      if (this.over) {
        startGame();
      } else {
        this.paused = !this.paused;
      }  
  }
  
  public void keyReleased(KeyEvent e) {}
  
  public void keyTyped(KeyEvent e) {}
}
