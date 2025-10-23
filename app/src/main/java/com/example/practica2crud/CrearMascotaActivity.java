package com.example.practica2crud;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practica2crud.controllers.MascotasController;
import com.example.practica2crud.modelos.mascota;

public class CrearMascotaActivity extends AppCompatActivity {

    private EditText etNombreNuevaMascota, etEdadNuevaMascota;
    private Button btnGuardarNuevaMascota, btnCancelarNuevaMascota;
    private MascotasController mascotasController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_mascota);

        etNombreNuevaMascota = findViewById(R.id.etNombreNuevaMascota);
        etEdadNuevaMascota = findViewById(R.id.etEdadNuevaMascota);
        btnGuardarNuevaMascota = findViewById(R.id.btnGuardarNuevaMascota);
        btnCancelarNuevaMascota = findViewById(R.id.btnCancelarNuevaMascota);
        mascotasController = new MascotasController(this);

        btnGuardarNuevaMascota.setOnClickListener(v -> {
            String nombre = etNombreNuevaMascota.getText().toString().trim();
            String sEdad = etEdadNuevaMascota.getText().toString().trim();
            if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(sEdad)) {
                if (TextUtils.isEmpty(nombre)) etNombreNuevaMascota.setError("Requerido");
                if (TextUtils.isEmpty(sEdad)) etEdadNuevaMascota.setError("Requerido");
                return;
            }
            int edad = Integer.parseInt(sEdad);
            mascotasController.nuevaMascota(new mascota(nombre, edad));
            finish();
        });

        btnCancelarNuevaMascota.setOnClickListener(v -> finish());
    }
}
