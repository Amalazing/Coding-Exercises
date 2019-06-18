
public class RationalNum {

	private int numerator;
    private int denominator;

    public RationalNum()
    {
        numerator = 0;
        denominator = 1;
    }

    public RationalNum(int n, int d)
    {
    	numerator = n;
    	
    	if(d == 0) {
    		System.out.println("Deominator cannot be 0, it has been set to 1");
    		denominator = 1;
    	}else {
    		denominator = d;
    	}
    }

    public int getNumerator()
    {
        return numerator;
    }

    public int getDenominator()
    {
        return denominator;
    }

    public void setNumerator(int n)
    {
        numerator = n;
    }

    public void setDenominator(int n) 
    {
    	if(n == 0) {
    		System.out.println("Deominator cannot be 0, it has been set to 1");
    		denominator = 1;
    	}else {
    		denominator = n;
    	}
        
    }

    public String printFraction()
    {
        return numerator + "/" + denominator; 
    }
    
    public double printDecimal()
    {
    	return (double) numerator / denominator;
    }

    public RationalNum add (RationalNum n)
    {
       int newDenominator = denominator * n.getDenominator();
       int newNumerator = (numerator * n.getDenominator()) + (n.getNumerator() * denominator);

       return new RationalNum (newNumerator, newDenominator);
    }

/*
    public RationalNum subtract(RationalNum n)
    {
    	int commonDenominator = denominator * n.getDenominator();
    }
*/
}
