package com.wakeapp.inventario_offline.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputLayout;
import com.wakeapp.inventario_offline.R;
import com.wakeapp.inventario_offline.controller.Adapters.AdapterUbicaciones;
import com.wakeapp.inventario_offline.controller.RoomSQLite.AppDataBase;
import com.wakeapp.inventario_offline.model.UbicationDB;
import com.wakeapp.inventario_offline.model.UserDB;
import com.wakeapp.inventario_offline.ui.UbicationsActivity;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class Alertas {

    /************* Recuperacion de credenciales **********************/
    //alert para enviar claves de acceso al usuario
    public static void recuperaCredenciales(LayoutInflater layoutInflater, Activity context, AppDataBase db) {

        final AlertDialog alertContrasena;
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View dialoglayout = layoutInflater.inflate(R.layout.solicita_correo, null);
        final TextInputLayout correo = dialoglayout.findViewById(R.id.credencialCorreo);
        List<UserDB> user = db.userDao().sp_Sel_AllUser();
        String parts[] = user.get(0).getEmail().split("@");

        if(parts[0].length() > 3) {
            correo.getEditText().setHint(parts[0].substring(0, 3) + "xxxxxxx@" + parts[1]);
        }else{
            correo.getEditText().setHint(parts[0] + "xxxxxxx@" + parts[1]);
        }
        correo.getEditText().requestFocus();

        Button cancelar = dialoglayout.findViewById(R.id.btnCancelar);
        Button agregar =  dialoglayout.findViewById(R.id.btnCrear);
        builder.setView(dialoglayout);

        alertContrasena = builder.create();
        alertContrasena.setCancelable(false);
        agregar.setOnClickListener(view -> {
            String con = correo.getEditText().getText().toString().trim();
            boolean valido = true;
            if (con.equals("")) {
                correo.setErrorEnabled(true);
                correo.setError(context.getString(R.string.campo_requerido));
                valido = false;
            } else {
                if(Validador.validateEmail(con)){
                    correo.setErrorEnabled(false);
                }else{
                    correo.setErrorEnabled(true);
                    correo.setError(context.getString(R.string.emailinvalido));
                    valido = false;
                }
            }

            if (valido) {
                if(db.userDao().sp_Val_Email(con) != null){
                    Local.setData("correo", context, "estado","");
                    alertContrasena.dismiss();
                    Local.setData("correo", context, "recuperacion", con);
                    Recuperacion recuperacion = new Recuperacion(context);
                    recuperacion.new validaInternet().execute();
                }else{
                    correo.setErrorEnabled(true);
                    correo.setError(context.getString(R.string.correo_no_corresponde));
                }
            }
        });
        cancelar.setOnClickListener(view -> alertContrasena.dismiss());
        alertContrasena.show();
    }


    /************* Creacion de usuario primer acceso ******************/
    //alert para solicitar permisos al usuario
    public static void alertCredenciales(Activity context, LayoutInflater layoutInflater, AppDataBase bd) {

        final AlertDialog alertCredencial;
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialoglayout = layoutInflater.inflate(R.layout.primera_credencial, null);
        final TextInputLayout nombre = dialoglayout.findViewById(R.id.credencialUsuario);
        final TextInputLayout contraseña = dialoglayout.findViewById(R.id.credencialContrasena);
        final TextInputLayout email = dialoglayout.findViewById(R.id.credencialEmail);
        Button cancelar = dialoglayout.findViewById(R.id.btnCancelar);
        Button agregar = (Button) dialoglayout.findViewById(R.id.btnCrear);
        builder.setView(dialoglayout);

        alertCredencial = builder.create();
        alertCredencial.setCancelable(false);

        agregar.setOnClickListener(view -> {

            String nom = nombre.getEditText().getText().toString().trim();
            String con = contraseña.getEditText().getText().toString().trim();
            String em = email.getEditText().getText().toString().trim();
            boolean valido = true;

            if (nom.equals("")) {
                nombre.setErrorEnabled(true);
                nombre.setError(context.getString(R.string.campo_requerido));
                valido = false;
            } else {
                nombre.setErrorEnabled(false);
            }
            if (em.equals("")) {
                email.setErrorEnabled(true);
                email.setError(context.getString(R.string.campo_requerido));
                valido = false;
            } else {
                if(Validador.validateEmail(em)){
                    email.setErrorEnabled(false);
                }else{
                    email.setErrorEnabled(true);
                    email.setError("El correo no es válido");
                    valido = false;
                }
            }
            if (con.equals("")) {
                contraseña.setErrorEnabled(true);
                contraseña.setError(context.getString(R.string.campo_requerido));
                valido = false;
            } else {
                contraseña.setErrorEnabled(false);
            }
            if (con.length() < 6) {
                contraseña.setErrorEnabled(true);
                contraseña.setError(context.getString(R.string.contraseña_corta));
                valido = false;
            }
            if (valido) {
                alertCredencial.dismiss();
                UserDB userDB = new UserDB(nom, con, em);
                bd.userDao().sp_Ins_User(userDB);
            }
        });
        cancelar.setOnClickListener(view -> {
            alertCredencial.dismiss();
            context.finishAffinity();
            System.exit(0);
        });
        alertCredencial.show();
    }


    //alert soliciar la creacion de una ubicacion cuando no existe
    public static void validarCrearUbicacion(LayoutInflater layoutInflater, Activity context) {

        final AlertDialog alertUbicacion;
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View dialoglayout = layoutInflater.inflate(R.layout.alert_sin_ubicacion, null);

        Button cancelar = dialoglayout.findViewById(R.id.btnCancelar);
        Button agregar =  dialoglayout.findViewById(R.id.btnCrear);
        builder.setView(dialoglayout);

        alertUbicacion = builder.create();
        alertUbicacion.setCancelable(false);
        agregar.setOnClickListener(view ->  {context.startActivity(new Intent(context, UbicationsActivity.class)); alertUbicacion.dismiss();} );
        cancelar.setOnClickListener(view -> alertUbicacion.dismiss());
        alertUbicacion.show();
    }

    //alertpara agregar nueva ubicacion
    public static void alertNuevaUbicacion(LayoutInflater layoutInflater, UbicationsActivity context, AppDataBase db, AdapterUbicaciones mAdapter) {

        final AlertDialog alertCreaUbicacion;
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View dialoglayout = layoutInflater.inflate(R.layout.alert_crear_ubicacion, null);
        final TextInputLayout ubicacion = dialoglayout.findViewById(R.id.tilUbicacion);
        final TextInputLayout descripcion = dialoglayout.findViewById(R.id.tilDescripcion);

        Button cancelar = dialoglayout.findViewById(R.id.btnCancelar);
        Button agregar =  dialoglayout.findViewById(R.id.btnGuardar);
        builder.setView(dialoglayout);

        alertCreaUbicacion = builder.create();
        alertCreaUbicacion.setCancelable(false);
        agregar.setOnClickListener(view -> {
           if(Validador.camposUbicacion(context, ubicacion, descripcion)){
                if(db.ubicationDao().sp_Sel_UbicacionNombre(ubicacion.getEditText().getText().toString().trim()) == 0){
                    UbicationDB ubicationDB = new UbicationDB(ubicacion.getEditText().getText().toString().trim(),descripcion.getEditText().getText().toString().trim());
                    if(db.ubicationDao().sp_Ins_Ubication(ubicationDB) > 0) {
                        context.cargarUbicaciones();
                        alertCreaUbicacion.dismiss();
                    }else{
                        Toasty.error(context, context.getString(R.string.error_agregar_ubicacion), Toasty.LENGTH_LONG).show();
                    }
                }else{
                    Toasty.warning(context, context.getString(R.string.ubicacion_existe), Toasty.LENGTH_LONG).show();
                }
           }
        });
        cancelar.setOnClickListener(view -> alertCreaUbicacion.dismiss());
        alertCreaUbicacion.show();
    }

}
