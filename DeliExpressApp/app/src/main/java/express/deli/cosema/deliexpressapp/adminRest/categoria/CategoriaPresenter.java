package express.deli.cosema.deliexpressapp.adminRest.categoria;

import express.deli.cosema.deliexpressapp.base.activity.BaseActivityPresenter;

public interface CategoriaPresenter extends BaseActivityPresenter<CategoriaView>{
    void onClickAgregarCategoria(String texto);
}
