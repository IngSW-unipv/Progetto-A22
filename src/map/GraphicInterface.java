package map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import map.Hexagons;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GraphicInterface {
	
	//inizializzo le variabili con valori standard
	private JFrame frame;
	private int width = 50;
	private int height = 50;
	private int radius = 20;

	public GraphicInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 568, 523);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 532, 421);
		frame.getContentPane().add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);

		//necessario per disegnare gli esagoni
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel.setPreferredSize(new Dimension(4000,4000));
		
		
		//zoomout
		JButton zoomOut = new JButton("-");
		zoomOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radius > 10) {
					radius -= 10;
					redraw(panel, width, height, radius);
				}

			}
		});
		zoomOut.setBounds(129, 11, 89, 23);
		frame.getContentPane().add(zoomOut);
		
		//zommIn
		JButton zoomIn = new JButton("+");
		zoomIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				radius += 10;
				redraw(panel, width, height, radius);
				
			}
		});
		zoomIn.setBounds(346, 11, 89, 23);
		frame.getContentPane().add(zoomIn);

		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		
		redraw(panel, width, height, radius);
	}
	
	/*
	 * 
	 * Width e Height sono il numero degli esagono per riga e colonna
	 * il radius è il raggio dell'esagono
	 * vengono passati sempre perchè il pannello deve essere ridisegnato in base alla quantità di esagoni e la loro grandezza
	 * 
	 */
	
	public void redraw(JPanel panel, int width, int height, int radius) {
		Hexagons hexagons = new Hexagons();
		hexagons.draw(panel, width, height, radius);
		
	}
}
