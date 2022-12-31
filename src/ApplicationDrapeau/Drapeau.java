
/**
 * Classe Drapeau
 * 
 * 
 * @author Loic, Léo-Paul
 * 
 * @version V0_1_0 
 * 
 */

package ApplicationDrapeau;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Drapeau extends JFrame implements Observer {
	/**
	 * Nom du drapeau
	 */
	private String pays;
	/**
	 * 
	 */
	private int click;
	private int x;
	private int y;
	private Image image;
	private Image image_m;
	private Image image_c;
	private Image image_p;
	public int x1;
	public int y1;
	public int musique;
	public int carte;
	public int paysage;
	private LinkedHashMap<?, ?> donnees;
	private ModeleDrapeau modele;
	private ControleurDrapeau2 createur;
	
	public ControleurDrapeau2 getCreateur() {return createur;}
	public void setCreateur(ControleurDrapeau2 createur) {this.createur = createur;}
	public void setDonnes(LinkedHashMap<?, ?> ça){donnees = ça;}
	public LinkedHashMap<?, ?> getDonnees(){return donnees;}
	
	
	// --------------------- Getter & Setter
	//public void setDrap(JFrame laframe){this.getContentPane() = (Drapeau)laframe;}
	/**
	 * Getter du click
	 * 
	 * @return (int) nombre de click, coordonnée d'abscisse et ordonnée
	 */
	public int getClick(){return click;}
	/**
	 * Setter du click avec les param
	 * 
	 * @param i
	 * @param x
	 * @param y
	 */
	public void setClick1(int i, int x,int y){click=i; this.x = x; this.y = y;}
	
	/**
	 * Getter du nom du pays
	 * 
	 * @return (String) nom du pays
	 */
	public String getPays() {return pays;}
	/**
	 * Setter du nom du pays avec le param
	 * 
	 * @param pays
	 */
	public void setPays(String pays) {this.pays = pays;}
	
	/**
	 * Getter de l'image du menu
	 * @return 	l'image(Image) du menu
	 */
	public Image getImage() {return image;}
	/**
	 * Setter de l'image du menu
	 * @param image
	 */
	public void setImage(Image image) {	this.image = image;}
	
	/**
	 * Getter de l'image du menu avec l'hymne sélectionnée
	 * @return 	l'image(Image) du menu
	 */
	public Image getImage_m() {	return image_m;}
	/**
	 * Setter de l'image du menu avec l'hymne sélectionnée
	 * @param image_m
	 */
	public void setImage_m(Image image_m) {	this.image_m = image_m;}
	
	/**
	 * Getter de l'image du menu avec la carte sélectionnée
	 * @return 	l'image(Image) du menu
	 */
	public Image getImage_c() {	return image_c;}
	/**
	 * Setter de l'image du menu avec la carte sélectionnée
	 * @param image_c
	 */
	public void setImage_c(Image image_c) {	this.image_c = image_c;}
	
	/**
	 * Getter de l'image du menu avec les paysage selectionnée
	 * @return 	l'image(Image) du menu
	 */
	public Image getImage_p() {	return image_p;}
	/**
	 * etter de l'image du menu avec les paysage selectionnée
	 * @param image_p
	 */
	public void setImage_p(Image image_p) {	this.image_p = image_p;}
	
	/**
	 * Méthode Drapeau , elle crée une fenetre avec un drapeau avec les param
	 * 
	 * @param pays
	 * @param createur
	 */
	// --------------------- Constructeur normal (1)
	public Drapeau(String pays, ControleurDrapeau2 createur){
		super();
		this.createur = createur;
		click = 0;musique =0;carte = 0;paysage = 0;
		this.setPays(pays);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		
	}
	
	/**
	 * Méthode redéfinie de Graphic
	 */
	// --------------------- Méthode paint
	public void paint(Graphics g) {
		super.paint(g);
		if(click == 1){
			
			g.drawImage(image,x-this.getWidth()/5 ,y-this.getWidth()/5/2,this.getWidth()/2,this.getHeight()/2,null);
			if(musique == 1)
				g.drawImage(image_m,x-this.getWidth()/5,y-this.getWidth()/5/2,this.getWidth()/2,this.getHeight()/2,null);
				
			else if(carte == 1)
				g.drawImage(image_c,x-this.getWidth()/5,y-this.getWidth()/5/2,this.getWidth()/2,this.getHeight()/2,null);
				
			else if(paysage == 1)
				g.drawImage(image_p,x-this.getWidth()/5,y-this.getWidth()/5/2,this.getWidth()/2,this.getHeight()/2,null);
		}
			
	}
	
	/**
	 * Méthode pour accrocher à chaque fenetre de drapeau un menu avec les param
	 * 
	 * @param donnees
	 * @param thise
	 * @return
	 */
	public JPanel set_fen(final LinkedHashMap<?, ?> donnees, final Drapeau thise){
		  JPanel pan = new JPanel();
		
		pan.setLayout(new GridLayout( (Integer)donnees.get("Ligne"),(Integer)donnees.get("Colonne")  )   );
		 
		   
		for(int i = 0; i < (Integer)donnees.get("Nombre") ; i ++){
		
		   Color c1 = Fichier.getCol((String)((LinkedHashMap<?, ?>)donnees.get("Couleur")).get(i));
		   pan.setBackground(c1);
		   this.add(pan);
		}
		
		Image image = null, image_m = null, image_c = null, image_p = null;
		try { image = ImageIO.read(new File("_Images/menu2.png"));}
		catch (IOException e) {e.printStackTrace();}
		try { image_m = ImageIO.read(new File("_Images/menu2_m.png"));}
		catch (IOException e) {e.printStackTrace();}
		try { image_c = ImageIO.read(new File("_Images/menu2_c.png"));}
		catch (IOException e) {e.printStackTrace();}
		try { image_p = ImageIO.read(new File("_Images/menu2_p.png"));}
		catch (IOException e) {e.printStackTrace();}
		
		thise.setImage(image); 
		thise.setImage_c(image_c); 
		thise.setImage_m(image_m); 
		thise.setImage_p(image_p);
		
		return pan;
		
	}
	
	/**
	 * Observ
	 */
	public void update(Observable arg0, Object arg1) {
		
		
	}
	public ModeleDrapeau getModele() {
		return modele;
	}
	public void setModele(ModeleDrapeau modele) {
		this.modele = modele;
	}
}
	
	

