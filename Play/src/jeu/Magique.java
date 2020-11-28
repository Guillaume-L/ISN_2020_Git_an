package jeu;

import java.util.ArrayList;

public class Magique extends Parcelle{

	public Magique() {
		this.visible = true;
		this.visuel = "M";
		this.population = new ArrayList<Personnage>();
	}
	public String declenchement() {
		System.out.println("La trésor est désormais visible");
		return new String("magique");
	}
}
