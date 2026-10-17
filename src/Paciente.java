public abstract class Paciente {

    private String nombre;
    private double peso;
    private int edad = -1;
    private String estado;
    private double factorDosis;
    private double dosisAcumulada;
    private Jaula jaula;

    public Paciente(String nombre, double peso, int edad, double factorDosis) {
        setNombre(nombre);
        setPeso(peso);
        setEdad(edad);
        setFactorDosis(factorDosis);
        setEstado("INGRESADO");
        setDosisAcumulada(0);
        setJaula(null);
    }

    // Solo estos 3 son abstractos: cada animal responde distinto
    public abstract String getTipo();

    public abstract double getPesoMaximo();

    public abstract String getZonaPermitida();

    // Getters normales: iguales para todos los animales
    public String getNombre() {
        return nombre;
    }

    public double getPeso() {
        return peso;
    }

    public String getEstado() {
        return estado;
    }

    public Jaula getJaula() {
        return jaula;
    }

    public double getDosisAcumulada() {
        return dosisAcumulada;
    }

    // Dosis maxima por aplicacion (R7)
    public double getDosisMaxima() {
        return peso * factorDosis;
    }

    // Revisa si al sumar mg se supera el tope acumulado (R7)
    public boolean superaTopeAcumulado(double mg) {
        return dosisAcumulada + mg > 3 * getDosisMaxima();
    }

    public boolean tieneEstresAlto () {
        return false;
    }
    public void aplicarDosis(double mg) {
        setDosisAcumulada(dosisAcumulada + mg);
        setEstado("EN_TRATAMIENTO");
    }
    public boolean datosValidos() {
        return nombre != null && peso > 0 && edad >= 0;
    }

    public String getTamanoRequerido() {
        if (peso < 5) {
            return "S";
        } else if (peso <= 25) {
            return "M";
        } else {
            return "L";
        }
    }



public void setNombre(String nombre) {
    if (nombre != null && !nombre.trim().isEmpty()) {
        this.nombre = nombre;
    }
}

public void setPeso(double peso) {
    if (peso > 0 && peso <= getPesoMaximo()) {
        this.peso = peso;
    }
}

public void setEdad(int edad) {
    if (edad >= 0 && edad <= 30) {
        this.edad = edad;
    }
}

public void setFactorDosis(double factorDosis) {
    if (factorDosis > 0) {
        this.factorDosis = factorDosis;
    }
}

public void setEstado(String estado) {
    if (estado.equals("INGRESADO") || estado.equals("CUARENTENA") || estado.equals("EN_TRATAMIENTO")) {
        this.estado = estado;
    }
}

public void setDosisAcumulada(double dosisAcumulada) {
    if (dosisAcumulada >= 0) {
        this.dosisAcumulada = dosisAcumulada;
    }
}

public void setJaula(Jaula jaula) {
    this.jaula = jaula;
}



}
