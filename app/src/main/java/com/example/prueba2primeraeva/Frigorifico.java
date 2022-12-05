package com.example.prueba2primeraeva;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Frigorifico implements Parcelable {
    private String nombre;
    private ArrayList<Alimento> alimentos;
    private final int CANTIDAD_MAXIMA = 10;

    public Frigorifico(String nombre) {
        this.nombre = nombre;
        this.alimentos = new ArrayList<>();
    }

    public boolean anadirAlimento(Alimento alimento) {
        boolean anadido = false;
        Alimento alimentoRepetido = buscarAlimento(alimento.getNombre());
        if (alimentoRepetido != null) {
            alimentoRepetido.sumarCantidad(alimento.getCantidad());
            anadido = true;
        } else if (alimentos.size() != CANTIDAD_MAXIMA) {
            this.alimentos.add(alimento);
            anadido = true;
        }
        return anadido;
    }

    public boolean restarAlimento(String nombre, int cantidad) {
        boolean restado = false;
        Alimento alimentoSel = buscarAlimento(nombre);
        if (alimentoSel != null) {

            if (alimentoSel.getCantidad() >= cantidad) {
                alimentoSel.restarCantidad(cantidad);
                restado = true;
            }
        }
        return restado;
    }

    private Alimento buscarAlimento(String nombre) {
        Alimento alimentoSel = null;
        for (Alimento alimento : alimentos) {
            if (alimento.getNombre().equals(nombre)) {
                alimentoSel = alimento;
            }
        }
        return alimentoSel;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Alimento> getAlimentos() {
        return alimentos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
