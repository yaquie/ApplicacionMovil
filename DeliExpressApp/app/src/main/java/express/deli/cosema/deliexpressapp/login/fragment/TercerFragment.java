package express.deli.cosema.deliexpressapp.login.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.login.listener.SliderListener;
import express.deli.cosema.deliexpressapp.utils.Constantes;

public class TercerFragment extends Fragment {

    public static final String TAG = TercerFragment.class.getSimpleName();

    Unbinder unbinder;
    SliderListener listener;

    public static TercerFragment newInstance() {
        TercerFragment f = new TercerFragment();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.listener = (SliderListener) context;
        } else {
            Log.d(TAG, "implementar Listener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.tercer_slide_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.Linearlayout)
    public void onClickNext() {
        listener.onClickSiguienteSlide(Constantes.TERCER_SLIDE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        listener = null;
    }
}
