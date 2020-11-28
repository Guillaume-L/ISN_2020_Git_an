package jeu;

import java.util.ArrayList;

public class Tresor extends Parcelle{

	public Tresor(boolean visible) {
		this.visible = visible;
		this.visuel = "T";
		this.population = new ArrayList<Personnage>();
	}
	public void reveler() {
		this.visible = true;
	}
	public String declenchement() {
		if (this.visible) 
			return new String("victoire");
		else
			return new String("rien");
	}
}
