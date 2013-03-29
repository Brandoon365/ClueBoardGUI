package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PeoplePanel extends JPanel {
	private JPanel people;
	private JCheckBox scarletBox, mustardBox, greenBox, whiteBox, peacockBox, plumBox;
	
	public PeoplePanel() {
		
		
		people = new JPanel();
		people.setLayout(new GridLayout(3,2));
		people.setSize(new Dimension(300,250));
		setSize(new Dimension(300, 250));
		this.setBorder(new TitledBorder(new EtchedBorder(), "People"));
		this.add(people, BorderLayout.SOUTH);
		
		
		scarletBox = new JCheckBox("Miss Scarlet");
		mustardBox = new JCheckBox("Colonel Mustard");
		greenBox = new JCheckBox("Mr. Green");
		whiteBox = new JCheckBox("Mrs. White");
		peacockBox = new JCheckBox("Mrs. Peacock");
		plumBox = new JCheckBox("Professor Plum");
		
		people.add(scarletBox);
		people.add(mustardBox);
		people.add(greenBox);
		people.add(whiteBox);
		people.add(peacockBox);
		people.add(plumBox);
	}
	
	public static void main(String[] arg0) {
		PeoplePanel people = new PeoplePanel();
		people.setVisible(true);
	}
	

}
