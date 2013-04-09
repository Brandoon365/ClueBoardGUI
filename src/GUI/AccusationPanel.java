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

public class AccusationPanel extends JFrame{
	private JPanel person,weapon,room,frame,plabel,wlabel,rlabel; 
	private JComboBox personGuess, roomGuess, weaponGuess;
	private JButton submit, cancel;
	private JLabel roomL,weaponL,personL;
	private ClueGame game;
	
	private JComboBox createPersonGuess() {
		personGuess = new JComboBox();
		personGuess.setPreferredSize(new Dimension(100,175));
		personGuess.addItem("Miss Scarlet");
		personGuess.addItem("Colonel Mustard");
		personGuess.addItem("Mr. Green");
		personGuess.addItem("Mrs. White");
		personGuess.addItem("Mrs. Peacock");
		personGuess.addItem("Professor Plum");
		return personGuess;
	}
	
	private JComboBox createRoomGuess() {
		roomGuess = new JComboBox();
		roomGuess.setPreferredSize(new Dimension(100,175));
		roomGuess.addItem("Kitchen");
		roomGuess.addItem("Dining Room");
		roomGuess.addItem("Lounge");
		roomGuess.addItem("Ballroom");
		roomGuess.addItem("Conservatory");
		roomGuess.addItem("Hall");
		roomGuess.addItem("Study");
		roomGuess.addItem("Library");
		roomGuess.addItem("Billiard Room");
		return roomGuess;
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
	
	public AccusationPanel(ClueGame game){
		this.game = game;
		setSize(new Dimension(300, 200));
		setTitle("Make an accusation");
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
		submit = new JButton("Submit");
		submit.addActionListener(new submitListener());
		cancel = new JButton("Cancel");
		cancel.addActionListener(new cancelListener());
		personGuess = createPersonGuess();
		weaponGuess = createWeaponGuess();
		roomGuess = createRoomGuess();
		frame.setLayout(new GridLayout(4,2));
		plabel.add(personL);
		wlabel.add(weaponL);
		rlabel.add(roomL);
		frame.add(plabel);
		frame.add(personGuess);
		frame.add(wlabel);
		frame.add(weaponGuess);
		frame.add(rlabel);
		frame.add(roomGuess);
		frame.add(submit);
		frame.add(cancel);
		this.add(frame);
	}
	
	private class submitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String person,weapon,room;
			person = (String) personGuess.getSelectedItem();
			weapon = (String) weaponGuess.getSelectedItem();
			room = (String) roomGuess.getSelectedItem();
			Solution accusation = new Solution(person, weapon, room);
			if(game.checkAccusation(accusation)) {
				game.setGameDone(true);
				game.showWinScreen();
			}
			else {
				game.showIncorrectScreen();
				game.setTurnDone(true);
				game.getHuman().setMadeAccusation(true);
			}
			game.getBoard().repaint();
			setVisible(false);		
		}
	}
	
	private class cancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//if(!makeAccusation){
			setVisible(false);
			//}
			//else{
			//	JOptionPane.showMessageDialog(null,"Your need to make an accusation before continuing","Address issue before continuing",JOptionPane.ERROR_MESSAGE);
			//}
		}
	}
	
	public static void main(String[] args) {
	//	AccusationPanel mainFrame = new AccusationPanel();
	//	mainFrame.setVisible(true);
	}

}
