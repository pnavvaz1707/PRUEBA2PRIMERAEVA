package com.example.prueba2primeraeva;

public class Alimento {
    private String nombre;
    private int cantidad;

    public Alimento(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

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
}
