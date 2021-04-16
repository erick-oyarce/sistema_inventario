package com.wakeapp.inventario_offline.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wakeapp.inventario_offline.R;
import com.wakeapp.inventario_offline.controller.RoomSQLite.AppDataBase;
import com.wakeapp.inventario_offline.databinding.ActivityLoginBinding;
import com.wakeapp.inventario_offline.databinding.ActivityMainBinding;
import com.wakeapp.inventario_offline.utils.Constants;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    /**
     * base de datos
     **/
    private AppDataBase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        bd = Room.databaseBuilder(getApplicationContext(),AppDataBase.class, Constants.BD_NAME)
                .allowMainThreadQueries()
                .build();

        init();
    }

    public void init(){

        cargarCantidades();

        binding.cardGestion.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, GestionActivity.class)) );
        binding.cardInventario.setOnClickListener(v ->{});
        binding.cardBusqueda.setOnClickListener(v ->{});
        binding.cardConfiguracion.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ConfigurationActivity.class)));
    }

    public void cargarCantidades(){
        binding.itemsInventory.txtNotebook.setText(bd.notebookDao().sp_Sel_CountNotebook()+"");
        binding.itemsInventory.txtHerramienta.setText(bd.toolDao().sp_Sel_CountTool()+"");
        binding.itemsInventory.txtMovil.setText(bd.movilDao().sp_Sel_CountMovil()+"");
        binding.itemsInventory.txtOtro.setText(bd.otherDao().sp_Sel_CountOther()+"");
        binding.itemsInventory.txtMueble.setText(bd.furnitureDao().sp_Sel_CountFurniture()+"");
    }
}