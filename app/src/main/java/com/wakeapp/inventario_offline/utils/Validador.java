package com.wakeapp.inventario_offline.utils;

import android.content.Context;
import android.util.Patterns;
import com.google.android.material.textfield.TextInputLayout;
import com.wakeapp.inventario_offline.R;

import java.util.regex.Pattern;

public class Validador {

    //comprobar si hay conexión a internet
    public static Boolean isOnlineNet() {
        try {
            Process p = Runtime.getRuntime().exec("ping -c 1 www.google.cl");
            int val = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static boolean validateEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public static boolean validaLogin(TextInputLayout user, TextInputLayout pass){
        boolean valido = true;

        if(pass.getEditText().getText().toString().trim().equals("")){
            valido = false;
            pass.setError("Requerido");
            pass.requestFocus();
        }else{
            pass.setError(null);
        }
        if(user.getEditText().getText().toString().trim().equals("")){
            valido = false;
            user.setError("Requerido");
            user.requestFocus();
        }else{
            user.setError(null);
        }

        return valido;

    }

    public static boolean camposUbicacion(Context context, TextInputLayout ubicacion, TextInputLayout descripcion){
        boolean valido = true;

        if(descripcion.getEditText().getText().toString().trim().equals("")){
            descripcion.setError(context.getString(R.string.campo_requerido));
            descripcion.requestFocus();
            valido = false;
        }else{
            descripcion.setError(null);
        }

        if(ubicacion.getEditText().getText().toString().trim().equals("")){
            ubicacion.setError(context.getString(R.string.campo_requerido));
            descripcion.requestFocus();
            valido = false;
        }else{
            ubicacion.setError(null);
        }

        return valido;
    }
}
