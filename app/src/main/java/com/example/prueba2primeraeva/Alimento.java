package com.example.prueba2primeraeva;

import android.os.Parcel;
import android.os.Parcelable;

public class Alimento implements Parcelable {
    private String nombre;
    private int cantidad;

    public Alimento(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    protected Alimento(Parcel in) {
        nombre = in.readString();
        cantidad = in.readInt();
    }

    public static final Creator<Alimento> CREATOR = new Creator<Alimento>() {
        @Override
        public Alimento createFromParcel(Parcel in) {
            return new Alimento(in);
        }

        @Override
        public Alimento[] newArray(int size) {
            return new Alimento[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void sumarCantidad(int cantidad) {
        this.cantidad += cantidad;
    }

    public void restarCantidad(int cantidad) {
        this.cantidad -= cantidad;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeInt(cantidad);
    }
}
