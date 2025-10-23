package com.example.practica2crud;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import com.example.practica2crud.controllers.MascotasController;
import com.example.practica2crud.modelos.mascota;

public class MainActivity extends AppCompatActivity {

    private MascotasController mascotasController;
    private MascotasAdapter adapter;
    private RecyclerView rvMascotas;
    private FloatingActionButton fabAgregarMascota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mascotasController = new MascotasController(MainActivity.this);

        rvMascotas = findViewById(R.id.rvMascotas);
        rvMascotas.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MascotasAdapter(new ArrayList<>(), new MascotasAdapter.Listener() {
            @Override
            public void onClick(mascota m) {
                Intent i = new Intent(MainActivity.this, EditarMascotaActivity.class);
                i.putExtra("id", m.getId());
                i.putExtra("nombre", m.getNombre());
                i.putExtra("edad", m.getEdad());
                startActivity(i);
            }

            @Override
            public void onLongClick(mascota m) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Eliminar")
                        .setMessage("¿Eliminar a \"" + m.getNombre() + "\"?")
                        .setPositiveButton("Sí", (dialog, which) -> {
                            mascotasController.eliminarMascota(m);
                            cargarMascotas();
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
        rvMascotas.setAdapter(adapter);

        fabAgregarMascota = findViewById(R.id.fabAgregarMascota);
        fabAgregarMascota.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, CrearMascotaActivity.class))
        );

        fabAgregarMascota.setOnLongClickListener(v -> {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Acerca de")
                    .setMessage("CRUD SQLite de ejemplo")
                    .setNegativeButton("Cerrar", (d, w) -> d.dismiss())
                    .show();
            return true;
        });

        cargarMascotas();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarMascotas();
    }

    private void cargarMascotas() {
        adapter.setData(mascotasController.obtenerMascotas());
    }
}
