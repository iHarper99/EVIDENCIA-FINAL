package com.clinica;

import java.util.List;

public class ClinicaService {

    public boolean existeDoctor(String idDoctor) {
        List<String> lines = CsvUtils.readAllLines(CsvUtils.DOCTORES);
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            Doctor d = Doctor.fromCsv(line);
            if (d.getId().equals(idDoctor)) return true;
        }
        return false;
    }

    public boolean existePaciente(String idPaciente) {
        List<String> lines = CsvUtils.readAllLines(CsvUtils.PACIENTES);
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            Paciente p = Paciente.fromCsv(line);
            if (p.getId().equals(idPaciente)) return true;
        }
        return false;
    }

    public void altaDoctor(Doctor d) {
        CsvUtils.appendLine(CsvUtils.DOCTORES, d.toCsv());
    }

    public void altaPaciente(Paciente p) {
        CsvUtils.appendLine(CsvUtils.PACIENTES, p.toCsv());
    }

    public void crearCita(Cita c) {
        CsvUtils.appendLine(CsvUtils.CITAS, c.toCsv());
    }

    public void listarCitas() {
        List<String> lines = CsvUtils.readAllLines(CsvUtils.CITAS);

        if (lines.isEmpty()) {
            System.out.println("No hay citas registradas.");
            return;
        }

        System.out.println("=== CITAS REGISTRADAS ===");
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            Cita c = Cita.fromCsv(line);
            System.out.println("ID: " + c.getId()
                    + " | FechaHora: " + c.getFechaHora()
                    + " | Motivo: " + c.getMotivo()
                    + " | Doctor: " + c.getIdDoctor()
                    + " | Paciente: " + c.getIdPaciente());
        }
    }
}
