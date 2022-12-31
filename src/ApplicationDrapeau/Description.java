/**
 * Class Description, elle gère tout le modele des drapeaux enregistrer
 * en .txt 
 * 
 * @author Loic,Léo-Paul
 * @version V0_6_0;
 * 
 */
package ApplicationDrapeau;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;


public class Description   {
	/**
	 * Colonne pour le GridLayout
	 */
	private int colonne;
	/**
	 * Ligne pour le GridLayout
	 */
	private int ligne;
	/**
	 * Nom du drapeau
	 */
	private String pays;
	/**
	 * Linked de tout les couleurs 
	 */
	private LinkedHashMap<?, ?> liste_couleur;
	
	/**
	 * Constructeur par défaut
	 * @deprecated
	 */
	// --------------------- Constructeur par défaut 
	public Description(){}
	
	/**
	 * Constructeur normal
	 * @param col Colonne pour le GridLayout
	 * @param ligne Ligne pour le GridLayout
	 * @param liste_couleur toutes les couleurs des panels
	 * @param pays nom du pays du drapeau
	 */
	// --------------------- Constructeur normal (1)
	public Description(int col, int ligne, LinkedHashMap<?, ?> liste_couleur,String pays){
		this.colonne = col;
		this.ligne = ligne;
		this.liste_couleur = liste_couleur ;
		this.pays = pays;
	}
	
	
	/**
	 * Méthode Store, qui permet d'enregistrer un Drapeau en .txt 
	 * @param chemin Ou l'enregistrer 
	 * @param lui le Description descriptif du drapeau
	 * @throws IOException
	 */
	// --------------------- Méthode Store
	public static void store(String chemin, Description lui) throws IOException{
		String enregistrement = "";
		enregistrement += "Ligne : " + lui.getLigne() +"\n";
		enregistrement += "Colonne : " + lui.getColonne() +"\n";
		enregistrement += "Nombre : "+ lui.getListe_couleur().size() +"\n";
		enregistrement += "Pays : "+lui.getPays() +"\n";
		for(int i = 0 ; i < lui.getListe_couleur().size(); i ++)
			enregistrement += "Couleur("+i+") : "+lui.getListe_couleur().get(String.valueOf(i)) +"\n";
		
		Fichier.sauvegardeFichierTexte(chemin, enregistrement);
	}
	
	
	/**
	 * Méthode load 
	 * @deprecated
	 * @param chemin ou allez chercher le drapeau
	 * @return LinkedHashMap modele
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	// --------------------- Méthode load
	public static LinkedHashMap<?, ?> load_simple(String chemin) throws FileNotFoundException, IOException, ClassNotFoundException{
		LinkedHashMap<String,Object> donnees = new LinkedHashMap<String,Object>();
		String enregistrement = Fichier.chargementFichierTexte("_Drapeau/"+chemin);
		int ligne = Integer.valueOf(Description.recup_config("Ligne : ", enregistrement));
		donnees.put("Ligne", ligne);
		int colonne = Integer.valueOf(Description.recup_config("Colonne : ", enregistrement));
		donnees.put("Colonne",colonne);
		int nombre = Integer.valueOf(Description.recup_config("Nombre : ", enregistrement));
		donnees.put("Nombre",nombre);
		

		LinkedHashMap<Integer, String> l1 = new LinkedHashMap<Integer, String>();
		for(int i  = 0 ; i < nombre;i++)
			l1.put(i,Description.getCouleur(enregistrement, i));
		donnees.put("Couleur",l1);	
		int largeur = Integer.valueOf(Description.recup_config("Largeur : ", enregistrement));
		donnees.put("Largeur",largeur);
		int hauteur = Integer.valueOf(Description.recup_config("Hauteur : ", enregistrement));
		donnees.put("Hauteur",hauteur);
		String pays = Description.recup_config("Pays : ", enregistrement);
		donnees.put("Pays",pays);
		int GMT = Integer.valueOf(Description.recup_config("GMT : ", enregistrement));
		donnees.put("GMT", GMT);
		
		return donnees;
	}
	
	/**
	 * Méthode load, elle charge un drapeau avec un chemin en paramètre, et analyse tout 
	 * le document texte pour y extraire toute les données dont on aura besoin 
	 * pour charger un drapeau et retourne la LinkedHashMap remplis
	 * 
	 * @param chemin Ou allez chercher le fichier texte après /_Drapeau 
	 * @return LinkedHashMap descriptive du drapeau
	 */
	// --------------------- Méthode load (2)
	public static LinkedHashMap<?, ?> load(String chemin){
		LinkedHashMap<String, Object> donnees = new LinkedHashMap<String, Object>();
		Integer panel = null;
		String enregistrement = Fichier.chargementFichierTexte("_Drapeau/"+chemin);
		int ligne = Integer.valueOf(Description.recup_config("Ligne : ", enregistrement));
		donnees.put("Ligne",ligne);
		int colonne = Integer.valueOf(Description.recup_config("Colonne : ", enregistrement));
		donnees.put("Colonne",colonne);
		int nombre = Integer.valueOf(Description.recup_config("Nombre : ", enregistrement));
		donnees.put("Nombre",nombre);
		donnees.put("GMT", Integer.valueOf(Description.recup_config("GMT : ", enregistrement)));
		LinkedHashMap<Integer, String> l1 = new LinkedHashMap<Integer, String>();
		for(int i  = 0 ; i < nombre;i++)
			l1.put(i,Description.getCouleur(enregistrement, i));	
		donnees.put("Couleur",l1);
		int largeur = Integer.valueOf(Description.recup_config("Largeur : ", enregistrement));
		donnees.put("Largeur",largeur);
		int hauteur = Integer.valueOf(Description.recup_config("Hauteur : ", enregistrement));
		donnees.put("Hauteur",hauteur);
		String image = Description.recup_config("Image : ", enregistrement);
		donnees.put("Image",image);
		if(enregistrement.contains("Panel : ")){
			 panel = Integer.valueOf(Description.recup_config("Panel : ", enregistrement));
		}
		if(panel != null)
			donnees.put("Panel",panel);
		else {
			int nb = Integer.valueOf(Description.recup_config("Nombre_Image : ", enregistrement));
			donnees.put("Panels", (LinkedHashMap<?, ?>) Description.getPanels(enregistrement,nb));
			donnees.put("Images", (LinkedHashMap<?,?>) Description.getImages(enregistrement,nb));
		}
		String pays = Description.recup_config("Pays : ", enregistrement);
		donnees.put("Pays",pays);
		
		return donnees;
	}
	
	/**
	 * Méthode static getCouleur, reprends recup_config, mais récup une config Couleur(int) 
	 * dans une boucle afin d'en récupérer plusieurs 
	 * @param total String contenant le contenu dans lequel on récupèrera la config
	 * @param i Couleur(i)
	 * @return le String de la couleur au 
	 */
	// --------------------- Méthode getCouleur
	public static String getCouleur(String total, int i){
		int bloc = total.indexOf("Couleur("+i+") : ");
		int bloc2 = bloc+12;
		String rep= "";
		for(int j = bloc2; total.charAt(j) != '\n'; j++){
			rep+=total.charAt(j);
		}
		return rep;
	}
	
	/**
	 * Méthode static recup_config, elle récupère le string après une config dans un String total
	 * jusqu'à un \n
	 * @param config contient le String config (ex : "Largeur : ")
	 * @param total contient tout le String à analyser 
	 * @return le String après la config
	 */
	// --------------------- Méthode recup_config
	public static String recup_config(String config,String total){
		int bloc = total.indexOf(config);
		int bloc2 = bloc + config.length();
		String loption= "";
		
		for(int i = bloc2; total.charAt(i+1) != '\n'; i++){
			loption+=total.charAt(i);
			if(total.charAt(i) == ','){
				
			}
		}
		return loption;
	}
	
	/**
	 * Méthode getPanels, reprends recup_config mais récupère obligatoirement un config : "Panel(i) : "
	 * et retourn un LinkedHashMap de tout les "Panel(i) : " trouvé
	 * @param total String contenant tout le String à afficher
	 * @param nombre_panel combien il y a  de Panel en tout 
	 * @return la LinkedHashMap de tout les Panels
	 */
	// --------------------- Méthdoe getCouleurs
	public static  LinkedHashMap<?, ?> getPanels(String total, int nombre_panel){
		
		int bloc = total.indexOf("Panel(0) : ");
		int bloc2 = bloc + total.length();	  
		LinkedHashMap<Integer,Integer> rep = new LinkedHashMap<Integer,Integer>();
		 
		for(int k = 0 ; k < nombre_panel;k++){
			String panel =null;
			panel="";
			bloc = total.indexOf("Panel("+k+") : ");
			String taille = "Panel("+k+") : ";
			bloc2 = bloc + taille.length();	  
			for(int j = bloc2; total.charAt(j+1) != '\n'; j++){
				panel+=total.charAt(j);
			}
			rep.put(k, Integer.valueOf(panel));
		}
		
		return rep;
	}
	
	/**
	 * Méthode getImages, identique avec getPanels pour pour une config obligatoire de "Image(i) : "
	 * @param total tout le contenu String à analyser
	 * @param nombre_image le nombre de "Image(i)" qu'il y a 
	 * @return La LinkedHashMap de toute les images 
	 */
	public static LinkedHashMap<?, ?> getImages(String total, int nombre_image){
		LinkedHashMap<Integer, String> liste_images = new LinkedHashMap<Integer, String>();	
		int bloc = total.indexOf("Image(0) : ");
		int bloc2 = bloc + total.length();
		String images ="";
	 
		for(int k = 0 ; k < nombre_image;k++){
			images="";
			bloc = total.indexOf("Image("+k+") : ");
			String taille = "Image("+k+") : ";
			bloc2 = bloc + taille.length();	  
			for(int j = bloc2; total.charAt(j+1) != '\n'; j++){
				images+=total.charAt(j);
			}
			liste_images.put(k, images);
		}
		
		return liste_images;
	}
	
	

	// --------------------- Getter & Setter
	/**
	 * Accesseur du nombre de Colonne
	 * @return le nombre de colonne
	 */
	public int getColonne() {return colonne;}
	/**
	 * Setter de colonne
	 * @param colonne
	 */
	public void setColonne(int colonne) {this.colonne = colonne;}
	/**
	 * Accesseur du nombre de Ligne
	 * @return le nombre de ligne
	 */
	public int getLigne() {return ligne;}
	/**
	 * Setter du nombre de ligne
	 * @param ligne
	 */
	public void setLigne(int ligne) {this.ligne = ligne;}
	/**
	 * Getter de Pays
	 * @return le Pays
	 */
	public String getPays(){return pays;}
	/**
	 * Setter de Pays 
	 * @param pays
	 */
	public void setPays(String pays){this.pays = pays;}
	/**
	 * Getter de la liste des couleurs 
	 * @return la liste des couleurs
	 */
	public LinkedHashMap<?, ?> getListe_couleur() {return liste_couleur;}
	/**
	 * Setter de la liste des couleurs 
	 * @param liste_couleur
	 */
	public void setListe_couleur(LinkedHashMap<?, ?> liste_couleur) {this.liste_couleur = liste_couleur;}

	
}
