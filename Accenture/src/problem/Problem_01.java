package problem;

import java.util.Scanner;

public class Problem_01 {

	public static void main(String[] arguments) {
		String yazi = new String();
		int limit;
		int count = 1;
		Scanner scan = new Scanner(System.in);
		Scanner scanlim = new Scanner(System.in);

		// D��ar�dan string alma

		System.out.print("Kontrol edilmesini istedi�iniz stringi giriniz :");
		yazi = scan.nextLine();

		// limit al�m�
		System.out.print("Ka� kez tekrar eden harfler silinsin? :");
		limit = scanlim.nextInt();

		for (int i = 0; i < yazi.length() - 1; i++) {
			count = 1;
			// kalan char bo�luk ise siler
			if (yazi.charAt(0) == ' ') {
				yazi = yazi.replaceFirst(" ", "");
				i = -1;
			} else if (yazi.charAt(i) != ' ') {
				for (int j = i + 1; j < yazi.length() - 1; j++) {
					// o anda index olarak kulland��� alandaki char ileride
					// tekrar ediyorsa i�eri girer
					if (yazi.charAt(j) == yazi.charAt(i)) {
						// tekrar say�s�n� art�r�r
						count++;
						if (count == limit) {
							// e�er limit kadar tekrar ederse o char t�m
							// stringten kald�r�l�r.
							String silinecek = String.valueOf(yazi.charAt(i));
							yazi = yazi.replaceAll(silinecek, "");
							// e�er bu i�lemler ger�ekle�tirilirse string ba�tan
							// kontrol eidlir
							i = -1;
							break;
						}
					}
				}
			}
		}
		for(int i=0; i<yazi.length()-1;i++)
		{
			if(yazi.charAt(i) == yazi.charAt(i+1))
			{
				String bosluk=String.valueOf(" ");
				yazi = yazi.replace(bosluk, "");
			}
		}
		System.out.println(yazi);
	}
}