

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;
//L�mina que dibuja las pelotas----------------------------------------------------------------------

class PanelRebote extends JPanel{
	
	private Ventana ventana;
	private ArrayList<Pelota> pelotas=new ArrayList<Pelota>();
	
	public PanelRebote(Ventana ventana) {
		this.ventana = ventana;
	}
	
	public void add(Pelota b){
		pelotas.add(b);
	}
	
	public void remove(Pelota pelota) {
		pelotas.remove(pelota);
	}
	
	public void removeThread(PelotaThread hilo) {
		ventana.getHilos().remove(hilo);
		ventana.lblNumPelotas.setText("Pelotas: " + pelotas.size());
	}

	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		Graphics2D g2=(Graphics2D)g;
		
		for(int i = 0; i < pelotas.size(); i++){
			
			g2.fill(pelotas.get(i).getForma());
			
		}
		
	}
	
	
}
