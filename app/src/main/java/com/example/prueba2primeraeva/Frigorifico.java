package com.example.prueba2primeraeva;

import java.util.ArrayList;

public class Frigorifico {
    private String nombre;
    private ArrayList<Alimento> alimentos;
    private final int CANTIDAD_MAXIMA = 10;

    public Frigorifico(String nombre) {
        this.nombre = nombre;
        this.alimentos = new ArrayList<>();
    }

    public boolean anadirAlimento(Alimento alimento) {
        boolean anadido = false;
        if (alimentos.size() != CANTIDAD_MAXIMA) {
            this.alimentos.add(alimento);
            anadido = true;
        }
        return anadido;
    }

    public boolean restarAlimento(String nombre, int cantidad) {
        boolean restado = false;
        for (Alimento alimento : alimentos) {
            if (alimento.getNombre().equals(nombre)) {
                if (alimento.getCantidad() >= cantidad) {
                    alimento.setCantidad(alimento.getCantidad() - cantidad);
                    restado = true;
                }
            }
        }
        return restado;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Alimento> getAlimentos() {
        return alimentos;
    }
}
