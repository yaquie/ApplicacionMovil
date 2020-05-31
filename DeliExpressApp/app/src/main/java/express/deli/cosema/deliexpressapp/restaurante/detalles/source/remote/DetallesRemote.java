package express.deli.cosema.deliexpressapp.restaurante.detalles.source.remote;

import express.deli.cosema.deliexpressapp.api.Api;
import express.deli.cosema.deliexpressapp.api.RetrofitClient;
import express.deli.cosema.deliexpressapp.api.response.ListaSeccionResponse;
import express.deli.cosema.deliexpressapp.api.response.MostrarHorarioResponse;
import express.deli.cosema.deliexpressapp.restaurante.detalles.source.DetallesSource;
import express.deli.cosema.deliexpressapp.utils.Constantes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallesRemote implements DetallesSource {

    public static final String TAG = DetallesRemote.class.getSimpleName();

    @Override
    public void onMostrarHorario(String codigoRestaurante, final CallBackResultado callBackResultado) {
        Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<MostrarHorarioResponse> call = apiService.mostrarHorario(codigoRestaurante);

        call.enqueue(new Callback<MostrarHorarioResponse>() {
            @Override
            public void onResponse(Call<MostrarHorarioResponse> call, Response<MostrarHorarioResponse> response) {
                MostrarHorarioResponse cambioResponse = response.body();
                if (cambioResponse != null) {
                    if (cambioResponse.getError()) {
                        callBackResultado.onCallBackResultado(null, null, null, null, null, null, null, null);

                    } else {
                        callBackResultado.onCallBackResultado(cambioResponse.getHoraInicio() + " - " + cambioResponse
                                        .getHoraFin(), cambioResponse.getLunes(), cambioResponse.getMartes(), cambioResponse.getMiercoles(),
                                cambioResponse.getJueves(), cambioResponse.getViernes(), cambioResponse.getSabado(), cambioResponse.getDomingo());
                    }
                } else {
                    callBackResultado.onCallBackResultado(null, null, null, null, null, null, null, null);
                }
            }

            @Override
            public void onFailure(Call<MostrarHorarioResponse> call, Throwable t) {
                callBackResultado.onCallBackResultado(null, null, null, null, null, null, null, null);
            }
        });
    }
}
