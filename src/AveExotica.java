public class AveExotica extends Paciente {

    private double envergadura;

    public AveExotica(String nombre, double peso, int edad, double envergadura) {
        super(nombre, peso, edad, 3);
        setEnvergadura(envergadura);
    }


    @Override
    public String getTipo() {
        return "Ave";
    }

    @Override
    public double getPesoMaximo() {
        return 15;
    }

    @Override
    public String getZonaPermitida() {
        return "B";
    }



    @Override
    public boolean datosValidos() {
        return super.datosValidos() && envergadura > 0;
    }

    // R4: ave con envergadura mayor a 60 cm necesita como minimo M
    @Override
    public String getTamanoRequerido() {
        String tamano = super.getTamanoRequerido();
        if (envergadura > 60 && tamano.equals("S")) {
            tamano = "M";
        }
        return tamano;
    }

    public double getEnvergadura() {
        return envergadura;
    }

    public void setEnvergadura(double envergadura) {
        if (envergadura > 0) {
            this.envergadura = envergadura;
        }
    }
}