package com.wakeapp.inventario_offline.controller.RoomSQLite.Interface;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.wakeapp.inventario_offline.model.UserDB;

import java.util.List;

@Dao
public interface UserDao {

    //cuenta los usuarios registrados
    @Query("SELECT COUNT(*) FROM " + UserDB.TABLE_NAME)
    int sp_Sel_CountUser();

    //Selecciona todos los usuarios registrados
    @Query("SELECT * FROM " + UserDB.TABLE_NAME)
    List<UserDB> sp_Sel_AllUser();

    //actualiza la informacion del usuario
    @Query("UPDATE " + UserDB.TABLE_NAME + " SET name = :name, password = :password, email = :email WHERE "+ UserDB.COLUMN_ID +" = :id")
    void sp_Upd_User(int id, String name, String password, String email);

    @Query("DELETE FROM " + UserDB.TABLE_NAME + " WHERE " + UserDB.COLUMN_ID + " = :id")
    int sp_Del_User(int id);

    //Insertar un usuario
    @Insert
    long sp_Ins_User(UserDB user);

    @Query("SELECT * FROM " + UserDB.TABLE_NAME + " WHERE " + UserDB.COLUMN_ID + " = :id LIMIT 1" )
    UserDB sp_Sel_User(int id);

    @Query("SELECT * FROM " + UserDB.TABLE_NAME + " WHERE name =:name AND password =:password")
    UserDB sp_Val_User(String name, String password);

    @Query("SELECT * FROM " + UserDB.TABLE_NAME + " WHERE email =:email LIMIT 1")
    UserDB sp_Val_Email(String email);
}
