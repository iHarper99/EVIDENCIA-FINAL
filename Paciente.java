package com.clinica;

public class Paciente {

    private String id;
    private String nombreCompleto;

    public Paciente(String id, String nombreCompleto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }

    public String getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String toCsv() {
        return id + "," + nombreCompleto;
    }

    public static Paciente fromCsv(String line) {
        String[] parts = line.split(",", -1);
        return new Paciente(parts[0], parts[1]);
    }
}
