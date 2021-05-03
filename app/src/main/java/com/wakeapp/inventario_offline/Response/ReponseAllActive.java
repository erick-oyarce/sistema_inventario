package com.wakeapp.inventario_offline.Response;

public class ReponseAllActive {

    private int furnitureDB;
    private int movilDB;
    private int notebookDB;
    private int toolDB;
    private int otherDB;

    public ReponseAllActive(int furnitureDB, int movilDB, int notebookDB, int toolDB, int otherDB) {
        this.furnitureDB = furnitureDB;
        this.movilDB = movilDB;
        this.notebookDB = notebookDB;
        this.toolDB = toolDB;
        this.otherDB = otherDB;
    }

    public int getFurnitureDB() {
        return furnitureDB;
    }

    public void setFurnitureDB(int furnitureDB) {
        this.furnitureDB = furnitureDB;
    }

    public int getMovilDB() {
        return movilDB;
    }

    public void setMovilDB(int movilDB) {
        this.movilDB = movilDB;
    }

    public int getNotebookDB() {
        return notebookDB;
    }

    public void setNotebookDB(int notebookDB) {
        this.notebookDB = notebookDB;
    }

    public int getToolDB() {
        return toolDB;
    }

    public void setToolDB(int toolDB) {
        this.toolDB = toolDB;
    }

    public int getOtherDB() {
        return otherDB;
    }

    public void setOtherDB(int otherDB) {
        this.otherDB = otherDB;
    }
}
