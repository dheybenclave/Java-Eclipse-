package com.npc.project;


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
	private FloatControl gaincontrol;
	private InputStream audiosrc;
	private URL url;
	private InputStream bufferedIn;
	private AudioInputStream ais,dais;
	private AudioFormat baseformat,decodeformat;
	
	public SoundEngine (String path)
	{
		try
		{
			File file = new File(path);
			ais = AudioSystem.getAudioInputStream(file);
			baseformat  = ais.getFormat();
			decodeformat =new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
							baseformat.getSampleRate(), 16,baseformat.getChannels(),
							baseformat.getChannels()*2, baseformat.getSampleRate(),false);
			dais = AudioSystem.getAudioInputStream(decodeformat,ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
			gaincontrol = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void Stop()
	{
		if(clip.isRunning())
		{
			clip.stop();
		}
	
		
	}
	public void Close()
	{
		Stop();
		clip.drain();
		clip.close();
	}
	public void SetVolume(Float value)
	{
		gaincontrol.setValue(value);
	}
	public void Loop()
	{
		clip.loop(clip.LOOP_CONTINUOUSLY);
		while(!clip.isRunning())
		{
			clip.start();
		}
	}
	public void Play()
	{
		if(clip == null)
		{
			return ;
		}
		Stop();
		clip.setFramePosition(0);
		while(!clip.isRunning())
		{
			clip.start();
		}
	}
}
