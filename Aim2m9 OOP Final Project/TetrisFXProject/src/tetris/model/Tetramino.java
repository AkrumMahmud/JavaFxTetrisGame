package tetris.model;

import java.util.ArrayList;
import java.util.List;

public class Tetramino {

    private List<int[]> rowColList;

    private int[] turn;

    private String titleColor;

    public Tetramino(List<int[]> rowColList) {
        this.rowColList = rowColList;
    }

    public List<int[]> leftMove() {
        List<int[]> left = new ArrayList<>();

        int[] nextTurn = turn;

        for (int[] rowCol : rowColList) {
            int[] newCoord = new int[]{rowCol[0], rowCol[1] - 1};

            left.add(newCoord);

            if (turn == rowCol) {
                nextTurn = newCoord;
            }
        }

        if (nextTurn != null) {
            left.add(nextTurn);
        }

        return left;
    }

    public List<int[]> rightMove() {
        List<int[]> rightMove = new ArrayList<>();

        int[] nextTurn = turn;

        for (int[] rowCol : rowColList) {
            int[] newCoord = new int[]{rowCol[0], rowCol[1] + 1};

            rightMove.add(newCoord);

            if (turn == rowCol) {
                nextTurn = newCoord;
            }
        }

        if (nextTurn != null) {
            rightMove.add(nextTurn);
        }

        return rightMove;
    }

    public List<int[]> downMove() {
        List<int[]> moveDown = new ArrayList<>();
        int[] nextTurn = turn;

        for (int[] rowCol : rowColList) {
            int[] newCoord = new int[]{rowCol[0] + 1, rowCol[1]};

            moveDown.add(newCoord);

            if (turn == rowCol) {
                nextTurn = newCoord;
            }
        }

        if (nextTurn != null) {
            moveDown.add(nextTurn);
        }

        return moveDown;
    }

    public List<int[]> upMove() {
        List<int[]> moveUp = new ArrayList<>();

        for (int[] rowCol : rowColList) {
            int[] newCoord = new int[]{rowCol[0] - 1, rowCol[1]};

            moveUp.add(newCoord);
        }

        return moveUp;
    }

    public List<int[]> rotation() {
        List<int[]> rotate = new ArrayList<>();
        int[] nextTurn = turn;

        for (int[] rowCol : rowColList) {

            int[] direction = new int[]{rowCol[0] - turn[0], rowCol[1] - turn[1]};

            int[] rotationFactor = new int[]{-direction[1], direction[0]};

            int[] nextRowCol = new int[]{turn[0] + rotationFactor[0], turn[1]
                + rotationFactor[1]};

            rotate.add(nextRowCol);

            if (turn == rowCol) {
                nextTurn = nextRowCol;
            }
        }

        if (nextTurn != null) {
            rotate.add(nextTurn);
        }

        return rotate;
    }

    public List<int[]> makeMove(int[] vector) {

        List<int[]> nextRowCol = new ArrayList<>();

        for (int[] rowCol : rowColList) {
            nextRowCol.add(new int[]{rowCol[0] + vector[0], rowCol[1] + vector[1]});
        }

        if (turn != null) {
            nextRowCol.add(new int[]{turn[0] + vector[0], turn[1] + vector[1]});
        }

        return nextRowCol;
    }

    public List<int[]> getRowColList() {
        return rowColList;
    }

    public void setRowColList(List<int[]> rowColList) {
        this.rowColList = rowColList;
        if (turn != null) {
            turn = rowColList.remove(rowColList.size() - 1);
        }
    }

    public void setTurn(int[] turn) {
        this.turn = turn;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

} // class
