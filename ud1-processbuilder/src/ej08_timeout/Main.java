package ej08_timeout;

import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		sleep();
	}

	private static void sleep() {
		try {
			ProcessBuilder pb = new ProcessBuilder("bash", "-lc", "sleep 10");
			Process p = pb.start();
			
			boolean terminado = p.waitFor(2, TimeUnit.SECONDS);
			if (!terminado) {
				p.destroy();
				System.out.println("Ha tardado más de la cuenta...");
			} else {
				System.out.println("Finalizado correctamente");
			}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

}
