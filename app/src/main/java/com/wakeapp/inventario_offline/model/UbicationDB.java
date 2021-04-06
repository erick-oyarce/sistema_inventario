package com.wakeapp.inventario_offline.model;

import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = UbicationDB.TABLE_NAME)
public class UbicationDB {

    /** nombre de la tabla **/
    public static final String TABLE_NAME = "tbl_Ubication";

    public static final String COLUMN_ID= BaseColumns._ID;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo( index = true, name = COLUMN_ID)
    private int id;

    @ColumnInfo(name = "ubicacion")
    private String ubicacion;

    @ColumnInfo(name = "observacion")
    private String observacion;

    public UbicationDB(String ubicacion, String observacion) {
        this.ubicacion = ubicacion;
        this.observacion = observacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
