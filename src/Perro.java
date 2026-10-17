    public class Perro extends Paciente {

        private String raza;
        private boolean vacunado;

        public Perro(String nombre, double peso, int edad, String raza, boolean vacunado) {
            super(nombre, peso, edad, 10);
            setRaza(raza);
            setVacunado(vacunado);

            if (!vacunado) {
                setEstado("CUARENTENA");
            }
        }


        @Override
        public String getTipo() {
            return "Perro";
        }

        @Override
        public double getPesoMaximo() {
            return 80;
        }

        @Override
        public String getZonaPermitida() {
            return "A";
        }


        public String getRaza() {
            return raza;
        }

        public boolean isVacunado() {
            return vacunado;
        }

        public void setRaza(String raza) {
            if (raza != null && !raza.trim().isEmpty()) {
                this.raza = raza;
            }
        }

        public void setVacunado(boolean vacunado) {
            this.vacunado = vacunado;
        }
    }

