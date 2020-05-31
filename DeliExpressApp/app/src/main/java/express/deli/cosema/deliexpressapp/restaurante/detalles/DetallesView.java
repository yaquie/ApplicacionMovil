package express.deli.cosema.deliexpressapp.restaurante.detalles;

import express.deli.cosema.deliexpressapp.base.activity.BaseActivityView;
import express.deli.cosema.deliexpressapp.restaurante.entidad.RestauranteUi;

public interface DetallesView extends BaseActivityView<DetallesPresenter> {
    void mostrarDataDetalles(RestauranteUi restauranteUi);

    void mostrarDataHorario(String horarioGeneral, String lunesResp, String martesResp, String miercolesResp, String juevesResp, String viernesResp, String sabadoResp, String domingoResp);

    void rotarImagenHorarioTrue();

    void rotarImagenHorarioFalse();

    void rotarImagenTarjetasTrue();

    void rotarImagenTarjetasFalse();
}
