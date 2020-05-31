package express.deli.cosema.deliexpressapp.restaurante.detalles;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import org.parceler.Parcels;

import express.deli.cosema.deliexpressapp.base.UseCase;
import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.activity.BaseActivityPresenterImpl;
import express.deli.cosema.deliexpressapp.restaurante.RestauranteFragment;
import express.deli.cosema.deliexpressapp.restaurante.detalles.useCase.MostrarHorario;
import express.deli.cosema.deliexpressapp.restaurante.entidad.RestauranteUi;
import express.deli.cosema.deliexpressapp.utils.Constantes;

public class DetallesPresenterImpl extends BaseActivityPresenterImpl<DetallesView> implements DetallesPresenter {

    private RestauranteUi restauranteUi;
    private MostrarHorario mostrarHorario;

    public static final String TAG = DetallesPresenterImpl.class.getSimpleName();

    public DetallesPresenterImpl(UseCaseHandler handler, Resources res, MostrarHorario mostrarHorario) {
        super(handler, res);
        this.mostrarHorario = mostrarHorario;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }


    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.restauranteUi = Parcels.unwrap(extras.getParcelable(Constantes.EXTRA_RESTAURANTE));


        initUseCaseHorario(restauranteUi.getIdRestaurante());
    }

    @Override
    public void onStart() {
        super.onStart();
        if (view != null) {
            view.mostrarDataDetalles(restauranteUi);
        }
    }

    private void initUseCaseHorario(String idRestaurante) {
        Log.d(TAG, "restaurante : " + restauranteUi.getNombreRestaurante());
        handler.execute(mostrarHorario, new MostrarHorario.RequestValues(idRestaurante),
                new UseCase.UseCaseCallback<MostrarHorario.ResponseValue>() {
                    @Override
                    public void onSuccess(MostrarHorario.ResponseValue response) {
                        if (response.getHorarioGeneral() != null) {
                            mostrarLunes(response.getLunesResp());
                            mostrarMartes(response.getMartesResp());
                            mostrarMiercoles(response.getMiercolesResp());
                            mostrarJueves(response.getJuevesResp());
                            mostrarViernes(response.getViernesResp());
                            mostrarSabado(response.getSabadoResp());
                            mostrarDomingo(response.getDomingoResp());
                            if (view != null) view.mostrarDataHorario(
                                    response.getHorarioGeneral(),
                                    lunesResp,
                                    martesResp,
                                    miercolesResp,
                                    juevesResp,
                                    viernesResp,
                                    sabadoResp,
                                    domingoResp);
                        }
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "onError");
                    }
                });
    }

    String domingoResp;

    private void mostrarDomingo(String domingo) {
        if (domingo == null) return;
        if (domingo.equals("0")) {
            domingoResp = "Cerrado";
        } else {
            domingoResp = "Abierto";
        }
    }

    String sabadoResp;

    private void mostrarSabado(String sabado) {
        if (sabado == null) return;
        if (sabado.equals("0")) {
            sabadoResp = "Cerrado";
        } else {
            sabadoResp = "Abierto";
        }
    }

    String viernesResp = "";

    private void mostrarViernes(String viernes) {
        if (viernes == null) return;
        if (viernes.equals("0")) {
            viernesResp = "Cerrado";
        } else {
            viernesResp = "Abierto";
        }
    }

    String juevesResp;

    private void mostrarJueves(String jueves) {
        if (jueves == null) return;
        if (jueves.equals("0")) {
            juevesResp = "Cerrado";
        } else {
            juevesResp = "Abierto";
        }
    }

    String miercolesResp = "";

    private void mostrarMiercoles(String miercoles) {
        if (miercoles == null) return;
        if (miercoles.equals("0")) {
            miercolesResp = "Cerrado";
        } else {
            miercolesResp = "Abierto";
        }
    }

    String martesResp = "";

    private void mostrarMartes(String martes) {
        if (martes == null) return;
        if (martes.equals("0")) {
            martesResp = "Cerrado";
        } else {
            martesResp = "Abierto";
        }
    }

    String lunesResp = "";

    private void mostrarLunes(String lunes) {
        if (lunes == null) return;
        if (lunes.equals("0")) {
            lunesResp = "Cerrado";
        } else {
            lunesResp = "Abierto";
        }
    }

    boolean aBooleanHorario = true;

    @Override
    public void onClickHorario() {
        if (aBooleanHorario) {
            aBooleanHorario = false;
            if (view != null) view.rotarImagenHorarioTrue();
        } else {
            aBooleanHorario = true;
            if (view != null) view.rotarImagenHorarioFalse();
        }

    }

    boolean aBooleanTarjetas = true;
    @Override
    public void onClickTarjetas() {
        if (aBooleanTarjetas) {
            aBooleanTarjetas = false;
            if (view != null) view.rotarImagenTarjetasTrue();
        } else {
            aBooleanTarjetas = true;
            if (view != null) view.rotarImagenTarjetasFalse();
        }
    }
}
