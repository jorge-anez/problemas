import java.util.Scanner;
class Main{

public static void main(String arg[]){
	Scanner in=new Scanner(System.in);
	while(true){	
		int n=in.nextInt();
		if(n==0) break;
		double res=n*(n+1)/3.0;
		String str=String.format("%.6f\n",Math.sqrt(res));
		System.out.print(str);
	}
}
}