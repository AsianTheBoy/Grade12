/*Q1:   a)q
 * 		b)q
 * 		c)p
 * 		d)p
 * 		e)q
 */

public class FractionMainInstance {

	public static void main(String[] args) {

		Fraction f1 = new Fraction();
		f1.num = 2;
		f1.den = 3;
		Fraction f2 = new Fraction();
		f2.num = 5;
		f2.den = 6;
		Fraction f3 = new Fraction();
		f3.num = 0;
		f3.den = 0;

		System.out.println("Fraction f1+f2");
		f1.plusEquals(f2);
		System.out.println(f1.num);
		System.out.println(f1.den);
		System.out.println("Fraction f3");
		f3 = f1.plus(f2);
		System.out.println(f3.num);
		System.out.println(f3.den);
		f3.reduce();
		System.out.println("Fraction f3 after simplification");
		System.out.println(f1.num);
		System.out.println(f1.den);
	}
}
