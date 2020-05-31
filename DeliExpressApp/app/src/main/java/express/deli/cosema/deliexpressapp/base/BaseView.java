package express.deli.cosema.deliexpressapp.base;

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}
