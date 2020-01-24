package tetris.model;

import tetris.controller.ControllerGameSession;

import java.util.List;

public interface Game {

    public void callController(ControllerGameSession observer);

    public boolean configuredLocation(List<int[]> newCoord, Tetramino piece, Tetramino[][] grid);

    public void configureRowColumnLocation(List<int[]> coords, Tetramino piece, Tetramino[][] grid);

} // interface
