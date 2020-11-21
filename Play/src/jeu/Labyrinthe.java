
package jeu;

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
	

}



	
	
	



