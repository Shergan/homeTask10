package com.divashchenko;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    private static final int BOARD_WIDTH = 500;
    private static final int BOARD_HEIGHT = 500;
    private static final int CIRCLE_DIAMETER = 30;

    private int x = 40;
    private int y = 0;
    private GraphicsContext gc;
    private boolean left = true;
    private boolean up = true;
    private Random random = new Random();
    private int speedX = 0;
    private int speedY = 0;


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

        startMoving();
    }

    private void startMoving() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                move();
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, 20, 20);
    }

    private void move() {
        clear();
        if (x <= 0) {
            left = true;
            speedX = random.nextInt(4);
        }
        if (y <= 0) {
            up = true;
            speedY = random.nextInt(4);
        }
        if (y >= gc.getCanvas().getHeight() - CIRCLE_DIAMETER) {
            up = false;
            speedY = random.nextInt(4);
        }
        if (x >= gc.getCanvas().getWidth() - CIRCLE_DIAMETER) {
            left = false;
            speedX = random.nextInt(4);
        }

        if (left) {
            x += 2 + speedX;
        }
        if (!left) {
            x -= 2 + speedX;
        }
        if (up) {
            y += 2 + speedY;
        }
        if (!up) {
            y -= 2 + speedY;
        }
        draw();
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

