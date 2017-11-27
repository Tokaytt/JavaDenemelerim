package pisti;

public class Card {

	public final static int SPADES = 0;
	public final static int HEARTS = 1;
	public final static int DIAMONDS = 2;
	public final static int CLUBS = 3;

	public final static int ACE = 1; // Codes for the non-numeric cards.
	public final static int JACK = 11; // Cards 2 through 10 have their
	public final static int QUEEN = 12; // numerical values for their codes.
	public final static int KING = 13;

	private final int suit;
	private final int value;
	private final String face;

	public Card(int theValue, int theSuit) {
		value = theValue;
		suit = theSuit;
		// face = setFace(theSuit, theValue);
		face = getSuitAsString() + "" + getValueAsString();
	}

	public int getSuit() {
		return suit;
	}

	public int getValue() {
		return value;
	}

	public String getSuitAsString() {
		switch (suit) {
		case SPADES:
			return "S_";
		case HEARTS:
			return "H_";
		case DIAMONDS:
			return "D_";
		default:
			return "C_";
		}
	}

	public String getValueAsString() {
		switch (value) {
		case 1:
			return "A";
		case 2:
			return "2";
		case 3:
			return "3";
		case 4:
			return "4";
		case 5:
			return "5";
		case 6:
			return "6";
		case 7:
			return "7";
		case 8:
			return "8";
		case 9:
			return "9";
		case 10:
			return "10";
		case 11:
			return "J";
		case 12:
			return "Q";
		default:
			return "K";
		}
	}

	public String getFace() {
		return face;
	}
}