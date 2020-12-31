package jeu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Moniteur extends JFrame { 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1710290985225719229L;


	public Moniteur(Labyrinthe labyrinthe) {
		super("écran de jeu");
//		this.setPreferredSize(new Dimension(600, 600));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener( new WindowAdapter() {
			
			public void windowClosing(java.awt.event.WindowEvent e) {
				int clickedButton = JOptionPane.showConfirmDialog(Moniteur.this, "Etes-vous sûr de vouloir quitter ?",
						"Confirmation", JOptionPane.YES_NO_OPTION);
				if ( clickedButton == JOptionPane.YES_OPTION) {
					Moniteur.this.dispose();
				}
			}
		});
		JPanel contentPane = (JPanel) this.getContentPane();
		contentPane.removeAll();
		contentPane.setLayout( new BorderLayout());
		this.pack();
//		this.setVisible(true); // semble mieux marcher si à la fins
	}
	
	private JPanel chargerNiveau(String nomFichier) {
		
		return null;
		
	}
	public static ImageIcon formatageIcon(String nomFichierImage,int dimension) {
		ImageIcon icon = new ImageIcon(nomFichierImage + ".png");
		Image image = icon.getImage();
		Image imageScale = image.getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
		ImageIcon iconScale = new ImageIcon(imageScale);

		return iconScale;
	}
	public void affichage(Labyrinthe labyrinthe, Heros hero, String status) {
		JPanel contentPane = (JPanel) this.getContentPane();
		contentPane.removeAll();
		contentPane.setLayout( new BorderLayout());
		contentPane.add( this.createVueLabyrinthe(labyrinthe), BorderLayout.NORTH);
		contentPane.add(this.createStatusBar(hero, status),BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true); // semble mieux marcher si à la fins
	}

	public JPanel createStatusBar(Heros hero, String status) {
		JPanel statusBar = new JPanel (new BorderLayout() );
		JLabel labelStatusVie = new JLabel("Le héro a " + String.valueOf(hero.point_de_vie) + " point(s) de vie");
		labelStatusVie.setPreferredSize(new Dimension(300, 60));
		statusBar.add( labelStatusVie, BorderLayout.WEST );
		JLabel labelStatus = new JLabel(status);
		statusBar.add( labelStatus, BorderLayout.EAST);
		JLabel commande = new JLabel("Déplacement: Z,Q,S,D attaque: ESPACE");
		statusBar.add( commande, BorderLayout.SOUTH);
		return statusBar;
	}
	public JPanel createVueLabyrinthe(Labyrinthe labyrinthe) {
		JPanel panneauLabyrinthe = new JPanel();
		panneauLabyrinthe.setLayout( new GridBagLayout());
		GridBagConstraints contrainte = new GridBagConstraints();
		int dimension = 40;
		for (int i = labyrinthe.longueur -1; i >= 0 ; i--) {
			for (int j = labyrinthe.largeur - 1; j >= 0; j--) {
				String nomFichierImage = labyrinthe.laby[j][i].image;
				if (labyrinthe.laby[j][i].testPresence() || labyrinthe.laby[j][i].testPresenceCombat()) {
					boolean survivantIntrouvable = true;
					int k = 0;
					while (survivantIntrouvable) {
						if (labyrinthe.laby[j][i].population.get(k).enCombat) {
							if (labyrinthe.laby[j][i].population.get(k).image.equalsIgnoreCase("hero") && (!labyrinthe.laby[j][i].population.get(k).testVivant())) {
								labyrinthe.laby[j][i].population.get(k).enCombat = false;
								survivantIntrouvable = false;
							}
							else {
								nomFichierImage = labyrinthe.laby[j][i].population.get(k).image + nomFichierImage + "blessure";
								survivantIntrouvable = false;
								labyrinthe.laby[j][i].population.get(k).enCombat = false;
							}
						}
						else {
							if (labyrinthe.laby[j][i].population.get(k).testVivant()) {
								nomFichierImage = labyrinthe.laby[j][i].population.get(k).image + nomFichierImage;
								survivantIntrouvable = false;
							}
						}
						k++;
					}
				}
				ImageIcon icon = this.formatageIcon(nomFichierImage, dimension);
				JLabel etiquette = new JLabel(icon);
				etiquette.setPreferredSize(new Dimension(dimension,dimension));
				contrainte.gridx = i;
				contrainte.gridy = j;
				panneauLabyrinthe.add(etiquette, contrainte);
			}
		}
		return panneauLabyrinthe;
	}
	public JPanel createMenu() {
		JPanel panneauMenu = new JPanel(new BorderLayout() );
		JButton buttonCreation = new JButton("Créer un niveau");
		buttonCreation.addMouseListener( new MouseAdapter() {
			
			public void mouseEntered(MouseEvent e) {
				buttonCreation.setBackground( Color.BLACK );
				buttonCreation.setForeground( Color.WHITE );
			}
			public void mouseExited(MouseEvent e) {
				buttonCreation.setForeground( Color.BLACK );
				buttonCreation.setBackground( Color.WHITE );
			}
			public void mouseClicked(MouseEvent e) {
				System.out.println("Bouton création cliqué");
			}
		});
		JButton buttonSelection = new JButton("Charger un niveau");
		buttonSelection.addMouseListener( new MouseAdapter() {
			
			public void mouseEntered(MouseEvent e) {
				buttonSelection.setBackground( Color.BLACK );
				buttonSelection.setForeground( Color.WHITE );
			}
			public void mouseExited(MouseEvent e) {
				buttonSelection.setForeground( Color.BLACK );
				buttonSelection.setBackground( Color.WHITE );
			}
			public void mouseClicked(MouseEvent e) {
				panneauMenu.add( createMenuSelection(), BorderLayout.SOUTH);
				Moniteur.this.pack();
			}
		});
		panneauMenu.add(buttonCreation, BorderLayout.WEST);
		panneauMenu.add(buttonSelection, BorderLayout.EAST);
		return panneauMenu;
	}
	public JPanel createMenuSelection() {
		JPanel panneauMenuSelection = new JPanel(new BorderLayout());
		JComboBox <String> menuDeroulant = new JComboBox<String>();
		ArrayList<String> listeNomNiveau = new ArrayList<String>();
		listeNomNiveau.add("niveau1");
		listeNomNiveau.add("niveau2");
		for (String nomNiveau : listeNomNiveau) {
			menuDeroulant.addItem(nomNiveau);
		}
		menuDeroulant.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
			}
			
		});
		panneauMenuSelection.add(menuDeroulant, BorderLayout.SOUTH);
		JLabel consigne = new JLabel("Selectionner le niveau à charger :");
		panneauMenuSelection.add(consigne, BorderLayout.NORTH);
		return panneauMenuSelection;
	}
}
