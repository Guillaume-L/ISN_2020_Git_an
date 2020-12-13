package jeu;

import java.util.ArrayList;
import java.util.Random;

public class Fantome extends Monstre{
	
	public Fantome(int point_de_vie, int position_x, int position_y) {
		super(point_de_vie,position_x,position_y);
		this.visuel = "F";
		this.image = "ghost";
	}
}
