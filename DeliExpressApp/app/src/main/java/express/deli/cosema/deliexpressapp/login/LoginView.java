package express.deli.cosema.deliexpressapp.login;

import express.deli.cosema.deliexpressapp.base.activity.BaseActivityView;

public interface LoginView extends BaseActivityView<LoginPresenter> {
    void mostrarMensajeError(String string);

    void initStartMainActivity(String tipoSessiones);




}
