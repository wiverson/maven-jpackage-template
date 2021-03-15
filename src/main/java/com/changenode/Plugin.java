package com.changenode;

import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

/**
 * This is a very basic, leaky example of a plugin interface
 */
public interface Plugin {

    void setup(Stage stage, TextArea textArea, ToolBar toolBar, Log log, MenuBar menuBar);

}
