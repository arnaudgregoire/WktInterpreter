package domain;

import java.util.ArrayList;
import java.util.List;


public class WktParser {
	
	public List<Token> tokens = new ArrayList<Token>();
	public List<Geometry> geoms = new ArrayList<Geometry>();
	private int index;
	private Token t;

	public WktParser(List<Token> tokens) {
		super();
		this.tokens = tokens;
		this.index  = 0;
		this.t = tokens.get(0);
	}
	
	public void parse(){
		while(this.index<tokens.size()){
			t = this.tokens.get(this.index);
			
			//System.out.println(this.tokens.get(index).getword());
			//System.out.println(geoms);
			
			if(t.getType().equals(Token.TokenType.KEYWORD)){
				
				if(t.getword().equals("POINT")){
					parsePoint();
				}
				
				else if(t.getword().equals("GEOMETRYCOLLECTION")){
					parseGeometryCollection();
				}
				
			}
		}
	}
	
	
	private void parseGeometryCollection() {
		int c = 0;
		//keyword
		this.index ++;
		//parenthèse gauche
		this.index ++;
		while(!this.tokens.get(this.index).getword().equals(")")){
			t = this.tokens.get(this.index);
			//System.out.println(this.tokens.get(index).getword());
			//System.out.println(geoms);
			
			if(t.getword().equals("POINT")){
				parsePoint();
			}
			
			else if(t.getword().equals("GEOMETRYCOLLECTION")){
				parseGeometryCollection();
			}
			c++;
		}
		//parenthèse droite
		this.index++;
		ArrayList<Geometry> temp = new ArrayList<Geometry>();
		for (int i = 0; i < c; i++) {
			temp.add(geoms.get(geoms.size()-1));
			geoms.remove(geoms.get(geoms.size() - 1));
		}
		this.geoms.add(new GeometryCollection(temp));
	}

	private void parsePoint(){
		double x = 0;
		double y = 0;
		//keyword
		this.index ++;
		//parenthèse gauche
		this.index ++;
		//x
		x = Double.parseDouble(this.tokens.get(this.index).getword());
		this.index ++;
		//y
		y = Double.parseDouble(this.tokens.get(this.index).getword());
		this.index ++;
		// parenthèse droite 
		this.index ++;
		
		//on ajoute le point
		this.geoms.add(new Point(x,y));
	}
}
