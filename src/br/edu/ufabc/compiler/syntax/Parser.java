package br.edu.ufabc.compiler.syntax;

import br.edu.ufabc.compiler.lexical.Analyzer;
import br.edu.ufabc.compiler.lexical.Token;

public class Parser {
	private Analyzer scanner;
	private Token    token;
	
	public Parser(Analyzer scanner) {
		this.scanner = scanner;
	}
	
	public void PROG() {
		token = scanner.nextToken();
		if (token.getType() != Token.RW || !token.getStr().equals("PROGRAM")) {
			throw new SyntaxException("Palavra reservada PROGRAM esperada, encontrei "+token.getStr()+ " linha "+scanner.getLine()+" coluna "+scanner.getColumn())	;		
		}
		token = scanner.nextToken();
		if (token.getType() != Token.ID) {
			throw new SyntaxException("Identificador de Programa esperado, encontrei "+token.getStr()+ " linha "+scanner.getLine()+" coluna "+scanner.getColumn());
		}
		token = scanner.nextToken();
		if (token.getType() != Token.PON) {
			throw new SyntaxException("; esperado, encontrei "+token.getStr() + " linha "+scanner.getLine()+" coluna "+scanner.getColumn());
		}
		E();
	}
	
	public void E() {
		T();
		El();
		if (token.getType() != Token.PON) {
			throw new SyntaxException("; Esperado - encontrei " + token.getStr() + " linha "+scanner.getLine()+" coluna "+scanner.getColumn());
		}
	}
	public void T() {
		token = scanner.nextToken();
		if (token.getType()!= Token.ID && token.getType()!= Token.NUM) {
			throw new SyntaxException("ID ou NUM esperado, encontrei "+token.getStr() + " linha "+scanner.getLine()+" coluna "+scanner.getColumn());
		}
	}
	public void El() {
		token = scanner.nextToken();
		if (token.getType() == Token.OP) {
			OP();
			T();
			El();
		}		
	}
	public void OP() {
		if (token.getType() != Token.OP) {
			throw new SyntaxException("Operador esperado, porém encontrei "+token.getStr() + " linha "+scanner.getLine()+" coluna "+scanner.getColumn());
		}
	}
}
