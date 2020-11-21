package jeu;

public class Heros extends Personnage{
	int pdv;
	int position_x;
	int position_y;
	
	public Heros() {
		pdv=1;
		position_x=1;
		position_y=1;
	}
	
	public void vivacite(int n) {
		pdv=n; 
	}
}
