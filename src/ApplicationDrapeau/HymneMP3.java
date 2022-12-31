/**
 * Class HymneMp3, elle s'occupe de lancer le son de l'hymne dans une fenêtre au part 
 * 
 * @author Loic, Léo-Paul
 * @version V0_2_0;
 */

package ApplicationDrapeau;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.PlugInManager;
import javax.media.bean.playerbean.MediaPlayer;
import javax.media.format.AudioFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HymneMP3 {
	private MediaPlayer mp;

	/**
	 * Constructeur normal du son
	 * @param mp3 le nom du pays
	 */
	public HymneMP3(String mp3) {
		Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
		Format input2 = new AudioFormat(AudioFormat.MPEG);
		Format output = new AudioFormat(AudioFormat.LINEAR);
		final JFrame f = new JFrame("Hymne");
		f.setSize(300, 100);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		WindowAdapter d = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
				mp.stop();
			}
		};
		f.addWindowListener(d);
		f.setLayout(new GridLayout(2, 0));
		f.add(new JLabel("lecture"));
		PlugInManager.addPlugIn("com.sun.media.codec.audio.mp3.JavaDecoder",
				new Format[] { input1, input2 }, new Format[] { output },
				PlugInManager.CODEC);
		try {
			Player player = Manager.createPlayer(new MediaLocator(new File(
					"_Son/"+mp3+"_anthem.mp3").toURI().toURL()));
			mp = new MediaPlayer();
			mp.setPlayer(player);
			mp.setPreferredSize(new Dimension(400, 50));
			mp.start();

			f.add(mp);

		} catch (Exception ex) {ex.printStackTrace();}
		f.setVisible(true);
	}

}