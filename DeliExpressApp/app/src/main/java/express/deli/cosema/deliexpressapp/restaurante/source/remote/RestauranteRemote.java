package express.deli.cosema.deliexpressapp.restaurante.source.remote;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import express.deli.cosema.deliexpressapp.api.Api;
import express.deli.cosema.deliexpressapp.api.RetrofitClient;
import express.deli.cosema.deliexpressapp.api.response.ListaRestauranteResponse;
import express.deli.cosema.deliexpressapp.api.response.ListaSeccionResponse;
import express.deli.cosema.deliexpressapp.listenerDataSource.CallBackResultado1;
import express.deli.cosema.deliexpressapp.restaurante.entidad.RestauranteUi;
import express.deli.cosema.deliexpressapp.restaurante.entidad.SeccionUi;
import express.deli.cosema.deliexpressapp.restaurante.source.RestauranteSource;
import express.deli.cosema.deliexpressapp.utils.Constantes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestauranteRemote implements RestauranteSource {
    public static final String TAG = RestauranteRemote.class.getSimpleName();

    @Override
    public void onMostrarListaRestaurante(final CallBackResultado1<List<SeccionUi>> listCallBackResultado1) {
        final Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<ListaSeccionResponse> call = apiService.mostrarListaSeccion();
        call.enqueue(new Callback<ListaSeccionResponse>() {
            @Override
            public void onResponse(Call<ListaSeccionResponse> call, Response<ListaSeccionResponse> response) {
                ListaSeccionResponse cambioResponse = response.body();
                if (cambioResponse != null) {
                    if (cambioResponse.getError()) {
                        Log.d(TAG, "cambioResponse.getError() : " + cambioResponse.getMessage());
                        listCallBackResultado1.onCallBackResultado(null);
                    } else {
                        if (cambioResponse.getListaSeccion() == null) {
                            Log.d(TAG, "else::cambioResponse.getListaSeccionRest() == null ");
                            listCallBackResultado1.onCallBackResultado(null);
                        } else {
                            List<ListaSeccionResponse.ListaSeccionRestResponse> list = cambioResponse.getListaSeccion();

                            final List<SeccionUi> seccionUiList = new ArrayList<>();
                            for (final ListaSeccionResponse.ListaSeccionRestResponse
                                    restResponse : list) {
                                final String codigoSeccion = restResponse.getCodigoSeccion();
                                final String nombreSeccion = restResponse.getNombreSeccion();


                                Call<ListaRestauranteResponse> callRestaurante = apiService.mostrarListaSecRestaurante(Integer.parseInt(restResponse.getCodigoSeccion()));


                                callRestaurante.enqueue(new Callback<ListaRestauranteResponse>() {
                                    @Override
                                    public void onResponse(Call<ListaRestauranteResponse> call, Response<ListaRestauranteResponse> response) {
                                        ListaRestauranteResponse cambioResponse = response.body();
                                        if (cambioResponse != null) {
                                            if (cambioResponse.getError()) {
                                                Log.d(TAG, "cambioResponse.getError() : " + cambioResponse.getMessage());
                                                listCallBackResultado1.onCallBackResultado(null);
                                            } else {
                                                if (cambioResponse.getListaRestauranteRestResponseList() == null) {
                                                    Log.d(TAG, "else::cambioResponse.getListaSeccionRest() == null ");
                                                    listCallBackResultado1.onCallBackResultado(null);
                                                } else {
                                                    List<ListaRestauranteResponse.ListaRestauranteRestResponse> list = cambioResponse.getListaRestauranteRestResponseList();

                                                    List<RestauranteUi> uiArrayList = new ArrayList<>();
                                                    SeccionUi seccionUi = new SeccionUi();
                                                    seccionUi.setIdSeccion(codigoSeccion);
                                                    seccionUi.setNombreSeccion(nombreSeccion);
                                                    seccionUiList.add(seccionUi);
                                                    for (ListaRestauranteResponse.ListaRestauranteRestResponse
                                                            restResponse : list) {
                                                        RestauranteUi restauranteUi = new RestauranteUi();
                                                        restauranteUi.setIdRestaurante(restResponse.getCodigoRestaurante());
                                                        restauranteUi.setNombreRestaurante(restResponse.getNombreRestaurante());
                                                        restauranteUi.setImageRestaurante(restResponse.getFotoRestaurante());
                                                        restauranteUi.setPuntosRestaurante(restResponse.getPuntosRestaurante());
                                                        restauranteUi.setPlatoPrecioPersona(restResponse.getPlatPersona());
                                                        restauranteUi.setSeccionUi(seccionUi);
                                                        uiArrayList.add(restauranteUi);
                                                        //
                                                    }
                                                    seccionUi.setRestauranteUi(uiArrayList);

                                                    listCallBackResultado1.onCallBackResultado(seccionUiList);
                                                }
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ListaRestauranteResponse> call, Throwable t) {
                                        Log.d(TAG, "cambioResponse : " + t.getMessage());
                                        listCallBackResultado1.onCallBackResultado(null);
                                    }
                                });

                            }

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ListaSeccionResponse> call, Throwable t) {
                Log.d(TAG, "cambioResponse : " + t.getMessage());
                listCallBackResultado1.onCallBackResultado(null);
            }
        });
    }

    private void initRetrofitRest(final CallBackResultado1<List<SeccionUi>> listCallBackResultado1, List<SeccionUi> seccionUiList) {

        Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<ListaRestauranteResponse> call = apiService.mostrarListaSecRestaurante(Integer.parseInt("1"));

        for (final SeccionUi seccionUi : seccionUiList) {
            call.enqueue(new Callback<ListaRestauranteResponse>() {
                @Override
                public void onResponse(Call<ListaRestauranteResponse> call, Response<ListaRestauranteResponse> response) {
                    ListaRestauranteResponse cambioResponse = response.body();
                    if (cambioResponse != null) {
                        if (cambioResponse.getError()) {
                            Log.d(TAG, "cambioResponse.getError() : " + cambioResponse.getMessage());
                            listCallBackResultado1.onCallBackResultado(null);
                        } else {
                            if (cambioResponse.getListaRestauranteRestResponseList() == null) {
                                Log.d(TAG, "else::cambioResponse.getListaSeccionRest() == null ");
                                listCallBackResultado1.onCallBackResultado(null);
                            } else {
                                List<ListaRestauranteResponse.ListaRestauranteRestResponse> list = cambioResponse.getListaRestauranteRestResponseList();

                                List<RestauranteUi> uiArrayList = new ArrayList<>();
                                for (ListaRestauranteResponse.ListaRestauranteRestResponse
                                        restResponse : list) {
                                    RestauranteUi restauranteUi = new RestauranteUi();
                                    restauranteUi.setIdRestaurante(restResponse.getCodigoRestaurante());
                                    restauranteUi.setNombreRestaurante(restResponse.getNombreRestaurante());
                                    uiArrayList.add(restauranteUi);
                                    //
                                }

                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<ListaRestauranteResponse> call, Throwable t) {
                    Log.d(TAG, "cambioResponse : " + t.getMessage());
                    listCallBackResultado1.onCallBackResultado(null);
                }
            });
        }


    }
}
