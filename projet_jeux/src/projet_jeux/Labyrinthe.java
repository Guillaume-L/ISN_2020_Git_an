package projet_jeux;

import java.util.ArrayList;

public class Labyrinthe {
	int longueurX;
	int largeurY;
	Case[][] cases;
	ArrayList<Monstre> populationMonstre;
	Hero hero;
	int[] positionTresor;
	
	public Labyrinthe(int longueur, int largeur) {
		this.largeurY = largeur;
		this.longueurX = longueur;
		Case[][] grille = new Case[largeurY][longueurX];
		for (int i=0;i<largeurY;i++) {
			for (int j=0;j<longueurX;j++){
				if (i==0 || j==0 || i==largeurY-1 || j==longueur-1) {
					Mur parcelle = new Mur();
					grille[i][j] = parcelle;
				}
				else {
					Sol parcelle = new Sol();
					grille[i][j] = parcelle;
				}
			}
		}
		this.cases = grille;
		this.populationMonstre = new ArrayList<Monstre>();
		this.hero = new Hero(2,1,1);
		int[] positionTresor = new int[2];
		positionTresor[0] = longueur -2;
		positionTresor[1] = largeur -2;
		this.positionTresor = positionTresor;
		this.cases[longueur -2][largeur -2] = new Tresor(false, true);
	}
	public Labyrinthe(int longueur, int largeur, ArrayList<Monstre> population) {
		this.largeurY = largeur;
		this.longueurX = longueur;
		Case[][] grille = new Case[largeurY][longueurX];
		for (int i=0;i<largeurY;i++) {
			for (int j=0;j<longueurX;j++){
				if (i==0 || j==0 || i==largeurY-1 || j==longueur-1) {
					Mur parcelle = new Mur();
					grille[i][j] = parcelle;
				}
				else {
					Sol parcelle = new Sol();
					grille[i][j] = parcelle;
				}
			}
		}
		this.cases = grille;
		this.populationMonstre = population;
		for (Monstre monstre : population) {
			this.cases[monstre.positionY][monstre.positionX].ajoutPersonnage(monstre);
		}
		int[] positionTresor = new int[2];
		positionTresor[0] = longueur -2;
		positionTresor[1] = largeur -2;
		this.positionTresor = positionTresor;
		this.cases[longueur -2][largeur -2] = new Tresor(false, true);
	}
	public Labyrinthe(int longueur, int largeur, ArrayList<Monstre> population, Hero hero) {
		this.largeurY = largeur;
		this.longueurX = longueur;
		Case[][] grille = new Case[largeurY][longueurX];
		for (int i=0;i<largeurY;i++) {
			for (int j=0;j<longueurX;j++){
				if (i==0 || j==0 || i==largeurY-1 || j==longueur-1) {
					Mur parcelle = new Mur();
					grille[i][j] = parcelle;
				}
				else {
					Sol parcelle = new Sol();
					grille[i][j] = parcelle;
				}
			}
		}
		this.cases = grille;
		this.populationMonstre = population;
		for (Monstre monstre : population) {
			this.cases[monstre.positionY][monstre.positionX].ajoutPersonnage(monstre);
		}
		this.hero = hero;
		int[] positionTresor = new int[2];
		positionTresor[0] = longueur -2;
		positionTresor[1] = largeur -2;
		this.positionTresor = positionTresor;
		this.cases[longueur -2][largeur -2] = new Tresor(false, true);
	}
	
	public void deplacementTresor(int positionX, int positionY) {
		this.cases[positionTresor[0]][positionTresor[1]] = new Sol();
		this.cases[positionX][positionY] = new Tresor(false, true);
		positionTresor[0] = positionX;
		positionTresor[1] = positionY;
	}


}
