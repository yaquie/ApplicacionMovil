package express.deli.cosema.deliexpressapp.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListaRestauranteResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("listaRestaurante")
    @Expose
    private List<ListaRestauranteRestResponse> listaRestauranteRestResponseList;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ListaRestauranteRestResponse> getListaRestauranteRestResponseList() {
        return listaRestauranteRestResponseList;
    }

    public void setListaRestauranteRestResponseList(List<ListaRestauranteRestResponse> listaRestauranteRestResponseList) {
        this.listaRestauranteRestResponseList = listaRestauranteRestResponseList;
    }

    public class ListaRestauranteRestResponse {
        private String codigoRestaurante;
        private String nombreRestaurante;
        private String descripcionRestaurante;
        private String direccionRestaurante;
        private String rankingRestaurante;
        private String codigoDepartamento;
        private String codigoProvincia;
        private String codigoDistrito;
        private String codigoPromocion;
        private String nombreSeccion;
        private String fotoRestaurante;
        private String puntosRestaurante;
        private String platPersona;

        public ListaRestauranteRestResponse() {
        }

        public String getCodigoRestaurante() {
            return codigoRestaurante;
        }

        public String getNombreRestaurante() {
            return nombreRestaurante;
        }

        public String getDescripcionRestaurante() {
            return descripcionRestaurante;
        }

        public String getDireccionRestaurante() {
            return direccionRestaurante;
        }

        public String getRankingRestaurante() {
            return rankingRestaurante;
        }

        public String getCodigoDepartamento() {
            return codigoDepartamento;
        }

        public String getCodigoProvincia() {
            return codigoProvincia;
        }

        public String getCodigoDistrito() {
            return codigoDistrito;
        }

        public String getCodigoPromocion() {
            return codigoPromocion;
        }

        public String getNombreSeccion() {
            return nombreSeccion;
        }

        public String getFotoRestaurante() {
            return fotoRestaurante;
        }

        public String getPuntosRestaurante() {
            return puntosRestaurante;
        }

        public String getPlatPersona() {
            return platPersona;
        }
    }
}
