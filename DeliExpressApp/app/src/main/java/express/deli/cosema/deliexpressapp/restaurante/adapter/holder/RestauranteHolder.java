package express.deli.cosema.deliexpressapp.restaurante.adapter.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.restaurante.entidad.RestauranteUi;
import express.deli.cosema.deliexpressapp.restaurante.listener.RestauranteListener;

public class RestauranteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //

    @BindView(R.id.imageViewRestaurante)
    ImageView imageViewRestaurante;
    @BindView(R.id.textViewPuntos)
    TextView textViewPuntos;
    @BindView(R.id.textViewNombreRestaurante)
    TextView textViewNombreRestaurante;
    @BindView(R.id.textViewDireccion)
    TextView textViewDireccion;
    @BindView(R.id.textViewPrecio)
    TextView textViewPrecioPlatoPorPersona;
    @BindView(R.id.cardViewRest)
    CardView cardViewRest;
    private RestauranteListener restauranteListener;
    private RestauranteUi restauranteUi;

    public RestauranteHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        cardViewRest.setOnClickListener(this);
    }

    public void bind(RestauranteUi restauranteUi, RestauranteListener restauranteListener) {
        this.restauranteListener = restauranteListener;
        this.restauranteUi = restauranteUi;
        textViewPuntos.setText(restauranteUi.getPuntosRestaurante());
        textViewNombreRestaurante.setText(restauranteUi.getNombreRestaurante());
        textViewDireccion.setText(restauranteUi.getDireccionRestaurante());
        textViewPrecioPlatoPorPersona.setText("S/." + restauranteUi.getPlatoPrecioPersona() + " por persona");
        Glide.with(itemView.getContext()).load(restauranteUi.getImageRestaurante()).into(imageViewRestaurante);
    }

    @Override
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.cardViewRest:
                restauranteListener.onClickRestaurante(restauranteUi);
                break;
        }

    }
}
