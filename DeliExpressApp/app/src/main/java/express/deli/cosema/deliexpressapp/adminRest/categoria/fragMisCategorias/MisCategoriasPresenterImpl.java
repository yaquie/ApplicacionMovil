package express.deli.cosema.deliexpressapp.adminRest.categoria.fragMisCategorias;

import android.content.res.Resources;

import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.fragment.BaseFragmentPresenterImpl;

public class MisCategoriasPresenterImpl extends BaseFragmentPresenterImpl<MisCategoriasView> implements MisCategoriasPresenter {


    public MisCategoriasPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void onActivityCreated() {

    }
}
