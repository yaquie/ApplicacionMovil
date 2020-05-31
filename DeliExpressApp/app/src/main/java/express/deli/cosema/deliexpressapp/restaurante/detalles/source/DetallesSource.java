package express.deli.cosema.deliexpressapp.restaurante.detalles.source;

public interface DetallesSource {

    interface CallBackResultado {
        void onCallBackResultado(String horarioGeneral,
                                 String lunesResp,
                                 String martesResp,
                                 String miercolesResp,
                                 String juevesResp,
                                 String viernesResp,
                                 String sabadoResp,
                                 String domingoResp);
    }

    void onMostrarHorario(String codigoRestaurante,DetallesSource.CallBackResultado callBackResultado);
}
