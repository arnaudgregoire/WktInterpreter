package domain;

public class Main {

	public static final String GEOMETRY = "POINT (5.2 6.7)";
	public static final String GEOMETRYCOLLECTION = "GEOMETRYCOLLECTION (POINT (5.2 6.7) GEOMETRYCOLLECTION (POINT (2.5 5.2) POINT (4.2 8.2)) POINT(4.2 1.0))";
	
	public static void main(String[] args) {
		WktLexer lexer = new WktLexer(GEOMETRYCOLLECTION);
		lexer.lex();
		/*
		for (int i = 0; i < lexer.tokens.size(); i++) {
			System.out.println(lexer.tokens.get(i));
		}
		*/
		WktParser parser = new WktParser(lexer.tokens);
		parser.parse();
		
		for (int i = 0; i < parser.geoms.size(); i++) {
			System.out.println(parser.geoms.get(i));
		}
		
	}

}
