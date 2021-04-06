package com.wakeapp.inventario_offline.model;

import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = ToolDB.TABLE_NAME)
public class ToolDB {

    /** nombre de la tabla **/
    public static final String TABLE_NAME = "tbl_Tool";

    public static final String COLUMN_ID= BaseColumns._ID;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo( index = true, name = COLUMN_ID)
    private int id;

    @ColumnInfo(name = "codigoInterno")
    private String codigoInterno;

    @ColumnInfo(name = "modelo")
    private String modelo;

    @ColumnInfo(name = "marca")
    private String marca;

    @ColumnInfo(name = "observacion")
    private String observacion;

    @ColumnInfo(name = "idUbicacion")
    private int idUbicacion;

    @ColumnInfo(name = "idResponsable")
    private int idresponsable;

    public ToolDB(String codigoInterno, String modelo, String marca, String observacion, int idUbicacion, int idresponsable) {
        this.codigoInterno = codigoInterno;
        this.modelo = modelo;
        this.marca = marca;
        this.observacion = observacion;
        this.idUbicacion = idUbicacion;
        this.idresponsable = idresponsable;
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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

    public int getIdresponsable() {
        return idresponsable;
    }

    public void setIdresponsable(int idresponsable) {
        this.idresponsable = idresponsable;
    }
}
