package com.example.practica2crud;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practica2crud.controllers.MascotasController;
import com.example.practica2crud.modelos.mascota;

public class EditarMascotaActivity extends AppCompatActivity {

    private EditText etNombreEditarMascota, etEdadEditarMascota;
    private Button btnGuardarCambiosDeMascota, btnCancelarEdicion, btnEliminarMascota;
    private MascotasController mascotasController;
    private long idMascota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_mascota);

        etNombreEditarMascota = findViewById(R.id.etNombreEditarMascota);
        etEdadEditarMascota = findViewById(R.id.etEdadEditarMascota);
        btnGuardarCambiosDeMascota = findViewById(R.id.btnGuardarCambiosDeMascota);
        btnCancelarEdicion = findViewById(R.id.btnCancelarEdicion);
        btnEliminarMascota = findViewById(R.id.btnEliminarMascota);

        mascotasController = new MascotasController(this);

        if (getIntent() != null) {
            idMascota = getIntent().getLongExtra("id", 0);
            String nombre = getIntent().getStringExtra("nombre");
            int edad = getIntent().getIntExtra("edad", 0);
            etNombreEditarMascota.setText(nombre);
            etEdadEditarMascota.setText(String.valueOf(edad));
        }

        // Guardar cambios
        btnGuardarCambiosDeMascota.setOnClickListener(v -> {
            String nombre = etNombreEditarMascota.getText().toString().trim();
            String sEdad = etEdadEditarMascota.getText().toString().trim();

            if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(sEdad)) {
                if (TextUtils.isEmpty(nombre)) etNombreEditarMascota.setError("Requerido");
                if (TextUtils.isEmpty(sEdad)) etEdadEditarMascota.setError("Requerido");
                return;
            }

            int edad = Integer.parseInt(sEdad);
            mascota m = new mascota(nombre, edad, idMascota);
            mascotasController.guardarCambios(m);
            finish();
        });

        // Eliminar mascota
        btnEliminarMascota.setOnClickListener(v -> {
            new androidx.appcompat.app.AlertDialog.Builder(EditarMascotaActivity.this)
                    .setTitle("Eliminar")
                    .setMessage("¿Eliminar a \"" + etNombreEditarMascota.getText().toString().trim() + "\"?")
                    .setPositiveButton("Sí", (d, w) -> {
                        mascota m = new mascota(
                                etNombreEditarMascota.getText().toString().trim(),
                                Integer.parseInt(etEdadEditarMascota.getText().toString().trim()),
                                idMascota
                        );
                        mascotasController.eliminarMascota(m);
                        finish();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

        // Cancelar edición
        btnCancelarEdicion.setOnClickListener(v -> finish());
    }
}
