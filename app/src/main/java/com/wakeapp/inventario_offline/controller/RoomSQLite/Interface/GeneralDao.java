package com.wakeapp.inventario_offline.controller.RoomSQLite.Interface;

import androidx.room.Dao;
import androidx.room.Query;

import com.wakeapp.inventario_offline.model.FurnitureDB;
import com.wakeapp.inventario_offline.model.MovilDB;
import com.wakeapp.inventario_offline.model.NotebookDB;
import com.wakeapp.inventario_offline.model.OtherDB;
import com.wakeapp.inventario_offline.model.ToolDB;

@Dao
public interface GeneralDao {

    //Revisar en todas las tablas si existe por lo menos 1 item registrado
    @Query("SELECT COUNT(*) FROM " + FurnitureDB.TABLE_NAME + "," + MovilDB.TABLE_NAME + "," + NotebookDB.TABLE_NAME + "," + OtherDB.TABLE_NAME + "," + MovilDB.TABLE_NAME + "," + ToolDB.TABLE_NAME)
    int sp_Sel_Count_AllActive();
}
