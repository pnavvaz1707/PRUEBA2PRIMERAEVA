package com.example.prueba2primeraeva;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class VentanaFrigorifico extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Frigorifico> frigorificos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frigorifico_ventana);

        Bundle bundle = getIntent().getExtras();

        frigorificos = bundle.getParcelableArrayList("frigorificos");

        Frigorifico frigorificoSel = bundle.getParcelable("frigoSel");

        TextView tvFrigoNombre = (TextView) findViewById(R.id.frigo_nombre_ventana);
        tvFrigoNombre.setText(frigorificoSel.getNombre());

        LinearLayout frigoAlimentosLayout = (LinearLayout) findViewById(R.id.frigo_alimentos);
        if (frigorificoSel.getAlimentos() != null) {
            for (Alimento alimento : frigorificoSel.getAlimentos()) {
                LinearLayout nombreLayout = new LinearLayout(this);

                TextView tvAlimentoNombreTitulo = new TextView(this);
                tvAlimentoNombreTitulo.setText(R.string.nombre_alimento_txt);

                TextView tvAlimentoNombre = new TextView(this);
                tvAlimentoNombre.setText(alimento.getNombre());

                nombreLayout.addView(tvAlimentoNombreTitulo);
                nombreLayout.addView(tvAlimentoNombre);

                LinearLayout cantidadLayout = new LinearLayout(this);

                TextView tvAlimentoCantidadTitulo = new TextView(this);
                tvAlimentoCantidadTitulo.setText(R.string.cantidad_txt);

                TextView tvAlimentoCantidad = new TextView(this);
                tvAlimentoCantidad.setText(String.valueOf(alimento.getCantidad()));

                cantidadLayout.addView(tvAlimentoCantidadTitulo);
                cantidadLayout.addView(tvAlimentoCantidad);

                frigoAlimentosLayout.addView(nombreLayout);
                frigoAlimentosLayout.addView(cantidadLayout);
            }
        }

        Button btnVolver = (Button) findViewById(R.id.btn_volver);
        btnVolver.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();

        bundle.putParcelableArrayList("frigorificos", frigorificos);
        i.putExtras(bundle);
        startActivity(i);
    }
}
