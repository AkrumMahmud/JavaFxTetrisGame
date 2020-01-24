package tetris.controller;

import tetris.DB;
import tetris.model.Tetris;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import tetris.ExceptionHandler;

public class ControllerTetrisGame extends ControllerGameSession implements Initializable {

    @FXML
    private GridPane nextShape;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label levelLabel;

    private Label[][] grid;

    private Tetris game;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Override
    public void startSession() {

        game.assignGameSessionController(this);

        board.setStyle("-fx-border-color:grey");
        lbls = new Label[game.getMATRIX().length][game.getMATRIX()[0].length];
        initBoard(board, game.getMATRIX(), lbls);

        grid = new Label[game.getShapes().length][game.getShapes()[0].length];
        initBoard(nextShape, game.getShapes(), grid);

        this.updateSession();
    }

    @Override
    public void updateSession() {

        if (game.isStopGame()) {
            try {
                DB.store(game);
            } catch (IOException exc) {
                ExceptionHandler.handle(exc);
            }
            Platform.runLater(() -> {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Tetris Game");
                alert.setHeaderText("Game is over");
                alert.show();
            });
        } else {
            updateBoard(game.getMATRIX(), lbls);
            updateBoard(game.getShapes(), grid);
            scoreLabel.setText(game.getScore());
            levelLabel.setText(game.getLevel());
        }
    }

    public void setGame(Tetris game) {
        this.game = game;
    }
}
