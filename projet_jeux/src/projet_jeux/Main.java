package projet_jeux;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Labyrinthe labyrinthe = new Labyrinthe(10,10);
		Hero hero = new Hero(1,1,1);
		labyrinthe.deplacementTresor(8, 8);
		Jeu jeu = new Jeu(labyrinthe, hero);
		jeu.randomPopulationMonstre(5);
		Scanner scan = new Scanner(System.in);
		while ((!jeu.testVictoire()) && (jeu.hero.testVivant())) {
			jeu.affichage();
			System.out.println("Deplacement z/q/s/d");
			String commande = scan.nextLine(); // Lire la saisie de l'utilisateur
			jeu.deplacementHero(commande);
			jeu.resoudreCombat();
		}
		if (jeu.testVictoire()) {
			System.out.println("Victoire : Trésor récupéré !");
		}
		else {System.out.println("Défaite : héro tué par un monstre");
			
		}
		scan.close();
	}

}

