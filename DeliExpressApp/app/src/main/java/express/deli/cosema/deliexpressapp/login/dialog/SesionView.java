package express.deli.cosema.deliexpressapp.login.dialog;


import express.deli.cosema.deliexpressapp.base.BaseView;


public interface SesionView extends BaseView<SesionPresenter> {
    void mostrarTitulo(String titulo1);

    void initInicioRegistro(String email, String clave);

    void initInicioSesion(String email, String clave);

    void mostrarMensaje(String message);

    void initPreferences();

    void initStartMainActivity(String tipoSessiones);
}
