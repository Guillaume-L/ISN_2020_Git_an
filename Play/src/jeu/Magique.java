package jeu;

import java.util.ArrayList;

public class Magique extends Parcelle{

	public Magique() {
		this.visible = true;
		this.visuel = "M";
		this.population = new ArrayList<Personnage>();
	}
	public String declenchement() {
		System.out.println("La tr�sor est d�sormais visible");
		return new String("magique");
	}
}
