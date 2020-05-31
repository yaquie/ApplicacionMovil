package express.deli.cosema.deliexpressapp.restaurante.listener;

import express.deli.cosema.deliexpressapp.restaurante.entidad.RestauranteUi;
import express.deli.cosema.deliexpressapp.restaurante.entidad.SeccionUi;

public interface RestauranteListener {
    void onClickRestaurante(RestauranteUi restauranteUi);

    void onClickSeccion(SeccionUi seccionUi);
}
