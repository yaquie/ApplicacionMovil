package express.deli.cosema.deliexpressapp.adminRest.categoria.fragCategorias.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.adminRest.categoria.entidad.CategoriaUi;
import express.deli.cosema.deliexpressapp.adminRest.categoria.fragCategorias.adapter.holder.FCategoriaHolder;

public class FCategoriasAdapter extends RecyclerView.Adapter<FCategoriaHolder> {

    private List<CategoriaUi> categoriaUiList;

    public FCategoriasAdapter(List<CategoriaUi> categoriaUiList) {
        this.categoriaUiList = categoriaUiList;
    }

    @NonNull
    @Override
    public FCategoriaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categoria, parent, false);
        return new FCategoriaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FCategoriaHolder holder, int position) {
        CategoriaUi categoriaUi = categoriaUiList.get(position);
        holder.bind(categoriaUi);
    }

    @Override
    public int getItemCount() {
        if (categoriaUiList == null) return 0;
        return categoriaUiList.size();
    }

    public void agregarItem(CategoriaUi categoriaUi) {
        this.categoriaUiList.add(categoriaUi);
        notifyItemInserted(getItemCount() - 1);
    }

    public void mostrarLista(List<CategoriaUi> categoriaUi) {
        this.categoriaUiList.clear();
        this.categoriaUiList.addAll(categoriaUi);
        notifyDataSetChanged();
    }


    public void editarItem(CategoriaUi clienteEstadosUi) {
        int position = this.categoriaUiList.indexOf(clienteEstadosUi);
        if (position != -1) {
            this.categoriaUiList.set(position, clienteEstadosUi);
            notifyItemChanged(position);
        }
    }

    public void eliminarItem(CategoriaUi categoriaUi) {
        int position = this.categoriaUiList.indexOf(categoriaUi);
        if (position != -1) {
            this.categoriaUiList.remove(categoriaUi);
            notifyItemRemoved(position);
        }
    }
}
