package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class CardPanel extends JFrame {
	private JPanel people, room, weapon, card,frame;
	private JLabel peopleCard, roomCard, weaponCard, myCard;
	
	public CardPanel(){
		setSize(new Dimension(100,800));
		frame  = new JPanel();
		people = new JPanel();
		room = new JPanel();
		weapon = new JPanel();
		card = new JPanel();
		this.setLayout(new GridLayout(4,1));
		peopleCard = new JLabel();
		roomCard = new JLabel();
		weaponCard = new JLabel();
		myCard = new JLabel("My cards");
		card.add(myCard);
		peopleCard.setBorder(new EtchedBorder());
		peopleCard.setText(" text  ");
		roomCard.setBorder(new EtchedBorder());
		roomCard.setText(" text  ");
		weaponCard.setBorder(new EtchedBorder());
		weaponCard.setText(" text  ");
		people.add(peopleCard);
		room.add(roomCard);
		weapon.add(weaponCard);
		people.setBorder(new TitledBorder(new EtchedBorder(), "People"));
		room.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
		weapon.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		this.add(card);
		this.add(people);
		this.add(room);
		this.add(weapon);
	}
	
	
	public static void main(String[] args) {
		CardPanel newPannel = new CardPanel();
		newPannel.setVisible(true);
	}

}
