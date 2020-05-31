package express.deli.cosema.deliexpressapp.platos.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.platos.adapter.holder.ListadoPlatoHolder;
import express.deli.cosema.deliexpressapp.platos.entidad.PlatosUi;

public class ListadoPlatoAdapter extends RecyclerView.Adapter<ListadoPlatoHolder> {

    private List<PlatosUi> platosUiList;

    public ListadoPlatoAdapter(List<PlatosUi> platosUiList) {
        this.platosUiList = platosUiList;
    }


    @NonNull
    @Override
    public ListadoPlatoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_platos, parent, false);
        return new ListadoPlatoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListadoPlatoHolder holder, int position) {
        PlatosUi platosUi = platosUiList.get(position);
        holder.bind(platosUi);
    }

    @Override
    public int getItemCount() {
        if (platosUiList == null) return 0;
        return platosUiList.size();
    }

    public void setMostrarListaPlatos(List<PlatosUi> mostrarListaPlatos) {
        this.platosUiList.clear();
        this.platosUiList.addAll(mostrarListaPlatos);
        notifyDataSetChanged();
    }
}
