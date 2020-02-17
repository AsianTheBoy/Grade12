
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * TO DO LIST (in order of most to least important)
 * Ramon:
 * flag on right click
 * 
 * Both:
 * Different difficulties
 * Start Menu asking to pick difficulty
 */

public class MinesweeperTEST extends JFrame {

	private JButton board[][];
	private JButton btn;
	Timer time;
	private int row = 8;
	private int col = 8;
	private int count = 0;
	private boolean firstClick = true;
	int bombCount = 0;
	int i = 0;

	private JPanel contentPane;
	private JLabel bombLabel;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel lblTime;
	private JLabel lblNewLabel_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MinesweeperTEST frame = new MinesweeperTEST();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MinesweeperTEST() {
		this.setSize(1200, 1200);
		getContentPane().setLayout(new GridLayout(9, 8));

		lblTime = new JLabel("Time:");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 32));
		add(lblTime);

		label = new JLabel("0");
		label.setFont(new Font("Tahoma", Font.PLAIN, 32));
		add(label);

		label_1 = new JLabel(" ");
		add(label_1);

		JButton restart = new JButton("RESTART");
		restart.addActionListener(restartBtn);
		add(restart);

		label_4 = new JLabel(" ");
		add(label_4);

		label_3 = new JLabel(" ");
		add(label_3);

		lblNewLabel_1 = new JLabel("Bomb:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 32));
		add(lblNewLabel_1);

		bombLabel = new JLabel(" ");
		bombLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		add(bombLabel);

		board = new JButton[row][col];
		initialize();
	}

	public void initialize() {
		boolean addedBomb = false;
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				board[r][c] = new JButton();
				addedBomb = mines(board[r][c], count, r, c);
				if (addedBomb == true)
					count++;
				board[r][c].addActionListener(minefield);
				getContentPane().add(board[r][c]);
			}
		}
		bombNumber();
		Timer();
		time.setInitialDelay(0);
	}

	ActionListener restartBtn = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			MinesweeperTEST game = new MinesweeperTEST();
			game.setVisible(true);
		}
	};

	public boolean mines(JButton x, int count, int r, int c) {
		boolean addedBomb = false;
		int rand = (int) (Math.random() * (1000 - 1)) + 1;
		if (r == 7 && c == 7 && count < 10) {
			int addMore = 10 - count;
			for (int i = 0; i < addMore; i++) {
				moveBomb();
				bombCount++;
			}
		} else if (count == 10) {
			x.setText("");
		} else if (rand > 825) {
			x.setText("\u200C");
			addedBomb = true;
			bombCount++;
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
		} else if (firstClick) {
			clearFirst(coor[0], coor[1]);
			findBlank(coor[0], coor[1]);
		} else {
			if (result.equals("1")) {
				btn.setForeground(Color.decode("#1d0fff"));
				btn.setFont(new Font("Arial", Font.BOLD, 40));
				btn.setText(result);
			} else if (result.equals("2")) {
				btn.setForeground(Color.decode("#237b00"));
				btn.setFont(new Font("Arial", Font.BOLD, 40));
				btn.setText(result);
			} else if (result.equals("3")) {
				btn.setForeground(Color.decode("#f40000"));
				btn.setFont(new Font("Arial", Font.BOLD, 40));
				btn.setText(result);
			} else {
				btn.setForeground(Color.BLACK);
				btn.setFont(new Font("Arial", Font.BOLD, 40));
				btn.setText(result);
			}
		}
	}

	ActionListener minefield = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String text = e.getActionCommand();
			btn = (JButton) e.getSource();
			time.start();
			if (text.equals("\u200C") && firstClick) {
				moveBomb();
				btn.setText(".");
				bombNum();
				firstClick = false;
			} else if (text.equals("\u200C")) {
				btn.setIcon(new ImageIcon(
						"C:\\\\Users\\\\Min Young_2\\\\Desktop\\\\33137367_2063544440339560_3548634793228894208_n.png"));
				btn.setFont(new Font("Arial", Font.BOLD, 40));
				endGame();
			} else {
				btn.setText(".");
				bombNum();
				firstClick = false;
				checkWin();
			}
		}
	};

	MouseListener rightClick = new MouseListener() {
		public void mousePressed(MouseEvent e) {
			btn.getModel().setArmed(true);
			btn.getModel().setPressed(true);
		}

		public void mouseReleased(MouseEvent e) {
			btn.getModel().setArmed(false);
			btn.getModel().setPressed(false);

			if (firstClick) {
				if (SwingUtilities.isRightMouseButton(e)) {
					btn.setText("F");
				} else {
					btn.setText("X");
				}
			}
			firstClick = false;
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
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

	public void moveBombFirst(int i, int j) {
		if (i + 2 < row && (board[i + 2][j].getText().equals(""))) {
			board[i + 2][j].setText("\u200C");
		} else if (i + 2 < row && j + 2 < col && (board[i + 2][j + 2].getText().equals(""))) {
			board[i + 2][j + 2].setText("\u200C");
		} else if (i - 2 > -1 && j + 2 < col && (board[i - 2][j + 2].getText().equals(""))) {
			board[i - 2][j + 2].setText("\u200C");
		} else if (i + 2 < row && j - 2 > -1 && (board[i + 2][j - 2].getText().equals(""))) {
			board[i + 2][j - 2].setText("\u200C");
		} else if (i - 2 > -1 && j - 2 > -1 && (board[i - 2][j - 2].getText().equals(""))) {
			board[i - 2][j - 2].setText("\u200C");
		} else if (i - 2 > -1 && (board[i - 2][j].getText().equals(""))) {
			board[i - 2][j].setText("\u200C");
		} else if (j + 2 < col && (board[i][j + 2].getText().equals(""))) {
			board[i][j + 2].setText("\u200C");
		} else if (j - 2 > -1 && (board[i][j - 2].getText().equals(""))) {
			board[i][j - 2].setText("\u200C");
		} else if (i + 2 < row && (board[i + 2][j - 1].getText().equals(""))) {
			board[i + 2][j - 1].setText("\u200C");
		} else if (i + 2 < row && (board[i + 2][j + 1].getText().equals(""))) {
			board[i + 2][j + 1].setText("\u200C");
		} else if (i - 2 > -1 && (board[i - 2][j + 1].getText().equals(""))) {
			board[i - 2][j + 1].setText("\u200C");
		} else if (i - 2 > -1 && (board[i - 2][j + 1].getText().equals(""))) {
			board[i - 2][j + 1].setText("\u200C");
		} else if (j - 2 > -1 && (board[i + 1][j - 2].getText().equals(""))) {
			board[i + 1][j - 2].setText("\u200C");
		} else if (j - 2 > -1 && (board[i - 1][j - 2].getText().equals(""))) {
			board[i - 1][j - 2].setText("\u200C");
		} else if (j + 2 < col && (board[i + 1][j + 2].getText().equals(""))) {
			board[i + 1][j + 2].setText("\u200C");
		} else if (j + 2 < col && (board[i - 1][j + 2].getText().equals(""))) {
			board[i - 1][j + 2].setText("\u200C");
		} else {
			moveBomb();
		}
	}

	public void clearFirst(int i, int j) {
		if (i + 1 != row && (board[i + 1][j].getText().equals("\u200C"))) {
			board[i + 1][j].setText("");
			moveBombFirst(i, j);
		}
		if (i + 1 != row && j + 1 != col && (board[i + 1][j + 1].getText().equals("\u200C"))) {
			board[i + 1][j + 1].setText("");
			moveBombFirst(i, j);
		}
		if (i - 1 != -1 && j + 1 != col && (board[i - 1][j + 1].getText().equals("\u200C"))) {
			board[i - 1][j + 1].setText("");
			moveBombFirst(i, j);
		}
		if (i + 1 != row && j - 1 != -1 && (board[i + 1][j - 1].getText().equals("\u200C"))) {
			board[i + 1][j - 1].setText("");
			moveBombFirst(i, j);
		}
		if (i - 1 != -1 && j - 1 != -1 && (board[i - 1][j - 1].getText().equals("\u200C"))) {
			board[i - 1][j - 1].setText("");
			moveBombFirst(i, j);
		}
		if (i - 1 != -1 && (board[i - 1][j].getText().equals("\u200C"))) {
			board[i - 1][j].setText("");
			moveBombFirst(i, j);
		}
		if (j + 1 != col && (board[i][j + 1].getText().equals("\u200C"))) {
			board[i][j + 1].setText("");
			moveBombFirst(i, j);
		}
		if (j - 1 != -1 && (board[i][j - 1].getText().equals("\u200C"))) {
			board[i][j - 1].setText("");
			moveBombFirst(i, j);
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
			board[i][j].setText("x");
			findBlank(i + 1, j);
			findBlank(i - 1, j);
			findBlank(i, j - 1);
			findBlank(i, j + 1);
			findBlank(i + 1, j + 1);
			findBlank(i + 1, j - 1);
			findBlank(i - 1, j - 1);
			findBlank(i - 1, j + 1);
		} else {
			String result = Integer.toString(findBomb(i, j));
			board[i][j].setText(result);
			if (result.equals("1")) {
				board[i][j].setForeground(Color.decode("#1d0fff"));
				board[i][j].setFont(new Font("Arial", Font.BOLD, 40));
			} else if (result.equals("2")) {
				board[i][j].setForeground(Color.decode("#237b00"));
				board[i][j].setFont(new Font("Arial", Font.BOLD, 40));
			} else if (result.equals("3")) {
				board[i][j].setForeground(Color.decode("#f40000"));
				board[i][j].setFont(new Font("Arial", Font.BOLD, 40));
			} else {
				board[i][j].setForeground(Color.BLACK);
				board[i][j].setFont(new Font("Arial", Font.BOLD, 40));
			}
			return;
		}
	}

	public void checkWin() {
		int end = 0;
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				String text = board[r][c].getText();
				if (!text.equals("")) {
					end++;
				}
				if (end == 64) {
					endGame();
				}
			}
		}
	}

	public void endGame() {
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				String text = board[r][c].getText();
				if (text.equals("\u200C")) {
					board[r][c].setIcon(new ImageIcon(
							"C:\\Users\\Min Young_2\\Desktop\\33137367_2063544440339560_3548634793228894208_n.png"));
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

	public void bombNumber() {
		bombLabel.setText(Integer.toString(bombCount));
	}

	public void Timer() {
		time = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				label.setText(Integer.toString(i));
				i++;
			}
		});
	}
}