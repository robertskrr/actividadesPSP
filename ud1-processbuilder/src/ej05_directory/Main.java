package ej05_directory;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		directoryPwd();
	}

	private static void directoryPwd() {
		try {
			Process p = new ProcessBuilder("pwd").directory(new File("/tmp")).start();

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
