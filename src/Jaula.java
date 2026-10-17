public class Jaula {

    private int numero;
    private String tamano;
    private String zona;
    private Paciente ocupante;

    public Jaula(int numero, String tamano, String zona) {
        setNumero(numero);
        setTamano(tamano);
        setZona(zona);
        setOcupante(null);
    }

    public boolean estaLibre() {
        return ocupante == null;
    }

    public int getNumero() {
        return numero;
    }

    public String getTamano() {
        return tamano;
    }

    public String getZona() {
        return zona;
    }

    public Paciente getOcupante() {
        return ocupante;
    }

    public void setNumero(int numero) {
        if (numero > 0) {
            this.numero = numero;
        }
    }

    public void setTamano(String tamano) {
        if (tamano.equals("S") || tamano.equals("M") || tamano.equals("L")) {
            this.tamano = tamano;
        }
    }

    public void setZona(String zona) {
        if (zona.equals("A") || zona.equals("B")) {
            this.zona = zona;
        }
    }

    public void setOcupante(Paciente ocupante) {
        this.ocupante = ocupante;
    }
}