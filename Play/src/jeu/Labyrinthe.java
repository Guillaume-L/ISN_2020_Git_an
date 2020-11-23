
package jeu;
import java.util.Scanner;
public class Labyrinthe {int longueur; int largeur; int[][] laby;

Labyrinthe(int plongueur,int plargeur) {longueur=plongueur;
largeur=plargeur;

	int[][] lab=new int[longueur][largeur];
	for (int i=0;i<lab.length;i++) {
		for (int j=0;j<lab[i].length;j++)
		{if (i==0 || j==0 || i==lab.length-1 || j==lab[i].length-1)
{lab[i][j]=1;
		}}}
laby=lab;

}
Labyrinthe(){
	boolean err= false;
	Scanner myObj = new Scanner(System.in);  
	Scanner myObj1 = new Scanner(System.in);
    System.out.println("Longueur du labyrinthe ? ");
    
    String longue = myObj.nextLine();  
    try {
    longueur=Integer.parseInt(longue);}
    catch(Exception e) {
    	System.out.println("paramètre invalide");
    	err=true;}
    
     
    System.out.println("Largeur du labyrinthe ? ");
  
 
    
    
    String larg = myObj.nextLine(); 
    try {
    largeur=Integer.parseInt(larg);}
    catch(Exception e1) {
    	System.out.println("paramètre invalide");
    	err=true;
    }
   if(err==false) {
	int[][] lab=new int[longueur][largeur];
	for (int i=0;i<lab.length;i++) {
		for (int j=0;j<lab[i].length;j++)
		{if (i==0 || j==0 || i==lab.length-1 || j==lab[i].length-1)
{lab[i][j]=1;
		}}}
	 
String mur_x="début";
String mur_y="a";
String fin="fin";
int abs=0;
int ord=0;
while(fin.equals(mur_x)!=true){
	 System.out.println("abscisse du mur ? (tapez fin si vous avez placé tous vos murs) ");
	 mur_x = myObj.nextLine();
	 if (mur_x!="fin") {
		 try {abs=Integer.parseInt(mur_x);
		 System.out.println("ordonnée du mur ? ");
		 mur_y = myObj1.nextLine();
		 try {ord=Integer.parseInt(mur_y);}
		 catch(Exception e) {System.out.println("paramètre non valide");}
		lab[abs][ord]=1;
		 }
		 catch(Exception e) {System.out.println("paramètre non valide");}
	 }
}
laby=lab;}}
}





	
	
	



