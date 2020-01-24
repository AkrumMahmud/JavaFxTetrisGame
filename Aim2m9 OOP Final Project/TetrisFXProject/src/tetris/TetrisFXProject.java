package tetris;

import tetris.controller.ControllerMenubar;
import tetris.controller.ControllerTetrisGame;
import tetris.model.Game;
import tetris.model.Tetris;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import javafx.scene.layout.VBox;

public class TetrisFXProject extends Application {

    private Stage primStage;

    private VBox vboxRoot;

    private Game game;

    private ControllerMenubar menuBar;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            this.primStage = primaryStage;
            this.primStage.setTitle("Tetris");

            this.primStage.setOnCloseRequest((WindowEvent we) -> {
                Platform.exit();
                System.exit(0);
            });

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(TetrisFXProject.class.getResource("view/rootLayout.fxml"));
            vboxRoot = (VBox) fxmlLoader.load();

            Scene scene = new Scene(vboxRoot);
            primaryStage.setScene(scene);

            updateMenubar();

            updateGameSession();

            primaryStage.show();

        } catch (IOException exc) {
            ExceptionHandler.handle(exc);
        }
    } // method

    public void updateMenubar() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(TetrisFXProject.class.getResource("view/menubarLayout.fxml"));
            AnchorPane menu = (AnchorPane) fxmlLoader.load();

            menuBar = fxmlLoader.getController();
            menuBar.setTetrisInstance(this);

            vboxRoot.getChildren().add(menu);

        } catch (IOException exc) {
            ExceptionHandler.handle(exc);
        }
    }

    public void updateGameSession() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(TetrisFXProject.class.getResource("view/boardLayout.fxml"));
            AnchorPane gamePane = (AnchorPane) fxmlLoader.load();

            Tetris tetrisInstance = new Tetris();
            game = tetrisInstance;

            ControllerTetrisGame tetrisController = fxmlLoader.getController();
            tetrisController.setGame(tetrisInstance);

            if (vboxRoot.getChildren().size() > 1) {
                vboxRoot.getChildren().remove(1); // Remove only board
                // when the user clicks on 'New Game' option. Menu bar
                // is under index 0, so we cannot use clear() function,
                // since menu bar will be deleted as well.
            }

            vboxRoot.getChildren().add(gamePane);

            this.setSize(gamePane);

            tetrisController.startSession();

            primStage.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
                tetrisInstance.supportKeyArrows(event.getCode());
            });

            tetrisInstance.launch();

        } catch (IOException exc) {
            ExceptionHandler.handle(exc);
        }
    }

    public void setSize(AnchorPane gamePane) {
        primStage.setHeight(gamePane.getPrefHeight() + 65);
        primStage.setWidth(gamePane.getPrefWidth() + 15);
        primStage.centerOnScreen();
    }

    public Stage getPrimStage() {
        return primStage;
    }

    public Game getGame() {
        return game;
    }

} // class
