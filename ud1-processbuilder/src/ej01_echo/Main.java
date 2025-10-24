package ej01_echo;

public class Main {

	public static void main(String[] args) {
		echo();
	}

	private static void echo() {
		try {
			ProcessBuilder pb = new ProcessBuilder("bash", "-lc", "echo 'Hola UD1'");
			pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);

			Process p = pb.start();
			int code = p.waitFor();

			if (code != 0) {
				System.err.println("Error en la ejecución. Return code: " + code);
			} else {
				System.out.println("Echo ejecutado con éxito. :)");
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

}
