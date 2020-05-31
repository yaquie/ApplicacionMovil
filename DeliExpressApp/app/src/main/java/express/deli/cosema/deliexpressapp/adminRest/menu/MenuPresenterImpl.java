package express.deli.cosema.deliexpressapp.adminRest.menu;

import android.content.res.Resources;

import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.activity.BaseActivityPresenterImpl;

public class MenuPresenterImpl extends BaseActivityPresenterImpl<MenuView> implements MenuPresenter {

    public MenuPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void onBackPressed() {

    }
}
