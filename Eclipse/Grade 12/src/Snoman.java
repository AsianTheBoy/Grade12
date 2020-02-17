import java.awt.*;
import javax.swing.JFrame;

class Snoman extends Canvas {

	int width3; // width of bottom circle
	int width2; // width of middle circle
	int width1; // width of head
	int x; // center of middle ball
	int y; // center of middle ball

	// class constructor if no arguments are provided
	// you need to think about ideal proportions for a Snowman
	public Snoman() {
		width3 = 200;
		width2 = 150;
		width1 = 100;
		x = 600;
		y = 200;
	}

	// class constructor providing the head width, and center of head
	public Snoman(int w, int a, int b) {
		width1 = b;
		// using the provided length w, calculate the ideal proportions for width2 and
		// width3
		width2 = b + (b/2);
		width3 = b * 2;
		x = w;
		y = a;
	}

	// note: you can create your own constructors if you want
	// other possible options: changing the fill colour, other body parts etc.
	// helper method to draw circle at center
	public void drawCenteredCircle(Graphics g, int x, int y, int w) {
		int a = x - (w / 2);
		int b = y - (w / 2);
		g.fillOval(a, b, w, w);
	}
	//left eye
	public void drawLeftCircle(Graphics g, int x, int y, int w) {
		int a = x - (w / 4);
		int b = y - (w / 4);
		g.fillOval(a, b, 10, 10);
	}
	//right eye
	public void drawRightCircle(Graphics g, int x, int y, int w) {
		int a = x + (w / 4);
		int b = y - (w / 4);
		g.fillOval(a, b, 10, 10);
	}
	public void drawCenteredTriangle(Graphics g, int x, int y, int w) {
		int a = x ;
		int b = y - 10;
		int[] xcoor = {a,a+width1/3,a};
		int[] ycoor = {b,b+5,b+10};
		g.fillPolygon(xcoor, ycoor, 3);
	}

	// method to draw the snowman - use inside paint() - see example
	// only one circle is drawn - use the drawCenteredCircle and variables to draw
	// the other two
	public void draw(Graphics g) {
		//arms
		g.setColor(Color.decode("#3a1900"));
		g.drawLine(x, y+width2, x+width1, y+40);
		g.drawLine(x, y+width2, x-width1, y+40);
		//body
		g.setColor(Color.white);
		drawCenteredCircle(g, x, y, width1);
		drawCenteredCircle(g, x, y+(width2/2), width2); 
		drawCenteredCircle(g, x, y+(width3), width3); 
		//eyes
		g.setColor(Color.black);
		drawLeftCircle(g, x, y, width1);
		drawRightCircle(g, x, y, width1);
		//hat
		g.setColor(Color.black);
		g.drawLine(x-(width1/3), y-(width1/2)+10, x+(width1/3), y-(width1/2)+10);
		g.fillRect(x-(width1/5), y-50-(width1/2)+5, (int)((double)width1/2.25), 50+5);
		//nose
		g.setColor(Color.decode("#f26f0c"));
		drawCenteredTriangle(g,x,y,width1);
	}

	public static void main(String[] args) {

		JFrame win = new JFrame("Snoman");
		win.setSize(800, 800);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Snoman canvas = new Snoman();
		win.add(canvas);
		win.setVisible(true);

	}
}