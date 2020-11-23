package jeu;

import java.util.Scanner;



public class Main {

	public static void main(String[] args) {
		//Labyrinthe labyrinthe = new Labyrinthe(10,10);

		Heros hero = new Heros();
		Game jeu = new Game(hero);
		jeu.randomPopulationMonstre(5);
		Scanner scan = new Scanner(System.in);
		while (jeu.hero.testVivant()) {
			jeu.affichage();
			System.out.println("Deplacement z/q/s/d");
			String commande = scan.nextLine(); // Lire la saisie de l'utilisateur
			jeu.deplacementHero(commande);
			jeu.resoudreCombat();
		}
			
		scan.close();
	}

}
