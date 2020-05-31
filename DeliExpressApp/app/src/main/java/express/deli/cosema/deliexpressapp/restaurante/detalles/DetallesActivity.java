package express.deli.cosema.deliexpressapp.restaurante.detalles;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.UseCaseThreadPoolScheduler;
import express.deli.cosema.deliexpressapp.base.activity.BaseActivity;
import express.deli.cosema.deliexpressapp.restaurante.detalles.source.DetallesRepository;
import express.deli.cosema.deliexpressapp.restaurante.detalles.source.remote.DetallesRemote;
import express.deli.cosema.deliexpressapp.restaurante.detalles.useCase.MostrarHorario;
import express.deli.cosema.deliexpressapp.restaurante.entidad.RestauranteUi;

public class DetallesActivity extends BaseActivity<DetallesView, DetallesPresenter> implements DetallesView {


    public static final String TAG = DetallesActivity.class.getSimpleName();

    @BindView(R.id.textViewPuntos)
    TextView textViewPuntos;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.textViewNombreRest)
    TextView textViewNombreRest;

    @BindView(R.id.textViewHorario)
    TextView textViewHorario;
    @BindView(R.id.textViewLunesResp)
    TextView textViewLunesResp;
    @BindView(R.id.textViewMartesResp)
    TextView textViewMartesResp;
    @BindView(R.id.textViewMiercolesResp)
    TextView textViewMiercolesResp;
    @BindView(R.id.textViewJuevesResp)
    TextView textViewJuevesResp;
    @BindView(R.id.textViewViernesResp)
    TextView textViewViernesResp;
    @BindView(R.id.textViewSabadoResp)
    TextView textViewSabadoResp;
    @BindView(R.id.textViewDomingoResp)
    TextView textViewDomingoResp;

    @BindView(R.id.imageViewPrincipal)
    ImageView imageViewPrincipal;

    @BindView(R.id.idArrowAbajoHorario)
    ImageView imageViewRotateHorario;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayoutHorario;

    @BindView(R.id.idArrowAbajoTarjetas)
    ImageView imageViewRotateTarjetas;

    @BindView(R.id.imageViewPrimero)
    ImageView imageViewPrimero;
    @BindView(R.id.imageViewSegundo)
    ImageView imageViewSegundo;
    @BindView(R.id.imageViewTercero)
    ImageView imageViewTercero;
    @BindView(R.id.imageViewCuarto)
    ImageView imageViewCuarto;


    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected DetallesPresenter getPresenter() {
        DetallesRepository detallesRepository = DetallesRepository.getmInstance(new DetallesRemote());
        return new DetallesPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new MostrarHorario(detallesRepository));
    }

    @Override
    protected DetallesView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_detalles);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("");
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void mostrarDataDetalles(RestauranteUi restauranteUi) {
        textViewPuntos.setText(restauranteUi.getPuntosRestaurante());
        textViewNombreRest.setText(restauranteUi.getNombreRestaurante());
        Glide.with(this).load(restauranteUi.getImageRestaurante()).into(imageViewPrincipal);
        Glide.with(this).load("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/q//4228/26546/restaurante_ache-restaurante_miraflores_398239_150095008455229_547617874_n.jpg").into(imageViewPrimero);
        Glide.with(this).load("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/q//4228/26546/restaurante_ache-restaurante_miraflores_398239_150095008455229_547617874_n.jpg").into(imageViewSegundo);
        Glide.with(this).load("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/q//4228/47292/restaurante_ache-restaurante_miraflores_carne.jpg").into(imageViewTercero);
        Glide.with(this).load("https://restorando-res.cloudinary.com/image/upload/b_rgb:000000,c_pad,f_auto,g_center,h_720,q_auto:eco,w_1280/v1/restaurant_photos/r//5321/109411/restaurante_la-trastienda_barranco_varios_1.jpg").into(imageViewCuarto);
    }

    @Override
    public void mostrarDataHorario(String horarioGeneral, String lunesResp, String martesResp, String miercolesResp, String juevesResp, String viernesResp, String sabadoResp, String domingoResp) {
        textViewHorario.setText(horarioGeneral);
        textViewLunesResp.setText(lunesResp);
        textViewMartesResp.setText(martesResp);
        textViewMiercolesResp.setText(miercolesResp);
        textViewJuevesResp.setText(juevesResp);
        textViewViernesResp.setText(viernesResp);
        textViewSabadoResp.setText(sabadoResp);
        textViewDomingoResp.setText(domingoResp);
    }


    @OnClick({R.id.idArrowAbajoHorario, R.id.idArrowAbajoTarjetas})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.idArrowAbajoHorario:
                presenter.onClickHorario();
                break;
            case R.id.idArrowAbajoTarjetas:
                presenter.onClickTarjetas();
                break;
        }
    }


    @Override
    public void rotarImagenHorarioTrue() {
        constraintLayoutHorario.setVisibility(View.VISIBLE);
        imageViewRotateHorario.clearAnimation();
        ViewCompat.animate(imageViewRotateHorario).rotation(180).start();
    }

    @Override
    public void rotarImagenHorarioFalse() {
        constraintLayoutHorario.setVisibility(View.GONE);
        imageViewRotateHorario.clearAnimation();
        ViewCompat.animate(imageViewRotateHorario).rotation(0).start();
    }

    @Override
    public void rotarImagenTarjetasTrue() {
        imageViewRotateTarjetas.clearAnimation();
        ViewCompat.animate(imageViewRotateTarjetas).rotation(180).start();
    }

    @Override
    public void rotarImagenTarjetasFalse() {
        imageViewRotateTarjetas.clearAnimation();
        ViewCompat.animate(imageViewRotateTarjetas).rotation(0).start();
    }
}
