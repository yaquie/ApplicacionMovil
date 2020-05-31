package express.deli.cosema.deliexpressapp.restaurante;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.UseCaseThreadPoolScheduler;
import express.deli.cosema.deliexpressapp.base.fragment.BaseFragment;
import express.deli.cosema.deliexpressapp.restaurante.adapter.SeccionAdapter;
import express.deli.cosema.deliexpressapp.restaurante.detalles.DetallesActivity;
import express.deli.cosema.deliexpressapp.restaurante.entidad.RestauranteUi;
import express.deli.cosema.deliexpressapp.restaurante.entidad.SeccionUi;
import express.deli.cosema.deliexpressapp.restaurante.listener.RestauranteListener;
import express.deli.cosema.deliexpressapp.restaurante.source.RestauranteRepository;
import express.deli.cosema.deliexpressapp.restaurante.source.remote.RestauranteRemote;
import express.deli.cosema.deliexpressapp.restaurante.useCase.MotrarListaRestSec;
import express.deli.cosema.deliexpressapp.utils.Constantes;

public class RestauranteFragment extends BaseFragment<RestauranteView, RestaurantePresenter> implements RestauranteView, RestauranteListener {


    public static final String TAG = RestauranteFragment.class.getSimpleName();
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.reciclador)
    RecyclerView reciclador;
    private SeccionAdapter restauranteAdapter;

    public static RestauranteFragment newInstance() {
        RestauranteFragment restauranteFragment = new RestauranteFragment();
        Bundle b = new Bundle();
        restauranteFragment.setArguments(b);
        return restauranteFragment;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected RestaurantePresenter getPresenter() {
        RestauranteRepository restauranteRepository = RestauranteRepository.getmInstance(new RestauranteRemote());
        return new RestaurantePresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new MotrarListaRestSec(restauranteRepository));
    }

    @Override
    protected RestauranteView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.restaurante_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }

    private void initAdapter() {
        restauranteAdapter = new SeccionAdapter(new ArrayList<SeccionUi>(), this);
        reciclador.setHasFixedSize(true);
        reciclador.setLayoutManager(new LinearLayoutManager(getActivity()));
        reciclador.setAdapter(restauranteAdapter);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void mostrarLista(List<SeccionUi> seccionUis) {
        restauranteAdapter.setMostrarLista(seccionUis);
    }

    @Override
    public void onClickRestaurante(RestauranteUi restauranteUi) {
        Intent intent = new Intent(getActivity(), DetallesActivity.class);
        intent.putExtra(Constantes.EXTRA_RESTAURANTE, Parcels.wrap(restauranteUi));
        startActivity(intent);
        Log.d(TAG, "onClickRestaurante : " + restauranteUi.getNombreRestaurante() +
                "onClickSeccion : " + restauranteUi.getSeccionUi().getNombreSeccion());
    }

    @Override
    public void onClickSeccion(SeccionUi seccionUi) {
        Log.d(TAG, "onClickSeccion : " + seccionUi.getNombreSeccion());
    }
}
