package tetris.controller;

import tetris.model.Tetramino;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public abstract class ControllerGameSession {

    @FXML
    GridPane board;

    Label[][] lbls;

    public abstract void startSession();

    public abstract void updateSession();

    protected void initBoard(GridPane gridView, Tetramino[][] grid, Label[][] labels) {

        double height = gridView.getPrefHeight() / grid.length;
        double width = gridView.getPrefWidth() / grid[0].length;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                Label lbl = new Label();
                lbl.setPrefHeight(height);
                lbl.setPrefWidth(width);
                lbl.setAlignment(Pos.CENTER);

                GridPane.setRowIndex(lbl, row);
                GridPane.setColumnIndex(lbl, col);
                gridView.getChildren().add(lbl);

                labels[row][col] = lbl;
            }
        }
    }

    protected void updateBoard(Tetramino[][] shapes, Label[][] lbls) {
        for (int row = 0; row < shapes.length; row++) {
            for (int col = 0; col < shapes[0].length; col++) {
                if (shapes[row][col] != null) {
                    lbls[row][col].setStyle("-fx-border-color:grey;-fx-background-color:"
                            + shapes[row][col].getTitleColor() + ";");
                } else {
                    lbls[row][col].setStyle("");
                }
            }
        }
    }

} // class
