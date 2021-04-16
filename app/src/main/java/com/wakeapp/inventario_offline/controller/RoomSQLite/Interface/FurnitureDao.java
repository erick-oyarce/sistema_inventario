package com.wakeapp.inventario_offline.controller.RoomSQLite.Interface;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.wakeapp.inventario_offline.model.FurnitureDB;

import java.util.List;

@Dao
public interface FurnitureDao {

    //cuenta los muebles
    @Query("SELECT COUNT(*) FROM " + FurnitureDB.TABLE_NAME)
    int sp_Sel_CountFurniture();

    //Selecciona todos los activos en categoria mueble
    @Query("SELECT * FROM " + FurnitureDB.TABLE_NAME)
    List<FurnitureDB> sp_Sel_AllFurniture();

    //actualiza la informacion del mueble
    @Query("UPDATE " + FurnitureDB.TABLE_NAME + " SET codigoInterno = :codigoInterno, tipo =:tipo, observacion = :observacion, idUbicacion =:idUbicacion, fechaAdquisicion =:fechaAdquisicion WHERE "+ FurnitureDB.COLUMN_ID +" = :id")
    void sp_Upd_Other(int id, String codigoInterno, String tipo, String observacion, int idUbicacion, String fechaAdquisicion);

    @Query("DELETE FROM " + FurnitureDB.TABLE_NAME + " WHERE " + FurnitureDB.COLUMN_ID + " = :id")
    int sp_Del_Furniture(int id);

    //Insertar muebles
    @Insert
    long sp_Ins_Furniture(FurnitureDB furniture);

    @Query("SELECT * FROM " + FurnitureDB.TABLE_NAME + " WHERE " + FurnitureDB.COLUMN_ID + " = :id")
    FurnitureDB sp_Sel_Furniture(int id);
}
