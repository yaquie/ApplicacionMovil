package express.deli.cosema.deliexpressapp.login.dialog;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.UseCaseThreadPoolScheduler;
import express.deli.cosema.deliexpressapp.main.MainActivity;
import express.deli.cosema.deliexpressapp.utils.Constantes;
import express.deli.cosema.deliexpressapp.utils.SecurePreferences;

public class SesionFragment extends DialogFragment implements SesionView {
    public static final String TAG = SesionFragment.class.getSimpleName();
    Unbinder unbinder;
    private SesionPresenter presenter;
    @BindView(R.id.textViewTitulo)
    TextView textViewTitulo;
    @BindView(R.id.editTextEmail)
    EditText editTextEmail;
    @BindView(R.id.editTextClave)
    EditText editTextClave;
    private FirebaseAuth firebaseAuth;
    private SecurePreferences securePreferences;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_sesion, container, false);
        unbinder = ButterKnife.bind(this, v);
        firebaseAuth = FirebaseAuth.getInstance();
        securePreferences = new SecurePreferences(getActivity(),Constantes.KEY_SECURE_PREFERENCE,true);
        initPresenter();
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onExtras(getArguments());

    }


    private void initPresenter() {
        //   TareaGlobalRepository tareaGlobalRepository = new TareaGlobalRepository(new TareaGlobalRemote(new FireStore()));
        presenter = new SesionPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()));
        setPresenter(presenter);
    }


    @Override
    public void onStart() {
        //firebaseAuth.addAuthStateListener(authStateListener);
        super.onStart();
        presenter.onStart();
        presenter.onSecurePreferences(securePreferences);
    }

    @Override
    public void onStop() {
        // firebaseAuth.removeAuthStateListener(authStateListener);
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setPresenter(SesionPresenter presenter) {
        presenter.attachView(this);
        presenter.onCreate();
    }

    @Override
    public void mostrarTitulo(String titulo1) {
        textViewTitulo.setText(titulo1);
    }


    @Override
    public void initInicioRegistro(String email, String clave) {
        Log.d(TAG, "initInicioRegistro : " + email);
        firebaseAuth.createUserWithEmailAndPassword(email, clave).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                presenter.onCompleteRegistro(task);
            }
        });
        /*AuthCredential credential = EmailAuthProvider.getCredential(email, clave);
        firebaseAuth.getCurrentUser().linkWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "linkWithCredential:success");
                            //FirebaseUser user = task.getResult().getUser();
                           // updateUI(user);
                            Toast.makeText(getActivity(), "Registro Correctamente.",
                                    Toast.LENGTH_SHORT).show();
                            dismiss();
                        } else {
                            Log.d(TAG, "linkWithCredential:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });*/
    }

    @Override
    public void initInicioSesion(String email, String clave) {
        firebaseAuth.signInWithEmailAndPassword(email, clave).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                securePreferences.put(Constantes.TIPO_SESION, Constantes.KEY_SECURE_ANONIMO);
                presenter.onCompleteInicioSesion(task);
            }
        });
    }

    @Override
    public void mostrarMensaje(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initPreferences() {
        securePreferences.put(Constantes.TIPO_SESION, Constantes.KEY_SECURE_ANONIMO);
    }

    @Override
    public void initStartMainActivity(String tipoSessiones) {
        switch (tipoSessiones) {
            case Constantes.INICIO_SESION_ANONIMO:
                securePreferences.put(Constantes.TIPO_SESION, Constantes.KEY_SECURE_ANONIMO);
                startActivityMainMenu();
                break;
        }

    }

    private void startActivityMainMenu() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @OnClick({R.id.button_cancelar, R.id.button_enviar})
    public void OnClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.button_cancelar:
                dismiss();
                break;
            case R.id.button_enviar:
                String email = editTextEmail.getText().toString();
                String clave = editTextClave.getText().toString();
                presenter.onClickEnviar(email, clave);
                break;
        }
    }
}
