package express.deli.cosema.deliexpressapp.platos.registro;

import android.content.res.Resources;

import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.activity.BaseActivityPresenterImpl;

public class RegistroPlatosPresenterImpl extends BaseActivityPresenterImpl<RegistroPlatosView> implements RegistroPlatosPresenter {

    public static final String TAG = RegistroPlatosPresenterImpl.class.getSimpleName();

    public RegistroPlatosPresenterImpl(UseCaseHandler handler, Resources res) {
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
