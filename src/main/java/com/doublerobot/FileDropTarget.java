package com.doublerobot;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.File;

public class FileDropTarget {

    public Node setupFileDropTarget() {
        Label label = new Label("Drag one or more files and/or directories here.");
        label.setPadding(new Insets(5.0f, 5.0f, 5.0f, 5.0f));

        Label dropped = new Label("");
        dropped.setPadding(new Insets(10.0f, 10.0f, 10.0f, 10.0f));
        VBox dragTarget = new VBox();
        dragTarget.getChildren().addAll(label, dropped);
        dragTarget.setOnDragOver(event -> {
            if (event.getGestureSource() != dragTarget && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();

        });

        dragTarget.setOnDragEntered(event -> {
            dragTarget.setBackground(
                    new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        });

        dragTarget.setOnDragExited(event -> {
            dragTarget.setBackground(
                    new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        });

        dragTarget.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {

                StringBuffer stringBuffer = new StringBuffer();

                for (File file : db.getFiles()) {
                    stringBuffer.append(file.getAbsolutePath());
                    if (file.isDirectory())
                        stringBuffer.append(" - directory");
                    stringBuffer.append('\n');
                }

                dropped.setText(stringBuffer.toString());
                success = true;
            }
            /* let the source know whether the information was successfully transferred and used */
            event.setDropCompleted(success);

            event.consume();
        });

        return dragTarget;
    }

}
