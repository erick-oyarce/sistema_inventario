package com.wakeapp.inventario_offline.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.wakeapp.inventario_offline.R;
import com.wakeapp.inventario_offline.controller.RoomSQLite.AppDataBase;
import com.wakeapp.inventario_offline.databinding.ActivityLoginBinding;
import com.wakeapp.inventario_offline.model.UserDB;
import com.wakeapp.inventario_offline.utils.Alertas;
import com.wakeapp.inventario_offline.utils.Constants;
import com.wakeapp.inventario_offline.utils.Local;
import com.wakeapp.inventario_offline.utils.Validador;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {

    KeyguardManager keyguardManager;
    FingerprintManager fingerprintManager;

    /**
     * base de datos
     **/
    private AppDataBase bd;

    private ActivityLoginBinding binding;

    //codigo para los permisos de la aplicacion
    final int PHONE_CHECK_STORAGE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        bd = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, Constants.BD_NAME)
                .allowMainThreadQueries()
                .build();

        keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        LoginFingerFragment fingerFragment = new LoginFingerFragment();
        LoginCredentialFragment credentialFragment = new LoginCredentialFragment();

        if(fingerprintManager == null || !Local.getData("huella", getApplicationContext(), "activa").equals("1")){
            fragmentTransaction.add(R.id.frameContent, credentialFragment);
            fragmentTransaction.commit();
        }else {
            if (!fingerprintManager.isHardwareDetected() || !Local.getData("huella", getApplicationContext(), "activa").equals("1")) {
                fragmentTransaction.add(R.id.frameContent, credentialFragment);
                fragmentTransaction.commit();
            } else {
                fragmentTransaction.add(R.id.frameContent, fingerFragment);
                fragmentTransaction.commit();
            }
        }
        init();

    }

    public void init(){
        if(bd.userDao().sp_Sel_CountUser() == 0) {
            Alertas.alertCredenciales(LoginActivity.this, getLayoutInflater(), bd);
        }

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            validarPermisosExternal();
        }
    }

    /************ Solicitud de permisos para la aplicación *************/
    public void validarPermisosExternal(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            //nuevas versiones
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE},PHONE_CHECK_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PHONE_CHECK_STORAGE:
                String write = permissions[0];
                String camera = permissions[1];
                String read = permissions[2];
                int resultWrite = grantResults[0];

                if(write.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE) && camera.equals(Manifest.permission.CAMERA) && read.equals(Manifest.permission.READ_EXTERNAL_STORAGE)){
                    //Comprobar si se acepto la petición
                    if(resultWrite == PackageManager.PERMISSION_GRANTED){
                        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                    }else{
                        //No se concedió el permiso
                        //  Toast.makeText(getApplicationContext(), "Permiso denegado", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}