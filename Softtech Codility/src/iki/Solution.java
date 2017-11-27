package iki;

class Solution {
	public String solution(int A, int B, int C, int D) {
		int num[] = { A, B, C, D };
		int temp = 0;
		int cnt = 0;
		int numA = 0;
		int numB = 0;
		int numC = 0;
		int numD = 0;

		for (int a = 0; a < num.length; a++) {
			for (int b = 0; b < num.length; b++) {
				for (int c = 0; c < num.length; c++) {
					for (int d = 0; d < num.length; d++) {
						if (a != b && a != c && a != d && b != c && b != d && c != d) {
							if ((10 * num[c] + num[d]) < 60) {
								int cal = (10 * num[a] + num[b]) * 60 + (10 * num[c] + num[d]);
								if (cal <= 1439) {
									cnt++;
									if (temp < cal) {
										temp = cal;
										numA = num[a];
										numB = num[b];
										numC = num[c];
										numD = num[d];
									}
								}
							}
						}
					}
				}
			}
		}

		if (cnt == 0) {
			return "NOT POSSIBLE";
		} else {
			return numA + "" + numB + ":" + numC + "" + numD;
		}
	}
}