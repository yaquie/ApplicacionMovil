package express.deli.cosema.deliexpressapp.login.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import express.deli.cosema.deliexpressapp.login.fragment.PrimerFragment;
import express.deli.cosema.deliexpressapp.login.fragment.SegundoFragment;
import express.deli.cosema.deliexpressapp.login.fragment.TercerFragment;

public class PaginaAdapter   extends FragmentStatePagerAdapter {

    public PaginaAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PrimerFragment.newInstance();
            case 1:
                return SegundoFragment.newInstance();
            case 2:
                return TercerFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
