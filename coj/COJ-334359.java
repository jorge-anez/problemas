import java.util.*;
class Main{


public static void main(String arg[]){
	Scanner in=new Scanner(System.in);	
	while(in.hasNext()){
		String A1="",A2="";
		StringTokenizer tokens=new StringTokenizer(in.nextLine());
		while(tokens.hasMoreTokens()){
			A1+=tokens.nextToken().toUpperCase().charAt(0);
		}
		tokens=new StringTokenizer(in.nextLine());
		while(tokens.hasMoreTokens()){
			A2+=tokens.nextToken().toUpperCase().charAt(0);
		}
		if(A2.equals(A1))
			System.out.println("yes");
		else
			System.out.println("no");
	}
}
}