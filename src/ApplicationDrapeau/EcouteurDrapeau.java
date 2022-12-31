/**
 * Class EcouteurDrapeau, la classe écoute la fenetre du drapeau pour piéger 
 * les clics sur les tiers verticaux du Drapeau 
 * Rappel : 1er tier => Hymne du pays
 * 			2eme tier => Carte du pays
 * 			3eme tier => Paysage du pays
 * La classe piège également le drag du clic de la souris pour afficher le menu
 * quand l'utilisateur relachera le clic, la classe interprétera son choix
 * 
 * @version V0_4_0
 * @author Loic, Léo-Paul
 * 
 * 
 * 
 */

package ApplicationDrapeau;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class EcouteurDrapeau implements MouseListener, MouseMotionListener{
	/**
	 * Hameçon vers le createur
	 */
	private Drapeau createur;
	/**
	 * Le pays du drapeau
	 */
	private String pays;
	/**
	 * Abscisse pour le clic realesed 
	 */
	private int x;
	/**
	 * Ordonnée pour le clic realesed
	 */
	private int y;
	/**
	 * Vitesse pour le diaporama
	 */
	private int vitesse = 1000;
	/**
	 * Hamecon vers le controleur
	 * @return
	 */
	private ControleurDrapeau2 controleur;
	
	// --------------------- Getter & Setter
	public int getX() {return x;}
	public void setX(int x) {this.x = x;}
	public int getY() {	return y;}
	public void setY(int y) {	this.y = y;}


	
	/**
	 * Constructeur normal de l'écouteur
	 * 
	 * @param createur Fenetre contenant le drapeau
	 * @param pays Pays représenté par le drapeau
	 */
	// --------------------- Constructeur normal (1)
	public EcouteurDrapeau(Drapeau createur, ControleurDrapeau2 controleur,String pays){
		this.createur =  (Drapeau) createur;
		this.setPays(pays);
		this.controleur = controleur;
	}
	
	
	/**
	 * Action quand on clic sur les tiers 
	 * 1er tier : Hymne
	 * 2eme tier : Carte
	 * 3eme tier : Paysage
	 * 
	 */
	// --------------------- Quand on clique + déclique
	public void mouseClicked(MouseEvent e) {
	/*	int tier1 = createur.getWidth() / 3;
		int tier2 = tier1 * 2;
		int x1 = e.getX();
		
		if(x1 < tier1){
			FenetreSon fs =new FenetreSon(createur.getPays());
			fs.getArreter().addActionListener(new EcouteurSon(fs));
		}else if(x1 >= tier1 && x1 < tier2){
			FenetreCarte france = new FenetreCarte(createur.getPays());
			france.setVisible(true);
		}else if (x1 >=tier2){
			FenetrePaysage fp = new FenetrePaysage(createur.getPays());
			fp.setVisible(true);
			createur.getCreateur().getVue_wb().getSlider().addChangeListener(new EcouteurSliderDiaporama(this,createur.getCreateur(),2));
		}*/
	
	}
	
	/**
	 * Action quand on reste appuyé sur la souris, 
	 * on affichera le menu
	 */
	// --------------------- Quand on reste appuyé => Menu spawn
	public void mousePressed(MouseEvent e) {
		 createur.x1 = e.getX();
		 createur.y1 = e.getY();
		 createur.setClick1(1, e.getX(),e.getY());
		 createur.repaint();
	}
	
	/**
	 * Action quand on relache le clic de la souris,
	 * 
	 */
	// --------------------- Quand on relache  => Menu despawn + intreprétation de la souris
	public void mouseReleased(MouseEvent e) {
		if(createur.musique == 1){
			@SuppressWarnings("unused")
			HymneMP3 dh  =new HymneMP3(createur.getPays());
			
			/*FenetreSon fs =new FenetreSon(createur.getPays());
			fs.getArreter().addActionListener(new EcouteurSon(fs));*/
			createur.musique=0;
		}else if(createur.carte == 1){
			FenetreCarte carte = new FenetreCarte(createur.getPays());
			carte.setVisible(true);
			createur.carte=0;
		}else if(createur.paysage ==1){
			final FenetrePaysage fp = new FenetrePaysage(createur.getPays());
			fp.getLeft().addActionListener(new EcouteurBouton(fp,-1));
			fp.getRight().addActionListener(new EcouteurBouton(fp,1));
			createur.getCreateur().getVue_wb().getSlider().addChangeListener(new EcouteurSliderDiaporama(this,controleur,2));

			Thread leThread = new Thread( new Runnable() {
				public synchronized void run() {
					
						for(int i = 0; i< 12;i++){
							if(i == 11) i=0;
							fp.setCheck(i);
							fp.repaint();
							try {this.wait(vitesse);}
							catch (InterruptedException e) {e.printStackTrace();		}
						}
					
					
				}});
			leThread.start();
			fp.setVisible(true);
			createur.paysage=0;
		}
		createur.setClick1(0, 10,10);
		createur.repaint();
	}

	
	// --------------------- Quand on dragge => Surlignement des icones 
	public void mouseDragged(MouseEvent e) {
		//if on est sur musique
		if( e.getX() < createur.x1 - createur.getWidth() / 50 && e.getY() < createur.y1 + createur.getWidth()/4 ){
			createur.musique = 1;
			createur.repaint();
		}else createur.musique = 0;
		
		
		//if on est sur carte
		if( e.getY() > createur.y1 + createur.getHeight() / 5){
			createur.carte = 1;
			createur.repaint();
		}else createur.carte = 0;
		
	
		//if on est sur paysage
		if(e.getX() > createur.x1 + createur.getWidth() / 10){
			createur.paysage = 1;
			createur.repaint();
		}else createur.paysage = 0;
		
		//if on est sur aucun des 3
		if(e.getY() < createur.y1) {
			createur.paysage = 0; createur.carte = 0; createur.musique = 0;
			//createur.repaint();
		}
		
	}


	// --------------------- Méthode du MouseListener Non Utilise
	public void mouseMoved(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	/**
	 * Getter de vitesse de diaporama
	 * @return int vitesse
	 */
	public int getVitesse() {return vitesse;}
	/**
	 * Setter de vitesse du diaporama
	 * @param vitesse
	 */
	public void setVitesse(int vitesse) {this.vitesse = vitesse;}
	/**
	 * Getter du nom de pays
	 * @return
	 */
	public String getPays() {return pays;}
	/**
	 * Setter du nom de Pays
	 */
	public void setPays(String pays) {this.pays = pays;}

}
