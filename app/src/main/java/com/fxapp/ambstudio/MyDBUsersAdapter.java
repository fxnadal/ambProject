package com.fxapp.ambstudio;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fx on 06/06/2016.
 */
public class MyDBUsersAdapter {
    // Definiciones y constantes
    private static final String DATABASE_NAME = "dbamb.db";
    private static final String DATABASE_TABLE = "usuarios";
    private static final int DATABASE_VERSION = 1;

    private static final String NOMBRE = "nombre";
    private static final String DNI = "dni";
    private static final String CONTRA = "contraseña";
    private static final Boolean PERMISOS = true;

    private static final String DATABASE_CREATE = "CREATE TABLE "+DATABASE_TABLE+" (_id integer primary key autoincrement, title text, year integer);";
    private static final String DATABASE_DROP = "DROP TABLE IF EXISTS "+DATABASE_TABLE+";";

    // Contexto de la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;

    public MyDBUsersAdapter(Context c){
        context = c;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        //OJO open();
    }

    public void open(){

        try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            db = dbHelper.getReadableDatabase();
        }

    }

    public void insertarNombre(String n, int id){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(NOMBRE,n);
        db.insert(DATABASE_TABLE,null,newValues);
    }

    public void modificarAnyo(int id, int y){
        //Creamos un nuevo ContentValues
        ContentValues newValues = new ContentValues();
        //Asignamos el valor del campo a modificar
        newValues.put(CONTRA,y);
        db.update(DATABASE_TABLE,newValues,"_id=" + id,null);
    }


    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP);
            onCreate(db);
        }

    }
}

