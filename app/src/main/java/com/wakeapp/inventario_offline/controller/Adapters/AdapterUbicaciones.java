package com.wakeapp.inventario_offline.controller.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;
import com.wakeapp.inventario_offline.R;
import com.wakeapp.inventario_offline.model.UbicationDB;

import java.util.ArrayList;
import java.util.List;

public class AdapterUbicaciones extends RecyclerView.Adapter<AdapterUbicaciones.exViewHolder> {

    private List<UbicationDB> listaUbicaciones;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onDeleteClick(int position);

        void onEditClikc(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public AdapterUbicaciones(List<UbicationDB> listaUbicaciones) {
        this.listaUbicaciones = listaUbicaciones;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public exViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_ubications, parent, false);
        exViewHolder evh = new exViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull exViewHolder holder, int position) {
        UbicationDB ubicationDB = listaUbicaciones.get(position);
        holder.ubicacion.setText(ubicationDB.getUbicacion());
        holder.descripcion.setText(ubicationDB.getObservacion());
    }

    @Override
    public int getItemCount() {
        return listaUbicaciones.size();
    }

    public static class exViewHolder extends RecyclerView.ViewHolder {
        public TextView ubicacion;
        public TextView descripcion;
        public ImageView mDeleteUbication;
        public ImageView mEditUbication;

        public exViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            ubicacion = itemView.findViewById(R.id.tvUbicacion);
            descripcion = itemView.findViewById(R.id.tvDesc);
            mDeleteUbication = itemView.findViewById(R.id.btnDelete);
            mEditUbication = itemView.findViewById(R.id.btnEdit);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });

            mDeleteUbication.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onDeleteClick(position);
                    }
                }

            });

            mEditUbication.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onEditClikc(position);
                    }
                }

            });
        }
    }
}
