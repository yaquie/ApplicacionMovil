package express.deli.cosema.deliexpressapp.perfil;

import com.google.firebase.auth.FirebaseAuth;

import express.deli.cosema.deliexpressapp.base.fragment.BaseFragmentPresenter;
import express.deli.cosema.deliexpressapp.utils.SecurePreferences;

public interface PerfilPresenter extends BaseFragmentPresenter<PerfilView> {
    void estadoFirebaseAccount(FirebaseAuth firebaseAuth);

    void onSecurePreference(SecurePreferences securePreferences);
}
