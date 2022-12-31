/**
 * Class SélectionPays, écoute la fenetre "Fenetres" et piège le clic sur un bouton
 * pour lancer le drapeau sélectionné 
 * 
 * @author Loic,Léo-Paul
 * @version V0_1_0;
 * 
 */

package ApplicationDrapeau;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedHashMap;


public class EcouteurSelectionPays implements ActionListener{

	 /**
	 * Lien vers le ControleurDrapeau
	 */
	private ControleurDrapeau2 createur;
	/**
	 * Lien vers la fenetre descriptive
	 */
	private PanelDescriptif vue_wb;
	/**
	 * Lien vers le thread pour le stoper
	 */
	private Thread lethread;
	/**
	 * Lien vers la fenetre dont il doit changer le JPanel
	 */
	private Drapeau lafram;
	/**
	 * Pays
	 */
	private String pays;
	/**
	 * Modele
	 */
	private ModeleDrapeau modele_1;
	


	/**
	 * Constructeur normal de l'écouteur
	 * @param createur
	 * @param vue_wb
	 * @param lethread
	 * @param lafram
	 * @param pays
	 * @param modele_1
	 */
	// --------------------- Constructeur normal (1)
	public EcouteurSelectionPays(ControleurDrapeau2 createur, PanelDescriptif vue_wb, Thread lethread, Drapeau lafram, String pays
								,ModeleDrapeau modele_1){
		this.createur = createur;
		this.vue_wb = vue_wb;
		this.lafram = lafram;
		this.setPays(pays);
		this.setLethread(lethread);
		this.setModele_1(modele_1);
	}
	
	
	/**
	 * Méthode de l'action sur le bouton 
	 */
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		LinkedHashMap<?, ?> donnees = null;
		
		vue_wb.getTField().setText(pays);
		this.setPays(pays);
		createur.getLethread().stop();
		lafram.setPays(pays);
	
		donnees = ModeleDrapeau.load(pays+"_drapeau");
		lafram.addMouseListener(new EcouteurDrapeau(lafram, createur,pays));
		lafram.addMouseMotionListener(new EcouteurDrapeau(lafram,createur,pays));
		
		if (((String) donnees.get("Image")) == null)
			lafram.setContentPane(createur.creer_drapeau(donnees));
		else
			lafram.setContentPane(createur.creer_drapeauIM(donnees));
		
		try {createur.setFrame(lafram, donnees);}
		catch (IOException e1) {e1.printStackTrace();}
	
		lafram.setVisible(true);
		createur.affiche_cache(lafram, vue_wb);
		
	}

	/**
	 * Getter de Thread
	 * @return un thread
	 */
	public Thread getLethread() {return lethread;}
	/**
	 * Setter de Thread
	 * @param lethread
	 */
	public void setLethread(Thread lethread) {this.lethread = lethread;}
	/**
	 * Getter de Pays 
	 * @return le pays
	 */
	public String getPays() {return pays;}
	/**
	 * Setter de Pays
	 * @param pays
	 */
	public void setPays(String pays) {this.pays = pays;}
	public ModeleDrapeau getModele_1() {return modele_1;}
	public void setModele_1(ModeleDrapeau modele_1) {this.modele_1 = modele_1;}

}
