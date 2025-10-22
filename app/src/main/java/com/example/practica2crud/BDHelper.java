package com.example.practica2crud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDHelper extends SQLiteOpenHelper {
    private static final String NOMBRE_BASE_DE_DATOS = "mascotas";
    public static final String NOMBRE_TABLA_MASCOTAS = "mascotas";
    private static final int VERSION_BASE_DE_DATOS = 1;

    public BDHelper(Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + NOMBRE_TABLA_MASCOTAS + " (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nombre TEXT," +
                        "edad INTEGER" +
                        ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
