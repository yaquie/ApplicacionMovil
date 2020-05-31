package express.deli.cosema.deliexpressapp.adminRest.categoria.fragCategorias;

import express.deli.cosema.deliexpressapp.base.fragment.BaseFragmentPresenter;

public interface FCategoriasPresenter extends BaseFragmentPresenter<FCategoriasView> {
    void onAgregarCategoriaNueva(String nombreCategoria);
}
