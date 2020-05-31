package express.deli.cosema.deliexpressapp.adminRest.categoria;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.adminRest.categoria.fragCategorias.FCategoriasFragment;
import express.deli.cosema.deliexpressapp.adminRest.categoria.fragMisCategorias.MisCategoriasFragment;
import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.UseCaseThreadPoolScheduler;
import express.deli.cosema.deliexpressapp.base.activity.BaseActivity;
import express.deli.cosema.deliexpressapp.utils.MyFragmentAdapter;

public class CategoriaActivity extends BaseActivity<CategoriaView, CategoriaPresenter> implements CategoriaView {

    public static final String TAG = CategoriaActivity.class.getSimpleName();


    @BindView(R.id.tablMenu)
    TabLayout tablMenu;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected CategoriaPresenter getPresenter() {
        return new CategoriaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected CategoriaView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return null;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_categoria);
        ButterKnife.bind(this);
        initToolbar();
    }

    private void initToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTabsAdapter();
    }

    private void initTabsAdapter() {
        MyFragmentAdapter fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(FCategoriasFragment.newInstance(),  "CATEGORIAS");
        fragmentAdapter.addFragment(MisCategoriasFragment.newInstance(), "MIS CATEGORIAS");

        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(fragmentAdapter);
        tablMenu.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
               /* if (presenter != null)
                    presenter.onPageChanged(fragmentAdapter.getItem(position) != null ? fragmentAdapter.getItem(position).getClass() : null);
*/
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        } else {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
