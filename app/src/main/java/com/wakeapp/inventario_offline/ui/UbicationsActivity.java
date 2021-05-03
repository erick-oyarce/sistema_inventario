package com.wakeapp.inventario_offline.ui;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;
import com.wakeapp.inventario_offline.R;
import com.wakeapp.inventario_offline.controller.Adapters.AdapterUbicaciones;
import com.wakeapp.inventario_offline.controller.Functions.SwipeUbications;
import com.wakeapp.inventario_offline.controller.RoomSQLite.AppDataBase;
import com.wakeapp.inventario_offline.databinding.ActivityUbicationsBinding;
import com.wakeapp.inventario_offline.model.ToolDB;
import com.wakeapp.inventario_offline.model.UbicationDB;
import com.wakeapp.inventario_offline.utils.Alertas;
import com.wakeapp.inventario_offline.utils.Constants;

import java.util.List;

import es.dmoral.toasty.Toasty;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class UbicationsActivity extends AppCompatActivity {

    private ActivityUbicationsBinding binding;

    /**
     * base de datos
     **/
    private AppDataBase bd;

    private AdapterUbicaciones mAdapter;
    private List<UbicationDB> listaUbicaciones;

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
            binding.indicador.setVisibility(View.GONE);
        }
        binding.floatAdd.setOnClickListener( v -> Alertas.alertNuevaUbicacion(getLayoutInflater(), UbicationsActivity.this, bd, mAdapter, binding));
        binding.appbar.topAppBar.setNavigationOnClickListener(v->finish());

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeUbications(UbicationsActivity.this).itemTouchHelper());
        itemTouchHelper.attachToRecyclerView(binding.recyclerUbicaciones);
    }

    public void cargarUbicaciones(){
        binding.recyclerUbicaciones.setHasFixedSize(true);
        binding.recyclerUbicaciones.setLayoutManager(new LinearLayoutManager(this));
        listaUbicaciones = bd.ubicationDao().sp_Sel_AllUbications();
        mAdapter = new AdapterUbicaciones(listaUbicaciones);
        binding.recyclerUbicaciones.setAdapter(mAdapter);

    }

    public void elminarUbicacion(int position){
        UbicationDB ubicationDB = listaUbicaciones.get(position);
        if(bd.ubicationDao().sp_Del_Ubication(listaUbicaciones.get(position).getId()) > 0){
            Snackbar.make(binding.recyclerUbicaciones, "Ubicación eliminada" , Snackbar.LENGTH_LONG)
                    .setAction("Deshacer", view -> deshacerEliminado(ubicationDB, position)).show();
            listaUbicaciones.remove(position);
            mAdapter.notifyItemRemoved(position);
            if(bd.ubicationDao().sp_Sel_CountUbication() < 1){
                binding.indicador.setVisibility(View.VISIBLE);
            }
        }else{
            Toasty.error(getApplicationContext(),"Error al eliminar", Toasty.LENGTH_LONG).show();
        }
    }

    public void editarUbicacion(int position){
        Alertas.alertEditUbicacion(getLayoutInflater(), UbicationsActivity.this, bd, listaUbicaciones.get(position));
    }

    public void deshacerEliminado(UbicationDB ubicationDB, int position){
        bd.ubicationDao().sp_Ins_Ubication(ubicationDB);
        listaUbicaciones.add(position,ubicationDB);
        mAdapter.notifyItemInserted(position);
        binding.indicador.setVisibility(View.INVISIBLE);
    }



}