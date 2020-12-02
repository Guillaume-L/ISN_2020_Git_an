package jeu;

public class Monstre extends Personnage{

	public Monstre(int point_de_vie, int position_x, int position_y) {
		this.point_de_vie = point_de_vie;
		this.position_x = position_x;
		this.position_y = position_y;
		this.visuel = "E";
		this.image = "monstre";
	}
	

}
