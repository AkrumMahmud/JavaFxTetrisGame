package tetris.model;

import java.util.ArrayList;
import java.util.List;

public class TetraminoGenerator {

    private final static String S_COLOR = "#A0522D";

    private final static String Z_COLOR = "#7FFF00";

    private final static String T_COLOR = "#FF0000";

    private final static String L_COLOR = "#FF00FF";

    private final static String I_COLOR = "#98FB98";

    private final static String O_COLOR = "#FFDAB9";

    private final static String J_COLOR = "#FF8C00";

    public static Tetramino generate(String type) {

        List<int[]> coord;
        Tetramino tetramino = new Tetramino(coord = new ArrayList<>());

        switch (type) {
            case "Z":
                coord.add(new int[]{0, 0});
                coord.add(new int[]{0, 1});
                coord.add(new int[]{1, 1});
                coord.add(new int[]{1, 2});
                tetramino.setTurn(coord.get(1));
                tetramino.setTitleColor(Z_COLOR);
                break;
            case "J":
                coord.add(new int[]{0, 1});
                coord.add(new int[]{0, 2});
                coord.add(new int[]{0, 3});
                coord.add(new int[]{1, 3});
                tetramino.setTurn(coord.get(2));
                tetramino.setTitleColor(J_COLOR);
                break;
            case "S":
                coord.add(new int[]{0, 2});
                coord.add(new int[]{0, 1});
                coord.add(new int[]{1, 1});
                coord.add(new int[]{1, 0});
                tetramino.setTurn(coord.get(1));
                tetramino.setTitleColor(S_COLOR);
                break;
            case "L":
                coord.add(new int[]{0, 0});
                coord.add(new int[]{1, 0});
                coord.add(new int[]{2, 0});
                coord.add(new int[]{2, 1});
                tetramino.setTurn(coord.get(2));
                tetramino.setTitleColor(L_COLOR);
                break;
            case "T":
                coord.add(new int[]{0, 0});
                coord.add(new int[]{1, 0});
                coord.add(new int[]{2, 0});
                coord.add(new int[]{1, 1});
                tetramino.setTurn(coord.get(3));
                tetramino.setTitleColor(T_COLOR);
                break;
            case "O":
                coord.add(new int[]{0, 0});
                coord.add(new int[]{0, 1});
                coord.add(new int[]{1, 0});
                coord.add(new int[]{1, 1});
                tetramino.setTurn(coord.get(1));
                tetramino.setTitleColor(O_COLOR);
                break;
            case "I":
                coord.add(new int[]{0, 0});
                coord.add(new int[]{1, 0});
                coord.add(new int[]{2, 0});
                coord.add(new int[]{3, 0});
                tetramino.setTurn(coord.get(1));
                tetramino.setTitleColor(I_COLOR);
                break;
        }
        return tetramino;
    } // method

} // class
