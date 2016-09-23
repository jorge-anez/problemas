public class Main{
	public static double f(double x){
		return (x-2)*(x-2);
	}
	//Recursive algorithm
	public static double ternarySearchRecursive(double left, double right, double precision){
		if (Math.abs(right - left) < precision)
			return (left + right)/2;	
		double leftThird = (2*left + right)/3;
		double rightThird = (left + 2*right)/3;		
		if (f(leftThird) > f(rightThird))
			return ternarySearchRecursive(leftThird, right, precision); 
		else
			return ternarySearchRecursive(left, rightThird, precision);		
	}
	public static double ternarySearchIterative(double left, double right, double precision){
		while(true){
			if (Math.abs(right - left) < precision)
				return (left + right)/2.0;
			
			double leftThird = left + (right - left)/3;
			double rightThird = right - (right - left)/3;
			if (f(leftThird) > f(rightThird))
				left = leftThird;
			else
				right = rightThird;
		}
	}
	
	public static double goldenSectionSearch(double a, double b, double tol){
		double gr = (Math.sqrt(5) + 1.0) / 2.0;
		double c = b -  (b - a) / gr;
		double d = a + (b - a) / gr;
		while (Math.abs(c - d) > tol){
			if (f(c) < f(d))
				b = d;
			else
				a = c;

			// we recompute both c and d here to avoid loss of precision which may lead to incorrect results or infinite loop
			c = b - (b - a) / gr;
			d = a + (b - a) / gr;
		}
		return (b + a) / 2;
	}
	
	public static void main(String arg[]){
		System.out.println(ternarySearchRecursive(-5,5,1e-5));
		System.out.println(ternarySearchIterative(-5,5,1e-5));
		System.out.println(goldenSectionSearch(-5,5,1e-5));
	}
	
}



