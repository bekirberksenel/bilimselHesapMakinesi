import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		StringIslemleri myString = new StringIslemleri();
		String formula;
//		formula = "3*3-2/1+4%14/3.5+4.0 ^3.0	-1+tan45";
//		formula = "0.5*6.0";
//		3-5*7/3.5
//		sin30 * 10
//		2 + 6%	1*5
		formula = "4*2+5-2+sin-45";
//		formula = "sin30";
		Scanner k = new Scanner(System.in);
		System.out.print("Lutfen bir formul girin: ");
		formula = k.nextLine();
		
		while (!formula.equals("0")) { //islem 0 girilene kadar yeniden istenecek, hata kontrolu sirasinda yanlis bir girdi girilirse hata mesaji verilip islem yeniden istenecek.
			formula = myString.boslukTemizle(formula); //bosluklari temizliyoruz. 
			formula = trigonometriIsle(formula); //trigonometrik bir islem varsa oncelikle bu islemleri yapiyoruz.
			formula = myString.myReplaceAll(formula, "+-", "-");	//bu hata olamaz cunku isleyis esnasinda da bu durum olusabiliyor 2+10+5-20, 2+-5 oluyor
			if(!formula.contains("-+")&&!formula.contains("**")&&!formula.contains("*/")&&!formula.contains("*^")&&!formula.contains("*%")&&!formula.contains("/*")&&!formula.contains("//")&&!formula.contains("/^")&&!formula.contains("/%")&&!formula.contains("^*")&&!formula.contains("^/")&&!formula.contains("^^")&&!formula.contains("^%")&&!formula.contains("%*")&&!formula.contains("%/")&&!formula.contains("%^")&&!formula.contains("%%")&&!formula.contains("q")&&!formula.contains("w")&&!formula.contains("e")&&!formula.contains("r")&&!formula.contains("t")&&!formula.contains("y")&&!formula.contains("u")&&!formula.contains("o")&&!formula.contains("p")&&!formula.contains("a")&&!formula.contains("s")&&!formula.contains("d")&&!formula.contains("f")&&!formula.contains("g")&&!formula.contains("h")&&!formula.contains("j")&&!formula.contains("k")&&!formula.contains("l")&&!formula.contains("i")&&!formula.contains("z")&&!formula.contains("x")&&!formula.contains("c")&&!formula.contains("v")&&!formula.contains("b")&&!formula.contains("n")&&!formula.contains("m")&&!formula.contains("Q")&&!formula.contains("W")&&!formula.contains("E")&&!formula.contains("R")&&!formula.contains("T")&&!formula.contains("Y")&&!formula.contains("U")&&!formula.contains("I")&&!formula.contains("O")&&!formula.contains("P")&&!formula.contains("A")&&!formula.contains("S")&&!formula.contains("D")&&!formula.contains("F")&&!formula.contains("G")&&!formula.contains("H")&&!formula.contains("J")&&!formula.contains("K")&&!formula.contains("L")&&!formula.contains("Z")&&!formula.contains("X")&&!formula.contains("C")&&!formula.contains("V")&&!formula.contains("B")&&!formula.contains("N")&&!formula.contains("M")) {
				
				formula = doublizeFormula(formula);
				
				formula = belliBirIslemiIsle(formula, "^");
		
				formula = belliBirIslemiIsle(formula, "/");
				
				formula = belliBirIslemiIsle(formula, "*");
		
				formula = belliBirIslemiIsle(formula, "-");
				
				formula = belliBirIslemiIsle(formula, "+");
				
				formula = belliBirIslemiIsle(formula, "%");
				System.out.println("Sonuc: " + formula);
			}
			
			else {
				System.out.println("Hatali formul girdiniz!");
			}
			System.out.print("Lutfen bir formul girin: ");
			formula = k.nextLine();
		}
		k.close();
	}

	private static String trigonometriIsle(String formula) {
		StringIslemleri myString = new StringIslemleri();
		MatematikselIslemler myMath = new MatematikselIslemler();
		int index = 0;
		double deger=0;
		String islem = "";
		String tumIslem = "", degerStr = "";
		while((index = trigonometrikIsaretAl(formula)) != -1){
			islem = formula.substring(index, index+3);
			deger = yanindakiSayiyiBul(formula, index+2, true);				//int olarak sin30 -> 30
			degerStr = yanindakiSayiyiBulString(formula, index+2, true);	//String olarak sin-30 -> "-30"
//			System.out.println(yanindakiSayiyiBul(formula, index+2, true));
			tumIslem = islem + degerStr;
			formula = myString.myReplaceAll(formula, tumIslem, "" + myMath.trigonometrikIslem(deger, islem));
		}
		return formula;
	}

	private static int trigonometrikIsaretAl(String formula) {
		if(formula.indexOf("sin") != -1) { 
			return formula.indexOf("sin");
		}
		else if(formula.indexOf("cos") != -1) {
			return formula.indexOf("cos");
		}
		else if(formula.indexOf("tan") != -1) {
			return formula.indexOf("tan");
		}
		else if(formula.indexOf("cot") != -1) {
			return formula.indexOf("cot");
		}
		return -1;
	}

	private static String doublizeFormula(String formula) {		//formulu alip her sayiya double'mis gibi ondalik kismi ekler
		StringIslemleri myString = new StringIslemleri();
		MatematikselIslemler mat = new MatematikselIslemler();
		int i=0, lastOperator = 0;
		String doublizedFormula = "";
		for(i = 0; i< formula.length(); i++) {
			if(myString.isOperator(formula.charAt(i))) {
				doublizedFormula += mat.trasEt(yanindakiSayiyiBul(formula, i, false));		//sayinin double halini virgulden sonra 2 basamakli olarak ayarla ve ekle
				doublizedFormula += formula.charAt(i);		//operatoru ekle
				lastOperator = i;
			}
		}
		doublizedFormula += mat.trasEt(yanindakiSayiyiBul(formula, lastOperator, true));		//bulunan son islemin sagindaki kisim
		return doublizedFormula;
	}

	private static String belliBirIslemiIsle(String formula, String islemStr) {
		StringIslemleri myString = new StringIslemleri();
		MatematikselIslemler myMath = new MatematikselIslemler();
		double sonuc = 0;
		while (formula.indexOf(islemStr) != -1) {
			sonuc = myMath.islemiSonuclandir(yanindakiSayiyiBul(formula, formula.indexOf(islemStr), false), islemStr,
					yanindakiSayiyiBul(formula, formula.indexOf(islemStr), true));
			
			formula = myString.myReplaceAll(formula, yanindakiSayiyiBulString(formula, formula.indexOf(islemStr), false)
					+ islemStr + yanindakiSayiyiBulString(formula, formula.indexOf(islemStr), true), "" + sonuc);
			formula = myString.myReplaceAll(formula, "+-", "-");
			if (islemBittiMi(formula)) {
				return formula;
			}
		}
		return formula;
	}
	
	private static boolean islemBittiMi(String formula) {
		if(formula.startsWith("-")) {
			return true;
		}
		return false;
	}

	private static double yanindakiSayiyiBul(String formula, int indeks, boolean isRight){ //buldugumuz sayiyi double a ceviriyoruz.
		StringIslemleri myString = new StringIslemleri();
		DonusturmeIslemleri donusturme = new DonusturmeIslemleri();
		int i = 0;
		if(isRight) {
			String sag = formula.substring(indeks+1);
			for(i = 1; i< sag.length(); i++) {
				if(myString.isOperator(sag.charAt(i))) {
					return donusturme.stringToDouble(sag.substring(0, i));
				}
			}
			return donusturme.stringToDouble(sag);
		}
		else {
			String sol = formula.substring(0,indeks);
			for(i = sol.length()-1; i>-1; i--) {
				if(myString.isOperator(sol.charAt(i))) {
					return donusturme.stringToDouble(sol.substring(i+1));
				}
			}
			return donusturme.stringToDouble(sol);
		}
	}
	
	private static String yanindakiSayiyiBulString(String formula, int indeks, boolean isRight){ //islemi gerceklestirmek icin operatorun sagindaki ve solundaki sayilari aliyoruz.
		StringIslemleri myString = new StringIslemleri();
		int i = 0;
		if(isRight) {
			String sag = formula.substring(indeks+1);
			for(i = 1; i< sag.length(); i++) {
				if(myString.isOperator(sag.charAt(i))) {
					return sag.substring(0, i);
				}
			}
			return sag;
		}
		else {
			String sol = formula.substring(0,indeks);
			for(i = sol.length()-1; i>-1; i--) {
				if(myString.isOperator(sol.charAt(i))) {
					return sol.substring(i+1);
				}
			}
			return sol;
		}
	}

}
