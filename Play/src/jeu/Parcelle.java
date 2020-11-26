package jeu;

import java.util.ArrayList;

public abstract class Parcelle {
	boolean visible;
	String visuel;
	ArrayList<Personnage> population;
	
	public boolean testPresence() {
		if (population.isEmpty())
			return false;
		else
			return true;
	}
}
