package com.wakeapp.inventario_offline.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wakeapp.inventario_offline.controller.RoomSQLite.AppDataBase;
import com.wakeapp.inventario_offline.databinding.ActivityLoginBinding;
import com.wakeapp.inventario_offline.model.UserDB;
import com.wakeapp.inventario_offline.utils.Alertas;
import com.wakeapp.inventario_offline.utils.Constants;
import com.wakeapp.inventario_offline.utils.Validador;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {

    /**
     * base de datos
     **/
    private AppDataBase bd;

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        bd = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, Constants.BD_NAME)
                .allowMainThreadQueries()
                .build();

        init();
    }

    public void init(){
        if(bd.userDao().sp_Sel_CountUser() == 0) {
            Alertas.alertCredenciales(LoginActivity.this, getLayoutInflater(), bd);
        }
        binding.btnIngresar.setOnClickListener(v -> {
            if (Validador.validaLogin(binding.edUsuario, binding.edContrasena)) {
                UserDB user = bd.userDao().sp_Val_User(binding.edUsuario.getEditText().getText().toString().trim(), binding.edContrasena.getEditText().getText().toString().trim());
                if(user != null){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else{
                    Toasty.error(getApplicationContext(), "Credenciales incorrectas", Toasty.LENGTH_SHORT).show();
                }
            }
        });

        binding.recuperar.setOnClickListener(view1 -> Alertas.recuperaCredenciales(getLayoutInflater(), LoginActivity.this, bd));


    }
}