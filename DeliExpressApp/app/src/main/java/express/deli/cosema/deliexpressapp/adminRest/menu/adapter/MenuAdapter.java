package express.deli.cosema.deliexpressapp.adminRest.menu.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import express.deli.cosema.deliexpressapp.R;
import express.deli.cosema.deliexpressapp.adminRest.menu.adapter.holder.MenuHolder;
import express.deli.cosema.deliexpressapp.adminRest.menu.entidad.MenuUi;

public class MenuAdapter extends RecyclerView.Adapter<MenuHolder> {
    private List<MenuUi> menuUiList;

    public MenuAdapter(List<MenuUi> menuUiList) {
        this.menuUiList = menuUiList;
    }

    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new MenuHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuHolder holder, int position) {
        MenuUi menuUi = menuUiList.get(position);
        holder.bind(menuUi);
    }

    @Override
    public int getItemCount() {
        if (menuUiList == null) return 0;
        return menuUiList.size();
    }
}
