package express.deli.cosema.deliexpressapp.login;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.UseCaseThreadPoolScheduler;
import express.deli.cosema.deliexpressapp.base.activity.BaseActivity;
import express.deli.cosema.deliexpressapp.login.adapter.PaginaAdapter;
import express.deli.cosema.deliexpressapp.login.dialog.SesionFragment;
import express.deli.cosema.deliexpressapp.login.listener.SliderListener;
import express.deli.cosema.deliexpressapp.main.MainActivity;
import express.deli.cosema.deliexpressapp.utils.Constantes;
import express.deli.cosema.deliexpressapp.utils.SecurePreferences;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView, SliderListener {

    public static final String TAG = LoginActivity.class.getSimpleName();
    public static final int RC_SIGN_IN = 777;


    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.dotsLayout)
    LinearLayout dotsLayout;
    @BindView(R.id.sign_in_button)
    SignInButton signInButton;
    @BindView(R.id.loginButton)
    LoginButton loginButton;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private GoogleSignInClient mGoogleSignInClient;
    private CallbackManager callbackManager;
    private SecurePreferences securePreferences;

    private int[] layouts = {R.layout.primer_slide_fragment, R.layout.segundo_slide_fragment, R.layout.tercer_slide_fragment};
    private ImageView imageViewDots[];

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected LoginView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return null;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.login_activity);
        securePreferences = new SecurePreferences(this, Constantes.KEY_SECURE_PREFERENCE, true);
        ButterKnife.bind(this);
        callbackManager = CallbackManager.Factory.create();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAdapter();
        initFireBaseAuth();
        initGoogleOptions();
        verificacionUsuarioFire();
        initVistas();
        initValidaTePreferences();
    }


    public void initValidaTePreferences() {
        String tipoSesion = securePreferences.getString(Constantes.TIPO_SESION);
        if (tipoSesion != null) {
            Log.d(TAG, "tipoSesion : " + tipoSesion);
        //    initStartMainActivity(tipoSesion);
            //startActivityMainMenu();
        }

    }



    private void initAdapter() {
        viewPager.setAdapter(new PaginaAdapter(getSupportFragmentManager()));
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        createDots(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                createDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void createDots(int currentItem) {
        if (dotsLayout != null)
            dotsLayout.removeAllViews();
        imageViewDots = new ImageView[layouts.length];
        for (int i = 0; i < layouts.length; i++) {
            imageViewDots[i] = new ImageView(this);
            if (i == currentItem) {
                imageViewDots[i].setImageDrawable(getResources().getDrawable(R.drawable.active_dots));
            } else {
                imageViewDots[i].setImageDrawable(getResources().getDrawable(R.drawable.default_dots));
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4, 0, 4, 0);
            dotsLayout.addView(imageViewDots[i], params);
        }
    }

    @OnClick({R.id.sign_in_button, R.id.btnIniciar, R.id.btnRegistrar, R.id.imageViewSkip})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.sign_in_button:
                mostrarProgressBar();
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
                break;
            case R.id.btnIniciar:
                initDialogIniciar();
                break;
            case R.id.btnRegistrar:
                initDialogRegistrar();
                break;
            case R.id.imageViewSkip:
                presenter.onClickSkipSinUser();
                break;
        }

    }

    private void initDialogIniciar() {
        String tipoInicio = "InicioSesion";
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        SesionFragment dialogFragment = new SesionFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tipoInicio", tipoInicio);
        dialogFragment.setArguments(bundle);
        dialogFragment.show(ft, "InicioSesion");
    }

    private void initDialogRegistrar() {
        String tipoInicio = "RegistroSesion";
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        SesionFragment dialogFragment = new SesionFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tipoInicio", tipoInicio);
        dialogFragment.setArguments(bundle);
        dialogFragment.show(ft, "RegistroSesion");
    }

    private void verificacionUsuarioFire() {
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
              //  presenter.onAuthStateChanged(firebaseUser);
               if (firebaseUser == null) {

                    Log.d(TAG, "firebaseUser == null");
                } else {
                 //   startActivityMainMenu();
                   // initValidaTePreferences();
                    Log.d(TAG, "firebaseUser != null");
                }
            }
        };
    }

    private void initGoogleOptions() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void initFireBaseAuth() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void initVistas() {
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setColorScheme(SignInButton.COLOR_DARK);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "Inicio Sesion de facebook");

                handleFacebookAccessToken(loginResult.getAccessToken());

            }

            @Override
            public void onCancel() {
                Log.d(TAG, "Inicio Sesion de onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "Inicio Sesion de onCancel" + error.getMessage());
            }
        });

        if (AccessToken.getCurrentAccessToken() == null) {
            Log.d(TAG, "Cuando la Sesion es nula");

        }
    }

    private void handleFacebookAccessToken(AccessToken accessToken) {

        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //securePreferences.put(Constantes.TIPO_SESION, Constantes.KEY_SECURE_FACEBOOK);
                        presenter.onComplete(task, Constantes.INICIO_SESION_FACEBOOK);
                    }
                });
    }


    @Override
    protected void onStart() {
        super.onStart();
        presenter.onSecurePreferences(securePreferences);
        if (authStateListener != null)
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null)
        firebaseAuth.removeAuthStateListener(authStateListener);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> completedTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            if (completedTask.isSuccessful()) {
                // Log.d(TAG,"completedTask.isSuccessful() " +completedTask.getResult().getEmail());
                AuthCredential credential = GoogleAuthProvider.getCredential(completedTask.getResult().getIdToken(), null);
                firebaseAuth.signInWithCredential(credential)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //securePreferences.put(Constantes.TIPO_SESION, Constantes.KEY_SECURE_GOOGLE);
                                presenter.onComplete(task, Constantes.INICIO_SESION_GOOGLE);
                               /* if (task.isSuccessful()) {
                                    FirebaseUser firebaseUser = task.getResult().getUser();
                                    securePreferences.put(Constantes.INICIO_SESION_GOOGLE,Constantes.KEY_SECURE_GOOGLE);
                                    Log.d(TAG,"firebaseUser : " +firebaseUser.getEmail());
                                }else{
                                    Log.d(TAG,"ERROR SINCRONIZACION FIRESTORE");
                                    mGoogleSignInClient.signOut();
                                }*/
                            }
                        });
            } else {
                Log.d(TAG, "elseelseelse : ");
            }

            // presenter.onComprobarEmailAdventista(completedTask);
            //  handleSignInResult(completedTask);
        }
    }


    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void onClickSiguienteSlide(int status) {
        int siguienteSlide = viewPager.getCurrentItem() + 1;
        switch (status) {
            case Constantes.PRIMER_SLIDE:
                viewPager.setCurrentItem(siguienteSlide);
                break;
            case Constantes.SEGUNDO_SLIDE:
                viewPager.setCurrentItem(siguienteSlide);
                break;
        }
    }

    @Override
    public void mostrarMensajeError(String mensaje) {
        mostrarToasMensaje(mensaje);
    }

    @Override
    public void initStartMainActivity(String tipoSessiones) {
        Log.d(TAG, "initStartMainActivity : " + tipoSessiones);
        switch (tipoSessiones) {
            case Constantes.INICIO_SESION_FACEBOOK:
                // securePreferences.put(Constantes.INICIO_SESION_FACEBOOK, Constantes.KEY_SECURE_FACEBOOK);
                securePreferences.put(Constantes.TIPO_SESION, Constantes.KEY_SECURE_FACEBOOK);
                startActivityMainMenu();
                break;
            case Constantes.INICIO_SESION_GOOGLE:
                // securePreferences.put(Constantes.INICIO_SESION_FACEBOOK, Constantes.KEY_SECURE_GOOGLE);
                securePreferences.put(Constantes.TIPO_SESION, Constantes.KEY_SECURE_GOOGLE);
                startActivityMainMenu();
                break;
            case Constantes.INICIO_SESION_ANONIMO:
                //   securePreferences.put(Constantes.INICIO_SESION_FACEBOOK, Constantes.KEY_SECURE_ANONIMO);
                securePreferences.put(Constantes.TIPO_SESION, Constantes.KEY_SECURE_ANONIMO);
                startActivityMainMenu();
                break;
            case Constantes.INICIO_SIN_USUARIO:
                //securePreferences.put(Constantes.INICIO_SIN_USUARIO, Constantes.KEY_SIN_USUARIO);
                securePreferences.put(Constantes.TIPO_SESION, Constantes.KEY_SIN_USUARIO);
                startActivityMainMenu();
                break;
        }
    }

    private void startActivityMainMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void mostrarToasMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
