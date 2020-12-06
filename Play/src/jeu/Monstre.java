package jeu;
import java.util.ArrayList;
import java.util.Random;
public class Monstre extends Personnage{

	public Monstre(int point_de_vie, int position_x, int position_y) {
		this.point_de_vie = point_de_vie;
		this.position_x = position_x;
		this.position_y = position_y;
		this.visuel = "E";
		this.image = "monstre";
	}
	
public void deplacement(Labyrinthe L) {
	boolean deplac;
	int[] coord=new int[2];
	ArrayList<int[]> deplacement_possib= new ArrayList<int[]>();
	int x=this.position_x+1;
	int y=this.position_y;
	coord[0]=x;
	coord[1]=y;
	deplac=this.test_deplacement(L,x,y);
	if (deplac) {deplacement_possib.add(coord);}
	 x=this.position_x-1;
	 y=this.position_y;
	coord[0]=x;
	coord[1]=y;
	deplac=this.test_deplacement(L,x,y);
	if (deplac) {deplacement_possib.add(coord);}
	 x=this.position_x;
	 y=this.position_y+1;
	coord[0]=x;
	coord[1]=y;
	deplac=this.test_deplacement(L,x,y);
	if (deplac) {deplacement_possib.add(coord);}
	 x=this.position_x+1;
	 y=this.position_y-1;
	coord[0]=x;
	coord[1]=y;
	deplac=this.test_deplacement(L,x,y);
	if (deplac) {deplacement_possib.add(coord);}
	int taille=deplacement_possib.size();
	Random rand= new Random();
	int[] move=deplacement_possib.get(rand.nextInt(taille));
	this.position_x=move[0];
	this.position_y=move[1];
	
}
	

}
