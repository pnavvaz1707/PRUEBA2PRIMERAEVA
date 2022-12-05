package com.example.prueba2primeraeva;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Frigorifico> frigorificos;
    private RadioGroup rGroupFrigorificos;
    private Button btnAnadir;
    private Button btnQuitar;
    private Button btnVer;
    private EditText nombreAlimento;
    private EditText cantidadAlimento;
    private ListView lstFrigorificos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_window);

        rGroupFrigorificos = findViewById(R.id.rGroupFrigorificos);
        lstFrigorificos = findViewById(R.id.lstFrigorificos);

        btnAnadir = findViewById(R.id.btnAnadir);
        btnQuitar = findViewById(R.id.btnQuitar);
        btnVer = findViewById(R.id.btnVer);

        btnAnadir.setOnClickListener(this);
        btnQuitar.setOnClickListener(this);
        btnVer.setOnClickListener(this);

        nombreAlimento = findViewById(R.id.et_nombre_alimento);
        cantidadAlimento = findViewById(R.id.et_cantidad_alimento);

        String[] nombreFrigorificos = getResources().getStringArray(R.array.frigorificos);

        for (String nombreFrigorifico : nombreFrigorificos) {
            RadioButton frigorifico = new RadioButton(this);
            frigorifico.setText(nombreFrigorifico);
            rGroupFrigorificos.addView(frigorifico);
        }

        Bundle bundle;
        if ((bundle = getIntent().getExtras()) != null) {
            frigorificos = bundle.getParcelableArrayList("frigorificos");
            actualizarFrigorificos();
        } else {
            frigorificos = new ArrayList<>();

            for (String nombreFrigorifico : nombreFrigorificos) {
                frigorificos.add(new Frigorifico(nombreFrigorifico));
            }
        }
    }

    @Override
    public void onClick(View view) {
        RadioButton radioBtnPulsado = (RadioButton) findViewById(rGroupFrigorificos.getCheckedRadioButtonId());
        if (radioBtnPulsado != null) {
            String nombreFrigorificoSel = String.valueOf(radioBtnPulsado.getText());
            Frigorifico frigorificoSel = null;
            for (Frigorifico frigorifico : frigorificos) {
                if (frigorifico.getNombre().equals(nombreFrigorificoSel)) {
                    frigorificoSel = frigorifico;
                }
            }
            if (view.getId() == R.id.btnVer) {
                Intent i = new Intent(this, VentanaFrigorifico.class);
                Bundle bundle = new Bundle();

                bundle.putParcelable("frigoSel", frigorificoSel);
                bundle.putParcelableArrayList("frigorificos", frigorificos);
                i.putExtras(bundle);
                startActivity(i);

            } else {
                if (!nombreAlimento.getText().toString().equals("") && !cantidadAlimento.getText().toString().equals("")) {
                    String nombre = nombreAlimento.getText().toString();
                    int cantidad = Integer.parseInt(cantidadAlimento.getText().toString());

                    if (view.getId() == R.id.btnAnadir) {
                        Alimento alimento = new Alimento(nombre, cantidad);
                        if (!(frigorificoSel.anadirAlimento(alimento))) {
                            Toast.makeText(this, "El frigorifico " + frigorificoSel.getNombre() + " está lleno", Toast.LENGTH_SHORT).show();
                        }
                    } else if (view.getId() == R.id.btnQuitar) {
                        if (!(frigorificoSel.restarAlimento(nombre, cantidad))) {
                            Toast.makeText(this, "No se ha podido restar " + cantidad + " al alimento " + nombre + " del frigorífico " + frigorificoSel.getNombre(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    actualizarFrigorificos();
                } else {
                    Toast.makeText(this, "Debes rellenar los campos antes de añadir o quitar alimentos", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void actualizarFrigorificos() {
        AdaptadorFrigorificos adaptadorFrigorificos = new AdaptadorFrigorificos(this, frigorificos);
        adaptadorFrigorificos.notifyDataSetChanged();
        lstFrigorificos.setAdapter(adaptadorFrigorificos);
    }
}
