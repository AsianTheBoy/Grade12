import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * getMousePosition()
 * 
 */

public class TEST2 extends JFrame {

	JButton board[][];
	JLabel underBoard[][];
	int row = 8;
	int col = 8;
	int currow = 0;
	int curcol = 0;
	int count = 0;

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TEST2 frame = new TEST2();
					frame.add(buttonOnLabel());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static JComponent buttonOnLabel(){
        JLayeredPane layers = new JLayeredPane();

        JLabel label = new JLabel("label");
        JButton button = new JButton("button");

        label.setBounds(40, 20, 100, 50);
        button.setBounds(20, 20, 150, 75);

        layers.add(label, new Integer(2));
        layers.add(button, new Integer(1));

        return layers;
    }
	
	public TEST2() {
		this.setSize(800, 800);
		setLayout(new GridLayout(8, 8));
		board = new JButton[row][col];
		underBoard = new JLabel[row][col];
		initialize();
		// JLabel bombs = new JLabel("# of Bombs: 10");
		// add(bombs);
	}

	//public int bombCheck() {
		
	//}
	
	ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String box = e.getActionCommand();
			JButton btn = (JButton) e.getSource();
			if (box.equals("\u200C")) {
				btn.setText("*");
			}
			else {
				btn.setVisible(false);
			}
		}
	};

	public void initialize() {
		for (int r = 0; r < row; r++) {
			currow++;
			for (int c = 0; c < col; c++) {
				board[r][c] = new JButton();
				underBoard[r][c] = new JLabel();
				underBoard[r][c].setText("F");
				mines(board[r][c], count);
				String text = board[r][c].getText();
				if (text == "\u200C") {
					count++;
				}
				board[r][c].addActionListener(actionListener);
				add(board[r][c]);
				add(underBoard[r][c]);
				curcol++;
			}
		}

	}

	public void mines(JButton x, int count) {
		int rand = (int) (Math.random() * (100 - 1)) + 1;
		if (count > 9) {
			x.setText("");
		} else if (rand > 78) {
			x.setText("\u200C");
		}
	}
}
