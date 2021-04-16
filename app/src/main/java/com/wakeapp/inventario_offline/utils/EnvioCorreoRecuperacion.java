package com.wakeapp.inventario_offline.utils;

import android.content.Context;
import android.os.StrictMode;

import androidx.room.Room;

import com.wakeapp.inventario_offline.controller.RoomSQLite.AppDataBase;
import com.wakeapp.inventario_offline.model.UserDB;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvioCorreoRecuperacion {

    public void EnviarCorreo(Context context){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        AppDataBase db = Room.databaseBuilder(context, AppDataBase.class, Constants.BD_NAME)
                .allowMainThreadQueries()
                .build();

        String correo = Local.getData("correo", context, "recuperacion");
        UserDB user = db.userDao().sp_Val_Email(correo);

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable",true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        try {

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(Constants.EMAIL_REC, Constants.PASS_EMAIL_REC);
                        }
                    });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("eoyarcepizarro@gmail.com"));

            message.addRecipient(Message.RecipientType.TO,new InternetAddress(correo.trim()));
            message.setSubject("Recuperacion de credenciales para Inventario Offline");
            String mensaje = "<h1>Credenciales de acceso</h1><p>Usuario: "+user.getName()+"</p><p>Clave de acceso: "+user.getPassword()+"</p>";
            message.setContent(mensaje,"text/html" );
            Transport.send(message);
            Local.setData("correo", context, "estado","1");


        } catch (MessagingException e) {
            Local.setData("correo", context, "estado","2");


        }
    }

}
