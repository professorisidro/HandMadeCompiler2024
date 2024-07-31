package br.edu.ufabc.compiler.lexical;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import br.edu.ufabc.compiler.lexical.exceptions.LexicalException;

public class Analyzer {

	private int state;
	private int position;
	private int line;
	private int column;
	private char content[];
	private List<String> reservedWords;

	public Analyzer(String filename) {
		try {
			String strContent = new String(Files.readAllBytes(Paths.get(filename)));
			this.content = strContent.toCharArray();
			this.state = 0;
			this.position = 0;
			this.line = 1;
			this.column = 0;
			this.reservedWords = Arrays.asList("BEGIN", "END", "VAR", "PROGRAM", "INTEGER");
		} catch (IOException ex) {
			System.err.println("Erro ao ler Arquivo " + filename);
		}
	}

	public Token nextToken() {
		char c;
		state = 0;
		String strTk = "";
		if (isEOF()) { // cheguei no fim do arquivo
			return null;
		}
		while (true) {
			c = nextChar();
			switch (state) {
			case 0:
				if (isWS(c)) {
					state = 0;
				} else if (isOperator(c)) {
					this.column++;
					return new Token(Token.OP, String.valueOf(c));
				} else if (isPonctuation(c)) {
					this.column++;
					return new Token(Token.PON, String.valueOf(c));
				} else if (isColon(c)) {
					this.column++;
					strTk += c;
					state = 3;
				} else if (isDigit(c)) {
					strTk += c;
					state = 1;
				} else if (isChar(c)) {
					strTk += c;
					state = 2;
				} else if (isEOF(c)) {
					return null;
				} else {
					throw new LexicalException("Malformed Token");
				}
				break;
			case 1:
				if (isDigit(c)) {
					state = 1;
					strTk += c;
				} else if (isOperator(c) || isWS(c) || isPonctuation(c)) {
					backtrack();
					this.column += strTk.length();
					return new Token(Token.NUM, strTk);
				} else {
					throw new LexicalException("Malformed Number");
				}
				break;
			case 2:
				if (isDigit(c) || isChar(c)) {
					state = 2;
					strTk += c;
				} else if (isOperator(c) || isWS(c) || isPonctuation(c)) {
					backtrack();
					this.column += strTk.length();
					if (isReservedWord(strTk)) {	
						return new Token(Token.RW, strTk);
					}
					return new Token(Token.ID, strTk);
				}
				break;
			case 3:
				if (isEqual(c)) {
					strTk+=c;
					this.column++;
					return new Token(Token.ATT, strTk);
				}
				else {
					throw new LexicalException("Unrecognized Assignment");
				}				
			default:
				return null;
			}
		}
	}

	public char nextChar() {
		if (isEOF()) {
			return '\0';
		}
		return content[position++];
	}

	public boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

	public boolean isChar(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}

	public boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}

	public boolean isPonctuation(char c) {
		return c == ';' || c == ',' || c == '.';
	}

	public boolean isWS(char c) {
		if (c == ' ') {
			this.column++;
		}
		else if (c == '\t') {
			this.column += 4;
		}
		else if (c == '\n') {
			this.line ++;
			this.column = 0;
		}
		
		return c == ' ' || c == '\t' || c == '\n' || c == '\r';
	}
	
	public boolean isEqual(char c) {
		return c == '=';
	}
	public boolean isColon(char c) {
		return c == ':';
	}

	public boolean isEOF() {
		return position >= content.length;
	}

	public boolean isEOF(char c) {
		return c == '\0';
	}

	public void backtrack() {
		this.column--;
		position--;
	}

	public boolean isReservedWord(String s) {
		for (String rw : reservedWords) {
			if (rw.equals(s)) {
				return true;
			}
		}
		return false;
	}

	public void welcome() {
		System.out.printf("STATE     POSITION CURRENT CHAR\n");
	}

	public void log(char c) {
		System.out.printf("%2d           %2d          %c\n", state, position, c);
	}

}
