package clueBoard;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

//Naomi and Brandon
public class ComputerPlayer extends Player{
	private boolean lastDisproved;
	
	public ComputerPlayer(String name, java.awt.Point location, java.awt.Color color) {
		super(name, location, color);
		setLastDisproved(true);
	}
	
	public ComputerPlayer() {
		super();
		setLastDisproved(true);
	}
	
	public BoardCell pickLocation(Set<BoardCell> target){
		BoardCell targetCell = null;
		Random roller = new Random();
		ArrayList<BoardCell> doors = new ArrayList<BoardCell>();
		ArrayList<BoardCell> nonDoors = new ArrayList<BoardCell>();
		//pick target, doors have first priority
		for(BoardCell b : target) {
			if(b.isDoorway()) {
				doors.add(b);
			}
			else
				nonDoors.add(b);
		}
		
		while(doors.size() > 0) {
			int index = roller.nextInt(doors.size());
			targetCell = doors.get(index);
			RoomCell cell = (RoomCell) targetCell;
			if(cell.getRoomInitial() != lastVistedRoom) {
				updateLastVisited(cell.getRoomInitial());
				return targetCell;
			}
			else 
				doors.remove(targetCell);
		}
		
		if(doors.size() == 0) {
			if(nonDoors.size() > 1) {
				int index = roller.nextInt(nonDoors.size());
				targetCell = nonDoors.get(index);
				updateCurrent('W');
			}
			else {
				targetCell = nonDoors.get(0);
				updateCurrent('W');
			}
		}
		
		return targetCell;
	}
	
	public Solution createSuggestion(){
		//make arrayLists
		ArrayList<Card> notSeen = new ArrayList<Card>();
		ArrayList<Card> notSeenPeople = new ArrayList<Card>();
		ArrayList<Card> notSeenWeapons = new ArrayList<Card>();
		
		//set notSeen
		for(Card c : ClueGame.getFullDeck()) {
			if(!seen.contains(c) && !cards.contains(c))
				notSeen.add(c);
		}
		
		//sort not seen
		for(Card c : notSeen) {
			if(c.getType() == Card.cardType.PERSON)
				notSeenPeople.add(c);
			else if(c.getType() == Card.cardType.WEAPON)
				notSeenWeapons.add(c);
		}
		
		//make random number generator and get person and weapon
		Random roller = new Random();
		int index = roller.nextInt(notSeenPeople.size());
		String person = notSeenPeople.get(index).getCard();
		index = roller.nextInt(notSeenWeapons.size());
		String weapon = notSeenWeapons.get(index).getCard();
		
		//set Room
		String room;
		switch(this.currentRoom) {
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
		case 's' :
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
		}
		
		Solution guess = new Solution(person, weapon, room);
		return guess;
	}
	
	/*public void updateSeen(Card seen){
		this.seen.add(seen);
	}*/

	public char getLastVistedRoom() {
		return lastVistedRoom;
	}

	public void setLastVistedRoom(char lastVistedRoom) {
		this.lastVistedRoom = lastVistedRoom;
	}

	@Override
	void makeMove(Set<BoardCell> targets, Board board) {
		board.setHumanTurn(false);
		BoardCell location = pickLocation(targets);
		Point playerLocation = new Point(location.getCellColumn(),location.getCellRow());
		setLocation(playerLocation);
		board.repaint();
	}

	public boolean isLastDisproved() {
		return lastDisproved;
	}

	public void setLastDisproved(boolean lastDisproved) {
		this.lastDisproved = lastDisproved;
	}
}
