package pisti;

import java.util.ArrayList;

public class Board {

	private ArrayList<Card> board;
	private int point;

	public Board() {
		board = new ArrayList<Card>();
	}

	public void clear() {
		board.clear();
		setPoint(0);
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public void addCard(Card c) {
		if (c == null)
			throw new NullPointerException("Can't add a null card to a hand.");
		board.add(c);
	}

	public int getCardCount() {
		return board.size();
	}

	public Card getCard(int position) {
		return board.get(position);
	}

	public ArrayList<Card> getBoard() {
		return board;
	}

	public int getPoint() {
		return point;
	}

	public void addPoint(int add) {
		point = point + add;
	}

	public void checkPoint() {

		for (int i = 0; i < board.size(); i++) {
			int value = board.get(i).getValue();
			int suit = board.get(i).getSuit();
			if (value == 1 || value == 11)
				addPoint(1);
			else if (suit == 2 && value == 10)
				addPoint(3);
			else if (suit == 3 && value == 2)
				addPoint(2);
		}
	}
}