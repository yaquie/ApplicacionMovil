package express.deli.cosema.deliexpressapp.adminRest.categoria.fragCategorias;

import android.content.res.Resources;

import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.adminRest.categoria.entidad.CategoriaUi;
import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.fragment.BaseFragmentPresenterImpl;

public class FCategoriasPresenterImpl extends BaseFragmentPresenterImpl<FCategoriasView> implements FCategoriasPresenter {

    public static final String TAG = FCategoriasPresenterImpl.class.getSimpleName();

    public FCategoriasPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onActivityCreated() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (view != null) view.ocultarProgressBar();
    }

    @Override
    public void onAgregarCategoriaNueva(String nombreCategoria) {
        if (nombreCategoria == null) {
            if (view != null) view.mostrarMensaje(res.getString(R.string.validarCategoria));
            return;
        }
        CategoriaUi categoriaUi = new CategoriaUi();
        categoriaUi.setNombreCategoria(nombreCategoria);
        if (view != null) view.agregarCategoria(categoriaUi);
    }
}
