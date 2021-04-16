package com.wakeapp.inventario_offline.model;

import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = FurnitureDB.TABLE_NAME)
public class FurnitureDB {

    /** nombre de la tabla **/
    public static final String TABLE_NAME = "tbl_Furniture";

    public static final String COLUMN_ID= BaseColumns._ID;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo( index = true, name = COLUMN_ID)
    private int id;

    @ColumnInfo(name = "codigoInterno")
    private String codigoInterno;

    @ColumnInfo(name = "tipo")
    private String tipo;

    @ColumnInfo(name = "observacion")
    private String observacion;

    @ColumnInfo(name = "idUbicacion")
    private int idUbicacion;

    @ColumnInfo(name = "fechaAdquisicion")
    private String fechaAdquisicion;

    public FurnitureDB(String codigoInterno, String tipo, String observacion, int idUbicacion, String fechaAdquisicion) {
        this.codigoInterno = codigoInterno;
        this.tipo = tipo;
        this.observacion = observacion;
        this.idUbicacion = idUbicacion;
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoInterno() {
        return codigoInterno;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public String getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(String fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }
}
