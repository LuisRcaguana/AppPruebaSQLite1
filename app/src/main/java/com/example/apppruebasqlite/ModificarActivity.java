package com.example.apppruebasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apppruebasqlite.db.ContactosPersistencia;
import com.example.apppruebasqlite.model.Contacto;

public class ModificarActivity extends AppCompatActivity {

    EditText etId;
    EditText etNom;
    EditText etEmail;

    ContactosPersistencia cp;

    Button btnBuscar;
    Button btnGuardar;
    Button btnLimpiar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        etId = findViewById(R.id.etIdM);
        etNom = findViewById(R.id.etNombreM);
        etEmail = findViewById(R.id.etEmailM);
        cp = new ContactosPersistencia(this);

        btnBuscar = findViewById(R.id.btnBuscarM);
        btnGuardar = findViewById(R.id.btnGuardarM);
        btnLimpiar = findViewById(R.id.btnLimpiarM);


    }

    public void guardar(View view) {
        int id = Integer.parseInt(etId.getText().toString());
        String nombre = etNom.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        if (nombre.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, getString(R.string.no_datos),
                    Toast.LENGTH_LONG).show();
        } else {
            Contacto contacto = new Contacto(nombre, email);
            contacto.setId(id);
            cp.actualizarContacto(contacto);

            Toast.makeText(this, R.string.update_ok,
                        Toast.LENGTH_LONG).show();
        }
    }

    public void limpiar(View view) {
        etId.setText("");
        etNom.setText("");
        etEmail.setText("");

        habilitar(true);
    }

    private void habilitar(boolean b) {
        etId.setEnabled(b);
        btnBuscar.setEnabled(b);

        etNom.setEnabled(!b);
        etEmail.setEnabled(!b);
        btnGuardar.setEnabled(!b);
        btnLimpiar.setEnabled(!b);
    }

    public void buscar(View view) {
        String sId = etId.getText().toString().trim();

        if (sId.isEmpty()) {
            Toast.makeText(this, R.string.no_id,
                    Toast.LENGTH_LONG).show();
        } else {
            int id = Integer.parseInt(sId);
            Contacto contacto = cp.leerContacto(id);

            if (contacto != null) {
                etNom.setText(contacto.getNombre());
                etEmail.setText(contacto.getEmail());

                habilitar(false);

            } else {
                Toast.makeText(this, R.string.no_contacto,
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
