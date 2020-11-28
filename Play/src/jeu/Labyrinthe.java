
package jeu;
import java.util.Scanner;
public class Labyrinthe {int longueur; int largeur; Parcelle[][] laby;

	Labyrinthe(int plongueur,int plargeur) {
		longueur=plongueur;
		largeur=plargeur;
	
		Parcelle[][] lab = new Parcelle[longueur][largeur];
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
					 lab[abs][ord]=new Mur();
					 }
					 catch(Exception e) {System.out.println("paramètre non valide");}
				 }
			}
		laby=lab;
	    }
	}
}





	
	
	



