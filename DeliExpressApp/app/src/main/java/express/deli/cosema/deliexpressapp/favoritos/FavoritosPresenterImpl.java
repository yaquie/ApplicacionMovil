package express.deli.cosema.deliexpressapp.favoritos;

import android.content.res.Resources;

import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.fragment.BaseFragmentPresenterImpl;


public class FavoritosPresenterImpl extends BaseFragmentPresenterImpl<FavoritosView> implements FavoritosPresenter  {
    public FavoritosPresenterImpl(UseCaseHandler handler, Resources res) {
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
