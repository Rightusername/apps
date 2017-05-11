package sample;

import javafx.scene.layout.Pane;

/**
 * Created by Star on 26.04.2016.
 */
public class Game extends Pane {
    int i = 0, j = 0;
    public boolean playerstep = false;
    public static int SIZE = 200;
    GameFieldItem[][] n = new GameFieldItem[3][3];
    int[][] bot = new int[3][3];

    Game() {
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                GameFieldItem y = new GameFieldItem(0 + i * SIZE, 0 + j * SIZE, SIZE);
                y.setOnMouseClicked(event -> {
                    if (playerstep == true) {
                        if (y.checked != true) {
                            y.l.setStyle("-fx-background-image: url('x.png');" +
                                    "-fx-background-repeat: no-repeat;" +
                                    "    -fx-background-color: #eee;\n" +
                                    "    -fx-border-color: aqua; \n" +
                                    "    -fx-background-size: 60%;\n" +
                                    "    -fx-border-width: 10px;\n" +
                                    "-fx-background-position: center");
                            if (y.checked != true) {
                                y.checked = true;
                                playerstep = false;
                                y.value = 1;
                            }
                        }
                    }
                });
                n[i][j] = y;
                getChildren().add(y);
            }
        }
    }


    public boolean CanFill() {
        for (int k = 0; k < 3; k++) {
            for (int h = 0; h < 3; h++) {
                if (n[h][k].checked == false)
                    return true;
            }
        }
        return false;
    }

    public void botstep() {
        int x = (int) Math.floor(Math.random() * 3);
        int y = (int) Math.floor(Math.random() * 3);
        step(n, x, y);
    }


    public void step(GameFieldItem[][] n, int i, int j) {
        if (n[i][j].checked == false) {
            n[i][j].l.setStyle("-fx-background-image: url('o.png');" +
                    "-fx-background-repeat: no-repeat;" +
                    "    -fx-background-color: #eee;\n" +
                    "    -fx-background-size: 90%;\n" +
                    "    -fx-border-color: aqua; \n" +
                    "    -fx-border-width: 10px;\n" +
                    "-fx-background-position: center");
            n[i][j].checked = true;
            n[i][j].value = 0;
            playerstep = true;
        }
    }

    public void endgame() {
        for (int k = 0; k < 3; k++) {
            for (int h = 0; h < 3; h++) {
                n[h][k].checked = true;
            }
        }
    }

    public int checkWin() {
        for (int i = 0; i < 3; i++) {
            if ((n[0][i].value == n[1][i].value && n[1][i].value == n[2][i].value && n[0][i].value != -1)) {
                n[0][i].win();
                n[1][i].win();
                n[2][i].win();
                return n[0][i].value;
            }
        }
        for (int i = 0; i < 3; i++) {
            if ((n[i][0].value == n[i][1].value && n[i][1].value == n[i][2].value && n[i][2].value != -1)) {
                n[i][0].win();
                n[i][1].win();
                n[i][2].win();
                return n[i][0].value;
            }
        }
        if ((n[0][0].value == n[1][1].value && n[1][1].value == n[2][2].value && n[1][1].value != -1)) {
            n[0][0].win();
            n[1][1].win();
            n[2][2].win();
            return n[0][0].value;
        }
        if ((n[2][0].value == n[1][1].value && n[1][1].value == n[0][2].value && n[1][1].value != -1)) {
            n[2][0].win();
            n[1][1].win();
            n[0][2].win();
            return n[2][0].value;
        }
        return -1;
    }

    public void CompGame() { // Так сказать ИИ :)


        int tx = -1, ty = -1, tp = 0; // tp - приоритет выбранной целевой клетки.

        for (int stX = 0; stX < n.length; stX++)
            for (int stY = 0; stY < n[0].length; stY++) // для каждой клетки
            {
                bot[stX][stY] = n[stX][stY].value;
            }


        for (int stX = 0; stX < n.length; stX++)
            for (int stY = 0; stY < n[0].length; stY++) // для каждой клетки
            {
                GameFieldItem lC = n[stX][stY];
                if (lC.value == -1) { // только для пустых клеток
                    bot[stX][stY] = 0;
                    if (isWin() == 1) { // пробуем победить
                        tx = stX;
                        ty = stY;
                        tp = 3;
                    } else if (tp < 3) {
                        bot[stX][stY] = 1;
                        if (isWin() == 0) { // или помешать победить игроку.
                            tx = stX;
                            ty = stY;
                            tp = 2;
                        } else if (tp < 2) { // или...
                            int mini = -1, maxi = 1, minj = -1, maxj = 1;
                            if (stX >= n.length - 1) maxi = 0;
                            if (stY >= n[0].length - 1) maxj = 0;
                            if (stX < 1) mini = 0;
                            if (stY < 1) minj = 0;
                            // найти ближайший нолик...
                            for (i = mini; i <= maxi; i++)
                                for (j = minj; j <= maxj; j++)
                                    if ((i != 0) && (j != 0)) { // если есть рядом своя занятая клетка - поближе к своим
                                        if (bot[stX + i][stY + j] == 0) {
                                            tx = stX;
                                            ty = stY;
                                            tp = 1;
                                        }
                                    }
                            if (tp < 1) { // или хотя бы на свободную клетку поставить.
                                tx = stX;
                                ty = stY;
                            }
                        }
                    }
                    bot[stX][stY] = lC.value;
                    //  step(n,stX,stY); // поставим нолик в клетку.
                    //  playerstep = true;
                }
            }
        if ((tx != -1) && (ty != -1)) { // если целевая клетка выбранна
             step(n, tx, ty); // поставим нолик в клетку.
            playerstep = true;
            System.out.println("tx = " + tx + " ty = " + ty);
        }
    }

    public int isWin() { // Проверка победы.
        // проверка зон 3 на 3...
        for (int stX = 0; stX <= n.length - 3; stX++) {
            for (int stY = 0; stY <= n[0].length - 3; stY++) // Если размер поля больше трёх.
            {
                GameFieldItem lC = n[stX][stY]; // проверка линии
                if (lC != null) for (i = 0; i < 3; i++)
                    if (n[i + stX][i + stY] != lC)
                        lC = null; // если проверяемая клетка не содержит lC, то сбросить значение lC
                if (lC != null) return lC.value; // если победа обнаружена.
                lC = n[2 + stX][stY];
                if (lC != null) for (i = 0; i < 3; i++) if (n[2 - i + stX][i + stY] != lC) lC = null;
                if (lC != null) return lC.value;

                for (i = 0; i < 3; i++) { // проверка по вертикали
                    lC = n[stX + i][stY];
                    if (lC != null) for (j = 0; j < 3; j++) if (n[i + stX][j + stY] != lC) lC = null;
                    if (lC != null) return lC.value;
                }
                for (j = 0; j < 3; j++) { // проверка по горизонтали
                    lC = n[stX][stY + j];
                    if (lC != null) for (i = 0; i < 3; i++) if (n[i + stX][j + stY] != lC) lC = null;
                    if (lC != null) return lC.value;
                }
            }
        }
                //  }
                // TO DO - если все клетки заняты, но никто не победил, то ничья, но пока это условие не сделано. Попробуйте сами доделать эту проверку ;)
                return -1; // если никто не победил


    }
}
