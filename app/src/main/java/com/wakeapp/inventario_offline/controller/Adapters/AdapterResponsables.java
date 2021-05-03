package com.wakeapp.inventario_offline.controller.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wakeapp.inventario_offline.R;
import com.wakeapp.inventario_offline.model.ResponsableDB;

import java.util.List;

public class AdapterResponsables extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ResponsableDB> listaResponsables;

    public AdapterResponsables(List<ResponsableDB> listaUbicaciones) {
        this.listaResponsables = listaUbicaciones;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_responsable, parent, false);
        return new ResponsablesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ResponsableDB responsableDB = listaResponsables.get(position);
        ((ResponsablesViewHolder)holder).tvDesc.setText(responsableDB.getDescripcion());
        ((ResponsablesViewHolder)holder).tvNombre.setText(responsableDB.getNombre()+" "+responsableDB.getApellido());
        ((ResponsablesViewHolder)holder).tvIndetificador.setText(responsableDB.getIdentificador());
    }

    @Override
    public int getItemCount() {
        return listaResponsables.size();
    }


}
