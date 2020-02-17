import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * TO DO LIST (in order of most to least important)
 * 
 * Min Young:
 * reveal all bombs at end
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

public class TEST extends JFrame {

    JButton board[][];
    JButton btn;
    int bombNear[][] = new int[8][8];
    int row = 8;
    int col = 8;
    int count = 0;

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TEST frame = new TEST();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TEST() {
        this.setSize(800, 800);
        setLayout(new GridLayout(8, 8));
        board = new JButton[row][col];
        initialize();
    }

    public void initialize() {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                board[r][c] = new JButton();
                mines(board[r][c], count);
                String text = board[r][c].getText();
                if (text == "\u200C") {
                    count++;
                }
                board[r][c].addActionListener(actionListener);
                add(board[r][c]);
            }
        }
    }

    public void findBlank(int i, int j) {
        if (i < 0 || i > 7 || j < 0 || j > 7) return; // check for bounds

        if (findBomb(i,j) == 0 && board[i][j].isVisible()) {
            board[i][j].setVisible(false);
            findBlank( i+1, j );
            findBlank( i-1, j );
            findBlank( i, j-1 );
            findBlank( i, j+1 );
            findBlank( i+1, j+1 );
            findBlank( i-1, j-1 );
            findBlank( i-1, j-1 );
            findBlank( i-1, j+1 );
        }
        else {
            String result = Integer.toString(findBomb(i, j));
            board[i][j].setText(result);
            return;
        }
    }

    public int findBomb(int i, int j) {
        int bombs = 0;
        if (i + 1 != row && (board[i + 1][j].getText().equals("\u200C")))
            bombs++;
        if (i + 1 != row && j + 1 != col
                && (board[i + 1][j + 1].getText().equals("\u200C")))
            bombs++;
        if (i - 1 != -1 && j + 1 != col
                && (board[i - 1][j + 1].getText().equals("\u200C")))
            bombs++;
        if (i + 1 != row && j - 1 != -1
                && (board[i + 1][j - 1].getText().equals("\u200C")))
            bombs++;
        if (i - 1 != -1 && j - 1 != -1
                && (board[i - 1][j - 1].getText().equals("\u200C")))
            bombs++;
        if (i - 1 != -1 && (board[i - 1][j].getText().equals("\u200C") ))
            bombs++;
        if (j + 1 != col && (board[i][j + 1].getText().equals("\u200C")))
            bombs++;
        if (j - 1 != -1 && (board[i][j - 1].getText().equals("\u200C")))
            bombs++;
        return bombs;
    }

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

    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String box = e.getActionCommand();
            btn = (JButton) e.getSource();
            if (box.equals("\u200C") || box.equals("*")) {
                btn.setText("*");
                System.exit(0);
                // REVEAL ALL BOMBS AND ADD RESTART BUTTON
            } else {
                btn.setText(".");
                bombNum();
            }
        }
    };

    public void mines(JButton x, int count) {
        int rand = (int) (Math.random() * (100 - 1)) + 1;
        if (count > 9) {
            x.setText("");
        } else if (rand > 78) {
            x.setText("\u200C");
        }
    }
}