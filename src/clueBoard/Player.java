package clueBoard;
//Naomi and Brandon
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public abstract class Player {
	private String name;
	public static ArrayList<Card> seen = new ArrayList<Card>();
	protected ArrayList<Card> cards = new ArrayList<Card>();
	private java.awt.Point location;
	private java.awt.Color color;
	protected char lastVistedRoom;
	protected char currentRoom;
	
	public Player() {
		name = null;
		location = null;
		color = null;
	}

	public Player(String name, java.awt.Point location, java.awt.Color color) {
		this.name = name;
		this.location = location;
		this.color = color;
	}
	
	public void updateLastVisited(char initial) {
		currentRoom = initial;
		lastVistedRoom = initial;
	}
	
	public void updateCurrent(char initial) {
		currentRoom = initial;
	}

	public Card disproveSuggestion(Solution suggestion){

		Random rand = new Random();
		ArrayList<Card> hand = new ArrayList<Card>();
		Card returned = null;
		for(Card c : cards)
			hand.add(c);
		while (!hand.isEmpty())
		{
			int j = rand.nextInt(hand.size());
			if(suggestion.getWeapon().equals(hand.get(j).getCard()) || suggestion.getPerson().equals(hand.get(j).getCard()) || suggestion.getRoom().equals(hand.get(j).getCard())) {
				returned = hand.get(j);
				break;
			}
			else
				hand.remove(j);
		}
		return returned;
	}
	
	public void acceptCard(Card card) {
		cards.add(card);
		//seen.add(card);
	}
	
	abstract void makeMove(Set<BoardCell> targets, Board board);

	
	//Setters and getters for tests
	public String getName() {
		return name;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public void setLocation(java.awt.Point location) {
		this.location = location;
	}

	public java.awt.Point getLocation() {
		return location;
	}
	
	public java.awt.Color getColor() {
		return color;
	}
	
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}


	public char getLastVistedRoom() {
		return lastVistedRoom;
	}
	
	public String getCurrentRoom(){
		String room;
		switch(this.currentRoom) {
		case 'W' :
			room = "Walkway";
			break;
		case 'C' :
			room = "Conservatory";
			break;
		case 'K' :
			room = "Kitchen";
			break;
		case 'B' :
			room = "Ballroom";
			break;
		case 'R' :
			room = "Billiard Room";
			break;
		case 'L' :
			room = "Library";
			break;
		case 'S' :
			room = "Study";
			break;
		case 'D' :
			room = "Dining Room";
			break;
		case 'O' :
			room = "Lounge";
			break;
		case 'H' :
			room = "Hall";
			break;
		default :
			room = null;
			break;
		}
		return room;
	}

	public static void updateSeen(Card toAdd) {
		seen.add(toAdd);
	}

	public void setLastVistedRoom(char lastVistedRoom) {
		this.lastVistedRoom = lastVistedRoom;
	}

	public void setCurrentRoom(char currentRoom) {
		this.currentRoom = currentRoom;
	}
	
}
