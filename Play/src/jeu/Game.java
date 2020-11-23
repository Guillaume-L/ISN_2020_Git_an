package jeu;

import java.util.ArrayList;

public class Game {
	Labyrinthe labyrinthe;
	Heros hero;
	ArrayList<Monstre> populationMonstre;

	public Game(Labyrinthe labyrinthe, Heros hero, ArrayList<Monstre> populationMonstre) {
		this.labyrinthe = labyrinthe;
		this.labyrinthe.laby[hero.position_y][hero.position_x] = 2;
		this.hero = hero;
		this.populationMonstre = populationMonstre;
		
	}
	public Game(Labyrinthe labyrinthe, Heros hero) {
		this.labyrinthe = labyrinthe;
		this.labyrinthe.laby[hero.position_y][hero.position_x] = 2 ;
		this.hero = hero;
		this.populationMonstre = new ArrayList<Monstre>();
	}
	public Game(Heros hero)
	{Labyrinthe labyrinthe=new Labyrinthe();
	try {
	this.labyrinthe = labyrinthe;
	this.labyrinthe.laby[hero.position_y][hero.position_x] = 2 ;
	this.hero = hero;
	this.populationMonstre = new ArrayList<Monstre>();}
	catch(Exception e) {System.out.println("Le labyrinthe ne peut être créer");}
	
	}
	public void deplacementHero(String mouvement) {
		switch (mouvement) {
			case "z":
				if (this.labyrinthe.laby[this.hero.position_y - 1][this.hero.position_x] == 0) {
					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x] = 0;
					this.hero.position_y -= 1;
					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x] = 2;
				}
				else {
					if (this.labyrinthe.laby[this.hero.position_y - 1][this.hero.position_x] == 3) {
						System.out.println("Défaite : héro tué par un monstre");
						this.hero.point_de_vie = 0;
					}
					else {
					System.out.println("Le héro bloque contre un mur");
					}
				}
			break;
			case "s":
				if (this.labyrinthe.laby[this.hero.position_y + 1][this.hero.position_x] == 0) {
					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x] = 0;
					this.hero.position_y += 1;
					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x] = 2;
				}
				else {
					if (this.labyrinthe.laby[this.hero.position_y + 1][this.hero.position_x] == 3) {
						System.out.println("Défaite : héro tué par un monstre");
						this.hero.point_de_vie -= 1;
					}
					else {
					System.out.println("Le héro bloque contre un mur");
					}
				}
			break;
			case "q":
				if (this.labyrinthe.laby[this.hero.position_y][this.hero.position_x -1] == 0) {
					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x]= 0;
					this.hero.position_x -= 1;
					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x] = 2;
				}
				else {
					if (this.labyrinthe.laby[this.hero.position_y][this.hero.position_x - 1] == 3) {
						System.out.println("Défaite : héro tué par un monstre");
						this.hero.point_de_vie -= 1;
					}
					else {
					System.out.println("Le héro bloque contre un mur");
					}
				}
			break;
			case "d":
				if (this.labyrinthe.laby[this.hero.position_y][this.hero.position_x + 1] == 0) {
					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x]= 0;
					this.hero.position_x += 1;
					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x] = 2;
				}
				else {
					if (this.labyrinthe.laby[this.hero.position_y][this.hero.position_x + 1] == 3) {
						System.out.println("Défaite : héro tué par un monstre");
						this.hero.point_de_vie -= 1;
					}
					else {
					System.out.println("Le héro bloque contre un mur");
					}
				}
			break;
			default:
		}
	}
	public void peuplement(Monstre monstre) {
		this.labyrinthe.laby[monstre.position_y][monstre.position_x] = 3;
		this.populationMonstre.add(monstre);
	}
	public void randomPopulationMonstre(int nbMonstre) {
		this.populationMonstre = new ArrayList<Monstre>();
		while (nbMonstre >0) {
			int positionX = (int)(Math.random() * (this.labyrinthe.longueur-2)) + 1;
			int positionY = (int)(Math.random() * (this.labyrinthe.largeur-2)) + 1;
			Monstre monstre = new Monstre(1, positionX,positionY);
			if (this.labyrinthe.laby[positionY][positionX] == 0) {
				this.peuplement(monstre);
				nbMonstre = nbMonstre - 1;
			}
		}
	}
	public void affichage() {
		for (int i=0;i<this.labyrinthe.laby.length;i++) {
			System.out.println(" ");
			for (int j=0;j<this.labyrinthe.laby[i].length;j++) {
				System.out.print(this.labyrinthe.laby[i][j]);
			}
		}
	}
	public void resoudreCombat() {
		for (Monstre monstre : this.populationMonstre) {
			if ((this.hero.position_x == monstre.position_x) && (this.hero.position_y == monstre.position_y)) {
				this.hero.point_de_vie = 0;
			}
		}
	}
}