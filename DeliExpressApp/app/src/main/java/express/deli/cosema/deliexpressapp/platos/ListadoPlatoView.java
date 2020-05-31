package express.deli.cosema.deliexpressapp.platos;

import java.util.List;

import express.deli.cosema.deliexpressapp.base.activity.BaseActivityView;
import express.deli.cosema.deliexpressapp.platos.entidad.PlatosUi;

public interface ListadoPlatoView extends BaseActivityView<ListadoPlatoPresenter> {
    void mostraListaPlatos(List<PlatosUi> platosUis);
}
