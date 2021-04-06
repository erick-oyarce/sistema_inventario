package com.wakeapp.inventario_offline.controller.RoomSQLite.Interface;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.wakeapp.inventario_offline.model.MovilDB;

import java.util.List;

@Dao
public interface MovilDao {

    //cuenta los moviles registrados
    @Query("SELECT COUNT(*) FROM " + MovilDB.TABLE_NAME)
    int sp_Sel_CountMovil();

    //Selecciona todos los MovilDB registrados
    @Query("SELECT * FROM " + MovilDB.TABLE_NAME)
    List<MovilDB> sp_Sel_AllMovil();

    //actualiza la informacion del MovilDB
    @Query("UPDATE " + MovilDB.TABLE_NAME + " SET codigoInterno = :codigoInterno, modelo = :modelo, marca = :marca, numero = :numero, observacion = :observacion, idUbicacion =:idUbicacion, idResponsable =:idResponsable WHERE "+ MovilDB.COLUMN_ID +" = :id")
    void sp_Upd_Movil(int id, String codigoInterno, String modelo, String marca, String numero, String observacion, int idUbicacion, int idResponsable);

    @Query("DELETE FROM " + MovilDB.TABLE_NAME + " WHERE " + MovilDB.COLUMN_ID + " = :id")
    int sp_Del_Movil(int id);

    //Insertar un MovilDB
    @Insert
    long sp_Ins_Movil(MovilDB user);

    @Query("SELECT * FROM " + MovilDB.TABLE_NAME + " WHERE " + MovilDB.COLUMN_ID + " = :id")
    MovilDB sp_Sel_Movil(int id);
}
