package com.doublerobot;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.filechooser.FileSystemView;
import java.io.*;

public class HelloWorld extends Application {

    private Button button;

    public static void main(String[] args) {

        try {
            File outputFile = File.createTempFile("debug", ".log", FileSystemView.getFileSystemView().getDefaultDirectory());
            PrintStream output = new PrintStream(new BufferedOutputStream(new FileOutputStream(outputFile)), true);
            System.setOut(output);
            System.setErr(output);
        } catch (IOException e) {
            e.printStackTrace();
        }

        launch(args);
    }

    @Override
    public void start(Stage stage) {

        Label label = new Label();
        label.setText("Launched.");
        label.setBackground(new Background(new BackgroundFill(Color.gray(0.8f), CornerRadii.EMPTY, Insets.EMPTY)));
        label.setPadding(new Insets(5.0f, 5.0f, 5.0f, 5.0f));
        label.setMaxWidth(Double.MAX_VALUE);

        BorderPane borderPane = new BorderPane();

        VBox topElements = new VBox();

        topElements.getChildren().add(new StandardMenus().standardMenus(stage, label));

        ToolBar toolbar = new ToolBar();

        button = new Button();
        button.setText("Hello World");
        button.setOnAction(event ->
                label.setText("Hello World! " + java.util.Calendar.getInstance().getTime()));
        button.setFocusTraversable(false);

        toolbar.getItems().add(button);

        topElements.getChildren().add(toolbar);

        borderPane.setTop(topElements);
        borderPane.setCenter(new FileDropTarget().setupFileDropTarget());
        borderPane.setBottom(label);

        Scene scene = new Scene(borderPane, 800, 600);

        stage.setTitle("Hello World!");
        stage.setScene(scene);
        stage.show();
    }
}
