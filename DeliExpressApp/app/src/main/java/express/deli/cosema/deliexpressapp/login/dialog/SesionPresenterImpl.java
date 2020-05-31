package express.deli.cosema.deliexpressapp.login.dialog;

import android.os.Bundle;
import android.util.Log;


import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import express.deli.cosema.deliexpressapp.api.Api;
import express.deli.cosema.deliexpressapp.api.RetrofitClient;
import express.deli.cosema.deliexpressapp.api.response.DefaultResponse;
import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.utils.Constantes;
import express.deli.cosema.deliexpressapp.utils.SecurePreferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SesionPresenterImpl implements SesionPresenter {

    public static final String TAG = SesionPresenterImpl.class.getSimpleName();
    SesionView view;

    public SesionPresenterImpl(UseCaseHandler handler) {
    }

    @Override
    public void attachView(SesionView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onBackPressed() {

    }

    String tipoInicio;

    @Override
    public void onExtras(Bundle arguments) {
        if (arguments == null) return;
        this.tipoInicio = arguments.getString("tipoInicio");
        validarTipoSesion(tipoInicio);
    }

    @Override
    public void onClickEnviar(String email, String clave) {
        switch (tipoInicio) {
            case "InicioSesion":
                if (view != null) view.initInicioSesion(email, clave);
                break;
            case "RegistroSesion":
                if (view != null) view.initInicioRegistro(email, clave);
                break;
        }
    }

    @Override
    public void onCompleteRegistro(Task<AuthResult> task) {
        if (task.isSuccessful()) {
            //inicia Actividad principal
            FirebaseUser firebaseUser = task.getResult().getUser();
            Log.d(TAG, "firebaseUserEmail : " + firebaseUser.getEmail() +
                    "firebaseUser Display Name : " + firebaseUser.getDisplayName() +
                    "firebaseUser : " + firebaseUser.getIdToken(true) +
                    "firebaseUser : " + firebaseUser.getPhotoUrl());
            Log.d(TAG, "datos Creado correctamente");
            //   if (view != null) view.initPreferences();
            initRetroFitValidar(Constantes.INICIO_SESION_ANONIMO, firebaseUser);
        } else {
            if (view != null) view.mostrarMensaje(task.getException().getMessage());
        }
    }

    private void initRetroFitValidar(final String tipoSessiones, FirebaseUser firebaseUser) {
        Log.d(TAG, "initRetroFitValidar :" + tipoSessiones);
        Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        final String userId = firebaseUser.getUid();
        String displayNombre = "";
        String fotoUser = "";
        String userEmail = firebaseUser.getEmail();
        apiService.validarSesionUsuario(userId,
                tipoSessiones,
                displayNombre,
                fotoUser,
                userEmail)
                .enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        DefaultResponse defaultResponse = response.body();
                        if (defaultResponse != null) {
                            switch (defaultResponse.getEstado()) {
                                case Constantes.ITEM_EXISTE:
                                    securePreferences.put(Constantes.KEY_CODIGO_USUARIO, userId);
                                    if (view != null) view.initStartMainActivity(tipoSessiones);
                                    //if (view != null) view.initPreferences();
                                    break;
                                case Constantes.ITEM_NO_EXISTE:
                                    securePreferences.put(Constantes.KEY_CODIGO_USUARIO, userId);
                                    if (view != null) view.initStartMainActivity(tipoSessiones);
                                    //if (view != null) view.initPreferences();
                                    break;
                            }


                        } else {
                            Log.d(TAG, "else : nullo Response ");
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure : " + t.getMessage().toString());
                    }
                });
    }

    @Override
    public void onCompleteInicioSesion(Task<AuthResult> task) {
        if (task.isSuccessful()) {
            FirebaseUser firebaseUser = task.getResult().getUser();

            Log.d(TAG, "firebaseUserEmail : " + firebaseUser.getEmail() +
                    "firebaseUser Display Name : " + firebaseUser.getDisplayName() +
                    "  /   getUid : " + firebaseUser.getUid() + "     /  " +
                    "getProviderId : " + firebaseUser.getProviderId());
            Log.d(TAG, "Inicio Session Correctamente : " + firebaseUser.getEmail());
            if (view != null) view.initPreferences();
        } else {
            if (view != null) view.mostrarMensaje(task.getException().getMessage());
        }
    }

    private SecurePreferences securePreferences;

    @Override
    public void onSecurePreferences(SecurePreferences securePreferences) {
        this.securePreferences = securePreferences;
    }

    private void validarTipoSesion(String tipoInicio) {
        switch (tipoInicio) {
            case "InicioSesion":
                String titulo1 = "Iniciar Sesión";
                if (view != null) view.mostrarTitulo(titulo1);
                break;
            case "RegistroSesion":
                String titulo2 = "Registrarse";
                if (view != null) view.mostrarTitulo(titulo2);
                break;
        }
    }
}
