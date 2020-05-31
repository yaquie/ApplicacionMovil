package express.deli.cosema.deliexpressapp.main;

import android.view.Menu;
import android.view.MenuItem;

import express.deli.cosema.deliexpressapp.base.activity.BaseActivityView;

public interface MainView extends BaseActivityView<MainPresenter> {
    void regresarLogin();

    void initBotomBar();

    void ocultarVistas();

    void mostrarVistas();

    void ocultarContentPerfil();

    void mostrarContentPerfil();

    void deshabilitarDrawerNavigation(boolean boleean);

    void habilitarDrawerNavigation(boolean boleean);

    void mostrarDataPerfil(String foto, String nombre, String tipoSesion);


    void mostrarButtonIniciar();

    void ocultarButtonIniciar();

    /*Mostrar Navigation*/
    void mostrarDatosNavigation();

    void ocultarDatosNavigation();
    /*Ocultar Navigation*/

    /*Mostrar Item Segun Roles*/
    void mostrarMenuNavigationAdminGeneral(Menu item);

    void mostrarMenuNavigationAdminRestaurante(Menu item);

    void validarShowNavigation(String tipoEstado);

    void mostrarDataPerfilNavigation(String fotoUsuarioFb, String nombreFb, String tipoSesionFb);
}
