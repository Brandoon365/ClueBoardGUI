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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueBoard.ClueGame;


public class ControlPanel extends JPanel{
	private JPanel turnPanel, dicePanel, guessPanel, responsePanel, topPanel, botPanel;
	private JButton nextPlayer, accusation;
	private JLabel turn, turnValue, dice, guess, response, diceValue, guessValue, responseValue;
	
	

	public ControlPanel(){
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(400,100));
		setPreferredSize(new Dimension(300, 100));
		turn = new JLabel("Whose Turn?");
		dice = new JLabel("Roll");
		guess = new JLabel("Guess");
		response = new JLabel("Response");
		nextPlayer = new JButton("Next player");
		nextPlayer.addActionListener(new ButtonListener());
		accusation = new JButton("Make an acusation");
		diceValue = new JLabel();
		guessValue = new JLabel();
		responseValue = new JLabel();
		turnValue = new JLabel();
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
				if(ClueGame.getCurrentPlayerIndex() == 5)
					ClueGame.setCurrentPlayerIndex(0);
				else
					ClueGame.setCurrentPlayerIndex(ClueGame.getCurrentPlayerIndex() + 1);
				setCurrentPlayer(ClueGame.getPlayers().get(ClueGame.getCurrentPlayerIndex()).getName());
				ClueGame.setCurrentPlayer(ClueGame.getPlayers().get(ClueGame.getCurrentPlayerIndex()));
				setRoll(ClueGame.roll());
			}
			
		}
		
	}
	
	public void setRoll(int roll){
		this.diceValue.setText(Integer.toString(roll));
	}
	
	public void setCurrentPlayer(String name) {
		turnValue.setText(name);
	}
	
	public static void main(String[] args) {
		ControlPanel newPannel = new ControlPanel();
		newPannel.setVisible(true);
	}

}
