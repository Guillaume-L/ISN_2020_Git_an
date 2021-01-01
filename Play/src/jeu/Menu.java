package jeu;

import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3766229019909723441L;

	public Menu() {
		super("Menu");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener( new WindowAdapter() {
			
			public void windowClosing(java.awt.event.WindowEvent e) {
				int clickedButton = JOptionPane.showConfirmDialog(Menu.this, "Etes-vous sûr de vouloir quitter ?",
						"Confirmation", JOptionPane.YES_NO_OPTION);
				if ( clickedButton == JOptionPane.YES_OPTION) {
					Menu.this.dispose();
				}
			}
		});
	}
}
