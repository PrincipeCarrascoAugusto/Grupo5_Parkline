package testing.modelo;

import java.time.LocalTime;

public class reserva {
    private String nom_espacio;
    private String tiempo;
    private String placa;
    public reserva(){}

    public String getNom_espacio() {
        return nom_espacio;
    }

    public void setNom_espacio(String nom_espacio) {
        this.nom_espacio = nom_espacio;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    
    
}
