package com.divashchenko;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int BOARD_WIDTH = 500;
    private static final int BOARD_HEIGHT = 500;
    private static final int CIRCLE_DIAMETER = 30;

    private int x = 10;
    private int y = 10;
    private GraphicsContext gc;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Test");
        Canvas canvas = new Canvas();
        canvas.setWidth(BOARD_WIDTH);
        canvas.setHeight(BOARD_HEIGHT);
        BorderPane group = new BorderPane(canvas);
        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.show();

        gc = canvas.getGraphicsContext2D();
        draw();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        if (y > 0) {
                            y -= 5;
                        }
                        break;
                    case DOWN:
                        if (y < gc.getCanvas().getWidth() - CIRCLE_DIAMETER) {
                            y += 5;
                        }
                        break;
                    case LEFT:
                        if (x > 0) {
                            x -= 5;
                        }
                        break;
                    case RIGHT:
                        if (x < gc.getCanvas().getWidth() - CIRCLE_DIAMETER) {
                            x += 5;
                        }
                        break;
                }
                clear();
                draw();
            }
        });
    }

    private void clear() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }

    private void draw() {
        gc.setLineWidth(2);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(x, y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
        gc.setFill(Color.RED);
        gc.fillOval(x, y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
    }

}
