

/**
 * Classe VueDrapeau, qui permet de creer un Panel de Drapeau et de le coller sur une JFrame
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
import javax.imageio.ImageIO;
import javax.swing.JPanel;



public class VueDrapeau extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Modele d'un drapeau
	 */
	private ModeleDrapeau modele;
	/**
	 * Getter d'un Modele d'un drapeau
	 * @return
	 */
	public ModeleDrapeau getModele() {
		return modele;
	}
	/**
	 * Setter d'un Modele d'un drapeau
	 * @param modele
	 */
	public void setModele(ModeleDrapeau modele) {
		this.modele = modele;
	}


	/**
	 * Fenetres qui accueil la VueDrapeau
	 */
	private Fenetres createur;
	/**
	 * JPanel de la VueDrapeau
	 */
	private JPanel principal;
	/**
	 * Nombre de clique
	 */
	private int click;
	/**
	 * Coordonnée x du click
	 */
	private int x;
	/**
	 * Coordonnée y du click
	 */
	private int y;
	/**
	 * Coordonnée x1 du click
	 */
	public int x1;
	/**
	 * Coordonnée y1 du click
	 */
	public int y1;
	/**
	 * Musique du drapeau
	 */
	public int musique;
	/**
	 * Carte du drapeau
	 */
	public int carte;
	/**
	 * nombre de paysage du Drapeau
	 */
	public int paysage;
	/**
	 * Image du menu non selectionnée
	 */
	private Image image;
	/**
	 * Image du menu avec l'hymne à ecouter selectionnée
	 */
	private Image image_m;
	/**
	 * Image du menu avec la carte à afficher selectionnée
	 */
	private Image image_c;
	/**
	 * Image du menu avec les paysage à afficher sélectionnée
	 */
	private Image image_p;
	/**
	 * Getter Image du menu avec l'hymne à ecouter selectionnée
	 * @return
	 */
	public Image getImage() {return image;}
	/**
	 * Setter Image du menu avec l'hymne à ecouter selectionnée
	 * @param image
	 */
	public void setImage(Image image) {	this.image = image;}
	/**
	 * Getter Image du menu avec l'hymne à ecouter selectionnée
	 * @return
	 */
	public Image getImage_m() {	return image_m;}
	/**
	 * Setter Image du menu avec l'hymne à ecouter selectionnée
	 * @param image_m
	 */
	public void setImage_m(Image image_m) {	this.image_m = image_m;}
	/**
	 * Getter Image du menu avec la carte à afficher selectionnée
	 * @return
	 */
	public Image getImage_c() {	return image_c;}
	/**
	 * Setter Image du menu avec la carte à afficher selectionnée
	 * @param image_c
	 */
	public void setImage_c(Image image_c) {	this.image_c = image_c;}
	/**
	 * Getter Image du menu avec les paysage à afficher sélectionnée
	 * @return
	 */
	public Image getImage_p() {	return image_p;}
	/**
	 * Setter Image du menu avec les paysage à afficher sélectionnée
	 * @param image_p
	 */
	public void setImage_p(Image image_p) {	this.image_p = image_p;}
	
	/**
	 * Constructeur pour créer la vue d'un Drapeau
	 * @param vue
	 * @param modele_1
	 */
	// --------------------- Constructeur normal (1)
	public VueDrapeau(Fenetres vue,ModeleDrapeau modele_1){
		this.modele = modele_1;
		this.setCreateur(vue);
		setPrincipal(new JPanel());
		setSize(800,600);

		click = 0;musique =0;carte = 0;paysage = 0;
	}
	
	/**
	 * Setter VueDrapeau
	 * @param modele
	 */
	public void setDrapeau(LinkedHashMap<?, ?> modele){
		
		
		  setLayout(new GridLayout((Integer) modele.get("Ligne"),(Integer)modele.get("Colonne")));
		   int k =0;
		   for(int i = 0; i < (Integer)modele.get("Nombre") ; i ++){
			   JPanel pan = new JPanel();
			   Color c1 = Fichier.getCol((String) ((LinkedHashMap<?, ?>)modele.get("Couleur")).get(i));
		
			   if((Integer)modele.get("Panel") != null){
				   if(i == (Integer)modele.get("Panel")){
					   pan.setLayout(new GridLayout());
	
					   PanelImage pan2 = new PanelImage((String)modele.get("Image"));
	
					   pan2.setBackground(c1);
					   pan.add(pan2);
	
				   }else{ pan.setBackground(c1); }
			   }else if (modele.get("Panels") != null){
				   pan.setLayout(new GridLayout());
					
				
				   if((Integer)i == (Integer)((LinkedHashMap<?, ?>)modele.get("Panels")).get(k)){
					 //  System.out.println("Limage :"+(String)((LinkedHashMap)donnees.get("Images")).get(k));
					   PanelImage pan2 = new PanelImage((String)((LinkedHashMap<?, ?>)modele.get("Images")).get(k));
					   pan2.setBackground(c1);
					   pan.add(pan2);
					   k++;
				   }else{
					   pan.setBackground(c1);
				   }
			   }
			   
			   add(pan);
			   
			   
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
			
			setImage(image); 
			setImage_c(image_c); 
			setImage_m(image_m); 
			setImage_p(image_p);
		
	}
	
	
	/**
	 * Méthode redéfini de paint pour afficher le menu sur la vueDrapeau
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
	public Fenetres getCreateur() {
		return createur;
	}
	public void setCreateur(Fenetres createur) {
		this.createur = createur;
	}
	public JPanel getPrincipal() {
		return principal;
	}
	public void setPrincipal(JPanel principal) {
		this.principal = principal;
	}
	
	

}
