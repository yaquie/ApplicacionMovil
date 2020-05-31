package express.deli.cosema.deliexpressapp.adminRest.restaurante;

import android.content.res.Resources;

import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.activity.BaseActivityPresenterImpl;

public class RestaurantePresenterImpl extends BaseActivityPresenterImpl<RestauranteView> implements RestaurantePresenter {

    public static final String TAG = RestaurantePresenterImpl.class.getSimpleName();

    public RestaurantePresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }
}
