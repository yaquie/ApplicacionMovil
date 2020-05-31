package express.deli.cosema.deliexpressapp.main;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.api.Api;
import express.deli.cosema.deliexpressapp.api.RetrofitClient;
import express.deli.cosema.deliexpressapp.api.response.DefaultResponse;
import express.deli.cosema.deliexpressapp.api.response.MostrarHorarioResponse;
import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.activity.BaseActivityPresenterImpl;
import express.deli.cosema.deliexpressapp.favoritos.FavoritosFragment;
import express.deli.cosema.deliexpressapp.perfil.PerfilFragment;
import express.deli.cosema.deliexpressapp.restaurante.RestauranteFragment;
import express.deli.cosema.deliexpressapp.utils.Constantes;
import express.deli.cosema.deliexpressapp.utils.SecurePreferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenterImpl extends BaseActivityPresenterImpl<MainView> implements MainPresenter {

    public static final String TAG = MainPresenterImpl.class.getSimpleName();
    private AppCompatActivity activity;

    public MainPresenterImpl(UseCaseHandler handler, Resources res, AppCompatActivity activity) {
        super(handler, res);
        this.activity = activity;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onBackPressed() {

    }

    FirebaseUser firebaseUser;

    @Override
    public void estadoFirebaseAccount(FirebaseAuth firebaseAuth) {
        this.firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {


            /*usuariosUi.setKeyUser(firebaseUser.getUid());
            usuariosUi.setFoto(firebaseUser.getPhotoUrl().toString());
            usuariosUi.setNombres(firebaseUser.getDisplayName());
            usuariosUi.setEmail(firebaseUser.getEmail());
            validarUsuarioDatosExtras(usuariosUi);*/
            Log.d(TAG, "BIENVENIDO : ");
            String tipoSesion = securePreferences.getString(Constantes.TIPO_SESION);
            mostrarDataNavigation(tipoSesion, firebaseUser);
            // if (view != null) view.initBotomBar(usuariosUi);
        } else {//Cuando no tiene un Usuario , o sesion es nula
            String sesionSinUser = securePreferences.getString(Constantes.TIPO_SESION);
            if (sesionSinUser==null){
                if (view != null) view.regresarLogin();
                return;
            }
            Log.d(TAG, " Es un usuario pero sin Sesion " + sesionSinUser);
           /* if (sesionSinUser != null) {
                Log.d(TAG, " Es un usuario pero sin Sesion" );
            } else {
                if (view != null) view.regresarLogin();
            }*/
        }
    }

    private void mostrarDataNavigation(String tipoSesion, FirebaseUser firebaseUser) {
        switch (tipoSesion) {
            case Constantes.KEY_SECURE_FACEBOOK:
                String fotoUsuarioFb = firebaseUser.getPhotoUrl().toString();
                String nombreFb = firebaseUser.getDisplayName();
                String tipoSesionFb = Constantes.INICIO_SESION_FACEBOOK;
                if (view != null) {
                    view.mostrarDataPerfilNavigation(fotoUsuarioFb, nombreFb, tipoSesionFb);
                    view.ocultarButtonIniciar();
                }
                break;
            case Constantes.KEY_SECURE_GOOGLE:
                String fotoUsuarioGog = firebaseUser.getPhotoUrl().toString();
                String nombreGog = firebaseUser.getDisplayName();
                String tipoSesionGog = Constantes.INICIO_SESION_GOOGLE;
                if (view != null) {
                    view.mostrarDataPerfilNavigation(fotoUsuarioGog, nombreGog, tipoSesionGog);
                    view.ocultarButtonIniciar();
                }
                break;
            case Constantes.KEY_SECURE_ANONIMO:
                if(view!=null)view.deshabilitarDrawerNavigation(true);
                break;
            case Constantes.KEY_SIN_USUARIO:
                break;

        }
    }

    Fragment fragment = null;

    @Override
    public void posicionFragment(int position) {

        switch (position) {
            case 0:
                fragment = RestauranteFragment.newInstance();

               /* String tipoSession = securePreferences.getString(Constantes.TIPO_SESION);
                Log.d(TAG, "tipoSession : " + tipoSession);
                validarTipoSession(tipoSession);*/


                if (view != null) {
                    view.mostrarVistas();
                    view.ocultarContentPerfil();
                    view.habilitarDrawerNavigation(true);
                }
                //validacionRoles();

                break;
            case 1:
                fragment = FavoritosFragment.newInstance();
                // if (view != null) view.cambiarTitulo(res.getString(R.string.mensajes_fragment));
                //  ocultarPost();
                if (view != null) {
                    view.mostrarVistas();
                    view.ocultarContentPerfil();
                    view.deshabilitarDrawerNavigation(false);
                }
                break;
            case 2:
                fragment = PerfilFragment.newInstance();
                String sesionSinUser = securePreferences.getString(Constantes.TIPO_SESION);
                Log.d(TAG, "sesionSinUser : " + sesionSinUser);
                if (view != null) {
                    view.ocultarVistas();
                    view.mostrarContentPerfil();
                    view.deshabilitarDrawerNavigation(false);
                }
                mostrarDataPerfilFragment(sesionSinUser);
                break;
            default:
                fragment = RestauranteFragment.newInstance();
                if (view != null) {
                    view.mostrarVistas();
                    view.ocultarContentPerfil();
                    view.habilitarDrawerNavigation(true);
                }
                // if (view != null) view.cambiarTitulo("NoticiasFragment");
                break;

        }
        if (fragment != null) {
            FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame, fragment);
            ft.commit();
        }
    }


    String tipoEstado;

    /*Este metodo validara el usuario Rol */
    private void initValidarRolUsuario(String codigoUsuario) {
        Log.d(TAG, "initValidarEstado : " + codigoUsuario);
        Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<DefaultResponse> call = apiService.validarRolUsuario(codigoUsuario);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse cambioResponse = response.body();
                if (cambioResponse != null) {
                    if (cambioResponse.getError()) {
                        Log.d(TAG, "Algo paso papu no encontre el id");
                    } else {
                        /*aca validamos el tipo de Rl*/
                        Log.d(TAG, " papu  encontre el id " + cambioResponse.getEstado());
                        tipoEstado = cambioResponse.getEstado();
                        if (view != null) view.validarShowNavigation(tipoEstado);

                        /*switch (cambioResponse.getEstado()) {
                            case Constantes.ROL_USUARIO_ADMIN_GENERAL:
                                if (view != null) {
                                    view.mostrarVistas();
                                    view.ocultarContentPerfil();
                                    view.habilitarDrawerNavigation(true);
                                    view.mostrarDatosNavigation();
                                }
                                break;
                            case Constantes.ROL_USUARIO_ADMIN_RESTAURANTE:
                                if (view != null) {
                                    view.mostrarVistas();
                                    view.ocultarContentPerfil();
                                    view.habilitarDrawerNavigation(true);
                                    view.mostrarDatosNavigation();
                                }
                                break;
                            case Constantes.ROL_USUARIO_NORMAL:
                                if (view != null) {
                                    view.mostrarVistas();
                                    view.ocultarContentPerfil();
                                    view.habilitarDrawerNavigation(false);
                                    view.ocultarDatosNavigation();
                                }
                                break;
                        }*/

                    }
                } else {
                    Log.d(TAG, "cambioResponse Null");
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }


    private void mostrarDataPerfilFragment(String sesionSinUser) {
        Log.d(TAG, "mostrarDataPerfilFragment" + sesionSinUser);
        switch (sesionSinUser) {
            case Constantes.KEY_SECURE_FACEBOOK:
                String fotoUsuarioFb = firebaseUser.getPhotoUrl().toString();
                String nombreFb = firebaseUser.getDisplayName();
                String tipoSesionFb = Constantes.INICIO_SESION_FACEBOOK;
                if (view != null) {
                    view.mostrarDataPerfil(fotoUsuarioFb, nombreFb, tipoSesionFb);
                    view.ocultarButtonIniciar();
                }
                break;
            case Constantes.KEY_SECURE_GOOGLE:
                String fotoUsuarioGog = firebaseUser.getPhotoUrl().toString();
                String nombreGog = firebaseUser.getDisplayName();
                String tipoSesionGog = Constantes.INICIO_SESION_GOOGLE;
                if (view != null) {
                    view.mostrarDataPerfil(fotoUsuarioGog, nombreGog, tipoSesionGog);
                    view.ocultarButtonIniciar();
                }
                break;
            case Constantes.KEY_SECURE_ANONIMO:
                String fotoUsuarioAnonimo = null;
                String nombreAnonimo = firebaseUser.getEmail();
                String tipoSesionAnonimo = Constantes.INICIO_SESION_ANONIMO;
                if (view != null) {
                    view.mostrarDataPerfil(fotoUsuarioAnonimo, nombreAnonimo, tipoSesionAnonimo);
                    view.ocultarButtonIniciar();
                }

                break;
            case Constantes.KEY_SIN_USUARIO:
                String fotoUsuarioSinUser = null;
                String nombreSinUser = "Sin Usuario";
                String tipoSesionSinUser = Constantes.INICIO_SIN_USUARIO;
                if (view != null) {
                    view.mostrarDataPerfil(fotoUsuarioSinUser, nombreSinUser, tipoSesionSinUser);
                    view.mostrarButtonIniciar();
                }

                break;
        }
    }

    SecurePreferences securePreferences;

    @Override
    public void onSecurePreferences(SecurePreferences securePreferences) {
        this.securePreferences = securePreferences;
        if (view != null) view.initBotomBar();
        String codigoUsuario = securePreferences.getString(Constantes.KEY_CODIGO_USUARIO);
        Log.d(TAG, "codigoUsuario : " + codigoUsuario);
        initValidarRolUsuario(codigoUsuario);
    }



    @Override
    public void onNavigationItemSelected(Menu item, String tipoEstado) {

        Log.d(TAG, "tipoEstado : " + tipoEstado);
        switch (tipoEstado) {
            case Constantes.ROL_USUARIO_ADMIN_GENERAL:
                if (view != null) view.mostrarMenuNavigationAdminGeneral(item);
                break;
            case Constantes.ROL_USUARIO_ADMIN_RESTAURANTE:
                if (view != null) view.mostrarMenuNavigationAdminRestaurante(item);
                break;
            case Constantes.ROL_USUARIO_NORMAL:
                if (view != null) view.deshabilitarDrawerNavigation(false);
                break;
            default:
                if (view != null) view.deshabilitarDrawerNavigation(false);
                break;

        }
    }
}
