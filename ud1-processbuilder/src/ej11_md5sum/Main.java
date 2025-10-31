package ej11_md5sum;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Main {

	public static void main(String[] args) {
		md5txt();
	}

	private static void md5txt() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				System.out.print("md5txt> ");
				String linea = br.readLine();
				// Fin: línea vacía o comando de salida
				if (linea.isBlank()) {
					break;
				}

				File tmp = File.createTempFile("pipe", ".txt");
				Files.writeString(tmp.toPath(), linea, StandardCharsets.UTF_8);

				ProcessBuilder pb = new ProcessBuilder("md5sum");
				pb.redirectInput(tmp);
				pb.redirectOutput(ProcessBuilder.Redirect.PIPE);
				pb.redirectError(ProcessBuilder.Redirect.PIPE);

				Process proceso = pb.start();
				proceso.waitFor();

				String salida = new String(proceso.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
				System.out.println(salida);
				
				tmp.delete();

			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		System.out.println("FIN");
	}

}
