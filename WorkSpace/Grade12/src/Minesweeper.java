
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Minesweeper extends JFrame {

	Timer time;
	private JButton board[][];
	private JButton btn;
	private int row = 8;
	private int col = 8;
	private int count = 0;
	private boolean firstClick = true;
	int bombCount = 0;
	int i = 0;

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
					Minesweeper frame = new Minesweeper();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor
	public Minesweeper() {
		this.setSize(1200, 1200);
		getContentPane().setLayout(new GridLayout(9, 8));

		//Timer label
		lblTime = new JLabel("Time:");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 32));
		add(lblTime);

		//Timer initial value
		label = new JLabel("0");
		label.setFont(new Font("Tahoma", Font.PLAIN, 32));
		add(label);

		label_1 = new JLabel(" ");
		add(label_1);

		//Restart button
		JButton restart = new JButton("RESTART");
		restart.addActionListener(restartBtn);
		add(restart);

		label_4 = new JLabel(" ");
		add(label_4);

		label_3 = new JLabel(" ");
		add(label_3);

		//Bomb number label
		lblNewLabel_1 = new JLabel("Bomb:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 32));
		add(lblNewLabel_1);

		bombLabel = new JLabel(" ");
		bombLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		add(bombLabel);

		board = new JButton[row][col];
		initialize();
	}

	//Method to add buttons, mines and Actionlisteners to the board
	public void initialize() {
		boolean addedBomb = false;
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				board[r][c] = new JButton();
				addedBomb = mines(board[r][c], count, r, c);
				if (addedBomb == true)
					count++;
				board[r][c].addActionListener(minefield);
				board[r][c].addMouseListener(rightClick);
				add(board[r][c]);
			}
		}
		bombNumber();
		Timer();
		time.setInitialDelay(0);
	}

	//Called when the restart button is pressed, resets the game
	ActionListener restartBtn = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			Minesweeper game = new Minesweeper();
			game.setVisible(true);
		}
	};

	// Method used in "initialize" to decide which buttons to add a bomb to, to a maximum of 10 bombs
	public boolean mines(JButton x, int count, int r, int c) {
		boolean addedBomb = false;
		int rand = (int) (Math.random() * (1000 - 1)) + 1;
		// If the method has scanned through the whole board and added less than 10 bombs, then it adds more accordingly
		if (r == 7 && c == 7 && count < 10) {
			int addMore = 10 - count;
			for (int i = 0; i < addMore; i++) {
				moveBomb();
				bombCount++;
			}
			//Stops adding bombs when 10 bombs have been added
		} else if (count == 10) {
			x.setText("");
			// Adds a bombs if the random number generated is above 825
		} else if (rand > 825) {
			x.setText("\u200C");
			addedBomb = true;
			bombCount++;
		}
		return addedBomb;
	}

	//Finds the number of bombs in a 1 square radius of the button clicked
	public void bombNum() {
		int[] coor;
		coor = findButton();
		//Finds the number of bombs around and sets it to a string
		String result = Integer.toString(findBomb(coor[0], coor[1]));
		//If there are no bombs around, finds other buttons with no bombs around it.
		if (result.equals("0")) {
			btn.setText("");
			findBlank(coor[0], coor[1]);
			// If it is the user's first click ensures that it is not a bomb
		} else if (firstClick) {
			clearFirst(coor[0], coor[1]);
			findBlank(coor[0], coor[1]);
			// Displays the number of bombs around the button and assigns each number a unique colour
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

	// ActionListener to detect left click inputs for the buttons on the board
	ActionListener minefield = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String text = e.getActionCommand();
			btn = (JButton) e.getSource();
			//Starts the timer when the user clicks the first button
			time.start();
			// Condition for the first click being a bomb: moves the bombs and ensures that first click is a blank
			if (text.equals("\u200C") && firstClick) {
				moveBomb();
				btn.setText(".");
				bombNum();
				firstClick = false;
				// Condition for when the user clicks a bomb
			} else if (text.equals("\u200C")) {
				btn.setIcon(new ImageIcon(
						"C:\\\\Users\\\\Min Young_2\\\\Desktop\\\\33137367_2063544440339560_3548634793228894208_n.png"));
				btn.setFont(new Font("Arial", Font.BOLD, 40));
				endGame();
				// Condition for the user not clicking a bomb
			} else {
				btn.setText(".");
				bombNum();
				firstClick = false;
				checkWin();
			}
		}
	};

	// MouseListner to detect right click inputs for the flag system
	MouseListener rightClick = new MouseListener() {

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			if (SwingUtilities.isRightMouseButton(e)) {
				JButton btn2 = (JButton) e.getSource();
				// Prevents the user from flagging a button with a number (already revealed)
				if (btn2.getText().equals("1") || btn2.getText().equals("2") || btn2.getText().equals("3")
						|| btn2.getText().equals("4") || btn2.getText().equals("5")) {
					btn2.setBackground(null);
					// Condition for when the button has not been flagged yet: flags the button
				} else if (!(btn2.getBackground() == Color.BLACK)) {
					// Flag = black square
					btn2.setBackground(Color.black);
					btn2.setEnabled(false);
					// Reduces the bomb count for each flag placed
					bombCount--;
					bombNumber();
					// Condition for when the button has already been flagged: unflags the button
				} else if (btn2.getBackground() == Color.BLACK) {
					btn2.setBackground(null);
					btn2.setEnabled(true);
					bombCount++;
					bombNumber();
				}
			}
		}
	};

	// Finds the row and column number of the button pressed
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

	// Moves a bomb to the upper left corner if that is taken goes one over to the right and so on.
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

	// Moves the bomb if it was clicked on the first click
	// Moves the bomb 2 squares away from the bomb clicked
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

	// Ensures that the first click will result in a blank click (no bombs within a 1 square radius)
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

	//Finds the number of bombs around the button clicked in a 1 square radius
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

	// Using recursion, finds the blank spaces (buttons with no bombs within a 1 square radius)
	// around the button clicked, until a button with a number label is found.
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

	//Checks for the win condition, if there are no more unclicked buttons other than bombs
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

	// Ends the game, reveals all remaining bombs and disables all buttons
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
		time.stop();
	}

	// Sets the label of the bomb counter
	public void bombNumber() {
		bombLabel.setText(Integer.toString(bombCount));
	}

	// The timer
	public void Timer() {
		time = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				label.setText(Integer.toString(i));
				i++;
			}
		});
	}
}