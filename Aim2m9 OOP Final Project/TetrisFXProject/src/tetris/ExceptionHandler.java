/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 */
public class ExceptionHandler {

    public static void handle(Exception exc) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Exception");
            alert.setHeaderText("Exception occurred");
            alert.setContentText(exc.getMessage());
            alert.showAndWait();
        });

    } // method

} // class
