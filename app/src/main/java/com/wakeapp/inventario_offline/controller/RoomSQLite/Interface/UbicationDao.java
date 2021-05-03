package com.wakeapp.inventario_offline.controller.RoomSQLite.Interface;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.wakeapp.inventario_offline.model.UbicationDB;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface UbicationDao {

    //cuenta las ubicaciones
    @Query("SELECT COUNT(*) FROM " + UbicationDB.TABLE_NAME)
    int sp_Sel_CountUbication();

    //Selecciona todas las ubicaciones
    @Query("SELECT * FROM " + UbicationDB.TABLE_NAME)
    List<UbicationDB> sp_Sel_AllUbications();

    //actualiza la informacion de la ubicacion
    @Query("UPDATE " + UbicationDB.TABLE_NAME + " SET ubicacion = :ubicacion, observacion = :observacion WHERE "+ UbicationDB.COLUMN_ID +" = :id")
    int sp_Upd_Ubication(int id, String ubicacion, String observacion);

    @Query("DELETE FROM " + UbicationDB.TABLE_NAME + " WHERE " + UbicationDB.COLUMN_ID + " = :id")
    int sp_Del_Ubication(int id);

    //Insertar una UbicationDB
    @Insert
    long sp_Ins_Ubication(UbicationDB ubication);

    @Query("SELECT * FROM " + UbicationDB.TABLE_NAME + " WHERE " + UbicationDB.COLUMN_ID + " = :id")
    UbicationDB sp_Sel_Ubication(int id);

    //valida si el nombre de la ubicación ya se encuentra registrado
    @Query("SELECT COUNT(*) FROM " + UbicationDB.TABLE_NAME + " WHERE ubicacion =:ubicacion")
    int sp_Sel_UbicacionNombre(String ubicacion);
}
