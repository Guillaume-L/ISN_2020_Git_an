package jeu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	Labyrinthe labyrinthe;
	Heros hero;
	ArrayList<Monstre> populationMonstre;

	public Game(Labyrinthe labyrinthe, Heros hero, ArrayList<Monstre> populationMonstre) {
		this.labyrinthe = labyrinthe;
		this.labyrinthe.laby[hero.position_y][hero.position_x].population.add(hero);
		this.hero = hero;
		this.populationMonstre = new ArrayList<Monstre>();
		for (Monstre monstre : populationMonstre) {
			this.labyrinthe.laby[monstre.position_y][monstre.position_x].population.add(monstre);
			this.populationMonstre.add(monstre);
		}
		this.populationMonstre = populationMonstre;
	}
	
	public Game(Labyrinthe labyrinthe, Heros hero) {
		this.labyrinthe = labyrinthe;
		this.labyrinthe.laby[hero.position_y][hero.position_x].population.add(hero) ;
		this.hero = hero;
		this.populationMonstre = new ArrayList<Monstre>();
	}
	
	public Game(Heros hero, Scanner scan) {
		Labyrinthe labyrinthe = new Labyrinthe(scan);
		try {
			this.labyrinthe = labyrinthe;
			this.labyrinthe.laby[hero.position_y][hero.position_x].population.add(hero);
			this.hero = hero;
			this.populationMonstre = new ArrayList<Monstre>();
		}
		catch(Exception e) {
			System.out.println("Le labyrinthe ne peut être créer");
		}
	}
	
	public Game(String nomFichier) throws Exception {
		try {
			ArrayList<Monstre> populationMonstre = new ArrayList<Monstre>();
			int pointDeVie;
			int positionX;
			int positionY;
			int positionArriveeX;
			int positionArriveeY;
			boolean visible;
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

						labyrinthe.laby[positionY][positionX] = new Mur();
						break;
					case "sol":
						positionX = Integer.parseInt(decoupe[1]);
						positionY = Integer.parseInt(decoupe[2]);
						labyrinthe.laby[positionY][positionX] = new Sol();
					break;
					case "tresor":
						positionX = Integer.parseInt(decoupe[1]);
						positionY = Integer.parseInt(decoupe[2]);
						visible = Boolean.parseBoolean(decoupe[3]);
						labyrinthe.laby[positionY][positionX] = new Tresor(visible);
					break;
					case "piege":
						positionX = Integer.parseInt(decoupe[1]);
						positionY = Integer.parseInt(decoupe[2]);
						labyrinthe.laby[positionY][positionX] = new Piege();
					break;
					case "magique":
						positionX = Integer.parseInt(decoupe[1]);
						positionY = Integer.parseInt(decoupe[2]);
						labyrinthe.laby[positionY][positionX] = new Magique();
					break;
					case "passage":
						positionX = Integer.parseInt(decoupe[1]);
						positionY = Integer.parseInt(decoupe[2]);
						positionArriveeX = Integer.parseInt(decoupe[3]);
						positionArriveeY = Integer.parseInt(decoupe[4]);
						labyrinthe.laby[positionY][positionX] = new Passage(positionArriveeX, positionArriveeY);
					break;
					default :
				}
			}
			buffer.close();
			if (HeroDefini) {
				if (labyrintheDefini) {
					this.labyrinthe = labyrinthe;
					if (!(this.labyrinthe.laby[hero.position_y][hero.position_x] instanceof Mur)) {
						this.labyrinthe.laby[hero.position_y][hero.position_x].population.add(hero);
						this.hero = hero;
					}
					else
						throw new Exception("Erreur : hero est dans un mur");
					this.populationMonstre = new ArrayList<Monstre>();
					for (Monstre monstre : populationMonstre) {
						if (this.labyrinthe.laby[monstre.position_y][monstre.position_x] instanceof Mur)
							throw new Exception("Erreur : 1 personnage est dans un mur");
						else  if (this.labyrinthe.laby[monstre.position_y][monstre.position_x].testPresence())
							throw new Exception("Erreur : 2 personnages sont initialement placés sur la même case.");
						else {
							this.labyrinthe.laby[monstre.position_y][monstre.position_x].population.add(monstre);
							this.populationMonstre.add(monstre);
						}
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
		catch(Exception e) {
			System.out.println("Erreur lors de la lecture du fichier");
			throw e;
		}
		
	}
	public void deplacementHero(String mouvement) {
		switch (mouvement) {
			case "z":
				if (this.labyrinthe.laby[this.hero.position_y - 1][this.hero.position_x] instanceof Mur)
					System.out.println("Le héro bloque contre un mur");
				else {
					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x].population.clear();
					this.hero.position_y -= 1;
					this.resoudreCombat(this.hero.position_x, this.hero.position_y);
					this.declencherEffetCase(this.hero.position_x, this.hero.position_y);
					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x].population.add(this.hero);
				}
			break;
			case "s":
				if (this.labyrinthe.laby[this.hero.position_y + 1][this.hero.position_x] instanceof Mur)
					System.out.println("Le héro bloque contre un mur");
				else {
					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x].population.clear();
					this.hero.position_y += 1;
					this.resoudreCombat(this.hero.position_x, this.hero.position_y);
					this.declencherEffetCase(this.hero.position_x, this.hero.position_y);
					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x].population.add(this.hero);
				}
			break;
			case "q":
				if (this.labyrinthe.laby[this.hero.position_y][this.hero.position_x - 1] instanceof Mur)
					System.out.println("Le héro bloque contre un mur");
				else {
					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x].population.clear();
					this.hero.position_x -= 1;
					this.resoudreCombat(this.hero.position_x, this.hero.position_y);
					this.declencherEffetCase(this.hero.position_x, this.hero.position_y);
					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x].population.add(this.hero);
				}
			break;
			case "d":
				if (this.labyrinthe.laby[this.hero.position_y][this.hero.position_x + 1] instanceof Mur)
					System.out.println("Le héro bloque contre un mur");
				else {
					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x].population.clear();
					this.hero.position_x += 1;
					this.resoudreCombat(this.hero.position_x, this.hero.position_y);
					this.declencherEffetCase(this.hero.position_x, this.hero.position_y);
					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x].population.add(this.hero);
				}
			break;
			default:
		}
	}
	public void peuplement(Monstre monstre) {
		this.labyrinthe.laby[monstre.position_y][monstre.position_x].population.add(monstre);
		this.populationMonstre.add(monstre);
	}
	public void randomPopulationMonstre(int nbMonstre) {
		this.populationMonstre = new ArrayList<Monstre>();
		while (nbMonstre >0) {
			int positionX = (int)(Math.random() * (this.labyrinthe.longueur-2)) + 1;
			int positionY = (int)(Math.random() * (this.labyrinthe.largeur-2)) + 1;
			Monstre monstre = new Monstre(1, positionX,positionY);
			if (!(this.labyrinthe.laby[positionY][positionX].testPresence())) {
				this.peuplement(monstre);
				nbMonstre = nbMonstre - 1;
			}
		}
	}
	public void affichage() {
		for (int i=0;i<this.labyrinthe.laby.length;i++) {
			System.out.println(" ");
			for (int j=0;j<this.labyrinthe.laby[i].length;j++) {
				if (this.labyrinthe.laby[i][j].testPresence()) {
					for (Personnage personnage : this.labyrinthe.laby[i][j].population) {
						if (personnage.testVivant())
							System.out.print(personnage.visuel);
					}
				}
				else if (this.labyrinthe.laby[i][j].visible) {
					System.out.print(this.labyrinthe.laby[i][j].visuel);
				}
				else {
					Sol sol = new Sol();
					System.out.print(sol.visuel);
				}
			}
		}
		System.out.println(" ");
		System.out.println("Le héro a "+ this.hero.point_de_vie + " point(s) de vie");
	}
	public void resoudreCombat(int positionX, int positionY) {
		if (this.labyrinthe.laby[positionY][positionX].population.size() > 0 ) {
			this.hero.subirDegat();
			System.out.print("Monstre tué, mais le héro a perdu 1 point de vie lors du combat");
			for (int i = 0; i < this.populationMonstre.size(); i++) {
				if ((this.populationMonstre.get(i).position_x == positionX) && (this.populationMonstre.get(i).position_y == positionY)) {
					this.populationMonstre.get(i).subirDegat();
				}
			}
			for (int i = 0; i < this.labyrinthe.laby[positionY][positionX].population.size(); i++) {
				this.labyrinthe.laby[positionY][positionX].population.get(i).subirDegat();
			}
		}
	}
	public void declencherEffetCase(int positionX, int positionY) {
			String effetCase = this.labyrinthe.laby[positionY][positionX].declenchement();
			String[] decoupeEffetCase = effetCase.split(" ");
			switch (decoupeEffetCase[0]) {
				case "piege":
					this.hero.subirDegat();
				break;
				case "magique":
					this.labyrinthe.revelerTresor();
				break;
				case "passage":
					this.labyrinthe.laby[positionY][positionX].population.add(this.hero);
					long pauseAvantTeleportation = 500; // exprimer en milliseconde
					this.affichage();
					try {
						Thread.sleep(pauseAvantTeleportation);// donc une pause de 0,2 sec.
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					} 
					int positionArriveeX = Integer.parseInt(decoupeEffetCase[1]);
					int positionArriveeY = Integer.parseInt(decoupeEffetCase[2]);
					this.labyrinthe.laby[positionY][positionX].population.clear();
					this.hero.position_x = positionArriveeX;
					this.hero.position_y = positionArriveeY;
					this.resoudreCombat(this.hero.position_x, this.hero.position_y);
					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x].declenchement();
				break;
				case "victoire":
					System.out.println("Victoire : le héro a récupéré le trésor");
				break;
				default:
		}
	}

}