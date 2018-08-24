import java.util.Scanner;

public class DonusturmeIslemleri {

	public int stringToDigit(String sayiStr) {		//parseInt'in yerine boyle bir metod yazdim
		switch (sayiStr) {
		case "0":
			return 0;
		case "1":
			return 1;
		case "2":
			return 2;
		case "3":
			return 3;
		case "4":
			return 4;
		case "5":
			return 5;
		case "6":
			return 6;
		case "7":
			return 7;
		case "8":
			return 8;
		case "9":
			return 9;
		}
		return -1;
	}
	
	public int charToDigit(char sayiStr) {		//harf alir, sayi verir
		switch (sayiStr) {
		case '0':
			return 0;
		case '1':
			return 1;
		case '2':
			return 2;
		case '3':
			return 3;
		case '4':
			return 4;
		case '5':
			return 5;
		case '6':
			return 6;
		case '7':
			return 7;
		case '8':
			return 8;
		case '9':
			return 9;
		}
		return -1;
	}

	public int stringToInt(String input) {	//Sayiyi String turunde aldik, basamaklarini dongu icerisinde tek tek cektik ve cektigimiz basamagi bulundugu basamaga gore 10 uzeri sekilde carptik ve her bir islem sonucunda buldugumuz degeri toplayarak stringi int e cevirmis olduk.
		String tersSayiStr = "";
		int j = 0, donulecekSayi = 0 ;
		int eksiCarpani = 1;
		if(input.startsWith("-")) {
			eksiCarpani = -1;
			input = input.substring(1);
		}
		tersSayiStr = StringIslemleri.tersCevir(input);
		for(j = 0; j < tersSayiStr.length() ; j++){
			donulecekSayi += charToDigit(tersSayiStr.charAt(j)) * (int)Math.pow(10, j);
		}
		return donulecekSayi * eksiCarpani;
	}
	
	public double stringToDouble(String input) {	//Stringi double a cevirdik. stringToInt metodunda yaptigimiz islemin aynisini double sayinin tam olmayan kisminda da uyguladik ve String turundeki sayiyi double a cevirmis olduk. 
		String tersSayiStr = "";
		String ondalikKisim = "";
		String tamKisim = input;
		int j = 0;
		double donulecekSayi = 0.0 ;
		int eksiCarpani = 1;
		if(input.startsWith("-")) {
			eksiCarpani = -1;
			input = input.substring(1);
			tamKisim = input;
		}
		if(input.indexOf(".") != -1) {		//eger . icermiyorsa bu methodu cagirmak yanlis olur
			ondalikKisim = input.substring(input.indexOf(".")+1);
			tamKisim = input.substring(0, input.indexOf("."));
			tersSayiStr = StringIslemleri.tersCevir(tamKisim);
			for(j = 0; j < tersSayiStr.length() ; j++){
				donulecekSayi += charToDigit(tersSayiStr.charAt(j)) * Math.pow(10, j);
			}
			for(j = 0; j < ondalikKisim.length() ; j++){
				donulecekSayi += charToDigit(ondalikKisim.charAt(j)) * Math.pow(10, -(j+1));
			}
			return donulecekSayi * eksiCarpani;
		}
		else {
			return stringToInt(tamKisim) * eksiCarpani;		//eger ondalik kisim icermiyorsa bu stringTOInt cagriliyor
		}
	}
	
}
