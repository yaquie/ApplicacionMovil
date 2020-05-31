package express.deli.cosema.deliexpressapp.adminRest.restaurante;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.UseCaseThreadPoolScheduler;
import express.deli.cosema.deliexpressapp.base.activity.BaseActivity;

public class RestauranteActivity extends BaseActivity<RestauranteView, RestaurantePresenter> implements RestauranteView {

    public static final String TAG = RestauranteActivity.class.getSimpleName();

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected RestaurantePresenter getPresenter() {
        return new RestaurantePresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),getResources());
    }

    @Override
    protected RestauranteView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return null;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_restaurante);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }
}
