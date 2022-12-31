

/**
 * Classe Fenetres, class qui affiche la liste des drapeau affichable
 * 
 * 
 * @author Loic, Léo-Paul
 * 
 * @version V0_1_0 
 * 
 */
package ApplicationDrapeau;

import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.JFrame;

public class Fenetres extends JFrame{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Méthode pour créer la fenetre avec les JButton drapeau
	 * @throws IOException
	 */
	// --------------------- Constructeur par défaut 
	public Fenetres() throws IOException{
		setSize(800,800);
		setTitle("Les drapeaux");
		setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    GridLayout principal = new GridLayout(5,5);
	    getContentPane().setLayout(principal);
	     
		
	}
	
	
	

}
