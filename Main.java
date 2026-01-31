package com.clinica;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        CsvUtils.ensureDbFiles();

        Scanner sc = new Scanner(System.in);
        AuthService auth = new AuthService();
        ClinicaService clinica = new ClinicaService(); // OJO: sin "a"

        System.out.println("=== SISTEMA DE CITAS - CONSULTORIO ===");

        System.out.print("Usuario: ");
        String user = sc.nextLine();

        System.out.print("Contraseña: ");
        String pass = sc.nextLine();

        if (!auth.login(user, pass)) {
            System.out.println("Acceso denegado ❌");
            return;
        }

        System.out.println("Acceso concedido ✅");

        int opcion = 0;

        while (opcion != 5) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1) Dar de alta doctor");
            System.out.println("2) Dar de alta paciente");
            System.out.println("3) Crear cita");
            System.out.println("4) Mostrar citas");
            System.out.println("5) Salir");
            System.out.print("Elige una opción: ");

            String entrada = sc.nextLine();

            try {
                opcion = Integer.parseInt(entrada);

                switch (opcion) {
                    case 1:
                        System.out.print("ID Doctor: ");
                        String idDoc = sc.nextLine();

                        if (clinica.existeDoctor(idDoc)) {
                            System.out.println("Error: ese ID de doctor ya existe.");
                            break;
                        }

                        System.out.print("Nombre completo: ");
                        String nomDoc = sc.nextLine();

                        System.out.print("Especialidad: ");
                        String esp = sc.nextLine();

                        clinica.altaDoctor(new Doctor(idDoc, nomDoc, esp));
                        System.out.println("Doctor registrado correctamente ✅");
                        break;

                    case 2:
                        System.out.print("ID Paciente: ");
                        String idPac = sc.nextLine();

                        if (clinica.existePaciente(idPac)) {
                            System.out.println("Error: ese ID de paciente ya existe.");
                            break;
                        }

                        System.out.print("Nombre completo: ");
                        String nomPac = sc.nextLine();

                        clinica.altaPaciente(new Paciente(idPac, nomPac));
                        System.out.println("Paciente registrado correctamente ✅");
                        break;

                    case 3:
                        System.out.print("ID Cita: ");
                        String idCita = sc.nextLine();

                        System.out.print("Fecha y hora (ej. 2026-01-31 10:30): ");
                        String fechaHora = sc.nextLine();

                        System.out.print("Motivo: ");
                        String motivo = sc.nextLine();

                        System.out.print("ID Doctor: ");
                        String doc = sc.nextLine();

                        System.out.print("ID Paciente: ");
                        String pac = sc.nextLine();

                        if (!clinica.existeDoctor(doc)) {
                            System.out.println("Error: el doctor no existe.");
                            break;
                        }
                        if (!clinica.existePaciente(pac)) {
                            System.out.println("Error: el paciente no existe.");
                            break;
                        }

                        clinica.crearCita(new Cita(idCita, fechaHora, motivo, doc, pac));
                        System.out.println("Cita creada correctamente ✅");
                        break;

                    case 4:
                        clinica.listarCitas();
                        break;

                    case 5:
                        System.out.println("Saliendo del sistema...");
                        break;

                    default:
                        System.out.println("Opción inválida.");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Escribe un número (1-5).");
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
                System.out.println("El programa continúa ejecutándose.");
            }
        }
    }
}