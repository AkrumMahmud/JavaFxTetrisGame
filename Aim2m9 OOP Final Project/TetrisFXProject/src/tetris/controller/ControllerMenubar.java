package tetris.controller;

import tetris.DB;
import tetris.ExceptionHandler;
import tetris.TetrisFXProject;
import tetris.model.Tetris;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

public class ControllerMenubar {

    private TetrisFXProject tetrisInstance;

    @FXML
    public void handleStat() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Statistics");
            TextArea ta = new TextArea();
            alert.getDialogPane().setExpandableContent(ta);

            try {
                ta.setText(DB.retrieve());
            } catch (IOException ex) {
                ExceptionHandler.handle(ex);
            }
            alert.show();
        });
    }

    @FXML
    public void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Tetris game, version 1.0");
        alert.setContentText("This is a well known 'Tetris' game"
                + "\n built via JavaFX library. For each completed line"
                + "\n 100 points are earned, one level worth 1000 points."
                + "\n You also can stop/continue the game and see the"
                + "\n statistics.");
        alert.show();
    }

    @FXML
    public void handleExit() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void handleNewGame() {

        Tetris tetris = (Tetris) tetrisInstance.getGame();
        try {
            DB.store(tetris);
        } catch (IOException exc) {
            ExceptionHandler.handle(exc);
        }
        tetris.getTimer().stop();
        tetrisInstance.updateGameSession();
    }

    @FXML
    public void handlePause() {
        Tetris tetris = (Tetris) tetrisInstance.getGame();
        tetris.getTimer().pause();
    }

    @FXML
    public void handleContinue() {
        Tetris tetris = (Tetris) tetrisInstance.getGame();
        tetris.getTimer().play();
    }

    public void setTetrisInstance(TetrisFXProject tetrisInstance) {
        this.tetrisInstance = tetrisInstance;
    }

} // class
