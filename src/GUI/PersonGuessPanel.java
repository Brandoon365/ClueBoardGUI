package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PersonGuessPanel extends JPanel {
	private JPanel guess;
	private JComboBox personGuess;
	
	private JComboBox createPersonGuess() {
		personGuess = new JComboBox();
		personGuess.setPreferredSize(new Dimension(100,175));
		personGuess.addItem("Not Sure");
		personGuess.addItem("Miss Scarlet");
		personGuess.addItem("Colonel Mustard");
		personGuess.addItem("Reverend Green");
		personGuess.addItem("Mrs. White");
		personGuess.addItem("Mrs. Peacock");
		personGuess.addItem("Professor Plum");
		return personGuess;
	}
	
	public PersonGuessPanel() {
		guess = new JPanel();
		this.setBorder(new TitledBorder(new EtchedBorder(), "Person Guess"));
		setSize(new Dimension(300, 250));
		this.add(guess,BorderLayout.WEST);
		personGuess = createPersonGuess();
		guess.add(personGuess,BorderLayout.WEST);
	}
}
