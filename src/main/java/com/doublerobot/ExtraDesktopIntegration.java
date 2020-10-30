package com.doublerobot;

import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;

import java.awt.*;
import java.awt.Taskbar.Feature;
import java.awt.image.BufferedImage;

import static com.doublerobot.StandardMenus.Configure;

public class ExtraDesktopIntegration {

    int currentIconProgress = 1;

    Image defaultIcon;
    Image redCircleIcon;

    public Menu extraDesktopIntegration(Label target) {
        if (!Taskbar.isTaskbarSupported())
            return null;

        target.setText(target.getText() + "\n");

        for (Feature feature : Feature.values()) {
            target.setText(target.getText() + " " + feature.name() + " " + Taskbar.getTaskbar().isSupported(feature));
        }

        if (Taskbar.getTaskbar().isSupported(Feature.ICON_IMAGE)) {
            defaultIcon = Taskbar.getTaskbar().getIconImage();

            BufferedImage bufferedImage = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics2D = bufferedImage.createGraphics();
            graphics2D.setColor(Color.red);
            graphics2D.fillOval(0, 0, 256, 256);
            graphics2D.dispose();

            redCircleIcon = bufferedImage;

        }
        MenuItem useCustomIcon = Configure("Use Custom App Icon", x -> Taskbar.getTaskbar().setIconImage(redCircleIcon), null);
        MenuItem useDefaultAppIcon = Configure("Use Default App Icon", x -> Taskbar.getTaskbar().setIconImage(defaultIcon), null);
        useCustomIcon.setDisable(!Taskbar.getTaskbar().isSupported(Feature.ICON_IMAGE));
        useDefaultAppIcon.setDisable(!Taskbar.getTaskbar().isSupported(Feature.ICON_IMAGE));

        Menu desktopIntegration = new Menu("Desktop");

        MenuItem setIconBadge = Configure("Set Badge", x -> Taskbar.getTaskbar().setIconBadge("1"), null);
        MenuItem removeIconBadge = Configure("Remove Badge", x -> Taskbar.getTaskbar().setIconBadge("1"), null);

        setIconBadge.setDisable(!Taskbar.getTaskbar().isSupported(Feature.ICON_BADGE_TEXT));
        removeIconBadge.setDisable(!Taskbar.getTaskbar().isSupported(Feature.ICON_BADGE_TEXT));


        MenuItem addProgress = Configure("Add Icon Progress", x -> Taskbar.getTaskbar().setProgressValue(currentIconProgress++), KeyCode.R);
        MenuItem clearProgress = Configure("Clear Icon Progress", x -> {
            currentIconProgress = -1;
            Taskbar.getTaskbar().setProgressValue(currentIconProgress++);
        }, null);
        addProgress.setDisable(!Taskbar.getTaskbar().isSupported(Feature.PROGRESS_VALUE));
        clearProgress.setDisable(!Taskbar.getTaskbar().isSupported(Feature.PROGRESS_VALUE));

        MenuItem requestUserAttention = Configure("Request User Attention (5s)", x -> requestUserAttention(), null);

        requestUserAttention.setDisable(!Taskbar.getTaskbar().isSupported(Feature.USER_ATTENTION));

        desktopIntegration.getItems().addAll(setIconBadge, removeIconBadge, addProgress, clearProgress, useCustomIcon, useDefaultAppIcon, requestUserAttention);

        return desktopIntegration;
    }

    private void requestUserAttention() {

        Task task = new Task<Void>() {

            @Override
            public Void call() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Taskbar.getTaskbar().requestUserAttention(true, true);
                return null;
            }
        };

        new Thread(task).start();
    }
}
