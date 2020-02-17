import java.awt.*;
import javax.swing.JFrame;

public class SnowmanTest extends Canvas
{
  Snoman olaf = new Snoman();
  Snoman frosty = new Snoman(200,200,100);
 
    public void paint( Graphics g )
    {
        olaf.draw(g);
        frosty.draw(g);
    }
 
    public static void main( String[] args )
    {        
        JFrame win = new JFrame("Do You Want To Build a Snowman?");
        win.setSize(800,600);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SnowmanTest canvas = new SnowmanTest();
        win.add(canvas);
        win.setVisible(true);
    }
}