package jeu;

import java.util.Scanner;



public class Main {

	public static void main(String[] args) {

		//Labyrinthe labyrinthe = new Labyrinthe(10,10);
		Scanner scan = new Scanner(System.in);
		String choix = new String();
		String nomFichier = new String();
		Game jeu;
		try {
			jeu = new Game("niveau1");
			while (!(choix.equalsIgnoreCase("oui") || choix.equalsIgnoreCase("non"))) {
				System.out.println("Souhaitez-vous creer votre propre niveau ?");
				System.out.println("Répondez 'oui ou 'non' dans la console.");
				choix = scan.nextLine().toLowerCase();
				switch (choix) {
					case "oui":
						jeu = new Game();
					break;
					case "non":
						boolean nomFichierInValide = true;
						while (nomFichierInValide) {
							try {
								System.out.println("Tapez le nom du niveau à charger");
								nomFichier = scan.nextLine();
								jeu = new Game(nomFichier);
								nomFichierInValide = false;
							}
							catch(Exception e) {
								System.out.println("Erreur lors de la lecture du fichier");
							}
						}
					break;
					default:
						System.out.println("Instruction non-comprise. Veuillez resaisir votre choix.");
				}
			}
			while (jeu.hero.testVivant()) {
				jeu.affichage();
				System.out.println("Deplacement z/q/s/d");
				String commande = scan.nextLine().toLowerCase(); // Lire la saisie de l'utilisateur
				jeu.deplacementHero(commande);
			}
			System.out.println("Défaite : Le héro a perdu tous ses points de vie");
		} catch (Exception e1) {
			e1.printStackTrace();
	
		}
		scan.close();
	}

}
