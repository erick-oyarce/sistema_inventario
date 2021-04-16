package com.wakeapp.inventario_offline.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;

import com.wakeapp.inventario_offline.R;
import com.wakeapp.inventario_offline.controller.RoomSQLite.AppDataBase;
import com.wakeapp.inventario_offline.databinding.ActivityGestionBinding;
import com.wakeapp.inventario_offline.databinding.ActivityLoginBinding;
import com.wakeapp.inventario_offline.utils.Alertas;
import com.wakeapp.inventario_offline.utils.Constants;

import es.dmoral.toasty.Toasty;

public class GestionActivity extends AppCompatActivity {

    private ActivityGestionBinding binding;

    /**
     * base de datos
     **/
    private AppDataBase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bd = Room.databaseBuilder(getApplicationContext(),AppDataBase.class, Constants.BD_NAME)
                .allowMainThreadQueries()
                .build();

        init();
    }

    public void init(){

        binding.appbar.topAppBar.setTitle(getString(R.string.gestion_activos));
        binding.linAdd.setOnClickListener( v->{
            if(bd.ubicationDao().sp_Sel_CountUbication() > 0){

            }else{
                Alertas.validarCrearUbicacion(getLayoutInflater(), GestionActivity.this);
            }
        });

        binding.linEdit.setOnClickListener(v ->{
            if(bd.generalDao().sp_Sel_Count_AllActive() > 0){

            }else{
                Toasty.warning(getApplicationContext(), getString(R.string.no_exist_acitve), Toasty.LENGTH_LONG).show();
            }
        });

        binding.linDelete.setOnClickListener(v ->{
            if(bd.generalDao().sp_Sel_Count_AllActive() > 0){

            }else{
                Toasty.warning(getApplicationContext(), getString(R.string.no_exist_acitve), Toasty.LENGTH_LONG).show();
            }
        });

        binding.linShare.setOnClickListener(v ->{
            if(bd.generalDao().sp_Sel_Count_AllActive() > 0){

            }else{
                Toasty.warning(getApplicationContext(), getString(R.string.no_exist_acitve), Toasty.LENGTH_LONG).show();
            }
        });
    }
}