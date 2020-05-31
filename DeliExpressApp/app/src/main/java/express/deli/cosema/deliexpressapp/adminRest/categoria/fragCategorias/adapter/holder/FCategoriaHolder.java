package express.deli.cosema.deliexpressapp.adminRest.categoria.fragCategorias.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.adminRest.categoria.entidad.CategoriaUi;

public class FCategoriaHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.textViewDescripcion)
    TextView textViewCategoria;

    public FCategoriaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(CategoriaUi categoriaUi) {
        textViewCategoria.setText(categoriaUi.getNombreCategoria());
    }
}
