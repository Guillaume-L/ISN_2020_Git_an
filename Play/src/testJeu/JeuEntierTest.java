package testJeu;

import static org.junit.Assert.*;

import java.util.ArrayList;

import jeu.*;

import org.junit.Before;
import org.junit.Test;

public class JeuEntierTest {
	public Heros perso1; 
	public Heros perso2; 
	public Heros perso4;	
	public Heros heros3;
	public Personnage perso3;
	public Labyrinthe laby1; 
	public Parcelle parcelle1;
	public Game game1;
	public Game game2;
	public Game game3;
	public Game game4; //Heros pdv negatifs
	public Game game5; //Heros pdv negatifs
	
	public Game game8;
	public Monstre monstre1;
	public Monstre monstre2; 
	public Monstre monstre3;
	public Monstre monstre4;
	
	public Monstre monstre5; //Monstre position negative
	public Monstre monstre6; //Monstre position negative
	public Monstre monstre7; //Monstre position mur
	
	public Monstre monstre8; //Monstre coll� au h�ros
	public Monstre monstre9; //Monstre coll� au h�ros
	
	public ArrayList<Monstre> popMonstre1; 
	public ArrayList<Monstre> popMonstre2;
	
	@Before      
	public void init()     {
		popMonstre1 = new ArrayList<Monstre>(); 
		popMonstre2 = new ArrayList<Monstre>();
		
		monstre1 = new Monstre(1,5,5);
		monstre2 = new Monstre(1,4,4); 
		monstre3 = new Monstre(1,6,6); 
		
		monstre5 = new Monstre(1,-3,4); //Monstre position negative
		monstre6 = new Monstre(1,3,-4); //Monstre position negative
		monstre7 = new Monstre(1,1,0); //Monsre position mur
		
		monstre8 = new Monstre(1,1,2); //Monstre coll� au h�ros
		monstre9 = new Monstre(1,3,2); //Monstre coll� au h�ros
		
		popMonstre1.add(monstre1);
		popMonstre1.add(monstre2);
		popMonstre1.add(monstre3);
		
		
		laby1 = new Labyrinthe(10,10);
		
		perso1 = new Heros();
		perso2 = new Heros(1,2,2);
		heros3 = new Heros(-2,2,2);  //Heros pdv negatifs
		perso4=new Heros(1,8,8);
		game2 = new Game(laby1,perso4,popMonstre1);
		game1 = new Game(laby1,perso1,popMonstre1);
		game3 = new Game(laby1,perso2,popMonstre1);
		
		game4 = new Game(laby1,heros3,popMonstre1); //Heros pdv negatifs
		
    } 
	
	//Fantome
	
		//Deplacement Fantome
	
	@Test //Verif si le fantome a bien chang� de position
	public void testDeplacementFantome() {
		monstre4 = new Fantome(1,4,2); //*******Attention coordonn�e y bizarre
		int old_x = monstre4.position_x;
		int old_y = monstre4.position_y;
		int[] coordonnee = monstre4.deplacementVersHero(perso1,laby1);
		int[] comparaisonNew = new int[2];
		comparaisonNew[0] = coordonnee[0];
		comparaisonNew[1] = coordonnee[1];
		int[] comparaisonOld = new int[2];
		comparaisonOld[0] = old_x;
		comparaisonOld[1] = old_y;
		assertNotSame(comparaisonNew, comparaisonOld);
	}
	@Test 
	public void double_depla()
	{game1.deplacementHero("sd");
	System.out.println(perso1.position_x);
	assertSame(perso1.position_x,1);
	}
	//Monstre 
	
		//Constructeur
//	@Test
//	public void testConstructeurMonstre () { 
//		
//	}
	
		//Deplacement
	
	@Test //Verif si le monstre a bien chang� de position
	public void testDeplacementMonstre() {
		monstre4 = new Monstre(1,4,2); //*******Attention coordonn�e y bizarre
		int old_x = monstre4.position_x;
		int old_y = monstre4.position_y;
		int[] coordonnee = monstre4.deplacementVersHero(perso1,laby1);
		int[] comparaisonNew = new int[2];
		comparaisonNew[0] = coordonnee[0];
		comparaisonNew[1] = coordonnee[1];
		int[] comparaisonOld = new int[2];
		comparaisonOld[0] = old_x;
		comparaisonOld[1] = old_y;
		assertNotSame(comparaisonNew, comparaisonOld);
	}
	
	//Heros
		
		//Constructeur par d�faut
	
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
		assertFalse(perso2.test_deplacement(laby1,2,2)); 
		assertTrue(perso2.test_deplacement(laby1,3,2));
		assertFalse(perso2.test_deplacement(laby1,11,2));
		assertFalse(perso2.test_deplacement(laby1,2,11));
		//laby1[2][1].visuel = "1";
		assertTrue(perso2.test_deplacement(laby1,2,1));
	}
	
		//testVivant() de Personnage
	@Test 
	public void testdeplacementmurhaut() {
		game1.deplacementHero("z");
		String a=game1.status;
		assertTrue(a.equalsIgnoreCase("Le h�ro bloque contre un mur"));
		
	}
	@Test 
	public void testdeplacementmurgauche() {
		game1.deplacementHero("q");
		String b=game1.status;
		assertTrue(b.equalsIgnoreCase("Le h�ro bloque contre un mur"));
	}
	@Test 
	public void testdeplacementmurbas() {
		game2.deplacementHero("s");
		String c=game2.status;
		
		String dh="Le h�ro bloque contre un mur";
		assertTrue(c.equalsIgnoreCase(dh));
	}
		@Test 
		public void testdeplacementmurdroite() {
		
			game2.deplacementHero("d");
			String c=game2.status;
			assertTrue(c.equalsIgnoreCase("Le h�ro bloque contre un mur"));
		}	
	
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
		assertSame(passage1.positionArriveeX,1); //Probleme
		assertSame(passage1.positionArriveeY,4); 
		//assertSame(passage1.population,0);
		assertSame(passage1.image,"passage");
	}
	
		//declenchement() de Passage
	
	
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
			assertFalse(piege1.visible);
			assertSame(piege1.visuel,"0");
			assertSame(piege1.image,"sol");
			piege1.declenchement(); 
			assertTrue(piege1.visible);
			assertSame(piege1.visuel,"X");
			assertSame(piege1.image,"piege");
			assertTrue(piege1.declenchement().equalsIgnoreCase("piege")); //Probleme

			//Manque un test pour savoir si le syso a �t� fait
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
	
//	@Test (expected=Erreurjeu.class)
//	public void testCreationLabyrinthe()throws Exception {
//		Labyrinthe laby4=new Labyrinthe();
//	}
	
	//Game
	
		//Constructeur de Game
	
	@Test 
	public void testConstructeurGame() {
		game1 = new Game(laby1,perso2);
	}
	
		//deplacementHero() de Game
	
	
	
		//peuplement() de Game
	
//	@Test
//	public void testPeuplementGame() {
//		//game1.peuplement(monstre1);
//	}
//	
//		//randomPopulationMonstre() de Game
//	
//	@Test
//	public void testRandomPopulationMonstreGame() {
//		//game1.randomPopulationMonstre(3);
//	}
//	
//		//affichage() de Game
//	
//	@Test
//	public void testAffichageGame() {
//		
//	}
//	
//		//resoudreCombat() de Game
//	
//	@Test
//	public void testResoudreCombatGame() {
//		
//	}
//	
//		//declencherEffetCase() de Game
//	
//	@Test
//	public void testDeclencherEffetCaseGame() {
//		
//	}
	
	
	
	//**********Sc�nario "D�placement"**********
	
	//Sc�nario nominal 
	
	@Test
	public void testDeplacementHeros() {
		game3.deplacementHero("z");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,1);
		game3.deplacementHero("s");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		game3.deplacementHero("d");
		assertSame(perso2.position_x,3);
		assertSame(perso2.position_y,2);
		game3.deplacementHero("q");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
	}
	
	//Sc�narios alternatifs
	
	//Au d�part le h�ros est � la position (2,2)
	//A
	@Test
	public void testDeplacementHerosA1() {
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
	}
	
	//B
	@Test
	public void testDeplacementHerosB1() {
		game3.deplacementHero("z");
		game3.deplacementHero("s");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		game3.deplacementHero("d");
		game3.deplacementHero("q");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
	}
	
	//C
	@Test
	public void testDeplacementHerosC1() {
		game3.deplacementHero("g");
		game3.deplacementHero("h");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
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
	//D
	@Test
	public void testDeplacementHerosD1() {
		
		//3 touches en m�me temps
		game3.deplacementHero("z");
		game3.deplacementHero("s");
		game3.deplacementHero("d");
		assertSame(perso2.position_x,3);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("z");
		game3.deplacementHero("s");
		game3.deplacementHero("q");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("z");
		game3.deplacementHero("q");
		game3.deplacementHero("d");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,1);
		
		game3.deplacementHero("q");
		game3.deplacementHero("s");
		game3.deplacementHero("d");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		//plus de 3 touches
		game3.deplacementHero("z");
		game3.deplacementHero("s");
		game3.deplacementHero("d");
		game3.deplacementHero("q");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("z");
		game3.deplacementHero("s");
		game3.deplacementHero("q");
		game3.deplacementHero("d");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("z");
		game3.deplacementHero("q");
		game3.deplacementHero("d");
		game3.deplacementHero("s");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("z");
		game3.deplacementHero("q");
		game3.deplacementHero("s");
		game3.deplacementHero("d");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("z");
		game3.deplacementHero("d");
		game3.deplacementHero("q");
		game3.deplacementHero("s");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("z");
		game3.deplacementHero("d");
		game3.deplacementHero("s");
		game3.deplacementHero("q");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("q");
		game3.deplacementHero("z");
		game3.deplacementHero("s");
		game3.deplacementHero("d");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("q");
		game3.deplacementHero("z");
		game3.deplacementHero("d");
		game3.deplacementHero("s");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("q");
		game3.deplacementHero("s");
		game3.deplacementHero("z");
		game3.deplacementHero("d");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("q");
		game3.deplacementHero("s");
		game3.deplacementHero("d");
		game3.deplacementHero("z");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("q");
		game3.deplacementHero("d");
		game3.deplacementHero("z");
		game3.deplacementHero("s");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("q");
		game3.deplacementHero("d");
		game3.deplacementHero("s");
		game3.deplacementHero("z");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("s");
		game3.deplacementHero("z");
		game3.deplacementHero("q");
		game3.deplacementHero("d");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("s");
		game3.deplacementHero("z");
		game3.deplacementHero("d");
		game3.deplacementHero("q");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("s");
		game3.deplacementHero("q");
		game3.deplacementHero("z");
		game3.deplacementHero("d");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("s");
		game3.deplacementHero("q");
		game3.deplacementHero("d");
		game3.deplacementHero("z");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("s");
		game3.deplacementHero("d");
		game3.deplacementHero("z");
		game3.deplacementHero("q");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("s");
		game3.deplacementHero("d");
		game3.deplacementHero("q");
		game3.deplacementHero("z");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("d");
		game3.deplacementHero("z");
		game3.deplacementHero("q");
		game3.deplacementHero("s");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("d");
		game3.deplacementHero("z");
		game3.deplacementHero("s");
		game3.deplacementHero("q");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("d");
		game3.deplacementHero("s");
		game3.deplacementHero("q");
		game3.deplacementHero("z");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("d");
		game3.deplacementHero("s");
		game3.deplacementHero("z");
		game3.deplacementHero("q");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("d");
		game3.deplacementHero("q");
		game3.deplacementHero("z");
		game3.deplacementHero("s");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
		
		game3.deplacementHero("d");
		game3.deplacementHero("q");
		game3.deplacementHero("s");
		game3.deplacementHero("z");
		assertSame(perso2.position_x,2);
		assertSame(perso2.position_y,2);
	}
	
	//Sc�narios exceptionnels 
	
	//E1
	//Si le joueur quitte le jeu on ne peut pas tester
	
	//E2
	//Difficile de similer des tests d'affichage
	
	
	//**********Sc�nario "V�rification des cases"**********
	
	//Sc�nario nominal
	
	
	
	//Sc�narios alternatifs
	
	//A1
	
	//voir les test plus haut dans le programme
	
	//Sc�narios exceptionnels
	
	
	//**********Sc�narios "G�n�ration Labyrinthe"**********
	
	//H�ros avec pdv n�gatif
	@Test
	public void testHerosPdvNegatifs() {
		Game game7 = new Game(laby1,heros3,popMonstre2);
		assertNotSame(game7.hero.point_de_vie,0);
		assertFalse(game7.hero.testVivant());
	}
	
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testMonstrePositionNegative() throws Exception{
		assertTrue(popMonstre2.isEmpty());
		popMonstre2.add(monstre5); //Monstre position negative
		popMonstre2.add(monstre6); //Monstre position negative
		Game game7 = new Game(laby1,perso2,popMonstre2);
		assertFalse(game7.populationMonstre.isEmpty());            
	}
	
	@Test
	public void testMonstrePositionMur() {
		assertTrue(popMonstre2.isEmpty());
		popMonstre2.add(monstre7); //Monstre position mur
		Game game7 = new Game(laby1,perso2,popMonstre2);
		assertFalse(game7.populationMonstre.isEmpty());   //tester si le monstre est bien dans le mur         
	}
	
	@Test
	public void testMonstresCollesHeros() {
		popMonstre1.add(monstre8);
		popMonstre1.add(monstre9);
		Game game7 = new Game(laby1,perso2,popMonstre1);
		assertFalse(game7.populationMonstre.isEmpty());
		assertTrue(game7.hero.testVivant());
	}
	
	//**********Sc�narios "G�n�ration Labyrinthe � partir d'un fichier"**********
	@Test
	public void testHerosPdvNegatifsFichier() throws Exception {
		game5 = new Game("testNiveauPdvHerosNegatif");
		int pdv = game5.hero.point_de_vie;
		assertNotSame(pdv,0);
		assertFalse(game5.hero.testVivant());
	}
	
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testMonstrePostionNegativeFichier() throws Exception {
		game5 = new Game("testNiveauMonstrePositionNegative"); 
	}
	
	@Test (expected = Exception.class)
	public void testMonstrePostionMur() throws Exception {
		game5 = new Game("testNiveauMonstrePostionMur"); 
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
	
@Test
public void testnontriggerfin() throws Exception{
	
	Game jeu=new Game("testmonstremarchecase") ;     
	ArrayList<Monstre> monstre= jeu.populationMonstre;     
	Monstre a=monstre.get(0);
	int old_x = a.position_x;
	int old_y = a.position_y;
	jeu.deplacementMonstre();
	int new_x = a.position_x;
	int new_y = a.position_y;
	int[] comparaisonNew = new int[2];
	comparaisonNew[0] = new_x;
	comparaisonNew[1] = new_y;
	int[] comparaisonOld = new int[2];
	comparaisonOld[0] = old_x;
	comparaisonOld[1] = old_y;
	int[] comparaisonTresor = new int[2];
	comparaisonTresor[0] = 5;
	comparaisonTresor[1] = 4;
	assertNotSame(comparaisonNew, comparaisonOld);
	
	
	
	assertNotSame(jeu.status,"Victoire : le h�ro a r�cup�r� le tr�sor");
	
	
}
@Test
public void testnontriggerpiege() throws Exception{
	
	Game jeu=new Game("testmonstremarchecasepiege") ;     
	ArrayList<Monstre> monstre= jeu.populationMonstre;     
	Monstre a=monstre.get(0);
	int old_x = a.position_x;
	int old_y = a.position_y;
	jeu.deplacementMonstre();
	int new_x = a.position_x;
	int new_y = a.position_y;
	int pdv_av =a.point_de_vie;
	int[] comparaisonNew = new int[2];
	comparaisonNew[0] = new_x;
	comparaisonNew[1] = new_y;
	int[] comparaisonOld = new int[2];
	comparaisonOld[0] = old_x;
	comparaisonOld[1] = old_y;
	int[] comparaisonPiege = new int[2];
	comparaisonPiege[0] = 5;
	comparaisonPiege[1] = 4;
	assertNotSame(comparaisonNew, comparaisonOld);
	System.out.println(comparaisonNew[0]);
	System.out.println(comparaisonNew[1]);
	
	int pdv_ap=a.point_de_vie;
	assertEquals(comparaisonNew[0],comparaisonPiege[0]);
	assertEquals(comparaisonNew[1],comparaisonPiege[1]);
	assertEquals(pdv_ap,pdv_av);
	
	
}
@Test
public void testdeplacementmonstrecoin() throws Exception{
	
	Game jeu=new Game("testmonstremarchecoin") ;     
	ArrayList<Monstre> monstre= jeu.populationMonstre;     
	Monstre a=monstre.get(0);
	int old_x = a.position_x;
	int old_y = a.position_y;
	jeu.deplacementMonstre();
	int new_x = a.position_x;
	int new_y = a.position_y;
	int[] comparaisonNew = new int[2];
	comparaisonNew[0] = new_x;
	comparaisonNew[1] = new_y;
	int[] comparaisonOld = new int[2];
	comparaisonOld[0] = old_x;
	comparaisonOld[1] = old_y;
	assertNotSame(comparaisonOld,comparaisonNew);}
}
