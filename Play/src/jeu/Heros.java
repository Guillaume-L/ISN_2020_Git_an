package jeu;

public class Heros extends Personnage{
	int pdv;
	int position_x;
	int position_y;
	
	public Heros() {
	}
	
	public void vivacite(int n) {
		pdv=n; 
	}

	public void deplacement(Labyrinthe L, int x, int y) {
		if (((Math.abs(x-position_x)!=1) && (Math.abs(y-position_y)!=0)) || 
				((Math.abs(x-position_x)!=0) && (Math.abs(y-position_y)!=1))) {
			System.out.println("Impossible d'aller dans cette direction");
		}
		else if (x>L.longueur || y>L.largeur) {
			System.out.println("Impossible d'aller dans cette direction");
		}
		else {
			position_x=x;
			position_y=y;
		}
	}
}
