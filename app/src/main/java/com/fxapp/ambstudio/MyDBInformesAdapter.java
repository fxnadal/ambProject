package com.fxapp.ambstudio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by fx on 06/06/2016.
 */
public class MyDBInformesAdapter {
    // Definiciones y constantes
    private static final String DATABASE_NAME = "dbamb.db";
    private static final String DATABASE_TABLE = "informes";
    private static final int DATABASE_VERSION = 1;

    private static final String TRABAJADOR = "trabajador";
    private static final String NUM_OBRA="numobra";
    private static final String FECHA_OBRA = "fechaobra";
    private static final String DURACION = "duracion";
    private static final String LOCALIZACION = "localizacion";
    private static final String HORA_INICIO = "horainicio";
    private static final String HORA_FIN = "horafin";




    private static final String DATABASE_CREATE = "CREATE TABLE "+DATABASE_TABLE+
            " (_id integer primary key autoincrement, trabajador text, " +
            "fechaobra text, duracion text, localizacion text, horainicio text, horafin text);";
    private static final String DATABASE_DROP = "DROP TABLE IF EXISTS "+DATABASE_TABLE+";";

    // Contexto de la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;

    public MyDBInformesAdapter(Context c){
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

    public void insertarInforme(String t, String f, String d, String l, String hI, String hF){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(TRABAJADOR,t);
        newValues.put(FECHA_OBRA,f);
        newValues.put(DURACION,d);
        newValues.put(LOCALIZACION,l);
        newValues.put(HORA_INICIO,hI);
        newValues.put(HORA_FIN,hF);
        db.insert(DATABASE_TABLE,null,newValues);
    }

    public ArrayList<String> recuperarInformes(){
        ArrayList<String> informes = new ArrayList<String>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE,null,null,null,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                informes.add(cursor.getString(0)+" "+cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+
                        " "+cursor.getString(4)+" "+cursor.getString(5)+" "+cursor.getString(6));
            }while (cursor.moveToNext());
        }
        return informes;
    }

    public ArrayList<String> recuperarInforme(int id){
        ArrayList<String> informe = new ArrayList<String>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE,null,null,null,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToPosition(id)){
            do{
                informe.add(cursor.getString(0)+" "+cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+
                        " "+cursor.getString(4)+" "+cursor.getString(5)+" "+cursor.getString(6));
            }while (cursor.moveToNext());
        }
        return informe;
    }

    public String recuperarTrabajador(int id){
        String text = new String();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE,null,null,null,null,null,null);
        //Recorremos el cursor
        cursor.moveToPosition(id);

        text = cursor.getString(1);

        return text;
    }
    public String recuperarFecha(int id){
        String text = new String();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE,null,null,null,null,null,null);
        //Recorremos el cursor
        cursor.moveToPosition(id);

        text = cursor.getString(2);

        return text;
    }
    public String recuperarDuracion(int id){
        String text = new String();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE,null,null,null,null,null,null);
        //Recorremos el cursor
        cursor.moveToPosition(id);

        text = cursor.getString(3);

        return text;
    }
    public String recuperarNumObra(int id){
        String text = new String();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE,null,null,null,null,null,null);
        //Recorremos el cursor
        cursor.moveToPosition(id);

        text = cursor.getString(0);

        return text;
    }
    public String recuperarLocalizacion(int id){
        String text = new String();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE,null,null,null,null,null,null);
        //Recorremos el cursor
        cursor.moveToPosition(id);

        text = cursor.getString(4);

        return text;
    }
    public String recuperarHoraInicio(int id){
        String text = new String();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE,null,null,null,null,null,null);
        //Recorremos el cursor
        cursor.moveToPosition(id);

        text = cursor.getString(5);

        return text;
    }
    public String recuperarHoraFin(int id){
        String text = new String();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE,null,null,null,null,null,null);
        //Recorremos el cursor
        cursor.moveToPosition(id);

        text = cursor.getString(6);

        return text;
    }

    public ArrayList<String>  recuperarListaListViewInformes(){
        ArrayList<String> informes = new ArrayList<String>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE,null,null,null,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                informes.add(cursor.getString(0)+". "+cursor.getString(1)+" el "+cursor.getString(2)+
                        " en "+cursor.getString(4));
            }while (cursor.moveToNext());
        }
        return informes;
    }

    /*
    public void modificarInforme(int id, String y){
        //Creamos un nuevo ContentValues
        ContentValues newValues = new ContentValues();
        //Asignamos el valor del campo a modificar
        newValues.put(ARG_TRABAJADOR,y);
        db.update(DATABASE_TABLE,newValues,"_id=" + id,null);
    }
    */


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
