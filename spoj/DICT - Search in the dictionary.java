import java.util.*;
import java.io.*;
class Main {
	public static final int ALPH_SIZE = 26;
	public static ArrayList<String> L = new ArrayList<>();
	static class Node{
		public int prefixes;
		public boolean word;
		public Node paths[];
		Node(){
			prefixes = 0;
			word = false;
			paths = new Node[ALPH_SIZE];
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
	public static void solve(Node p, char V[], int i, StringBuffer buff){
		if(i < V.length){
			if(!p.existPath(V[i] - 'a'))
				//System.out.println("No match.");
			buff.append("No match.");
			else
				solve(p.getPath(V[i] - 'a'), V, i + 1, buff);
		}
		else
			for(int k = 0; k < ALPH_SIZE; k++)
				if(p.existPath(k))
					solve(p.getPath(k), buff.append((char)(k + 'a')));
	}
	public static void solve(Node p, StringBuffer buff){		
		if(p.word){			
			//buff = new StringBuffer(buff);
			//L.add(buff.toString());
			//System.out.println(buff);
			buff.append('\n');
		}
			//System.out.println(buff);
		
			for(int k = 0; k < ALPH_SIZE; k++)
				if(p.existPath(k))
					solve(p.getPath(k), buff.append((char)(k + 'a')));
	}
	public static void main(String []arg)throws IOException{
		InputReader in = new InputReader(System.in);
		int n = in.nextInt();
		Node root = new Node();
		for(int k = 0; k < n; k++){
			Node p = root;
			for(char e : in.next().toCharArray()){
				p = p.getPath(e - 'a');
				p.prefixes++;
			}
			p.word = true;
		}
		int m = in.nextInt();
		//solve(root, "");
		for(int k = 1; k <= m; k++){
			System.out.println(String.format("Case #%d:", k));
			StringBuffer buff = new StringBuffer();
			solve(root, in.next().toCharArray(), 0, buff);
			System.out.println(buff);
		}
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