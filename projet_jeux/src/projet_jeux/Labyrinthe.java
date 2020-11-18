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
	public void affichage() {
		for (int i=0;i<this.largeurY;i++) {
			System.out.println(" ");
			for (int j=0;j<this.longueurX;j++) {
				if (this.cases[i][j].testPresence()) {
					System.out.print(this.cases[i][j].population.get(0).visuel+" ");
				}
				else {
					System.out.print(this.cases[i][j].visuel+" ");
				}
			}
		}
	}
	public void peuplement(Monstre monstre) {
		this.cases[monstre.positionY][monstre.positionX].ajoutPersonnage(monstre);
		this.populationMonstre.add(monstre);
	}
	public void peuplement(Hero hero) {
		this.cases[hero.positionY][hero.positionX].ajoutPersonnage(hero);
		this.hero = hero;
	}
	public void randomPlacementMonstre(int nbMonstre) {
		while (nbMonstre >0) {
			int positionX = (int)(Math.random() * (longueurX-2)) + 1;
			int positionY = (int)(Math.random() * (largeurY-2)) + 1;
			Monstre monstre = new Monstre(1, positionX,positionY);
			if (!(this.cases[positionX][positionY].testPresence())) {
				this.peuplement(monstre);
				nbMonstre = nbMonstre - 1;
			}
		}
	}
	public void deplacement(Monstre monstre) {
		boolean recherche = true;
		int i = 0;
		while (recherche) {
			if (this.populationMonstre.get(i).equals(monstre)) {
			}
		}
	}
	public void deplacementHero(String mouvement) {
		switch (mouvement) {
			case "z":
				if (this.cases[this.hero.positionY -1][this.hero.positionX].franchissable) {
					this.cases[this.hero.positionY][this.hero.positionX].retirerHero();
					this.hero.positionY -= 1;
					this.cases[this.hero.positionY][this.hero.positionX].ajoutPersonnage(this.hero);
				}
				else {
					System.out.println("Le héro bloque contre un mur");
				}
			break;
			case "s":
				if (this.cases[this.hero.positionY +1][this.hero.positionX].franchissable) {
					this.cases[this.hero.positionY][this.hero.positionX].retirerHero();
					this.hero.positionY += 1;
					this.cases[this.hero.positionY][this.hero.positionX].ajoutPersonnage(this.hero);
				}
				else {
					System.out.println("Le héro bloque contre un mur");
				}
			break;
			case "q":
				if (this.cases[this.hero.positionY][this.hero.positionX -1].franchissable) {
					this.cases[this.hero.positionY][this.hero.positionX].retirerHero();
					this.hero.positionX -= 1;
					this.cases[this.hero.positionY][this.hero.positionX].ajoutPersonnage(this.hero);
				}
				else {
					System.out.println("Le héro bloque contre un mur");
				}
			break;
			case "d":
				if (this.cases[this.hero.positionY][this.hero.positionX +1].franchissable) {
					this.cases[this.hero.positionY][this.hero.positionX].retirerHero();
					this.hero.positionX += 1;
					this.cases[this.hero.positionY][this.hero.positionX].ajoutPersonnage(this.hero);
				}
				else {
					System.out.println("Le héro bloque contre un mur");
				}
			break;
			default:
		}
	}
	public void deplacementTresor(int positionX, int positionY) {
		this.cases[positionTresor[0]][positionTresor[1]] = new Sol();
		this.cases[positionX][positionY] = new Tresor(false, true);
		positionTresor[0] = positionX;
		positionTresor[1] = positionY;
	}
	public boolean testVictoire() {
		return ((this.hero.positionX == this.positionTresor[0]) && (this.hero.positionY == this.positionTresor[1]));
	}
	public void resoudreCombat() {
		for (Monstre monstre : this.populationMonstre) {
			if ((this.hero.positionX == monstre.positionX) && (this.hero.positionY == monstre.positionY)) {
				this.hero.subirDegats();
			}
		}
	}

}
