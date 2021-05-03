package com.wakeapp.inventario_offline.controller.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wakeapp.inventario_offline.R;
import com.wakeapp.inventario_offline.model.UbicationDB;

import java.util.List;

public class AdapterUbicaciones extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<UbicationDB> listaUbicaciones;

    public AdapterUbicaciones(List<UbicationDB> listaUbicaciones) {
        this.listaUbicaciones = listaUbicaciones;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_ubicacion, parent, false);
        return new UbicationsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UbicationDB ubicationDB = listaUbicaciones.get(position);
        ((UbicationsViewHolder)holder).ubicacion.setText(ubicationDB.getUbicacion());
        ((UbicationsViewHolder)holder).descripcion.setText(ubicationDB.getObservacion());
    }

    @Override
    public int getItemCount() {
        return listaUbicaciones.size();
    }

}
