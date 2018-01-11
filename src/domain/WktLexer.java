package domain;

import java.util.ArrayList;
import java.util.List;

public class WktLexer {
	
	public List<Token> tokens = new ArrayList<Token>();
	private String sourceCode;
	private int current;
	private String currentWord;
	private String currentChar;
	
	public WktLexer(String sourceCode){
		this.sourceCode = sourceCode;
		this.current = 0;
		this.currentWord = "";
		this.currentChar = "";
	}
	
	public void lex(){
		while (this.current < this.sourceCode.length()) {
			if(this.currentWord==""){
				//System.out.println(this.currentWord);
			}
			
			this.currentChar = this.sourceCode.substring(this.current, this.current +1);
			
			if(testSymbols()){
				consume();
				this.tokens.add(new Token(this.currentWord,Token.TokenType.SYMBOL));
				this.currentWord = "";
			}
			else if(testSpace()){
				consume();
				while(testSpace()){
					consume();
				}
				//this.tokens.add(new Token(this.currentWord,Token.TokenType.SPACE));
				this.currentWord= "";
			}
			else if (testNumber()){
				while(testNumber()){
					consume();
				}
				this.tokens.add(new Token(this.currentWord,Token.TokenType.NUMBER));
				this.currentWord="";
			}
			else if (testKey()){
				while(testKey()){
					consume();
				}
				this.tokens.add(new Token(this.currentWord,Token.TokenType.KEYWORD));
				this.currentWord="";
			}
			else{
				consume();
			}
		}
	}
	
	private boolean testSymbols(){
		return "()".contains(this.currentChar);
	}
	
	
	private boolean testSpace(){
		return " \t\n".contains(this.currentChar);
	}
	
	private boolean testKey(){
		String keys ="AZERTYUIOPMLKJHGFDSQWXCVBN" ;
		return (keys.contains(this.currentChar));
	}
	
	private boolean testNumber(){
		return ("9876543210.".contains(this.currentChar));
	}
	

	private void consume() {
		
		this.currentWord += this.currentChar;
		this.current += 1;
		
		if(this.current < this.sourceCode.length()){
			this.currentChar = this.sourceCode.substring(this.current, this.current+1);
		}
	}
}
