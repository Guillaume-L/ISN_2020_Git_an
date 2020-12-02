package jeu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game {
	Labyrinthe labyrinthe;
	Heros hero;
	ArrayList<Monstre> populationMonstre;
	Moniteur moniteur;
	String status;

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
		this.moniteur = new Moniteur(labyrinthe);
		this.moniteur.addKeyListener( new KeyAdapter() {
			
			public void keyTyped( KeyEvent e) {
				Game.this.deplacementHero(String.valueOf(e.getKeyChar()).toLowerCase());
				Game.this.affichage();
			}
		});
		this.status = "";
	}
	
	public Game(Labyrinthe labyrinthe, Heros hero) {
		
		this.labyrinthe = labyrinthe;
		this.labyrinthe.laby[hero.position_y][hero.position_x].population.add(hero) ;
		this.hero = hero;
		this.populationMonstre = new ArrayList<Monstre>();
		this.status = "";
		this.moniteur = new Moniteur(labyrinthe);
		this.moniteur.addKeyListener( new KeyAdapter() {
			
			public void keyTyped( KeyEvent e) {
				Game.this.deplacementHero(String.valueOf(e.getKeyChar()));
				Game.this.affichage();
			}
		});
	}

	public Game() throws Exception {
		boolean err=false;
		this.status = "";
		Labyrinthe labyrinthe = new Labyrinthe();
		this.labyrinthe = labyrinthe;
		int compteur=0;
		
		for (int i=0;i<labyrinthe.laby.length;i++) {
			for (int j=0;j<labyrinthe.laby[i].length;j++) {
				if (labyrinthe.laby[i][j].visuel.equals("1")) {
					compteur=compteur+1;
					}
				}
			}
		int place_dispo=labyrinthe.longueur*labyrinthe.largeur-compteur;
		if (place_dispo==0) {
			System.out.println("pas de places dans le labyrinthe");}
		else {
			Scanner myObj = new Scanner(System.in); 
			
			String absi="";
			String ordi="";
			
			int abs=0;
			int ord=0;
		while(!err) {
			System.out.println("Abscisse du héros? ");
			absi= myObj.nextLine();
		
			try {abs=Integer.parseInt(absi);
			
			 System.out.println("ordonnée du héros ? ");
			 ordi = myObj.nextLine();
			 ord=Integer.parseInt(ordi);
			
		
		if(labyrinthe.laby[ord][abs].visuel.equals("-")) {err=true;}
		else {System.out.println("position occupée par un mur");}}
		catch(Exception e) {System.out.println("paramètre2 non valide");}}
		err=false;
		place_dispo=place_dispo-1;
		
		Heros hero=new Heros(1,abs,ord);
		String nbi;
		int nb_monstres=0;
		while(!err) {
			System.out.println("Nombre de monstres? ");
			nbi= myObj.nextLine();
		
			try {nb_monstres=Integer.parseInt(nbi);
			if(nb_monstres>-1) {if(place_dispo-2-nb_monstres<0) {System.out.println("pas assez de places");}
			else {err=true;}}else{System.out.println("il ne peut pas y avoir un nombre négatifs de monstres");}}
			catch(Exception e) {System.out.println("paramètre3 non valide");}
			}
			
			
		try {
		
		this.labyrinthe.laby[hero.position_y][hero.position_x].population.add(hero);
		this.hero = hero;
		
		this.populationMonstre = new ArrayList<Monstre>();
		this.randomPopulationMonstre(nb_monstres);
		place_dispo=place_dispo-nb_monstres;
		this.affichage();}
		catch(Exception e) {
			System.out.println("Le labyrinthe ne peut être créer");
			throw e;
		}
		
		err=false;
		
		while(!err) {
			System.out.println("Abscisse de la fin? ");
			absi= myObj.nextLine();
		
			try {abs=Integer.parseInt(absi);
			
			 System.out.println("ordonnée de la fin ? ");
			 ordi = myObj.nextLine();
			 ord=Integer.parseInt(ordi);
			
			 if(!labyrinthe.laby[ord][abs].visuel.equals("1")&& labyrinthe.laby[ord][abs].visible) {
			 if(!labyrinthe.laby[ord][abs].testPresence()) {
				 labyrinthe.laby[ord][abs]=new Tresor(false);
						 err=true;
			 }
			 else {System.out.println("case occupée");	}}
			 else {System.out.println("case mur");}}
			catch(Exception e) {System.out.println("paramètre4 non valide");}
	}
	place_dispo--;
	this.affichage();
	err=false;
	if(place_dispo>0) {while(!err) {
		System.out.println("Abscisse de magique? ");
		absi= myObj.nextLine();
	
		try {abs=Integer.parseInt(absi);
		
		 System.out.println("ordonnée de magique ? ");
		 ordi = myObj.nextLine();
		 ord=Integer.parseInt(ordi);
		 if(!labyrinthe.laby[ord][abs].visuel.equals("1")&& labyrinthe.laby[ord][abs].visible) {
		 if(!labyrinthe.laby[ord][abs].testPresence()) {
			 labyrinthe.laby[ord][abs]=new Magique();
					 err=true;
		 }
		 else {System.out.println("case occupée");	}}
		 else {System.out.println("case prise");}}
		catch(Exception e) {System.out.println("paramètre5 non valide");}
		
	}
		
		}
	place_dispo--;
	this.affichage();
	err=false;
	if(place_dispo>0) {while(!err) {
		System.out.println("Abscisse de piège? ");
		absi= myObj.nextLine();
	
		try {abs=Integer.parseInt(absi);
		
		 System.out.println("ordonnée de piège ? ");
		 ordi = myObj.nextLine();
		 ord=Integer.parseInt(ordi);
		 if(labyrinthe.laby[ord][abs].visuel.equals("-") && !labyrinthe.laby[ord][abs].testPresence() && labyrinthe.laby[ord][abs].visible)
		  {
			 labyrinthe.laby[ord][abs]=new Piege();
					 err=true;
		 }
		else {System.out.println("case prise");}}
		catch(Exception e) {System.out.println("paramètre6 non valide");}
		
	}}
place_dispo--;
this.affichage();
	err=false;
	int abs_sortie;
	int ord_sortie;
	if(place_dispo>1) {while(!err) {
		System.out.println("Abscisse de passage? ");
		absi= myObj.nextLine();
	
		try {abs=Integer.parseInt(absi);
		
		 System.out.println("ordonnée de passage ? ");
		 ordi = myObj.nextLine();
		 ord=Integer.parseInt(ordi);
		 if(labyrinthe.laby[ord][abs].visuel.equals("-") && !labyrinthe.laby[ord][abs].testPresence() && labyrinthe.laby[ord][abs].visible)
		  {System.out.println("Abscisse de sortie du passage? ");
			absi= myObj.nextLine();
			try {abs_sortie=Integer.parseInt(absi);
			
			 System.out.println("ordonnée de sortie passage ? ");
			 ordi = myObj.nextLine();
			 ord_sortie=Integer.parseInt(ordi);
			if (abs!=abs_sortie || ord!=ord_sortie) {
				if(labyrinthe.laby[ord_sortie][abs_sortie].visuel.equals("-") && !labyrinthe.laby[ord_sortie][abs_sortie].testPresence() && labyrinthe.laby[ord_sortie][abs_sortie].visible)
				{labyrinthe.laby[ord][abs]=new Passage(abs_sortie,ord_sortie);
				 err=true;
			}
				else	{System.out.println("case prise ");}	 
		 }
		else {System.out.println("case prise par entrée passage");}}
		catch(Exception e) {System.out.println("paramètre7 non valide");}
		
	}else {System.out.println("coordonnées invalides");}
		
		
	}catch(Exception e) {System.out.println("paramètre8 non valide");}
	}
	}
	}
	System.out.println("le jeu commence");}
	
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
		this.moniteur = new Moniteur(labyrinthe);
		this.moniteur.addKeyListener( new KeyAdapter() {
			
			public void keyTyped( KeyEvent e) {
				Game.this.deplacementHero(String.valueOf(e.getKeyChar()));
				Game.this.affichage();
			}
		});
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
	public void peuplement(Monstre monstre) throws Exception {
		int x = monstre.position_x;
		int y = monstre.position_y;
		if (y >= 0 && y < this.labyrinthe.largeur && x >= 0 && x < this.labyrinthe.longueur) {
//			System.out.print(this.labyrinthe.laby[y][x].testPresence());
//			System.out.print(this.labyrinthe.laby[y][x].visuel.equalsIgnoreCase("1"));
			if (!(this.labyrinthe.laby[y][x].testPresence() || (this.labyrinthe.laby[y][x].visuel.equalsIgnoreCase("1")))) {
				System.out.println("here");
				this.labyrinthe.laby[monstre.position_y][monstre.position_x].population.add(monstre);
				this.populationMonstre.add(monstre);
			}
			else
				throw new Exception("Vous ne pouvez pas placer un monstre sur un mur ou un autre personnage");
		}
			else
				throw new Exception("Coordonnées du monstre à placer invalides");
	}
	public void randomPopulationMonstre(int nbMonstre) throws Exception {
		this.populationMonstre = new ArrayList<Monstre>();
		ArrayList<int[]> caseDisponible = new ArrayList<int[]>();
		Random random = new Random();
		for (int i = 0; i < labyrinthe.laby.length; i++) {
			for (int j = 0; j < labyrinthe.laby[i].length; j++) {
				if (!(this.labyrinthe.laby[i][j].testPresence() || (this.labyrinthe.laby[i][j].visuel.equalsIgnoreCase("1")))) {
					int[] position = new int[2];
					position[0] = j;
					position[1] = i;
					caseDisponible.add(position);
				}
			}
		}
		int[] position = new int[2];
		if (caseDisponible.size() > nbMonstre) {
			for (int i = 0; i < nbMonstre; i++) {
				int j = random.nextInt(caseDisponible.size());
				position = caseDisponible.get(j);
				caseDisponible.remove(j);
				System.out.print(position[0]);
				System.out.println(position[1]);
				Monstre monstre = new Monstre(1, position[0],position[1]);
				this.peuplement(monstre);
			}
		}
		else
			throw new Exception("Pas assez de place disponible pour placer les monstres.");
	}
	public void affichage() {
		this.moniteur.affichage(this.labyrinthe, this.hero, this.status);
	}
	public void resoudreCombat(int positionX, int positionY) {
		if (this.labyrinthe.laby[positionY][positionX].population.size() > 0 ) {
			this.hero.subirDegat();
			if (!this.hero.testVivant()) {
				this.labyrinthe.laby[positionY][positionX].image = "killByMonster";
				status = "Défaite, le héro a été tué par un monstre";
			}
			else
				this.status = "Monstre tué, mais le héro a perdu 1 point de vie lors du combat";
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
					if (!this.hero.testVivant()) {
						this.labyrinthe.laby[positionY][positionX].image = "killByPiege";
						this.status = "Défaite, le héro a été tué par un piège";
					}
				break;
				case "magique":
					this.labyrinthe.revelerTresor();
					this.status = "La trésor est désormais visible";
				break;
				case "passage":
					this.labyrinthe.laby[positionY][positionX].population.add(this.hero);
					long pauseAvantTeleportation = 1000; // exprimer en milliseconde
					this.affichage();
					try {
						Thread.sleep(pauseAvantTeleportation);// donc une pause de 2 sec.
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
					this.status = "Victoire : le héro a récupéré le trésor";
				break;
				default:
		}
	}

}