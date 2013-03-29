package GUI;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class Weapons extends JPanel {
	private JCheckBox candleStick, leadPipe, knife, revolver, wrench, rope;
	private JLabel weapon;
	private JPanel weaponPanel;

	public Weapons(){
		weaponPanel = new JPanel();
		this.add(weaponPanel);
		setSize(300, 250);
		weaponPanel.setLayout(new GridLayout(3,2,30,50));
		this.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		//Check boxes
		candleStick = new JCheckBox("Candlestick");
		weaponPanel.add(candleStick);
		//Check boxes
		leadPipe = new JCheckBox("Lead Pipe");
		weaponPanel.add(leadPipe);
		knife = new JCheckBox("Knife");
		weaponPanel.add(knife);
		revolver = new JCheckBox("Revolver");
		weaponPanel.add(revolver);
		wrench = new JCheckBox("Wrench");
		weaponPanel.add(wrench);
		rope = new JCheckBox("Rope");
		weaponPanel.add(rope);
	}
	public static void main(String[] args) {
		Weapons weaponMain = new Weapons();
		weaponMain.setVisible(true);
	}
}
