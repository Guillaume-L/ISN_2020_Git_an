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
	public Game() {
		boolean err=false;
	
		Labyrinthe labyrinthe = new Labyrinthe();
		this.labyrinthe = labyrinthe;
		int compteur=0;
		this.affichage();
		for (int i=0;i<labyrinthe.laby.length;i++) {
			for (int j=0;j<labyrinthe.laby[i].length;j++)
			{if (labyrinthe.laby[i][j].visuel.equals("1")) {compteur=compteur+1;}}}
		int place_dispo=labyrinthe.longueur*labyrinthe.largeur-compteur;
		if (place_dispo==0) {System.out.println("pas de places dans le labyrinthe");}
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
			
		
		if(labyrinthe.laby[abs][ord].visuel.equals("-")) {err=true;}
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
			if(nb_monstres>-1) {if(place_dispo-1-nb_monstres<0) {System.out.println("pas assez de places");}
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
		catch(Exception e) {System.out.println("Le labyrinthe ne peut être créer");}
		
		err=false;
		
		while(!err) {
			System.out.println("Abscisse de la fin? ");
			absi= myObj.nextLine();
		
			try {abs=Integer.parseInt(absi);
			
			 System.out.println("ordonnée de la fin ? ");
			 ordi = myObj.nextLine();
			 ord=Integer.parseInt(ordi);
			
			 if(!labyrinthe.laby[abs][ord].visuel.equals("1")&& labyrinthe.laby[abs][ord].visible) {
			 if(!labyrinthe.laby[abs][ord].testPresence()) {
				 labyrinthe.laby[abs][ord]=new Tresor();
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
		 if(!labyrinthe.laby[abs][ord].visuel.equals("1")&& labyrinthe.laby[abs][ord].visible) {
		 if(!labyrinthe.laby[abs][ord].testPresence()) {
			 labyrinthe.laby[abs][ord]=new Magique();
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
		 if(labyrinthe.laby[abs][ord].visuel.equals("-") && !labyrinthe.laby[abs][ord].testPresence() && labyrinthe.laby[abs][ord].visible)
		  {
			 labyrinthe.laby[abs][ord]=new Piege();
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
		 if(labyrinthe.laby[abs][ord].visuel.equals("-") && !labyrinthe.laby[abs][ord].testPresence() && labyrinthe.laby[abs][ord].visible)
		  {System.out.println("Abscisse de sortie du passage? ");
			absi= myObj.nextLine();
			try {abs_sortie=Integer.parseInt(absi);
			
			 System.out.println("ordonnée de sortie passage ? ");
			 ordi = myObj.nextLine();
			 ord_sortie=Integer.parseInt(ordi);
			if (abs!=abs_sortie || ord!=ord_sortie) {
				if(labyrinthe.laby[abs_sortie][ord_sortie].visuel.equals("-") && !labyrinthe.laby[abs_sortie][ord_sortie].testPresence() && labyrinthe.laby[abs_sortie][ord_sortie].visible)
				{labyrinthe.laby[abs][ord]=new Passage(abs_sortie,ord_sortie);
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
	}
//	public Game(String nomFichier) {
//		try {
//			ArrayList<Monstre> populationMonstre = new ArrayList<Monstre>();
//			int pointDeVie;
//			int positionX;
//			int positionY;
//			boolean labyrintheDefini = false;
//			boolean HeroDefini = false;
//			Heros hero = new Heros();
//			Labyrinthe labyrinthe = new Labyrinthe(2,2);
//			BufferedReader buffer = new BufferedReader(new FileReader(nomFichier));
//			String ligneCourante = new String();
//			String natureObjet = new String();
//			while ((ligneCourante = buffer.readLine()) != null) {
//				String[] decoupe = ligneCourante.split(" ");
//				natureObjet = decoupe[0].toLowerCase();
//				switch (natureObjet) {
//					case "labyrinthe":
//						int longueur = Integer.parseInt(decoupe[1]);
//						int largeur = Integer.parseInt(decoupe[2]);
//						labyrinthe = new Labyrinthe(longueur, largeur);
//						labyrintheDefini = true;
//					break;
//					case "heros":
//						pointDeVie = Integer.parseInt(decoupe[1]);
//						positionX = Integer.parseInt(decoupe[2]);
//						positionY = Integer.parseInt(decoupe[3]);
//						hero = new Heros(pointDeVie, positionX, positionY);
//						HeroDefini = true;
//					break;
//					case "monstre":
//						pointDeVie = Integer.parseInt(decoupe[1]);
//						positionX = Integer.parseInt(decoupe[2]);
//						positionY = Integer.parseInt(decoupe[3]);
//						populationMonstre.add(new Monstre(pointDeVie, positionX, positionY));
//					break;
//					case "mur":
//						positionX = Integer.parseInt(decoupe[1]);
//						positionY = Integer.parseInt(decoupe[2]);
//	//					constructeur de Mur à définir
//						labyrinthe.laby[positionY][positionX] = new Mur();
//						break;
//					case "sol":
//						positionX = Integer.parseInt(decoupe[1]);
//						positionY = Integer.parseInt(decoupe[2]);
//	//					constructeur de Mur à définir
//					break;
//					case "tresor":
//						positionX = Integer.parseInt(decoupe[1]);
//						positionY = Integer.parseInt(decoupe[2]);
//	//					constructeur de Mur à définir
//					break;
//					case "piege":
//						positionX = Integer.parseInt(decoupe[1]);
//						positionY = Integer.parseInt(decoupe[2]);
//	//					constructeur de Mur à définir
//					break;
//					case "casemagique":
//						positionX = Integer.parseInt(decoupe[1]);
//						positionY = Integer.parseInt(decoupe[2]);
//	//					constructeur de Mur à définir
//					break;
//					case "passage":
//						positionX = Integer.parseInt(decoupe[1]);
//						positionY = Integer.parseInt(decoupe[2]);
//	//					constructeur de Mur à définir
//					break;
//					default :
//				}
//			}
//			buffer.close();
//			if (HeroDefini) {
//				if (labyrintheDefini) {
//					this.labyrinthe = labyrinthe;
//					if (this.labyrinthe.laby[hero.position_y][hero.position_x] == 0)
//						this.labyrinthe.laby[hero.position_y][hero.position_x] = 2;
//					else
//						throw new Exception("Erreur : hero est dans un mur");
//					this.hero = hero;	
//					this.populationMonstre = new ArrayList<Monstre>();
//					for (Monstre monstre : populationMonstre) {
//						if (this.labyrinthe.laby[monstre.position_y][monstre.position_x] == 0) {
//							this.labyrinthe.laby[monstre.position_y][monstre.position_x] = 3;
//							this.populationMonstre.add(monstre);
//						}
//						else
//							throw new Exception("Erreur : 2 personnages sont sur la même case ou 1 personnage est dans un mur");
//					}
//					this.populationMonstre = populationMonstre;							
//				}
//				else {
//					throw new Exception("Erreur : Labyrinthe mal défini dans le fichier");
//				}
//			}
//			else {
//				throw new Exception("Erreur : Hero mal défini dans le fichier");
//			}
//		}
//		catch(Exception e)
//			{System.out.println("Erreur lors de la lecture du fichier");
//		}
//		
//	}
//	public void deplacementHero(String mouvement) {
//		switch (mouvement) {
//			case "z":
//				if (this.labyrinthe.laby[this.hero.position_y - 1][this.hero.position_x] == 0) {
//					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x] = 0;
//					this.hero.position_y -= 1;
//					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x] = 2;
//				}
//				else {
//					if (this.labyrinthe.laby[this.hero.position_y - 1][this.hero.position_x] == 3) {
//						System.out.println("Défaite : héro tué par un monstre");
//						this.hero.point_de_vie = 0;
//					}
//					else {
//					System.out.println("Le héro bloque contre un mur");
//					}
//				}
//			break;
//			case "s":
//				if (this.labyrinthe.laby[this.hero.position_y + 1][this.hero.position_x] == 0) {
//					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x] = 0;
//					this.hero.position_y += 1;
//					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x] = 2;
//				}
//				else {
//					if (this.labyrinthe.laby[this.hero.position_y + 1][this.hero.position_x] == 3) {
//						System.out.println("Défaite : héro tué par un monstre");
//						this.hero.point_de_vie -= 1;
//					}
//					else {
//					System.out.println("Le héro bloque contre un mur");
//					}
//				}
//			break;
//			case "q":
//				if (this.labyrinthe.laby[this.hero.position_y][this.hero.position_x -1] == 0) {
//					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x]= 0;
//					this.hero.position_x -= 1;
//					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x] = 2;
//				}
//				else {
//					if (this.labyrinthe.laby[this.hero.position_y][this.hero.position_x - 1] == 3) {
//						System.out.println("Défaite : héro tué par un monstre");
//						this.hero.point_de_vie -= 1;
//					}
//					else {
//					System.out.println("Le héro bloque contre un mur");
//					}
//				}
//			break;
//			case "d":
//				if (this.labyrinthe.laby[this.hero.position_y][this.hero.position_x + 1] == 0) {
//					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x]= 0;
//					this.hero.position_x += 1;
//					this.labyrinthe.laby[this.hero.position_y][this.hero.position_x] = 2;
//				}
//				else {
//					if (this.labyrinthe.laby[this.hero.position_y][this.hero.position_x + 1] == 3) {
//						System.out.println("Défaite : héro tué par un monstre");
//						this.hero.point_de_vie -= 1;
//					}
//					else {
//					System.out.println("Le héro bloque contre un mur");
//					}
//				}
//			break;
//			default:
//		}
//	}
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
					System.out.print(this.labyrinthe.laby[i][j].population.get(0).visuel);
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
	}
	public void resoudreCombat() {
		for (Monstre monstre : this.populationMonstre) {
			if ((this.hero.position_x == monstre.position_x) && (this.hero.position_y == monstre.position_y)) {
				this.hero.point_de_vie = 0;
			}
		}
	}
}

