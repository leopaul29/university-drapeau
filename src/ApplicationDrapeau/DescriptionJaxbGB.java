/**
 * Class DescriptionJaxB, une spécialisation de Description qui gère les enregistrement en XML grace à JaxB
 * 
 * @author Loic,Léo-Paul
 * @version V0_6_0;
 */
package ApplicationDrapeau;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.Serializable;
import java.util.LinkedHashMap;

import javax.swing.JPanel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class DescriptionJaxbGB implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * Liste des GridBagConstraints
	 */
	private LinkedHashMap<Integer,LinkedJaxB> liste_contrainte;
	/**
	 * Nom du pays
	 */
	private String pays;
	/**
	 * Largeur 
	 */
	private int largeur;
	/**
	 * Hauteur
	 */
	private int hauteur;
	/**
	 * Fuseau Horaire du pays en décalage de int 
	 */
	private int gmt;
	
	public DescriptionJaxbGB(){liste_contrainte = new LinkedHashMap<Integer, LinkedJaxB>();}

	@XmlAttribute (name="GMT")
	/**
	 * Getter du fuseau horaire
	 * @return le fuseau horaire
	 */
	public int getGmt(){return gmt;}
	/**
	 * Setter du fuseau horaire
	 * @param gmt
	 */
	public void setGmt(int gmt){this.gmt = gmt;}
	@XmlAttribute (name="pays")
	/**
	 * Getter du nom du pays
	 * @return le nom du pays
	 */
	public String getPays() {return pays;	}
	/**
	 * Setter du nom du pays
	 * @param pays
	 */
	public void setPays(String pays) {this.pays = pays;}
	@XmlAttribute(name="largeur")
	/**
	 * Getteur de largeur 
	 * @return la largeur
	 */
	public int getLargeur(){return largeur;}
	/**
	 * Setter de Largeur
	 * @param larg
	 */
	public void setLargeur(int larg){largeur = larg;}
	@XmlAttribute(name="hauteur")
	/**
	 * Getter de Hauteur
	 * @return
	 */
	public int getHauteur(){return hauteur;}
	/**
	 * Setter de Hauteur
	 * @param haut
	 */
	public void setHauteur(int haut){hauteur = haut;}
	@XmlElement
	/**
	 * Getter de la liste des contraintes <un indice, une LinkedHashMap (LinkedJaxB)>
	 * @return la liste des contraintes
	 */
	public LinkedHashMap<Integer,LinkedJaxB> getListeContrainte(){return liste_contrainte;	}
	/**
	 * Setter de la liste des contraintes 
	 * @param liste_contrainte
	 */
	public void setListe_contrainte(LinkedHashMap<Integer, LinkedJaxB> liste_contrainte) {this.liste_contrainte = liste_contrainte;}
	
	
	/**
	 * Méthode Store, elle sauvegarde un Drapeau avec des panels en GridBagLayout 
	 * @param chemin ou l'enregistrer 
	 * @param op l'opérande descriptive du drapeau au préalable remplis
	 */
	// --------------------- Méthode Store
	public static void store(String chemin, DescriptionJaxbGB op) {
		File file = new File(chemin);
		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(DescriptionJaxbGB.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(op, file);
			jaxbMarshaller.marshal(op, System.out);
		}
		catch (JAXBException e) {e.printStackTrace();}
	}
	
	/**
	 * Méthode Build, elle renvois tout le panel du drapeau GridBagLayout, dont on spécifira le chemin
	 * sur le disque après _Drapeau/ 
	 * Amélioration à venir : _ Prendre en paramètre un Linked, elle pourra au moins prendre le résultat de la 
	 * 							méthode load, et donc alléger son code 
	 * @param chemin le chemin ou allez chercher le drapeau
	 * @return tout le JPanel du drapeau
	 */
	// --------------------- Méthode build
		public static JPanel build(String chemin){
			DescriptionJaxbGB ret=null;
			//on load
			try {
				File file = new File("_Drapeau/"+chemin);
				System.out.println(chemin);
				JAXBContext jaxbContext = JAXBContext.newInstance(DescriptionJaxbGB.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				ret = (DescriptionJaxbGB) jaxbUnmarshaller.unmarshal(file);
			}
			catch (JAXBException e) {e.printStackTrace();}
			
			//Panel principal qui sera retourné
			JPanel laframe = new JPanel();
			laframe.setSize(800,600);
			GridBagLayout gb = new GridBagLayout();
			laframe.setLayout(gb);
			laframe.setBackground(Color.black);
			GridBagConstraints c = new GridBagConstraints();
			
			//tant qu'il y a des panels dans le gridBagLayout, on ajoute au panel un panel en prenant en compte
			//le gridBagConstraint
			for(int i = 0; i < ret.getListeContrainte().size();i++){
				c.fill = ret.getListeContrainte().get(i).getFill();
				c.insets = new Insets(ret.getListeContrainte().get(i).getInset1(),
										ret.getListeContrainte().get(i).getInset2(),
										ret.getListeContrainte().get(i).getInset3(),
										ret.getListeContrainte().get(i).getInset4());
				c.gridx = ret.getListeContrainte().get(i).getGridx();
				c.gridy = ret.getListeContrainte().get(i).getGridy();
				c.gridheight = ret.getListeContrainte().get(i).getGridheight();
				c.gridwidth = ret.getListeContrainte().get(i).getGridwidth();
				c.ipadx = ret.getListeContrainte().get(i).getIpadx();
				c.ipady = ret.getListeContrainte().get(i).getIpady();
				JPanel fond = new JPanel();
				Color couleur = new Color(ret.getListeContrainte().get(i).getR(), ret.getListeContrainte().get(i).getV(), ret.getListeContrainte().get(i).getB());
				fond.setBackground(couleur);
				gb.setConstraints(fond, c);
				laframe.add(fond);
			}
			
			return laframe;
		}
		
	
	/**
	 * Méthode load, elle retourne tout la LinkedHash des Panels avec leur GridBagLayout
	 *  
	 * @param chemin ou allez chercher le fichier
	 * @return le LinkedHashMap de tout le Drapeau
	 */
	 // --------------------- Méthode Load
	 public static LinkedHashMap<Object,Object> load(String chemin){
		DescriptionJaxbGB ret=null;
		//load du fichier
		try {
			File file = new File("_Drapeau/"+chemin);
			JAXBContext jaxbContext = JAXBContext.newInstance(DescriptionJaxbGB.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			ret = (DescriptionJaxbGB) jaxbUnmarshaller.unmarshal(file);
			
		}
		catch (JAXBException e) {e.printStackTrace();}
		
		//Linked général
		LinkedHashMap<Object,Object> total = new LinkedHashMap<Object, Object>();
		int nombre =0;
		total.put("Pays",ret.getPays());
		total.put("GMT",ret.getGmt());
		total.put("Taille", ret.getListeContrainte().size());
		
		//Tant qu'il y a des Panels, on ajoute à notre Linked général
		for(int i = 0; i < ret.getListeContrainte().size();i++){
			LinkedHashMap<String,Object> grid = new LinkedHashMap<String, Object>();
			grid.put("Fill",ret.getListeContrainte().get(i).getFill());
			grid.put("Inset1",ret.getListeContrainte().get(i).getInset1());
			grid.put("Inset2",ret.getListeContrainte().get(i).getInset2());
			grid.put("Inset3",ret.getListeContrainte().get(i).getInset3());
			grid.put("Inset4",ret.getListeContrainte().get(i).getInset4());
			grid.put("Gridx",ret.getListeContrainte().get(i).getGridx());
			grid.put("Gridy",ret.getListeContrainte().get(i).getGridy());
			grid.put("GridHeight",ret.getListeContrainte().get(i).getGridheight());
			grid.put("GridWidth",ret.getListeContrainte().get(i).getGridwidth());
			grid.put("Ipadx",ret.getListeContrainte().get(i).getIpadx());
			grid.put("Ipady",ret.getListeContrainte().get(i).getIpady());
			grid.put("Couleur",new Color(ret.getListeContrainte().get(i).getR(),
										ret.getListeContrainte().get(i).getV(),
										ret.getListeContrainte().get(i).getB()));
			total.put(i,grid);
			nombre++;
		}
	
		total.put("Largeur", ret.getLargeur());
		total.put("Hauteur",ret.getHauteur());
		total.put("Nombre",nombre);
		
		return total;
	 }
	
	 /**
	  * Méthode init_description, qui prends tout les paramètres du GridBagConstraints et ajoute le LinkedJaxB à liste des Constraints
	  * utilisé uniquement lors d'un store de drapeau
	  * @param num le numéro du Linked, devra être incrémenté pour chaque appelle de la méthode, 
	  * 		sinon, chaque linked sera enregistrer par dessus le précédent
	  * @param fill c.fill
	  * @param inset1 c.insets.top
	  * @param inset2 c.insets.left
	  * @param inset3 c.insets.bottom
	  * @param inset4 c.insets.right
	  * @param gridx
	  * @param gridy
	  * @param gridwidth
	  * @param gridheight
	  * @param ipady
	  * @param ipadx
	  * @param couleur
	  */
	// --------------------- Méthode init_description
	public void init_description(int num, int fill, int inset1, int inset2, int inset3, int inset4, int gridx, int gridy, int gridwidth, int gridheight, int ipady, int ipadx, Color couleur){
		LinkedJaxB lj = new LinkedJaxB();
		lj.init(fill, inset1, inset2, inset3, inset4, gridx, gridy, gridwidth, gridheight, ipady, ipadx, couleur);
		liste_contrainte.put(num, lj);
	}
	
}
