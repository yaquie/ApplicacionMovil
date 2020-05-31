package express.deli.cosema.deliexpressapp.platos;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.activity.BaseActivityPresenterImpl;
import express.deli.cosema.deliexpressapp.platos.entidad.PlatosUi;

public class ListadoPlatoPresenterImpl extends BaseActivityPresenterImpl<ListadoPlatoView> implements ListadoPlatoPresenter {

    public static final String TAG = ListadoPlatoPresenterImpl.class.getSimpleName();


    public ListadoPlatoPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onStart() {
        super.onStart();
        initDataPlato();

    }

    private void initDataPlato() {

        List<PlatosUi> platosUis = new ArrayList<>();

        PlatosUi platosUiCeviche = new PlatosUi();
        platosUiCeviche.setNombrePlato("maremoto");
        platosUiCeviche.setDescripcionPlato("Ceviche preparado a base de pescado y mixtura de pulpo,  almejas,caracoles,langostinos,calamares y\n" +
                "conchas de abanico.");
        platosUiCeviche.setImagenPlato("http://www.laylita.com/recipes/wp-content/uploads/2008/07/ramon-fish-ceviche-1.jpg");
        platosUiCeviche.setTipoPlato("Criollas");
        platosUiCeviche.setTipoCategoria("Ceviche");
        platosUis.add(platosUiCeviche);

        PlatosUi platosUiCeviche2 = new PlatosUi();
        platosUiCeviche2.setNombrePlato("tsunami");
        platosUiCeviche2.setDescripcionPlato("El Gordo... ha autorizado que le ofrezcan a usted un Maremoto mas movido. Este, bajo la clave Tsunami, le podrá ser servido con los adicionales de frescas conchas negras y una medida generosa de leche de pantera.");
        platosUiCeviche2.setImagenPlato("http://www.laylita.com/recipes/wp-content/uploads/2008/07/ramon-fish-ceviche-1.jpg");
        platosUiCeviche2.setTipoPlato("Criollas");
        platosUiCeviche2.setTipoCategoria("Ceviche");
        platosUis.add(platosUiCeviche2);

        PlatosUi platosUiCeviche3 = new PlatosUi();
        platosUiCeviche3.setNombrePlato("mixto");
        platosUiCeviche3.setDescripcionPlato("Un ceviche peruano con pulpo, camarón y pescado cocidos con una rica marinada de limón y ...");
        platosUiCeviche3.setImagenPlato("https://cdn.kiwilimon.com/recetaimagen/26255/th5-640x426-23270.jpg");
        platosUiCeviche3.setTipoPlato("Criollas");
        platosUiCeviche3.setTipoCategoria("Ceviche");
        platosUis.add(platosUiCeviche3);

        PlatosUi platosUiPato= new PlatosUi();
        platosUiPato.setNombrePlato("Arroz con pato");
        platosUiPato.setDescripcionPlato("ligeramente cremoso, dependiendo de la receta. Es de color verde oscuro debido a las yerbas que se usan.");
        platosUiPato.setImagenPlato("http://agenciaperu.net/wp-content/uploads/2015/05/arrozconpato_290515-660x330.jpg");
        platosUiPato.setTipoPlato("Criollas");
        platosUiPato.setTipoCategoria("Segundos");
        platosUis.add(platosUiPato);

        if(view!=null)view.mostraListaPlatos(platosUis);


    }
}
