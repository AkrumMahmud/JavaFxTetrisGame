/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;
import tetris.model.Tetris;

/**
 *
 */
public class DB {

    // File where datetime of game and score are stored.
    // This file is placed at the root of the project.
    private final static File DATABASE = new File("store.db");

    public static void store(Tetris game) throws IOException {
        String data = "Date: " + LocalDateTime.now() + ", Score: "
                + game.getScore() + ", Level: " + game.getLevel() + "\r\n";
        try (FileWriter fw = new FileWriter(DATABASE, true)) {
            fw.write(data);
        }
    }

    public static String retrieve() throws IOException {
        if (Files.notExists(DATABASE.toPath())) {
            Files.createFile(DATABASE.toPath());
        }
        List<String> lines = Files.readAllLines(DATABASE.toPath());
        StringBuilder sb = new StringBuilder();
        lines.forEach(line -> {
            sb.append(line).append("\n");
        });

        return sb.toString();
    }

} // class
