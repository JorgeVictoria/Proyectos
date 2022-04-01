package E705;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import javax.swing.JPanel;

public class E705 {

	private JFrame ecualizador; 				//JFrame
	private JPanel pCopia;						//Jpanel donde metemeos el histograma de la copia
	private File fichero;						//para guardar el fichero que leemos
	private JScrollPane SPfotoOriginal;			//ScrollPane de la foto original
	private JScrollPane SPfotoCopia;			//ScrollPane de la foto que es copia
	private BufferedImage bi;					//imagen con la que vamos a trabajar
	private ImageIcon ii;						//paso intermedio de file a bufferedImage
	private double[]histo = new double[256];	//histograma de los pixeles
	private double[]histoAcu = new double[256]; //histrograma acumulado
	private JPanel pOriginal;					//Jpanel donde metemos el histograma de la foto original

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					E705 window = new E705();
					window.ecualizador.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public E705() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ecualizador = new JFrame();
		ecualizador.setTitle("Jorge Victoria Andreu");
		ecualizador.setBounds(50, 50, 800, 700);
		ecualizador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ecualizador.getContentPane().setLayout(null);
		
		JButton btAbrir = new JButton("Abrir");
		btAbrir.setIcon(new ImageIcon("C:\\Users\\jvand\\OneDrive\\Estudios\\Workspace\\Eclipse\\Tema 07\\src\\E705\\iconos\\carpeta.png"));
		btAbrir.setBounds(10, 11, 120, 23);
		ecualizador.getContentPane().add(btAbrir);
		
		JButton btEcualizar = new JButton("Ecualizar");
		btEcualizar.setIcon(new ImageIcon("C:\\Users\\jvand\\OneDrive\\Estudios\\Workspace\\Eclipse\\Tema 07\\src\\E705\\iconos\\ecualizador.png"));
		btEcualizar.setEnabled(false);
		btEcualizar.setBounds(140, 11, 120, 23);
		ecualizador.getContentPane().add(btEcualizar);
		
		JButton btGuardar = new JButton("Guardar");
		btGuardar.setEnabled(false);
		btGuardar.setIcon(new ImageIcon("C:\\Users\\jvand\\OneDrive\\Estudios\\Workspace\\Eclipse\\Tema 07\\src\\E705\\iconos\\disquette.png"));
		btGuardar.setBounds(270, 11, 120, 23);
		ecualizador.getContentPane().add(btGuardar);
		
		SPfotoCopia = new JScrollPane();
		SPfotoCopia.setBounds(404, 45, 370, 255);
		ecualizador.getContentPane().add(SPfotoCopia);
		
		SPfotoOriginal = new JScrollPane();
		SPfotoOriginal.setBounds(10, 45, 370, 255);
		ecualizador.getContentPane().add(SPfotoOriginal);
		
		pOriginal = new JPanel() {
			
			public void paint(Graphics g) {
				super.paint(g);
				g.setColor(Color.black);
				
				//ejes horizontal y vertical
				g.drawLine(30, 270, 286, 270);
				g.drawLine(30, 270, 30, 20);
				
				g.drawString("0", 28, 290 );
				
				int ancho = 28;
				//dibujamos marcas y numeros
				for(int i = 1; i <= 5; i++) {
					ancho = ancho + 50;
					g.drawString(String.valueOf(i*50), ancho-5, 290);
					g.drawLine(ancho, 268, ancho, 272);
				}
			}
		};
		
		pOriginal.setBorder(new LineBorder(new Color(0, 0, 0)));
		pOriginal.setBackground(Color.WHITE);
		pOriginal.setBounds(10, 311, 370, 300);
		ecualizador.getContentPane().add(pOriginal);
		
		pCopia = new JPanel() {
			public void paint(Graphics g) {
				super.paint(g);
				g.setColor(Color.black);
				
				//ejes horizontal y vertical
				g.drawLine(30, 270, 286, 270);
				g.drawLine(30, 270, 30, 20);
				
				g.drawString("0", 28, 290 );
				
				int ancho = 28;
				//dibujamos marcas y numeros
				for(int i = 1; i <= 5; i++) {
					ancho = ancho + 50;
					g.drawString(String.valueOf(i*50), ancho-5, 290);
					g.drawLine(ancho, 268, ancho, 272);
				}
			}
		};
		pCopia.setBorder(new LineBorder(new Color(0, 0, 0)));
		pCopia.setBackground(Color.WHITE);
		pCopia.setBounds(404, 311, 370, 300);
		ecualizador.getContentPane().add(pCopia);
		
		//listener para el jfilechooser
		btAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//buscamos el fichero
				fichero = leerFichero();
				
				//cargamos la imagen
				cargaImagen();
				
				//vamos a crear el histograma
				calculoHistograma();
				
				//vamos a pintar el histograma
				pintaGrafica(pOriginal);
				
				//ponemos el boton de ecualizar activo
				btEcualizar.setEnabled(true);
		}
		});
		
		//listener para el ecualizador
				btEcualizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//ecualizamos la imagen
						bi = ecualizar(bi);
						
						//cargamos la imagen
						cargaImagenCopia();
						
						//vamos a crear el histograma
						calculoHistograma();
						
						//vamos a pintar el histograma
						pintaGrafica(pCopia);
						
						//ponemos el boton de guardar activo
						btGuardar.setEnabled(true);
				}
				});
				
				//listener para el guardado
				btGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						guardarFichero();
				}
				});
	}
	
	//metodo para leer fichero con jfileChooer
	public static File leerFichero() {

        JFileChooser fd = new JFileChooser();
        fd.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("archivos imagen", "jpg", "png", "gif");
        fd.addChoosableFileFilter(filter);
        fd.setDialogTitle("Selecciona el fichero a leer");
        fd.setSelectedFile(null);
        int opcion = fd.showOpenDialog(null);
        
        if (opcion != JFileChooser.APPROVE_OPTION) return null;
        
        File f = fd.getSelectedFile();
        
        return f;
        
    }
	
	//metodo para cargar la imagen en pantalla
	public void cargaImagen() {
		
		try {
			bi = ImageIO.read(fichero);
		} catch (IOException e1) {
			// TODO Bloque catch generado automáticamente
			e1.printStackTrace();
		}
		
		//llamamos al metodo para pasar a escala de grises
		bi = escalarGrises(bi);
		
		//escalamos la imagen
		ii = new ImageIcon(bi.getScaledInstance(240, 291, Image.SCALE_SMOOTH));
		
		//construimos un jlabel para poder incrustar la imagen en el jpanel
		JLabel etiqueta = new JLabel(ii);
		
		//cargamos la foto en el jscrollpane
		SPfotoOriginal.setViewportView(etiqueta);
	}
	
	//metodo para cargar la imagen ecualizada en pantalla
	public void cargaImagenCopia() {
		
		//escalamos la imagen
		ii = new ImageIcon(bi.getScaledInstance(240, 291, Image.SCALE_SMOOTH));
		
		//construimos un jlabel para poder incrustar la imagen en el jpanel
		JLabel etiqueta = new JLabel(ii);
		
		//cargamos la foto en el jscrollpane
		SPfotoCopia.setViewportView(etiqueta);
	}
	
	//metodo para escalar la imagen a grises, si viene en color
	public BufferedImage escalarGrises(BufferedImage imagen) {
		int mediaPixeles;
		int colorSRGB;
		Color nuevoColor;
		
		 //Recorremos la imagen píxel a píxel
        for( int i = 0; i < imagen.getWidth(); i++ ){
            for( int j = 0; j < imagen.getHeight(); j++ ){
                //Almacenamos el color del píxel
                nuevoColor=new Color(imagen.getRGB(i, j));
                //Calculamos la media de los tres canales (rojo, verde, azul)
                mediaPixeles=(int)((nuevoColor.getRed()+nuevoColor.getGreen()+nuevoColor.getBlue())/3);
                //Cambiamos a formato sRGB
                colorSRGB=(mediaPixeles << 16) | (mediaPixeles << 8) | mediaPixeles;
                //Asignamos el nuevo valor al BufferedImage
                imagen.setRGB(i, j,colorSRGB);
            }
        }
        //Retornamos la imagen
        return imagen;
	}
	
	//metodo para calcular los histogramas
	public void calculoHistograma() {
		int nivel;
		//ponemos el array de colores a 0
		for(int i = 0; i < 256; i++) {
			histo[i] = 0;
		}
		
		//Recorremos la imagen píxel a píxel y rellenamos el array histo
        for( int i = 0; i < bi.getWidth(); i++ ){
            for( int j = 0; j < bi.getHeight(); j++ ){
            	Color color =new Color( bi.getRGB(i, j));
            	nivel = (color.getRed() +color.getGreen() +color.getBlue() )/3;
            	histo[nivel]++;
            }
        }
        
        //ahora rellenamos el array acumulado
        //primero rellenamos el array de 0
        for(int i = 0; i < 256; i++) {
			histoAcu[i] = 0;
		}
		
        //ponemos la posicion 0
        histoAcu[0] = histo[0];
        
        //y ya rellenamos el resto
        for(int i = 1; i < 256; i++) {
        	histoAcu[i] = histoAcu[i-1] + histo[i];
        }
		
	}
	
	//metodo para pintar las graficas en pantalla
	public void pintaGrafica(JPanel original ) {
		
		Graphics gr = original.getGraphics();
		
		gr.setColor(Color.red);
		
		//pintamos barra con el resultado de las posiciones del arry histo
		for(int i = 0; i < 256; i++) {
			gr.drawLine(30+i, 270-(int)histo[i]/20, 30+i, 270);
		}
		
		gr.setColor(Color.black);
		
		//pintamos linea histograma historico acumulado
		for(int i = 0; i < 256; i++) {
			gr.drawString("-", 30+i, 270-(int)histoAcu[i]/600);
		}
		
	}
	
	//metodo para ecualizar la imagen
	public BufferedImage ecualizar(BufferedImage imagen) {
		int mediaPixeles;
		int colorSRGB;
		Color nuevoColor;
		
		for (int i = 0; i < imagen.getWidth(); i++) {
			for (int j = 0; j < imagen.getHeight(); j++) {
				//Almacenamos el color del píxel
                nuevoColor=new Color(imagen.getRGB(i, j));
                //Calculamos la media de los tres canales (rojo, verde, azul)
                mediaPixeles=(int)((nuevoColor.getRed()+nuevoColor.getGreen()+nuevoColor.getBlue())/3);
                
				imagen.setRGB(i,j,(int) (255*histoAcu[mediaPixeles]/histoAcu[255]));
			}
		}
		return imagen;
	}
	
	
	//metodo para guardar el fichero
	public void guardarFichero() {

        JFileChooser fd = new JFileChooser();
        fd.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fd.setAcceptAllFileFilterUsed(false);
        fd.setDialogTitle("Guardar imagen");
        fd.setApproveButtonText("Aceptar");
        int opcion = fd.showSaveDialog(null);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("archivos imagen", "jpg");
        if(opcion==JFileChooser.APPROVE_OPTION){
	        File f = fd.getSelectedFile();
	        String test = f.getAbsolutePath();
	        try {
				ImageIO.write(bi,"jpg",new File(test));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    } 
    }
}
