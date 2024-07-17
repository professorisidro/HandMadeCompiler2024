package br.edu.ufabc.compiler.main;
import br.edu.ufabc.compiler.lexical.Analyzer;
import br.edu.ufabc.compiler.lexical.Token;

public class MainClass {
	public static void main(String[] args) {
		Analyzer analyzer = new Analyzer("input.in");
//		analyzer.welcome();
		Token tk = null;
		do {
		   tk = analyzer.nextToken();
		   System.out.println(tk);
		} while (tk != null);
	}
}
