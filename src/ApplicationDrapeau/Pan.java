
/**
 * Classe Pan, Construit un JPanel en fonction d'un pays
 * 
 * 
 * @author Loic, Léo-Paul
 * 
 * @version V0_1_0 
 * 
 */


package ApplicationDrapeau;

import javax.swing.JPanel;

public class Pan extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Nom du pays
	 */
	private String pays;
	
	/**
	 * Getter nom du pays
	 * @return
	 */
	public String getPays(){return pays;}
	
	/**
	 * Construit un JPanel en fonction d'un pays
	 * @param pays
	 */
	public Pan(String pays){
		super();
		this.pays = pays;
		
	}
}
