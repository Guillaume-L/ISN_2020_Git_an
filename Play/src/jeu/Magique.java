package jeu;

import java.util.ArrayList;

public class Magique extends Parcelle{

	public Magique() {
		this.visible = true;
		this.visuel = "M";
		this.population = new ArrayList<Personnage>();
		this.image = "magique";
	}
	public String declenchement() {
		return new String("magique");
	}
}
