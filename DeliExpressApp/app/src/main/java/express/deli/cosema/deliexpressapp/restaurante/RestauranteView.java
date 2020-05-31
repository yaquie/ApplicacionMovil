package express.deli.cosema.deliexpressapp.restaurante;

import java.util.List;

import express.deli.cosema.deliexpressapp.base.activity.BaseActivityView;
import express.deli.cosema.deliexpressapp.restaurante.entidad.SeccionUi;

public interface RestauranteView extends BaseActivityView<RestaurantePresenter> {
    void mostrarLista(List<SeccionUi> seccionUis);
}
