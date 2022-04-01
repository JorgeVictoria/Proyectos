package E703;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;

public class E703 {
	
	//variables
	private JFrame frmCalculadoraJorgeVictoria; //el JFrame sobre el que vamos a trabajar
	private JTextField tfPantalla;				//pantalla de la calculadora
	private String valorA = "";					//primer operando
	private String valorB = "";					//segundo operando
	private String signo = "";					//signo de la operacion
	private boolean bloqueoPantalla = false;	//cuando imprimimos el resultado, se bloquea la pantalla
	private double potenciaDouble;				//para calcular la potencia cuando el numero tiene decimales
	private int potenciaInteger;				//para calcular la potencia cuando el numero es un entero
	private double raizCuadrada;				//calcula la raiz cuadrada

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					E703 window = new E703();
					window.frmCalculadoraJorgeVictoria.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public E703() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//Jframe y sus parametros
		frmCalculadoraJorgeVictoria = new JFrame();
		frmCalculadoraJorgeVictoria.setResizable(false);
		frmCalculadoraJorgeVictoria.setTitle("Calculadora Jorge Victoria");
		frmCalculadoraJorgeVictoria.setBounds(100, 100, 455, 300);
		frmCalculadoraJorgeVictoria.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCalculadoraJorgeVictoria.getContentPane().setLayout(null);
		
		//pantalla de la calculadora
		tfPantalla = new JTextField();
		tfPantalla.setBorder(new LineBorder(new Color(0, 0, 0)));
		tfPantalla.setEditable(false);
		tfPantalla.setBackground(new Color(154, 205, 50));
		tfPantalla.setHorizontalAlignment(SwingConstants.RIGHT);
		tfPantalla.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		tfPantalla.setText("0");
		tfPantalla.setBounds(10, 0, 420, 50);
		frmCalculadoraJorgeVictoria.getContentPane().add(tfPantalla);
		tfPantalla.setColumns(10);
		
		//boton para el digito 7
		JButton bt7 = new JButton("7");
		bt7.setBorder(new LineBorder(new Color(0, 0, 0)));
		bt7.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		bt7.setBounds(10, 61, 80, 40);
		frmCalculadoraJorgeVictoria.getContentPane().add(bt7);
		
		//boton para el digito 8
		JButton bt8 = new JButton("8");
		bt8.setBorder(new LineBorder(new Color(0, 0, 0)));
		bt8.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		bt8.setBounds(95, 61, 80, 40);
		frmCalculadoraJorgeVictoria.getContentPane().add(bt8);
		
		//boton para el digito 9
		JButton bt9 = new JButton("9");
		bt9.setBorder(new LineBorder(new Color(0, 0, 0)));
		bt9.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		bt9.setBounds(180, 61, 80, 40);
		frmCalculadoraJorgeVictoria.getContentPane().add(bt9);
		
		//boton para borrar digito
		JButton btdel = new JButton("DEL");
		btdel.setToolTipText("Borrar Digito");
		btdel.setFont(new Font("Comic Sans MS", Font.BOLD, 10));
		btdel.setBorder(new LineBorder(new Color(0, 0, 0)));
		btdel.setBackground(new Color(255, 215, 0));
		btdel.setBounds(265, 61, 80, 40);
		frmCalculadoraJorgeVictoria.getContentPane().add(btdel);
		
		//boton para limpiar la pantalla
		JButton btLimpiar = new JButton("CL");
		btLimpiar.setToolTipText("Limpiar Pantalla");
		btLimpiar.setFont(new Font("Comic Sans MS", Font.BOLD, 10));
		btLimpiar.setBorder(new LineBorder(new Color(0, 0, 0)));
		btLimpiar.setBackground(new Color(255, 215, 0));
		btLimpiar.setBounds(350, 61, 80, 40);
		frmCalculadoraJorgeVictoria.getContentPane().add(btLimpiar);
		
		//boton para el digito 4
		JButton bt4 = new JButton("4");
		bt4.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		bt4.setBorder(new LineBorder(new Color(0, 0, 0)));
		bt4.setBounds(10, 112, 80, 40);
		frmCalculadoraJorgeVictoria.getContentPane().add(bt4);
		
		//boton para el digito 5
		JButton bt5 = new JButton("5");
		bt5.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		bt5.setBorder(new LineBorder(new Color(0, 0, 0)));
		bt5.setBounds(95, 112, 80, 40);
		frmCalculadoraJorgeVictoria.getContentPane().add(bt5);
		
		//boton para el digito 6
		JButton bt6 = new JButton("6");
		bt6.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		bt6.setBorder(new LineBorder(new Color(0, 0, 0)));
		bt6.setBounds(180, 112, 80, 40);
		frmCalculadoraJorgeVictoria.getContentPane().add(bt6);
		
		//boton para la operacion suma
		JButton btsuma = new JButton("+");
		btsuma.setToolTipText("Sumar");
		btsuma.setForeground(new Color(255, 255, 255));
		btsuma.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btsuma.setBorder(new LineBorder(new Color(0, 0, 0)));
		btsuma.setBackground(new Color(0, 0, 255));
		btsuma.setBounds(265, 112, 80, 40);
		frmCalculadoraJorgeVictoria.getContentPane().add(btsuma);
		
		//boton para la operacion resta
		JButton btresta = new JButton("-");
		btresta.setToolTipText("Restar");
		btresta.setForeground(new Color(255, 255, 255));
		btresta.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btresta.setBorder(new LineBorder(new Color(0, 0, 0)));
		btresta.setBackground(new Color(0, 0, 255));
		btresta.setBounds(350, 112, 80, 40);
		frmCalculadoraJorgeVictoria.getContentPane().add(btresta);
		
		//boton para el digito 1
		JButton bt1 = new JButton("1");
		bt1.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		bt1.setBorder(new LineBorder(new Color(0, 0, 0)));
		bt1.setBounds(10, 163, 80, 40);
		frmCalculadoraJorgeVictoria.getContentPane().add(bt1);
		
		//boton para el digito 2
		JButton bt2 = new JButton("2");
		bt2.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		bt2.setBorder(new LineBorder(new Color(0, 0, 0)));
		bt2.setBounds(95, 163, 80, 40);
		frmCalculadoraJorgeVictoria.getContentPane().add(bt2);
		
		//boton para el digito 3
		JButton bt3 = new JButton("3");
		bt3.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		bt3.setBorder(new LineBorder(new Color(0, 0, 0)));
		bt3.setBounds(180, 163, 80, 40);
		frmCalculadoraJorgeVictoria.getContentPane().add(bt3);
		
		//boton para la operacion producto
		JButton btproducto = new JButton("*");
		btproducto.setToolTipText("Producto");
		btproducto.setForeground(new Color(255, 255, 255));
		btproducto.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btproducto.setBorder(new LineBorder(new Color(0, 0, 0)));
		btproducto.setBackground(new Color(0, 0, 255));
		btproducto.setBounds(265, 163, 80, 40);
		frmCalculadoraJorgeVictoria.getContentPane().add(btproducto);
		
		//boton para la operacion division
		JButton btdivision = new JButton("/");
		btdivision.setToolTipText("Division");
		btdivision.setForeground(new Color(255, 255, 255));
		btdivision.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btdivision.setBorder(new LineBorder(new Color(0, 0, 0)));
		btdivision.setBackground(new Color(0, 0, 255));
		btdivision.setBounds(350, 163, 80, 40);
		frmCalculadoraJorgeVictoria.getContentPane().add(btdivision);
		
		//boton para el digito 0
		JButton bt0 = new JButton("0");
		bt0.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		bt0.setBorder(new LineBorder(new Color(0, 0, 0)));
		bt0.setBounds(10, 214, 80, 40);
		frmCalculadoraJorgeVictoria.getContentPane().add(bt0);
		
		//boton para el digito decimal
		JButton btpunto = new JButton(".");
		btpunto.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btpunto.setBorder(new LineBorder(new Color(0, 0, 0)));
		btpunto.setBounds(95, 214, 80, 40);
		frmCalculadoraJorgeVictoria.getContentPane().add(btpunto);
		
		//boton para el signo igual
		JButton btIgual = new JButton("=");
		btIgual.setToolTipText("Igual");
		btIgual.setForeground(new Color(255, 255, 255));
		btIgual.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btIgual.setBorder(new LineBorder(new Color(0, 0, 0)));
		btIgual.setBackground(new Color(0, 0, 255));
		btIgual.setBounds(180, 214, 80, 40);
		frmCalculadoraJorgeVictoria.getContentPane().add(btIgual);
		
		//boton para la operacion potencia
		JButton btCuadrado = new JButton("^");
		btCuadrado.setToolTipText("Cuadrado");
		btCuadrado.setForeground(new Color(255, 255, 255));
		btCuadrado.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btCuadrado.setBorder(new LineBorder(new Color(0, 0, 0)));
		btCuadrado.setBackground(new Color(0, 0, 255));
		btCuadrado.setBounds(265, 214, 80, 40);
		frmCalculadoraJorgeVictoria.getContentPane().add(btCuadrado);
		
		//boton para la operacion raiz cuadrada
		JButton btRaiz = new JButton("R");
		btRaiz.setToolTipText("Raiz Cuadrada");
		btRaiz.setForeground(new Color(255, 255, 255));
		btRaiz.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btRaiz.setBorder(new LineBorder(new Color(0, 0, 0)));
		btRaiz.setBackground(new Color(0, 0, 255));
		btRaiz.setBounds(350, 214, 80, 40);
		frmCalculadoraJorgeVictoria.getContentPane().add(btRaiz);
		
		//listener de los digitos
		ActionListener digitos = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				 if(!bloqueoPantalla) { 
					//si solo esta el cero, lo quitamos y añadimos el digito. Sino, añadimos el digito
					if (tfPantalla.getText().charAt(0) == 48 && tfPantalla.getText().length() == 1) tfPantalla.setText(btn.getText());
					else tfPantalla.setText(tfPantalla.getText() + btn.getText());
				} 
			}
		};
		
		//listener para poner un punto
		ActionListener decimal = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				if(!bloqueoPantalla) {
					//vemos si existe un punto y sino, lo podemos añadir
					if (!tfPantalla.getText().contains(".")) tfPantalla.setText(tfPantalla.getText() + btn.getText());
				} 
			}
		};
		
		//listener para borrar digito
		ActionListener borrarDigito = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				if (!bloqueoPantalla) {
					//si solo hay un caracter, pero no es el cero. Si solo hay un cero en pantalla, nada que borrar
					if (tfPantalla.getText().charAt(0) != 48 && tfPantalla.getText().length() == 1) tfPantalla.setText("0");
					//si hay mas de un caracter, vamos borrando por la derecha. 
					if (tfPantalla.getText().length() > 1) {
						tfPantalla.setText(tfPantalla.getText().substring(0, tfPantalla.getText().length()-1));
					}
					//puede ocurrir que borremos todos los caracteres, pues ponemos un cero
					if (tfPantalla.getText().length() == 0) tfPantalla.setText("0");
				} 
			}
		};
		
		//listener para las operaciones binarias
		ActionListener operacion = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				JButton btn = (JButton) e.getSource();
				//almacenamos el texto
				signo = btn.getText();
				//solo pasamos el valor en caso de que no lo hayamos hecho
				//si valorA tuviese un valor, el cambio de signo va cambiando hasta que no pulsemos =
				//una vez leido el primer operando, ponemos el digito en 0
				if(valorA.equals("")) {
					valorA = tfPantalla.getText();
					tfPantalla.setText("0");
				}
				
			}
		};
		
		//listener para el boton igual
		ActionListener igual = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!bloqueoPantalla) {
					//para recoger el valorB, valorA debe tener un valor
					if (valorA.length() > 0) {
						valorB = tfPantalla.getText();
						//llamada al metodo para realizar la operacion binaria correspondiente
						opBinaria();
						bloqueoPantalla = true;
					}
				}
				
			}
			
			//metodo que realiza una operacion binaria en funcion del signo almacenado
			//he intentado tener en cuenta la posibilidad de operaciones con y sin decimales
			private void opBinaria() {
					
					//suma
					if(signo.equals("+") && (valorA.contains(".") || valorB.contains(".")))
						tfPantalla.setText(String.valueOf(Double.parseDouble(valorA) + Double.parseDouble(valorB)));
					else if(signo.equals("+")) tfPantalla.setText(String.valueOf(Integer.parseInt(valorA) + Integer.parseInt(valorB)));
					
					//resta
					if(signo.equals("-") && (valorA.contains(".") || valorB.contains(".")))
						tfPantalla.setText(String.valueOf(Double.parseDouble(valorA) - Double.parseDouble(valorB)));
					else if(signo.equals("-"))tfPantalla.setText(String.valueOf(Integer.parseInt(valorA) - Integer.parseInt(valorB)));
					
					//producto
					if(signo.equals("*") && (valorA.contains(".") || valorB.contains(".")))
						tfPantalla.setText(String.valueOf(Double.parseDouble(valorA) * Double.parseDouble(valorB)));
					else if(signo.equals("*")) tfPantalla.setText(String.valueOf(Integer.parseInt(valorA) * Integer.parseInt(valorB)));
					
					//division
					if(signo.equals("/") && (Double.parseDouble(valorA)%Double.parseDouble(valorB) != 0))
						tfPantalla.setText(String.valueOf(Double.parseDouble(valorA) / Double.parseDouble(valorB)));
					else if(signo.equals("/")) tfPantalla.setText(String.valueOf(Integer.parseInt(valorA) / Integer.parseInt(valorB)));
				
					
			}
		};
		
		
			//listener para operaciones unarias
			ActionListener unario = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton btn = (JButton) e.getSource();
					
					//elevado al cuadrado
					if(!bloqueoPantalla) {
						//para doubles
						if(btn.getText().equals("^") && (tfPantalla.getText().contains("."))) {
							potenciaDouble = Double.parseDouble(tfPantalla.getText());
							potenciaDouble = Math.pow(potenciaDouble, 2);
							tfPantalla.setText(Double.toString(potenciaDouble));
							
						//para enteros
						} else if(btn.getText().equals("^")) {
							potenciaInteger = Integer.parseInt(tfPantalla.getText());
							potenciaInteger = (int)Math.pow(potenciaInteger, 2);
							tfPantalla.setText(Integer.toString(potenciaInteger));
						}
						
						//raiz cuadrada
						if(btn.getText().equals("R")){
							raizCuadrada = Double.parseDouble(tfPantalla.getText());
							raizCuadrada = Math.sqrt(raizCuadrada);
							if(raizCuadrada < 0) tfPantalla.setText("Err");
							if ((int)raizCuadrada%raizCuadrada==0)
								tfPantalla.setText(Integer.toString((int)raizCuadrada));
							else if(Double.toString(raizCuadrada).contains("."))
								tfPantalla.setText(Double.toString(raizCuadrada));
							
						}
						
						bloqueoPantalla = true;
					}
				}
			};
			
			//listener para limpiar la pantalla
			ActionListener limpiarPantalla = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//desbloqueamos la pantalla e inicializamos todo
					bloqueoPantalla = false;
					tfPantalla.setText("0");
					valorA = "";
					valorB = "";
					signo = "";
				}
			};

			
		
		
		//llamadas a listeners
		bt0.addActionListener(digitos);
		bt1.addActionListener(digitos);
		bt2.addActionListener(digitos);
		bt3.addActionListener(digitos);
		bt4.addActionListener(digitos);
		bt5.addActionListener(digitos);
		bt6.addActionListener(digitos);
		bt7.addActionListener(digitos);
		bt8.addActionListener(digitos);
		bt9.addActionListener(digitos);
		
		btpunto.addActionListener(decimal);
		
		btdel.addActionListener(borrarDigito);
		
		btsuma.addActionListener(operacion);
		btresta.addActionListener(operacion);
		btproducto.addActionListener(operacion);
		btdivision.addActionListener(operacion);
		
		btIgual.addActionListener(igual);
		
		btCuadrado.addActionListener(unario);
		btRaiz.addActionListener(unario);
		
		btLimpiar.addActionListener(limpiarPantalla);
		
			
	}
}
