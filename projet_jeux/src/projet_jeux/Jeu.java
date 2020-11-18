package projet_jeux;

import java.util.ArrayList;

public class Jeu {
	Labyrinthe labyrinthe;
	Hero hero;
	ArrayList<Monstre> populationMonstre;
	
	public Jeu(Labyrinthe labyrinthe, Hero hero, ArrayList<Monstre> populationMonstre) {
		this.labyrinthe = labyrinthe;
		this.labyrinthe.cases[hero.positionY][hero.positionX].ajoutPersonnage(hero);
		this.hero = hero;
		this.populationMonstre = populationMonstre;
	}
	public Jeu(Labyrinthe labyrinthe, Hero hero) {
		this.labyrinthe = labyrinthe;
		this.labyrinthe.cases[hero.positionY][hero.positionX].ajoutPersonnage(hero);
		this.hero = hero;
		this.populationMonstre = new ArrayList<Monstre>();
	}
	public void resoudreCombat() {
		for (Monstre monstre : this.populationMonstre) {
			if ((this.hero.positionX == monstre.positionX) && (this.hero.positionY == monstre.positionY)) {
				this.hero.subirDegats();
			}
		}
	}
	public boolean testVictoire() {
		return ((this.hero.positionX == this.labyrinthe.positionTresor[0]) && (this.hero.positionY == this.labyrinthe.positionTresor[1]));
	}
	public void deplacementHero(String mouvement) {
		switch (mouvement) {
			case "z":
				if (this.labyrinthe.cases[this.hero.positionY -1][this.hero.positionX].franchissable) {
					this.labyrinthe.cases[this.hero.positionY][this.hero.positionX].retirerHero();
					this.hero.positionY -= 1;
					this.labyrinthe.cases[this.hero.positionY][this.hero.positionX].ajoutPersonnage(this.hero);
				}
				else {
					System.out.println("Le héro bloque contre un mur");
				}
			break;
			case "s":
				if (this.labyrinthe.cases[this.hero.positionY +1][this.hero.positionX].franchissable) {
					this.labyrinthe.cases[this.hero.positionY][this.hero.positionX].retirerHero();
					this.hero.positionY += 1;
					this.labyrinthe.cases[this.hero.positionY][this.hero.positionX].ajoutPersonnage(this.hero);
				}
				else {
					System.out.println("Le héro bloque contre un mur");
				}
			break;
			case "q":
				if (this.labyrinthe.cases[this.hero.positionY][this.hero.positionX -1].franchissable) {
					this.labyrinthe.cases[this.hero.positionY][this.hero.positionX].retirerHero();
					this.hero.positionX -= 1;
					this.labyrinthe.cases[this.hero.positionY][this.hero.positionX].ajoutPersonnage(this.hero);
				}
				else {
					System.out.println("Le héro bloque contre un mur");
				}
			break;
			case "d":
				if (this.labyrinthe.cases[this.hero.positionY][this.hero.positionX +1].franchissable) {
					this.labyrinthe.cases[this.hero.positionY][this.hero.positionX].retirerHero();
					this.hero.positionX += 1;
					this.labyrinthe.cases[this.hero.positionY][this.hero.positionX].ajoutPersonnage(this.hero);
				}
				else {
					System.out.println("Le héro bloque contre un mur");
				}
			break;
			default:
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
	public void peuplement(Monstre monstre) {
		this.labyrinthe.cases[monstre.positionY][monstre.positionX].ajoutPersonnage(monstre);
		this.populationMonstre.add(monstre);
	}
	public void peuplement(Hero hero) {
		this.labyrinthe.cases[hero.positionY][hero.positionX].ajoutPersonnage(hero);
		this.hero = hero;
	}
	public void randomPopulationMonstre(int nbMonstre) {
		this.populationMonstre = new ArrayList<Monstre>();
		while (nbMonstre >0) {
			int positionX = (int)(Math.random() * (this.labyrinthe.longueurX-2)) + 1;
			int positionY = (int)(Math.random() * (this.labyrinthe.largeurY-2)) + 1;
			Monstre monstre = new Monstre(1, positionX,positionY);
			if (!(this.labyrinthe.cases[positionX][positionY].testPresence())) {
				this.peuplement(monstre);
				nbMonstre = nbMonstre - 1;
			}
		}
	}
	public void affichage() {
		for (int i=0;i<this.labyrinthe.largeurY;i++) {
			System.out.println(" ");
			for (int j=0;j<this.labyrinthe.longueurX;j++) {
				if (this.labyrinthe.cases[i][j].testPresence()) {
					System.out.print(this.labyrinthe.cases[i][j].population.get(0).visuel+" ");
				}
				else {
					System.out.print(this.labyrinthe.cases[i][j].visuel+" ");
				}
			}
		}
	}

}
