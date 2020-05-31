package express.deli.cosema.deliexpressapp.perfil;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.UseCaseThreadPoolScheduler;
import express.deli.cosema.deliexpressapp.base.fragment.BaseFragment;
import express.deli.cosema.deliexpressapp.utils.Constantes;
import express.deli.cosema.deliexpressapp.utils.SecurePreferences;

public class PerfilFragment extends BaseFragment<PerfilView, PerfilPresenter> implements PerfilView {
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private SecurePreferences securePreferences;

    public static final String TAG = PerfilFragment.class.getSimpleName();
    public static PerfilFragment newInstance() {
        PerfilFragment perfilFragment = new PerfilFragment();
        Bundle b = new Bundle();
        perfilFragment.setArguments(b);
        return perfilFragment;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected PerfilPresenter getPresenter() {
        return new PerfilPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),getResources());
    }

    @Override
    protected PerfilView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.perfil_fragment,container,false);
        securePreferences = new SecurePreferences(getActivity(), Constantes.KEY_SECURE_PREFERENCE, true);
        firebaseAuth = FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onSecurePreference(securePreferences);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void onStart() {
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                presenter.estadoFirebaseAccount(firebaseAuth);
            }
        };
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authStateListener != null) firebaseAuth.removeAuthStateListener(authStateListener);
    }

    /*@Override
    protected void onStart() {
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                presenter.estadoFirebaseAccount(firebaseAuth);
            }
        };
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null) firebaseAuth.removeAuthStateListener(authStateListener);
    }*/
}
