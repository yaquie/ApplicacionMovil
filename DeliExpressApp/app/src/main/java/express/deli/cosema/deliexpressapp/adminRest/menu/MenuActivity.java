package express.deli.cosema.deliexpressapp.adminRest.menu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.adminRest.menu.adapter.MenuAdapter;
import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.UseCaseThreadPoolScheduler;
import express.deli.cosema.deliexpressapp.base.activity.BaseActivity;

public class MenuActivity extends BaseActivity<MenuView, MenuPresenter> implements MenuView {

    public static final String TAG = MenuActivity.class.getSimpleName();
   /* @BindView(R.id.floatingActionButton2)
    FloatingActionButton floatingActionButton;*/

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected MenuPresenter getPresenter() {
        return new MenuPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected MenuView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return null;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_menu2);
        ButterKnife.bind(this);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @OnClick({R.id.floatingActionButton2})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.floatingActionButton2:
                break;
        }
    }
}
