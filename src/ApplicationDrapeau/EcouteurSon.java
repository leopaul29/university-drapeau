/**
 * Class EcouteurSon, qui s'occupe de couper le son de l'hymne 
 * 
 * @author Loic,Léo-Paul
 * @version V0_1_0;
 * 
 */
package ApplicationDrapeau;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

	
	
	public class EcouteurSon implements ActionListener{
	private FenetreSon createur;
	
	
	/**
	 * Constructeur normal de l'écouteur
	 *
	 */
	// --------------------- Constructeur normal (1)
	public EcouteurSon(FenetreSon fs){
		this.createur = fs;
	}
	
	/**
	 * Arrète le son quand on clic 
	 */
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		createur.getThread().stop();
		createur.dispose();
	}

	
}
