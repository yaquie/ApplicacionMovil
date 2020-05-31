package express.deli.cosema.deliexpressapp.adminRest.categoria.fragCategorias;

import express.deli.cosema.deliexpressapp.adminRest.categoria.entidad.CategoriaUi;
import express.deli.cosema.deliexpressapp.base.activity.BaseActivityView;

public interface FCategoriasView extends BaseActivityView<FCategoriasPresenter> {
    void mostrarMensaje(String mensaje);

    void agregarCategoria(CategoriaUi categoriaUi);
}
