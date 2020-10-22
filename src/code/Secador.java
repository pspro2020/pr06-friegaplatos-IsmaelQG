package code;

public class Secador implements Runnable{

private final Amigos fregadero;
	
	public Secador(Amigos fregadero) {
		this.fregadero = fregadero;
	}
	
	@Override
	public void run() {

		while(!Thread.currentThread().isInterrupted()) {
			try{
				fregadero.añadirPlatoSeco();
			}
			catch(InterruptedException e){
				return;
			}
		}
		
	}

}
