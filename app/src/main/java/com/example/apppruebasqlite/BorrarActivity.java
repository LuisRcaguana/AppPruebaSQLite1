package com.example.apppruebasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apppruebasqlite.db.ContactosPersistencia;
import com.example.apppruebasqlite.model.Contacto;

public class BorrarActivity extends AppCompatActivity {
    EditText etId;
    EditText etNombre;
    EditText etEmail;

    Button btnBuscar;
    Button btnGuardar;
    Button btnBorrado;


    ContactosPersistencia cds;
    Contacto contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar);

        etId = findViewById(R.id.etIdB);
        etNombre = findViewById(R.id.etNombreB);
        etEmail = findViewById(R.id.etEmailB);

        btnBuscar = findViewById(R.id.btnBuscarB);
        btnGuardar = findViewById(R.id.btnGuardarB);
        btnBorrado = findViewById(R.id.btnBorrar);

        cds = new ContactosPersistencia(this);
    }

    public void buscarB(View view) {

        String id = etId.getText().toString().trim();

        if (id.isEmpty()) {
            Toast.makeText(this, "Debe introducir un id",
                    Toast.LENGTH_LONG).show();
        } else {
            contacto = cds.leerContacto(Integer.parseInt(id));

            if (contacto != null) {
                etId.setEnabled(false);
                btnBuscar.setEnabled(false);

                btnGuardar.setEnabled(true);

                etNombre.setText(contacto.getNombre());
                etEmail.setText(contacto.getEmail());

            } else {
                Toast.makeText(this,
                        "No se ha encontrado ningún contacto para el id introducido",
                        Toast.LENGTH_LONG).show();
            }
        }

    }

    public void borrar(View view) {
        cds.borrarContacto(contacto.getId());

        Toast.makeText(this,
                "Se ha eliminado el contacto correctamente",
                Toast.LENGTH_LONG).show();

        etId.setText("");
        etNombre.setText("");
        etEmail.setText("");

        etId.setEnabled(true);
        btnBuscar.setEnabled(true);

        btnGuardar.setEnabled(false);




    }

    public void cancelarB(View view) {
        etId.setText("");
        etNombre.setText("");
        etEmail.setText("");

        btnBuscar.setEnabled(true);
        etId.setEnabled(true);






    }
}
