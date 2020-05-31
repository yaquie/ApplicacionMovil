package express.deli.cosema.deliexpressapp.restaurante.entidad;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class SeccionUi {
     String idSeccion;
     String nombreSeccion;
     List<RestauranteUi> restauranteUi;

    public SeccionUi() {
    }

    public String getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(String idSeccion) {
        this.idSeccion = idSeccion;
    }

    public String getNombreSeccion() {
        return nombreSeccion;
    }

    public void setNombreSeccion(String nombreSeccion) {
        this.nombreSeccion = nombreSeccion;
    }

    public List<RestauranteUi> getRestauranteUi() {
        return restauranteUi;
    }

    public void setRestauranteUi(List<RestauranteUi> restauranteUi) {
        this.restauranteUi = restauranteUi;
    }
}
