package com.changenode.plugin;

import com.changenode.Log;
import com.changenode.Plugin;
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

import static java.lang.System.getProperty;

public class StandardMenus implements Plugin {

    private Stage stage;
    private MenuBar menuBar;
    private Log output;

    public static boolean isMac() {
        return getProperty("os.name").contains("Mac");
    }

    public static MenuItem Configure(String name, EventHandler<ActionEvent> action, KeyCode keyCode) {
        MenuItem item = new MenuItem(name);
        item.setOnAction(action);
        if (keyCode != null)
            item.setAccelerator(new KeyCodeCombination(keyCode, KeyCombination.SHORTCUT_DOWN));
        return item;
    }

    private void openFileDialog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            output.log(file.getAbsolutePath());
        } else {
            output.log("Open File cancelled.");
        }
    }

    public void standardMenus() {

        Menu file = new Menu("File");
        MenuItem newFile = Configure("New", x -> output.log("File -> New"), KeyCode.N);
        MenuItem open = Configure("Open...", x -> openFileDialog(), KeyCode.O);

        file.getItems().addAll(newFile, open);

        if (!isMac()) {
            MenuItem quit = Configure("Quit", x -> Platform.exit(), KeyCode.Q);
            file.getItems().add(quit);
        } else {
            menuBar.setUseSystemMenuBar(true);
        }

        Menu edit = new Menu("Edit");
        MenuItem undo = Configure("Undo", x -> output.log("Undo"), KeyCode.Z);
        MenuItem redo = Configure("Redo", x -> output.log("Redo"), KeyCode.R);
        SeparatorMenuItem editSeparator = new SeparatorMenuItem();
        MenuItem cut = Configure("Cut", x -> output.log("Cut"), KeyCode.X);
        MenuItem copy = Configure("Copy", x -> output.log("Copy"), KeyCode.C);
        MenuItem paste = Configure("Paste", x -> output.log("Paste"), KeyCode.V);

        edit.getItems().addAll(undo, redo, editSeparator, cut, copy, paste);

        menuBar.getMenus().addAll(file, edit);
    }

    @Override
    public void setup(Stage stage, TextArea textArea, ToolBar toolBar, Log log, MenuBar menuBar) {
        this.menuBar = menuBar;
        this.output = log;
        this.stage = stage;

        standardMenus();
    }
}
