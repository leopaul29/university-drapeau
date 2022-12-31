/**
 * Class EcouteurSliderDiaporama, il pi�ge le changement du slider et modifie 
 * l'attribut vitesse du diaporama
 * 
 * @author Loic, L�o-Paul
 * @version V0_1_0;
 * 
 */

package ApplicationDrapeau;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EcouteurSliderDiaporama implements ChangeListener {
	/**
	 * Hame�on vers le cr�ateur
	 */
	EcouteurDrapeau createur2;
	/**
	 * Maniere de set la vitesse 1 = Menu 2 = Image
	 */
	private int facon;
	/**
	 * Hame�on vers le controleur
	 *
	 */
	ControleurDrapeau2 createur;
	/**
	 * Cr�ateur normal de l'�couteur
	 * @param createur hame�on
	 */
	public EcouteurSliderDiaporama(EcouteurDrapeau createur2, ControleurDrapeau2 createur, int facon){
		this.createur2 = createur2;
		this.facon = facon;
		this.createur = createur;
	}
	
	/**
	 * M�thode modifiant l'attribut vitesse du diaporama
	 * 
	 */
	public void stateChanged(ChangeEvent arg0) {
		int valeur = createur.getVue_wb().getSlider().getValue();
		if(valeur != 0 ){
			if(facon == 1)
				createur.getEcouteurjouerAction().setVitesse(valeur*50);
			else if(facon == 2)
				createur2.setVitesse(valeur*50);
		}else if(valeur == 100){
			if(facon == 1)
				createur.getEcouteurjouerAction().setVitesse(valeur*1000);
			else if(facon == 2)
				createur2.setVitesse(valeur*1000);
		}else{
			if(facon == 1 )
				createur.getEcouteurjouerAction().setVitesse(10);
			else if(facon == 2){
				createur2.setVitesse(10);
			}
		}
	}

}
