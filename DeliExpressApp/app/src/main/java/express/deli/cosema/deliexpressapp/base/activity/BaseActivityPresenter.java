package express.deli.cosema.deliexpressapp.base.activity;

import android.os.Bundle;

import express.deli.cosema.deliexpressapp.base.BasePresenter;


public interface BaseActivityPresenter<T extends BaseActivityView> extends BasePresenter<T> {
    void setExtras(Bundle extras);


}
