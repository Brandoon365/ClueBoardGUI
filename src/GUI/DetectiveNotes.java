package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DetectiveNotes extends JFrame {
	private PeoplePanel people;
	private PersonGuessPanel personGuess;
	
	private Rooms rooms;
	private RoomGuessPanel roomGuess;
	
	private Weapons weapons;
	private WeaponGuessPanel weaponGuess;
	
	private JPanel peoplePanel, roomPanel, weaponPanel;
	
	public DetectiveNotes() {
		peoplePanel = new JPanel();
		peoplePanel.setLayout(new GridLayout(1,2));
		
		roomPanel = new JPanel();
		roomPanel.setLayout(new GridLayout(1,2));
		
		weaponPanel = new JPanel();
		weaponPanel.setLayout(new GridLayout(1,2));
		
		setSize(new Dimension(600, 750));
		setTitle("Detective Notes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(3,1));
		
		people = new PeoplePanel();
		peoplePanel.add(people);
		personGuess = new PersonGuessPanel();
		peoplePanel.add(personGuess);
		
		weapons = new Weapons();
		weaponPanel.add(weapons);
		weaponGuess = new WeaponGuessPanel();
		weaponPanel.add(weaponGuess);
		
		rooms = new Rooms();
		roomPanel.add(rooms);
		roomGuess = new RoomGuessPanel();
		roomPanel.add(roomGuess);
		
		this.add(peoplePanel);
		this.add(roomPanel);
		this.add(weaponPanel);
	}
	
	public static void main(String[] arg0) {
		DetectiveNotes notes = new DetectiveNotes();
		notes.setVisible(true);
		
	}
	
}
