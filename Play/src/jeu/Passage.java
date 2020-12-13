package jeu;

import java.util.ArrayList;

public class Passage extends Parcelle{
	public int positionArriveeY;
	public int positionArriveeX;
	

	public Passage(int positionArriveeX, int positionArriveeY) {
		this.visible = true;
		this.visuel = "P";
		this.positionArriveeX = positionArriveeX;
		this.positionArriveeY = positionArriveeY;
		this.population = new ArrayList<Personnage>();
		this.image ="passage";
	}
	public String declenchement() {
		return new String("passage " + positionArriveeX + " " + positionArriveeY);
	}
}
