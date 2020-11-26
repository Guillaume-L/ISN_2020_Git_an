package jeu;

import java.util.ArrayList;

public class Magique extends Parcelle{

	public Magique() {
		this.visible = true;
		this.visuel = "M";
		this.population = new ArrayList<Personnage>();
	}
}
