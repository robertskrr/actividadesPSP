package ej12_ip;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;

public class Main {

	public static void main(String[] args) {
		ip();
	}

	private static void ip() {
		try {
			Instant inicio = Instant.now();
			// IP Pública
			ProcessBuilder pbPublica = new ProcessBuilder("curl", "-s", "https://api.ipify.org/?format=json");
			ProcessBuilder pbPrivada = new ProcessBuilder("hostname", "-I");

			Process p = pbPublica.start();
			int exitCode = p.waitFor();
			Duration duracion = Duration.between(inicio, Instant.now());

			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

			System.out.println("🛰️ Consultando IP pública...");
			System.out.println("PID del proceso: " + p.pid());
			System.out.print("🌐 Respuesta API: ");
			String linea;
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
			System.out.println("Código de salida: " + exitCode);
			System.out.println("Duración: " + duracion.toMillis() + "ms");

			if (exitCode == 0) {
				System.out.println("✅ Proceso finalizado correctamente.");
			}

			// IP Privada
			inicio = Instant.now();
			p = pbPrivada.start();
			exitCode = p.waitFor();
			duracion = Duration.between(inicio, Instant.now());
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));

			System.out.println("💻 Consultando IP privada...");
			System.out.println("PID del proceso: " + p.pid());
			System.out.print("🌐 Respuesta API: ");
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
			System.out.println("Código de salida: " + exitCode);
			System.out.println("Duración: " + duracion.toMillis() + "ms");

			if (exitCode == 0) {
				System.out.println("✅ Proceso finalizado correctamente.");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

}
