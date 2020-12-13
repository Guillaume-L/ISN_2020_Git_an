package jeu;
import java.util.ArrayList;
import java.util.Random;
public class Monstre extends Personnage{

	public Monstre(int point_de_vie, int position_x, int position_y) {
		this.point_de_vie = point_de_vie;
		this.position_x = position_x;
		this.position_y = position_y;
		this.visuel = "E";
		this.image = "monstre";
	}
	
	public int[] deplacement(Labyrinthe L) {
		boolean deplac;
		int[] coord = new int[2];
		int[] nouvellePosition = new int[2];
		ArrayList<int[]> deplacement_possib= new ArrayList<int[]>();
		ArrayList<int[]> deplacements = new ArrayList<int[]>();
		int[] gauche = new int[2];
		gauche[0] = -1;
		gauche[1] = 0;
		int[] droite = new int[2];
		droite[0] = 1;
		droite[1] = 0;
		int[] haut = new int[2];
		haut[0] = 0;
		haut[1] = -1;
		int[] bas = new int[2];
		bas[0] = 0;
		bas[1] = 1;
		deplacements.add(gauche);
		deplacements.add(droite);
		deplacements.add(haut);
		deplacements.add(bas);
		for (int[] deplacement : deplacements) {
			int[] coordonnee = new int[2];
			coordonnee[0] = this.position_x + deplacement[0];
			coordonnee[1] = this.position_y + deplacement[1];
			if (this.test_deplacement(L, coordonnee[0], coordonnee[1])) {
				deplacement_possib.add(coordonnee);
				}
		}
		int taille=deplacement_possib.size();
		if (taille == 0) {
			nouvellePosition[0] = this.position_x;
			nouvellePosition[1] = this.position_y;
			return nouvellePosition;
		}
		else {
			Random rand= new Random();
			int[] move = deplacement_possib.get(rand.nextInt(taille));
			return move;
		}	
	}
}
