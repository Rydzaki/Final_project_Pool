package com.pool.dto.cartProduct;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IdManager {
    private static final String FILE_NAME = "src/test/resources/currentId.txt";
    private static final int INITIAL_ID = readInitialId();

    private static int readInitialId() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                return Integer.parseInt(line);
            } else {
                writeCurrentId(INITIAL_ID);
                return INITIAL_ID;
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return INITIAL_ID; // Начальное значение по умолчанию
        }
    }

    public static int readCurrentId() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                return Integer.parseInt(line);
            } else {
                return INITIAL_ID;
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return INITIAL_ID; // Начальное значение по умолчанию
        }
    }

    public static void writeCurrentId(int id) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(Integer.toString(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
