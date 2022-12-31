/**
 * Class EcouteurBoutonPays, elle �coute le bouton Afficher de "Choisir un pays :"
 * de la fenetre descriptive du drapeau
 * 
 * @author Loic, L�o-Paul
 * 
 * @version V0_1_0;
 * 
 * 
 */
package ApplicationDrapeau;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedHashMap;

import javax.swing.JPanel;


public class EcouteurBoutonPaysJaxB implements ActionListener{

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
	private DescriptionJaxbGB modele_1;
	/**
	 * Ecouteurdrapeau
	 */
	private EcouteurDrapeau ecouteur;
	
	/**
	 * Constructeur Normal de l'�couteur
	 * 
	 * @param createur 
	 * @param vue_wb
	 * @param lethread
	 * @param lafram
	 * @param pays
	 * @param modele_1
	 */
	// --------------------- Constructeur normal (1)
	public EcouteurBoutonPaysJaxB(ControleurDrapeau2 createur, PanelDescriptif vue_wb, Thread lethread, Drapeau lafram, String pays
								,DescriptionJaxbGB modele_1){
		this.createur = createur;
		this.vue_wb = vue_wb;
		this.lafram = lafram;
		this.setPays(pays);
		this.setLethread(lethread);
		this.setModele_1(modele_1);

	}
	
	
	/**
	 * Action sur le bouton, la m�thode r�cup�re le pays s�lectionn� et l'affiche 
	 */
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {

		ecouteur = new EcouteurDrapeau(lafram,createur, pays);
		String pays = (String)createur.getVue_wb().getComboBox_jaxb().getSelectedItem();
		LinkedHashMap<?, ?> donnees = null;
		vue_wb.getTField().setText(pays);
		this.pays = pays;
		createur.getLethread().stop();
		lafram.setPays(pays);
	
		donnees = DescriptionJaxbGB.load(pays+"_jaxb");
	
		lafram.addMouseListener(ecouteur);
		lafram.addMouseMotionListener(ecouteur);
		
		vue_wb.getTField().setText(pays);
		lafram.setPays(pays);
		lethread.stop();
		JPanel pan  = DescriptionJaxbGB.build(pays+"_jaxb");
		donnees = DescriptionJaxbGB.load(pays+"_jaxb");
		lafram.addMouseListener(new EcouteurDrapeau(lafram, null,lafram.getPays()));
		lafram.addMouseMotionListener(new EcouteurDrapeau(lafram,null,lafram.getPays()));
	
		lafram.setContentPane(pan);
		lafram.setVisible(true);
		
		try {createur.setFrame(lafram, donnees);} 
		catch (IOException e1) {e1.printStackTrace();}
		
		lafram.setVisible(true);
		createur.affiche_cache(lafram, vue_wb);
		
	}

	/**
	 * Getter du thread 
	 * @return thread 
	 */
	public Thread getLethread() {return lethread;}
	/**
	 * Setter du thread
	 * @param lethread
	 */
	public void setLethread(Thread lethread) {this.lethread = lethread;}
	/**
	 * Getter du pays
	 * @return lepays
	 */
	public String getPays() {return pays;}
	/**
	 * Setter du pays
	 * @param pays
	 */
	public void setPays(String pays) {this.pays = pays;}
	public DescriptionJaxbGB getModele_1() {return modele_1;}
	public void setModele_1(DescriptionJaxbGB modele_1) {this.modele_1 = modele_1;}


	public EcouteurDrapeau getEcouteur() {
		return ecouteur;
	}


	public void setEcouteur(EcouteurDrapeau ecouteur) {
		this.ecouteur = ecouteur;
	}

}
