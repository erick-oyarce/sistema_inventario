package com.wakeapp.inventario_offline.model;

import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = ResponsableDB.TABLE_NAME)
public class ResponsableDB {

    /** nombre de la tabla **/
    public static final String TABLE_NAME = "tbl_Responsable";

    public static final String COLUMN_ID= BaseColumns._ID;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo( index = true, name = COLUMN_ID)
    private int id;

    @ColumnInfo(name = "identificador")
    private String identificador;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "Apellido")
    private String apellido;

    @ColumnInfo(name = "Descripcion")
    private String descripcion;

    public ResponsableDB(String identificador, String nombre, String apellido, String descripcion) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
