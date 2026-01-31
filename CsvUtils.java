package com.clinica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {

    public static final String DB_DIR = "db";

    public static final String ADMINS = "db/admins.csv";
    public static final String DOCTORES = "db/doctores.csv";
    public static final String PACIENTES = "db/pacientes.csv";
    public static final String CITAS = "db/citas.csv";

    public static void ensureDbFiles() {
        try {
            Files.createDirectories(Paths.get(DB_DIR));
            createIfMissing(ADMINS, "admin,admin123\n");
            createIfMissing(DOCTORES, "");
            createIfMissing(PACIENTES, "");
            createIfMissing(CITAS, "");
        } catch (IOException e) {
            System.out.println("Error creando db: " + e.getMessage());
        }
    }

    private static void createIfMissing(String path, String defaultContent) throws IOException {
        Path p = Paths.get(path);
        if (!Files.exists(p)) {
            Files.write(p, defaultContent.getBytes());
        }
    }

    public static List<String> readAllLines(String path) {
        try {
            Path p = Paths.get(path);
            if (!Files.exists(p)) return new ArrayList<>();
            return Files.readAllLines(p);
        } catch (IOException e) {
            System.out.println("Error leyendo archivo " + path + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void appendLine(String path, String line) {
        try (FileWriter fw = new FileWriter(path, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error escribiendo archivo " + path + ": " + e.getMessage());
        }
    }
}