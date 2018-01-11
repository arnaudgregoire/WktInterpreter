package domain;

public class Token {
	
	//static
	public enum TokenType {
		  KEYWORD,
		  IDENT,
		  NUMBER,
		  COMMENT,
		  STRING,
		  SYMBOL,
		  SPACE
		}
	
	//final
	private final String word;
	private final TokenType type;
	
	//constructor
	public Token(String word, TokenType type) {
		super();
		this.word = word;
		this.type = type;
	}
	
	//accessor
	public String getword() {
		return word;
	}
	public TokenType getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Token [word=" + word + ", type=" + type + "]";
	}
	
}

