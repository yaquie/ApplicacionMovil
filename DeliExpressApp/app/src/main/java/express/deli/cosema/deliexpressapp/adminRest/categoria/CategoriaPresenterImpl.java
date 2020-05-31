package express.deli.cosema.deliexpressapp.adminRest.categoria;

import android.content.res.Resources;

import express.deli.cosema.deliexpressapp.adminRest.categoria.entidad.CategoriaUi;
import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.activity.BaseActivityPresenterImpl;

public class CategoriaPresenterImpl extends BaseActivityPresenterImpl<CategoriaView> implements CategoriaPresenter {

    public static final String TAG = CategoriaPresenterImpl.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public CategoriaPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClickAgregarCategoria(String texto) {
        /*CategoriaUi categoriaUi = new CategoriaUi();
        categoriaUi.setNombreCategoria(texto);
        if (view != null) view.agregarCategoria(categoriaUi);*/
    }
}
