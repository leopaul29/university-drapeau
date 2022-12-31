

/**
 * Classe FenetreCarte, class qui affiche le paysage d'un pays
 * 
 * 
 * @author Loic, Léo-Paul
 * 
 * @version V0_1_0 
 * 
 */


package ApplicationDrapeau;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

public class FenetrePaysage extends JFrame {
	private static final long serialVersionUID = 1L;
	/**
	 * Liste des images de paysage
	 */
	private LinkedHashMap<Integer, Image> liste_paysage;
	/**
	 * 
	 */
	private int check;
	/**
	 * JButton de gauche
	 */
	private JButton left;
	/**
	 * Getter du JButton de gauche
	 * @return
	 */
	public JButton getLeft() {
		return left;
	}
	/**
	 * Setter du JButton de gauche
	 * @param left
	 */
	public void setLeft(JButton left) {
		this.left = left;
	}
	/**
	 * JButton de droite
	 */
	private JButton right;
	/**
	 * Getter du JButton de droite
	 * @return
	 */
	public JButton getRight() {
		return right;
	}
	/**
	 * Setter du JButton de droite
	 * @param left
	 */
	public void setRight(JButton right) {
		this.right = right;
	}

	
	/**
	 * Getter du numero de l'image
	 * @return
	 */
	public int getCheck(){return check;}
	/**
	 * Setter du numero de l'image
	 * @param x
	 */
	public void setCheck(int x){check=x;}
	
	/**
	 * Méthode pour créer une fenetre contenant les paysage du pays passé en param
	 * @param pays
	 */
	// --------------------- Constructeur normal (1)
	public FenetrePaysage(String pays){
		super();
		this.setSize(700,500);
		 left = new JButton("<");
		 right = new JButton(">");
		check = 0 ; 
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		try { 
			liste_paysage = new LinkedHashMap<Integer, Image>();
			for(int i = 0;i<12;i++){
				File a = new File("_Images/_Paysages/"+pays+"-"+i+".jpg");
				liste_paysage.put(i,ImageIO.read(a));
			}
			this.setLayout(new BorderLayout());
			
			this.add(left,BorderLayout.WEST);
			this.add(right,BorderLayout.EAST);
		}
		catch (IOException e) {e.printStackTrace();}
		
	}
	
	/**
	 * Méthode redéfinie de Graphics qui affiche les images de paysage de la liste en fonction du check
	 */
	// --------------------- Méthode paint
	public void paint(Graphics g){
		g.drawImage(liste_paysage.get(check),0,0,this.getWidth(),this.getHeight(),null);
	}
}
