package tetris.model;

import tetris.controller.ControllerGameSession;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.util.List;
import java.util.Random;

public class Tetris implements Game {

    public final static Random RAND = new Random();

    private final String[] SHAPE_NAMES = {"S", "Z", "L", "J", "T", "O", "I"};

    private final Tetramino[][] MATRIX;

    protected ControllerGameSession sessionController;

    protected Timeline timer;

    private Tetramino[][] shapes;

    private Tetramino nextShape;

    private Tetramino currentShape;

    private int score;

    private int level = 1; // Denote first level.

    private boolean stopGame;

    public Tetris() {

        this.MATRIX = new Tetramino[22][10];
        this.shapes = new Tetramino[4][4];

        this.nextShape = TetraminoGenerator.generate(SHAPE_NAMES[RAND.nextInt(SHAPE_NAMES.length)]);

        nextShape.getRowColList().forEach((rowCol) -> {
            shapes[rowCol[0]][rowCol[1]] = nextShape;
        });

        this.currentShape = TetraminoGenerator.generate(SHAPE_NAMES[RAND.nextInt(SHAPE_NAMES.length)]);

        currentShape.getRowColList().stream().map((rowCol) -> {
            rowCol[1] += MATRIX[0].length / 2;
            return rowCol;
        }).forEachOrdered((rowCol) -> {
            MATRIX[rowCol[0]][rowCol[1]] = currentShape;
        });
    } // constructor

    public void launch() {
        timer = new Timeline(new KeyFrame(Duration.millis(1000 - 100 * level),
                ae -> handleMove()));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();
    }

    public void handleMove() {

        if (defineGameStatus()) {
            stopGame = true;
            timer.stop();
        } else {

            List<int[]> nextRowCol = currentShape.downMove();

            if (configuredLocation(nextRowCol, currentShape, MATRIX)) {
                configureRowColumnLocation(nextRowCol, currentShape, MATRIX);
            } else {
                currentShape = nextShape;
                handleLine();
                boolean noMove = false;
                for (int[] rowCol : currentShape.getRowColList()) {
                    rowCol[1] += MATRIX[0].length / 2;
                    if (MATRIX[rowCol[0]][rowCol[1]] != null) {
                        noMove = true;
                    }
                    if (!noMove && MATRIX[rowCol[0]][rowCol[1]] == null) {
                        MATRIX[rowCol[0]][rowCol[1]] = currentShape;
                    }
                }
                nextShape = TetraminoGenerator.generate(SHAPE_NAMES[RAND.nextInt(SHAPE_NAMES.length)]);

                shapes = new Tetramino[4][4];

                nextShape.getRowColList().forEach((coord) -> {
                    shapes[coord[0]][coord[1]] = nextShape;
                });
            }
        }
        callController(sessionController);
    }

    public void supportKeyArrows(KeyCode code) {

        if (timer.getStatus() == Animation.Status.RUNNING && !isStopGame()) {
            List<int[]> rowColNext = currentShape.getRowColList();

            switch (code) {
                case UP:
                    rowColNext = currentShape.rotation();
                    break;
                case LEFT:
                    rowColNext = currentShape.leftMove();
                    break;
                case RIGHT:
                    rowColNext = currentShape.rightMove();
                    break;
                case DOWN:
                    rowColNext = currentShape.downMove();
                    break;
                default:
                    return;
            } // switch

            if (configuredLocation(rowColNext, currentShape, MATRIX)) {
                configureRowColumnLocation(rowColNext, currentShape, MATRIX);
            }
            callController(sessionController);
        }
    }

    private void handleLine() {
        boolean completeLine = false;
        for (int row = MATRIX.length - 1; row >= 0; row--) {
            completeLine = true;
            for (int col = 0; col < MATRIX[0].length && completeLine; col++) {
                if (MATRIX[row][col] == null || MATRIX[row][col] == currentShape) {
                    completeLine = false;
                }
            }
            if (completeLine) {
                deleteLine(row);
                row++;
            }
        }
    }

    private void deleteLine(int line) {

        for (int row = line; row >= 0; row--) {
            for (int col = 0; col < MATRIX[0].length && row > 0; col++) {
                MATRIX[row][col] = MATRIX[row - 1][col];
            }
        }

        score = score + 100; // Add 100 points for each completed line.

        callController(sessionController);

        if (score >= level * 1000 && level < 9) {
            level++;

            callController(sessionController);
            timer.stop();
            launch();
        }
    }

    public void assignGameSessionController(ControllerGameSession sessionController) {
        this.sessionController = sessionController;
    }

    // Checks if the game is over.
    private boolean defineGameStatus() {
        for (Tetramino item : MATRIX[0]) {
            if (item instanceof Tetramino && item != currentShape) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void callController(ControllerGameSession session) {
        session.updateSession();
    }

    @Override
    public boolean configuredLocation(List<int[]> rowCol, Tetramino tetramino,
            Tetramino[][] shapes) {
        for (int[] rc : rowCol) {
            if (rc[0] >= shapes.length || rc[0] < 0) {
                return false;
            }
            if (rc[1] >= shapes[0].length || rc[1] < 0) {
                return false;
            }
            if (shapes[rc[0]][rc[1]] instanceof Tetramino && shapes[rc[0]][rc[1]] != tetramino) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void configureRowColumnLocation(List<int[]> rowCol, Tetramino tetramino,
            Tetramino[][] shapes) {
        tetramino.getRowColList().forEach((int[] coord) -> {
            shapes[coord[0]][coord[1]] = null;
        });

        rowCol.forEach((coord) -> {
            shapes[coord[0]][coord[1]] = tetramino;
        });

        tetramino.setRowColList(rowCol);
    }

    public Tetramino[][] getShapes() {
        return shapes;
    }

    public String getScore() {
        return score + "";
    }

    public String getLevel() {
        return level + "";
    }

    public boolean isStopGame() {
        return stopGame;
    }

    public Timeline getTimer() {
        return timer;
    }

    public Tetramino[][] getMATRIX() {
        return MATRIX;
    }
}
