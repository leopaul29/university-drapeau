/**
 * Classe de Boutton, elle redéfinie JButton et y ajoute une image
 * 
 * 
 * @author Loic, Léo-Paul
 * 
 * @version 0_1_0 
 * 				Fonctionnalité : Le bouton dispose d'une image de fond
 * 
 * @version 0_1_1 
 * 				Fonctionnalité : Le bouton dispose d'une image de fond
 * 								 Le bouton permet d'ajouter un tooltip lors du passage de la souris sur lui
 * 
 * 
 * 
 * 
 */

package ApplicationDrapeau;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;



public class Button extends JButton{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Image qui sera placé sur le bouton cachant de ce faite le titre de celui-ci
	 */
	private Image imagefond;
	/**
	 * Titre du bouton au sens JButton(title)
	 */
	private String chemin;
	
	/**
	 * Getter de l'image du bouton
	 * @return 	l'image(Image) du bouton 
	 */
	public Image getImagefond() {return imagefond;}
	/**
	 * Setter de l'image du bouton
	 * @param imagefond image(Image) à placer sur le bouton
	 */
	public void setImagefond(Image imagefond) {this.imagefond = imagefond;}
	
	
	/**
	 * Constructeur normal du bouton
	 * 
	 * @param title Titre du bouton, si il y a une image, le titre ne s'affichera pas
	 * @param image Lien vers l'image placé après _Images/_Bouton/ 
	 */
	// --------------------- Constructeur normal (1)
	public Button(String title, String image){
		super();
		this.setText(title);
		chemin = "_Images/_Bouton/"+image;
	}
	
	
	/**
	 * Méthode redéfinie de Graphics, qui affiche l'image du bouton sur le bouton
	 */
	// --------------------- Méthode paint
	public void paint (Graphics g) {
		super.paint(g);
		Image imagefond = null;
		try {imagefond = ImageIO.read(new File(chemin));}
		catch (IOException e) {e.printStackTrace();}
		if (imagefond != null) 
			g.drawImage(imagefond, 0, 0, getWidth(), getHeight(), null);
	  
	}  

	/**
	 * Méthode qui ajoute un message quand on passe la souris dessus
	 * 
	 * @param message
	 */
	public void addTooltip(final String message){
		addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {
			    javax.swing.ToolTipManager.sharedInstance().setInitialDelay(0);
			    setToolTipText(message);
			}
			public void mouseClicked(MouseEvent arg0) {}
		});
	}

}
