package jeu;

import java.util.ArrayList;

public class Mur extends Parcelle{

	public Mur() {
		this.visible = true;
		this.visuel = "1";
		this.population = new ArrayList<Personnage>();
		this.image = "mur";
	}
}
