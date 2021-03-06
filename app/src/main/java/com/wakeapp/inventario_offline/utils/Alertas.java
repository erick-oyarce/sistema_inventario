package com.wakeapp.inventario_offline.utils;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputLayout;
import com.wakeapp.inventario_offline.R;
import com.wakeapp.inventario_offline.controller.Adapters.AdapterUbicaciones;
import com.wakeapp.inventario_offline.controller.RoomSQLite.AppDataBase;
import com.wakeapp.inventario_offline.databinding.ActivityResponsableBinding;
import com.wakeapp.inventario_offline.databinding.ActivityUbicationsBinding;
import com.wakeapp.inventario_offline.model.ResponsableDB;
import com.wakeapp.inventario_offline.model.UbicationDB;
import com.wakeapp.inventario_offline.model.UserDB;
import com.wakeapp.inventario_offline.ui.ResponsableActivity;
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

        if (parts[0].length() > 3) {
            correo.getEditText().setHint(parts[0].substring(0, 3) + "xxxxxxx@" + parts[1]);
        } else {
            correo.getEditText().setHint(parts[0] + "xxxxxxx@" + parts[1]);
        }
        correo.getEditText().requestFocus();

        Button cancelar = dialoglayout.findViewById(R.id.btnCancelar);
        Button agregar = dialoglayout.findViewById(R.id.btnCrear);
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
                if (Validador.validateEmail(con)) {
                    correo.setErrorEnabled(false);
                } else {
                    correo.setErrorEnabled(true);
                    correo.setError(context.getString(R.string.emailinvalido));
                    valido = false;
                }
            }

            if (valido) {
                if (db.userDao().sp_Val_Email(con) != null) {
                    Local.setData("correo", context, "estado", "");
                    alertContrasena.dismiss();
                    Local.setData("correo", context, "recuperacion", con);
                    Recuperacion recuperacion = new Recuperacion(context);
                    recuperacion.new validaInternet().execute();
                } else {
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
        final TextInputLayout contrase??a = dialoglayout.findViewById(R.id.credencialContrasena);
        final TextInputLayout email = dialoglayout.findViewById(R.id.credencialEmail);
        Button cancelar = dialoglayout.findViewById(R.id.btnCancelar);
        Button agregar = (Button) dialoglayout.findViewById(R.id.btnCrear);
        builder.setView(dialoglayout);

        alertCredencial = builder.create();
        alertCredencial.setCancelable(false);

        agregar.setOnClickListener(view -> {

            String nom = nombre.getEditText().getText().toString().trim();
            String con = contrase??a.getEditText().getText().toString().trim();
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
                if (Validador.validateEmail(em)) {
                    email.setErrorEnabled(false);
                } else {
                    email.setErrorEnabled(true);
                    email.setError("El correo no es v??lido");
                    valido = false;
                }
            }
            if (con.equals("")) {
                contrase??a.setErrorEnabled(true);
                contrase??a.setError(context.getString(R.string.campo_requerido));
                valido = false;
            } else {
                contrase??a.setErrorEnabled(false);
            }
            if (con.length() < 6) {
                contrase??a.setErrorEnabled(true);
                contrase??a.setError(context.getString(R.string.contrase??a_corta));
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
        Button agregar = dialoglayout.findViewById(R.id.btnCrear);
        builder.setView(dialoglayout);

        alertUbicacion = builder.create();
        alertUbicacion.setCancelable(false);
        agregar.setOnClickListener(view -> {
            context.startActivity(new Intent(context, UbicationsActivity.class));
            alertUbicacion.dismiss();
        });
        cancelar.setOnClickListener(view -> alertUbicacion.dismiss());
        alertUbicacion.show();
    }

    //alertpara agregar nueva ubicacion
    public static void alertNuevaUbicacion(LayoutInflater layoutInflater, UbicationsActivity context, AppDataBase db, AdapterUbicaciones mAdapter, ActivityUbicationsBinding binding) {

        final AlertDialog alertCreaUbicacion;
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View dialoglayout = layoutInflater.inflate(R.layout.alert_crear_ubicacion, null);
        final TextInputLayout ubicacion = dialoglayout.findViewById(R.id.tilUbicacion);
        final TextInputLayout descripcion = dialoglayout.findViewById(R.id.tilDescripcion);

        Button cancelar = dialoglayout.findViewById(R.id.btnCancelar);
        Button agregar = dialoglayout.findViewById(R.id.btnGuardar);
        builder.setView(dialoglayout);

        alertCreaUbicacion = builder.create();
        alertCreaUbicacion.setCancelable(false);
        agregar.setOnClickListener(view -> {
            if (Validador.camposUbicacion(context, ubicacion, descripcion)) {
                if (db.ubicationDao().sp_Sel_UbicacionNombre(ubicacion.getEditText().getText().toString().trim()) == 0) {
                    UbicationDB ubicationDB = new UbicationDB(ubicacion.getEditText().getText().toString().trim(), descripcion.getEditText().getText().toString().trim());
                    if (db.ubicationDao().sp_Ins_Ubication(ubicationDB) > 0) {
                        binding.indicador.setVisibility(View.GONE);
                        context.cargarUbicaciones();
                        alertCreaUbicacion.dismiss();
                    } else {
                        Toasty.error(context, context.getString(R.string.error_agregar_ubicacion), Toasty.LENGTH_LONG).show();
                    }
                } else {
                    Toasty.warning(context, context.getString(R.string.ubicacion_existe), Toasty.LENGTH_LONG).show();
                }
            }
        });
        cancelar.setOnClickListener(view -> alertCreaUbicacion.dismiss());
        alertCreaUbicacion.show();
    }

    //alertpara agregar nueva ubicacion
    public static void alertEditUbicacion(LayoutInflater layoutInflater, UbicationsActivity context, AppDataBase db, UbicationDB ubication) {

        final AlertDialog alertCreaUbicacion;
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View dialoglayout = layoutInflater.inflate(R.layout.alert_editar_ubicacion, null);
        final TextInputLayout ubicacion = dialoglayout.findViewById(R.id.tilUbicacion);
        final TextInputLayout descripcion = dialoglayout.findViewById(R.id.tilDescripcion);

        ubicacion.getEditText().setText(ubication.getUbicacion());
        descripcion.getEditText().setText(ubication.getObservacion());

        Button cancelar = dialoglayout.findViewById(R.id.btnCancelar);
        Button agregar = dialoglayout.findViewById(R.id.btnGuardar);
        builder.setView(dialoglayout);

        alertCreaUbicacion = builder.create();
        alertCreaUbicacion.setCancelable(false);
        agregar.setOnClickListener(view -> {
            if (Validador.camposUbicacion(context, ubicacion, descripcion)) {
                if (db.ubicationDao().sp_Sel_UbicacionNombre(ubicacion.getEditText().getText().toString().trim()) == 0) {
                    if (db.ubicationDao().sp_Upd_Ubication(ubication.getId(), ubicacion.getEditText().getText().toString().trim(), descripcion.getEditText().getText().toString().trim()) > 0) {
                        context.cargarUbicaciones();
                        alertCreaUbicacion.dismiss();
                    } else {
                        Toasty.error(context, context.getString(R.string.error_editar_ubicacion), Toasty.LENGTH_LONG).show();
                    }
                } else {
                    Toasty.warning(context, context.getString(R.string.ubicacion_existe), Toasty.LENGTH_LONG).show();
                }
            }
        });
        cancelar.setOnClickListener(view -> alertCreaUbicacion.dismiss());
        alertCreaUbicacion.show();
    }

    //alertpara agregar un nuevo responsable
    public static void alertNuevoResponsable(LayoutInflater layoutInflater, ResponsableActivity context, AppDataBase db, ActivityResponsableBinding binding) {

        final AlertDialog alertCrearResponsable;
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View dialoglayout = layoutInflater.inflate(R.layout.alert_crear_responsable, null);
        final TextInputLayout identificador = dialoglayout.findViewById(R.id.tilIdentificador);
        final TextInputLayout nombre = dialoglayout.findViewById(R.id.tilNombre);
        final TextInputLayout apellido = dialoglayout.findViewById(R.id.tilApellido);
        final TextInputLayout descripcion = dialoglayout.findViewById(R.id.tilDescripcion);

        Button cancelar = dialoglayout.findViewById(R.id.btnCancelar);
        Button agregar = dialoglayout.findViewById(R.id.btnGuardar);
        builder.setView(dialoglayout);

        alertCrearResponsable = builder.create();
        alertCrearResponsable.setCancelable(false);
        agregar.setOnClickListener(view -> {
            if (Validador.camposResponsable(context, identificador, nombre, apellido, descripcion)) {
                if (db.responsableDao().sp_Sel_IdentificadorExist(identificador.getEditText().getText().toString().trim()) == 0) {
                    ResponsableDB responsableDB = new ResponsableDB(identificador.getEditText().getText().toString().trim(), nombre.getEditText().getText().toString().trim(), apellido.getEditText().getText().toString().trim(), descripcion.getEditText().getText().toString().trim());
                    if (db.responsableDao().sp_Ins_Responsable(responsableDB) > 0) {
                        binding.indicador.setVisibility(View.GONE);
                        context.cargaResponsables();
                        alertCrearResponsable.dismiss();
                    } else {
                        Toasty.error(context, context.getString(R.string.error_agregar_responsable), Toasty.LENGTH_LONG).show();
                    }
                } else {
                    Toasty.warning(context, context.getString(R.string.identificador_existe), Toasty.LENGTH_LONG).show();
                }
            }
        });
        cancelar.setOnClickListener(view -> alertCrearResponsable.dismiss());
        alertCrearResponsable.show();
    }

    //alertpara agregar un nuevo responsable
    public static void alertEditarResponsable(LayoutInflater layoutInflater, ResponsableActivity context, AppDataBase db, ResponsableDB responsable) {

        final AlertDialog alerteditarResponsable;
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View dialoglayout = layoutInflater.inflate(R.layout.alert_editar_responsable, null);
        final TextInputLayout identificador = dialoglayout.findViewById(R.id.tilIdentificador);
        final TextInputLayout nombre = dialoglayout.findViewById(R.id.tilNombre);
        final TextInputLayout apellido = dialoglayout.findViewById(R.id.tilApellido);
        final TextInputLayout descripcion = dialoglayout.findViewById(R.id.tilDescripcion);

        identificador.getEditText().setText(responsable.getIdentificador());
        nombre.getEditText().setText(responsable.getNombre());
        apellido.getEditText().setText(responsable.getApellido());
        descripcion.getEditText().setText(responsable.getDescripcion());

        Button cancelar = dialoglayout.findViewById(R.id.btnCancelar);
        Button agregar = dialoglayout.findViewById(R.id.btnGuardar);
        builder.setView(dialoglayout);

        alerteditarResponsable = builder.create();
        alerteditarResponsable.setCancelable(false);
        agregar.setOnClickListener(view -> {
            if (Validador.camposResponsable(context, identificador, nombre, apellido, descripcion)) {
                if (db.responsableDao().sp_Upd_Responsable(responsable.getId(), responsable.getIdentificador(), nombre.getEditText().getText().toString().trim(), apellido.getEditText().getText().toString().trim(), descripcion.getEditText().getText().toString().trim()) > 0) {
                    context.cargaResponsables();
                    alerteditarResponsable.dismiss();
                } else {
                    Toasty.error(context, context.getString(R.string.error_editar_datos), Toasty.LENGTH_LONG).show();
                }

            }
        });
        cancelar.setOnClickListener(view -> {
           context.cargaResponsables();
            alerteditarResponsable.dismiss();
        });
        alerteditarResponsable.show();
    }

}
