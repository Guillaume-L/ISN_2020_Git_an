package jeu;

import java.util.ArrayList;

public class Piege extends Parcelle{
	
	public Piege() {
		this.visible = false;
		this.visuel = "0";
		this.population = new ArrayList<Personnage>();
	}

}
