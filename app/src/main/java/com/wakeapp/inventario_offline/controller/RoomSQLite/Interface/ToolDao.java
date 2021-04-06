package com.wakeapp.inventario_offline.controller.RoomSQLite.Interface;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.wakeapp.inventario_offline.model.ToolDB;

import java.util.List;

@Dao
public interface ToolDao {
    //cuenta las herramientas registradas
    @Query("SELECT COUNT(*) FROM " + ToolDB.TABLE_NAME)
    int sp_Sel_CountTool();

    //Selecciona todas las herramientas
    @Query("SELECT * FROM " + ToolDB.TABLE_NAME)
    List<ToolDB> sp_Sel_AllTool();

    //actualiza la informacion de la herramienta
    @Query("UPDATE " + ToolDB.TABLE_NAME + " SET codigoInterno = :codigoInterno, modelo = :modelo, marca = :marca, observacion = :observacion, idUbicacion =:idUbicacion, idResponsable =:idResponsable WHERE "+ ToolDB.COLUMN_ID +" = :id")
    void sp_Upd_Tool(int id, String codigoInterno, String modelo, String marca, String observacion, int idUbicacion, int idResponsable);

    @Query("DELETE FROM " + ToolDB.TABLE_NAME + " WHERE " + ToolDB.COLUMN_ID + " = :id")
    int sp_Del_Tool(int id);

    //Insertar una herramienta
    @Insert
    long sp_Ins_Tool(ToolDB user);

    @Query("SELECT * FROM " + ToolDB.TABLE_NAME + " WHERE " + ToolDB.COLUMN_ID + " = :id")
    ToolDB sp_Sel_Tool(int id);
}
