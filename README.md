# READ_ME Projet Drapeau
Lo�c Feuga & L�o-Paul Martin

## /*********IMPORTANT**********/
Pour que le projet fonctionne, il faut reconstruire l'arborescence suivante : 
	Votre_Dossier : _Drapeau
		         _Images
		         _Son
		         _Application_Drapeau_lib
		         _Application_Drapeau.jar
		         _run.bat
En lan�ant le run.bat l'application se lance 
/****************************/


## /*************Fonctionnalit�s****************/

Drapeaux:
+ drapeau � un niveau de panneaux => OK
+ drapeau � plusieurs niveaux de panneaux =>KO
+ drapeau avec images => OK
+ utilisation du gridbaglayout => OK
+ drapeau avec formes g�om�triques => OK
+ comportement homoth�tique du panneau lors de la modification de la taille du cadre principal de l�application => (sauf pour le GridBagLayout) OK 
 
Meta description du drapeau sous forme d�une structure de donn�es Java. dans le dossier Data
+ serialisation avec la classe utilitaire Data =>KO
+ serialisation dans un fichier texte avec generation/analyse => OK
+ serialisation en xml avec jaxb => OK
 
Sous panneaux sensibles � des clics souris,
+ InfoBulles (en anglais: tooltips) => OK
 
menu:
+ JCombobox pour naviguer dans les pays => OK
+ JButton pour la carte / mosaique de paysages / hymne, ... =>OK
 
la carte g�ographique d�taill�e du pays cible=>OK
 
une mosa�que de douze paysages remarquables du pays cible=>OK
OU slideshow dans une fen�tre externe:
+ menu:
  + lecture/pause =>  (Par l'interm�diaire d'un JSlider) OK
  + pr�c�dent (d�sactiv� pour le premier paysage), suivant (d�sactiv� pour le dernier)=>OK
  + choix du nombre de seconde d'affichage de chaque paysage (1s par d�faut)=>OK
  + utilisation du design pattern observable/observer => KO 
 
l�audition de l�hymne du pays (� partir d�un fichier au format mp3 !).
+ menu: lecture/pause, relire=>OK
 
affichage de l'heure locale => OK
 
utilisation du design pattern MVC =>OK
 
Un panneau avec description contextuelle des fonctionnalit�s du site
+ en HTML => KO
 
Packaging avec Ant (execution d'un fichier project.xml de votre conception situ� � la racine de l'application):
+ les sources et le dossier Data dans un .jar, => Externe au .jar OK
+ la javadoc dans un .jar, => Dans fichier doc OK
+ les sources dans un .jar, => Dans un .jar �x�cutable gr�ce � run.bat OK
/*****************************************/


## /****************Rajout***************/

Diaporama :
+JSlider permettant de changer la vitesse du diaporama les extr�mes gauche et droite ont volontairement �tait modifi� (tr�s tr�s vite, tr�s lent)

Menu : 
+Un menu apparait sur le drapeau quand le clic de la souris est enfonc�, en le relachant �a active l'item sur lequel la souris �tait 

Fenetre Descriptive :
+Une fenetre en plus sur la droite apporte les m�mes fonctionnalit�s que le menu, mais sauf une autre forme ( avec JButton, JComboBox)
/**************************************/

