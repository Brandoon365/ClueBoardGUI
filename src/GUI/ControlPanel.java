package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueBoard.Card;
import clueBoard.ClueGame;
import clueBoard.Solution;
import GUI.*;


public class ControlPanel extends JPanel{
	private JPanel turnPanel, dicePanel, guessPanel, responsePanel, topPanel, botPanel;
	private JButton nextPlayer, accusation;
	private JLabel turn, dice, guess, response;
	private JTextField turnValue, diceValue, responseValue, guessValue;
	private ClueGame game;
	private AccusationPanel accPanel;
	

	public ControlPanel(ClueGame game){
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.game = game;
		setSize(new Dimension(400,100));
		setPreferredSize(new Dimension(300, 100));
		turn = new JLabel("Whose Turn?");
		dice = new JLabel("Roll");
		guess = new JLabel("Guess");
		response = new JLabel("Response");
		nextPlayer = new JButton("Next player");
		nextPlayer.addActionListener(new ButtonListener());
		accusation = new JButton("Make an accusation");
		accusation.addActionListener(new ButtonListener());
		diceValue = new JTextField(1);
		diceValue.setEditable(false);
		guessValue = new JTextField(30);
		guessValue.setEditable(false);
		responseValue = new JTextField(15);
		responseValue.setEditable(false);
		turnValue = new JTextField(15);
		turnValue.setEditable(false);
		dicePanel = new JPanel();
		guessPanel = new JPanel();
		responsePanel = new JPanel();
		turnPanel = new JPanel();
		topPanel = new JPanel();
		botPanel = new JPanel();
		this.add(topPanel,BorderLayout.CENTER);
		this.add(botPanel,BorderLayout.SOUTH);
		topPanel.setLayout(new GridLayout(1,3));
		botPanel.setLayout(new GridLayout(1,3));
		turnPanel.add(turn);
		turnPanel.add(turnValue);
		turnValue.setBorder(new TitledBorder(new EtchedBorder()));
		topPanel.add(turnPanel);
		topPanel.add(nextPlayer);
		topPanel.add(accusation);
		
		dicePanel.setBorder(new TitledBorder(new EtchedBorder(), "Die"));
		guessPanel.setBorder(new TitledBorder(new EtchedBorder(), "Guess"));
		responsePanel.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
		dicePanel.add(dice);
		diceValue.setText("    ");
		dicePanel.add(diceValue);
		diceValue.setBorder(new EtchedBorder());
		botPanel.add(dicePanel);
	
		guessPanel.add(guess);
		guessValue.setText("                                     ");
		guessPanel.add(guessValue);
		guessValue.setBorder(new EtchedBorder());
		botPanel.add(guessPanel);
		
		responsePanel.add(response);
		responseValue.setText("                            ");
		responsePanel.add(responseValue);
		responseValue.setBorder(new EtchedBorder());
		botPanel.add(responsePanel);

	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == nextPlayer) {
				if(game.turnDone && !game.isGameDone()) {
					if(game.getCurrentPlayerIndex() + 1 == game.getPlayers().size())
						game.setCurrentPlayerIndex(0);
					else
						game.setCurrentPlayerIndex(game.getCurrentPlayerIndex() + 1);
					setCurrentPlayer(game.getPlayers().get(game.getCurrentPlayerIndex()).getName());
					game.setCurrentPlayer(game.getPlayers().get(game.getCurrentPlayerIndex()));
					game.getHuman().setMadeAccusation(false);
					int roll = ClueGame.roll();
					setRoll(roll);
					game.setTurnDone(false);
					game.takeTurn(roll);
				}
				else {
					JOptionPane.showMessageDialog(null,"Your turn is not done yet.", "Your turn is not done yet.", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(e.getSource() == accusation) {
				if(game.getCurrentPlayer() == game.getHuman() && game.getPlayers().contains(game.getHuman()) && !game.getHuman().isMadeAccusation() && !game.turnDone) {
					accPanel = new AccusationPanel(game);
					accPanel.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null,"You cannot make an accusation at this time.", "You cannot make an accusation at this time.", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
		
	
	
	public void setRoll(int roll){
		this.diceValue.setText(Integer.toString(roll));
	}
	
	public void setCurrentPlayer(String name) {
		turnValue.setText(name);
	}
	
	public void setGuess(Solution guess) {
		guessValue.setText(guess.getPerson() + " in the " + guess.getRoom() + " with the " + guess.getWeapon());
	}
	
	public void setRevealed(Card card) {
		if(card == null)
			responseValue.setText("No new Clue");
		else
			responseValue.setText(card.getCard());
	}
	
	public static void main(String[] args) {
		//ControlPanel newPannel = new ControlPanel();
		//newPannel.setVisible(true);
	}

}
