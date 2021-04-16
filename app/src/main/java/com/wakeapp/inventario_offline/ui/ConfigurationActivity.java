package com.wakeapp.inventario_offline.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.wakeapp.inventario_offline.R;
import com.wakeapp.inventario_offline.controller.RoomSQLite.AppDataBase;
import com.wakeapp.inventario_offline.databinding.ActivityConfigurationBinding;
import com.wakeapp.inventario_offline.databinding.ActivityMainBinding;
import com.wakeapp.inventario_offline.utils.Local;

public class ConfigurationActivity extends AppCompatActivity {

    private ActivityConfigurationBinding binding;
    KeyguardManager keyguardManager;
    FingerprintManager fingerprintManager;

    /**
     * base de datos
     **/
    private AppDataBase bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfigurationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();
    }

    public void init(){
        keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        binding.appbar.topAppBar.setTitle(getString(R.string.administracion));
        binding.appbar.topAppBar.setNavigationOnClickListener(v->finish());
        validarHuella();

        binding.linUbic.setOnClickListener(v -> startActivity(new Intent(ConfigurationActivity.this, UbicationsActivity.class)));
        binding.linOpinion.setOnClickListener(v -> startActivity(new Intent(ConfigurationActivity.this, RateAppActivity.class)));

    }

    public void validarHuella(){
        if(fingerprintManager == null){
            binding.linHuella.setVisibility(View.GONE);
            binding.viewHuella.setVisibility(View.GONE);
        }else {
            if (!fingerprintManager.isHardwareDetected()) {
                binding.linHuella.setVisibility(View.GONE);
                binding.viewHuella.setVisibility(View.GONE);
            }
        }

        if(Local.getData("huella", getApplicationContext(), "activa").equals("1")){
            binding.switchHuella.setChecked(true);
        }

        binding.switchHuella.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                Local.setData("huella", getApplicationContext(), "activa", "1");
            }else{
                Local.setData("huella", getApplicationContext(), "activa", "2");
            }
        });
    }
}