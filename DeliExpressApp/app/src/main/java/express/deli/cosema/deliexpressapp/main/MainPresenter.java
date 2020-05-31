package express.deli.cosema.deliexpressapp.main;

import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import express.deli.cosema.deliexpressapp.base.activity.BaseActivityPresenter;
import express.deli.cosema.deliexpressapp.utils.SecurePreferences;

public interface MainPresenter extends BaseActivityPresenter<MainView>{
    void estadoFirebaseAccount(FirebaseAuth firebaseAuth);

    void posicionFragment(int position);

    void onSecurePreferences(SecurePreferences securePreferences);

    void onNavigationItemSelected(Menu item, String tipoEstado);
}
