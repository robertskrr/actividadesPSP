package ej02_ls_salida;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		capturaLs();
	}

	private static void capturaLs() {
		try {
			ProcessBuilder pb = new ProcessBuilder("ls", "-l");
			Process p = pb.start();

			// FLUJO DE ENTRADA DE BYTE --> 0, 1
			InputStream inputStream = p.getInputStream();
			InputStreamReader lectorDeFlujo = new InputStreamReader(inputStream);
			BufferedReader br = new BufferedReader(lectorDeFlujo);

			String linea;

			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
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
