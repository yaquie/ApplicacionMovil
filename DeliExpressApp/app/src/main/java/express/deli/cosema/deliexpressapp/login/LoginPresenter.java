package express.deli.cosema.deliexpressapp.login;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import express.deli.cosema.deliexpressapp.base.activity.BaseActivityPresenter;
import express.deli.cosema.deliexpressapp.utils.SecurePreferences;

public interface LoginPresenter extends BaseActivityPresenter<LoginView> {

    void onComplete(Task<AuthResult> task, String tipoSessiones);

    void onClickSkipSinUser();

    void onSecurePreferences(SecurePreferences securePreferences);


}
