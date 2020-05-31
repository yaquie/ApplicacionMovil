package express.deli.cosema.deliexpressapp.restaurante.entidad;

import org.parceler.Parcel;

@Parcel
public class RestauranteUi {
     String idRestaurante;
     String nombreRestaurante;
     String direccionRestaurante;
     String puntosRestaurante;
     String platoPrecioPersona;
     String imageRestaurante;
     SeccionUi seccionUi;

    public RestauranteUi() {
    }

    public String getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(String idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public String getNombreRestaurante() {
        return nombreRestaurante;
    }

    public void setNombreRestaurante(String nombreRestaurante) {
        this.nombreRestaurante = nombreRestaurante;
    }

    public String getDireccionRestaurante() {
        return direccionRestaurante;
    }

    public void setDireccionRestaurante(String direccionRestaurante) {
        this.direccionRestaurante = direccionRestaurante;
    }

    public String getPuntosRestaurante() {
        return puntosRestaurante;
    }

    public void setPuntosRestaurante(String puntosRestaurante) {
        this.puntosRestaurante = puntosRestaurante;
    }

    public String getPlatoPrecioPersona() {
        return platoPrecioPersona;
    }

    public void setPlatoPrecioPersona(String platoPrecioPersona) {
        this.platoPrecioPersona = platoPrecioPersona;
    }

    public String getImageRestaurante() {
        return imageRestaurante;
    }

    public void setImageRestaurante(String imageRestaurante) {
        this.imageRestaurante = imageRestaurante;
    }

    public SeccionUi getSeccionUi() {
        return seccionUi;
    }

    public void setSeccionUi(SeccionUi seccionUi) {
        this.seccionUi = seccionUi;
    }
}
