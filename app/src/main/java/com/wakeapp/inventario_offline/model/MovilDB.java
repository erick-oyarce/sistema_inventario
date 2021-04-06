package com.wakeapp.inventario_offline.model;

import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = MovilDB.TABLE_NAME)
public class MovilDB {

    /** nombre de la tabla **/
    public static final String TABLE_NAME = "tbl_Movil";

    public static final String COLUMN_ID= BaseColumns._ID;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo( index = true, name = COLUMN_ID)
    private int id;

    @ColumnInfo(name = "codigoInterno")
    private String codigoInterno;

    @ColumnInfo(name = "imei")
    private String imei;

    @ColumnInfo(name = "modelo")
    private String modelo;

    @ColumnInfo(name = "marca")
    private String marca;

    @ColumnInfo(name = "numero")
    private String numero;

    @ColumnInfo(name = "observacion")
    private String observacion;

    @ColumnInfo(name = "idUbicacion")
    private int idUbicacion;

    @ColumnInfo(name = "idResponsable")
    private int idresponsable;

    public MovilDB(String codigoInterno, String imei, String modelo, String marca, String numero, String observacion, int idUbicacion, int idresponsable) {
        this.codigoInterno = codigoInterno;
        this.imei = imei;
        this.modelo = modelo;
        this.marca = marca;
        this.numero = numero;
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

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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
