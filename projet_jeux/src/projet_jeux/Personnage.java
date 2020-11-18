package projet_jeux;

public abstract class Personnage {
	int pointDeVie;
	int positionX;
	int positionY;
	String visuel;
	
	public Personnage(int pointDeVie, int positionX, int positionY) {
		this.pointDeVie = pointDeVie;
		this.positionX = positionX;
		this.positionY = positionY;
	}
	public boolean testVivant() {
		return (this.pointDeVie > 0);
	}
}
