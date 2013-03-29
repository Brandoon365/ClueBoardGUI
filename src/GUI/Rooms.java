package GUI;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class Rooms extends JPanel{
	private JCheckBox kitchen, lounge, conservatory, study, billardRoom, diningRoom, ballroom, hall, library;
	private JLabel roomLabel;
	private JPanel roomPanel;
	
	public Rooms(){
		roomPanel = new JPanel();
		this.add(roomPanel);
		setSize(300,250);
		roomPanel.setLayout(new GridLayout(5,2,40,20));
		this.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
		//Check Boxes
		kitchen = new JCheckBox("Kitchen");
		roomPanel.add(kitchen);
		lounge = new JCheckBox("Lounge");
		roomPanel.add(lounge);
		conservatory = new JCheckBox("Conservatory");
		roomPanel.add(conservatory);
		study = new JCheckBox("Study");
		roomPanel.add(study);
		billardRoom = new JCheckBox("Billard Room");
		roomPanel.add(billardRoom);
		diningRoom = new JCheckBox("Dining Room");
		roomPanel.add(diningRoom);
		ballroom = new JCheckBox("Ballroom");
		roomPanel.add(ballroom);
		hall = new JCheckBox("Hall");
		roomPanel.add(hall);
		library = new JCheckBox("Library");
		roomPanel.add(library);	
	}
	public static void main(String[] args) {
		Rooms roomMain = new Rooms();
		roomMain.setVisible(true);
	}
}
