package pisti;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;

public class Client {

	private static String al, gonder;
	private static Socket sk;
	private static BufferedReader sin;
	private static PrintStream sout;
	private static BufferedReader stdin;

	public static void main(String arg[]) throws Exception {

		sk = new Socket("127.0.0.1", 1342);
		sin = new BufferedReader(new InputStreamReader(sk.getInputStream()));
		sout = new PrintStream(sk.getOutputStream());
		stdin = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			al = sin.readLine();
			if (al.equals("TURN")) {
				System.out.print("Player2, enter the card that you’d like to play: ");
				gonder = stdin.readLine();
				sout.println(gonder);
			} else if (al.equals("END"))
				break;
			else
				System.out.println(al);
		}
	}
}
