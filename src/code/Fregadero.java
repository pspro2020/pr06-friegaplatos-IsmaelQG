package code;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Fregadero {
	
	ArrayList<Plato> platosLimpios = new ArrayList<>();
	ArrayList<Plato> platosSecos = new ArrayList<>();
	ArrayList<Plato> alacena = new ArrayList<>();
	
	DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	public void añadirPlatoLimpio(Plato plato) throws InterruptedException {
		synchronized(this) {
			platosLimpios.add(plato);
			System.out.printf("%s Lavando plato nº %d\n",LocalTime.now().format(format), plato.getId());
			notifyAll();
		}
		
	}
	
	public void añadirPlatoSeco() throws InterruptedException {
		synchronized(this) {
			while(platosLimpios.isEmpty()) {
				System.out.printf("%s Esperando platos empapados\n", LocalTime.now().format(format));
				wait();
			}
			platosSecos.add(platosLimpios.get(0));
			System.out.printf("%s Secando plato nº %d\n",LocalTime.now().format(format), platosSecos.get(0).getId());
			platosLimpios.remove(0);
			TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1,4));
			notifyAll();
			
		}
	}
	
	public void guardarPlato() throws InterruptedException {
		synchronized(this) {
			while(platosSecos.isEmpty()) {
				System.out.printf("%s Esperando platos secos\n", LocalTime.now().format(format));
				wait();
			}
			alacena.add(platosSecos.get(0));
			System.out.printf("%s Guardando plato nº %d\n",LocalTime.now().format(format), platosSecos.get(0).getId());
			platosSecos.remove(0);
			TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1,3));
			notifyAll();
		}
	}

}
