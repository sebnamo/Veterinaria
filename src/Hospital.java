public class Hospital {

    private Paciente[] pacientes;
    private int contador;
    private Jaula[] jaulas;

    public Hospital() {
        pacientes = new Paciente[10];
        contador = 0;
        jaulas = new Jaula[6];
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        jaulas[0] = new Jaula(1, "S", "A");
        jaulas[1] = new Jaula(2, "M", "A");
        jaulas[2] = new Jaula(3, "M", "A");
        jaulas[3] = new Jaula(4, "L", "A");
        jaulas[4] = new Jaula(5, "S", "B");
        jaulas[5] = new Jaula(6, "M", "B");

        ingresarPerro("Rocky", 30.0, 4, "labrador", true);
        ingresarGato("Misifu", 4.0, 3);
        ingresarAve("Kiwi", 0.8, 2, 70);
    }


    private int buscarIndice(String nombre) {
        for (int i = 0; i < contador; i++) {
            if (pacientes[i].getNombre().equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }

    // ---------- Ingresar ----------
    public String ingresarPerro(String nombre, double peso, int edad, String raza, boolean vacunado) {
        Perro perro = new Perro(nombre, peso, edad, raza, vacunado);
        return agregarPaciente(perro);
    }

    public String ingresarGato(String nombre, double peso, int edad) {
        Gato gato = new Gato(nombre, peso, edad, 0);
        return agregarPaciente(gato);
    }

    public String ingresarAve(String nombre, double peso, int edad, double envergadura) {
        AveExotica ave = new AveExotica(nombre, peso, edad, envergadura);
        return agregarPaciente(ave);
    }


    private String agregarPaciente(Paciente p) {
        if (!p.datosValidos()) {
            return "ERROR: datos invalidos";
        }
        if (buscarIndice(p.getNombre()) != -1) {
            return "ERROR: nombre duplicado";
        }
        if (contador == pacientes.length) {
            return "ERROR: hospital lleno";
        }
        pacientes[contador] = p;
        contador++;
        if (p.getEstado().equals("CUARENTENA")) {
            return "OK: " + p.getNombre() + " ingresado en CUARENTENA";
        }
        return "OK: " + p.getNombre() + " ingresado";
    }


    public String vacunar(String nombre) {
        int i = buscarIndice(nombre);
        if (i == -1) {
            return "ERROR: paciente no encontrado";
        }
        if (!(pacientes[i] instanceof Perro)) {
            return "ERROR: solo aplica a perros";
        }
        Perro perro = (Perro) pacientes[i];
        if (perro.isVacunado()) {
            return "ERROR: ya vacunado";
        }
        perro.setVacunado(true);
        perro.setEstado("INGRESADO");
        return "OK: " + perro.getNombre() + " vacunado";
    }


    public String asignar(String nombre) {
        int i = buscarIndice(nombre);
        if (i == -1) {
            return "ERROR: paciente no encontrado";
        }
        Paciente p = pacientes[i];
        if (p.getEstado().equals("CUARENTENA")) {
            return "ERROR: paciente en cuarentena";
        }
        if (p.getJaula() != null) {
            return "ERROR: paciente ya tiene jaula";
        }
        for (int j = 0; j < jaulas.length; j++) {
            Jaula jaula = jaulas[j];
            if (jaula.estaLibre()
                    && jaula.getTamano().equals(p.getTamanoRequerido())
                    && jaula.getZona().equals(p.getZonaPermitida())) {
                jaula.setOcupante(p);
                p.setJaula(jaula);
                return "OK: " + p.getNombre() + " asignado a jaula " + jaula.getNumero();
            }
        }
        return "ERROR: no hay jaula compatible";
    }


    public String medicar(String nombre, double mg) {
        int i = buscarIndice(nombre);
        if (i == -1) {
            return "ERROR: paciente no encontrado";
        }
        Paciente p = pacientes[i];
        if (mg <= 0) {
            return "ERROR: datos invalidos";
        }
        if (p.getEstado().equals("CUARENTENA")) {
            return "ERROR: paciente en cuarentena";
        }
        if (p.getJaula() == null) {
            return "ERROR: paciente sin jaula";
        }
        if (p.tieneEstresAlto()) {
            return "ERROR: gato con estres alto";
        }
        if (mg > p.getDosisMaxima()) {
            return "ERROR: dosis excede el maximo";
        }
        if (p.superaTopeAcumulado(mg)) {
            return "ERROR: tope acumulado superado";
        }
        p.aplicarDosis(mg);
        return "OK: " + p.getNombre() + " medicado con " + mg + " mg";
    }


    public String alta(String nombre) {
        int i = buscarIndice(nombre);
        if (i == -1) {
            return "ERROR: paciente no encontrado";
        }
        Paciente p = pacientes[i];
        if (!p.getEstado().equals("EN_TRATAMIENTO")) {
            return "ERROR: paciente no apto para alta";
        }
        // Liberar la jaula
        if (p.getJaula() != null) {
            p.getJaula().setOcupante(null);
            p.setJaula(null);
        }
        // Compactar el vector
        for (int k = i; k < contador - 1; k++) {
            pacientes[k] = pacientes[k + 1];
        }
        pacientes[contador - 1] = null;
        contador--;
        return "OK: " + p.getNombre() + " dado de alta";
    }


    public String reporte() {
        String texto = "--- REPORTE ---\n";
        texto = texto + "Pacientes: " + contador + "/" + pacientes.length + "\n";
        for (int i = 0; i < contador; i++) {
            Paciente p = pacientes[i];
            double pesoRedondeado = Math.round(p.getPeso() * 10) / 10.0;
            String numeroJaula = "-";
            if (p.getJaula() != null) {
                numeroJaula = "" + p.getJaula().getNumero();
            }
            texto = texto + p.getNombre() + " | " + p.getTipo() + " | " + pesoRedondeado + " kg | "
                    + p.getEstado() + " | jaula: " + numeroJaula + "\n";
        }
        texto = texto + "Jaulas libres: " + contarJaulasLibres();
        return texto;
    }

    private int contarJaulasLibres() {
        int libres = 0;
        for (int j = 0; j < jaulas.length; j++) {
            if (jaulas[j].estaLibre()) {
                libres++;
            }
        }
        return libres;
    }
}