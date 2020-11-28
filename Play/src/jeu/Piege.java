package jeu;

import java.util.ArrayList;

public class Piege extends Parcelle{
	
	public Piege() {
		this.visible = false;
		this.visuel = "0";
		this.population = new ArrayList<Personnage>();
	}
	public String declenchement() {
		this.visible = true;
		this.visuel = "X";
		System.out.println("Le Hero vient de marcher sur un piège. Il perd 1 point de vie");
		return new String("piege");
		}

}
