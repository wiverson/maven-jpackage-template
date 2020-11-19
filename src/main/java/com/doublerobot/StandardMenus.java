package com.doublerobot;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class StandardMenus {

    Label actionTarget;
    Stage stage;

    private void openFileDialog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            actionTarget.setText(file.getAbsolutePath());
        } else {
            actionTarget.setText("Open File cancelled.");
        }
    }

    public static MenuItem Configure(String name, EventHandler<ActionEvent> action, KeyCode keyCode) {
        MenuItem item = new MenuItem(name);
        item.setOnAction(action);
        if (keyCode != null)
            item.setAccelerator(new KeyCodeCombination(keyCode, KeyCombination.SHORTCUT_DOWN));
        return item;
    }

    public MenuBar standardMenus(Stage s, Label at) {

        stage = s;
        actionTarget = at;

        MenuBar menuBar = new MenuBar();

        Menu file = new Menu("File");
        MenuItem newFile = Configure("New", x -> actionTarget.setText("File New"), KeyCode.N);
        MenuItem open = Configure("Open...", x -> openFileDialog(), KeyCode.O);

        file.getItems().addAll(newFile, open);

        if (!MacSpecific.isMac()) {
            MenuItem quit = Configure("Quit", x -> Platform.exit(), KeyCode.Q);
            file.getItems().add(quit);
        } else {
            menuBar.setUseSystemMenuBar(true);
        }

        Menu edit = new Menu("Edit");
        MenuItem undo = Configure("Undo", x -> actionTarget.setText("Undo"), KeyCode.Z);
        MenuItem redo = Configure("Redo", x -> actionTarget.setText("Redo"), KeyCode.R);
        SeparatorMenuItem editSeparator = new SeparatorMenuItem();
        MenuItem cut = Configure("Cut", x -> actionTarget.setText("Cut"), KeyCode.X);
        MenuItem copy = Configure("Copy", x -> actionTarget.setText("Copy"), KeyCode.C);
        MenuItem paste = Configure("Paste", x -> actionTarget.setText("Paste"), KeyCode.V);

        edit.getItems().addAll(undo, redo, editSeparator, cut, copy, paste);

        menuBar.getMenus().addAll(file, edit);

        menuBar.getMenus().add(new ExtraDesktopIntegration().extraDesktopIntegration(actionTarget));

        return menuBar;
    }

}
