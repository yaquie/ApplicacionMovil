package express.deli.cosema.deliexpressapp.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListaSeccionResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("listaSeccion")
    @Expose
    private List<ListaSeccionResponse.ListaSeccionRestResponse> listaSeccion;

    public ListaSeccionResponse() {
    }

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

    public List<ListaSeccionRestResponse> getListaSeccion() {
        return listaSeccion;
    }

    public void setListaSeccion(List<ListaSeccionRestResponse> listaSeccion) {
        this.listaSeccion = listaSeccion;
    }

    public class ListaSeccionRestResponse {
        private String codigoSeccion;
        private String nombreSeccion;
        private String descripcionSeccion;

        public ListaSeccionRestResponse() {
        }

        public String getCodigoSeccion() {
            return codigoSeccion;
        }

        public void setCodigoSeccion(String codigoSeccion) {
            this.codigoSeccion = codigoSeccion;
        }

        public String getNombreSeccion() {
            return nombreSeccion;
        }

        public void setNombreSeccion(String nombreSeccion) {
            this.nombreSeccion = nombreSeccion;
        }

        public String getDescripcionSeccion() {
            return descripcionSeccion;
        }

        public void setDescripcionSeccion(String descripcionSeccion) {
            this.descripcionSeccion = descripcionSeccion;
        }
    }
}
