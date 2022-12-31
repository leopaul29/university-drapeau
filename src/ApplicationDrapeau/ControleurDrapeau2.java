/**
 * Classe Controleur, point d'entré de l'application
 * 
 * 
 * @author Loic, Léo-Paul
 * 
 * @version V0_1_0 
 * 
 */


package ApplicationDrapeau;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class ControleurDrapeau2{ 
	/**
	 * Modele d'un drapeau en .txt
	 */
	private ModeleDrapeau modele_1;
	/**
	 * Modele d'un drapeau en jaxb
	 */
	private DescriptionJaxbGB modele_2;
	/**
	 * Vue JFrame de tous les drapeaux en bouton
	 */
	private Fenetres vue;
	/**
	 * Vue JPanel du drapeau sélectionné
	 */
	private VueDrapeau vue_drap;
	/**
	 * Vue JFrame du drapeau sélectionné
	 */
	private Drapeau lavue;
	/**
	 * Thread servant à rafraichir l'heure GMT du pays sélectionné
	 */
	private Thread lethread =new Thread();
	/**
	 * Vue JPanel de la selectionne descriptive du drapeau sélectionné
	 */
	private PanelDescriptif vue_wb;
	/**
	 * JFrame de la selection descriptive du drapeau
	 */
	private JFrame frame_wb;
	/**
	 * Ecouteur pour changer la vitesse du diaporama
	 */
	private EcouteurJouerAction ecouteurjouerAction;
	/**
	 * Ecouteur pour changer la vitesse du diaporama
	 */
	private EcouteurDrapeau ecouteur;

	/**
	 * Méthode Main, Point d'entré de l'application
	 * 
	 * @param args
	 */
	// -------------------- Méthode main
	public static void main(String args[]) {

		new ControleurDrapeau2();
	}
	
	/**
	 * Controleur normal, il créé un modele pour les drapeaux en txt, et un modele pour les drapeaux en jaxb
	 * il créé également les vues et les écouteurs
	 * 
	 */
	// --------------------- Constructeur normal (1)
	public ControleurDrapeau2(){

		GraphicsEnvironment ecran =GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		//Initialisation des deux types de modèle pour une lecture d'un fichier .txt et jaxb
		modele_1 = new ModeleDrapeau();
		setModele_2(new DescriptionJaxbGB());
		
		try {vue = new Fenetres();} 
		catch (IOException e) {e.printStackTrace();} 
		
		setVue_drap(new VueDrapeau(vue,modele_1)); 
		lavue = new Drapeau("",this);
		lavue.setSize(800,600);
		frame_wb = new JFrame();
		vue_wb = new PanelDescriptif();
		vue_wb.getBtnAfficher().addActionListener(new EcouteurBoutonPays(this,vue_wb,lethread,lavue,"",modele_1));
		ecouteurjouerAction = new EcouteurJouerAction(this);
		vue_wb.getBtnLancer().addActionListener(ecouteurjouerAction);
		vue_wb.getButton_1().addActionListener(new EcouteurBoutonPaysJaxB(this, vue_wb, lethread, lavue, "", modele_2));
		frame_wb.setContentPane(vue_wb);

		lavue.addMouseListener(new EcouteurDrapeau(lavue,this, ""));
		lavue.addMouseMotionListener(new EcouteurDrapeau(lavue,this,""));
		frame_wb.setLocation((int) (ecran.getMaximumWindowBounds().getWidth() * 32/40), 0);
		frame_wb.setSize(275,600);
		frame_wb.setVisible(true);
		
		//chargement de plusieurs bouton
		File f1 = new File("_Drapeau");
		for(int i = 0; i < f1.list().length;i++){
				if(f1.list()[i].contains("_drapeau")){
					String nom = f1.list()[i];
					nom = 	Fichier.sub_String(nom, '_');
					ajout_Bouton(nom, lavue, vue);
				}
		}
		
		ajout_BoutonJaxB("Norvege", lavue, vue);
		ajout_BoutonJaxB("Grece", lavue, vue);
		ajout_BoutonJaxB("Danemark", lavue, vue);

		vue.setVisible(true);
		
	 
	}
	
	/**
	 * Méthode creer_drapeau, elle créé un JPanel de drapeau avec le param
	 * 
	 * @param donnes : Doit contenir Pays, Nombre, Ligne, Couleur, Colonne en KEy
	 * @return JPanel : Retourne un JPanel contenant les couleurs spécifié dans donnees
	 */
	// --------------------- Méthode creer_fen
	public JPanel creer_drapeau(LinkedHashMap<?, ?> donnees){
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout( (Integer)donnees.get("Ligne"),(Integer)donnees.get("Colonne")  )   );
	
		for(int i = 0; i < (Integer)donnees.get("Nombre") ; i ++){
			JPanel pan2 = new JPanel();
		   Color c1 = Fichier.getCol((String)((LinkedHashMap<?, ?>)donnees.get("Couleur")).get(i));
		   pan2.setBackground(c1);
		   pan.add(pan2);
	   }
	   return pan;
   }
	
	/**
	 * Méthode creer_drapeauIM, elle créé un JPanel de drapeau avec le param
	 * 
	 * @param donnes : Doit contenir Pays, Nombre, Ligne, Couleur, Colonne, Image, Panel, Panels,  en KEy
	 * @return JPanel : Retourne un JPanel contenant les couleurs et les images spécifié dans donnees
	 * 
	 */
	// --------------------- Méthode creer_drapeau avec IMage
	public JPanel creer_drapeauIM(final LinkedHashMap<?, ?> donnees){
			
		JPanel ret = new JPanel();   int k =0;
		ret.setLayout(new GridLayout((Integer) donnees.get("Ligne"),(Integer)donnees.get("Colonne")));
		
		for(int i = 0; i < (Integer)donnees.get("Nombre") ; i ++){
		   JPanel pan = new JPanel();
		   Color c1 = Fichier.getCol((String) ((LinkedHashMap<?, ?>)donnees.get("Couleur")).get(i));
		
		   if((Integer)donnees.get("Panel") != null){
		   if(i == (Integer)donnees.get("Panel")){
			   pan.setLayout(new GridLayout());
			   PanelImage pan2 = new PanelImage((String)donnees.get("Image"));
				   pan2.setBackground(c1);
				   pan.add(pan2);
			   }else pan.setBackground(c1); 
		   }else if (donnees.get("Panels") != null){
			   pan.setLayout(new GridLayout());
		   
			   if((Integer)i == (Integer)((LinkedHashMap<?, ?>)donnees.get("Panels")).get(k)){
				   PanelImage pan2 = new PanelImage((String)((LinkedHashMap<?, ?>)donnees.get("Images")).get(k));
				   pan2.setBackground(c1);
				   pan.add(pan2);
				   k++;
			   }else pan.setBackground(c1);
			   
		   }
		   ret.add(pan);
		}
		return ret;
	}
		
	/**
	 * Méthode setFrame, elle initiliase un JFrame et y ajoute un Thread qui changera son titre en fonction
	 * de l'heure du pays qu'elle contient en JPanel
	 * 
	 * @param laframe JFrame à changer
	 * @param donnees LinkedHashMap contenant Pays, Largeur, Hauteur, GMT
	 * @throws IOException 
	 * 
	 * 
	 */
	public void setFrame(final JFrame laframe, final LinkedHashMap<?, ?> donnees) throws IOException{
		laframe.setTitle((String) donnees.get("Pays"));
		laframe.setSize((Integer)donnees.get("Largeur"),(Integer)donnees.get("Hauteur"));
		 lethread = new Thread(new Runnable() {
			public synchronized void run() {
				for(;;){
					laframe.setTitle((String)donnees.get("Pays")+" " + getDate((Integer)donnees.get("GMT")));
					try {this.wait(1);}
					catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		});
		lethread.start();
		
		((Drapeau) laframe).setImage(ImageIO.read(new File("_Images/menu2.png"))); 
		((Drapeau) laframe).setImage_c(ImageIO.read(new File("_Images/menu2_c.png"))); 
		((Drapeau) laframe).setImage_m(ImageIO.read(new File("_Images/menu2_m.png"))); 
		((Drapeau) laframe).setImage_p(ImageIO.read(new File("_Images/menu2_p.png")));
	}
	
	
	/**
	 * Méthode ajout_Bouton, elle place un bouton selon les params sur le param vue
	 * 
	 * @param pays Le nom du pays, on se servira de ça pour allez chercher les fichiers de donnee
	 * @param lafram la fenetre qui s'ouvrira lors du clic
	 * @param vue la fenetre à lequel on attache le bouton
	 */
	// --------------------- Méthode ajout_Bouton
	public void ajout_Bouton(final String pays, final Drapeau lafram, final JFrame vue){
		 final Button bouton = new Button(pays,pays+".png");
		 ecouteur = new EcouteurDrapeau(lafram,this, pays);
		 lafram.addMouseListener(ecouteur);
		 lafram.addMouseMotionListener(ecouteur);
		 bouton.addActionListener(new EcouteurSelectionPays(this, vue_wb, lethread, lafram, pays, modele_1));
		 bouton.addTooltip(pays);  					  
		 vue.add(bouton);
	}
	
	/**
	 * Méthode ajout_BoutonJaxB, elle place un bouton selon les params sur le param vue
	 * identique à la méthode ajout_Bouton, sauf qu'elle à pour objectif de load
	 * des drapeaux enregistrer avec JaxB
	 * 
	 * @param pays Le nom du pays, on se servira de ça pour allez chercher les fichiers de donnee
	 * @param lafram la fenetre qui s'ouvrira lors du clic
	 * @param vue la fenetre à lequel on attache le bouton
	 */
	// --------------------- Méthode ajout_Bouton
	public void ajout_BoutonJaxB(final String pays, final Drapeau lafram, JFrame vue){
		 final Button bouton = new Button(pays,pays+".png");
		 bouton.addActionListener(new ActionListener() {
			  @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
					vue_wb.getTField().setText(pays);
					lafram.setPays(pays);
					LinkedHashMap<?, ?> donnees = null;
					lethread.stop();
					JPanel pan  = DescriptionJaxbGB.build(pays+"_jaxb");
					donnees = DescriptionJaxbGB.load(pays+"_jaxb");
					lafram.addMouseListener(new EcouteurDrapeau(lafram, null,lafram.getPays()));
					lafram.addMouseMotionListener(new EcouteurDrapeau(lafram,null,lafram.getPays()));
					try {setFrame(lafram, donnees);} 
					catch (IOException e) {e.printStackTrace();}
					lafram.setContentPane(pan);
					lafram.setVisible(true);
					affiche_cache(lafram, vue_wb);
			  }});
		 bouton.addTooltip( pays);
		 vue.add(bouton);
	}
	
	/**
	 * Méthode getDate, elle renvois un String de l'heure courante par rapport à un décalage
	 * 
	 * @param decalage : Décalage en int pour le GMT (ex: France, decalage = 1)
	 */
	// --------------------- Méthode getDate
	public static String getDate(int decalage){
		Calendar cal = Calendar.getInstance();
		int hd = cal.get(Calendar.HOUR_OF_DAY) -1;
		hd+=decalage;
		return hd+"h:"+cal.get(Calendar.MINUTE)+"m:"+cal.get(Calendar.SECOND)+"s:"+cal.get(Calendar.MILLISECOND)+"'";
	}

	/**
	 * Méthode affiche_cache, elle affiche ou cache le message : "Restez appuyé sur la fenetre du drapeau pour faire apparaitre le menu"
	 * si une fenetre Drapeau est ouverte
	 * 
	 * @param lafenetre fenetre Drapeau sur laquel le test sera fait
	 * @param wb JPanel contenant le message à afficher et cacher
	 */
	// --------------------- Méthode affiche_cache
	public void affiche_cache(final JFrame lafenetre, final PanelDescriptif wb){
		Thread newTh = new Thread(new Runnable() {
			public void run() {
				while(lafenetre.isVisible()){
					if(lafenetre.isVisible()) wb.affich();
					else wb.cache();
				}
				wb.cache();
			}
		});
		newTh.start();
	}


	/**
	 * Getter de la vue Descriptif d'un drapeau
	 * @return un JPanel
	 */
	public PanelDescriptif getVue_wb() {return vue_wb;	}
	/**
	 * Setter de la vue Descriptif d'un drapeau
	 * @param vue_wb
	 */
	public void setVue_wb(PanelDescriptif vue_wb) {this.vue_wb = vue_wb;}
	/**
	 * Getter du thread qui permet de changer le titre en fonction de l'heure
	 * @return un Thread
	 */
	public Thread getLethread() {return lethread;}
	/**
	 * Setter du thread qui permet de changer le titre en fonction de l'heure
	 */
	public void setLethread(Thread lethread) {this.lethread = lethread;}
	/**
	 * Getter de l'écouteur qui controle les actions sur la fenetre descriptive d'un drapeau
	 * @return
	 */
	public EcouteurJouerAction getEcouteurjouerAction() {return ecouteurjouerAction;}
	/**
	 * Setter de l'écouteur qui controle les actions sur la fenetre descriptive d'un drapeau
	 * @param ecouteurjouerAction
	 */
	public void setEcouteurjouerAction(EcouteurJouerAction ecouteurjouerAction) {this.ecouteurjouerAction = ecouteurjouerAction;}
	public DescriptionJaxbGB getModele_2() {return modele_2;}
	public void setModele_2(DescriptionJaxbGB modele_2) {this.modele_2 = modele_2;}
	public VueDrapeau getVue_drap() {return vue_drap;}
	public void setVue_drap(VueDrapeau vue_drap) {	this.vue_drap = vue_drap;}

	public EcouteurDrapeau getEcouteurdrapeau() {
		return ecouteur;
	}

	public void setEcouteurdrapeau(EcouteurDrapeau ecouteurdrapeau) {
		this.ecouteur = ecouteurdrapeau;
	}
	
}
