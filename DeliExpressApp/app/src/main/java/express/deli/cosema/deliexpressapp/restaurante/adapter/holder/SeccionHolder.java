package express.deli.cosema.deliexpressapp.restaurante.adapter.holder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.restaurante.adapter.RestauranteAdapter;
import express.deli.cosema.deliexpressapp.restaurante.entidad.SeccionUi;
import express.deli.cosema.deliexpressapp.restaurante.listener.RestauranteListener;

public class SeccionHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.textViewSeccion)
    TextView textViewSeccion;
    @BindView(R.id.recicladorRestaurantes)
    RecyclerView reciclador;
    @BindView(R.id.textViewVerMas)
    TextView textViewVerMas;
    private RestauranteListener listener;
    private SeccionUi seccionUi;


    public SeccionHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        textViewVerMas.setOnClickListener(this);
    }

    public void bind(SeccionUi seccionUi, RestauranteListener listener) {
        this.listener = listener;
        this.seccionUi = seccionUi;
        textViewSeccion.setText(seccionUi.getNombreSeccion());
        RestauranteAdapter restauranteAdapter = new RestauranteAdapter(seccionUi.getRestauranteUi());
        reciclador.setHasFixedSize(true);
        reciclador.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL,
                false));
        reciclador.setAdapter(restauranteAdapter);
        restauranteAdapter.setListener(listener);
    }

    @Override
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.textViewVerMas:
                listener.onClickSeccion(seccionUi);
                break;
        }

    }
}
