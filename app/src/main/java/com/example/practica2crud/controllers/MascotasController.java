package com.example.practica2crud.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.example.practica2crud.BDHelper;
import com.example.practica2crud.modelos.mascota;

public class MascotasController {
    private final BDHelper bdHelper;
    private static final String TABLA = BDHelper.NOMBRE_TABLA_MASCOTAS;

    public MascotasController(Context contexto) {
        bdHelper = new BDHelper(contexto);
    }

    public long nuevaMascota(mascota mascota) {
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nombre", mascota.getNombre());
        cv.put("edad", mascota.getEdad());
        return db.insert(TABLA, null, cv);
    }

    public ArrayList<mascota> obtenerMascotas() {
        ArrayList<mascota> lista = new ArrayList<>();
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        String[] cols = {"nombre", "edad", "id"};
        Cursor c = db.query(TABLA, cols, null, null, null, null, "id DESC");
        if (c == null) return lista;
        if (!c.moveToFirst()) {
            c.close();
            return lista;
        }
        do {
            String nombre = c.getString(0);
            int edad = c.getInt(1);
            long id = c.getLong(2);
            lista.add(new mascota(nombre, edad, id));
        } while (c.moveToNext());
        c.close();
        return lista;
    }

    public int guardarCambios(mascota mascota) {
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nombre", mascota.getNombre());
        cv.put("edad", mascota.getEdad());
        String[] args = { String.valueOf(mascota.getId()) };
        return db.update(TABLA, cv, "id = ?", args);
    }

    public int eliminarMascota(mascota mascota) {
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        String[] args = { String.valueOf(mascota.getId()) };
        return db.delete(TABLA, "id = ?", args);
    }
}
