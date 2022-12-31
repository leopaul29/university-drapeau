/**
 * Class LinkedJaxB, c'est une description d'une LinkedHashMap 
 * 
 * 
 * @author Loic, Léo-Paul
 * 
 * @version V0_1_0
 * 
 */

package ApplicationDrapeau;

import java.awt.Color;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LinkedJaxB {
	/**
	 * c.fill
	 */
	private int fill;
	/**
	 * c.insets.top
	 */
	private int inset1;
	/**
	 * c.insets.left
	 */
	private int inset2;
	/**
	* c.insets.bottom
	*/
	private int inset3;
	/**
	 * c.insets.right
	 */
	private int inset4;
	/**
	 * c.gridx
	 */
	private int gridx;
	/**
	 * c.gridy
	 */
	private int gridy;
	/**
	 * c.gridwidth
	 */
	private int gridwidth;
	/**
	 * c.gridheight
	 */
	private int gridheight;
	/**
	 * c.ipady
	 */
	private int ipady;
	/**
	 * c.ipadx
	 */
	private int ipadx;
	/**
	 * Color.getR()
	 */
	private int r;
	/**
	 * Color.B()
	 */
	private int b;
	/**
	 * Color.getG()
	 */
	private int v;
	
	/**
	 * Constructeur par défaut
	 */
	public LinkedJaxB(){}
	
	@XmlAttribute (name ="r")
	public int getR(){return r;}
	public void setR(int r){this.r = r;}
	@XmlAttribute (name ="b")
	public int getB(){return b;}
	public void setB(int b){this.b=b;}
	@XmlAttribute(name ="v")
	public int getV(){return v;}
	public void setV(int v){this.v = v ;}
	@XmlAttribute  (name="fill")
	public int getFill() {return fill;}
	public void setFill(int fill) {this.fill = fill;}
	@XmlAttribute  (name="inset1")
	public int getInset1() {return inset1;}
	public void setInset1(int insets) {this.inset1 = insets;}
	@XmlAttribute  (name="inset2")
	public int getInset2() {return inset2;}
	public void setInset2(int insets) {this.inset2 = insets;}
	@XmlAttribute  (name="inset3")
	public int getInset3() {return inset3;}
	public void setInset3(int insets) {this.inset3 = insets;}
	@XmlAttribute  (name="inset4")
	public int getInset4() {return inset4;}
	public void setInset4(int insets) {this.inset4 = insets;}
	@XmlAttribute  (name="gridx")
	public int getGridx() {return gridx;}
	public void setGridx(int gridx) {	this.gridx = gridx;}
	@XmlAttribute  (name="gridy")
	public int getGridy() {	return gridy;}
	public void setGridy(int gridy) {	this.gridy = gridy;}
	@XmlAttribute  (name="gridwidth")
	public int getGridwidth() 	{return gridwidth;}
	public void setGridwidth(int gridwidth) {	this.gridwidth = gridwidth;	}
	@XmlAttribute  (name="gridheight")
	public int getGridheight() {	return gridheight;}
	public void setGridheight(int gridheight) {	this.gridheight = gridheight;}
	@XmlAttribute  (name="ipady")
	public int getIpady() {	return ipady;}
	public void setIpady(int ipady) {	this.ipady = ipady;}
	@XmlAttribute  (name="ipadx")
	public int getIpadx() {	return ipadx;}
	public void setIpadx(int ipadx) {	this.ipadx = ipadx;}
	
	/**
	 * Méthode init, elle set tout les attributs de la Linked 
	 * @param fill
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
	public void init(int fill, int inset1, int inset2, int inset3, int inset4, int gridx, int gridy, int gridwidth, int gridheight, int ipady, int ipadx, Color couleur){
		this.fill= fill;
		this.inset1 = inset1;
		this.inset2 = inset2;
		this.inset3 = inset3;
		this.inset4 = inset4;
		this.gridx = gridx;
		this.gridy = gridy;
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
		this.ipadx = ipadx;
		this.ipady = ipady;
		this.r = couleur.getRed();
		this.b = couleur.getBlue();
		this.v = couleur.getGreen();
	}
	
	/**
	 * Méthode description, affiche tout les attributs au sens toString
	 */
	// --------------------- Méthode description = toString()
	public void description(){
		System.out.println("Fill : " + fill +"\nInsets : ("+inset1+", "+inset2+", "+inset3+", "+inset4+" )"+"\nGridx : "+gridx+
							"\nGridy : "+gridy+"\nGridwidth : "+gridwidth + "\nGridheight : "+gridheight+
							"\nipadx : "+ipadx+"\nipady : "+ipady);
		System.out.println("Couleur R "+r+"Couleur B "+b+"Couleur V "+ v);
	}
}
