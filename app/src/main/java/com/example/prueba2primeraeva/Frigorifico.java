package com.example.prueba2primeraeva;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Frigorifico implements Parcelable {
    private String nombre;
    private ArrayList<Alimento> alimentos;
    private int CANTIDAD_MAXIMA = 10;

    public Frigorifico(String nombre) {
        this.nombre = nombre;
        this.alimentos = new ArrayList<>();
    }

    protected Frigorifico(Parcel in) {
        nombre = in.readString();
        alimentos = in.createTypedArrayList(Alimento.CREATOR);
        CANTIDAD_MAXIMA = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeTypedList(alimentos);
        dest.writeInt(CANTIDAD_MAXIMA);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Frigorifico> CREATOR = new Creator<Frigorifico>() {
        @Override
        public Frigorifico createFromParcel(Parcel in) {
            return new Frigorifico(in);
        }

        @Override
        public Frigorifico[] newArray(int size) {
            return new Frigorifico[size];
        }
    };

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
}
