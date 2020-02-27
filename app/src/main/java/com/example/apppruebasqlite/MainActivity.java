package com.example.apppruebasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void registrar(View view) {
        Intent i = new Intent(this, RegistrarActivity.class);
        startActivity(i);
    }

    public void modificar(View view) {
        Intent i = new Intent(this, ModificarActivity.class);
        startActivity(i);
    }

    public void borrar(View view) {
        Intent i = new Intent(this, BorrarActivity.class);
        startActivity(i);
    }

    public void consultar(View view) {
        Intent i = new Intent(this, ConsultarActivity.class);
        startActivity(i);
    }
}
