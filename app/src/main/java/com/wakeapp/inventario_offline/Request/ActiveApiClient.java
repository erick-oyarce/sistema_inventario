package com.wakeapp.inventario_offline.Request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wakeapp.inventario_offline.Response.ReponseAllActive;
import com.wakeapp.inventario_offline.controller.RoomSQLite.AppDataBase;
import com.wakeapp.inventario_offline.ui.ResponsableActivity;
import com.wakeapp.inventario_offline.utils.AppExecutors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ActiveApiClient {

    private static ActiveApiClient instance;

    private MutableLiveData<List<ReponseAllActive>> infoActive;

    private RetrieveAllActive retrieveAllActive;

    //Singleton
    public static ActiveApiClient getInstance(){
        if(instance == null){
            instance = new ActiveApiClient();
        }
        return instance;
    }
    private ActiveApiClient(){

        infoActive = new MutableLiveData<>();
    }

    public LiveData<List<ReponseAllActive>> getActives(){
        return infoActive;
    }

    //1- this method that we are going to call through the classes
    public void searchAllActive(AppDataBase db){

        if(retrieveAllActive != null){
            retrieveAllActive = null;
        }

        retrieveAllActive = new RetrieveAllActive(db);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveAllActive);

        AppExecutors.getInstance().networkIO().schedule(() -> {
            //cancelling retrofit call
            myHandler.cancel(true);
        }, 3000, TimeUnit.MILLISECONDS);
    }


    private class RetrieveAllActive implements Runnable{

        private AppDataBase query;

        public RetrieveAllActive(AppDataBase db) {
            this.query = db;

        }

        @Override
        public void run() {
            List<ReponseAllActive>response = getActives(query);

            if(response != null){
                infoActive.postValue(response);
            }else{
                Log.d("ActiveApiClient", "Error ReponseAllActive");
            }

        }

        private List<ReponseAllActive> getActives(AppDataBase bd){
            return Collections.singletonList(new ReponseAllActive(
                    bd.notebookDao().sp_Sel_CountNotebook(),
                    bd.toolDao().sp_Sel_CountTool(),
                    bd.movilDao().sp_Sel_CountMovil(),
                    bd.otherDao().sp_Sel_CountOther(),
                    bd.furnitureDao().sp_Sel_CountFurniture()
            ));
        }

    }
}
