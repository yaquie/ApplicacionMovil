package express.deli.cosema.deliexpressapp.login.dialog;

import android.os.Bundle;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import express.deli.cosema.deliexpressapp.base.BasePresenter;
import express.deli.cosema.deliexpressapp.base.BaseView;
import express.deli.cosema.deliexpressapp.utils.SecurePreferences;

public interface SesionPresenter extends BasePresenter<SesionView> {
    void onExtras(Bundle arguments);

    void onClickEnviar(String email, String clave);

    void onCompleteRegistro(Task<AuthResult> task);

    void onCompleteInicioSesion(Task<AuthResult> task);

    void onSecurePreferences(SecurePreferences securePreferences);
}
