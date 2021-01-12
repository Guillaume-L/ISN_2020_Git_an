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
	public int[] deplacementVersHero(Heros hero, Labyrinthe labyrinthe) {
		int[] nouvellePosition = new int[2];
		ArrayList<int[]> deplacementsPossibles= new ArrayList<int[]>();
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
			if (this.test_deplacement(labyrinthe, coordonnee[0], coordonnee[1])) {
				deplacementsPossibles.add(coordonnee);
				}
		}
		for (int[] deplacement : deplacementsPossibles) {
			if ((deplacement[0] == hero.position_x) && (deplacement[1] == hero.position_y)) {
				return deplacement;
			}
		}
		int taille=deplacementsPossibles.size();
		if (taille == 0) {
			nouvellePosition[0] = this.position_x;
			nouvellePosition[1] = this.position_y;
			return nouvellePosition;
		}
		double distanceMin = labyrinthe.largeur * labyrinthe.longueur;
		ArrayList<int[]> deplacementsOptimaux = new ArrayList<int[]>();
		for (int[] deplacement : deplacementsPossibles) {
			double distanceAuHero = Math.sqrt( Math.pow(hero.position_x - deplacement[0], 2) + Math.pow(hero.position_y - deplacement[1], 2) );
			if (distanceAuHero < distanceMin ) {
				deplacementsOptimaux.clear();
				deplacementsOptimaux.add(deplacement);
				distanceMin = distanceAuHero;
			}
			else if (distanceAuHero == distanceMin ) {
				deplacementsOptimaux.add(deplacement);
			}
		}
		Random rand= new Random();
		taille=deplacementsOptimaux.size();
		int[] move = deplacementsOptimaux.get(rand.nextInt(taille));
		return move;
	}
	public double distanceAuHero(Heros hero, int[] deplacement) {
		return Math.sqrt( Math.pow(hero.position_x - deplacement[0], 2) + Math.pow(hero.position_y - deplacement[1], 2) );
	}
	public int[] deplacementSuiveur(Heros hero, Labyrinthe labyrinthe) {
		class Chemin{
			int[] noeud;
			Chemin suivant;
			boolean accessible;
			
			Chemin(int[] noeud){
				this.noeud = noeud;
				this.suivant = null;
				this.accessible = true;
			}
			int longueur(){
				if (this.suivant == null) {
					return 0;
				}
				else {
					return 1 + this.suivant.longueur();
				}
			}
			public String toString() {
				String abscisse = String.valueOf(noeud[0]);
				String ordonnee = String.valueOf(noeud[1]);
				if (this.suivant == null) {
					return "[" + abscisse + "," + ordonnee + "]";
				}
				else {
					return this.suivant.toString() + " ; [" + abscisse + "," + ordonnee + "]";
				}
			}
			Chemin parcoursLargeur(Heros hero, Labyrinthe labyrinthe) {
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
					coordonnee[0] = this.noeud[0] + deplacement[0];
					coordonnee[1] = this.noeud[1] + deplacement[1];
					Monstre explorateur = new Monstre(1, 1, 1);
					explorateur.position_x = this.noeud[0];
					explorateur.position_y = this.noeud[1];
					explorateur.image = Monstre.this.image;
					if (explorateur.test_deplacement(labyrinthe, coordonnee[0], coordonnee[1])) {
						deplacement_possib.add(coordonnee);
						}
				}
				if (deplacement_possib.size() == 0) {
					this.accessible = false;
					return this;
				}
				for (int[] deplacement : deplacement_possib) {
					if ((deplacement[0] == hero.position_x) && (deplacement[1] == hero.position_y)) {
						this.suivant = new Chemin(deplacement);
						return this;
					}
				}
				int longueurPlusCourte = labyrinthe.longueur * labyrinthe.longueur;
				Chemin cheminPlusCourt = new Chemin(bas);
				boolean nonAccessible = true;
				for (int[] deplacement : deplacement_possib) {
					Labyrinthe labyrintheSuivant = labyrinthe.dupliquer();
					Mur mur = new Mur();
					labyrintheSuivant.laby[this.noeud[1]][this.noeud[0]] = mur;
					Chemin chemin = new Chemin(deplacement);
					Chemin cheminCandidat = chemin.parcoursLargeur(hero,  labyrintheSuivant);
					if (cheminCandidat.accessible) {
						if (cheminCandidat.longueur() < longueurPlusCourte) {
							longueurPlusCourte = cheminCandidat.longueur();
							cheminPlusCourt = cheminCandidat;
							nonAccessible = false;
						}
					}
				}
				if (nonAccessible) {
					this.accessible = false;
					return this;
				}
				else {
					this.suivant = cheminPlusCourt;
					return this;
				}
				
			}
//			Chemin parcoursLargeurItteratif(Heros hero, Labyrinthe labyrinthe) {
//				int[] depart = new int[2];
//				depart[0] = Monstre.this.position_x;
//				depart[1] = Monstre.this.position_y;
//				Chemin cheminCourant = new Chemin(noeud);
//				int longueurCourante = 1;
//				Chemin cheminLePlusCourt = new Chemin(noeud);
//				Labyrinthe labyrintheCourant = labyrinthe.dupliquer()
//				int distanceLaPlusCourte = labyrinthe.largeur * labyrinthe.longueur;;
//				boolean ResteAParcourir = true;
//				while (ResteAParcourir) {
//						ArrayList<int[]> deplacement_possib= new ArrayList<int[]>();
//						ArrayList<int[]> deplacements = new ArrayList<int[]>();
//						int[] gauche = new int[2];
//						gauche[0] = -1;
//						gauche[1] = 0;
//						int[] droite = new int[2];
//						droite[0] = 1;
//						droite[1] = 0;
//						int[] haut = new int[2];
//						haut[0] = 0;
//						haut[1] = -1;
//						int[] bas = new int[2];
//						bas[0] = 0;
//						bas[1] = 1;
//						deplacements.add(gauche);
//						deplacements.add(droite);
//						deplacements.add(haut);
//						deplacements.add(bas);
//						for (int[] deplacement : deplacements) {
//							int[] coordonnee = new int[2];
//							coordonnee[0] = cheminCourant.noeud[0] + deplacement[0];
//							coordonnee[1] = cheminCourant.noeud[1] + deplacement[1];
//							Monstre explorateur = new Monstre(1, 1, 1);
//							explorateur.position_x = cheminCourant.noeud[0];
//							explorateur.position_y = cheminCourant.noeud[1];
//							explorateur.image = Monstre.this.image;
//							if (explorateur.test_deplacement(labyrinthe, coordonnee[0], coordonnee[1])) {
//								deplacement_possib.add(coordonnee);
//								}
//						}
//						if (deplacement_possib.size() == 0) {
//							Mur mur = new Mur();
//							labyrintheCourant.laby[this.noeud[1]][this.noeud[0]] = mur;
//						}
//						for (int[] deplacement : deplacement_possib) {
//							if ((deplacement[0] == hero.position_x) && (deplacement[1] == hero.position_y)) {
//								this.suivant = new Chemin(deplacement);
//								longueurCourante ++;
//								if (longueurCourante < distanceLaPlusCourte)
//								cheminLePlusCourt = this;
//							}
//						}
//						boolean nonAccessible = true;
//						int i = 0;
//						while (i< deplacement_possib.size()) {
//							Labyrinthe labyrintheSuivant = labyrinthe.dupliquer();
//							Mur mur = new Mur();
//							labyrintheSuivant.laby[this.noeud[1]][this.noeud[0]] = mur;
//							Chemin cheminCandidat = new Chemin(deplacement_possib.get(i));
//							if (cheminCandidat.accessible) {
//								if (cheminCandidat.longueur() < longueurPlusCourte) {
//									longueurPlusCourte = cheminCandidat.longueur();
//									cheminPlusCourt = cheminCandidat;
//									nonAccessible = false;
//								}
//							}
//						}
//					}
//				}
//			}
		}
		int[] noeud = new int[2];
		noeud[0] = this.position_x;
		noeud[1] = this.position_y;
		Chemin chemin = new Chemin(noeud);
		chemin = chemin.parcoursLargeur(hero, labyrinthe);
		if (chemin.accessible) {
			return chemin.suivant.noeud;
		}
		else {
			return noeud;
			
		}

	}

}

