import java.math.BigDecimal;

public class MatematikselIslemler {

	public double islemiSonuclandir(double sayi1, String islem, double sayi2) { //matematiksel islem operatorlerini tanimlayarak yapilacak islemi belirledik.
		if(islem.equals("%")) {
			return sayi1 % sayi2;
		}
		else if(islem.equals("/")) {
			return sayi1 / sayi2;
		}
		else if(islem.equals("*")) {
			return sayi1 * sayi2;
		}
		else if(islem.equals("+")) {
			return sayi1 + sayi2;
		}
		else if(islem.equals("-")) {
			return sayi1 - sayi2;
		}
		else if(islem.equals("^")) {
			return Math.pow(sayi1, sayi2);
		}
		else {
			System.out.println("Beklenenden farkli bir islem geldi!");
			return 0;		//islem bu beklenenlerden farkli ise, method 0 donuyor
		}
	}

	public double trigonometrikIslem(double deger, String islem) { //trigonometrik islemleri tanimlayarak yapilacak islemleri belirledik.
		double result = 0.0;
		if(islem.equals("sin")) {
			result = Math.sin(Math.toRadians(deger));
		}
		else if(islem.equals("cos")) {
			result = Math.cos(Math.toRadians(deger));
		}
		else if(islem.equals("tan")) {
			result = Math.tan(Math.toRadians(deger));
		}
		else if(islem.equals("cot")) {
			result = 1 / Math.tan(Math.toRadians(deger));
		}
		//ondalik kisim cok yuksek basamakli oluyor, onu azaltmak gerekiyor, bunun icin islemin sonucunu yazdigimiz trasEt metoduna gondererek kisalttik. 
		return trasEt(result);
	}
	
	public double trasEt(double trassizSayi) {
		//burada sin-45 degeri -0.710000000001 gibi bir degere donustugu icin kendi yazdigim string replace metodumu yapamiyordum,
		//o yuzden trasEt methodunu yazmak zorunda kaldim.
		BigDecimal a = new BigDecimal(trassizSayi);
		BigDecimal trasli = a.setScale(3, BigDecimal.ROUND_HALF_EVEN);
		return trasli.doubleValue();
	}

}

