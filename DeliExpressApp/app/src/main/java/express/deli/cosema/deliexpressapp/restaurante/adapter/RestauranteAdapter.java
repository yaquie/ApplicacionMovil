package express.deli.cosema.deliexpressapp.restaurante.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.restaurante.adapter.holder.RestauranteHolder;
import express.deli.cosema.deliexpressapp.restaurante.entidad.RestauranteUi;
import express.deli.cosema.deliexpressapp.restaurante.listener.RestauranteListener;

public class RestauranteAdapter extends RecyclerView.Adapter<RestauranteHolder> {

    private List<RestauranteUi> restauranteUiList;
    private RestauranteListener listener;


    public RestauranteAdapter(List<RestauranteUi> restauranteUiList) {
        this.restauranteUiList = restauranteUiList;

    }

    @NonNull
    @Override
    public RestauranteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurantes, parent, false);
        return new RestauranteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestauranteHolder holder, int position) {
        RestauranteUi restauranteUi = restauranteUiList.get(position);
        holder.bind(restauranteUi,listener);

    }

    @Override
    public int getItemCount() {
        if (restauranteUiList == null) return 0;
        return restauranteUiList.size();
    }

    public void setListener(RestauranteListener listener) {
        this.listener = listener;
    }
}
