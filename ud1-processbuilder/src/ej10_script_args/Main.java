package ej10_script_args;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		ejecutaSh();
	}

	private static void ejecutaSh() {
		try {
			ProcessBuilder pb = new ProcessBuilder("bash", "-lc", "./hola.sh Ana")
					.directory(new File("/home/usuario/scripts")).inheritIO();
			// Creando variable de entorno
			pb.environment().put("PRUEBA", "🍕");
			
			Process p = pb.start();

			int code = p.waitFor();

			if (code == 0) {
				System.out.println("Todo ha ido bien :)");
			} else {
				System.err.println("Algo ha fallado");
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
