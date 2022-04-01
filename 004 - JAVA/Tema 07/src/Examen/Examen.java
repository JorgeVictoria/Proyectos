package Examen;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Examen {

	private JFrame frmJorgeVictoriaAndreu;
	private JTextField tfAciertos;
	private JTextField tfFallos;
	private JTextField tfPuntos;
	private int radio = 0;
	private int ex = 0;
	private int ey = 0;
	private int cx = 0;
	private int cy = 0;
	private int x = 0;
	private int y = 0;
	private int distancia = 0;
	private int aciertos = 0;
	private int fallos = 0;
	private int puntos = 0;
	private int posXMax = 0;
	private int posYMax = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Examen window = new Examen();
					window.frmJorgeVictoriaAndreu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Examen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJorgeVictoriaAndreu = new JFrame();
		frmJorgeVictoriaAndreu.setTitle("Jorge Victoria Andreu");
		frmJorgeVictoriaAndreu.setResizable(false);
		frmJorgeVictoriaAndreu.setBounds(100, 100, 410, 460);
		frmJorgeVictoriaAndreu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJorgeVictoriaAndreu.getContentPane().setLayout(null);
		
		JPanel pSuperior = new JPanel();
		pSuperior.setBorder(new LineBorder(Color.BLACK));
		pSuperior.setBounds(new Rectangle(0, 0, 400, 50));
		pSuperior.setBounds(0, 0, 400, 30);
		frmJorgeVictoriaAndreu.getContentPane().add(pSuperior);
		pSuperior.setLayout(null);
		
		JLabel lbAciertos = new JLabel("Aciertos");
		lbAciertos.setBounds(10, 11, 54, 14);
		pSuperior.add(lbAciertos);
		
		tfAciertos = new JTextField();
		tfAciertos.setEditable(false);
		tfAciertos.setBounds(58, 8, 30, 20);
		pSuperior.add(tfAciertos);
		tfAciertos.setColumns(10);
		
		JLabel lbFallos = new JLabel("Fallos");
		lbFallos.setBounds(102, 11, 40, 14);
		pSuperior.add(lbFallos);
		
		tfFallos = new JTextField();
		tfFallos.setEditable(false);
		tfFallos.setBounds(137, 8, 30, 20);
		pSuperior.add(tfFallos);
		tfFallos.setColumns(10);
		
		JLabel lbPuntos = new JLabel("Puntos");
		lbPuntos.setBounds(177, 11, 40, 14);
		pSuperior.add(lbPuntos);
		
		tfPuntos = new JTextField();
		tfPuntos.setEditable(false);
		tfPuntos.setBounds(224, 8, 30, 20);
		pSuperior.add(tfPuntos);
		tfPuntos.setColumns(10);
		
		JButton btIntento = new JButton("Nuevo Intento");
		btIntento.setBounds(267, 7, 123, 18);
		pSuperior.add(btIntento);
		
		JPanel pInferior = new JPanel();
		pInferior.setBackground(Color.WHITE);
		pInferior.setBounds(0, 31, 400, 400);
		frmJorgeVictoriaAndreu.getContentPane().add(pInferior);
		
		btIntento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfAciertos.setText("0");
				tfFallos.setText("0");
				tfPuntos.setText("0");
				//ajusto el radio a las dimensiones del panel para que salga entero
				radio = 190;
				//en principio esto lo descarto porque le voy a poner una posicion incial fija
				//ex = (int)(Math.random() * pInferior.getWidth());
				//ey = (int)(Math.random() * pInferior.getHeight());
				//reiniciamos todos las puntuaciones y posiciones
				aciertos = 0;
				fallos = 0;
				puntos = 0;
				posXMax = 0;
				posYMax = 0;
				ex = posXMax;
				ey = posYMax;
				Graphics g = pInferior.getGraphics();
				//dibujamos el ovalo relleno
				g.setColor(Color.blue);
				g.fillOval(ex, ey, radio * 2, radio * 2);
				//dibujamos el borde del ovalo
				g.setColor(Color.black);
				g.drawOval(ex, ey, radio * 2, radio * 2);
			}
		});
		
		pInferior.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//averiguamos la posicion del centro del circulo
				cx = ex + radio;
				cy = ey + radio;
				//almacenamos donde hacemos click
				x = e.getX();
				y = e.getY();
				//calculamos la distancia
				distancia =(int) Math.sqrt(Math.pow((cx-x),2) + (Math.pow((cy-y), 2)));
				if (distancia <= radio && radio != 0) {
					aciertos++;
					tfAciertos.setText(String.valueOf(aciertos));
					radio = radio-10;
					//como quitamos 10 al radio, ganamos 10 pixeles para colocar el circulo y que no se salga
					posYMax = posYMax + 20;
					posXMax = posXMax + 20;
					ex = (int)(Math.random() * posXMax);
					ey = (int)(Math.random() * posYMax);
					Graphics g = pInferior.getGraphics();
					g.clearRect(0, 0, 400, 400);
					g.setColor(Color.white);
					g.fillRect(0, 0, 400, 400);
					//dibujamos el ovalo relleno
					g.setColor(Color.blue);
					g.fillOval(ex, ey, radio * 2, radio * 2);
					//dibujamos el borde del ovalo
					g.setColor(Color.black);
					g.drawOval(ex, ey, radio * 2, radio * 2);
					pInferior.setBackground(Color.white);
					
				}
				else {
					if(radio != 0) {
					fallos++;
					tfFallos.setText(String.valueOf(fallos));
					}
				}
				
				puntos = (100*aciertos)/(aciertos+fallos);
				tfPuntos.setText(String.valueOf(puntos));
			}
			
		});
	}
}
