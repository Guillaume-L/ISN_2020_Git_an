package jeu;

public abstract class Personnage {
	public int point_de_vie; 
	public int position_x;
	public int position_y;
	public String visuel;
	public String image;
	
	public boolean testVivant() {
		return (point_de_vie > 0);
	}
	public void subirDegat() {
		this.point_de_vie = Math.max(this.point_de_vie - 1, 0);
	}
}