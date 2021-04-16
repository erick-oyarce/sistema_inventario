package com.wakeapp.inventario_offline.controller.RoomSQLite;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.wakeapp.inventario_offline.controller.RoomSQLite.Interface.FurnitureDao;
import com.wakeapp.inventario_offline.controller.RoomSQLite.Interface.GeneralDao;
import com.wakeapp.inventario_offline.controller.RoomSQLite.Interface.MovilDao;
import com.wakeapp.inventario_offline.controller.RoomSQLite.Interface.NotebookDao;
import com.wakeapp.inventario_offline.controller.RoomSQLite.Interface.OtherDao;
import com.wakeapp.inventario_offline.controller.RoomSQLite.Interface.ResponsableDao;
import com.wakeapp.inventario_offline.controller.RoomSQLite.Interface.ToolDao;
import com.wakeapp.inventario_offline.controller.RoomSQLite.Interface.UbicationDao;
import com.wakeapp.inventario_offline.controller.RoomSQLite.Interface.UserDao;
import com.wakeapp.inventario_offline.model.FurnitureDB;
import com.wakeapp.inventario_offline.model.MovilDB;
import com.wakeapp.inventario_offline.model.NotebookDB;
import com.wakeapp.inventario_offline.model.OtherDB;
import com.wakeapp.inventario_offline.model.ResponsableDB;
import com.wakeapp.inventario_offline.model.ToolDB;
import com.wakeapp.inventario_offline.model.UbicationDB;
import com.wakeapp.inventario_offline.model.UserDB;

@Database(entities = {MovilDB.class, NotebookDB.class, OtherDB.class, ResponsableDB.class, ToolDB.class, UbicationDB.class, UserDB.class, FurnitureDB.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    @SuppressWarnings("WeakerAccess")
    public abstract MovilDao movilDao();
    public abstract NotebookDao notebookDao();
    public abstract OtherDao otherDao();
    public abstract ResponsableDao responsableDao();
    public abstract ToolDao toolDao();
    public abstract UbicationDao ubicationDao();
    public abstract UserDao userDao();
    public abstract FurnitureDao furnitureDao();
    public abstract GeneralDao generalDao();


}
