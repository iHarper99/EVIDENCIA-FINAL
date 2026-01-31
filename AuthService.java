package com.clinica;

import java.util.List;

public class AuthService {

    public boolean login(String user, String pass) {
        List<String> lines = CsvUtils.readAllLines(CsvUtils.ADMINS);

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            String[] p = line.split(",", -1);
            if (p.length >= 2) {
                String u = p[0].trim();
                String pw = p[1].trim();

                if (u.equals(user) && pw.equals(pass)) {
                    return true;
                }
            }
        }
        return false;
    }
}