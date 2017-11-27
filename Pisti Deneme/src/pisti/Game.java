package pisti;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Game {

	static Random rand = new Random();
	private static Socket sk;
	private static int move;
	private static int round;
	private static int pistiCount;
	private static int bot;
	private static int handLook;
	private static int cardNo;
	private static int lastPick = 0;
	private static String temporary;
	private static String counterMove;
	private static ServerSocket ss;
	static Deck deck = new Deck();

	static Hand hand_1 = new Hand();
	static Hand hand_2 = new Hand();
	static Board board = new Board();
	static Bag bag_1 = new Bag();
	static Bag bag_2 = new Bag();

	private static Scanner scanner;
	private static BufferedReader cin;
	private static PrintStream cout;
	private static BufferedReader stdin;

	public static void main(String[] args) throws Exception {

		deck.shuffle();
		round = 0;
		move = 1;

		int durum = -1;

		// Oyuncu Tercihi
		System.out.println("User-User ---> 0");
		System.out.println("User-PC -----> 1");
		System.out.println("PC-PC -------> 2");
		System.out.print("Your Choice?...: ");

		scanner = new Scanner(System.in);
		bot = scanner.nextInt();
		if (bot == 0) {

			System.out.println("Waiting for client...");

			scanner = new Scanner(System.in);
			ss = new ServerSocket(1342);
			sk = ss.accept();
			System.out.println("Client session closed!");
			cin = new BufferedReader(new InputStreamReader(sk.getInputStream()));
			cout = new PrintStream(sk.getOutputStream());
			stdin = new BufferedReader(new InputStreamReader(System.in));

		}

		if (bot == 1)
		// Bot görünürlüðü
		{
			System.out.println();
			System.out.println("Do you want to see Bot's hand?");
			System.out.println("Yeah! -_-  -------> 1");
			System.out.println("No! Thanks!   ----> 2");
			System.out.print("Your Choice?...: ");

			handLook = scanner.nextInt();
		}

		for (int i = 0; i < 4; i++) {
			board.addCard(deck.dealCard());
		}
		while (deck.cardsLeft() > 0 || hand_1.getCardCount() > 0 || hand_2.getCardCount() > 0) {
			durum = -1;
			round++;
			move = 1;
			cardCheck();
			statusWrite();

			if (bot == 0 || bot == 1) {
				while (durum < 0) {
					durum = player1CardDrop();
				}
				durum = -1;
			} else if (bot == 2) {
				bot1CardDrop();
			}
			checkBoard();

			move = 2;
			statusWrite();

			if (bot == 0) {
				cardNo = -1;
				while (cardNo < 0) {
					System.out.println("Waiting for Player_2");
					cout.println("TURN");
					counterMove = cin.readLine();
					for (int i = 0; i < hand_2.getCardCount(); i++) {
						temporary = hand_2.getCard(i).getFace();
						if (counterMove.equals(temporary)) {
							cardNo = i;
							break;
						}
					}
					if (cardNo > -1) {
						board.addCard(hand_2.getCard(cardNo));
						hand_2.removeCard(cardNo);
					}
				}
				durum = -1;
			} else if (bot == 1 || bot == 2) {
				bot2CardDrop();
			}

			checkBoard();
		}
		if (board.getCardCount() > 0)
			lastCheck();

		lastStatusWrite();

		if (bot == 0) {
			cout.println("END");
			ss.close();
			sk.close();
			cin.close();
			cout.close();
			stdin.close();
		}
	}

	private static void bot1CardDrop() {
		int cardNo = -1;
		int handValue;
		int boardValue;
		if (board.getCardCount() > 1)
			boardValue = board.getCard(board.getCardCount() - 1).getValue();
		else
			boardValue = 0;
		for (int i = 0; i < hand_1.getCardCount(); i++) {
			handValue = hand_1.getCard(i).getValue();
			if (11 == handValue) {
				if (board.getPoint() > 0 || board.getCardCount() > 4) {
					cardNo = i;
					break;
				}
			}
		}
		for (int i = 0; i < hand_1.getCardCount(); i++) {
			handValue = hand_1.getCard(i).getValue();
			if (boardValue == handValue) {
				cardNo = i;
				break;
			}
		}

		if (cardNo <= -1) {

			cardNo = rand.nextInt(hand_1.getCardCount());
		}
		board.addCard(hand_1.getCard(cardNo));
		hand_1.removeCard(cardNo);
	}

	private static void bot2CardDrop() {
		int cardNo = -1;
		int handValue;
		int boardValue;
		if (board.getCardCount() > 1)
			boardValue = board.getCard(board.getCardCount() - 1).getValue();
		else
			boardValue = 0;
		for (int i = 0; i < hand_2.getCardCount(); i++) {
			handValue = hand_2.getCard(i).getValue();
			if (11 == handValue) {
				if (board.getPoint() > 0 || board.getCardCount() > 4) {
					cardNo = i;
					break;
				}
			}
		}
		for (int i = 0; i < hand_2.getCardCount(); i++) {
			handValue = hand_2.getCard(i).getValue();
			if (boardValue == handValue) {
				cardNo = i;
				break;
			}
		}

		if (cardNo <= -1) {

			cardNo = rand.nextInt(hand_2.getCardCount());
		}
		board.addCard(hand_2.getCard(cardNo));
		hand_2.removeCard(cardNo);
	}

	private static void lastStatusWrite() throws Exception {
		if (bot == 0)
			cout.println();
		System.out.println();

		if (bot == 0)
			cout.println("***************");
		System.out.println("***************");
		if (bot == 0)
			cout.println("*Game Finished*");
		System.out.println("*Game Finished*");

		if (lastPick == 1) {
			if (bot == 0)
				cout.println("Assign_Pile_Left_Card = Player_1");
			System.out.println("Assign_Pile_Left_Card = Player_1");
		} else if (lastPick == 2) {
			if (bot == 0)
				cout.println("Assign_Pile_Left_Card = Player_2");
			System.out.println("Assign_Pile_Left_Card = Player_2");
		}

		if (bot == 0)
			cout.print("Player_1_Bag : ");
		System.out.print("Player_1_Bag : ");
		for (int i = bag_1.getCardCount() - 1; i > -1; i--) {
			if (bot == 0)
				cout.print(bag_1.getCard(i).getFace());
			System.out.print(bag_1.getCard(i).getFace());
			if (i > 0) {
				if (bot == 0)
					cout.print(" | ");
				System.out.print(" | ");
			}
		}
		if (bot == 0)
			cout.println();
		System.out.println();

		if (bot == 0)
			cout.print("Player_2_Bag : ");
		System.out.print("Player_2_Bag : ");
		for (int i = bag_2.getCardCount() - 1; i > -1; i--) {
			if (bot == 0)
				cout.print(bag_2.getCard(i).getFace());
			System.out.print(bag_2.getCard(i).getFace());
			if (i > 0) {
				if (bot == 0)
					cout.print(" | ");
				System.out.print(" | ");
			}
		}
		if (bot == 0)
			cout.println();
		System.out.println();

		if (bot == 0)
			cout.print("Player_1_Point = ");
		System.out.print("Player_1_Point = ");
		if (bot == 0)
			cout.println(bag_1.getPoint());
		System.out.println(bag_1.getPoint());

		if (bot == 0)
			cout.print("Player_2_Point = ");
		System.out.print("Player_2_Point = ");
		if (bot == 0)
			cout.println(bag_2.getPoint());
		System.out.println(bag_2.getPoint());
		if (bag_1.getPoint() > bag_2.getPoint()) {
			if (bot == 0)
				cout.println("Player_1 Won!");
			System.out.println("Player_1 Won!");
		} else {
			if (bot == 0)
				cout.println("Player_2 Won!");
			System.out.println("Player_2 Won!");
		}
	}

	private static void lastCheck() {
		if (board.getCardCount() % 2 == 0) {
			lastPick = 1;
			bag_1.addCards(board.getBoard());
			bag_1.checkPoint();
			board.clear();
		} else {
			lastPick = 2;
			bag_2.addCards(board.getBoard());
			bag_2.checkPoint();
			board.clear();
		}
	}

	private static int player1CardDrop() {
		scanner = new Scanner(System.in);
		System.out.print("Player1, enter the card that you’d like to play: ");
		String oku = scanner.nextLine();
		String temporary;

		int cardNo = -1;
		for (int i = 0; i < hand_1.getCardCount(); i++) {
			temporary = hand_1.getCard(i).getFace();
			if (oku.equals(temporary)) {
				cardNo = i;
				break;
			}
		}
		if (cardNo > -1) {
			board.addCard(hand_1.getCard(cardNo));
			hand_1.removeCard(cardNo);
			return cardNo;
		} else
			return cardNo;
	}

	private static void cardCheck() {
		if ((hand_1.getCardCount() == 0) && (hand_2.getCardCount() == 0)) {
			for (int i = 0; i < 4; i++)
				hand_1.addCard(deck.dealCard());
			for (int i = 0; i < 4; i++)
				hand_2.addCard(deck.dealCard());
		}
	}

	private static void statusWrite() throws Exception {
		if (bot == 0)
			cout.println();
		System.out.println();
		if (bot == 0)
			cout.println("Round " + round + "_Move " + move);
		System.out.println("Round " + round + "_Move " + move);
		if (bot == 0)
			cout.print("Pile_Cards = ");
		System.out.print("Pile_Cards = ");
		for (int i = board.getCardCount() - 1; i > -1; i--) {
			if (bot == 0)
				cout.print(board.getCard(i).getFace());
			System.out.print(board.getCard(i).getFace());
			if (i > 0) {
				System.out.print(" | ");
				if (bot == 0)
					cout.print(" | ");
			}
		}

		System.out.println();
		System.out.print("Player_1_Hand = ");
		for (int i = 0; i < hand_1.getCardCount(); i++) {

			System.out.print(hand_1.getCard(i).getFace());
			if (i < 3) {
				System.out.print(" | ");
			}
		}
		if (bot == 0)
			cout.println();
		System.out.println();
		if (handLook == 1) {
			if (bot == 0)
				cout.print("Player_2_Hand = ");
			System.out.print("Player_2_Hand = ");
			for (int i = 0; i < hand_2.getCardCount(); i++) {
				if (bot == 0)
					cout.print(hand_2.getCard(i).getFace());
				System.out.print(hand_2.getCard(i).getFace());
				if (i < 3) {
					System.out.print(" | ");
					if (bot == 0)
						cout.print(" | ");
				}
			}
			if (bot == 0)
				cout.println();
			System.out.println();
		} else if (handLook == 2) {
			System.out.println("Player_2_Hand = **Hand is hidden!**");
		} else if (bot == 0) {
			cout.print("Player_2_Hand = ");
			for (int i = 0; i < hand_2.getCardCount(); i++) {
				cout.print(hand_2.getCard(i).getFace());
				if (i < 3) {
					cout.print(" | ");
				}
			}
			cout.println();
		}

		if (bot == 0)
			cout.print("Player_1_Point = ");
		System.out.print("Player_1_Point = ");

		if (bot == 0)
			cout.println(bag_1.getPoint());
		System.out.println(bag_1.getPoint());

		if (bot == 0)
			cout.print("Player_2_Point = ");
		System.out.print("Player_2_Point = ");
		if (bot == 0)
			cout.println(bag_2.getPoint());
		System.out.println(bag_2.getPoint());
	}

	private static void checkBoard() {
		boolean isPick = false;
		pistiCount = 0;

		if (board.getCardCount() > 1) {
			Card last1 = board.getCard(board.getCardCount() - 1);
			Card last2 = board.getCard(board.getCardCount() - 2);
			if (last1.getValue() == last2.getValue()) {
				isPick = true;
				if (board.getCardCount() == 2) {
					pistiCount = 1;
					if (last1.getValue() == 11)
						pistiCount = 2;
				}
			}
			if (last1.getValue() == 11)
				isPick = true;
			if (isPick == true) {
				if (hand_1.getCardCount() < hand_2.getCardCount()) {
					bag_1.addCards(board.getBoard());
					bag_1.addPisti(pistiCount);
					bag_1.checkPoint();
					board.clear();
				} else {
					bag_2.addCards(board.getBoard());
					bag_2.addPisti(pistiCount);
					bag_2.checkPoint();
					board.clear();
				}
			}
		}
	}
}