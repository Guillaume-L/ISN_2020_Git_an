package jeu;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Monstre Ted=new Monstre();
Ted.deplacement();
Labyrinthe test=new Labyrinthe(4,6);
int[][] a=test.creer_lab();

for (int i=0;i<a.length;i++) {
	System.out.println(" ");
	for (int j=0;j<a[i].length;j++)
	{System.out.print(a[i][j]);}}
System.out.print("BG");
}}
