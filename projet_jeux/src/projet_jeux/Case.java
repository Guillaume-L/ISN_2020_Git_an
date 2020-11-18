package projet_jeux;

import java.util.ArrayList;

public abstract class Case {
	boolean visible;
	boolean franchissable;
	String visuel;
	ArrayList<Personnage> population;
	
	public Case(boolean visible, boolean franchissable, String visuel) {
		this.visible = visible;
		this.franchissable = franchissable;
		this.visuel = visuel;
		population = new ArrayList<Personnage>();
	}
	public Case(boolean visible, boolean franchissable) {
		this.visible = visible;
		this.franchissable = franchissable;
		population = new ArrayList<Personnage>();
	}
	public void ajoutPersonnage(Personnage personnage) {
		this.population.add(personnage);
	}
	public boolean testPresence() {
		return (!(this.population.isEmpty()));
	}
	public void retirerHero() {
		boolean recherche = true;
		int i = 0;
		while (recherche) {
			if (this.population.get(i) instanceof Hero) {
				recherche = false;
				this.population.remove(i);
			}
			else {
				i++;
			}
		}
	}
}
