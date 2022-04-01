package etapa3;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.image.BufferStrategy;

//clase bichos extendida con Canvas para poder pintar
public class Bichos extends Canvas {
	
	//Ancho y alto de la pantalla del juego
	public static final int ANCHO = 800;
	public static final int ALTO = 600;
	
	//declaramos el hashmap
	public HashMap sprites;
	
	//posiciones random del muñeco
	private static int posX = ANCHO/2;
	private static int posY = ALTO/2;
	
	//para la tiempa de recarga
	private static final int RETARDO = 10; 
	
	//variable para la velocidad del marciano
	public int vX = 2;
	
	//imagen en memoria
	public BufferedImage buffer;
	
	//definimos un bufferStrategy
	public BufferStrategy strategy;
	
	//variable para calcular los fps
	public int fps;
	
	
	//constructor de bichos
	public Bichos() {
		//caracteristicas del JFrame
		JFrame ventana = new JFrame("Bichos");
		setBounds(0,0, ANCHO, ALTO);
		
		//titulo de la ventana
		ventana.setTitle("Bichos Jorge Victoria");
		
		//quita el layout del panel y añade el content pane de la ventana del objeto Bichos
		JPanel panel = (JPanel)ventana.getContentPane();
		
		//Redimensionar el panel y la ventana
		panel.setBounds(0, 0, ANCHO, ALTO);
		panel.setPreferredSize(new Dimension(ANCHO, ALTO));
		
		//anular el layout manager
		panel.setLayout(null);
		
		//añadir el objeto Bichos como componente de la ventana
		panel.add(this);
		
		//tamaño y posicion de la ventana
		ventana.setBounds(0,0, ANCHO, ALTO);
		
		//para que no se pueda modificar el tamaño de la ventana
		ventana.setResizable(false);
		
		
		//listener para cerrar la aplicacion al pulsar salir en la ventana
		ventana.addWindowListener(
			new WindowAdapter() { public void windowClosing(WindowEvent e) { System.exit(0);} }
				);
		
		ventana.setVisible(true);
		
		
			createBufferStrategy(2);
			strategy = getBufferStrategy();
			requestFocus();
		
		//creamos el hashmap
		sprites = new HashMap();
		
		//creamos el buffer de memoria
		buffer = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
	}
	
	//metodo paint para pintar el marciano
	/*public void paint (Graphics g) {
		//BufferedImage extra  = cargaImagen("res/extra.gif");
		//g.drawImage(extra, 40, 40, this);
		//borramos la pantalla a mano
		//g.setColor(getBackground());
		//g.fillRect(0, 0, getWidth(), getHeight());
		//dibujamos el extraterrestre
		//g.drawImage (getSprite("res/extra.gif"),posX, posY, this);
		g.drawImage(buffer, 0, 0, this);
	}*/
	
	//metodo para cargar la imagen. Le pasamos la ruta del fichero
	public BufferedImage cargaImagen(String nombre) {
		URL url = null;
		try {
			//url = getClass().getClassLoader().getResource(nombre);
			url = getClass().getResource(nombre);
			return ImageIO.read(url);
		} catch (Exception e) {
			System.out.println("Error al cargar imagen " + nombre + " de " + url);
			System.out.println("ERROR: " + e.getClass().getName() + " " + e.getMessage());
			System.exit(0);
			return null;
		}
	}
	
	//metodo para darles un hashmap a los monigotes
	public BufferedImage getSprite (String nombre) {
		BufferedImage img = (BufferedImage)sprites.get(nombre);
		if(img==null) {
			img = cargaImagen(nombre);
			sprites.put(nombre,img);
		}
		return img;
	}
	
	//metodo para actualizar el mundo. Seria el paso 1
	public void actualizaMundo() {
		
		//creamos una posicion random del bicho
		//posX = (int)(Math.random() * ANCHO);
		//posY = (int)(Math.random() * ALTO);
		
		//velocidad y cambio de direccion del marciano
		posX += vX;
		if(posX < 0 || posX > ANCHO - 10) vX = -vX;
	}
	
	public void juego() {
		while (isVisible()) { //es visible el Canvas
			long tiempo = System.currentTimeMillis();
			actualizaMundo();
			pintaMundo();
			tiempo = System.currentTimeMillis() - tiempo;
			if( tiempo != 0) fps = (int)(1000L/tiempo); else fps = -1;
			//paint (getGraphics());
			try {
				Thread.sleep(RETARDO);
			} catch (InterruptedException e) {}
		}
	}
	
	//buffer para dibujar el mundo
	public void pintaMundo() {
		//Graphics g = buffer.getGraphics();	//un grafico para dibujar en RAM
		//g.setColor(getBackground());        //hacemos lo mismo
		Graphics g = strategy.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(getSprite("res/extra.gif"), posX, posY, this);
		g.setColor(Color.green);
		if(fps < 0) g.drawString("---fps", 10, ALTO - 50);
		else g.drawString(fps + " fps", 10, ALTO - 50);
		strategy.show();  		//lo dejamos con el buffer activo
	}
	

	public static void main(String[] args) {
		
		//creamos el JFramre
		Bichos b = new Bichos();
		
		//se inicia la partida
		b.juego();
	}

}

