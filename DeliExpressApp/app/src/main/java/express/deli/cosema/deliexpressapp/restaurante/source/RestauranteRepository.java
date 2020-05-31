package express.deli.cosema.deliexpressapp.restaurante.source;

import java.util.List;

import express.deli.cosema.deliexpressapp.listenerDataSource.CallBackResultado1;
import express.deli.cosema.deliexpressapp.restaurante.entidad.SeccionUi;
import express.deli.cosema.deliexpressapp.restaurante.source.remote.RestauranteRemote;

public class RestauranteRepository implements RestauranteSource {

    public static RestauranteRepository mInstance = null;

    private RestauranteRemote restauranteRemote;

    public static final RestauranteRepository getmInstance(RestauranteRemote restauranteRemote) {
        if (mInstance == null) {
            mInstance = new RestauranteRepository(restauranteRemote);
        }
        return mInstance;
    }

    public RestauranteRepository(RestauranteRemote restauranteRemote) {
        this.restauranteRemote = restauranteRemote;
    }

    @Override
    public void onMostrarListaRestaurante(CallBackResultado1<List<SeccionUi>> listCallBackResultado1) {
        restauranteRemote.onMostrarListaRestaurante(listCallBackResultado1);
    }
}
