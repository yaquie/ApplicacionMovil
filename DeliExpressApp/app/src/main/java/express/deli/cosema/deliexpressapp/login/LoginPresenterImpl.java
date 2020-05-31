package express.deli.cosema.deliexpressapp.login;

import android.content.res.Resources;
import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.api.Api;
import express.deli.cosema.deliexpressapp.api.RetrofitClient;
import express.deli.cosema.deliexpressapp.api.response.DefaultResponse;
import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.activity.BaseActivityPresenterImpl;
import express.deli.cosema.deliexpressapp.utils.Constantes;
import express.deli.cosema.deliexpressapp.utils.SecurePreferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenterImpl extends BaseActivityPresenterImpl<LoginView> implements LoginPresenter {

    public static final String TAG = LoginPresenterImpl.class.getSimpleName();

    public LoginPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onComplete(Task<AuthResult> task, String tipoSessiones) {
        if (task.isSuccessful()) {
            FirebaseUser firebaseUser = task.getResult().getUser();
            String uiId = firebaseUser.getUid();
            Log.d(TAG, "firebaseUserEmail : " + firebaseUser.getEmail() +
                    "firebaseUser Display Name : " + firebaseUser.getDisplayName() +
                    "  /   getUid : " + firebaseUser.getUid() + "     /  " +
                    "firebaseUser : " + firebaseUser.getPhotoUrl());
            if (firebaseUser != null) {
                //  if (view != null) view.initStartMainActivity(tipoSessiones);
                switch (tipoSessiones) {
                    case Constantes.INICIO_SESION_FACEBOOK:
                        /*Datos Completos sin Email*/
                        if (view != null) view.mostrarProgressBar();
                        initRetroFitValidar(tipoSessiones, firebaseUser);
                        break;
                    case Constantes.INICIO_SESION_GOOGLE:
                        /*Email , Datos completos */
                        if (view != null) view.mostrarProgressBar();
                        initRetroFitValidar(tipoSessiones, firebaseUser);
                        break;
                    case Constantes.INICIO_SESION_ANONIMO:
                        if (view != null) view.mostrarProgressBar();
                        initRetroFitValidar(tipoSessiones, firebaseUser);
                        break;
                }
            } else {
                if (view != null) view.mostrarMensajeError(res.getString(R.string.error_sesion));
            }

        } else {
            if (view != null) view.mostrarMensajeError(res.getString(R.string.error_sesion));
        }
    }

    private void initRetroFitValidar(final String tipoSessiones, FirebaseUser firebaseUser) {
        Log.d(TAG, "initRetroFitValidar :" + tipoSessiones);
        Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        final String userId = firebaseUser.getUid();
        String displayNombre = firebaseUser.getDisplayName();
        String fotoUser = firebaseUser.getPhotoUrl().toString();
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
                                    break;
                                case Constantes.ITEM_NO_EXISTE:
                                    securePreferences.put(Constantes.KEY_CODIGO_USUARIO, userId);
                                   if (view != null) view.initStartMainActivity(tipoSessiones);
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
    public void onClickSkipSinUser() {
        String tipoSessiones = Constantes.INICIO_SIN_USUARIO;
        if (view != null) view.initStartMainActivity(tipoSessiones);
    }

    private SecurePreferences securePreferences;

    @Override
    public void onSecurePreferences(SecurePreferences securePreferences) {
        this.securePreferences = securePreferences;
    }


}
