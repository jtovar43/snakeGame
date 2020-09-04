import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

public class RenderPanel extends JPanel
{
  public Color black = new Color(0);

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(black);
    g.fillRect(0, 0, 800, 700);
    Snake snake = Snake.snake;
    g.setColor(Color.BLUE);
    for (Point point : snake.snakeParts) {
      g.fillRect(point.x * 10, point.y * 10, 
          10, 10);
    }
    g.fillRect(snake.head.x * 10, snake.head.y * 10, 
        10, 10);
    g.setColor(Color.RED);
    g.fillRect(snake.cherry.x * 10, snake.cherry.y * 10, 
        10, 10);
    String string = "Score: " + snake.score + ", Length: " + 
      snake.tailLength + ", Time: " + (snake.time / 20);
    g.setColor(Color.white);
    g.drawString(string, (int)((getWidth() / 2) - string.length() * 2.5F), 
        10);
    string = "Game Over!";
    if (snake.over)
      g.drawString(string, 
          (int)((getWidth() / 2) - string.length() * 2.5F), 
          (int)snake.dim.getHeight() / 4); 
    string = "Paused!";
    if (snake.paused && !snake.over)
      g.drawString(string, 
          (int)((getWidth() / 2) - string.length() * 2.5F), 
          (int)snake.dim.getHeight() / 4); 
  }
}