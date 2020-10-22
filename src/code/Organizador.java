package code;

public class Organizador implements Runnable{

private final Amigos fregadero;
	
	public Organizador(Amigos fregadero) {
		this.fregadero = fregadero;
	}
	
	@Override
	public void run() {
		
		while(!Thread.currentThread().isInterrupted()) {
			try{
				fregadero.guardarPlato();
			}
			catch(InterruptedException e){
				return;
			}
		}
		
	}
	

}
