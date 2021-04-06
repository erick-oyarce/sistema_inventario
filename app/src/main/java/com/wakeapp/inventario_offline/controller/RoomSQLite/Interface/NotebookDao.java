package com.wakeapp.inventario_offline.controller.RoomSQLite.Interface;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.wakeapp.inventario_offline.model.NotebookDB;

import java.util.List;

@Dao
public interface NotebookDao {

    //cuenta los NotebookDB registrados
    @Query("SELECT COUNT(*) FROM " + NotebookDB.TABLE_NAME)
    int sp_Sel_CountNotebook();

    //Selecciona todos los NotebookDB registrados
    @Query("SELECT * FROM " + NotebookDB.TABLE_NAME)
    List<NotebookDB> sp_Sel_AllNotebook();

    //actualiza la informacion del NotebookDB
    @Query("UPDATE " + NotebookDB.TABLE_NAME + " SET codigoInterno = :codigoInterno, numeroSerie =:numeroSerie, modelo = :modelo, marca = :marca, observacion = :observacion, idUbicacion =:idUbicacion, idResponsable =:idResponsable WHERE "+ NotebookDB.COLUMN_ID +" = :id")
    void sp_Upd_Notebook(int id, String codigoInterno, String numeroSerie, String modelo, String marca, String observacion, int idUbicacion, int idResponsable);

    @Query("DELETE FROM " + NotebookDB.TABLE_NAME + " WHERE " + NotebookDB.COLUMN_ID + " = :id")
    int sp_Del_Notebook(int id);

    //Insertar un usuario
    @Insert
    long sp_Ins_Notebook(NotebookDB user);

    @Query("SELECT * FROM " + NotebookDB.TABLE_NAME + " WHERE " + NotebookDB.COLUMN_ID + " = :id")
    NotebookDB sp_Sel_Notebook(int id);
}
