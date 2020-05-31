package express.deli.cosema.deliexpressapp.restaurante;

import android.content.res.Resources;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import express.deli.cosema.deliexpressapp.base.UseCase;
import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.fragment.BaseFragmentPresenterImpl;
import express.deli.cosema.deliexpressapp.restaurante.entidad.RestauranteUi;
import express.deli.cosema.deliexpressapp.restaurante.entidad.SeccionUi;
import express.deli.cosema.deliexpressapp.restaurante.useCase.MotrarListaRestSec;

public class RestaurantePresenterImpl extends BaseFragmentPresenterImpl<RestauranteView> implements RestaurantePresenter {
    public static final String TAG = RestaurantePresenterImpl.class.getSimpleName();

    private MotrarListaRestSec motrarListaRestSec;

    public RestaurantePresenterImpl(UseCaseHandler handler, Resources res, MotrarListaRestSec motrarListaRestSec) {
        super(handler, res);
        this.motrarListaRestSec = motrarListaRestSec;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onActivityCreated() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        //if (view != null) view.mostrarLista(onObtenerdata());

    }

    @Override
    public void onStart() {
        super.onStart();
        initUseCaseLista();
    }

    private void initUseCaseLista() {
        if (view != null) view.mostrarProgressBar();
        handler.execute(motrarListaRestSec, new MotrarListaRestSec.RequestValues(),
                new UseCase.UseCaseCallback<MotrarListaRestSec.ResponseValue>() {
                    @Override
                    public void onSuccess(MotrarListaRestSec.ResponseValue response) {
                        if (response.getSeccionUiList() != null) {
                            if (view != null) {
                                view.ocultarProgressBar();
                                view.mostrarLista(response.getSeccionUiList());
                            }
                        }

                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "Lista Es Nula Papu");
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        //if (view != null) view.mostrarLista(onObtenerdata());
    }

    private List<SeccionUi> onObtenerdata() {
        List<SeccionUi> seccionUiList = new ArrayList<>();
        SeccionUi primeraSeccion = new SeccionUi();
        primeraSeccion.setIdSeccion("1");
        primeraSeccion.setNombreSeccion("Vivá Peru");
        primeraSeccion.setRestauranteUi(restauranteUiListPrimeraSeccion());
        seccionUiList.add(primeraSeccion);

        SeccionUi segundaSeccion = new SeccionUi();
        segundaSeccion.setIdSeccion("2");
        segundaSeccion.setNombreSeccion("Los Mejores buffets");
        segundaSeccion.setRestauranteUi(restauranteUiListSegundaSeccion());
        seccionUiList.add(segundaSeccion);

        SeccionUi terceraSeccion = new SeccionUi();
        terceraSeccion.setIdSeccion("3");
        terceraSeccion.setNombreSeccion("Cervecerias");
        terceraSeccion.setRestauranteUi(restauranteUiListTerceraSeccion());
        seccionUiList.add(terceraSeccion);

        SeccionUi cuartaSeccion = new SeccionUi();
        cuartaSeccion.setIdSeccion("3");
        cuartaSeccion.setNombreSeccion("Salida Romantica ");
        cuartaSeccion.setRestauranteUi(restauranteUiListCuartaSeccion());
        seccionUiList.add(cuartaSeccion);

        SeccionUi quintaSeccion = new SeccionUi();
        quintaSeccion.setIdSeccion("3");
        quintaSeccion.setNombreSeccion("Lo mas Reservadors");
        quintaSeccion.setRestauranteUi(restauranteUiListQuintaSeccion());
        seccionUiList.add(quintaSeccion);
        return seccionUiList;
    }

    private List<RestauranteUi> restauranteUiListQuintaSeccion() {
        List<RestauranteUi> restauranteUis = new ArrayList<>();
        RestauranteUi restauranteUiMiraFlores = new RestauranteUi();
        restauranteUiMiraFlores.setIdRestaurante("6");
        restauranteUiMiraFlores.setNombreRestaurante("Alzo");
        restauranteUiMiraFlores.setDireccionRestaurante("Surquillo");
        restauranteUiMiraFlores.setPuntosRestaurante("8.6");
        restauranteUiMiraFlores.setPlatoPrecioPersona("S/. 15.00");
        restauranteUiMiraFlores.setImageRestaurante("http://i67.tinypic.com/21ah34x.jpg");
        restauranteUis.add(restauranteUiMiraFlores);

        RestauranteUi restauranteUiMiraFlores2 = new RestauranteUi();
        restauranteUiMiraFlores2.setIdRestaurante("7");
        restauranteUiMiraFlores2.setNombreRestaurante("Ache Restaurante");
        restauranteUiMiraFlores2.setDireccionRestaurante("Miraflores");
        restauranteUiMiraFlores2.setPuntosRestaurante("8.3");
        restauranteUiMiraFlores2.setPlatoPrecioPersona("S/. 12.00");
        restauranteUiMiraFlores2.setImageRestaurante("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/q//4228/26546/restaurante_ache-restaurante_miraflores_398239_150095008455229_547617874_n.jpg");//----------
        restauranteUis.add(restauranteUiMiraFlores2);

        RestauranteUi restauranteUiSanIsidro = new RestauranteUi();
        restauranteUiSanIsidro.setIdRestaurante("3");
        restauranteUiSanIsidro.setNombreRestaurante("Asakusa");
        restauranteUiSanIsidro.setDireccionRestaurante("San Borja");
        restauranteUiSanIsidro.setPuntosRestaurante("8.0");
        restauranteUiSanIsidro.setPlatoPrecioPersona("S/. 12.00");
        restauranteUiSanIsidro.setImageRestaurante("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/q//4228/47292/restaurante_ache-restaurante_miraflores_carne.jpg");//----------
        restauranteUis.add(restauranteUiSanIsidro);

        RestauranteUi restauranteUiSanIsidro2 = new RestauranteUi();
        restauranteUiSanIsidro2.setIdRestaurante("4");
        restauranteUiSanIsidro2.setNombreRestaurante("Aromas Peruanos");
        restauranteUiSanIsidro2.setDireccionRestaurante("San Isidro");
        restauranteUiSanIsidro2.setPuntosRestaurante("7.6");
        restauranteUiSanIsidro2.setPlatoPrecioPersona("S/. 10.00");
        restauranteUiSanIsidro2.setImageRestaurante("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/q//4228/47401/restaurante_ache-restaurante_miraflores_interior.jpg");//----------
        restauranteUis.add(restauranteUiSanIsidro2);

        RestauranteUi restauranteUiSanIsidro3 = new RestauranteUi();
        restauranteUiSanIsidro3.setIdRestaurante("4");
        restauranteUiSanIsidro3.setNombreRestaurante("Cala");
        restauranteUiSanIsidro3.setDireccionRestaurante("Barranco");
        restauranteUiSanIsidro3.setPuntosRestaurante("7.0");
        restauranteUiSanIsidro3.setPlatoPrecioPersona("S/. 10.00");
        restauranteUiSanIsidro3.setImageRestaurante("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/q//4228/47404/restaurante_ache-restaurante_miraflores_sashimi_de_salmon.jpg");//----------
        restauranteUis.add(restauranteUiSanIsidro3);
        return restauranteUis;
    }

    private List<RestauranteUi> restauranteUiListCuartaSeccion() {
        List<RestauranteUi> restauranteUis = new ArrayList<>();

        RestauranteUi restauranteUiMiraFlores = new RestauranteUi();
        restauranteUiMiraFlores.setIdRestaurante("6");
        restauranteUiMiraFlores.setNombreRestaurante("Hotel B ");
        restauranteUiMiraFlores.setDireccionRestaurante("Barranco");
        restauranteUiMiraFlores.setPuntosRestaurante("8.6");
        restauranteUiMiraFlores.setPlatoPrecioPersona("S/. 15.00");
        restauranteUiMiraFlores.setImageRestaurante("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/r//5321/109435/restaurante_la-trastienda_barranco_jpg3.jpg");//----------
        restauranteUis.add(restauranteUiMiraFlores);

        RestauranteUi restauranteUiMiraFlores2 = new RestauranteUi();
        restauranteUiMiraFlores2.setIdRestaurante("7");
        restauranteUiMiraFlores2.setNombreRestaurante("La Terrazita (BITH Hotel)");
        restauranteUiMiraFlores2.setDireccionRestaurante("San Isidro");
        restauranteUiMiraFlores2.setPuntosRestaurante("8.3");
        restauranteUiMiraFlores2.setPlatoPrecioPersona("S/. 12.00");
        restauranteUiMiraFlores2.setImageRestaurante("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/r//5321/109411/restaurante_la-trastienda_barranco_varios_1.jpg");//----------
        restauranteUis.add(restauranteUiMiraFlores2);

        RestauranteUi restauranteUiSanIsidro = new RestauranteUi();
        restauranteUiSanIsidro.setIdRestaurante("3");
        restauranteUiSanIsidro.setNombreRestaurante("El Señorio de Sulco");
        restauranteUiSanIsidro.setDireccionRestaurante("San Isidro");
        restauranteUiSanIsidro.setPuntosRestaurante("8.0");
        restauranteUiSanIsidro.setPlatoPrecioPersona("S/. 12.00");
        restauranteUiSanIsidro.setImageRestaurante("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/r//5321/109415/restaurante_la-trastienda_barranco_sweet_roses.jpg");//----------
        restauranteUis.add(restauranteUiSanIsidro);

        RestauranteUi restauranteUiSanIsidro2 = new RestauranteUi();
        restauranteUiSanIsidro2.setIdRestaurante("4");
        restauranteUiSanIsidro2.setNombreRestaurante("Cala");
        restauranteUiSanIsidro2.setDireccionRestaurante("Barranco");
        restauranteUiSanIsidro2.setPuntosRestaurante("7.6");
        restauranteUiSanIsidro2.setPlatoPrecioPersona("S/. 10.00");
        restauranteUiSanIsidro2.setImageRestaurante("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/r//5321/109418/restaurante_la-trastienda_barranco_untitled-00001-2.jpg");//----------
        restauranteUis.add(restauranteUiSanIsidro2);

        RestauranteUi restauranteUiSanIsidro3 = new RestauranteUi();
        restauranteUiSanIsidro3.setIdRestaurante("4");
        restauranteUiSanIsidro3.setNombreRestaurante("La Forechetta");
        restauranteUiSanIsidro3.setDireccionRestaurante("Miraflores");
        restauranteUiSanIsidro3.setPuntosRestaurante("7.0");
        restauranteUiSanIsidro3.setPlatoPrecioPersona("S/. 10.00");
        restauranteUiSanIsidro3.setImageRestaurante("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/r//5321/109419/restaurante_la-trastienda_barranco_untitled-00009.jpg");//----------
        restauranteUis.add(restauranteUiSanIsidro3);
        return restauranteUis;
    }

    private List<RestauranteUi> restauranteUiListTerceraSeccion() {

        List<RestauranteUi> restauranteUis = new ArrayList<>();

        RestauranteUi restauranteUiMiraFlores = new RestauranteUi();
        restauranteUiMiraFlores.setIdRestaurante("6");
        restauranteUiMiraFlores.setNombreRestaurante("Bon Beef (Patio Panorama) ");
        restauranteUiMiraFlores.setDireccionRestaurante("La Molina");
        restauranteUiMiraFlores.setPuntosRestaurante("8.6");
        restauranteUiMiraFlores.setPlatoPrecioPersona("S/. 15.00");
        restauranteUiMiraFlores.setImageRestaurante("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/u//11278/109214/restaurante_lanacional-mall-aventura-plaza-santa-anita_santa-anita_20170308_172848.jpg");//----------
        restauranteUis.add(restauranteUiMiraFlores);

        RestauranteUi restauranteUiMiraFlores2 = new RestauranteUi();
        restauranteUiMiraFlores2.setIdRestaurante("7");
        restauranteUiMiraFlores2.setNombreRestaurante("Bon Beef(San Isidro)");
        restauranteUiMiraFlores2.setDireccionRestaurante("San Isidro");
        restauranteUiMiraFlores2.setPuntosRestaurante("8.3");
        restauranteUiMiraFlores2.setPlatoPrecioPersona("S/. 12.00");
        restauranteUiMiraFlores2.setImageRestaurante("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/u//11278/109105/restaurante_lanacional-mall-aventura-plaza-santa-anita_santa-anita_copia_de_exportimg_0468.jpg");//----------
        restauranteUis.add(restauranteUiMiraFlores2);

        RestauranteUi restauranteUiSanIsidro = new RestauranteUi();
        restauranteUiSanIsidro.setIdRestaurante("3");
        restauranteUiSanIsidro.setNombreRestaurante("Aromas Peruanas");
        restauranteUiSanIsidro.setDireccionRestaurante("San Isidro");
        restauranteUiSanIsidro.setPuntosRestaurante("8.0");
        restauranteUiSanIsidro.setPlatoPrecioPersona("S/. 12.00");
        restauranteUiSanIsidro.setImageRestaurante("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/r//5321/109415/restaurante_la-trastienda_barranco_sweet_roses.jpg");//----------
        restauranteUis.add(restauranteUiSanIsidro);

        RestauranteUi restauranteUiSanIsidro2 = new RestauranteUi();
        restauranteUiSanIsidro2.setIdRestaurante("4");
        restauranteUiSanIsidro2.setNombreRestaurante("305 Sur");
        restauranteUiSanIsidro2.setDireccionRestaurante("Barranco");
        restauranteUiSanIsidro2.setPuntosRestaurante("7.6");
        restauranteUiSanIsidro2.setPlatoPrecioPersona("S/. 10.00");
        restauranteUiSanIsidro2.setImageRestaurante("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/r//5321/109418/restaurante_la-trastienda_barranco_untitled-00001-2.jpg");//----------
        restauranteUis.add(restauranteUiSanIsidro2);

        RestauranteUi restauranteUiSanIsidro3 = new RestauranteUi();
        restauranteUiSanIsidro3.setIdRestaurante("4");
        restauranteUiSanIsidro3.setNombreRestaurante("El Salar, Bar de Maras ");
        restauranteUiSanIsidro3.setDireccionRestaurante("San Isidro");
        restauranteUiSanIsidro3.setPuntosRestaurante("7.0");
        restauranteUiSanIsidro3.setPlatoPrecioPersona("S/. 10.00");
        restauranteUiSanIsidro3.setImageRestaurante("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/r//5321/109419/restaurante_la-trastienda_barranco_untitled-00009.jpg");//----------
        restauranteUis.add(restauranteUiSanIsidro3);
        return restauranteUis;
    }

    private List<RestauranteUi> restauranteUiListSegundaSeccion() {
        List<RestauranteUi> restauranteUis = new ArrayList<>();

        RestauranteUi restauranteUiMiraFlores = new RestauranteUi();
        restauranteUiMiraFlores.setIdRestaurante("6");
        restauranteUiMiraFlores.setNombreRestaurante("El Bosque (Hotel Plaza del Bosque) ");
        restauranteUiMiraFlores.setDireccionRestaurante("Miraflores");
        restauranteUiMiraFlores.setPuntosRestaurante("8.6");
        restauranteUiMiraFlores.setPlatoPrecioPersona("S/. 15.00");
        restauranteUiMiraFlores.setImageRestaurante("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/w//3896/24463/restaurante_aromas-peruanos-restaurante_san-isidro_buffet_entradas_baja.jpg");//----------
        restauranteUis.add(restauranteUiMiraFlores);

        RestauranteUi restauranteUiMiraFlores2 = new RestauranteUi();
        restauranteUiMiraFlores2.setIdRestaurante("7");
        restauranteUiMiraFlores2.setNombreRestaurante("Bravo Restovar (Miraflores)");
        restauranteUiMiraFlores2.setDireccionRestaurante("Miraflores");
        restauranteUiMiraFlores2.setPuntosRestaurante("8.3");
        restauranteUiMiraFlores2.setPlatoPrecioPersona("S/. 12.00");
        restauranteUiMiraFlores2.setImageRestaurante("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/w//3896/24464/restaurante_aromas-peruanos-restaurante_san-isidro_buffet_rocoto1_baja.jpg");//----------
        restauranteUis.add(restauranteUiMiraFlores2);

        RestauranteUi restauranteUiSanIsidro = new RestauranteUi();
        restauranteUiSanIsidro.setIdRestaurante("3");
        restauranteUiSanIsidro.setNombreRestaurante("Aromas Peruanas");
        restauranteUiSanIsidro.setDireccionRestaurante("San Isidro");
        restauranteUiSanIsidro.setPuntosRestaurante("8.0");
        restauranteUiSanIsidro.setPlatoPrecioPersona("S/. 12.00");
        restauranteUiSanIsidro.setImageRestaurante("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/w//3896/24465/restaurante_aromas-peruanos-restaurante_san-isidro_fetuccini_en_salsa_huancaina_-_baja_resolucion.jp");//----------
        restauranteUis.add(restauranteUiSanIsidro);

        RestauranteUi restauranteUiSanIsidro2 = new RestauranteUi();
        restauranteUiSanIsidro2.setIdRestaurante("4");
        restauranteUiSanIsidro2.setNombreRestaurante("Aromas Peruanos");
        restauranteUiSanIsidro2.setDireccionRestaurante("San Isidro");
        restauranteUiSanIsidro2.setPuntosRestaurante("7.6");
        restauranteUiSanIsidro2.setPlatoPrecioPersona("S/. 10.00");
        restauranteUiSanIsidro2.setImageRestaurante("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/w//3896/24467/restaurante_aromas-peruanos-restaurante_san-isidro_causitas_de_lomo_saltado_para_face1.jpg");//----------
        restauranteUis.add(restauranteUiSanIsidro2);

        RestauranteUi restauranteUiSanIsidro3 = new RestauranteUi();
        restauranteUiSanIsidro3.setIdRestaurante("4");
        restauranteUiSanIsidro3.setNombreRestaurante("El Lobby del Pardo 41 ");
        restauranteUiSanIsidro3.setDireccionRestaurante("San Isidro");
        restauranteUiSanIsidro3.setPuntosRestaurante("7.0");
        restauranteUiSanIsidro3.setPlatoPrecioPersona("S/. 10.00");
        restauranteUiSanIsidro3.setImageRestaurante("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/w//3896/91377/restaurante_aromas-peruanos_san-isidro_imagen3.jpg");//----------
        restauranteUis.add(restauranteUiSanIsidro3);
        return restauranteUis;
    }

    private List<RestauranteUi> restauranteUiListPrimeraSeccion() {
        List<RestauranteUi> restauranteUis = new ArrayList<>();
        RestauranteUi restauranteUiMiraFlores = new RestauranteUi();
        restauranteUiMiraFlores.setIdRestaurante("1");
        restauranteUiMiraFlores.setNombreRestaurante("Costanero 700");
        restauranteUiMiraFlores.setDireccionRestaurante("Miraflores");
        restauranteUiMiraFlores.setPuntosRestaurante("8.6");
        restauranteUiMiraFlores.setPlatoPrecioPersona("S/. 15.00");
        restauranteUiMiraFlores.setImageRestaurante("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/g//4920/82697/restaurante_pescados-capitales-chacarilla_san-borja_dsc03608.jpg");//----------
        restauranteUis.add(restauranteUiMiraFlores);

        RestauranteUi restauranteUiMiraFlores2 = new RestauranteUi();
        restauranteUiMiraFlores2.setIdRestaurante("2");
        restauranteUiMiraFlores2.setNombreRestaurante("Fiesta Restaunrate Goumet");
        restauranteUiMiraFlores2.setDireccionRestaurante("Miraflores");
        restauranteUiMiraFlores2.setPuntosRestaurante("8.3");
        restauranteUiMiraFlores2.setPlatoPrecioPersona("S/. 12.00");
        restauranteUiMiraFlores2.setImageRestaurante("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/g//4920/32066/restaurante_pescados-capitales-chacarilla_san-borja_6g.jpg");//----------
        restauranteUis.add(restauranteUiMiraFlores2);

        RestauranteUi restauranteUiSanIsidro = new RestauranteUi();
        restauranteUiSanIsidro.setIdRestaurante("3");
        restauranteUiSanIsidro.setNombreRestaurante("Bravo Restobar");
        restauranteUiSanIsidro.setDireccionRestaurante("San Isidro");
        restauranteUiSanIsidro.setPuntosRestaurante("8.0");
        restauranteUiSanIsidro.setPlatoPrecioPersona("S/. 12.00");
        restauranteUiSanIsidro.setImageRestaurante("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/g//4920/32069/restaurante_pescados-capitales-chacarilla_san-borja_183044_10150194857353998_1628397_n.jpg");//----------
        restauranteUis.add(restauranteUiSanIsidro);

        RestauranteUi restauranteUiSanIsidro2 = new RestauranteUi();
        restauranteUiSanIsidro2.setIdRestaurante("4");
        restauranteUiSanIsidro2.setNombreRestaurante("Aromas Peruanos");
        restauranteUiSanIsidro2.setDireccionRestaurante("San Isidro");
        restauranteUiSanIsidro2.setPuntosRestaurante("7.6");
        restauranteUiSanIsidro2.setPlatoPrecioPersona("S/. 10.00");
        restauranteUiSanIsidro2.setImageRestaurante("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/g//4920/32070/restaurante_pescados-capitales-chacarilla_san-borja_183097_10150195064143998_5929748_n.jpg");//----------
        restauranteUis.add(restauranteUiSanIsidro2);

        RestauranteUi restauranteUiSanIsidro3 = new RestauranteUi();
        restauranteUiSanIsidro3.setIdRestaurante("4");
        restauranteUiSanIsidro3.setNombreRestaurante("Embarcadero 41 ");
        restauranteUiSanIsidro3.setDireccionRestaurante("San Isidro");
        restauranteUiSanIsidro3.setPuntosRestaurante("7.0");
        restauranteUiSanIsidro3.setPlatoPrecioPersona("S/. 10.00");
        restauranteUiSanIsidro3.setImageRestaurante("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/g//4920/32071/restaurante_pescados-capitales-chacarilla_san-borja_530654_10150894869543998_496140408_n.jpg");//----------
        restauranteUis.add(restauranteUiSanIsidro3);
        return restauranteUis;

    }
}
