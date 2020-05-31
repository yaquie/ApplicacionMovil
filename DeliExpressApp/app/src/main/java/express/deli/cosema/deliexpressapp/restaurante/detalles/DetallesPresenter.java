package express.deli.cosema.deliexpressapp.restaurante.detalles;

import express.deli.cosema.deliexpressapp.base.activity.BaseActivityPresenter;

public interface DetallesPresenter extends BaseActivityPresenter<DetallesView> {
    void onClickHorario();

    void onClickTarjetas();
}
