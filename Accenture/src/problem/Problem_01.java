package problem;

import java.util.Scanner;

public class Problem_01 {

	public static void main(String[] arguments) {
		String yazi = new String();
		int limit;
		int count = 1;
		Scanner scan = new Scanner(System.in);
		Scanner scanlim = new Scanner(System.in);

		// Dýþarýdan string alma

		System.out.print("Kontrol edilmesini istediðiniz stringi giriniz :");
		yazi = scan.nextLine();

		// limit alýmý
		System.out.print("Kaç kez tekrar eden harfler silinsin? :");
		limit = scanlim.nextInt();

		for (int i = 0; i < yazi.length() - 1; i++) {
			count = 1;
			// kalan char boþluk ise siler
			if (yazi.charAt(0) == ' ') {
				yazi = yazi.replaceFirst(" ", "");
				i = -1;
			} else if (yazi.charAt(i) != ' ') {
				for (int j = i + 1; j < yazi.length() - 1; j++) {
					// o anda index olarak kullandýðý alandaki char ileride
					// tekrar ediyorsa içeri girer
					if (yazi.charAt(j) == yazi.charAt(i)) {
						// tekrar sayýsýný artýrýr
						count++;
						if (count == limit) {
							// eðer limit kadar tekrar ederse o char tüm
							// stringten kaldýrýlýr.
							String silinecek = String.valueOf(yazi.charAt(i));
							yazi = yazi.replaceAll(silinecek, "");
							// eðer bu iþlemler gerçekleþtirilirse string baþtan
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