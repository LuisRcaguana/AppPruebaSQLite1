package com.example.apppruebasqlite.RwUtils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apppruebasqlite.R;
import com.example.apppruebasqlite.model.Contacto;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class ContatoAdater extends RecyclerView.Adapter<ContatoAdater.ContactoVH> {


    private ArrayList<Contacto> listas;

    public ContatoAdater(ArrayList<Contacto> listas) {

        this.listas = listas;

    }


    public static class ContactoVH extends RecyclerView.ViewHolder{
        private TextView tvNombre;
        private TextView tvEmail;


        public ContactoVH(@NonNull View v) {

            super(v);

            tvNombre = v.findViewById(R.id.tvNombreC);
            tvEmail = v.findViewById(R.id.tvEmailC);

        }

        public  void bindContato(Contacto c){
            tvNombre.setText(c.getNombre());
            tvEmail.setText(c.getEmail());
        }
    }
    @NonNull
    @Override
    public ContactoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cotactoitem, parent, false);



        return new ContactoVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoVH holder, int position) {
        holder.bindContato(listas.get(position));
    }


    @Override
    public int getItemCount() {
        return listas.size();
    }



}
