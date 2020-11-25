package jeu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Game {
	Labyrinthe labyrinthe;
	Heros hero;
	ArrayList<Monstre> populationMonstre;

	public Game(Labyrinthe labyrinthe, Heros hero, ArrayList<Monstre> populationMonstre) {
		this.labyrinthe = labyrinthe;
		this.labyrinthe.laby[hero.position_y][hero.position_x] = 2;
		this.hero = hero;
		this.populationMonstre = new ArrayList<Monstre>();
		for (Monstre monstre : populationMonstre) {
			this.labyrinthe.laby[monstre.position_y][monstre.position_x] = 3;
			this.populationMonstre.add(monstre);
		}
		this.populationMonstre = populationMonstre;
		
	}
	public Game(Labyrinthe labyrinthe, Heros hero) {
		this.labyrinthe = labyrinthe;
		this.labyrinthe.laby[hero.position_y][hero.position_x] = 2 ;
		this.hero = hero;
		this.populationMonstre = new ArrayList<Monstre>();
	}
	public Game(Heros hero) {
		Labyrinthe labyrinthe=new Labyrinthe();
		try {
		this.labyrinthe = labyrinthe;
		this.labyrinthe.laby[hero.position_y][hero.position_x] = 2 ;
		this.hero = hero;
		this.populationMonstre = new ArrayList<Monstre>();}
		catch(Exception e) {System.out.println("Le labyrinthe ne peut être créer");}
	}
	public Game(String nomFichier) {
		try {
			ArrayList<Monstre> populationMonstre = new ArrayList<Monstre>();
			int pointDeVie;
			int positionX;
			int positionY;
			boolean labyrintheDefini = false;
			boolean HeroDefini = false;
			Heros hero = new Heros();
			Labyrinthe labyrinthe = new Labyrinthe(2,2);
			BufferedReader buffer = new BufferedReader(new FileReader(nomFichier));
			String ligneCourante = new String();
			String natureObjet = new String();
			while ((ligneCourante = buffer.readLine()) != null) {
				String[] decoupe = ligneCourante.split(" ");
				natureObjet = decoupe[0].toLowerCase();
				switch (natureObjet) {
					case "labyrinthe":
						int longueur = Integer.parseInt(decoupe[1]);
						int largeur = Integer.parseInt(decoupe[2]);
						labyrinthe = new Labyrinthe(longueur, largeur);
						labyrintheDefini = true;
					break;
					case "heros":
						pointDeVie = Integer.parseInt(decoupe[1]);
						positionX = Integer.parseInt(decoupe[2]);
						positionY = Integer.parseInt(decoupe[3]);
						hero = new Heros(pointDeVie, positionX, positionY);
						HeroDefini = true;
					break;
					case "monstre":
						pointDeVie = Integer.parseInt(decoupe[1]);
						positionX = Integer.parseInt(decoupe[2]);
						positionY = Integer.parseInt(decoupe[3]);
						populationMonstre.add(new Monstre(pointDeVie, positionX, positionY));
					break;
					case "mur":
						positionX = Integer.parseInt(decoupe[1]);
						positionY = Integer.parseInt(decoupe[2]);
	//					constructeur de Mur à définir
						labyrinthe.laby[positionX][positionY] = 1;
						break;
					case "sol":
						positionX = Integer.parseInt(decoupe[1]);
						positionY = Integer.parseInt(decoupe[2]);
	//					constructeur de Mur à définir
					break;
					case "tresor":
						positionX = Integer.parseInt(decoupe[1]);
						positionY = Integer.parseInt(decoupe[2]);
	//					constructeur de Mur à définir
					break;
					case "piege":
						positionX = Integer.parseInt(decoupe[1]);
						positionY = Integer.parseInt(decoupe[2]);
	//					constructeur de Mur à définir
					break;
					case "casemagique":
						positionX = Integer.parseInt(decoupe[1]);
						positionY = Integer.parseInt(decoupe[2]);
	//					constructeur de Mur à définir
					break;
					case "passage":
						positionX = Integer.parseInt(decoupe[1]);
						positionY = Integer.parseInt(decoupe[2]);
	//					constructeur de Mur à définir
					break;
					default :	
				}
			}
			buffer.close();
			if (HeroDefini) {
				if (labyrintheDefini) {
					this.labyrinthe = labyrinthe;
					this.labyrinthe.laby[hero.position_y][hero.position_x] = 2;	
					this.hero = hero;	
					this.populationMonstre = new ArrayList<Monstre>();
					for (Monstre monstre : populationMonstre) {
						if (this.labyrinthe.laby[monstre.position_y][monstre.position_x] == 0) {
							this.labyrinthe.laby[monstre.position_y][monstre.position_x] = 3;
							this.populationMonstre.add(monstre);
						}
						else
							throw new Exception("Erreur : 2 personnages sont sur la même case");
					}
					this.populationMonstre = populationMonstre;							
				}
				else {
					throw new Exception("Erreur : Labyrinthe mal défini dans le fichier");
				}
			}
			else {
				throw new Exception("Erreur : Hero mal défini dans le fichier");
			}
		}
		catch(Exception e)
			{System.out.println("Erreur lors de la lecture du fichier");
		}
		
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