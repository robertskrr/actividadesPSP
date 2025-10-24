package ej03_ls_error;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		capturaError();
	}

	private static void capturaError() {
		try {
			ProcessBuilder pb = new ProcessBuilder("ls", "/ruta/que/no/existe");
			Process p = pb.start();

			// FLUJO DE ENTRADA DE BYTE --> 0, 1
			InputStream errorStream = p.getErrorStream();
			InputStreamReader lectorDeFlujo = new InputStreamReader(errorStream);
			BufferedReader br = new BufferedReader(lectorDeFlujo);

			String linea;

			while ((linea = br.readLine()) != null) {
				System.err.println(linea);
			}

			int code = p.waitFor();

			if (code == 0) {
				System.out.println("Todo fue perfecto :)");
			} else {
				System.err.println("Error al ejecutar. :(");
			}

			br.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
