package E706;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

public class E706 {

	private JFrame frame;
	private BufferedReader br = null;	
	private File fichero;
	private int[] notas = new int[11];
	private int[] porcentaje = new int[11];
	private ArrayList<Double> list = new ArrayList<Double>(); //para almacenar las notas para la desviacion
	private int contador=0;
	private double nota;
	private String palabra ="";
	private double notaTotal=0;
	private JPanel panel;
	private double desviacion;
	private double sumaDesv=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					E706 window = new E706();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public E706() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 454, 371);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btAbrir = new JButton("Abrir Fichero");
		btAbrir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btAbrir.setBounds(10, 11, 142, 23);
		frame.getContentPane().add(btAbrir);
		
		JLabel lbFichero = new JLabel("");
		lbFichero.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbFichero.setBounds(162, 11, 201, 23);
		frame.getContentPane().add(lbFichero);
		
		panel = new JPanel() {
			public void paint(Graphics g) {
				super.paint(g);
				g.setColor(Color.black);
				
				//ejes horizontal y vertical
				g.drawLine(30, 250, 360, 250);
				g.drawLine(30, 250, 30, 16);
				
				//marcas eje horizontal
				int ancho = 60;
				for(int i = 0; i <= 10; i++) {
					g.drawString(Integer.toString(i), ancho-20, 266);
					g.drawLine(ancho, 248, ancho, 252);
					ancho += 30;
				}
				
				//marcas eje vertical
				ancho = 250;
				for(int i = 0; i <= 26; i++) {
					if(i== 0 || i == 10 || i == 20 || i == 26) {
						g.drawString(Integer.toString(i) +"%", 0, ancho+5);
						g.drawLine(26, ancho, 34, ancho);
					}
					else g.drawLine(28, ancho, 32, ancho);
					ancho -= 9;
				}
				
			}
		};
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 45, 415, 276);
		frame.getContentPane().add(panel);
		
		//listener para el jfilechooser
			btAbrir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//buscamos el fichero
					fichero = leerFichero();
					//ponemos nombre del fichero
					lbFichero.setText(fichero.getName());
					//recorremos el fichero y hacemos cosas con él
					recorrerNotas(fichero);
					//rellenamos con el grafico
					rellenarGrafico();
			}
			});
		
	}
	
	public static File leerFichero() {

        JFileChooser fd = new JFileChooser();
        fd.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("archivos CSV", "csv");
        fd.addChoosableFileFilter(filter);
        fd.setDialogTitle("Selecciona el fichero a leer");
        fd.setSelectedFile(null);
        int opcion = fd.showOpenDialog(null);
        
        if (opcion != JFileChooser.APPROVE_OPTION) return null;
        
        File f = fd.getSelectedFile();
        
        return f;
        
    }
	
	public void recorrerNotas(File fichero) {
		
		//rellenamos el array de notas a 0
		for(int i = 0; i < 11; i++) {
			notas[i] = 0;
		}
		
		try {
			//nos creamos un filereader envuelto en un buffer de lectura
			br = new BufferedReader(new FileReader(fichero));
			
			//leemos la primera linea
			String linea = br.readLine();
			
			//mientras hayan lineas que leer
			while (linea != null) {
				
				//vamos contando lineas leidas
			    contador++; 
			    
			    //construimos un array con los campos de la linea. Separamos los campos con split
				String [] fields = linea.split(";"); 
		
				//comprobamos que cada linea tiene los dos campos correctos
				if(fields.length == 2) {
					try {
						//comprobamos la conversion
						nota = Double.parseDouble(fields[1]);
					} catch (NumberFormatException exception) {
						//como nos vamos a encontrar con comas en vez de puntos, reconstruimos la linea
						//ademas elimino unas comillas que aparecen al final de cada linea
						//primero vemos si hay un 10
						if (fields[1].charAt(0) == 49 && fields[1].charAt(1) == 48 ) { //es un 10
							palabra = palabra + fields[1].charAt(0) + fields[1].charAt(1) + "." + fields[1].charAt(3) + fields[1].charAt(4);
						}
						//no es un 10
						else palabra = palabra + fields[1].charAt(0) + "." + fields[1].charAt(2) + fields[1].charAt(3);
						//con la nueva linea correctamente creada, ya podemos hacer la conversion
						nota = Double.parseDouble(palabra);
						
						list.add(nota);
						
						notaTotal += nota; //almacenamos en el total de notas
						palabra = ""; //inicializamos la cadena
					}
					
					//vamos a incrementar el valor en la posicion correspondiente del array segun la nota
					
					//obtenemos la parte entera
					int num = (int)nota;
					
					//incrementamos la posicion en el array
					notas[num]++;
					
					//calcular porcentajes de cada nota
					for(int i = 0; i < 11; i++) {
						porcentaje[i] = notas[i]*100/contador;
					}
					
					
					
				} 
				
				 //leemos la siguiente linea
				linea = br.readLine();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		finally {  //cerramos cosas
          // br.close();
         }		
	}
	
	//este metodo permite crear la grafica de barras despues de todos los calculos
	public void rellenarGrafico() {
		
		Graphics gr = panel.getGraphics();
		gr.setColor(Color.GREEN);
		
		//pintamos
		int ejeX = 32;
		for(int i = 0; i <= 10; i++) {
			gr.fillRect(ejeX, 250-porcentaje[i]*9, 26, porcentaje[i]*9);
			ejeX += 30;
		}
		
		gr.setColor(Color.black);
		
		gr.drawString("Media:" + notaTotal/contador, 40, 10);
		
		for(int i = 0; i < list.size(); i++) {
			sumaDesv = sumaDesv + Math.pow((list.get(i)-notaTotal/contador),2);
		}
		
		sumaDesv = sumaDesv/contador;
		desviacion = Math.sqrt(sumaDesv);
		
		gr.drawString("Desviacion: " + String.valueOf(desviacion), 200, 10);
	}
}
