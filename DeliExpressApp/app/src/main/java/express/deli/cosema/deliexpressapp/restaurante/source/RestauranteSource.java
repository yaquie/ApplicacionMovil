package express.deli.cosema.deliexpressapp.restaurante.source;

import java.util.List;

import express.deli.cosema.deliexpressapp.listenerDataSource.CallBackResultado1;
import express.deli.cosema.deliexpressapp.restaurante.entidad.SeccionUi;

public interface RestauranteSource {

    void onMostrarListaRestaurante(CallBackResultado1<List<SeccionUi>> listCallBackResultado1);

}
