
package jeu;
import java.util.Scanner;
public class Labyrinthe {int longueur; int largeur; public Parcelle[][] laby;

	public Labyrinthe(int plongueur,int plargeur) {
		longueur=plongueur;
		largeur=plargeur;
	
		Parcelle[][] lab = new Parcelle[largeur][longueur];
		for (int i=0;i<lab.length;i++) {
			for (int j=0;j<lab[i].length;j++)
			{if (i==0 || j==0 || i==lab.length-1 || j==lab[i].length-1) {
				
				lab[i][j] = new Mur();
			}
				else {
					lab[i][j] = new Sol();
				}
			}
		}
	laby=lab;
	}
	public void revelerTresor() {
		for (int i = 0; i < this.laby.length; i++) {
			for (int j = 0; j < this.laby[i].length; j++) {
				this.laby[i][j].reveler();
			}
		}
	}




public Labyrinthe ()throws Erreurjeu {
	
	boolean err= false;
	Scanner myObj = new Scanner(System.in);  
	Scanner myObj1 = new Scanner(System.in);

    boolean a=true;
    while (!err) {
    		try {
    	    System.out.println("Longueur du labyrinthe ? ");
    	    String longue = myObj.nextLine();
    	    System.out.println("Largeur du labyrinthe ? ");
    	    String larg = myObj.nextLine(); 
    	    longueur=Integer.parseInt(longue);
    	    largeur=Integer.parseInt(larg);
    	    if (longueur < 0 || largeur < 0) {
    	    	
    	    	throw new Erreurjeu(" indice invalide");
    	    	
    	    }
    	    	
    	    
    	    else
    	    	err = true;
    	}
    		catch(Exception e) {
    			System.out.println("coordonnées non valide");}	
    		}
    	    

    	
    

	Parcelle[][] lab=new Parcelle[largeur][longueur];
	for (int i=0;i<lab.length;i++) {
		for (int j=0;j<lab[i].length;j++)
		{if (i==0 || j==0 || i==lab.length-1 || j==lab[i].length-1)
{lab[i][j]=new Mur();
		}else{lab[i][j]=new Sol();}}}
	 
String mur_x="début";
String mur_y="a";
String fin="fin";
int abs=0;
int ord=0;
int placeDisponible = ((largeur - 2) * (longueur - 2)) - 3;
while((fin.equals(mur_x)!=true) && (placeDisponible > 0)){
	 System.out.println("abscisse du mur ? (tapez fin si vous avez placé tous vos murs) ");
	 mur_x = myObj.nextLine();
	 if (!mur_x.equals("fin")) {
		 try {abs=Integer.parseInt(mur_x);
		 System.out.println("ordonnée du mur ? ");
		 mur_y = myObj1.nextLine();
		 try {ord=Integer.parseInt(mur_y);}
		 catch(Exception e) {System.out.println("paramètre non valide");}
		lab[ord][abs]=new Mur();
		placeDisponible--;
		 }
		 catch(Exception e) {System.out.println("paramètre1 non valide");}
	 }
}
laby=lab;}


	Labyrinthe(Scanner scan){
		boolean err= false;
	    System.out.println("Longueur du labyrinthe ? ");
	
	    String longue = scan.nextLine();  
	    try {
	    	longueur=Integer.parseInt(longue);}
	    catch(Exception e) {
	    	System.out.println("paramètre invalide");
	    	err=true;}
	
	    System.out.println("Largeur du labyrinthe ? ");
	
	    String larg = scan.nextLine(); 
	    try {
	    	largeur=Integer.parseInt(larg);}
	    catch(Exception e1) {
	    	System.out.println("paramètre invalide");
	    	err=true;
	    }
	    if(err==false) {
	    	Parcelle[][] lab=new Parcelle[longueur][largeur];
			for (int i=0;i<lab.length;i++) {
				for (int j=0;j<lab[i].length;j++) {
					if (i==0 || j==0 || i==lab.length-1 || j==lab[i].length-1) 
						lab[i][j]=new Mur();
					else
						lab[i][j]=new Sol();
					}
				}
			String mur_x="début";
			String mur_y="a";
			String fin="fin";
			int abs=0;
			int ord=0;
			while(fin.equals(mur_x)!=true){
				 System.out.println("abscisse du mur ? (tapez fin si vous avez placé tous vos murs) ");
				 mur_x = scan.nextLine();
				 if (mur_x!="fin") {
					 try {
						 abs=Integer.parseInt(mur_x);
						 System.out.println("ordonnée du mur ? ");
						 mur_y = scan.nextLine();
					 try {
						 ord=Integer.parseInt(mur_y);
						 }
					 catch(Exception e) {
						 System.out.println("paramètre non valide");
						 }
					 lab[ord][abs]=new Mur();
					 }
					 catch(Exception e) {System.out.println("paramètre non valide");}
				 }
			}
		laby=lab;
	    }
	}

}





	
	
	



