package com.example.prueba2primeraeva;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdaptadorAlimentos extends ArrayAdapter {
    Activity context;
    ArrayList<Alimento> alimentos;

    public AdaptadorAlimentos(Activity context, ArrayList<Alimento> alimentos) {
        super(context, R.layout.list_view_alimentos, alimentos);
        this.context = context;
        this.alimentos = alimentos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.list_view_alimentos, null);

        TextView tvAlimentoNombre = (TextView) item.findViewById(R.id.tv_alimento_nombre);
        tvAlimentoNombre.setText(alimentos.get(position).getNombre());

        TextView tvAlimentoCantidad = (TextView) item.findViewById(R.id.tv_alimento_cantidad);
        tvAlimentoCantidad.setText(String.valueOf(alimentos.get(position).getCantidad()));

        return item;
    }
}
