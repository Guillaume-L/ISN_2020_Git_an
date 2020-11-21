package jeu;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Monstre Ted=new Monstre();

Labyrinthe test=new Labyrinthe(4,6);
int[][] a=test.laby;

for (int i=0;i<a.length;i++) {
	System.out.println(" ");
	for (int j=0;j<a[i].length;j++)
	{System.out.print(a[i][j]);}}

Heros H=new Heros();
System.out.println(H.pdv);
H.vivacite(100);
System.out.println(H.pdv);
}}
