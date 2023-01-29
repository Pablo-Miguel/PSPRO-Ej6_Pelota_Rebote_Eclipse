
public class PelotaThread extends Thread {

	private static final int NUM_MOVIMIENTOS = 1040;
    public PanelRebote zonaRebote;
    public Integer cont;
    private boolean stop;
    
    public PelotaThread(String name, PanelRebote zonaRebote) {
        super(name);
        this.zonaRebote = zonaRebote;
        cont = 0;
        stop = false;
    }
    
    public boolean isStop() {
        return stop;
    }
    
    public void suspender(){
        this.stop = true;
    }
    
    public synchronized void reanudar() {
        this.stop = false;
        notify();
    }

    @Override
    public void run() {
    	lanzarJuego();
    }
    
    public synchronized void lanzarJuego() {
    	Pelota pelota = new Pelota(cont++, zonaRebote);//pasarle zonarebote y que se pinte a s� misma

        zonaRebote.add(pelota);

        for (int i = 1; i < NUM_MOVIMIENTOS; i++) {
        	
        	if(stop){
                try {
                    wait();
                } catch (InterruptedException ex) { }
            }
        	
            pelota.paint();

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) { }

        }
        
        zonaRebote.remove(pelota);
        zonaRebote.updateUI();
        zonaRebote.removeThread(this);
    }
}
