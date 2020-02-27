package com.example.apppruebasqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.apppruebasqlite.RwUtils.ContatoAdater;
import com.example.apppruebasqlite.db.ContactosPersistencia;
import com.example.apppruebasqlite.model.Contacto;

import java.util.ArrayList;

public class ConsultarActivity extends AppCompatActivity {

    RecyclerView rv;
    ContatoAdater ac;
    ContactosPersistencia cp;
    LinearLayoutManager ll;
    EditText etServidro;
    ArrayList<Contacto> listaContacto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        rv = findViewById(R.id.rwC);

        etServidro = findViewById(R.id.etservidor);

        cp = new ContactosPersistencia(this);

        listaContacto = cp.leerContactos();

        rv();


    }

    private void rv() {
        ac = new ContatoAdater(listaContacto);
        ll = new LinearLayoutManager(this);

        rv.setLayoutManager(ll);
        rv.setHasFixedSize(true);
        rv.setAdapter(ac);
    }

    public void consultar(View view) {

        String servidor = etServidro.getText().toString().trim();

        if (servidor.isEmpty()){
            listaContacto = cp.leerContactos();
        }else{
            listaContacto = cp.leerContactosEmail(servidor);
        }

        rv();


    }
}
