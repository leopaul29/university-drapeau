/**
 * Class EcouteurJoueurAction, écoute la sélection de la ComboBox 
 * entre : "Hymne, Paysage et Carte" et à la pression du bouton Lancer
 * lance la fenetre qui va bien  
 * 
 *@author Loic,Léo-Paul
 *@version V0_2_0;
 * 
 */
package ApplicationDrapeau;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class EcouteurJouerAction implements ActionListener{
	/**
	 * Hameçon vers le createur
	 */
	private ControleurDrapeau2 createur;
	/**
	 * Vitesse du diaporama, initialisé à 1s/photo par défaut
	 */
	private int vitesse = 1000;
	
	/**
	 * Constructeur normal de l'écouteur
	 * @param createur hameçon
	 */
	// --------------------- Constructeur normal (1)
	public EcouteurJouerAction(ControleurDrapeau2 createur){
		this.createur = createur;
	}
	
	/**
	 * Méthode au clic sur Lancer
	 * Récupère la valeur de la ComboBox Action, et exécute ce qu'il faut
	 */
	public void actionPerformed(ActionEvent e) {
		String action = (String) createur.getVue_wb().getComboBox_action().getSelectedItem();
		String pays = createur.getVue_wb().getTField().getText();
		
		//Si on ne sélectionne pas de pays 
		if(createur.getVue_wb().getTField().getText().isEmpty()){
			JDialog jd = new JDialog();
			jd.setSize(270, 100);
			JPanel erreur = new JPanel();
			erreur.setBackground(Color.red);
			erreur.add(new JLabel("Aucun pays n'est affiché .."));
			jd.setContentPane(erreur);
			jd.setVisible(true);
		}else{
			if(action == "Hymne"){
				HymneMP3 dh  =new HymneMP3(pays);
			}else if(action =="Carte"){
				FenetreCarte carte = new FenetreCarte(pays);
				carte.setVisible(true);
			}else if(action == "Paysage"){
				final FenetrePaysage fp = new FenetrePaysage(pays);
				fp.getLeft().addActionListener(new EcouteurBouton(fp,-1));
				fp.getRight().addActionListener(new EcouteurBouton(fp,1));
				createur.getVue_wb().getSlider().addChangeListener(new EcouteurSliderDiaporama(null,createur,1));
	
				Thread leThread = new Thread( new Runnable() {
					public synchronized void run() {
						for(int i = 0; i< 12;i++){
							if(i == 11) i=0;
							fp.setCheck(i);
							fp.repaint();
							try {this.wait(vitesse);}
							catch (InterruptedException e) {e.printStackTrace();		}
						}
					}});
				leThread.start();
				fp.setVisible(true);
			}
		}
	}
	
	/**
	 * Getter de la vitesse du diaporama
	 * @return la vitesse du diaporama
	 */
	public int getVitesse() {return vitesse;}
	/**
	 * Setter de la vitesse du diaporama
	 * @param vitesse
	 */
	public void setVitesse(int vitesse) {this.vitesse = vitesse;}
	
}
