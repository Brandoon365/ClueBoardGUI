package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class RoomGuessPanel extends JPanel {
	private JPanel guess;
	private JComboBox roomGuess;
	
	private JComboBox createRoomGuess() {
		roomGuess = new JComboBox();
		roomGuess.setPreferredSize(new Dimension(100,175));
		roomGuess.addItem("Not Sure");
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

	public RoomGuessPanel() {
		guess = new JPanel();
		this.setBorder(new TitledBorder(new EtchedBorder(), "Room Guess"));
		setSize(new Dimension(300, 250));
		this.add(guess,BorderLayout.WEST);
		roomGuess = createRoomGuess();
		guess.add(roomGuess,BorderLayout.WEST);
	}
}
