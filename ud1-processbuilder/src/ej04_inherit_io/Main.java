package ej04_inherit_io;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		inheritIO();
	}

	private static void inheritIO() {
		try {
			int code = new ProcessBuilder("top", "-b", "-n1").inheritIO().start().waitFor();

			if (code == 0) {
				System.out.println("Todo fue bien :)");
			} else {
				System.err.println("Error al ejecutar :(");
			}
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}
