package com.wakeapp.inventario_offline.controller.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wakeapp.inventario_offline.R;

public class UbicationsViewHolder extends RecyclerView.ViewHolder{
    public TextView ubicacion;
    public TextView descripcion;

    public UbicationsViewHolder(@NonNull View itemView) {
        super(itemView);

        ubicacion = itemView.findViewById(R.id.tvDesc);
        descripcion = itemView.findViewById(R.id.tvNombre);
    }
}
