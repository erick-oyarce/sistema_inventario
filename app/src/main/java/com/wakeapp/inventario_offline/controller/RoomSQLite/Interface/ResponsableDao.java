package com.wakeapp.inventario_offline.controller.RoomSQLite.Interface;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.wakeapp.inventario_offline.model.ResponsableDB;

import java.util.List;

@Dao
public interface ResponsableDao {

    //cuenta los ResponsableDB registrados
    @Query("SELECT COUNT(*) FROM " + ResponsableDB.TABLE_NAME)
    int sp_Sel_CountResponsable();

    //Selecciona todos los ResponsableDB registrados
    @Query("SELECT * FROM " + ResponsableDB.TABLE_NAME)
    List<ResponsableDB> sp_Sel_AllResponsabler();

    //actualiza la informacion del ResponsableDB
    @Query("UPDATE " + ResponsableDB.TABLE_NAME + " SET identificador = :identificador, nombre = :nombre, Apellido = :Apellido, Descripcion = :Descripcion WHERE "+ ResponsableDB.COLUMN_ID +" = :id")
    void sp_Upd_Responsable(int id, String identificador, String nombre, String Apellido, String Descripcion);

    @Query("DELETE FROM " + ResponsableDB.TABLE_NAME + " WHERE " + ResponsableDB.COLUMN_ID + " = :id")
    int sp_Del_Responsable(int id);

    //Insertar un usuario
    @Insert
    long sp_Ins_Responsable(ResponsableDB user);

    @Query("SELECT * FROM " + ResponsableDB.TABLE_NAME + " WHERE " + ResponsableDB.COLUMN_ID + " = :id")
    ResponsableDB sp_Sel_Responsable(int id);
}
