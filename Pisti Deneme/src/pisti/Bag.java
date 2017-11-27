package pisti;

import java.util.ArrayList;

public class Bag {

	private ArrayList<Card> bag;
	private int point;
	private int pisti = 0;

	public Bag() {
		bag = new ArrayList<Card>();
		bag.clear();
	}

	public Card getCard(int position) {
		if (position < 0 || position >= bag.size())
			throw new IllegalArgumentException("Position does not exist in hand: " + position);
		return bag.get(position);
	}

	public void addCards(ArrayList<Card> list) {
		bag.addAll(list);
	}

	public int getCardCount() {
		return bag.size();
	}

	public int getPoint() {
		return point;
	}

	public void addPoint(int add) {
		point = point + add;
	}

	public void addPisti(int pistiSayisi) {
		pisti = pisti + pistiSayisi;
	}

	public void checkPoint() {
		point = 0;

		for (int i = 0; i < bag.size(); i++) {
			int value = bag.get(i).getValue();
			int suit = bag.get(i).getSuit();
			if (value == 1 || value == 11)
				addPoint(1);
			else if (suit == 2 && value == 10)
				addPoint(3);
			else if (suit == 3 && value == 2)
				addPoint(2);
		}
		point = point + (pisti * 10);
		if (bag.size() > 26)
			point = point + 3;
	}
}
