package express.deli.cosema.deliexpressapp.favoritos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.UseCaseThreadPoolScheduler;
import express.deli.cosema.deliexpressapp.base.fragment.BaseFragment;


public class FavoritosFragment extends BaseFragment<FavoritosView, FavoritosPresenter> implements FavoritosView {

    public static final String TAG = FavoritosFragment.class.getSimpleName();

    public static FavoritosFragment newInstance() {
        FavoritosFragment favoritosFragment = new FavoritosFragment();
        Bundle b = new Bundle();
        favoritosFragment.setArguments(b);
        return favoritosFragment;
    }


    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected FavoritosPresenter getPresenter() {
        return new FavoritosPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources());
    }

    @Override
    protected FavoritosView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.favoritos_fragment,container,false);
        return view;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }
}
