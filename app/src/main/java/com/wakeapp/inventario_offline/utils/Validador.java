package com.wakeapp.inventario_offline.utils;

import android.util.Patterns;
import com.google.android.material.textfield.TextInputLayout;

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
}
