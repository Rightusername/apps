package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    static Pane root = new Pane();
    Game game = new Game();
    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            update();
        }
    };
    Guipanel guipanel = new Guipanel(game, timer);

    @Override
    public void start(Stage primaryStage) throws Exception{
        root.setPrefSize(game.SIZE*4-2, game.SIZE*3-2);
        root.getChildren().add(guipanel);
        root.getChildren().add(game);
        Scene scene = new Scene(root);
        primaryStage.setTitle("TicTacToe");

        primaryStage.setScene(scene);
        scene.getStylesheets().add(Main.class.getResource("styles.css").toExternalForm());
        primaryStage.setResizable(false);
        primaryStage.show();

        timer.start();
    }



    public void update(){
        if(!game.CanFill()) {
            timer.stop();
        }
        if(game.checkWin()==1 || game.checkWin()==0){
            game.endgame();
            timer.stop();
        }
        if(game.playerstep!=true){
            game.CompGame();
        }

        guipanel.showWin(game);
    }




    public static void main(String[] args) {
        launch(args);
    }



}
