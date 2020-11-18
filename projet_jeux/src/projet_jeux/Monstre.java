package projet_jeux;

public class Monstre extends Personnage{
	int numero;
	static int  nbMonstre = 0;
	
	public Monstre(int pointDeVie, int positionX, int positionY) {
		super(pointDeVie, positionX, positionY);
		this.visuel = "M";
		nbMonstre++;
		numero = nbMonstre;
	}

}
