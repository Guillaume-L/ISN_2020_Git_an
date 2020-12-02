package jeu;

import java.util.ArrayList;

public abstract class Parcelle {
	boolean visible;
	String visuel;
	ArrayList<Personnage> population;
	String image;
	
	public boolean testPresence() {
		for (Personnage personnage : this.population) {
			if (personnage.testVivant())
				return true;
		}
		return false;

	}
	public String declenchement() {
		return new String("rien");
	}
	public void reveler() {
	}
}
