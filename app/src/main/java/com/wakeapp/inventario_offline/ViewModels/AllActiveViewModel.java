package com.wakeapp.inventario_offline.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.wakeapp.inventario_offline.Repositories.ActiveRepository;
import com.wakeapp.inventario_offline.Response.ReponseAllActive;
import com.wakeapp.inventario_offline.controller.RoomSQLite.AppDataBase;

import java.util.List;

public class AllActiveViewModel extends ViewModel {

    private ActiveRepository activeRepository;

    public AllActiveViewModel(){
        activeRepository = ActiveRepository.getInstance();
    }

    public LiveData<List<ReponseAllActive>> getAllActive(){
        return activeRepository.getActives();
    }

    public void searchActives(AppDataBase dataBase){
        activeRepository.searchActives(dataBase);
    }
}
