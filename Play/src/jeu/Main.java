package jeu;

import java.util.Scanner;





public class Main {

	public static void main(String[] args) {

		//Labyrinthe labyrinthe = new Labyrinthe(10,10);
		Scanner scan = new Scanner(System.in);
		String choix = new String();
		String nomFichier = new String();
		boolean continuer = true;
		Game jeu;
		try {
			jeu = new Game("niveau2");
			while (!(choix.equalsIgnoreCase("oui") || choix.equalsIgnoreCase("non"))) {
				System.out.println("Souhaitez-vous creer votre propre niveau ?");
				System.out.println("R�pondez 'oui ou 'non' dans la console.");
				choix = scan.nextLine().toLowerCase();
				switch (choix) {
					case "oui":
						jeu = new Game();
					break;
					case "non":
						boolean nomFichierInValide = true;
						while (nomFichierInValide) {
							try {
								System.out.println("Tapez le nom du niveau � charger");
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
			jeu.affichage();
//			Scanner scan = new Scanner(System.in);
//			while (jeu.hero.testVivant() && continuer) {

//				System.out.println("Deplacement z/q/s/d, tapez 'quitter' pour arr�ter le jeu");
//				String commande = scan.nextLine().toLowerCase(); // Lire la saisie de l'utilisateur
//				if (commande.equalsIgnoreCase("quitter")) {
//					continuer = false;
//					System.out.println("Vous venez de quitter le jeu");
//				}
//				jeu.deplacementHero(commande);

//			}
//			if (continuer)
//				System.out.println("D�faite : Le h�ro a perdu tous ses points de vie");
		} catch (Exception e1) {
			e1.printStackTrace();
	
		}
		scan.close();
	}

}
