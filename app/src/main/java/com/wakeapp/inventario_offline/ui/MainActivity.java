package com.wakeapp.inventario_offline.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wakeapp.inventario_offline.Response.ReponseAllActive;
import com.wakeapp.inventario_offline.ViewModels.AllActiveViewModel;
import com.wakeapp.inventario_offline.controller.RoomSQLite.AppDataBase;
import com.wakeapp.inventario_offline.databinding.ActivityMainBinding;
import com.wakeapp.inventario_offline.utils.Constants;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    /**
     * base de datos
     **/
    private AppDataBase bd;

    //ViewModel
    AllActiveViewModel allActiveViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        bd = Room.databaseBuilder(getApplicationContext(),AppDataBase.class, Constants.BD_NAME)
                .allowMainThreadQueries()
                .build();

        allActiveViewModel = new ViewModelProvider(this).get(AllActiveViewModel.class);

        init();
        ObserverAllActive();
        allActiveViewModel.searchActives(bd);

    }

    private void ObserverAllActive(){
        allActiveViewModel.getAllActive().observe(this, allActive -> {
            //OBSERVING FOR ANY DATA CHANGUE
            cargarCantidades(allActive.get(0));
        });
    }

    public void init(){


        binding.cardGestion.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, GestionActivity.class)) );
        binding.cardInventario.setOnClickListener(v ->{});
        binding.cardBusqueda.setOnClickListener(v ->{});
        binding.cardConfiguracion.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ConfigurationActivity.class)));
    }

    public void cargarCantidades(ReponseAllActive allActive){
        binding.itemsInventory.txtNotebook.setText(allActive.getNotebookDB()+"");
        binding.itemsInventory.txtHerramienta.setText(allActive.getToolDB()+"");
        binding.itemsInventory.txtMovil.setText(allActive.getMovilDB()+"");
        binding.itemsInventory.txtOtro.setText(allActive.getOtherDB()+"");
        binding.itemsInventory.txtMueble.setText(allActive.getFurnitureDB()+"");
    }
}