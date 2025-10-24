package ej06_environment;

public class Main {

	public static void main(String[] args) {
		variableEntorno();
	}

	private static void variableEntorno() {
		try {
			ProcessBuilder pb = new ProcessBuilder("printenv", "MI_VAR").inheritIO();
			pb.environment().put("MI_VAR", "UD1");

			Process p = pb.start();

			int code = p.waitFor();

			if (code == 0) {
				System.out.println("Todo fue bien :)");
			} else {
				System.err.println("Error al ejecutar :(");
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
