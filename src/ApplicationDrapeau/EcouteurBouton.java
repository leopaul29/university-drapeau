/**
 * Class EcouteurBouton, elle gère les clics sur les flèches de FenetrePaysage
 * 
 * @version V0_1_0
 * 
 * @author Loic, Léo-Paul
 * 
 * 
 */


package ApplicationDrapeau;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EcouteurBouton implements ActionListener{
	private FenetrePaysage fp;
	private int plus;
	
	/**
	 * Constructeur normal
	 * 
	 * @param fp une fenetre cible
	 * @param plus int indiquant le clic de l'utilisateur
	 */
	// --------------------- Constructeur normal (1)
	public EcouteurBouton(FenetrePaysage fp, int plus){
		this.fp = fp;
		this.plus = plus;
		
	}
	
	
	/**
	 * Action sur le bouton, si on clic, on change de photo
	 */
	public void actionPerformed(ActionEvent e) {

		if(plus == 1 && fp.getCheck() < 12 ) {
			fp.setCheck(fp.getCheck()+1);
			fp.repaint();
		}
		
		else if(plus == -1 && fp.getCheck() >= 0 ){
			fp.setCheck(fp.getCheck()-1);
			fp.repaint();
		}
		
	}

}
