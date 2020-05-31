package express.deli.cosema.deliexpressapp.adminRest.categoria.fragMisCategorias;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.UseCaseThreadPoolScheduler;
import express.deli.cosema.deliexpressapp.base.fragment.BaseFragment;

public class MisCategoriasFragment extends BaseFragment<MisCategoriasView, MisCategoriasPresenter> implements MisCategoriasView {

    public static final String TAG = MisCategoriasFragment.class.getSimpleName();


    public static MisCategoriasFragment newInstance() {
        Bundle bundle = new Bundle();
        MisCategoriasFragment fragment = new MisCategoriasFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected MisCategoriasPresenter getPresenter() {
        return new MisCategoriasPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected MisCategoriasView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.mis_categorias_fragment, container, false);
        return view;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }
}
