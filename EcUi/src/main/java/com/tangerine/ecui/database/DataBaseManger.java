package com.tangerine.ecui.database;

import android.database.sqlite.SQLiteDatabase;

import org.litepal.tablemanager.Connector;

public class DataBaseManger {
    private static class DataBaseHolder {


    private static SQLiteDatabase getInStance() {
        return Connector.getDatabase();
    }

}
    public static void getDataBase(){
        DataBaseHolder.getInStance();
    }
}
