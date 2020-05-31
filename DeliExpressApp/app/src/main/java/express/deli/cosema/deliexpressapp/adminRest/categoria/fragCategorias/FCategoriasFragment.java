package express.deli.cosema.deliexpressapp.adminRest.categoria.fragCategorias;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.adminRest.categoria.entidad.CategoriaUi;
import express.deli.cosema.deliexpressapp.adminRest.categoria.fragCategorias.adapter.FCategoriasAdapter;
import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.UseCaseThreadPoolScheduler;
import express.deli.cosema.deliexpressapp.base.fragment.BaseFragment;

public class FCategoriasFragment extends BaseFragment<FCategoriasView, FCategoriasPresenter> implements FCategoriasView {

    public static final String TAG = FCategoriasFragment.class.getSimpleName();

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.editTextCategoria)
    EditText editTextCategoria;

    @BindView(R.id.reciclador)
    RecyclerView reciclador;

    FCategoriasAdapter categoriaAdapter;

    public static FCategoriasFragment newInstance() {
        Bundle bundle = new Bundle();
        FCategoriasFragment fragment = new FCategoriasFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected FCategoriasPresenter getPresenter() {
        return new FCategoriasPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected FCategoriasView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fcategorias_fragment, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        categoriaAdapter = new FCategoriasAdapter(new ArrayList<CategoriaUi>());
        reciclador.setLayoutManager(new LinearLayoutManager(getActivity()));
        reciclador.setHasFixedSize(true);
        reciclador.setAdapter(categoriaAdapter);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }


    @OnClick({R.id.fabCategoria})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.fabCategoria:
                String nombreCategoria = editTextCategoria.getText().toString();
                presenter.onAgregarCategoriaNueva(nombreCategoria);
                break;
        }
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(getActivity(),mensaje,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void agregarCategoria(CategoriaUi categoriaUi) {
        categoriaAdapter.agregarItem(categoriaUi);
    }
}
