package com.wakeapp.inventario_offline.Repositories;

import androidx.lifecycle.LiveData;

import com.wakeapp.inventario_offline.Request.ActiveApiClient;
import com.wakeapp.inventario_offline.Response.ReponseAllActive;
import com.wakeapp.inventario_offline.controller.RoomSQLite.AppDataBase;

import java.util.List;

public class ActiveRepository {

    private static ActiveRepository instance;
    private ActiveApiClient activeApiClient;



    public static ActiveRepository getInstance(){
        if(instance == null){
            instance = new ActiveRepository();
        }
        return instance;
    }

    private ActiveRepository(){
        activeApiClient = ActiveApiClient.getInstance();
    }

    public LiveData<List<ReponseAllActive>> getActives(){
        return activeApiClient.getActives();
    }

    //2- calling method in repository
    public void searchActives(AppDataBase dataBase){

        activeApiClient.searchAllActive(dataBase);

    }
}
