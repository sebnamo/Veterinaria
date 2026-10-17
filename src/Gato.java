public class Gato extends Paciente {

    private int nivelEstres;

    public Gato(String nombre, double peso, int edad, int nivelEstres) {
        super(nombre, peso, edad, 6);
        setNivelEstres(nivelEstres);
    }


    @Override
    public String getTipo() {
        return "Gato";
    }

    @Override
    public double getPesoMaximo() {
        return 15;
    }

    @Override
    public String getZonaPermitida() {
        return "A";
    }


    // R9: con estres de 8 o mas no se puede medicar
    @Override
    public boolean tieneEstresAlto() {
        return nivelEstres >= 8;
    }

    // R9: despues de medicar, el estres sube 2 con tope 10
    @Override
    public void aplicarDosis(double mg) {
        super.aplicarDosis(mg);
        int nuevo = nivelEstres + 2;
        if (nuevo > 10) {
            nuevo = 10;
        }
        setNivelEstres(nuevo);
    }

    public int getNivelEstres() {
        return nivelEstres;
    }

    public void setNivelEstres(int nivelEstres) {
        if (nivelEstres >= 0 && nivelEstres <= 10) {
            this.nivelEstres = nivelEstres;
        }
    }
}
