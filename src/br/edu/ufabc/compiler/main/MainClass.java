package br.edu.ufabc.compiler.main;

import br.edu.ufabc.compiler.lexical.Analyzer;
import br.edu.ufabc.compiler.lexical.exceptions.LexicalException;
import br.edu.ufabc.compiler.syntax.Parser;
import br.edu.ufabc.compiler.syntax.SyntaxException;

public class MainClass {
	public static void main(String[] args) {
		try {
		Analyzer analyzer = new Analyzer("expr.in");		
		Parser parser = new Parser(analyzer);
		parser.PROG();
		System.out.println("Analise Sintática completa sem erros");
		}
		catch(Exception ex) {
			if (ex instanceof LexicalException) {
				System.out.print("LEXICAL ");
			}
			else if (ex instanceof SyntaxException) {
				System.out.print("SYNTAX ");
			}
			System.out.println("ERROR - "+ex.getMessage());
		}
	}
}
