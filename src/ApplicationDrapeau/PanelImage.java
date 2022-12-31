
/**
 * Classe PanelImage
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
import javax.swing.JPanel;

public class PanelImage extends JPanel {
	private static final long serialVersionUID = 1L;
	/**
	 * Image
	 */
	private Image image;
	
	/**
	 * Charger un image avec un chemin
	 * @param chemin
	 */
	// --------------------- Constructeur normal (1)
	public PanelImage(String chemin){
		try {image = ImageIO.read(new File("_Images/"+chemin));} 
		catch (IOException e) {e.printStackTrace();}
	}
   
	/**
	 * Méthode redéfinie de Graphics pour afficher l'image 
	 */
    // --------------------- Méthode paint
    public void paint(Graphics g) {
    	super.paint(g);
        g.drawImage(image,this.getWidth()/100,this.getHeight()/100,this.getWidth(),this.getHeight(),null);
    }
}
