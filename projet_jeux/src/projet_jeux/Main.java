package projet_jeux;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		boolean victoire = false;
		Labyrinthe labyrinthe = new Labyrinthe(10,10);
		Hero hero = new Hero(1,1,1);
		labyrinthe.peuplement(hero);
		labyrinthe.deplacementTresor(8, 8);
		labyrinthe.randomPlacementMonstre(5);
		Scanner scan = new Scanner(System.in);
		while ((!labyrinthe.testVictoire()) && (labyrinthe.hero.testVivant())) {
			labyrinthe.affichage();
			System.out.println("Deplacement z/q/s/d");
			String commande = scan.nextLine(); // Lire la saisie de l'utilisateur
			labyrinthe.deplacementHero(commande);
			labyrinthe.resoudreCombat();
		}
		if (labyrinthe.testVictoire()) {
			System.out.println("Victoire : Trésor récupéré !");
		}
		else {System.out.println("Défaite : héro tué par un monstre");
			
		}
		scan.close();
	}

}

