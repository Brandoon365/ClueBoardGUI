package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import clueBoard.*;

public class GuessPanel extends JDialog{
	private JPanel person,weapon,room,frame,plabel,wlabel,rlabel, submitP, main; 
	private JComboBox personGuess, weaponGuess;
	private JButton submit, cancel;
	private JLabel roomL,weaponL,personL, roomGuess;
	private ClueGame game;
	
	private JComboBox createPersonGuess() {
		personGuess = new JComboBox();
		personGuess.setPreferredSize(new Dimension(100,175));
		personGuess.addItem("Miss Scarlet");
		personGuess.addItem("Colonel Mustard");
		personGuess.addItem("Reverend Green");
		personGuess.addItem("Mrs. White");
		personGuess.addItem("Mrs. Peacock");
		personGuess.addItem("Professor Plum");
		return personGuess;
	}
	
	private JComboBox createWeaponGuess(){
		weaponGuess = new JComboBox();
		weaponGuess.setPreferredSize(new Dimension(100,175));
		weaponGuess.addItem("Candlestick");
		weaponGuess.addItem("Lead Pipe");
		weaponGuess.addItem("Knife");
		weaponGuess.addItem("Revolver");
		weaponGuess.addItem("Wrench");
		weaponGuess.addItem("Rope");
		return weaponGuess;
	}
	
	public GuessPanel(ClueGame game){
		this.game = game;
		setSize(new Dimension(300, 200));
		setTitle("Make a suggestion");
		personL = new JLabel("Person");
		weaponL = new JLabel("Weapon");
		roomL = new JLabel("Room");
		person = new JPanel();
		weapon = new JPanel();
		room = new JPanel();
		frame = new JPanel();
		plabel = new JPanel();
		wlabel = new JPanel();
		rlabel = new JPanel();
		submitP = new JPanel();
		main = new JPanel();
		main.setLayout(new GridLayout(2,1));
		submitP.setLayout(new GridLayout(1,1));
		submit = new JButton("Submit");
		submit.addActionListener(new submitListener());
		submit.setPreferredSize(new Dimension(75,200));
		submitP.setPreferredSize(new Dimension(75,200));
		submitP.add(submit);
		personGuess = createPersonGuess();
		weaponGuess = createWeaponGuess();
		roomGuess = new JLabel(game.getHuman().getCurrentRoom());
		frame.setLayout(new GridLayout(3,2));
		plabel.add(personL);
		wlabel.add(weaponL);
		rlabel.add(roomL);
		frame.add(plabel);
		frame.add(personGuess);
		frame.add(wlabel);
		frame.add(weaponGuess);
		frame.add(rlabel);
		frame.add(roomGuess);
		//frame.add(submitP);
		main.add(frame);
		main.add(submitP);
		this.add(main);
	}
	
	private class submitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String person,weapon,room;
			Card disproveCard = null; 
			person = (String) personGuess.getSelectedItem();
			weapon = (String) weaponGuess.getSelectedItem();
			room = (game.getHuman().getCurrentRoom());
			Solution accusation = new Solution(person, weapon, room);
			disproveCard = (game.handleSuggestion(accusation));
			game.getControlPanel().setGuess(accusation);
			game.getControlPanel().setRevealed(disproveCard);
			if(disproveCard != null){
				JOptionPane.showMessageDialog(null,"This is an incorrect guess. Keep playing","Wrong",JOptionPane.ERROR_MESSAGE);
				game.setTurnDone(true);
			}else if(disproveCard == null){
				JOptionPane.showMessageDialog(null,"If you dont have these three cards, make an accusation to win!","Cannot be disproved",JOptionPane.DEFAULT_OPTION);
				game.setTurnDone(true);
			}
			game.getBoard().repaint();
			setVisible(false);
		}
	}
	
	public static void main(String[] args) {
	//	ClueGame game = new ClueGame();
	//	System.out.println(game.getAnswer().person + game.getAnswer().room + game.getAnswer().weapon);
	//	System.out.println(game.getHuman().getCards().get(0).getCard() + game.getHuman().getCards().get(1).getCard() + game.getHuman().getCards().get(2).getCard());
	//	game.getHuman().setCurrentRoom('L');
	//	GuessPanel mainFrame = new GuessPanel(game);
	//	mainFrame.setVisible(true);
	}

}
