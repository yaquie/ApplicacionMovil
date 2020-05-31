package express.deli.cosema.deliexpressapp.base.fragment;

import android.os.Bundle;

import express.deli.cosema.deliexpressapp.base.BasePresenter;
import express.deli.cosema.deliexpressapp.base.BaseView;


public interface BaseFragmentPresenter<T extends BaseView> extends BasePresenter<T> {
    void onCreateView();
    void onViewCreated();
    void onActivityCreated();
    void onDestroyView();
    void onDetach();
    void setExtras(Bundle extras);
}
