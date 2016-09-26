import java.util.*;
import java.io.*;
class Main {
	public static final int ALPH_SIZE = 10;
	public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out), 2048);
	public static Node root = new Node(true);
	static class Node{
		public int prefixes;
		public boolean word;
		public Node paths[];
		Node(){
			prefixes = 0;
			word = false;
			paths = new Node[ALPH_SIZE];
		}
		Node(boolean sw){
			prefixes = 0;
			word = false;
			paths = new Node[2];
		}
		public boolean existPath(int i){
			return paths[i] != null;
		}
		public Node getPath(int i){
			if(!existPath(i))
				paths[i] = new Node();
			return paths[i];
		}
	}
	public static void print(Node p, char buff[], int i) throws IOException{		
		if(p.word) {			
			out.write(buff, 0, i);
			out.newLine();
		}
		if(p.paths.length == 2){
			if(p.existPath(0)){
				buff[i] = '-';
				print(p.getPath(0), buff, i + 1);
			}
			if(p.existPath(1)){
				buff[i] = '+';
				print(p.getPath(1), buff, i + 1);
			}
		}else
			for(int k = 0; k < ALPH_SIZE; k++)
				if(p.existPath(k)){
					if(buff[0] == '-')
						buff[i] = (char)('9' - k);
					else
						buff[i] = (char)(k + '0');
					print(p.getPath(k), buff, i + 1);
				}
	}
	public static long count1(char pattern[]) throws IOException{
		Node p = root;
		int i = 0;
		long s = 0;
		if(pattern[i] == '-'){
			p = p.getPath(0);
			i++;
		}else{
			s +=p.getPath(0).prefixes;
			p = p.getPath(1);
		}
		for( ; i < pattern.length; i++){
			for(int j = 0; j < pattern[i] - '0'; j++ ){
				if(p.paths[j] != null)
					s += p.paths[j].prefixes;
			}
			if(p.existPath(pattern[i] - '0'))
				p = p.getPath(pattern[i] - '0');
		}
		return s;
	}
	public static boolean exist(char pattern[]){
		Node p = root;
		int i = 0;
		if(pattern[i] == '-'){
			p = p.getPath(0);
			i++;
		}else
			p = p.getPath(1);
		for( ; i < pattern.length; i++){
			if(!p.existPath(pattern[i] - '0'))
				return false;
			p = p.getPath(pattern[i] - '0');
		}
		return p.word;
	}
	public static void main(String []arg)throws IOException{
		InputReader in = new InputReader(System.in);
		int T = in.nextInt();
		for(int k = 0; k < T; k++){
			Node p = root;
			int i = 0;
			char str[] = in.next().toCharArray();
			if(exist(str))
				continue;
			if(str[i] == '-'){
				p = p.getPath(0);
				p.prefixes++;
				i++;
				for( ; i < str.length; i++){
					p = p.getPath('9' - str[i]);
					p.prefixes++;
				}
			}else{
				p = p.getPath(1);
				p.prefixes++;
				for( ; i < str.length; i++){
					p = p.getPath(str[i] - '0');
					p.prefixes++;
				}
			}
			p.word = true;
		}
		char buff[] = new char[10];
		print(root, buff, 0);
		String pattern = "-002";
		out.write(String.valueOf(count1(pattern.toCharArray())));
		out.flush();
	}
	static class InputReader{
		BufferedReader in;
		StringTokenizer tokens;		
		InputReader(InputStream in){
			this.in = new BufferedReader(new InputStreamReader(in), 2048);			
		}
		String next() throws IOException{
			if(tokens == null || !tokens.hasMoreTokens()){
				String str = in.readLine();
				if(str == null || str.equals(""))
					return null;
				tokens = new StringTokenizer(str);			
			}
			return tokens.nextToken();
		}
		int nextInt() throws IOException{			
			return Integer.parseInt(next());
		}		
	}
	
}