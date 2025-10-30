package ej09_redirect;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		redirect();
	}

	private static void redirect() {
		try {
			ProcessBuilder pb = new ProcessBuilder("bash", "-lc", "dmesg");
			pb.redirectOutput(new File("salida.txt"));
			pb.redirectError(new File("errores.txt"));
			
			Process p = pb.start();
			p.waitFor();
			System.out.println("Finalizado");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

}