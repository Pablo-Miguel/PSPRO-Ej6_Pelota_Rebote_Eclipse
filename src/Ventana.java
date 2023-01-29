

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//Marco con l�mina y botones------------------------------------------------------------------------------

class Ventana extends JFrame{
	
	private PanelRebote zonaRebote;
	JButton btnNuevaPelota, btnSalir, btnParar;
	JLabel lblNumPelotas;
	private Integer cont = 0;
	private ArrayList<PelotaThread> hilos;
	
	public Ventana(){
		
		setBounds(600,300,400,350);
		
		setTitle ("Juego pelotas");
		
		hilos = new ArrayList<PelotaThread>();
		
		zonaRebote=new PanelRebote(this);
		
		add(zonaRebote, BorderLayout.CENTER);
		
		JPanel zonaBotones=new JPanel();
		
		btnNuevaPelota = new JButton("Nueva pelota");
		zonaBotones.add(btnNuevaPelota);
		btnNuevaPelota.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent evento){
				cont++;
				PelotaThread hilo = new PelotaThread("Pelota" + cont, zonaRebote);
				hilos.add(hilo);
				lblNumPelotas.setText("Pelotas: " + hilos.size());
				hilo.start();
			}
			
		});
		
		btnParar = new JButton("Parar");
		zonaBotones.add(btnParar);
		btnParar.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent evento){
				
				if(btnParar.getText().equals("Parar") && hilos.size() > 0){
					
					for(int i = 0; i < hilos.size(); i++) {
						hilos.get(i).suspender();
					}
					
					btnParar.setText("Continuar");
		        } else if(btnParar.getText().equals("Continuar")) {
		        	
		        	for(int i = 0; i < hilos.size(); i++) {
						hilos.get(i).reanudar();
					}
		        	
		        	btnParar.setText("Parar");
		        }
				
			}
			
		});
		
		btnSalir = new JButton("Salir");
		zonaBotones.add(btnSalir);
		btnSalir.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent evento){
				
				System.exit(0);
				
			}
			
		});
		
		lblNumPelotas = new JLabel("Pelotas: 0");
		zonaBotones.add(lblNumPelotas);
		
		add(zonaBotones, BorderLayout.SOUTH);
	}

	public ArrayList<PelotaThread> getHilos() {
		return hilos;
	}
	
}
