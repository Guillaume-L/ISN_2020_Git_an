package jeu;

public abstract class Personnage {
	public int point_de_vie; 
	public int position_x;
	public int position_y;
	public String visuel;
	
	public boolean test_deplacement(Labyrinthe L, int x, int y) {
		if (((Math.abs(x-position_x)!=1) && (Math.abs(y-position_y)!=0)) || 
				((Math.abs(x-position_x)!=0) && (Math.abs(y-position_y)!=1))) {
			return false;	
		}
		else if (x>L.longueur || y>L.largeur) {
			return false;
		} 
		else if (L.laby[x][y].visuel == "1"){ 
			return false;
		}
		else {
			return true;
		}
	}
	public boolean testVivant() {
		return this.point_de_vie > 0;
	}
	public void subirDegat() {
			this.point_de_vie = Math.max(0, this.point_de_vie - 1);
	}
}