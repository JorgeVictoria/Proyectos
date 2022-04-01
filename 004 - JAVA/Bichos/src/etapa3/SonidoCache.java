package etapa3;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SonidoCache extends RecursosCache {
	
	protected Object cargaRecurso(File f) {
		try { return Applet.newAudioClip(f.toURL()); } catch(Exception e) {};
		return null;
	}
	
	public AudioClip getAudioClip(String nombre) { return (AudioClip)getRecurso(nombre);}
	
	public void tocaSonido(final String nombre) {
		new Thread(
				new Runnable() {
					public void run() {getAudioClip(nombre).play();}
				}
				).start();
	}
	
	public void bucleSonido(final String nombre) {
		new Thread(
				new Runnable() {
					public void run() {
						try{
							Clip clip = AudioSystem.getClip();
							clip.open(AudioSystem.getAudioInputStream(getFicheroRecurso("musica.wav")));
							clip.start();
							clip.loop(Clip.LOOP_CONTINUOUSLY);
						}catch(Exception e) {}
						}
					}
				).start();}
}
