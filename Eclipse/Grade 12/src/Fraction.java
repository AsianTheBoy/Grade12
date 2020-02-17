
public class Fraction {

	int num; // numerator
	int den; // denominator

	public void plusEquals(Fraction f2) {
		if (this.den != f2.den) {
		this.num *= f2.den;
		f2.num *= this.den;
		f2.den *= this.den;
		this.den = f2.den;
		this.num += f2.num;
		}
	}

	public Fraction plus(Fraction f2) {
		if (this.den != f2.den) {
			this.num *= f2.den;
			f2.num *= this.den;
			f2.den *= this.den;
			this.den = f2.den;
			this.num += f2.num;
			}
		else {
			this.num += f2.num;
		}
		return this;
	}

	public void reduce() {

		for (int i = 2; i < 10; i++) {
			if ((this.num % i) == 0 && (this.den % i) == 0) {
				this.num /= i;
				this.den /= i;
				i--;
			}
		}

	}
}