package jeu;

import java.util.ArrayList;

public class Tresor extends Parcelle{

	public Tresor() {
		this.visible = false;
		this.visuel = "T";
		this.population = new ArrayList<Personnage>();
	}
}
