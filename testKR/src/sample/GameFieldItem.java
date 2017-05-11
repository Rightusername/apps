package sample;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Created by Star on 25.04.2016.
 */
public class GameFieldItem extends Pane {
    Label l;
    int value=-1;
    boolean checked=false;

    GameFieldItem(int x, int y, int SIZE){
        l = new Label();
        l.setPrefSize(SIZE, SIZE);
        l.setAlignment(Pos.CENTER);
        setTranslateY(y);
        setTranslateX(x);
        l.setStyle("\n" +
                "    -fx-background-color: #eee;\n" +
                "    -fx-border-color: aqua; \n" +
                "    -fx-border-width: 10px;\n" +
                "  ");
        getChildren().addAll(l);
    }

    public void refresh(){
        value=-1;
        checked = false;
        l.setStyle("\n" +
                "    -fx-background-color: #eee;\n" +
                "    -fx-border-color: aqua; \n" +
                "    -fx-border-width: 10px;\n" +
                "  ");
    }

    public void win(){
        if(value==1) {
            l.setStyle("-fx-background-image: url('x.png');" +
                    "-fx-background-repeat: no-repeat;"+
                    "    -fx-background-color: #aea;\n" +
                    "    -fx-border-color: khaki; \n" +
                    "    -fx-border-width: 10px;\n"+
                    "    -fx-background-size: 60%;\n" +
                    "-fx-background-position: center");
        }
        if(value==0) {
            l.setStyle("-fx-background-image: url('o.png');" +
                    "-fx-background-repeat: no-repeat;"+
                    "    -fx-background-color: #aea;\n" +
                    "    -fx-background-size: 90%;\n" +
                    "    -fx-border-color: khaki; \n" +
                    "    -fx-border-width: 10px;\n"+
                    "-fx-background-position: center");
        }
    }


}


