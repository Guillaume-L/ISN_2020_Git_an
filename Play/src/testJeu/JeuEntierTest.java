package testJeu;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import jeu.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class JeuEntierTest {
	public Heros perso1; 
	public Heros perso2; 
	public Personnage perso3;
	public Labyrinthe laby1; 
	public Parcelle parcelle1;
	public Game game1;
	public Monstre monstre1;
	public Monstre monstre2; 
	public Monstre monstre3;
	public Monstre monstre4;
	public ArrayList<Monstre> popMonstre1; 
	
	@Before      
	public void init()     {
		popMonstre1 = new ArrayList<Monstre>(); 
		
		monstre1 = new Monstre(1,5,5);
		monstre2 = new Monstre(1,4,4); 
		monstre3 = new Monstre(1,6,6); 
		
		popMonstre1.add(monstre1);
		popMonstre1.add(monstre2);
		popMonstre1.add(monstre3);
		
		laby1 = new Labyrinthe(10,10);
		
		perso1 = new Heros();
		perso2 = new Heros(2,2,2);
		
		game1 = new Game(laby1,perso1,popMonstre1);
	
    } 
	
	//Fantome
	
		//Deplacement Fantome
	
	@Test //Verif si le fantome a bien changé de position
	public void testDeplacementFantome() {
		Fantome fantome1 = new Fantome(1,6,1);
		int old_x = fantome1.position_x;
		int old_y = fantome1.position_y;
		fantome1.deplacement(laby1);
		assertNotSame(fantome1.position_x,old_x);
		assertNotSame(fantome1.position_y,old_y); 
	}
	
	//Monstre 
	
		//Constructeur
//	@Test
//	public void testConstructeurMonstre () { 
//		
//	}
	
		//Deplacement
	
	@Test //Verif si le monstre a bien changé de position
	public void testDeplacementMonstre() {
		monstre4 = new Monstre(1,4,2); //*******Attention coordonnée y bizarre
		int old_x = monstre4.position_x;
		int old_y = monstre4.position_y;
		monstre4.deplacement(laby1);
		assertNotSame(monstre4.position_x,old_x);
		assertNotSame(monstre4.position_y,old_y); 
	}
	
	//Heros
		
		//Constructeur par défaut
	
	@Test
	public void testConstructeurDefautHeros() {
		assertSame(perso1.point_de_vie,2);
		assertSame(perso1.position_x,1);
		assertSame(perso1.position_y,1);
		assertSame(perso1.visuel,"H");
		assertSame(perso1.image,"hero");
	}
	
		//Constructeur
	
	@Test
	public void testConstructeurHeros() {
		int pdv = perso2.point_de_vie;
		int x = perso2.position_x;
		int y = perso2.position_y; 
		String visu1 = perso2.visuel; 
		String image1 = perso2.image;
		assertSame(perso2.point_de_vie,pdv);
		assertSame(perso2.position_x,x);
		assertSame(perso2.position_y,y);
		assertSame(perso2.visuel,visu1);
		assertSame(perso2.image,image1);
	}
	
//	@Test
//	public void testDeplacementHeroHaut() {
//	//assertEquals(Game.deplacementHero("z"),);
//
//	}
	
	//Personnage
	
		//testDeplacement() de Personnage
	
	@Test
	public void testTestDeplacement() {
		assertFalse(perso2.test_deplacement(laby1,2,2)); //Probleme
		assertTrue(perso2.test_deplacement(laby1,3,2));
		assertFalse(perso2.test_deplacement(laby1,11,2));
		assertFalse(perso2.test_deplacement(laby1,2,11));
		//laby1[2][1].visuel = "1";
		assertFalse(perso2.test_deplacement(laby1,2,1));
	}
	
		//testVivant() de Personnage
	
	@Test
	public void testTestVivant() {
		assertTrue(perso2.testVivant());
		perso2.point_de_vie = 0; 
		assertFalse(perso2.testVivant());
	}
	
		//subirDegat() de Personnage
	
	@Test
	public void testSubirDegat() {
		perso2.point_de_vie = 2;
		perso2.subirDegat();
		assertSame(perso2.point_de_vie,1);
		perso2.subirDegat();
		assertFalse(perso2.testVivant());
		
	}
	//Sol
	
	
	//Mur
	
	
	//Magique
	
		//Constructeur de Magique
	
	@Test
	public void testConstructeurMagique() {
		Magique caseM = new Magique(); 
		assertTrue(caseM.visible);
		assertSame(caseM.visuel,"M");
		//assertSame(caseM.population,0);
		assertSame(caseM.image,"magique");
	}
	
		//declenchement() de Magique
	
	@Test
	public void testDeclenchementMagique() {
		Magique caseM = new Magique(); 
		assertNotSame(caseM.declenchement(),"rien");
	}
	
	//Passage
	
		//Constructeur de Passage
	
	@Test
	public void testConstructeurPassage() {
		Passage passage1 = new Passage(1,4);
		assertTrue(passage1.visible);
		assertSame(passage1.visuel,"P");
		assertSame(passage1.positionArriveeX,4); //Probleme
		assertSame(passage1.positionArriveeY,1); 
		//assertSame(passage1.population,0);
		assertSame(passage1.image,"passage");
	}
	
		//declenchement() de Passage
	
	@Test
	public void testDeclenchementPassage() {
		Passage passage1 = new Passage(1,4);
		assertSame(passage1.declenchement(),"passage "+4+" "+1); //Probleme
	}
	
	//Piege
	
			//Constructeur de Piege
		
		@Test
		public void testConstructeurPiege() {
			Piege piege1 = new Piege();
			assertFalse(piege1.visible);
			assertSame(piege1.visuel,"0");
			//assertSame(piege1.population,0);
			assertSame(piege1.image,"sol");
		}
		
			//declenchement() de Passage
		
		@Test
		public void testDeclenchementPiege() {
			Piege piege1 = new Piege();
			//piege1.declenchement(); 
			assertTrue(piege1.visible);
			assertSame(piege1.visuel,"X");
			assertSame(piege1.image,"piege");
			//assertSame(System.out,"Le Hero vient de marcher sur un piège. Il perd 1 point de vie"); //Probleme
			//Manque un test pour savoir si le syso a été fait
			//assertSame(piege1.declenchement(),"piege");
		}
		
	//Tresor
		
		//Constructeur de Tresor
	
	@Test
	public void testConstructeurTresor() {
		Tresor tresor1 = new Tresor(true);
		assertTrue(tresor1.visible);
		assertSame(tresor1.visuel,"T");
		//assertSame(tresor1.population,0);
		assertSame(tresor1.image,"tresor");
		Tresor tresor2 = new Tresor(false);
		assertSame(tresor2.image,"sol");
	}
		//reveler() de Tresor
	
	@Test
	public void testRevelerTresor() {
		Tresor tresor1 = new Tresor(true);
		tresor1.reveler(); 
		assertTrue(tresor1.visible);
		assertSame(tresor1.image,"tresor");
		Tresor tresor2 = new Tresor(false);
		tresor2.reveler();
		assertTrue(tresor1.visible);
		assertSame(tresor1.image,"tresor");
	}
	
		//declenchement() de Tresor
	
	@Test
	public void testDeclenchementTresor() {
		Tresor tresor1 = new Tresor(true);
		assertNotSame(tresor1.declenchement(),"victoire");  
		
		Tresor tresor2 = new Tresor(false); //Coquille !! 
		assertNotSame(tresor2.declenchement(),"rien");
	}
	
	//Parcelle 
	
		//testPresence() de Parcelle
	
	@Test
	public void testTestPresenceParcelle() {
		Sol sol1 = new Sol(); 
		assertFalse(sol1.testPresence());
		Mur mur1 = new Mur();
		assertFalse(mur1.testPresence());
		Magique caseM = new Magique();
		assertFalse(caseM.testPresence());
		Passage passage1 = new Passage(1,4);
		assertFalse(passage1.testPresence());
		Piege piege1 = new Piege();
		assertFalse(piege1.testPresence());
		Tresor tresor1 = new Tresor(true);
		assertFalse(tresor1.testPresence());
		//Manque une blanche de testPresence
	}
	
		//delenchement() de Parcelle
	
	@Test
	public void testDeclenchementParcelle() {
		//MANQUE
	}
	
		//reveler() de Parcelle
	
	@Test
	public void testRevelerParcelle() {
		//MANQUE
	}
	
	//Labyrinthe
	
		//revelerTresor() de Labyrinthe
	
	@Test
	public void testRevelerTresorLabyrinthe() {
		Tresor tresor1 = new Tresor(false); 
		laby1.revelerTresor(); 
		assertFalse(tresor1.visible);
	}
	
		//Creaton Labyrinthe
	
//	@Test (expected = Exception.class) 
//	public void testCreationLabyrinthe() throws Exception {
//		Labyrinthe laby2 = new Labyrinthe();
//		//Entrer dans la console les paramètres qui font activer les exceptions
//	}
	
		//Creation Labyrinthe carré
	
	@Test (expected = Exception.class) 
	public void testCreationLabyrintheCarre() throws Exception {
		//Labyrinthe laby2 = new Labyrinthe(-10);
		//Entre dans la console les paramètres qui font activer les exceptions
	}
	
	
	
	//Game
	
		//Constructeur de Game
	
//	@Test (expected = Exception.class)
//	public void testConstructeurGame() throws Exception {
//		Game game1 = new Game();
//		//Game game1 = new Game(perso2,laby1);
//		//Autre façon de creer une Game
//		game1.declencherEffetCase(7,1);
//		//Entrer dans la console les paramètres qui font activer les exceptions
//	}
	
	
		//Constructeur de Game avec nom de fichier
	
	@Test (expected = Exception.class)
	public void testConstructeurGameNomFichier() throws Exception {
	Game game1 = new Game("niveau1");
	game1.declencherEffetCase(7,1);
	//Entrer dans la console les paramètres qui font activer les exceptions

	}

	
		//deplacementHero() de Game
	
	@Test 
	public void testDeplacementHeroGameHaut() {
		Game game1 = new Game(laby1,perso2);
		int positiondepart_x = perso2.position_x;
		int positiondepart_y = perso2.position_y;
		game1.deplacementHero("z");
		assertSame(perso2.position_x,positiondepart_x);
		assertSame(perso2.position_y,positiondepart_y);
	}
	
	@Test 
	public void testDeplacementHeroGameBas() {
		Game game1 = new Game(laby1,perso2);
		int positiondepart_x = perso2.position_x;
		int positiondepart_y = perso2.position_y;
		game1.deplacementHero("s");
		assertSame(perso2.position_x,positiondepart_x);
		assertSame(perso2.position_y,positiondepart_y);
	}
	
	@Test 
	public void testDeplacementHeroGameGauche() {
		Game game1 = new Game(laby1,perso2);
		int positiondepart_x = perso2.position_x;
		int positiondepart_y = perso2.position_y;
		game1.deplacementHero("q");
		assertSame(perso2.position_x,positiondepart_x);
		assertSame(perso2.position_y,positiondepart_y);
	}
	
	@Test 
	public void testDeplacementHeroGameDroite() {
		Game game1 = new Game(laby1,perso2);
		int positiondepart_x = perso2.position_x;
		int positiondepart_y = perso2.position_y;
		game1.deplacementHero("d");
		assertSame(perso2.position_x,positiondepart_x);
		assertSame(perso2.position_y,positiondepart_y);
	}


	
		//peuplement() de Game
	
	@Test
	public void testPeuplementGame() {
		//game1.peuplement(monstre1);
	}




//		//randomPopulationMonstre() de Game
//	
//	@Test
//	public void testRandomPopulationMonstreGame() {
//		//game1.randomPopulationMonstre(3);
//	}
//	
		//affichage() de Game
	
	@Test
	public void testAffichageGame() {
		Game game1 = new Game(laby1,perso2);
		game1.affichage(); 
	}
	
		//resoudreCombat() de Game
	
	@Test
	public void testResoudreCombatGame() {
		Game game1 = new Game(laby1,perso2);
		
	}
	
		//declencherEffetCase() de Game
	
	@Test
	public void testDeclencherEffetCaseGame() {
		
	}
	

}
		


