package jeu;

import java.util.ArrayList;

public abstract class Parcelle {
	public boolean visible;
	public String visuel;
	ArrayList<Personnage> population;
	public String image;
	
	public boolean testPresence() {
		for (Personnage personnage : this.population) {
			if (personnage.testVivant())
				return true;
		}
		return false;
	}
	public boolean testPresenceHero() {
		for (Personnage personnage : this.population) {
			if (personnage.image.equalsIgnoreCase("hero"))
				return true;
		}
		return false;
	}
	public String declenchement() {
		return new String("rien");
	}
	public void reveler() {
	}
	public boolean testPresenceEnnemi() {
		for (Personnage personnage : this.population) {
			if ((!personnage.image.equalsIgnoreCase("hero")) && personnage.testVivant())
				return true;
		}
		return false;
	}
	public boolean testPresenceCombat() {
		for (Personnage personnage : this.population) {
			if (personnage.enCombat)
				return true;
		}
		return false;
	}
}