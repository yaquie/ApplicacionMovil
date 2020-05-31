package express.deli.cosema.deliexpressapp.platos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.UseCaseThreadPoolScheduler;
import express.deli.cosema.deliexpressapp.base.activity.BaseActivity;
import express.deli.cosema.deliexpressapp.platos.adapter.ListadoPlatoAdapter;
import express.deli.cosema.deliexpressapp.platos.entidad.PlatosUi;
import express.deli.cosema.deliexpressapp.platos.registro.RegistroPlatosActivity;

public class ListadoPlatoActivity extends BaseActivity<ListadoPlatoView, ListadoPlatoPresenter> implements ListadoPlatoView {

    public static final String TAG = ListadoPlatoActivity.class.getSimpleName();
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.reciclador)
    RecyclerView reciclador;

    private ListadoPlatoAdapter listadoPlatoAdapter;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected ListadoPlatoPresenter getPresenter() {
        return new ListadoPlatoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected ListadoPlatoView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.listado_plato_activity);
        ButterKnife.bind(this);
        listadoPlatoAdapter = new ListadoPlatoAdapter(new ArrayList<PlatosUi>());
        reciclador.setLayoutManager(new LinearLayoutManager(this));
        reciclador.setHasFixedSize(true);
        reciclador.setAdapter(listadoPlatoAdapter);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @OnClick({R.id.fab})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.fab:
                initStartActivityRegistroPlato();
                break;
        }
    }

    private void initStartActivityRegistroPlato() {
        Intent intent = new Intent(this, RegistroPlatosActivity.class);
        startActivity(intent);
    }

    @Override
    public void mostraListaPlatos(List<PlatosUi> platosUis) {
        listadoPlatoAdapter.setMostrarListaPlatos(platosUis);
    }
}
