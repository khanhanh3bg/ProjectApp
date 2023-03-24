package com.example.project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String nameDB= "Qlproduct";
    private  static final int DB_ver=1;
    private  static DBHelper instance= null;
    private DBHelper (Context context){
        super(context,nameDB,null,DB_ver);
    }
    public synchronized static DBHelper getInstance(Context context){
        if (instance==null){
            instance = new DBHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable="create table product(id integer primary key autoincrement,name varchar(200), url  varchar(200), quantity int)" ;
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
