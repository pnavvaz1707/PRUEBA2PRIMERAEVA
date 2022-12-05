package com.example.prueba2primeraeva;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdaptadorFrigorificos extends ArrayAdapter {
    Activity context;
    ArrayList<Frigorifico> frigorificos;

    public AdaptadorFrigorificos(Activity context, ArrayList<Frigorifico> frigorificos) {
        super(context, R.layout.list_view_frigorifico, frigorificos);
        this.context = context;
        this.frigorificos = frigorificos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.list_view_frigorifico, null);

        TextView tvFrigoNombre = (TextView) item.findViewById(R.id.frigo_nombre);
        tvFrigoNombre.setText(frigorificos.get(position).getNombre());

        ListView lstAlimentos = (ListView) item.findViewById(R.id.lstAlimentos);
        AdaptadorAlimentos adaptadorAlimentos = new AdaptadorAlimentos(context, frigorificos.get(position).getAlimentos());
        lstAlimentos.setAdapter(adaptadorAlimentos);

        return item;
    }
}
