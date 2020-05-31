package express.deli.cosema.deliexpressapp.perfil;

import android.content.res.Resources;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.fragment.BaseFragmentPresenterImpl;
import express.deli.cosema.deliexpressapp.utils.Constantes;
import express.deli.cosema.deliexpressapp.utils.SecurePreferences;

public class PerfilPresenterImpl extends BaseFragmentPresenterImpl<PerfilView> implements PerfilPresenter {

    public static final String TAG = PerfilPresenterImpl.class.getSimpleName();

    public PerfilPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onActivityCreated() {

    }

    @Override
    public void estadoFirebaseAccount(FirebaseAuth firebaseAuth) {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            /*usuariosUi.setKeyUser(firebaseUser.getUid());
            usuariosUi.setFoto(firebaseUser.getPhotoUrl().toString());
            usuariosUi.setNombres(firebaseUser.getDisplayName());
            usuariosUi.setEmail(firebaseUser.getEmail());
            validarUsuarioDatosExtras(usuariosUi);*/
            Log.d(TAG, "BIENVENIDO : " + firebaseUser.getEmail());
            // if (view != null) view.initBotomBar(usuariosUi);
        } else {//Cuando no tiene un Usuario , o sesion es nula
            String sesionSinUser = securePreferences.getString(Constantes.TIPO_SESION);
            Log.d(TAG, " Es un usuario pero sin Sesion " + sesionSinUser);
           /* if (sesionSinUser != null) {
                Log.d(TAG, " Es un usuario pero sin Sesion" );
            } else {
                if (view != null) view.regresarLogin();
            }*/
        }
    }

    private SecurePreferences securePreferences;
    @Override
    public void onSecurePreference(SecurePreferences securePreferences) {
        this.securePreferences = securePreferences;
    }
}
