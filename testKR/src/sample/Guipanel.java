package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;


/**
 * Created by Star on 28.04.2016.
 */
public class Guipanel extends Pane {
    int i=0, j=0;
    Label show = new Label();
    Label x = new Label("");
    Label o = new Label("");
    Label tictac = new Label(" TicTacToe");
    Guipanel(Game game, AnimationTimer timer){
        setTranslateX(game.SIZE * 3);
        setStyle("-fx-background-color: blueviolet;");
        setPrefSize(game.SIZE, game.SIZE * 3);

        Button newgame = new Button("Newgame");
        newgame.setStyle("-fx-background-color: darkmagenta; -fx-font-size: 30px; -fx-text-fill: #FFFF00; -fx-font-weight: bold;");

        x.setPrefSize(game.SIZE / 2, game.SIZE / 2);
        x.setStyle("-fx-background-image: url('x.png');" +
                "-fx-background-repeat: no-repeat;" +
                "    -fx-background-color: aliceblue;\n" +
                "    -fx-border-color: darkslateblue; \n" +
                "    -fx-background-size: 60%;\n" +
                "    -fx-border-width: 10px;\n" +
                "-fx-background-position: center");
        o.setStyle("-fx-background-image: url('o.png');" +
                "-fx-background-repeat: no-repeat;" +
                "    -fx-background-color: aliceblue;\n" +
                "    -fx-background-size: 80%;\n" +
                "    -fx-border-color: darkslateblue; \n" +
                "    -fx-border-width: 10px;\n" +
                "-fx-background-position: center");
        tictac.setPrefSize(game.SIZE, game.SIZE / 2);
        tictac.setStyle(" -fx-font-size: 38px; -fx-text-fill: #FFffff; -fx-font-weight: bold;");
        o.setPrefSize(game.SIZE / 2, game.SIZE / 2);
        o.setTranslateX(game.SIZE / 2);
        o.setTranslateY(game.SIZE/2);
        x.setTranslateY(game.SIZE/2);
        show.setTranslateY(game.SIZE * 1.25);
        show.setPrefSize(game.SIZE, game.SIZE / 2);
        show.setTextAlignment(TextAlignment.CENTER);
        show.setStyle("-fx-background-color: greenyellow; -fx-font-size: 40px;-fx-font-weight: bold; -fx-text-fill: darkmagenta;");

        newgame.setTranslateY(game.SIZE * 2.5);
        newgame.setPrefSize(game.SIZE, game.SIZE/2);
        newgame.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                for (i = 0; i < 3; i++) {
                    for (j = 0; j < 3; j++) {
                        game.n[i][j].refresh();
                    }
                }
                show.setText("");
                timer.start();
            }
        });
        getChildren().addAll(o, x,show,newgame,tictac);
    }

    public void showWin(Game game){
        if(game.checkWin()==1)
            show.setText(" You Win!");
        if(game.checkWin()==0)
            show.setText(" You Lose!");
        if(game.checkWin()==-1 && game.CanFill()==false)
            show.setText(" Standoff");
    }
}
