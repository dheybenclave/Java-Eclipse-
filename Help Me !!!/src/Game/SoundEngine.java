package Game;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundEngine {

	private Clip clip;
	private FloatControl gainControl;
	private InputStream audioSource;
	private URL url;
	private InputStream buffedIn;
	private AudioInputStream ais,dais;
	private AudioFormat baseFormat,decodeFormat;
	
	public SoundEngine(String path){
		try{
			File file = new File (path);
			
			ais=AudioSystem.getAudioInputStream(file);
			baseFormat= ais.getFormat();
			decodeFormat = new AudioFormat (AudioFormat.Encoding.PCM_SIGNED,baseFormat.getSampleRate(),16,
					baseFormat.getChannels(),baseFormat.getChannels()*2, baseFormat.getSampleRate(),false);
			dais=AudioSystem.getAudioInputStream(decodeFormat,ais);
			clip=AudioSystem.getClip();
			clip.open(dais);
			clip.open();
			gainControl=(FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		}catch (Exception ex){
			
		}
	}
	
	public void stop(){
		if(clip.isRunning()){
			clip.stop();
		}
	}
	public void close(){
		stop();
		clip.drain();
		clip.close();
	}
	public void setVolume (float value){
		gainControl.setValue(value);
	}
	public void loop(){
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		while(!clip.isRunning()){
			clip.start();
		}
	}
	public void play(){
		if(clip == null){
			return;
		}
		stop();
		clip.setFramePosition(0);
		while(!clip.isRunning()){
			clip.start();
		}
	}
}
