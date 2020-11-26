package jeu;

import java.util.ArrayList;

public class Passage extends Parcelle{
	int positionArriveeY;
	int positionArriveeX;
	

	public Passage(int positionArriveeX, int positionArriveeY) {
		this.visible = true;
		this.visuel = "P";
		this.positionArriveeX = positionArriveeX;
		this.positionArriveeY = positionArriveeY;
		this.population = new ArrayList<Personnage>();
	}
}
