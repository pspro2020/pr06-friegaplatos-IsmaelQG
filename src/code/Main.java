package code;

import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		Amigos amigos = new Amigos();
		Thread fregadorThread = new Thread(new Fregador(amigos), "Amigo 1");
		Thread secadorThread = new Thread(new Secador(amigos), "Amigo 2");
		Thread organizadorThread = new Thread(new Organizador(amigos), "Amigo 3");
		
		fregadorThread.start();
		secadorThread.start();
		organizadorThread.start();
		
		TimeUnit.SECONDS.sleep(60);
		
		fregadorThread.interrupt();
		secadorThread.interrupt();
		organizadorThread.interrupt();
		
		fregadorThread.join();
		secadorThread.join();
		organizadorThread.join();
		
		System.out.println("Feliz cumpleaños");
	}

}
