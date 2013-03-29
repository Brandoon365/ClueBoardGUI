package GUI;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class WeaponGuessPanel extends JPanel{
	private JPanel weaponPanel;
	private JComboBox weaponGuess;
	
	private JComboBox createWeaponGuess(){
		weaponGuess = new JComboBox();
		weaponGuess.setPreferredSize(new Dimension(100,175));
		weaponGuess.addItem("Candlestick");
		weaponGuess.addItem("Lead Pipe");
		weaponGuess.addItem("Knife");
		weaponGuess.addItem("Revolver");
		weaponGuess.addItem("Wrench");
		weaponGuess.addItem("Rope");
		weaponGuess.addItem("Not sure");
		return weaponGuess;
	}
	public WeaponGuessPanel(){
		weaponPanel = new JPanel();
		this.add(weaponPanel);
		setSize(300,250);
		this.setBorder(new TitledBorder(new EtchedBorder(), "Weapon Guess"));
		weaponGuess = createWeaponGuess();
		weaponPanel.add(weaponGuess);
	}
	public static void main(String[] args) {
		WeaponGuessPanel weaponGuess = new WeaponGuessPanel();
		weaponGuess.setVisible(true);
	}
}