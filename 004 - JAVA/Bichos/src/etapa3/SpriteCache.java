package etapa3;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class SpriteCache extends RecursosCache implements ImageObserver {
	
	private HashMap sprites;
	
	//se modifica toda la clase a partir del paso 4.4 de los sonidos
	public SpriteCache() {
		sprites = new HashMap();
	}
	 //ultimos pasos
	public static BufferedImage creaCompatible(int ancho, int alto, int transparencia) {
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		BufferedImage compatible = gc.createCompatibleImage(alto, ancho, transparencia);
		return compatible;
	}
	
	private BufferedImage cargaImagen(String nombre) {
		URL url = null; 	//primero intentamos con url (falla en Eclipse)
		File f = null;		//Intentamos con fichero (falla fuera de Eclipse)
		try {
			url = getClass().getClassLoader().getResource("etapa3/res/" + nombre);
			return ImageIO.read(url);
		} catch (Exception e) {
			System.out.println("Error al cargar imagen " + nombre + " de " + url);
			System.out.println("ERROR: " + e.getClass().getName() + " " + e.getMessage());
		} try {
			url = getClass().getClassLoader().getResource("etapa3/res/");
			return ImageIO.read(new File(url.getPath() + nombre));
		} catch(Exception e1) {
			System.out.println("Error al cargar imagen de fichero " + url);
			System.out.println("ERROR: " + e1.getClass().getName() + " " + e1.getMessage());
			System.exit(0);
			return null;
		}
	}
	
	/*public BufferedImage getSprite(String nombre) {
		BufferedImage img = (BufferedImage)sprites.get(nombre);
		if(img == null) {
			img = cargaImagen(nombre);
			sprites.put(nombre, img);
		}
		return img;
	}*/
	
	protected Object cargaRecurso(File f) {
		try {
			return ImageIO.read(f);
		} catch (Exception e) {
			System.out.println("Error al cargar imagen " + f);
			System.out.println("ERROR: " + e.getClass().getName()+ " " + e.getMessage());
			System.exit(0);
			return null;
		}
	}
	
	public BufferedImage getSprite(String nombre) {
		//BufferedImage carga = (BufferedImage)getRecurso(nombre);
		BufferedImage carga = (BufferedImage)sprites.get(nombre);
		if(carga == null) {
			carga = cargaImagen(nombre);
			sprites.put(nombre, carga);
		}
		BufferedImage compatible = creaCompatible(carga.getWidth(),carga.getHeight(),Transparency.BITMASK);
		Graphics g = compatible.getGraphics();
		g.drawImage(carga, 0, 0, this);
		return compatible;
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Esbozo de método generado automáticamente
		return (infoflags & (ALLBITS | ABORT)) == 0;
	}

}
