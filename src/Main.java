import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hospital hospital = new Hospital();

        while (sc.hasNextLine()) {
            String linea = sc.nextLine().trim();
            if (linea.isEmpty()) {
                continue;
            }
            String[] partes = linea.split(" +");
            String comando = partes[0];

            if (comando.equals("salir")) {
                break;
            }

            String respuesta;
            try {
                if (comando.equals("ingresar") && partes.length >= 2) {
                    respuesta = procesarIngreso(hospital, partes);
                } else if (comando.equals("vacunar") && partes.length == 2) {
                    respuesta = hospital.vacunar(partes[1]);
                } else if (comando.equals("asignar") && partes.length == 2) {
                    respuesta = hospital.asignar(partes[1]);
                } else if (comando.equals("medicar") && partes.length == 3) {
                    double mg = Double.parseDouble(partes[2]);
                    respuesta = hospital.medicar(partes[1], mg);
                } else if (comando.equals("alta") && partes.length == 2) {
                    respuesta = hospital.alta(partes[1]);
                } else if (comando.equals("reporte") && partes.length == 1) {
                    respuesta = hospital.reporte();
                } else if (comando.equals("ingresar") || comando.equals("vacunar") || comando.equals("asignar")
                        || comando.equals("medicar") || comando.equals("alta") || comando.equals("reporte")) {
                    respuesta = "ERROR: datos invalidos";
                } else {
                    respuesta = "ERROR: comando desconocido";
                }
            } catch (NumberFormatException e) {
                respuesta = "ERROR: datos invalidos";
            }
            System.out.println(respuesta);
        }
        sc.close();
    }

    private static String procesarIngreso(Hospital hospital, String[] partes) {
        String tipo = partes[1];
        if (tipo.equals("perro")) {
            if (partes.length != 7) {
                return "ERROR: datos invalidos";
            }
            String vacuna = partes[6];
            if (!vacuna.equals("si") && !vacuna.equals("no")) {
                return "ERROR: datos invalidos";
            }
            return hospital.ingresarPerro(partes[2], Double.parseDouble(partes[3]),
                    Integer.parseInt(partes[4]), partes[5], vacuna.equals("si"));
        } else if (tipo.equals("gato")) {
            if (partes.length != 5) {
                return "ERROR: datos invalidos";
            }
            return hospital.ingresarGato(partes[2], Double.parseDouble(partes[3]),
                    Integer.parseInt(partes[4]));
        } else if (tipo.equals("ave")) {
            if (partes.length != 6) {
                return "ERROR: datos invalidos";
            }
            return hospital.ingresarAve(partes[2], Double.parseDouble(partes[3]),
                    Integer.parseInt(partes[4]), Double.parseDouble(partes[5]));
        }
        return "ERROR: comando desconocido";
    }
}