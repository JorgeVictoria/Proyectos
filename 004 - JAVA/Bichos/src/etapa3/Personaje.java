package etapa3;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Personaje {
	
	protected int x,y;
	protected int ancho,alto;
	protected String[] spriteNombres;
	protected Escenario escena;
	protected SpriteCache sc;
	protected int actualFrame;
	protected int frameVelocidad;
	protected int t;
	protected boolean marcadoParaBorrar;
	
	public Personaje(Escenario e) {
		this.escena = e;
		sc = e.getSpriteCache();
		actualFrame = 0;
	}
	
	public void paint (Graphics2D g) {
		g.drawImage(sc.getSprite(spriteNombres[actualFrame]), x, y, escena);
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int i) {
		x = i;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int i) {
		y = i;
	}
	
	public String[] getSpriteNombre() {
		return spriteNombres;
	}
	
	public void setSpriteNombres(String[] s) {
		spriteNombres = s;
		
		//esto cambia en el paso 3.6
		/*BufferedImage imagen = sc.getSprite(spriteNombre);
		alto = imagen.getHeight();
		ancho = imagen.getWidth();*/
		
		alto = 0;
		ancho = 0;
		for (int i = 0; i < s.length; i++) {
			BufferedImage imagen = sc.getSprite(spriteNombres[i]);
			alto = Math.max(alto, imagen.getHeight());
			ancho = Math.max(ancho, imagen.getWidth());
		}
	}
	
	public int getAlto() {
		return alto;
	}
	
	public int getAncho() {
		return ancho;
	}
	
	public void setAlto(int i) {
		alto = i;
	}
	
	public void setAncho(int i) {
		ancho = i;
	}
	
	public void actua() {
		//cambia en el paso 3.7
		//actualFrame = (actualFrame + 1) % spriteNombres.length;
		try {
			t++;
			if(t % frameVelocidad == 0) {
				t = 0;
				actualFrame = ( actualFrame + 1) % spriteNombres.length;
			} 
		} catch (Exception e) { e.getMessage(); }
	}
	
	public void setFrameVelocidad(int fv) { frameVelocidad = fv;}
	
	public void borra() {marcadoParaBorrar = true; }
	
	public boolean estaMarcadoParaBorrar() {return marcadoParaBorrar;}
	
	public Rectangle getFronteras() { return new Rectangle(x , y, ancho, alto); }
	
	public void colision(Personaje a) {}
	
	
	
}
