
/**
 * Class Fichier, elle aporte plusieurs fonctionnalité concernant les fichiers et le traitement d'int 
 * 
 * @author Loic
 * 
 * @version V1.0.0
 * 		Méthode  -chargementFichierTexte
 * 				 -sauvegardeFichierTexte
 * 
 *@version V2.0.0
 *		Ajout de la méthode - chargementFichierImage
 *
 * 
 */
package ApplicationDrapeau;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;


public class Fichier {
	
	/**
	 * Méthode chargementFichierTexte, elle charge le String contenu du nom du fichier texte passé en paramètre
	 * @param nomFichier nom du fichier à aller chercher 
	 * @return le String du fichier
	 */
	public static String chargementFichierTexte (String nomFichier){
		if(nomFichier == null || nomFichier.length() == 0)
			return null;
		
		File fichier = new File(nomFichier);
		
		if(fichier.isDirectory()) return null;
		
		String contenuFichier = new String();
		BufferedReader lecture = null;
		
		try {
			//on ouvre un flux de lecture
			lecture = new BufferedReader(new FileReader(nomFichier));
			String s = new String();
		
			while((s=lecture.readLine())!=null)
			{
				if(s!=null);
					contenuFichier += s+"\r\n";//on recupére le contenu
			}
		} catch (IOException e) {
			System.out.println("Erreur sur la lecture du fichier: "+ nomFichier);
			System.out.println(e);
			return null;
		}
		finally{
			try {
				lecture.close();
			} catch (IOException e) {
				e.printStackTrace();
			}// on ferme le flux
		}
		
		return contenuFichier;
	}
	
	
	/**
	 * Méthode sauvegardeFichierTexte, il sauvegarde un contenu au chemin nomFichier préciser 
	 * @param nomFichier chemin du fichier à enregistrer 
	 * @param contenu contenu à enregistre
	 * @return (true) si tout c'est bien passé, (false) si il y a eu un problème
	 */
	public static boolean sauvegardeFichierTexte (String nomFichier, String contenu ){
		
		if(nomFichier == null || nomFichier.length() == 0)
			return false;
		
		File fichier = new File(nomFichier);
		
		if(fichier.isDirectory()) return false;
		
		String cont = contenu.replace("\n", "\r\n");
		
		if(!fichier.exists()){
			try {
				fichier.createNewFile();
			} catch (IOException e) {
				System.out.printf("Impossible de creer le fichier: " + fichier.getName());
				return false;
			}
		}
		
		PrintWriter ecriture = null;
		
		try {
			//on ouvre un flux d'écriture
			ecriture = new PrintWriter(new BufferedWriter(new FileWriter(fichier)));
			ecriture.println(cont);
			
		} catch (IOException e) {
			System.out.println("Erreur lors de l'ecriture dans le fichier: "+ nomFichier);
			return false;
		}
		finally{
			ecriture.close();// on ferme le flux
		}
		
		return true;
	}
	
	
	/**
	 * Méthode chargementFichierImage, elle charge une BufferedImage, sauvegarde au chemin préciser en paramètre
	 * @param chemin de l'imahge
	 * @return l'image en BufferedImage 
	 */
	public static BufferedImage chargementFichierImage(String chemin){
		
		File fichier = new File(chemin);
		
		if(!fichier.isFile()) return null;
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(fichier);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return image;
	}
	

	/**
	 * Méthode getCol, elle renvois une Class Color, d'une config
	 * 
	 * @param config le string de la Color
	 * @return la class Color
	 */
	// --------------------- Méthode getCol
	public static Color getCol(String config){
		int bloc = config.indexOf("r=");
		int bloc2 = bloc+2;
		String lacouleur= "";
		int rouge;int vert;int bleu;
		
		//récupération du rouge 
		for(int i = bloc2; config.charAt(i) != ','; i++){
			int r = config.charAt(i) - 48;
			if(r <= 9 && r >=0)
				lacouleur+=config.charAt(i);
		}
		rouge = Integer.valueOf(lacouleur);
		lacouleur ="";
		
		//récupération du vert
		bloc = config.indexOf("g=");
		bloc2 = bloc+2;
		for(int i = bloc2; config.charAt(i) != ','; i++){
			int r = config.charAt(i) - 48;
			if(r <= 9 && r >=0)
				lacouleur+=config.charAt(i);
		}
		vert = Integer.valueOf(lacouleur);
		lacouleur ="";
		
		//récupération du bleu
		bloc = config.indexOf("b=");
		bloc2 = bloc+2;
		for(int i = bloc2; config.charAt(i) != ']'; i++){
			int r = config.charAt(i) - 48;
			if(r <= 9 && r >=0)
				lacouleur+=config.charAt(i);
		}
		bleu = Integer.valueOf(lacouleur);
		
		return new Color(rouge,vert,bleu);
	}
	
	
	
	/**
	 * Méthode static getCouleur, reprends recup_config, mais récup une config Couleur(int) 
	 * dans une boucle afin d'en récupérer plusieurs 
	 * @param total String contenant le contenu dans lequel on récupèrera la config
	 * @param i Couleur(i)
	 * @return le String de la couleur au 
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
	 * Méthode sub_String, elle rend un String, du String total arrèté à config
	 * @param total String total dans lequel on veux s'arrèter à un char
	 * @param config config 
	 * @return le new String
	 */
	// --------------------- Méthode enleverStrin
	public static String sub_String(String total,char config){
		String ret ="";
		for(int i = 0 ; i < total.length();i++){
			if(total.charAt(i) != config){
				ret+=total.charAt(i);
			}else{
				return ret;
			}
		}
		return ret;
		
	}
}
