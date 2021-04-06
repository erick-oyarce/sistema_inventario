package com.wakeapp.inventario_offline.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;


import com.wakeapp.inventario_offline.R;

import es.dmoral.toasty.Toasty;

public class Recuperacion {

    private Activity activity;

    public Recuperacion(Activity activity) {
        this.activity = activity;
    }


    //metodo para validar la conexion a internet
    public class validaInternet extends android.os.AsyncTask<String, Integer, Boolean> {
        ProgressDialog procesaCorreo;
        @Override
        protected Boolean doInBackground(String... params) {
            if(Validador.isOnlineNet()) {
                procesaCorreo.cancel();
                return true;
            }else {
                procesaCorreo.cancel();
                return false;
            }
        }
        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if(result){
                new enviaCorreo().execute();
            }else{
                Toasty.warning(activity, activity.getString(R.string.sin_internet), Toasty.LENGTH_SHORT).show();
            }
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            procesaCorreo = new ProgressDialog(activity);
            procesaCorreo.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            procesaCorreo.setMessage(activity.getString(R.string.comprueba_conexion));
            procesaCorreo.setCanceledOnTouchOutside(false);
            procesaCorreo.show();
        }
    }

    //metodo para enviar correo de credenciales
    public class enviaCorreo extends android.os.AsyncTask<String, Integer, Boolean> {
        ProgressDialog procesaCorreo;
        @Override
        protected Boolean doInBackground(String... params) {

            EnvioCorreoRecuperacion envioCorreoRecuperacion = new EnvioCorreoRecuperacion();
            envioCorreoRecuperacion.EnviarCorreo(activity);

            while (Local.getData("correo", activity, "estado").equals("")){}
            procesaCorreo.cancel();
            if(Local.getData("correo", activity, "estado").equals("1")) {
                return true;
            }else{
                return false;
            }

        }
        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if(result){
                Toasty.success(activity, activity.getString(R.string.correo_enviado), Toast.LENGTH_SHORT, true).show();
            }else{
                Toasty.error(activity, activity.getString(R.string.correo_error), Toast.LENGTH_SHORT, true).show();
            }

        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            procesaCorreo = new ProgressDialog(activity);
            procesaCorreo.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            procesaCorreo.setMessage(activity.getString(R.string.enviando_correo));
            procesaCorreo.setCanceledOnTouchOutside(false);
            procesaCorreo.show();
        }
    }
}
