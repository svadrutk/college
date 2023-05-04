import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.application.Platform;
import javafx.geometry.Pos;

import java.util.Random;

public class DessertGame extends Application {
    private int score = 0;

    @Override
    public void start(final Stage stage) {
        // Step 3 & 4
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 640, 480);
        stage.setTitle("Dessert in the Desert JavaFX Game");

        // Step 5
        Label scoreLabel = new Label("Score: 0");
        borderPane.setTop(scoreLabel);
        BorderPane.setAlignment(scoreLabel, Pos.TOP_LEFT);

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> {
            Platform.exit();
        });
        borderPane.setBottom(exitButton);
        BorderPane.setAlignment(exitButton, Pos.BOTTOM_RIGHT);

        // Step 6
        Pane pane = new Pane();
        borderPane.setCenter(pane);
        BorderPane.setAlignment(pane, Pos.CENTER);

        // TODO: Step 7-10
        Random rand = new Random();
        // Step 7
        Button dessert = new Button("dessert");
        Button desert1 = new Button("desert");
        Button desert2 = new Button("desert");
        Button desert3 = new Button("desert");
        Button desert4 = new Button("desert");
        Button desert5 = new Button("desert");
        Button desert6 = new Button("desert");
        Button desert7 = new Button("desert");

        Button[] buttons = new Button[] {desert1, desert2, desert3, desert4, desert5, desert6, desert7, dessert};

        for(Button button : buttons) {
            pane.getChildren().add(button);
        }
        randomizeButtonPositions(rand, buttons);
        exitButton.requestFocus();
        dessert.setOnAction(event -> {
            randomizeButtonPositions(rand, buttons);
            score++;
            scoreLabel.setText("Score: " + score);
            exitButton.requestFocus();

        });
        desert1.setOnAction(event -> {
            randomizeButtonPositions(rand, buttons);
            score--;
            scoreLabel.setText("Score: " + score);
            exitButton.requestFocus();

        });
        desert2.setOnAction(event -> {
            randomizeButtonPositions(rand, buttons);
            score--;
            scoreLabel.setText("Score: " + score);
            exitButton.requestFocus();

        });
        desert3.setOnAction(event -> {
            randomizeButtonPositions(rand, buttons);
            score--;
            scoreLabel.setText("Score: " + score);
            exitButton.requestFocus();

        });
        desert4.setOnAction(event -> {
            randomizeButtonPositions(rand, buttons);
            score--;
            scoreLabel.setText("Score: " + score);
            exitButton.requestFocus();

        });
        desert5.setOnAction(event -> {
            randomizeButtonPositions(rand, buttons);
            score--;
            scoreLabel.setText("Score: " + score);
            exitButton.requestFocus();

        });
        desert6.setOnAction(event -> {
            randomizeButtonPositions(rand, buttons);
            score--;
            scoreLabel.setText("Score: " + score);
            exitButton.requestFocus();

        });
        desert7.setOnAction(event -> {
            randomizeButtonPositions(rand, buttons);
            score--;
            scoreLabel.setText("Score: " + score);
            exitButton.requestFocus();

        });
        stage.setScene(scene);
        stage.show();
    }

    private void randomizeButtonPositions(Random rand, Button[] buttons) {
        for (Button button : buttons) {
            button.setLayoutX(rand.nextInt(600));
            button.setLayoutY(rand.nextInt(400));
        }
    }
    public static void main(String[] args) {
        Application.launch();
    }
}