package projet_jeux;

public class CaseMagique extends Case{
	boolean revelation;
	
	public CaseMagique(boolean revelation, String visuel) {
		super(true, true, "0");
		this.revelation = revelation;
	}

}
