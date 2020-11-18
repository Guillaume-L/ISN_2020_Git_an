package projet_jeux;

public class Tresor extends Case{
	
	public Tresor(boolean presence, boolean visible) {
		super(visible, true);
		String visu = new String();
		if (visible) {
			visu = "T";
		}
		else {
			visu = "-";
		}
		this.visuel = visu;
	}

}
