import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.http.WebSocket.Listener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionAdapter;

public class PreExamen  {

	private JFrame frame;
	private JLabel pos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PreExamen window = new PreExamen();
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
	public PreExamen() {
		initialize();
		addKeyListener(this);
	}

	private void addKeyListener(PreExamen preExamen) {
		// TODO Esbozo de método generado automáticamente
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				pos.setText(String.valueOf(e.getKeyChar()));
			}
		});
		
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 584, 61);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		pos = new JLabel("");
		
		pos.setBounds(155, 5, 169, 28);
		panel.add(pos);
		
		JPanel panel_1 = new JPanel() {
			
			public void paint(Graphics g ) {
				super.paint(g);
				g.setColor(Color.blue);
				g.drawOval(50, 50, 10, 10);
				
			}
		};
		panel_1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
			}
		});
		
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pos.setText(String.valueOf(e.getPoint()));
			}
		});
		panel_1.setBounds(10, 59, 584, 302);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
	}
	
}
