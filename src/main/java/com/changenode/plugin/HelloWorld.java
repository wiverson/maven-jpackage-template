package com.changenode.plugin;

import com.changenode.Log;
import com.changenode.Plugin;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

public class HelloWorld implements Plugin {

    Button button;

    @Override
    public void setup(Stage stage, TextArea textArea, ToolBar toolBar, Log log, MenuBar menuBar) {
        button = new Button();
        button.setText("Hello World");
        button.setOnAction(event -> log.log("Hello World! " + java.util.Calendar.getInstance().getTime()));
        button.setFocusTraversable(false);

        toolBar.getItems().add(button);
    }
}
