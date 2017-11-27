package bir;

// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
	public String solution(int T) {
		if (T < 86400) {
			byte hours = (byte) (T / 3600);
			byte minutes = (byte) ((T % 3600) / 60);
			byte seconds = (byte) (T % 60);

			String out = new String();
			out = hours + "h" + minutes + "m" + seconds + "s";
			return out;
		}
		return "T is an integer within the range[0..86,399].";
	}
}