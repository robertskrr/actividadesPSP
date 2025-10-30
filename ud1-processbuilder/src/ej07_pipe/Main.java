package ej07_pipe;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Main {

	public static void main(String[] args) {
		pipe();
	}

	private static void pipe() {
		// Método IA
		try {
			Process p1 = new ProcessBuilder("echo", "'hola mundo'").start();
			Process p2 = new ProcessBuilder("wc", "-w").start();

			InputStream inputP1 = p1.getInputStream();
			OutputStream outputP2 = p2.getOutputStream();

			inputP1.transferTo(outputP2);

			outputP2.close();

			InputStreamReader lectorDeFlujo = new InputStreamReader(p2.getInputStream());

			BufferedReader br = new BufferedReader(lectorDeFlujo);
			String linea;
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}

			p1.waitFor();
			p2.waitFor();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		// Visto en clase
		try {
			File tmp = File.createTempFile("pipe", ".txt");
			ProcessBuilder pb1 = new ProcessBuilder("bash", "-lc", "echo 'hola mundo'");
			pb1.redirectOutput(tmp);
			Process proceso1 = pb1.start();
			int codeP1 = proceso1.waitFor();

			ProcessBuilder pb2 = new ProcessBuilder("bash", "-lc", "wc -w");
			pb2.redirectInput(tmp);
			Process proceso2 = pb2.start();

			String salida = new String(proceso2.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
			proceso2.waitFor();

			System.out.println(salida);

			Files.deleteIfExists(tmp.toPath());

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
