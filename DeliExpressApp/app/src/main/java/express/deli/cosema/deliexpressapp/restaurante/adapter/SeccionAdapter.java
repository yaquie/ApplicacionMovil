package express.deli.cosema.deliexpressapp.restaurante.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.restaurante.adapter.holder.SeccionHolder;
import express.deli.cosema.deliexpressapp.restaurante.entidad.SeccionUi;
import express.deli.cosema.deliexpressapp.restaurante.listener.RestauranteListener;

public class SeccionAdapter extends RecyclerView.Adapter<SeccionHolder> {

    private List<SeccionUi> seccionUiList;

    private RestauranteListener listener;

    public SeccionAdapter(List<SeccionUi> seccionUiList, RestauranteListener listener) {
        this.seccionUiList = seccionUiList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SeccionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seccion_restaurantes, parent, false);
        return new SeccionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeccionHolder holder, int position) {
        SeccionUi seccionUi = seccionUiList.get(position);
        holder.bind(seccionUi,listener);
    }

    @Override
    public int getItemCount() {
        if (seccionUiList == null) return 0;
        return seccionUiList.size();
    }


    public void setMostrarLista(List<SeccionUi> seccionUis) {
        this.seccionUiList.clear();
        this.seccionUiList.addAll(seccionUis);
        notifyDataSetChanged();
    }
}
