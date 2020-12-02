package jeu;

import java.util.ArrayList;

public class Tresor extends Parcelle{

	public Tresor(boolean visible) {
		this.visible = visible;
		this.visuel = "T";
		this.population = new ArrayList<Personnage>();
		if (visible)
			this.image = "tresor";
		else
			this.image = "sol";
	}
	public void reveler() {
		this.visible = true;
		this.image = "tresor";
	}
	public String declenchement() {
		if (this.visible) 
			return new String("victoire");
		else
			return new String("rien");
	}
}
