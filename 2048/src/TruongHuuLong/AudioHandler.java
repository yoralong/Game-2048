package TruongHuuLong;

import java.net.URL;
import java.util.HashMap;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class AudioHandler {
	
	private static AudioHandler handler;
	private HashMap<String,Clip> sounds;
	
	private AudioHandler() {
		sounds = new HashMap<String,Clip>();
	}
	
	public static AudioHandler getInstance() {
		if(handler == null) {
			handler =  new AudioHandler();
		}
		return handler;
	}
	
	public void load(String resourcePath,String name) {
		URL resource = AudioHandler.class.getClassLoader().getResource(resourcePath);
		AudioInputStream input = null;
		try{
			input = AudioSystem.getAudioInputStream(resource);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		AudioFormat baseFormat = input.getFormat();
		if(baseFormat.getEncoding()==AudioFormat.Encoding.PCM_SIGNED) {
			try {
				Clip c =  AudioSystem.getClip();
				c.open(input);
				sounds.put(name, c);
				return;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		AudioFormat decodedFormat = new AudioFormat(
				AudioFormat.Encoding.PCM_SIGNED,
				baseFormat.getSampleRate(),
				16,
				baseFormat.getChannels(),
				baseFormat.getChannels()*2,
				baseFormat.getSampleRate(),
				false
				);
			
		AudioInputStream decodedIn = AudioSystem.getAudioInputStream(decodedFormat,input);
		try {
			Clip c =  AudioSystem.getClip();
			c.open(decodedIn);
			sounds.put(name, c);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void play(String name, int loopCount) {
		if(sounds.get(name).isRunning()) {
			sounds.get(name).stop();
		}
		sounds.get(name).setFramePosition(0);
		sounds.get(name).loop(loopCount);
	}
	
	public void adjustVolume(String name , int value) {
		FloatControl control = (FloatControl)sounds.get(name).getControl(FloatControl.Type.MASTER_GAIN);
		control.setValue(value);
	}
}
