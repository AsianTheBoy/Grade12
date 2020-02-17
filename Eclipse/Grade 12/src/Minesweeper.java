import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * TO DO LIST (in order of most to least important)
 * 
 * Min Young:
 * restart button
 * first click not bomb
 * 
 * Ramon:
 * Timer and number of bombs
 * flag on right click
 * change colour of bombs and numbers (e.g 1 is green, 2 is blue, 3 is orange)
 * 
 * Both:
 * Different difficulties
 * Start Menu asking to pick difficulty
 */

public class Minesweeper extends JFrame {

	private JButton board[][];
	private JButton btn;
	private int row = 8;
	private int col = 8;
	private int count = 0;
	private boolean firstClick = true;

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Minesweeper frame = new Minesweeper();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Minesweeper() {
		this.setSize(800, 800);
		setLayout(new GridLayout(8, 8));
		board = new JButton[row][col];
		initialize();
	}

	public void initialize() {
		boolean addedBomb = false;
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				board[r][c] = new JButton();
				addedBomb = mines(board[r][c], count);
				if (addedBomb == true)
					count++;
				board[r][c].addActionListener(actionListener);
				add(board[r][c]);
			}
		}
	}

	public boolean mines(JButton x, int count) {
		boolean addedBomb = false;
		int rand = (int) (Math.random() * (100 - 1)) + 1;
		if (count > 9) {
			x.setText("");
		} else if (rand > 78) {
			x.setText("\u200C");
			addedBomb = true;
		}
		return addedBomb;
	}

	public void bombNum() {
		int[] coor;
		coor = findButton();
		String result = Integer.toString(findBomb(coor[0], coor[1]));
		if (result.equals("0")) {
			btn.setText("");
			findBlank(coor[0], coor[1]);
		} else {
			btn.setText(result);
		}
	}

	public void bombNum(boolean firstClick) {
		int[] coor;
		coor = findButton();
		String result = Integer.toString(findBomb(coor[0], coor[1]));
		if (result.equals("0")) {
			btn.setText("");
			findBlank(coor[0], coor[1]);
		} else if (!result.equals("0") && firstClick == true) {
			firstBlank(coor[0], coor[1]);
			bombNum();
		} else {
			bombNum();
		}
	}

	ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String text = e.getActionCommand();
			btn = (JButton) e.getSource();
			if (text.equals("\u200C") && firstClick) {
				btn.setText(".");
				firstClick = false;
				moveBomb();
				bombNum();
			} else if (text.equals("\u200C")) {
				btn.setText("*");
				btn.setFont(new Font("Arial", Font.BOLD, 40));
				endGame();
			} else {
				btn.setText(".");
				bombNum(firstClick);
				firstClick = false;
			}
		}
	};

	public int[] findButton() {
		int[] coor = { 0, 0 };
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				String text = board[r][c].getText();
				if (text.equals(".")) {
					coor[0] = r;
					coor[1] = c;
					board[r][c].setText("");
				}
			}
		}
		return coor;
	}

	public void moveBomb() {
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (board[r][c].getText().equals("")) {
					board[r][c].setText("\u200C");
					r = 10;
					c = 10;
					break;
				}
			}
		}
	}

	public void firstBlank(int i, int j) {
		if (i + 1 != row && (board[i + 1][j].getText().equals("\u200C"))) {
			board[i + 1][j].setText("");
			moveBomb();
		}
		if (i + 1 != row && j + 1 != col && (board[i + 1][j + 1].getText().equals("\u200C"))) {
			board[i + 1][j + 1].setText("");
			moveBomb();
		}
		if (i - 1 != -1 && j + 1 != col && (board[i - 1][j + 1].getText().equals("\u200C"))) {
			board[i - 1][j + 1].setText("");
			moveBomb();
		}
		if (i + 1 != row && j - 1 != -1 && (board[i + 1][j - 1].getText().equals("\u200C"))) {
			board[i + 1][j - 1].setText("");
			moveBomb();
		}
		if (i - 1 != -1 && j - 1 != -1 && (board[i - 1][j - 1].getText().equals("\u200C"))) {
			board[i - 1][j - 1].setText("");
			moveBomb();
		}
		if (i - 1 != -1 && (board[i - 1][j].getText().equals("\u200C"))) {
			board[i - 1][j].setText("");
			moveBomb();
		}
		if (j + 1 != col && (board[i][j + 1].getText().equals("\u200C"))) {
			board[i][j + 1].setText("");
			moveBomb();
		}
		if (j - 1 != -1 && (board[i][j - 1].getText().equals("\u200C"))) {
			board[i][j - 1].setText("");
			moveBomb();
		}
	}

	public int findBomb(int i, int j) {
		int bombs = 0;
		if (i + 1 != row && (board[i + 1][j].getText().equals("\u200C")))
			bombs++;
		if (i + 1 != row && j + 1 != col && (board[i + 1][j + 1].getText().equals("\u200C")))
			bombs++;
		if (i - 1 != -1 && j + 1 != col && (board[i - 1][j + 1].getText().equals("\u200C")))
			bombs++;
		if (i + 1 != row && j - 1 != -1 && (board[i + 1][j - 1].getText().equals("\u200C")))
			bombs++;
		if (i - 1 != -1 && j - 1 != -1 && (board[i - 1][j - 1].getText().equals("\u200C")))
			bombs++;
		if (i - 1 != -1 && (board[i - 1][j].getText().equals("\u200C")))
			bombs++;
		if (j + 1 != col && (board[i][j + 1].getText().equals("\u200C")))
			bombs++;
		if (j - 1 != -1 && (board[i][j - 1].getText().equals("\u200C")))
			bombs++;
		return bombs;
	}

	public void findBlank(int i, int j) {
		if (i < 0 || i > 7 || j < 0 || j > 7)
			return;

		if (findBomb(i, j) == 0 && board[i][j].isVisible()) {
			board[i][j].setVisible(false);
			findBlank(i + 1, j);
			findBlank(i - 1, j);
			findBlank(i, j - 1);
			findBlank(i, j + 1);
			findBlank(i + 1, j + 1);
			findBlank(i - 1, j - 1);
			findBlank(i - 1, j - 1);
			findBlank(i - 1, j + 1);
		} else {
			String result = Integer.toString(findBomb(i, j));
			board[i][j].setText(result);
			return;
		}
	}

	public void endGame() {
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				String text = board[r][c].getText();
				if (text.equals("\u200C")) {
					board[r][c].setText("*");
					board[r][c].setFont(new Font("Arial", Font.BOLD, 40));
				}
			}
		}
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				board[r][c].setEnabled(false);
			}
		}
	}
}