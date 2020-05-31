package express.deli.cosema.deliexpressapp.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.adminRest.categoria.CategoriaActivity;
import express.deli.cosema.deliexpressapp.adminRest.menu.MenuActivity;
import express.deli.cosema.deliexpressapp.adminRest.restaurante.RestauranteActivity;
import express.deli.cosema.deliexpressapp.base.UseCaseHandler;
import express.deli.cosema.deliexpressapp.base.UseCaseThreadPoolScheduler;
import express.deli.cosema.deliexpressapp.base.activity.BaseActivity;
import express.deli.cosema.deliexpressapp.login.LoginActivity;
import express.deli.cosema.deliexpressapp.platos.ListadoPlatoActivity;
import express.deli.cosema.deliexpressapp.utils.Constantes;
import express.deli.cosema.deliexpressapp.utils.SecurePreferences;

//public class MainActivity extends AppCompatActivity
//        implements NavigationView.OnNavigationItemSelectedListener {
public class MainActivity extends BaseActivity<MainView, MainPresenter>
        implements MainView, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;
    @BindView(R.id.view_perfil)
    View content_perfil;

    @BindView(R.id.imageView2)
    CircleImageView circleImageView;
    @BindView(R.id.textViewNombre)
    TextView textViewNombrePerfil;
    @BindView(R.id.textViewSesion)
    TextView textViewSesionPerfil;

    @BindView(R.id.buttonIniciar)
    Button buttonIniciar;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private SecurePreferences securePreferences;

    public static final String TAG = MainActivity.class.getSimpleName();


   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }*/

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(), this);
    }

    @Override
    protected MainView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return null;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        securePreferences = new SecurePreferences(this, Constantes.KEY_SECURE_PREFERENCE, true);
        Log.d(TAG, "setContentView");
        initView();
        initAuthFirebase();
        initBottomBar();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  presenter.onSecurePreferences(securePreferences);
        // presenter.onNavigationItemSelected(menuItem);
        Log.d(TAG, "onCreate");
    }


    private void initBottomBar() {
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.name_main_descubrir, R.drawable.ic_document, R.color.md_orange_400);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.name_main_guardados, R.drawable.ic_like, R.color.md_orange_400);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.name_main_perfil, R.drawable.ic_perfl, R.color.md_orange_400);
        // Agregando Items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        // Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
        // Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(false);
        // Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
        // Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);
        // Display color under navigation bar (API 21+)
        // Don't forget these lines in your style-v21
        // <item name="android:windowTranslucentNavigation">true</item>
        // <item name="android:fitsSystemWindows">true</item>
        bottomNavigation.setTranslucentNavigationEnabled(true);
        // Manage titles

       /* bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);*/
        // Use colored navigation with circle reveal effect
        bottomNavigation.setColored(true);

        // Set current item programmatically
        ///bottomNavigation.setCurrentItem(displaySelectedScreen(););
        // Customize notification (title, background, typeface)
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));

    }

    private void initAuthFirebase() {
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        presenter.onSecurePreferences(securePreferences);
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                /*Se verifica cuando el usuario esta iniciado desde Firebase*/
               /* FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                //  presenter.onAuthStateChanged(firebaseUser);
                if (firebaseUser == null) {

                    Log.d(TAG, "firebaseUser == null");
                } else {
                    //   startActivityMainMenu();
                    // initValidaTePreferences();
                    Log.d(TAG, "firebaseUser != null");
                }*/


                presenter.estadoFirebaseAccount(firebaseAuth);
            }
        };
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null) firebaseAuth.removeAuthStateListener(authStateListener);
    }

    ActionBarDrawerToggle toggle;

    Menu menu;

    private void initView() {
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        menu = navigationView.getMenu();

    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void onBackPressed() {
        // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Log.d(TAG, "onNavigationItemSelected");


        int id = item.getItemId();
        if (id == R.id.nav_camera) {

            Log.d(TAG, "nav_camera");
            //Intent intent = new Intent()
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Log.d(TAG, "nav_gallery");

        } else if (id == R.id.nav_slideshow) {
            Log.d(TAG, "nav_slideshow");
        } else if (id == R.id.nav_manage) {
            Log.d(TAG, "nav_manage");
        } else if (id == R.id.nav_share) {
            Log.d(TAG, "nav_share");
        } else if (id == R.id.nav_send) {
            Log.d(TAG, "nav_send");
        } else if (id == R.id.nav_platos) {
            initStartActivityRegistroPlato();
        } else if (id == R.id.nav_send_categoria) {
            initStartActivityRegistroCategoria();
        } else if (id == R.id.nav_menu) {
            initStartActivityRegistroMenu();
        } else if (id == R.id.datos_restaurante) {
            initStartActivityDatosRestaurante();
        }


        //  DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initStartActivityDatosRestaurante() {//RestauranteActivity
        Intent intent = new Intent(this, RestauranteActivity.class);
        startActivity(intent);
    }

    private void initStartActivityRegistroMenu() {//MenuActivity
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private void initStartActivityRegistroCategoria() {
        Intent intent = new Intent(this, CategoriaActivity.class);
        startActivity(intent);
    }

    private void initStartActivityRegistroPlato() {
        Intent intent = new Intent(this, ListadoPlatoActivity.class);
        startActivity(intent);
    }


    @Override
    public void regresarLogin() {
        Log.d(TAG, "USUARIO NO LOGUEADO");
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void initBotomBar() {
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                // Do something cool here...
                Log.d(TAG, "position : " + position + " / wasSelesct " + wasSelected);
                //  displaySelectedScreen(position);
                presenter.posicionFragment(position);
                return true;
            }
        });
        bottomNavigation.setCurrentItem(0);

    }

    @Override
    public void ocultarVistas() {
        toolbar.setVisibility(View.GONE);
        navigationView.setVisibility(View.GONE);
        navigationView.setEnabled(true);
        //  content_perfil.setVisibility(V  iew.GONE);
        //setDrawerState(false);
    }

    @Override
    public void mostrarVistas() {
        // content_perfil.setVisibility(View.VISIBLE);
        toolbar.setVisibility(View.VISIBLE);
        navigationView.setVisibility(View.VISIBLE);
        //navigationView.setEnabled(false);
        //setDrawerState(true);
    }

    @Override
    public void ocultarContentPerfil() {
        content_perfil.setVisibility(View.GONE);
    }

    @Override
    public void mostrarContentPerfil() {
        content_perfil.setVisibility(View.VISIBLE);
    }

    @Override
    public void deshabilitarDrawerNavigation(boolean boleean) {
        setDrawerState(boleean);
    }

    @Override
    public void habilitarDrawerNavigation(boolean boleean) {
        setDrawerState(boleean);
    }

    @Override
    public void mostrarDataPerfil(String foto, String nombre, String tipoSesion) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_man);
        requestOptions.error(R.drawable.ic_man);
        Glide.with(this)
                .load(foto)
                .into(circleImageView);
        textViewNombrePerfil.setText(nombre);
        textViewSesionPerfil.setText(tipoSesion);
    }

    @Override
    public void mostrarButtonIniciar() {
        buttonIniciar.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarButtonIniciar() {
        buttonIniciar.setVisibility(View.GONE);
    }

    @Override
    public void mostrarDatosNavigation() {

    }

    @Override
    public void ocultarDatosNavigation() {

    }

    @Override
    public void mostrarMenuNavigationAdminGeneral(Menu item) {

        item.findItem(R.id.nav_camera).setVisible(true);
        item.findItem(R.id.nav_gallery).setVisible(true);
        item.findItem(R.id.nav_slideshow).setVisible(true);
        item.findItem(R.id.nav_manage).setVisible(true);
        item.findItem(R.id.nav_share).setVisible(true);
        item.findItem(R.id.nav_send).setVisible(true);


        item.findItem(R.id.nav_platos).setVisible(false);
        item.findItem(R.id.nav_promociones).setVisible(false);
        item.findItem(R.id.nav_horarios).setVisible(false);

        item.findItem(R.id.datos_restaurante).setVisible(false);
        item.findItem(R.id.repor_cliente_rest).setVisible(false);

        item.findItem(R.id.nav_send_by).setVisible(false);

        item.findItem(R.id.nav_menu).setVisible(false);//nav_send_categoria

        item.findItem(R.id.nav_send_categoria).setVisible(false);
    }

    @Override
    public void mostrarMenuNavigationAdminRestaurante(Menu item) {

        item.findItem(R.id.nav_camera).setVisible(false);
        item.findItem(R.id.nav_gallery).setVisible(false);
        item.findItem(R.id.nav_slideshow).setVisible(false);
        item.findItem(R.id.nav_manage).setVisible(false);
        item.findItem(R.id.nav_share).setVisible(false);
        item.findItem(R.id.nav_send).setVisible(false);


        item.findItem(R.id.nav_platos).setVisible(false);

        item.findItem(R.id.nav_promociones).setVisible(true);
        item.findItem(R.id.nav_horarios).setVisible(true);

        item.findItem(R.id.datos_restaurante).setVisible(true);
        item.findItem(R.id.repor_cliente_rest).setVisible(true);
        item.findItem(R.id.nav_send_by).setVisible(true);

        item.findItem(R.id.nav_menu).setVisible(true);

        item.findItem(R.id.nav_send_categoria).setVisible(true);
    }

    @Override
    public void validarShowNavigation(String tipoEstado) {
        presenter.onNavigationItemSelected(menu, tipoEstado);
    }

    @Override
    public void mostrarDataPerfilNavigation(String fotoUsuarioFb, String nombreFb, String tipoSesionFb) {
        View hView = navigationView.getHeaderView(0);
        CircleImageView circleImageView = hView.findViewById(R.id.imageViewFoto);
        TextView textViewNombre = hView.findViewById(R.id.textViewNombre);
        TextView textViewSesion = hView.findViewById(R.id.textViewSesion);
        textViewNombre.setText(nombreFb);
        textViewSesion.setText(tipoSesionFb);
        Glide.with(this).load(fotoUsuarioFb).into(circleImageView);
    }

    @OnClick({R.id.buttonIniciar})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.buttonIniciar:
                break;
        }
    }

    public void setDrawerState(boolean isEnabled) {
        if (isEnabled) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            toggle.setDrawerIndicatorEnabled(true);
            toggle.syncState();

        } else {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            toggle.setDrawerIndicatorEnabled(false);
            toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSupportNavigateUp();
                }
            });
            toggle.syncState();
        }
    }
}
