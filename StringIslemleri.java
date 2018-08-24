import java.util.Scanner;

public class StringIslemleri {
	
	public static String tersCevir(String sayiStr) {
		int i = 0;
		String yeniSayi = "";
		for(i = sayiStr.length()-1 ; i > -1 ; i--){ //sayiyi ters cevirdik
			yeniSayi = yeniSayi + sayiStr.charAt(i);
		}
		return yeniSayi;
	}
	
	public String boslukTemizle(String formula){ //bosluklari temizledik, tab karakteri kadar bosluk varsa onlari da temizledik.
		//formula = formula.replace(" ", "");
		formula = myClearMethod(formula, ' ');
		//formula = formula.replace("\t", "");
		formula = myClearMethod(formula, '	');
		return formula;
	}
	
	public String myReplaceAll(String formula, String source, String target) { //kendi replace metodumuzu yazdik.
		int i = 0;
		String strN = "" ;  
		while(i < formula.length()){
			if(formula.indexOf(source) != -1){				
				i = formula.indexOf(source);
				formula = formula.substring(0, i) + target + formula.substring(i+source.length());			
			}
			else return formula;
		}
		return strN ;
	}
	
	public boolean isOperator(char c) { //operator kontrolu 
		if(c == '/' || c == '*' || c == '+' || c == '-' || c=='%' || c== '^') {
			return true;
		}
		else return false;
	}
	
	public String myClearMethod(String formula, char toClean){	//buradaki ikinci parametre ile verilen harf formulden temizleniyor
		int i = 0;
		String strN = "" ;  
		for(i = 0 ; i < formula.length() ; i++){
			if(formula.charAt(i) != toClean){				
				strN = strN + formula.charAt(i);			
			}
		}
		return strN ;
	}
	
}
