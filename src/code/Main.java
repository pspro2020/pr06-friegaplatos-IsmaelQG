package code;

import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		Fregadero fregadero = new Fregadero();
		Thread fregadorThread = new Thread(new Fregador(fregadero), "Amigo 1");
		Thread secadorThread = new Thread(new Secador(fregadero), "Amigo 2");
		Thread organizadorThread = new Thread(new Organizador(fregadero), "Amigo 3");
		
		fregadorThread.start();
		secadorThread.start();
		organizadorThread.start();
		
		TimeUnit.SECONDS.sleep(60);
		
		fregadorThread.interrupt();
		secadorThread.interrupt();
		organizadorThread.interrupt();
		
		System.out.printf("\n%s: Estoy mol�o\n", fregadorThread.getName());
		System.out.printf("%s: Mucho plato\n", secadorThread.getName());
		System.out.printf("%s: Donde est� el cumplea�ero?\n\n", organizadorThread.getName());
		
		fregadorThread.join();
		secadorThread.join();
		organizadorThread.join();
		
		System.out.println("Feliz cumplea�os!!!!!!");
	}

}
