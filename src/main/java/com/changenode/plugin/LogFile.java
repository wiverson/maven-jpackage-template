package com.changenode.plugin;

import com.changenode.BaseApplication;
import com.changenode.Log;
import com.changenode.Plugin;
import javafx.scene.control.*;
import javafx.stage.Stage;

import static java.awt.Desktop.getDesktop;
import static java.lang.System.out;
import static java.util.Calendar.getInstance;

public class LogFile implements Plugin {
    @Override
    public void setup(Stage stage, TextArea textArea, ToolBar toolBar, Log log, MenuBar menuBar) {

        Menu menu = new Menu("Debug");
        MenuItem findDebugLog = new MenuItem("Find Debug Log");
        findDebugLog.setOnAction(e -> showDebugLog());

        MenuItem writeHelloWorldToLog = new MenuItem("Write Hello World to Log");
        writeHelloWorldToLog.setOnAction(e -> out.println("Hello World! " + getInstance().getTime()));

        menu.getItems().addAll(findDebugLog, writeHelloWorldToLog);
        menuBar.getMenus().add(menu);
    }

    private void showDebugLog() {
        getDesktop().browseFileDirectory(BaseApplication.outputFile);
    }
}
