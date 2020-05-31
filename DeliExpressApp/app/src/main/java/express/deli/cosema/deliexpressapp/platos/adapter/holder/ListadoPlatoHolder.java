package express.deli.cosema.deliexpressapp.platos.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.platos.entidad.PlatosUi;

public class ListadoPlatoHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.imageView2)
    ImageView imageViewPlato;

    @BindView(R.id.textView5)
    TextView textViewNombrePlato;

    @BindView(R.id.idtypeComb)
    TextView textViewTipoComida;

    @BindView(R.id.idSettings)
    TextView textViewTipoCategoria;

    @BindView(R.id.textViewDescripcion)
    TextView textViewDescripcion;

    public ListadoPlatoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(PlatosUi platosUi) {
        Glide.with(itemView.getContext()).load(platosUi.getImagenPlato()).into(imageViewPlato);
        textViewNombrePlato.setText(platosUi.getNombrePlato().toUpperCase());
        textViewTipoComida.setText(platosUi.getTipoPlato());
        textViewTipoCategoria.setText(platosUi.getTipoCategoria());
        textViewDescripcion.setText(platosUi.getDescripcionPlato());
    }
}
