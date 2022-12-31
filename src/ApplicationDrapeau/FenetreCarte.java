
/**
 * Classe FenetreCarte, class qui affiche la carte d'un pays
 * 
 * 
 * @author Loic, Léo-Paul
 * 
 * @version V0_1_0 
 * 
 */

package ApplicationDrapeau;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class FenetreCarte extends JFrame {
	private static final long serialVersionUID = 1L;
	/**
	 * Image de la carte
	 */
	private Image image;
	/**
	 * Nom du pays
	 */
	private String pays;
	
	/**
	 * Getter de l'image de la carte
	 * @return
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Setter de l'image de la carte
	 * @param image
	 */
	public void setImage(Image image) {
		this.image = image;
	}


	/**
	 * Méthode pour créer une fenetre contenant la carte du pays passé en param
	 * @param pays
	 */
	// --------------------- Constructeur normal (1)
	public FenetreCarte(String pays){
		super();
		this.setPays(pays);
		this.setSize(700,500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		try { image = ImageIO.read(new File("_Images/_Cartes/carte-"+pays+".jpg"));} 
		catch (IOException e) {e.printStackTrace();}
		
	}
	
	/**
	 * Méthode redéfinie de Graphics qui affiche l'image de la carte
	 */
	// --------------------- Méthode paint
	public void paint(Graphics g){
		g.drawImage(image,0,0,this.getWidth(),this.getHeight(),null);
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}
}
