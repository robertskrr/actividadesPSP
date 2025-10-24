package ej02_ls_salida;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		capturaLs();
	}

	private static void capturaLs() {
		try {
			ProcessBuilder pb = new ProcessBuilder("ls","-l");

			Process p = pb.start();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String linea;
			
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
			
			br.close();
			p.waitFor();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
