package projet_jeux;

public class Piege extends Case{
	
	public Piege(boolean visible) {
		super(visible, true);
		String visu = new String();
		if (visible) {
			visu = "X";
		}
		else {
			visu = "-";
		}
		this.visuel = visu;
	}

}
