

/**
 * Classe FenetreSon, class qui lance le son de l'hymne d'un pays
 * 
 * 
 * @author Loic, Léo-Paul
 * 
 * @version V0_1_0 
 * 
 */
package ApplicationDrapeau;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;


public class FenetreSon extends JFrame {
	private static final long serialVersionUID = 1L;
	/**
	 * Nom du pays
	 */
	public String pays;
	/**
	 * le Thread
	 */
	private Thread thread;
	/**
	 * JButton pour arreter le son
	 */
	private JButton arreter;
	/**
	 * Getter du JButton pour arreter le son
	 * @return
	 */
	public JButton getArreter() {
		return arreter;
	}
	/**
	 * Setter du JButton pour arreter le son
	 * @param arreter
	 */
	public void setArreter(JButton arreter) {
		this.arreter = arreter;
	}
	/**
	 * Getter du thread
	 * @return
	 */
	public Thread getThread() {return thread;}
	/**
	 * Setter du thread
	 * @param thread
	 */
	public void setThread(Thread thread) {this.thread = thread;}


	/**
	 * Méthode pour crée une fenetre permettant d'arreter le son de l'hymne d'un pays
	 * @param pays
	 */
	// --------------------- Constructeur normal (1)
	public FenetreSon(final String pays){
		this.pays=pays;
		arreter = new JButton("Arreter");
		setTitle("Hymne-"+pays);
		setSize(200,100);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		add(arreter);
		thread = new Thread(new Runnable() {
			public void run() {
				try {jouerSon(pays);}
				catch (UnsupportedAudioFileException e) {e.printStackTrace();}
				catch (IOException e) {	e.printStackTrace();}
				catch (LineUnavailableException e) {e.printStackTrace();}
			}
		});
		
		thread.start();
		

	}
	
	/**
	 * Méthode jouerSon, qui permet de lancer le son d'un hymne
	 * @param hymne
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	// --------------------- Méthode jouerSon
	public static void jouerSon(String hymne) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		File file = null;
		file = new File("_Son/"+hymne+"_anthem.mp3");
		AudioInputStream in= AudioSystem.getAudioInputStream(file);
		AudioInputStream din = null;
		AudioFormat baseFormat = in.getFormat();
		AudioFormat decodedFormat =
		    new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
		                    baseFormat.getSampleRate(),
		                    16,
		                    baseFormat.getChannels(),
		                    baseFormat.getChannels() * 2,
		                    baseFormat.getSampleRate(),
		                    false);
		din = AudioSystem.getAudioInputStream(decodedFormat, in);
		rawplay(decodedFormat, din);
		in.close();
	}
	

	// --------------------- Méthode rawplay
	private static void rawplay(AudioFormat targetFormat,  AudioInputStream din) throws IOException, LineUnavailableException {
	
		byte[] data = new byte[4096];
		SourceDataLine line = getLine(targetFormat);
		if (line != null){
			line.start();
			int nBytesRead = 0;
	
			while (nBytesRead != -1 ){
				nBytesRead = din.read(data, 0, data.length);
				

				if (nBytesRead != -1)
					line.write(data, 0, nBytesRead);
			}
			
			line.drain();
			line.stop();
			line.close();
			din.close();
		}
	}
				
	// --------------------- Méthode getLine
	private static SourceDataLine getLine(AudioFormat audioFormat)throws LineUnavailableException{
		SourceDataLine res = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		res = (SourceDataLine) AudioSystem.getLine(info);
		res.open(audioFormat);
		
		return res;
	}


	
	
}
