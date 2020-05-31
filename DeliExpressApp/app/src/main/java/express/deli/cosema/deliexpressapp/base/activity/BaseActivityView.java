package express.deli.cosema.deliexpressapp.base.activity;


import express.deli.cosema.deliexpressapp.base.BasePresenter;
import express.deli.cosema.deliexpressapp.base.BaseView;

public interface BaseActivityView<T extends BasePresenter> extends BaseView<T> {

    void mostrarProgressBar();

    void ocultarProgressBar();



}
