package express.deli.cosema.deliexpressapp.platos.registro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import butterknife.ButterKnife;
import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.UseCaseThreadPoolScheduler;
import express.deli.cosema.deliexpressapp.base.activity.BaseActivity;

public class RegistroPlatosActivity extends BaseActivity<RegistroPlatosView, RegistroPlatosPresenter> implements RegistroPlatosView {

    public static final String TAG = RegistroPlatosActivity.class.getSimpleName();

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected RegistroPlatosPresenter getPresenter() {
        return new RegistroPlatosPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected RegistroPlatosView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.registro_plato_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }
}
