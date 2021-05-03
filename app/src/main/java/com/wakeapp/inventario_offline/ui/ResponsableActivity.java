package com.wakeapp.inventario_offline.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.wakeapp.inventario_offline.R;
import com.wakeapp.inventario_offline.controller.Adapters.AdapterResponsables;
import com.wakeapp.inventario_offline.controller.Functions.SwipeResponsable;
import com.wakeapp.inventario_offline.controller.RoomSQLite.AppDataBase;
import com.wakeapp.inventario_offline.databinding.ActivityResponsableBinding;
import com.wakeapp.inventario_offline.model.ResponsableDB;
import com.wakeapp.inventario_offline.utils.Alertas;
import com.wakeapp.inventario_offline.utils.Constants;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class ResponsableActivity extends AppCompatActivity {

    private ActivityResponsableBinding binding;

    /**
     * base de datos
     **/
    private AppDataBase bd;

    private AdapterResponsables mAdapter;
    private List<ResponsableDB> listaResponsable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResponsableBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bd = Room.databaseBuilder(getApplicationContext(),AppDataBase.class, Constants.BD_NAME)
                .allowMainThreadQueries()
                .build();

        init();
    }

    public void init(){
        cargaResponsables();
        binding.appbar.topAppBar.setTitle(getString(R.string.responsables));
        if(bd.responsableDao().sp_Sel_CountResponsable() > 0){
            binding.indicador.setVisibility(View.GONE);
        }
        binding.floatAdd.setOnClickListener( v -> Alertas.alertNuevoResponsable(getLayoutInflater(), ResponsableActivity.this, bd, binding));
        binding.appbar.topAppBar.setNavigationOnClickListener(v->finish());

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeResponsable(ResponsableActivity.this).itemTouchHelper());
        itemTouchHelper.attachToRecyclerView(binding.recyclerResponsable);
    }

    public void cargaResponsables(){
        binding.recyclerResponsable.setHasFixedSize(true);
        binding.recyclerResponsable.setLayoutManager(new LinearLayoutManager(this));
        listaResponsable = bd.responsableDao().sp_Sel_AllResponsabler();
        mAdapter = new AdapterResponsables(listaResponsable);
        binding.recyclerResponsable.setAdapter(mAdapter);


    }

    public void eliminarResponsable(int position){
        ResponsableDB responsableDB = listaResponsable.get(position);
        if(bd.responsableDao().sp_Del_Responsable(listaResponsable.get(position).getId()) > 0){
            Snackbar.make(binding.recyclerResponsable, "Responsable eliminado" , Snackbar.LENGTH_LONG)
                    .setAction("Deshacer", view -> deshacerEliminado(responsableDB, position)).show();
            listaResponsable.remove(position);
            mAdapter.notifyItemRemoved(position);
            if(bd.responsableDao().sp_Sel_CountResponsable() < 1){
                binding.indicador.setVisibility(View.VISIBLE);
            }
        }else{
            Toasty.error(getApplicationContext(),"Error al eliminar", Toasty.LENGTH_LONG).show();
        }
    }

    public void editarResponsable(int position){
        Alertas.alertEditarResponsable(getLayoutInflater(), ResponsableActivity.this, bd, listaResponsable.get(position));
    }

    public void deshacerEliminado(ResponsableDB responsableDB, int position){
        bd.responsableDao().sp_Ins_Responsable(responsableDB);
        listaResponsable.add(position,responsableDB);
        mAdapter.notifyItemInserted(position);
        binding.indicador.setVisibility(View.INVISIBLE);
    }

}