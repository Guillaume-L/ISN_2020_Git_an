package jeu;

public class Heros extends Personnage{
	public Heros() {
		point_de_vie=2;
		position_x=1;
		position_y=1;
		visuel = "H";
		image = "hero";
	}
	public Heros(int pointDeVie, int positionX, int positionY) {
		point_de_vie = pointDeVie;
		position_x = positionX;
		position_y = positionY;
		visuel = "H";
		image = "hero";
	}
}
