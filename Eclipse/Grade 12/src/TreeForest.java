import java.awt.*;
import javax.swing.JFrame;
import java.util.Random;

public class TreeForest extends Canvas {

 public void paint(Graphics g) {
  int x = 300;
  int y = 300;
  int w = 400;
  int h = 600;
  drawForest(g, x, y, w, h);
  g.drawRect(x,y,w,h);
 }

 public void drawTree(Graphics g, int x, int y) {
  int[] xcoord8 = { x, x + 25, x + 50 };
  int[] ycoord8 = { y + 75, y + 0, y + 75 };
  g.setColor(Color.decode("#592c06"));
  g.fillRect(x + 17, y + 50, 16, 50);
  g.setColor(Color.decode("#0f6806"));
  g.fillPolygon(xcoord8, ycoord8, 3);
  
 }

 public void drawForest(Graphics g, int x, int y, int w, int h) {
  int width = 0;
  int height = 0;
  if (w > 50) {
	  width = w-50;
  }
  else {
	  width = 0;
  }
  if (h > 100) {
	  height = h-100;
  }
  else {
	  height = 0;
  }
  for (int i = 0; i < 500; i++) {
	  int xcoor = (int)(x + Math.random()*width);
	  int ycoor = (int)(y + Math.random()*height);
   drawTree(g, xcoor, ycoor);
  }
 }

 public static void main(String[] args) {

  JFrame win = new JFrame("Tree");
  win.setSize(800, 600);
  win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  TreeForest canvas = new TreeForest();
  win.add(canvas);
  win.setVisible(true);
 }
}
