package com.wakeapp.inventario_offline.controller.RoomSQLite.Interface;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.wakeapp.inventario_offline.model.OtherDB;

import java.util.List;

@Dao
public interface OtherDao {
    //cuenta los otros activos
    @Query("SELECT COUNT(*) FROM " + OtherDB.TABLE_NAME)
    int sp_Sel_CountOther();

    //Selecciona todos los activos en categoria otros
    @Query("SELECT * FROM " + OtherDB.TABLE_NAME)
    List<OtherDB> sp_Sel_AllOther();

    //actualiza la informacion del activo
    @Query("UPDATE " + OtherDB.TABLE_NAME + " SET codigoInterno = :codigoInterno, observacion = :observacion, idUbicacion =:idUbicacion, idResponsable =:idResponsable WHERE "+ OtherDB.COLUMN_ID +" = :id")
    void sp_Upd_Other(int id, String codigoInterno, String observacion, int idUbicacion, int idResponsable);

    @Query("DELETE FROM " + OtherDB.TABLE_NAME + " WHERE " + OtherDB.COLUMN_ID + " = :id")
    int sp_Del_Other(int id);

    //Insertar otros
    @Insert
    long sp_Ins_Other(OtherDB other);

    @Query("SELECT * FROM " + OtherDB.TABLE_NAME + " WHERE " + OtherDB.COLUMN_ID + " = :id")
    OtherDB sp_Sel_Other(int id);
}
