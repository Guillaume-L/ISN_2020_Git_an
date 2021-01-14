package testJeu;

import static org.junit.Assert.*;

import java.util.ArrayList;

import jeu.*;

import org.junit.Before;
import org.junit.Test;

public class JeuEntierTest {
	public Heros heros1; 
	
	
	@Before      
	public void init()     {
		
		heros1 = new Heros(1,2,2);
		
		
    } 
	
	
	
	@Test //Verif si le fantome a bien changé de position
	public void testDeplacementFantome() {
		Monstre monstre4 = new Fantome(1,4,2); 
		Labyrinthe laby1 = new Labyrinthe(10,10);
		int old_x = monstre4.position_x;
		int old_y = monstre4.position_y;
		int[] coordonnee = monstre4.deplacementVersHero(heros1,laby1);
		int[] comparaisonNew = new int[2];
		comparaisonNew[0] = coordonnee[0];
		comparaisonNew[1] = coordonnee[1];
		int[] comparaisonOld = new int[2];
		comparaisonOld[0] = old_x;
		comparaisonOld[1] = old_y;
		assertNotSame(comparaisonNew, comparaisonOld);
	}
	
	@Test //Verif si le héros se déplacement si le string de la touche est composé de lettres de déplacement "z,q,s,d"
	public void double_depla() {
		Monstre monstre1 = new Monstre(1,5,5);
		Monstre monstre2 = new Monstre(1,4,4); 
		Monstre monstre3 = new Monstre(1,6,6); 
		ArrayList <Monstre >popMonstre1 = new ArrayList<Monstre>(); 
		popMonstre1.add(monstre1);
		popMonstre1.add(monstre2);
		popMonstre1.add(monstre3);
		Heros heros1 = new Heros(); //heros généré à une position de défaut de (1,1)
		Labyrinthe laby1 = new Labyrinthe(10,10);
		Game game1 = new Game(laby1,heros1,popMonstre1);
		game1.deplacementHero("sd");
		//System.out.println(heros1.position_x);
		assertSame(heros1.position_x,1);
	}
	
	
		
	@Test
	public void testConstructeurMonstre () { 
		Heros heros1 = new Heros();
		Monstre monstre1 = new Monstre(1,5,5);
		ArrayList <Monstre >popMonstre1 = new ArrayList<Monstre>(); 
		popMonstre1.add(monstre1);
		Labyrinthe laby1 = new Labyrinthe(10,10);
		Game game1 = new Game(laby1,heros1,popMonstre1);
		assertTrue(game1.populationMonstre.get(0).testVivant());
	}
	
		
	
	@Test //Verif si le monstre a bien changé de position
	public void testDeplacementMonstre() {
		Monstre monstre4 = new Fantome(1,4,2);
		monstre4 = new Monstre(1,4,2); 
		Labyrinthe laby1 = new Labyrinthe(10,10);
		int old_x = monstre4.position_x;
		int old_y = monstre4.position_y;
		int[] coordonnee = monstre4.deplacementVersHero(heros1,laby1);
		int[] comparaisonNew = new int[2];
		comparaisonNew[0] = coordonnee[0];
		comparaisonNew[1] = coordonnee[1];
		int[] comparaisonOld = new int[2];
		comparaisonOld[0] = old_x;
		comparaisonOld[1] = old_y;
		assertNotSame(comparaisonNew, comparaisonOld);
	}
	
	
		
		
	
	@Test
	public void testConstructeurDefautHeros() {
		Heros heros1 = new Heros();
		assertSame(heros1.point_de_vie,2);
		assertSame(heros1.position_x,1);
		assertSame(heros1.position_y,1);
		assertSame(heros1.visuel,"H");
		assertSame(heros1.image,"hero");
	}
	
		
	
	@Test
	public void testConstructeurHeros() {
		Heros heros2 = new Heros(1,2,2);
		int pdv = heros2.point_de_vie;
		int x = heros2.position_x;
		int y = heros2.position_y; 
		String visu1 = heros2.visuel; 
		String image1 = heros2.image;
		Labyrinthe laby1 = new Labyrinthe(10,10);
		Game game1 = new Game(laby1,heros2);
		assertSame(heros2.point_de_vie,pdv);
		assertSame(heros2.position_x,x);
		assertSame(heros2.position_y,y);
		assertSame(heros2.visuel,visu1);
		assertSame(heros2.image,image1);
		
		//Avec le constructeur de game
		assertSame(game1.hero.point_de_vie,pdv);
		assertSame(game1.hero.position_x,x);
		assertSame(game1.hero.position_y,y);
		assertSame(game1.hero.visuel,visu1);
		assertSame(game1.hero.image,image1);
	}
	
	

	
	@Test
	public void testTestDeplacement() {     
		Heros heros2 = new Heros(1,2,2);
		Labyrinthe laby1 = new Labyrinthe(10,10);
		assertFalse(heros2.test_deplacement(laby1,2,2)); 
		assertTrue(heros2.test_deplacement(laby1,3,2));
		assertFalse(heros2.test_deplacement(laby1,11,2));
		assertFalse(heros2.test_deplacement(laby1,2,11));
		//laby1[2][1].visuel = "1";
		assertTrue(heros2.test_deplacement(laby1,2,1));
	}
	
		
	@Test 
	public void testdeplacementmurhaut() {
		Heros heros1 = new Heros();
		Monstre monstre1 = new Monstre(1,5,5);
		Monstre monstre2 = new Monstre(1,4,4); 
		Monstre monstre3 = new Monstre(1,6,6); 
		ArrayList <Monstre >popMonstre1 = new ArrayList<Monstre>(); 
		popMonstre1.add(monstre1);
		popMonstre1.add(monstre2);
		popMonstre1.add(monstre3);
		Labyrinthe laby1 = new Labyrinthe(10,10);
		Game game1 = new Game(laby1,heros1,popMonstre1);
		game1.deplacementHero("z");
		String a=game1.status;
		assertTrue(a.equalsIgnoreCase("Le héro bloque contre un mur"));
		
	}
	@Test 
	public void testdeplacementmurgauche() {
		Heros heros1 = new Heros();
		Labyrinthe laby1 = new Labyrinthe(10,10);
		Game game1 = new Game(laby1,heros1);
		game1.deplacementHero("q");
		String b=game1.status;
		assertSame(game1.hero.position_x,1);
		assertSame(game1.hero.position_y,1);
		assertTrue(b.equalsIgnoreCase("Le héro bloque contre un mur"));
	}
	@Test 
	public void testdeplacementmurbas() {
		Heros heros4 = new Heros(1,8,8);
		Labyrinthe laby1 = new Labyrinthe(10,10);
		Game game2 = new Game(laby1,heros4);
		game2.deplacementHero("s");
		String c=game2.status;
		String dh="Le héro bloque contre un mur";
		assertSame(game2.hero.position_x,8);
		assertSame(game2.hero.position_y,8);
		assertTrue(c.equalsIgnoreCase(dh));
	}
	
	@Test 
	public void testdeplacementmurdroite() {
		Heros heros4 = new Heros(1,8,8);
		Labyrinthe laby1 = new Labyrinthe(10,10);
		Game game2 = new Game(laby1,heros4);
		game2.deplacementHero("d");
		String c=game2.status;
		assertSame(game2.hero.position_x,8);
		assertSame(game2.hero.position_y,8);
		assertTrue(c.equalsIgnoreCase("Le héro bloque contre un mur"));
	}	
	
	@Test
	public void testTestVivant() {
		Heros heros2 = new Heros(1,2,2);
		Labyrinthe laby1 = new Labyrinthe(10,10);
		Game game2 = new Game(laby1,heros2);
		assertTrue(game2.hero.testVivant());
		heros2.point_de_vie = 0; 
		assertFalse(game2.hero.testVivant());
	}
	
		//subirDegat() de Personnage
	
	@Test
	public void testSubirDegat() {
		Heros heros2 = new Heros(1,2,2);
		Labyrinthe laby1 = new Labyrinthe(10,10);
		Game game2 = new Game(laby1,heros2);
		heros2.point_de_vie = 2;
		heros2.subirDegat();
		assertSame(game2.hero.point_de_vie,1);
		heros2.subirDegat();
		assertFalse(game2.hero.testVivant());
	}
	
	@Test
	public void testHeroTueParMonstre() throws Exception {
		Game game2 = new Game("testHerosTueParMonstre");
		assertSame(game2.hero.point_de_vie,1);
		game2.deplacementMonstre();
		assertFalse(game2.hero.testVivant());
	}
	

	
	@Test
	public void testConstructeurMagique() {
		Magique caseM = new Magique(); 
		assertTrue(caseM.visible);
		assertSame(caseM.visuel,"M");
		
		assertSame(caseM.image,"magique");
	}
	

	
	@Test
	public void testDeclenchementMagiqueMonstre() throws Exception {  
		Game jeu = new Game("testDeclenchementMagiqueMonstre") ; 
		assertSame(jeu.labyrinthe.laby[4][6].image,"sol");
		jeu.deplacementMonstre();
		assertSame(jeu.labyrinthe.laby[4][6].image,"sol");
	}
	
	
	
	
	@Test
	public void testConstructeurPassage() {
		Passage passage1 = new Passage(1,4);
		assertTrue(passage1.visible);
		assertSame(passage1.visuel,"P");
		assertSame(passage1.positionArriveeX,1); 
		assertSame(passage1.positionArriveeY,4); 
		
		assertSame(passage1.image,"passage");
	}

		
		@Test
		public void testConstructeurPiege() {
			Piege piege1 = new Piege();
			assertFalse(piege1.visible);
			assertSame(piege1.visuel,"0");
			
			assertSame(piege1.image,"sol");
		}
		
		
		@Test
		public void testDeclenchementPiege() {
			Piege piege1 = new Piege();
			assertFalse(piege1.visible);
			assertSame(piege1.visuel,"0");
			assertSame(piege1.image,"sol");
			piege1.declenchement(); 
			assertTrue(piege1.visible);
			assertSame(piege1.visuel,"X");
			assertSame(piege1.image,"piege");
			assertTrue(piege1.declenchement().equalsIgnoreCase("piege")); //Probleme

			
		}

	
	@Test
	public void testConstructeurTresor() {
		Tresor tresor1 = new Tresor(true);
		assertTrue(tresor1.visible);
		assertSame(tresor1.visuel,"T");
		
		assertSame(tresor1.image,"tresor");
		Tresor tresor2 = new Tresor(false);
		assertSame(tresor2.image,"sol");
	}
	
	
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
	
	@Test
	public void testDeclenchementMagiqueParHeros () throws Exception {
		Game game = new Game("testDeclenchementMagiqueParHeros");
		assertSame(game.labyrinthe.laby[8][2].image,"sol");
		assertNotSame(game.status,"La trésor est désormais visible");
		game.deplacementHero("s");
		assertSame(game.status,"La trésor est désormais visible");
	}
	
		
	
	@Test
	public void testDeclenchementTresorParHeros() throws Exception {
		Game game = new Game("testDeclenchementTresorParHeros");
		assertNotSame(game.status,"Victoire : le héro a récupéré le trésor");
		assertNotSame(game.labyrinthe.laby[1][2].image,"tresor");
		game.deplacementHero("s");
		assertSame(game.status, "Victoire : le héro a récupéré le trésor");
	}

	
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
		
	}
	
		
	
	
	

	@Test
	public void testRevelerTresorLabyrinthe() {
		Labyrinthe laby1 = new Labyrinthe(10,10);
		Tresor tresor1 = new Tresor(false); 
		laby1.revelerTresor(); 
		assertFalse(tresor1.visible);
	}
	
		
	
	@Test 
	public void testLabyrinthePlein () throws Exception {
		Game game = new Game("testLabyrinthePlein");
		game.deplacementHero("s");
		assertSame(game.hero.position_x,1);
		assertSame(game.hero.position_y,1); 
		game.deplacementHero("z");
		assertSame(game.hero.position_x,1);
		assertSame(game.hero.position_y,1);
		game.deplacementHero("d");
		assertSame(game.hero.position_x,1);
		assertSame(game.hero.position_y,1);
		game.deplacementHero("q");
		assertSame(game.hero.position_x,1);
		assertSame(game.hero.position_y,1);
	}
	
	
	
	@Test 
	public void testConstructeurGame() {
		Heros heros2 = new Heros(1,2,2);
		Labyrinthe laby1 = new Labyrinthe(10,10);
		Game game1 = new Game(laby1,heros2); //*****************************************************
	}
	
		
	
	@Test
	public void testDeplacementHeros() {
		Heros heros2 = new Heros(1,2,2);
		Monstre monstre1 = new Monstre(1,5,5);
		Monstre monstre2 = new Monstre(1,4,4); 
		Monstre monstre3 = new Monstre(1,6,6); 
		ArrayList <Monstre >popMonstre1 = new ArrayList<Monstre>(); 
		popMonstre1.add(monstre1);
		popMonstre1.add(monstre2);
		popMonstre1.add(monstre3);
		Labyrinthe laby1 = new Labyrinthe(10,10);
		Game game3 = new Game(laby1,heros2,popMonstre1);
		game3.deplacementHero("z");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,1);
		game3.deplacementHero("s");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		game3.deplacementHero("d");
		assertSame(heros2.position_x,3);
		assertSame(heros2.position_y,2);
		game3.deplacementHero("q");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
	}
	
	
	@Test
	public void testDeplacementHerosA1() {
		Heros heros2 = new Heros(1,2,2);
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
	}
	
	
	@Test
	public void testDeplacementHerosB1() {
		Heros heros2 = new Heros(1,2,2);
		Monstre monstre1 = new Monstre(1,5,5);
		Monstre monstre2 = new Monstre(1,4,4); 
		Monstre monstre3 = new Monstre(1,6,6); 
		ArrayList <Monstre >popMonstre1 = new ArrayList<Monstre>(); 
		popMonstre1.add(monstre1);
		popMonstre1.add(monstre2);
		popMonstre1.add(monstre3);
		Labyrinthe laby1 = new Labyrinthe(10,10);
		Game game3 = new Game(laby1,heros2,popMonstre1);
		game3.deplacementHero("z");
		game3.deplacementHero("s");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		game3.deplacementHero("d");
		game3.deplacementHero("q");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
	}
	
	
	@Test
	public void testDeplacementHerosC1() {
		Heros heros2 = new Heros(1,2,2);
		Monstre monstre1 = new Monstre(1,5,5);
		Monstre monstre2 = new Monstre(1,4,4); 
		Monstre monstre3 = new Monstre(1,6,6); 
		ArrayList <Monstre >popMonstre1 = new ArrayList<Monstre>(); 
		popMonstre1.add(monstre1);
		popMonstre1.add(monstre2);
		popMonstre1.add(monstre3);
		Labyrinthe laby1 = new Labyrinthe(10,10);
		Game game3 = new Game(laby1,heros2,popMonstre1);
		game3.deplacementHero("g");
		game3.deplacementHero("h");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
	}
	@Test
	public void testmonstrepasmouvement()throws Exception {
		Game jeu=new Game("testmonstrestuck") ;
		ArrayList<Monstre> monstre= jeu.populationMonstre;
		Monstre a=monstre.get(0);
		int x=a.position_x;
		int y=a.position_y;
		int[] stay=a.deplacementVersHero(jeu.hero,jeu.labyrinthe);
		assertSame(x,stay[0]);
		assertSame(y,stay[1]);
	
	
}
	
	@Test
	public void testDeplacementHerosD1() {
		
		//3 touches en même temps
		Heros heros2 = new Heros(1,2,2);
		Monstre monstre1 = new Monstre(1,5,5);
		Monstre monstre2 = new Monstre(1,4,4); 
		Monstre monstre3 = new Monstre(1,6,6); 
		ArrayList <Monstre >popMonstre1 = new ArrayList<Monstre>(); 
		popMonstre1.add(monstre1);
		popMonstre1.add(monstre2);
		popMonstre1.add(monstre3);
		Labyrinthe laby1 = new Labyrinthe(10,10);
		Game game3 = new Game(laby1,heros2,popMonstre1);
		game3.deplacementHero("z");
		game3.deplacementHero("s");
		game3.deplacementHero("d");
		assertSame(heros2.position_x,3);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("z");
		game3.deplacementHero("s");
		game3.deplacementHero("q");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("z");
		game3.deplacementHero("q");
		game3.deplacementHero("d");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,1);
		
		game3.deplacementHero("q");
		game3.deplacementHero("s");
		game3.deplacementHero("d");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		//plus de 3 touches
		game3.deplacementHero("z");
		game3.deplacementHero("s");
		game3.deplacementHero("d");
		game3.deplacementHero("q");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("z");
		game3.deplacementHero("s");
		game3.deplacementHero("q");
		game3.deplacementHero("d");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("z");
		game3.deplacementHero("q");
		game3.deplacementHero("d");
		game3.deplacementHero("s");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("z");
		game3.deplacementHero("q");
		game3.deplacementHero("s");
		game3.deplacementHero("d");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("z");
		game3.deplacementHero("d");
		game3.deplacementHero("q");
		game3.deplacementHero("s");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("z");
		game3.deplacementHero("d");
		game3.deplacementHero("s");
		game3.deplacementHero("q");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("q");
		game3.deplacementHero("z");
		game3.deplacementHero("s");
		game3.deplacementHero("d");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("q");
		game3.deplacementHero("z");
		game3.deplacementHero("d");
		game3.deplacementHero("s");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("q");
		game3.deplacementHero("s");
		game3.deplacementHero("z");
		game3.deplacementHero("d");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("q");
		game3.deplacementHero("s");
		game3.deplacementHero("d");
		game3.deplacementHero("z");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("q");
		game3.deplacementHero("d");
		game3.deplacementHero("z");
		game3.deplacementHero("s");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("q");
		game3.deplacementHero("d");
		game3.deplacementHero("s");
		game3.deplacementHero("z");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("s");
		game3.deplacementHero("z");
		game3.deplacementHero("q");
		game3.deplacementHero("d");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("s");
		game3.deplacementHero("z");
		game3.deplacementHero("d");
		game3.deplacementHero("q");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("s");
		game3.deplacementHero("q");
		game3.deplacementHero("z");
		game3.deplacementHero("d");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("s");
		game3.deplacementHero("q");
		game3.deplacementHero("d");
		game3.deplacementHero("z");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("s");
		game3.deplacementHero("d");
		game3.deplacementHero("z");
		game3.deplacementHero("q");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("s");
		game3.deplacementHero("d");
		game3.deplacementHero("q");
		game3.deplacementHero("z");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("d");
		game3.deplacementHero("z");
		game3.deplacementHero("q");
		game3.deplacementHero("s");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("d");
		game3.deplacementHero("z");
		game3.deplacementHero("s");
		game3.deplacementHero("q");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("d");
		game3.deplacementHero("s");
		game3.deplacementHero("q");
		game3.deplacementHero("z");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("d");
		game3.deplacementHero("s");
		game3.deplacementHero("z");
		game3.deplacementHero("q");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("d");
		game3.deplacementHero("q");
		game3.deplacementHero("z");
		game3.deplacementHero("s");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
		
		game3.deplacementHero("d");
		game3.deplacementHero("q");
		game3.deplacementHero("s");
		game3.deplacementHero("z");
		assertSame(heros2.position_x,2);
		assertSame(heros2.position_y,2);
	}
	
	
	//Héros avec pdv négatif
	@Test
	public void testHerosPdvNegatifs() {
		Heros heros3 = new Heros(-2,2,2);  //Heros pdv negatifs
		ArrayList <Monstre> popMonstre2 = new ArrayList<Monstre>();
		Labyrinthe laby1 = new Labyrinthe(10,10);
		Game game4 = new Game(laby1,heros3,popMonstre2);
		assertNotSame(game4.hero.point_de_vie,0);
		assertFalse(game4.hero.testVivant());
	}
	
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testMonstrePositionNegative() throws Exception{
		Heros heros2 = new Heros(1,2,2);
		Monstre monstre5 = new Monstre(1,-3,4); //Monstre position negative
		Monstre monstre6 = new Monstre(1,3,-4); //Monstre position negative
		ArrayList <Monstre> popMonstre2 = new ArrayList<Monstre>();
		Labyrinthe laby1 = new Labyrinthe(10,10);
		assertTrue(popMonstre2.isEmpty());
		popMonstre2.add(monstre5); //Monstre position negative
		popMonstre2.add(monstre6); //Monstre position negative
		Game game7 = new Game(laby1,heros2,popMonstre2);
		assertFalse(game7.populationMonstre.isEmpty());            
	}
	
	@Test
	public void testMonstrePositionMur() {
		Heros heros2 = new Heros(1,2,2);
		ArrayList <Monstre> popMonstre2 = new ArrayList<Monstre>();
		Monstre monstre7 = new Monstre(1,1,0); //Monsre position mur
		Labyrinthe laby1 = new Labyrinthe(10,10);
		assertTrue(popMonstre2.isEmpty());
		popMonstre2.add(monstre7); //Monstre position mur
		Game game7 = new Game(laby1,heros2,popMonstre2);
		assertFalse(game7.populationMonstre.isEmpty());   //tester si le monstre est bien dans le mur         
	}
	
	@Test
	public void testMonstresCollesHeros() {
		Heros heros2 = new Heros(1,2,2);
		Monstre monstre8 = new Monstre(1,1,2); //Monstre collé au héros
		Monstre monstre9 = new Monstre(1,3,2); //Monstre collé au héros
		ArrayList <Monstre >popMonstre1 = new ArrayList<Monstre>(); 
		Labyrinthe laby1 = new Labyrinthe(10,10);
		popMonstre1.add(monstre8);
		popMonstre1.add(monstre9);
		Game game7 = new Game(laby1,heros2,popMonstre1);
		assertFalse(game7.populationMonstre.isEmpty());
		assertTrue(game7.hero.testVivant());
	}
	
	
	@Test
	public void testHerosPdvNegatifsFichier() throws Exception {
		Game game5 = new Game("testNiveauPdvHerosNegatif");
		int pdv = game5.hero.point_de_vie;
		assertNotSame(pdv,0);
		assertFalse(game5.hero.testVivant());
	}
	
	//affichage message erreur fichier dans console vient de ces tests
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testMonstrePostionNegativeFichier() throws Exception {
		Game game5 = new Game("testNiveauMonstrePositionNegative"); //Affiche "Erreur lors de la lecture du fichier" dans la console
	}
	
	//affichage message erreur fichier dans console vient de ces tests
	@Test (expected = Exception.class)
	public void testMonstrePostionMur() throws Exception {
		Game game5 = new Game("testNiveauMonstrePostionMur"); //Affiche "Erreur lors de la lecture du fichier" dans la console
	}
	
	@Test 
	public void testMonstresCollesHerosFichier() throws Exception {
		int [] tab = new int[2];
		Game jeu=new Game("testMonstresCollesHeros") ;     
		ArrayList<Monstre> monstre= jeu.populationMonstre;     
		Monstre a=monstre.get(0);
		Monstre b=monstre.get(1);
		jeu.attaque();
		if (a.point_de_vie==0) {
			tab[0]+=1;
		} else if (b.point_de_vie==0) {
			tab[1]+=1;
		}
		int compteur = 0; 
		boolean bool = true;
		while (compteur < 100 && bool) {
			jeu=new Game("testMonstresCollesHeros") ;     
			monstre= jeu.populationMonstre;     
			a=monstre.get(0);
			b=monstre.get(1);
			jeu.attaque();
			if (a.point_de_vie==0) {
				tab[0]+=1;
			} else if (b.point_de_vie==0) {
				tab[1]+=1;
			}
			if (tab[0] != 0 && tab[1]!=0) {
				bool = false; 
			}
			compteur+=1;
		}
		assertSame(bool,false);
	}
	
	@Test 
	public void testMonstresCollesHerosMemeTemps() throws Exception {
		int [] tab = new int[1];
		Game jeu=new Game("testMonstresCollesHeros") ;     
		ArrayList<Monstre> monstre= jeu.populationMonstre;     
		Monstre a=monstre.get(0);
		Monstre b=monstre.get(1);
		jeu.attaque();
		if (a.point_de_vie==0 && b.point_de_vie==0) {
			tab[0]+=1;
		} 
		int compteur = 0; 
		boolean bool = true;
		while (compteur < 30 && bool) {
			jeu=new Game("testMonstresCollesHeros") ;     
			monstre= jeu.populationMonstre;     
			a=monstre.get(0);
			b=monstre.get(1);
			jeu.attaque();
			if (a.point_de_vie==0 && b.point_de_vie==0) {
				tab[0]+=1;
			} 
			if (tab[0] != 0) {
				bool = false; 
			}
			compteur+=1;
		}
		assertNotSame(bool,false);
	}
	
	@Test
	public void testCombatNormal() throws Exception {
		Game jeu=new Game("testNiveauCombatNormal") ;     
		ArrayList<Monstre> monstre= jeu.populationMonstre;     
		Monstre a=monstre.get(0);
		assertNotSame(a.point_de_vie,0);
		jeu.attaque(); 
		assertSame(a.point_de_vie,0); 
	}
	
	@Test
	public void testCombatFantomeMur() throws Exception {
		Game jeu=new Game("testNiveauCombatFantomeMur") ;     
		ArrayList<Monstre> monstre= jeu.populationMonstre;     
		Monstre a=monstre.get(0);
		assertNotSame(a.point_de_vie,0);
		jeu.attaque(); 
		assertNotSame(a.point_de_vie,0); 
	}

//	affichage message erreur fichier dans console vient de ces tests
	@Test (expected = Exception.class)
	public void testLabyrintheSansHeros () throws Exception {
		
	Game game = new Game("testLabyrintheSansHeros"); //Retourne "Hero mal défini dans le fichier"
	//Affiche "Erreur lors de la lecture du fichier" dans la console
}
	
	@Test 
	public void testCaseSuperposee () throws Exception { //Des cases sont superposées en x = 3 et y = 2 
		Game game = new Game("testCaseSuperposee");
		
		assertSame(game.labyrinthe.laby[3][2].image,"sol"); //Lors de la lecture du fichier c'est derniere case ayant la position superposée qui est prise en compte
	}
	
	//affichage message erreur fichier dans console vient de ces tests
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testCoordonneeEnDehors () throws Exception {
		Game game = new Game("testCoordonneesEnDehors"); //Affiche "Erreur lors de la lecture du fichier" dans la console
	}
	//affichage message erreur fichier dans console vient de ces tests
	@Test (expected = Exception.class)
	public void testFichierValeursManquantes () throws Exception {
		Game game = new Game("testFichierValeursManquantes"); //Affiche "Erreur lors de la lecture du fichier" dans la console
	}
	
	//affichage message erreur fichier dans console vient de ces tests
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testCaseSpecialePostionInvalide () throws Exception { 
		Game game = new Game("testCasePositionInvalide");
	}
	

	
	@Test
	public void testNonActivationMagiqueParMonstre () throws Exception {
		Game game = new Game("testNonActivationMagiqueParMonstre");
		game.deplacementMonstre();
		assertNotSame(game.status,"victoire");
		assertSame(game.labyrinthe.laby[4][5].image,"sol");
	}
	
	@Test
	public void testNonActivationPassageParMonstre () throws Exception {
		Game game = new Game("testNonActivationPassageParMonstre");
		assertSame(game.populationMonstre.get(0).position_x,8);
		assertSame(game.populationMonstre.get(0).position_y,8);
		game.deplacementMonstre();
		assertSame(game.populationMonstre.get(0).position_x,8);
		assertSame(game.populationMonstre.get(0).position_y,7);
		assertNotSame(game.labyrinthe.laby[1][4].image,"monstre");
	}

	
	
	
	
	

	

	

}