package com.wakeapp.inventario_offline.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.wakeapp.inventario_offline.R;
import com.wakeapp.inventario_offline.controller.Adapters.AdapterUbicaciones;
import com.wakeapp.inventario_offline.controller.RoomSQLite.AppDataBase;
import com.wakeapp.inventario_offline.databinding.ActivityUbicationsBinding;
import com.wakeapp.inventario_offline.utils.Alertas;
import com.wakeapp.inventario_offline.utils.Constants;

import static android.view.View.GONE;

public class UbicationsActivity extends AppCompatActivity {

    private ActivityUbicationsBinding binding;

    /**
     * base de datos
     **/
    private AppDataBase bd;

    private AdapterUbicaciones mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUbicationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bd = Room.databaseBuilder(getApplicationContext(),AppDataBase.class, Constants.BD_NAME)
                .allowMainThreadQueries()
                .build();
        binding.appbar.topAppBar.setTitle(R.string.ubicaciones);

        init();
    }

    public void init(){
        cargarUbicaciones();
        if(bd.ubicationDao().sp_Sel_CountUbication() > 0){
            binding.indicador.setVisibility(GONE);
        }
        binding.floatAdd.setOnClickListener( v -> Alertas.alertNuevaUbicacion(getLayoutInflater(), UbicationsActivity.this, bd, mAdapter));
    }

    public void cargarUbicaciones(){
        binding.listUbicaciones.setHasFixedSize(true);
        binding.listUbicaciones.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AdapterUbicaciones(bd.ubicationDao().sp_Sel_AllUbications());
        binding.listUbicaciones.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new AdapterUbicaciones.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onDeleteClick(int position) {

            }

            @Override
            public void onEditClikc(int position) {

            }
        });
    }
}