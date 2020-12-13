package jeu;

import java.util.ArrayList;

public class Piege extends Parcelle{
	
	public Piege() {
		this.visible = false;
		this.visuel = "0";
		this.population = new ArrayList<Personnage>();
		this.image = "sol";
	}
	public String declenchement() {
		this.visible = true;
		this.visuel = "X";
		this.image = "piege";
		return new String("piege");
		}

}
