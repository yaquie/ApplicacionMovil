package express.deli.cosema.deliexpressapp.base;


public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void onCreate();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onBackPressed();
}
