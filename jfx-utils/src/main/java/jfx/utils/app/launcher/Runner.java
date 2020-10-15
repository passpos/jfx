/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.utils.app.launcher;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class Runner extends Application {

    private static AnchorPane app;

    public static void main(String[] args) {
        Application.launch(Runner.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(app));
        stage.show();
    }

    public static AnchorPane setApp(AnchorPane app) {
        return app;
    }
}
