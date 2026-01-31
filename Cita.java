package com.clinica;

public class Cita {

    private String id;
    private String fechaHora;   // Ej: "2026-01-31 10:30"
    private String motivo;
    private String idDoctor;
    private String idPaciente;

    public Cita(String id, String fechaHora, String motivo, String idDoctor, String idPaciente) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.idDoctor = idDoctor;
        this.idPaciente = idPaciente;
    }

    public String getId() {
        return id;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getIdDoctor() {
        return idDoctor;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public String toCsv() {
        return id + "," + fechaHora + "," + motivo + "," + idDoctor + "," + idPaciente;
    }

    public static Cita fromCsv(String line) {
        String[] parts = line.split(",", -1);
        return new Cita(parts[0], parts[1], parts[2], parts[3], parts[4]);
    }
}