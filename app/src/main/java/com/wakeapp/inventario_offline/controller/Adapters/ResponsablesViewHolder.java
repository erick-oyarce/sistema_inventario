package com.wakeapp.inventario_offline.controller.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wakeapp.inventario_offline.R;

public class ResponsablesViewHolder extends RecyclerView.ViewHolder {

    public TextView tvDesc;
    public TextView tvNombre;
    public TextView tvIndetificador;
    public ImageView mDeleteUbication;
    public ImageView mEditUbication;

    public ResponsablesViewHolder(@NonNull View itemView) {
        super(itemView);

        tvDesc = itemView.findViewById(R.id.tvDesc);
        tvNombre = itemView.findViewById(R.id.tvNombre);
        tvIndetificador = itemView.findViewById(R.id.tvIndetificador);
    }
}
