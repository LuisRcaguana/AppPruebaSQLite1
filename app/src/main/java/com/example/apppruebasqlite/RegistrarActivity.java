package com.example.apppruebasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apppruebasqlite.db.ContactosPersistencia;
import com.example.apppruebasqlite.model.Contacto;

public class RegistrarActivity extends AppCompatActivity {

    EditText etNombre;
    EditText etEmail;
    ContactosPersistencia cp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        etNombre = findViewById(R.id.etNombreR);
        etEmail = findViewById(R.id.etEmailR);

        cp = new ContactosPersistencia(this);
    }

    public void guardar(View view) {
        String nombre = etNombre.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        if (nombre.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, getString(R.string.no_datos),
                    Toast.LENGTH_LONG).show();
        } else {
            Contacto contacto = new Contacto(nombre, email);
            long id = cp.insertarContacto(contacto);

            if (id != -1) {
                Toast.makeText(this, getString(R.string.insert_ok),
                        Toast.LENGTH_LONG).show();
                contacto.setId(id);
            } else {
                Toast.makeText(this, getString(R.string.insert_ko),
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    public void limpiar(View view) {
        etNombre.setText("");
        etEmail.setText("");
    }
}
