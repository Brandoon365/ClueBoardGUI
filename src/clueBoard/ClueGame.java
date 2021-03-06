package clueBoard;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
//Naomi and Brandon
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import GUI.CardPanel;
import GUI.ControlPanel;
import GUI.DetectiveNotes;
import GUI.GuessPanel;

public class ClueGame extends JFrame{
	public ArrayList<Player> players;
	private ArrayList<ComputerPlayer> computer;
	private Solution answer;
	private ArrayList<Card> cards;
	private static ArrayList<Card> fullDeck;
	private HumanPlayer human;
	private boolean turn;
	private Player currentPlayer;
	public int currentPlayerIndex = 0;
	private String playerFile;
	private String cardFile;
	private Board Board;
	private JMenuBar menu;
	private JMenu menuName;
	private JMenuItem detectiveNotes, exit;
	private DetectiveNotes notesWindow;
	private ControlPanel controlPanel;
	private CardPanel cardPanel;
	public boolean turnDone, gameDone;
	private GuessPanel guess;
	
	public ClueGame() {
		turnDone = true;
		setSize(new Dimension(800,800));
		notesWindow = new DetectiveNotes();
		cardPanel = new CardPanel();
		controlPanel = new ControlPanel(this);	
		controlPanel.setSize(new Dimension(this.getWidth(), 100));
		controlPanel.setPreferredSize(new Dimension(this.getWidth(), 100));

		
		computer = new ArrayList<ComputerPlayer>();
		cards = new ArrayList<Card>();
		players = new ArrayList<Player>();
		fullDeck = new ArrayList<Card>();
		human = new HumanPlayer();
		
		setPlayerFile("Players.txt");
		setCardFile("Cards.txt");
		loadConfigFiles();
		currentPlayer = human;
		players.add(human);
		for(ComputerPlayer c : computer)
			players.add(c);
		deal();
		cardPanel.setRoomName(human.getCards().get(0).getCard());
		cardPanel.setWeaponName(human.getCards().get(1).getCard());
		cardPanel.setPersonName(human.getCards().get(2).getCard());
		
		//GUI 
		Board = new Board(this);
		Board.setPlayers(players);
		this.add(Board, BorderLayout.CENTER);
		this.add(controlPanel, BorderLayout.SOUTH);
		this.controlPanel.setCurrentPlayer(players.get(currentPlayerIndex).getName());
		this.add(cardPanel, BorderLayout.EAST);
		setTitle("Clue");
		menu = new JMenuBar();
		
		menuName = new JMenu("File");
		detectiveNotes = new JMenuItem("Show Detective notes");
		exit = new JMenuItem("Exit");
		detectiveNotes.addActionListener(new detectiveListener());
		exit.addActionListener(new exitListener());
		menuName.add(detectiveNotes);
		menuName.add(exit);
		menu.add(menuName);
		this.setJMenuBar(menu);
		JOptionPane.showMessageDialog(this, "You are " + human.getName() + ". Press OK to continue.");
		
	}
	private class exitListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	private class detectiveListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			notesWindow.setVisible(true);
		}
	}
	
	public void showWinScreen() {
		if(currentPlayer == human)
			JOptionPane.showMessageDialog(this, "Congratulations, you win!");
		else
			JOptionPane.showMessageDialog(this, "Sorry, " + currentPlayer.getName() + " won the game.");
	}
	
	public void showIncorrectScreen() {
		JOptionPane.showMessageDialog(this, "Sorry, that is incorrect.");
	}
	
	
	public void takeTurn(int roll) {
		//calculate targets
		Board.calcTargets(currentPlayer.getLocation().y, currentPlayer.getLocation().x, roll);
		//take human turn
		if(currentPlayer == human) {
			this.setTurnDone(false);
			Board.setHumanTurn(true);
			Board.repaint();			
		}
		//take computer turn
		else {
			setTurnDone(true);
			currentPlayer.makeMove(Board.getTargets(), Board);
			//Code to make computer make suggestion if in a room
			if(!currentPlayer.getCurrentRoom().equals("Walkway") && ((ComputerPlayer) currentPlayer).isLastDisproved()) {
				Solution guess = ((ComputerPlayer) currentPlayer).createSuggestion();
				Card returned = handleSuggestion(guess);
				if(returned == null)
					((ComputerPlayer) currentPlayer).setLastDisproved(false);
				controlPanel.setGuess(guess);
				controlPanel.setRevealed(returned);	
			}
			else if(!currentPlayer.getCurrentRoom().equals("Walkway")) {
				Solution guess = ((ComputerPlayer) currentPlayer).createSuggestion();
				JOptionPane.showMessageDialog(this, currentPlayer.getName() + " has made an accusation of " + guess.getPerson() + " in the " + guess.getRoom() + " with the " + guess.getWeapon());
				if(checkAccusation(guess)) {
					showWinScreen();
					setGameDone(true);
				}
			}
		
		
		}
		//Board.setHumanTurn(false);
	}
	
	public void deal(){
		selectAnswer();
		ArrayList<Card> rooms = new ArrayList<Card>();
		ArrayList<Card> weapons = new ArrayList<Card>();
		ArrayList<Card> people = new ArrayList<Card>();
		for(Card c : cards) {
			if(c.getType() == Card.cardType.ROOM) 
				rooms.add(c);
			else if(c.getType() == Card.cardType.WEAPON) 
				weapons.add(c);
			else
				people.add(c);
		}
		Random roller = new Random();
		
		//Deal one of each card type to player
		int cardIndex = roller.nextInt(rooms.size());
		human.acceptCard(rooms.get(cardIndex));
		cards.remove(rooms.get(cardIndex));
		cardIndex = roller.nextInt(weapons.size());
		human.acceptCard(weapons.get(cardIndex));
		cards.remove(weapons.get(cardIndex));
		cardIndex = roller.nextInt(people.size());
		human.acceptCard(people.get(cardIndex));
		cards.remove(people.get(cardIndex));
		
		//deal remaining to computers
		int dealt = 0;
		while(!cards.isEmpty()) {
			int index = dealt % 5;
			Player dealtTo = null;
			Card toBeDealt;
			switch(index) {
			case 0:
				dealtTo = computer.get(0);
				break;
			case 1:
				dealtTo = computer.get(1);
				break;
			case 2:
				dealtTo = computer.get(2);
				break;
			case 3:
				dealtTo = computer.get(3);
				break;
			case 4:
				dealtTo = computer.get(4);
				break;
			}
			if(cards.size() > 1) {
				cardIndex = roller.nextInt(cards.size());
				toBeDealt = cards.get(cardIndex);
				dealtTo.acceptCard(toBeDealt);
				cards.remove(cardIndex);
				dealt++;
			}
			else {
				toBeDealt = cards.get(0);
				dealtTo.acceptCard(toBeDealt);
				cards.remove(0);
				dealt++;
			}
		}
	}
	
	public void loadConfigFiles(){
		try {
			loadPlayerConfigFile(playerFile);
			loadCardConfigFile(cardFile);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (BadConfigFormatException e) {
			System.out.println(e);
		}
		
		
	}
	
	public void selectAnswer(){
		String person;
		String room;
		String weapon;
		ArrayList<Card> people = new ArrayList<Card>();
		ArrayList<Card> rooms = new ArrayList<Card>();
		ArrayList<Card> weapons = new ArrayList<Card>();
		for(Card c : cards) {
			if(c.getType() == Card.cardType.PERSON)
				people.add(c);
			else if(c.getType() == Card.cardType.ROOM)
				rooms.add(c);
			else
				weapons.add(c);
		}
		Card selected = null;
		Random roller = new Random();
		
		int index = roller.nextInt(people.size());
		selected = people.get(index);
		person = selected.getCard();
		cards.remove(selected);
		
		index = roller.nextInt(rooms.size());
		selected = rooms.get(index);
		room = selected.getCard();
		cards.remove(selected);
		
		index = roller.nextInt(weapons.size());
		selected = weapons.get(index);
		weapon = selected.getCard();
		cards.remove(selected);
		
		answer = new Solution(person,weapon,room);
	}
	
	public Card handleSuggestion(Solution suggestion){
		ArrayList<Card> clues = new ArrayList<Card>();
		Random roller = new Random();
		
		for(Player player : players) {
			if(player.getName().equals(suggestion.getPerson()))
				player.setLocation(currentPlayer.getLocation());
			if(players.get(currentPlayerIndex) == player) {
			}
			else {
				if(player.disproveSuggestion(suggestion) != null) {
					clues.add(player.disproveSuggestion(suggestion));
				}
			}
		}
		
		if(clues.size() == 0)
			return null;
		else {
			int cardIndex = roller.nextInt(clues.size());
			ComputerPlayer.updateSeen(clues.get(cardIndex));
			return clues.get(cardIndex);
		}
	}
		
	
	public boolean checkAccusation(Solution solution){
		boolean correct = false;
		if(solution.getPerson().equals(answer.getPerson()) && solution.getRoom().equals(answer.getRoom()) && solution.getWeapon().equals(answer.getWeapon())) {
			correct = true;
		}
		return correct;
	}

	
	//load separate config files to test for exceptions
	public void loadPlayerConfigFile(String file) throws FileNotFoundException, BadConfigFormatException {
		FileReader playerReader = new FileReader(file);
		Scanner playerScanner = new Scanner(playerReader);
		HumanPlayer human;
		ComputerPlayer currentComputer;
		final int numColumns = 4;
		int row = 0, column = 0;
		int numRows = 0;
		Point location;
		String name;
		java.awt.Color color;
		
		while(playerScanner.hasNextLine()) {
			String inputLine = playerScanner.nextLine();
			String[] parts = inputLine.split(",");
			
			//if first line, create human player
			if(numRows == 0) {
				//check for all data
				if(parts.length != numColumns) 
					throw new BadConfigFormatException("Too few columns in player legend file");
				else {
					name = parts[0];
					row = Integer.parseInt(parts[1]);
					column = Integer.parseInt(parts[2]);
					location = new Point(row,column);
					color = convertColor(parts[3]);
					human = new HumanPlayer(name, location, color);
					this.human = human;
				}
				
			}
			//otherwise make computer player
			else {
				//check for all data
				if(parts.length != numColumns) 
					throw new BadConfigFormatException("Too few columns in player legend file");
				else {
					name = parts[0];
					row = Integer.parseInt(parts[1]);
					column = Integer.parseInt(parts[2]);
					location = new Point(row,column);
					color = convertColor(parts[3]);
					currentComputer = new ComputerPlayer(name, location, color);
					this.computer.add(currentComputer);
				}
			}
			numRows++;
		}
		playerScanner.close();
	}
	
    // Be sure to trim the color, we don't want spaces around the name
	public Color convertColor(String strColor) {
		Color color; 
		try {     
			// We can use reflection to convert the string to a color
			Field field = Class.forName("java.awt.Color").getField(strColor.trim());     
			color = (Color)field.get(null); } 
		catch (Exception e) {  
			color = null; // Not defined } 
		}
		return color;
	}
	
	public void loadCardConfigFile(String file) throws FileNotFoundException, BadConfigFormatException {
		FileReader cardReader = new FileReader(file);
		Scanner cardScanner = new Scanner(cardReader);
		Card toAdd;
		final int numColumns = 2;
		String name;
		Card.cardType type = null;
		while(cardScanner.hasNextLine()) {
			String line = cardScanner.nextLine();
			String[] parts = line.split(",");
			if(parts.length != numColumns) {
				throw new BadConfigFormatException("Error in card file");
			}
			else {
				name = parts[0];
				if(parts[1].equals("WEAPON")) 
					type = Card.cardType.WEAPON;
				else if (parts[1].equals("PERSON"))
					type = Card.cardType.PERSON;
				else if (parts[1].equals("ROOM")) 
					type = Card.cardType.ROOM;
				else
					throw new BadConfigFormatException("invalid card type");
				toAdd = new Card(name, type);
				this.cards.add(toAdd);
				this.fullDeck.add(toAdd);
				
			}
		}
	}
	
	public static int roll(){
		Random rand = new Random();
		return (rand.nextInt(6)+1);
		
	}

	public void nextPlayer() {
		if(currentPlayerIndex == 5)
			currentPlayerIndex = 0;
		else
			currentPlayerIndex++;
	}
	
	public static void main(String[] args) {
		ClueGame mainPannel = new ClueGame();
		mainPannel.setVisible(true);
		int roll = mainPannel.roll();
		mainPannel.controlPanel.setRoll(roll);
		mainPannel.takeTurn(roll);
	}
	
	
	//Getters and Setters for tests
	public Solution getAnswer() {
		return answer;
	}

	public void setAnswer(Solution answer) {
		this.answer = answer;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public static ArrayList<Card> getFullDeck() {
		return fullDeck;
	}

	public void setComputer(ArrayList<ComputerPlayer> computer) {
		this.computer = computer;
	}

	public ArrayList<ComputerPlayer> getComputer() {
		return computer;
	}

	public HumanPlayer getHuman() {
		return human;
	}
	
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public String getPlayerFile() {
		return playerFile;
	}

	public void setPlayerFile(String playerFile) {
		this.playerFile = playerFile;
	}

	public String getCardFile() {
		return cardFile;
	}

	public void setCardFile(String cardFile) {
		this.cardFile = cardFile;
	}
	
	public int getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}

	public void setCurrentPlayerIndex(int currentPlayerIndex) {
		this.currentPlayerIndex = currentPlayerIndex;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public boolean isTurnDone() {
		return turnDone;
	}

	public void setTurnDone(boolean turnDone) {
		this.turnDone = turnDone;
	}

	public boolean isGameDone() {
		return gameDone;
	}

	public void setGameDone(boolean gameDone) {
		this.gameDone = gameDone;
	}


	public Board getBoard() {
		return Board;
	}


	public void setBoard(Board board) {
		Board = board;
	}
	
	public ControlPanel getControlPanel() {
		return this.controlPanel;
	}

	public GuessPanel getGuess() {
		return this.guess;
	}
	
	public void setGuess(GuessPanel guess) {
		this.guess = guess;
	}
}
