package com.changenode.plugin;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import com.changenode.Log;
import com.changenode.Plugin;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

public class DarkMode implements Plugin {

    private boolean isDark;

    private Scene scene;
    private Button toggleDark;

    @Override
    public void setup(Stage stage, TextArea textArea, ToolBar toolBar, Log log, MenuBar menuBar) {
        scene = stage.getScene();
        toggleDark = new Button();
        toggleDark.setText("Light");
        toggleDark.setOnAction(e -> toggleDark());
        toggleDark.setFocusTraversable(false);
        toolBar.getItems().add(toggleDark);
    }

    private void toggleDark() {
        if (isDark) {
            // This is how to set a light style w/the default JavaFX CSS
            // scene.getRoot().setStyle("");
            Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
            toggleDark.setText("Light");
        } else {
            // This is how to set a dark style w/the default JavaFX CSS.
            // scene.getRoot().setStyle("-fx-base:#25292D;");
            Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
            toggleDark.setText("Dark");
        }
        isDark = !isDark;
    }
}
