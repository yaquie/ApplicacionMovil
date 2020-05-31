package express.deli.cosema.deliexpressapp.restaurante.detalles.source;

import express.deli.cosema.deliexpressapp.restaurante.detalles.source.remote.DetallesRemote;

public class DetallesRepository implements DetallesSource {

    private DetallesRemote detallesRemote;
    public static DetallesRepository mInstance = null;

    public static final DetallesRepository getmInstance(DetallesRemote detallesRemote) {
        if (mInstance == null) {
            mInstance = new DetallesRepository(detallesRemote);
        }
        return mInstance;
    }

    public DetallesRepository(DetallesRemote detallesRemote) {
        this.detallesRemote = detallesRemote;
    }

    @Override
    public void onMostrarHorario(String codigoRestaurante, CallBackResultado callBackResultado) {
        detallesRemote.onMostrarHorario(codigoRestaurante, callBackResultado);
    }
}
