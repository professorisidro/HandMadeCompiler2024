package br.edu.ufabc.compiler.lexical;

import java.util.HashMap;

public class Token {
	
	public static final int NUM = 0;
	public static final int ID  = 1;
	public static final int OP  = 2;
	public static final int PON = 3;
	public static final int RW  = 4;
	public static final int ATT = 5;
	
	
	
	private int type;
	private String str;
	private HashMap<Integer,String> tokenType;
	
	public Token() {
		super();
		init();
	}
	public Token(int type, String str) {
		super();
		init();
		this.type = type;
		this.str = str;
	}
	public void init() {
		this.tokenType = new HashMap<Integer, String>();
		tokenType.put(0, "TK_NUM");
		tokenType.put(1, "TK_ID ");
		tokenType.put(2, "TK_OP ");
		tokenType.put(3, "TK_PON");
		tokenType.put(4, "TK_RW ");
		tokenType.put(5, "TK_ATT");
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	@Override
	public String toString() {
		return "Token [type=" + tokenType.get(type) + ", str=" + str + "]";
	}
}
