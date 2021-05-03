package com.wakeapp.inventario_offline.ui;

import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wakeapp.inventario_offline.R;
import com.wakeapp.inventario_offline.controller.RoomSQLite.AppDataBase;
import com.wakeapp.inventario_offline.databinding.FragmentLoginCredentialBinding;
import com.wakeapp.inventario_offline.model.UserDB;
import com.wakeapp.inventario_offline.utils.Alertas;
import com.wakeapp.inventario_offline.utils.Constants;
import com.wakeapp.inventario_offline.utils.Local;
import com.wakeapp.inventario_offline.utils.Validador;

import es.dmoral.toasty.Toasty;

import static android.content.Context.FINGERPRINT_SERVICE;

public class LoginCredentialFragment extends Fragment {

    public LoginCredentialFragment() {
        // Required empty public constructor
    }
    public static LoginCredentialFragment newInstance() {
        return new LoginCredentialFragment();
    }

    private FragmentLoginCredentialBinding binding;
    FingerprintManager fingerprintManager;

    /**
     * base de datos
     **/
    private AppDataBase bd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginCredentialBinding.inflate(getLayoutInflater());
        init();
        return binding.getRoot();
    }

    public void init(){
        fingerprintManager = (FingerprintManager) getActivity().getSystemService(FINGERPRINT_SERVICE);
        bd = Room.databaseBuilder(getContext(), AppDataBase.class, Constants.BD_NAME)
                .allowMainThreadQueries()
                .build();

        if(fingerprintManager == null){
            binding.huella.setVisibility(View.INVISIBLE);
        }

        if(Local.getData("credenciales", getContext(),"recordar").equals("1")){
            cargarSesion();
        }

        binding.huella.setOnClickListener(v ->{
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameContent, LoginFingerFragment.newInstance())
                    .commitNow();
        });


        binding.btnIngresar.setOnClickListener(v -> {
            if (Validador.validaLogin(binding.edUsuario, binding.edContrasena)) {
                UserDB user = bd.userDao().sp_Val_User(binding.edUsuario.getEditText().getText().toString().trim(), binding.edContrasena.getEditText().getText().toString().trim());
                if(user != null){
                    if(binding.switchRecordar.isChecked()){
                        guardarSesion();
                    }
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }else{
                    Toasty.error(getContext(), "Credenciales incorrectas", Toasty.LENGTH_SHORT).show();
                }
            }
        });

        binding.recuperar.setOnClickListener(view1 -> Alertas.recuperaCredenciales(getLayoutInflater(), getActivity(), bd));

    }

    public void cargarSesion(){
        binding.switchRecordar.setChecked(true);
        binding.edUsuario.getEditText().setText(Local.getData("credenciales", getContext(),"user"));
        binding.edContrasena.getEditText().setText(Local.getData("credenciales", getContext(),"password"));
    }

    public void guardarSesion(){
        Local.setData("credenciales", getContext(),"recordar", "1");
        Local.setData("credenciales", getContext(),"user", binding.edUsuario.getEditText().getText().toString().trim());
        Local.setData("credenciales", getContext(),"password", binding.edContrasena.getEditText().getText().toString().trim());
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}