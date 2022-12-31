
/**
 * Classe Windowsbuild, qui permet de creer un Panel menuDrapeau et de le coller sur une JFrame
 * 
 * 
 * @author Loic, Léo-Paul
 * 
 * @version V0_1_0 
 * 
 */

package ApplicationDrapeau;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;

import java.awt.TextField;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSlider;


public class PanelDescriptif extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Bouton Lancer les drapeaux JaxB
	 */
	private JButton button_1;
	public JButton getButton_1() {
		return button_1;
	}
	public void setButton_1(JButton button_1) {
		this.button_1 = button_1;
	}


	/**
	 * Panel principal
	 */
	private JPanel contentPane;
	/**
	 * hamecon
	 */
	private ControleurDrapeau2 mc;
	/**
	 * Afficher le nom du drapeau selectionnée
	 */
	private TextField textField = new TextField();
	/**
	 * label choisir Pays
	 */
	private JLabel label;
	/**
	 * label choisir PaysJaxb
	 */
	private JLabel label_1;
	/**
	 * label choisir Action
	 */
	private JLabel label_2;
	/**
	 * Contient les Drapeau serialisé en .txt
	 */
	private JComboBox<?> lespays_txt;
	/**
	 * JButton pour afficher les Drapeau serialisé en .txt
	 */
	private JButton btnAfficher;
	/**
	 * Contient les Drapeau serialisé en jaxb
	 */
	private JComboBox<?> comboBox_jaxb;
	/**
	 * JButton pour afficher les Drapeau serialisé en jaxb
	 */
	private JButton btnLancer;
	/**
	 * Contient les action possible comme "afficherCarte", "afficherPaysage", "ecouterHymne"
	 */
	private JComboBox<?> comboBox_action;
	/**
	 * Permet de régler la vitesse du diaporama du paysage
	 */
	private JSlider slider;
	/**
	 * Getter du slider
	 * @return
	 */
	public JSlider getSlider() {
		return slider;
	}
	/**
	 * Setter du slider
	 * @param slider
	 */
	public void setSlider(JSlider slider) {
		this.slider = slider;
	}
	/**
	 * Getter du bouton lancer
	 * @return
	 */
	public JButton getBtnLancer() {
		return btnLancer;
	}
	/**
	 * Setter du bouton lancer
	 * @param btnLancer
	 */
	public void setBtnLancer(JButton btnLancer) {
		this.btnLancer = btnLancer;
	}
	/**
	 * Getter de la comboBox action
	 * @return
	 */
	public JComboBox<?> getComboBox_action() {
		return comboBox_action;
	}
	/**
	 * Setter de la comboBox action
	 * @param comboBox_action
	 */
	public void setComboBox_action(JComboBox<?> comboBox_action) {
		this.comboBox_action = comboBox_action;
	}
	// --------------------- Getter & Setter 
	/**
	 * Getter de la comboBox des Drapeau serialisé en .txt
	 * @return
	 */
	public JComboBox<?> getLespays() {return lespays_txt;}
	/**
	 * Setter de la comboBox des Drapeau serialisé en .txt
	 * @param lespays
	 */
	public void setLespays(JComboBox<?> lespays) {this.lespays_txt = lespays;}
	/**
	 * Getter du TField qui affiche le nom du drapeau selectionnée
	 * @return
	 */
	public TextField getTField(){return textField;}
	/**
	 * Setter du TField qui affiche le nom du drapeau selectionnée
	 * @param text
	 */
	public void setTField(String text){textField.setText(text);}
	/**
	 * Getter Label
	 * @return
	 */
	public JLabel getLabel(){return label;}
	
	/**
	 * Getter bounton afficher
	 * @return
	 */
	public JButton getBtnAfficher() {
		return btnAfficher;
	}
	/**
	 * Setter bounton afficher
	 * @param btnAffiche
	 */
	public void setBtnAfficher(JButton btnAffiche) {
		this.btnAfficher = btnAffiche;
	}
	/**
	 * Getter Combobox_jaxb
	 * @return
	 */
	public JComboBox<?> getComboBox_jaxb() {
		return comboBox_jaxb;
	}
	/**
	 * Setter Combobox_jaxb
	 * @param comboBox_jaxb
	 */
	public void setComboBox_jaxb(JComboBox<?> comboBox_jaxb) {
		this.comboBox_jaxb = comboBox_jaxb;
	}
	/**
	 * Getter contentPane
	 * @return
	 */
	public JPanel getContentPane() {
		return contentPane;
	}
	/**
	 * Setter contentPane
	 * @return
	 */
	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}
	/**
	 * Getter mc
	 * @return
	 */
	public ControleurDrapeau2 getMc() {
		return mc;
	}
	/**
	 * Setter mc
	 * @return
	 */
	public void setMc(ControleurDrapeau2 mc) {
		this.mc = mc;
	}
	
	/**
	 * Méthode cache, qui permet de cacher le text quand on a pas selectionné de drapeau
	 */
	public void cache(){label.setText("");label_1.setText("");label_2.setText("");}
	/**
	 * Méthode affich, qui permet de afficher le text quand on a selectionné un drapeau
	 */
	public void affich(){
		label.setText("Restez appuyé sur la fenetre ");
		label_1.setText("du drapeau pour faire ");
		label_2.setText("apparaitre le menu");
	}
	

	/**
	 * Create the frame.
	 */
	public PanelDescriptif() {

	
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		
		JLabel lblPaysActuelle = new JLabel("Pays Actuelle :");
		lblPaysActuelle.setBounds(5, 9, 107, 14);
		add(lblPaysActuelle);
		textField.setBounds(33, 31, 138, 22);
		
		
		textField.setEnabled(false);
		add(textField);
		
		 label = new JLabel("");
		label.setBounds(5, 77, 214, 14);
		add(label);
		
		label_1 = new JLabel("");
		label_1.setBounds(5, 89, 214, 14);
		add(label_1);
		
		label_2 = new JLabel("");
		label_2.setBounds(5, 102, 221, 14);
		add(label_2);
		
		 lespays_txt = new JComboBox();
		lespays_txt.setModel(new DefaultComboBoxModel(new String[] {"France", "Italie", "Allemagne", "Colombie", "Autriche", "Armenie", "Mali", "Pays bas", "Pologne", "Russie", "Chili", "Thailande", "Yemen", "Zambie", "Japon", "Maroc", "Tunisie", "Mali", "Egypte", "Ghana", "Liban", "Senegal", "Suisse"}));
		lespays_txt.setBounds(33, 239, 107, 20);
		add(lespays_txt);
		
		JLabel lblChoisirUnPays = new JLabel("Choisir un pays : ");
		lblChoisirUnPays.setBounds(10, 214, 102, 14);
		add(lblChoisirUnPays);
		
		 btnAfficher = new JButton("Afficher ");
		btnAfficher.setBounds(146, 238, 107, 23);
		add(btnAfficher);
		
		JLabel lblChoisirUnPays_1 = new JLabel("Choisir un pays (jaxb) : ");
		lblChoisirUnPays_1.setBounds(10, 291, 171, 14);
		add(lblChoisirUnPays_1);
		
		 comboBox_jaxb = new JComboBox();
		comboBox_jaxb.setModel(new DefaultComboBoxModel(new String[] {"Norvege", "Danemark", "Grece"}));
		comboBox_jaxb.setBounds(33, 316, 107, 20);
		add(comboBox_jaxb);
		
		 button_1 = new JButton("Afficher ");
		button_1.setBounds(146, 315, 107, 23);
		add(button_1);
		
		 comboBox_action = new JComboBox();
		comboBox_action.setModel(new DefaultComboBoxModel(new String[] {"Hymne", "Paysage", "Carte"}));
		comboBox_action.setBounds(33, 391, 107, 22);
		add(comboBox_action);
		
		JLabel lblChoisirUneAction = new JLabel("Choisir une action : ");
		lblChoisirUneAction.setBounds(10, 367, 102, 14);
		add(lblChoisirUneAction);
		
		 btnLancer = new JButton("Lancer");
		btnLancer.setBounds(146, 391, 89, 23);
		add(btnLancer);
		
		 slider = new JSlider();
		slider.setBounds(26, 442, 200, 26);
		add(slider);
		
		JLabel lblVitesseDuDiaporama = new JLabel("Vitesse du diaporama : ");
		lblVitesseDuDiaporama.setBounds(10, 424, 161, 14);
		add(lblVitesseDuDiaporama);
		
		
		//setVisible(true);
	}
	
}
