package projet_jeux;

public class Hero extends Personnage{
	
	public Hero(int pointDeVie, int positionX, int positionY) {
		super(pointDeVie, positionX, positionY);
		this.visuel = "H";
	}
	public void subirDegats() {
		this.pointDeVie --;
		if (this.pointDeVie < 1) {
			this.visuel = "X";
		}
	}
}
