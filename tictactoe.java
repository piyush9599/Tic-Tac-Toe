package assigments;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class tictactoe extends JFrame implements ActionListener {
	public static int BOARD_SIZE = 3;

	public static enum gamestatus {
		incomplete, xwins, Owins, tie
	}

	private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
	boolean crossturn = true;

	public tictactoe() {
		super.setTitle("tictactoe");
		super.setSize(800, 800);
		GridLayout grid = new GridLayout(BOARD_SIZE, BOARD_SIZE);
		super.setLayout(grid);
		super.setResizable(false);
		Font font = new Font("comic sans", 1, 150);
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				JButton button = new JButton("");
				buttons[row][col] = button;
				button.setFont(font);
				button.addActionListener(this); // all buttons will be notified when action will be performed
				super.add(button);

			}
		}
		super.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton clickedbutton = (JButton) e.getSource();
		makemove(clickedbutton);
		gamestatus gs = this.getgamestatus();
		if (gs == gamestatus.incomplete) {
			return;

		}
		declarewinner(gs);
		int choice = JOptionPane.showConfirmDialog(this, "do you want to restart the game?");
		if (choice == JOptionPane.YES_OPTION) {
			for (int row = 0; row < BOARD_SIZE; row++) {
				for (int col = 0; col < BOARD_SIZE; col++) {
					buttons[row][col].setText("");

				}
			}
			crossturn = true;

		}

	}

	private void makemove(JButton clickedbutton) {
		String buttontext = clickedbutton.getText();
		if (buttontext.length() > 0) {
			JOptionPane.showMessageDialog(this, "INVALID MOVE");

		} else {
			if (crossturn) {
				clickedbutton.setText("X");
			} else {
				clickedbutton.setText("0");
			}
			crossturn = !crossturn;

		}
	}

	private gamestatus getgamestatus() {
		String text1 = "", text2 = "";
		int row = 0, col = 0;
		// text inside rows
		row = 0;
		while (row < BOARD_SIZE) {
			col = 0;
			while (col < BOARD_SIZE - 1) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row][col + 1].getText();
				if (!text1.equals(text2) || text1.length() == 0) {
					break;
				}
				col++;
			}
			if (col == BOARD_SIZE - 1) {
				if (text1.equals("X")) {
					return gamestatus.xwins;
				} else {
					return gamestatus.Owins;
				}
			}
			row++;
		}
		// text inside cols
		col = 0;
		while (col < BOARD_SIZE) {
			row = 0;
			while (row < BOARD_SIZE - 1) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row + 1][col].getText();
				if (!text1.equals(text2) || text1.length() == 0) {
					break;
				}
				row++;
			}
			if (row == BOARD_SIZE - 1) {
				if (text1.equals("X")) {
					return gamestatus.xwins;
				} else {
					return gamestatus.Owins;
				}
			}
			col++;
		}
		// diagonal 1
		row = 0;
		col=0;
		while (row < BOARD_SIZE - 1) {
			text1 = buttons[row][col].getText();
			text2 = buttons[row + 1][col + 1].getText();
			if (!text1.equals(text2) || text1.length() == 0) {
				break;
			}
			row++;
			col++;

		}
		if (row == BOARD_SIZE - 1) {
			if (text1.equals("X")) {
				return gamestatus.xwins;
			} else {
				return gamestatus.Owins;
			}
		}
		row = BOARD_SIZE - 1;
		col = 0;
		while (row > 0) {
			text1 = buttons[row][col].getText();
			text2 = buttons[row - 1][col + 1].getText();
			if (!text1.equals(text2) || text1.length() == 0) {
				break;
			}
			row--;
			col++;

		}
		if (row == 0) {
			if (text1.equals("X")) {
				return gamestatus.xwins;
			} else {
				return gamestatus.Owins;
			}
		}
		String txt = "";
		for (row = 0; row < BOARD_SIZE; row++) {
			for (col = 0; col < BOARD_SIZE; col++) {
				txt = buttons[row][col].getText();
				if (txt.length() == 0) {
					return gamestatus.incomplete;
				}
			}

		}
		return gamestatus.tie;

	}

	private void declarewinner(gamestatus gs) {

		if (gs == getgamestatus().xwins) {
			JOptionPane.showMessageDialog(this, "X wins!!");
		} else if (gs == getgamestatus().Owins) {
			JOptionPane.showMessageDialog(this, "O wins!!");
		} else {
			JOptionPane.showMessageDialog(this, "TIE!!");
		}
	}

}
