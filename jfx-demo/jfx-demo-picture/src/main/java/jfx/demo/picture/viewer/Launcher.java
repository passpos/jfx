/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.demo.picture.viewer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class Launcher extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("图片查看器");
        stage.setWidth(800);
        stage.setHeight(600);

        App app = new App();

        Scene scene = new Scene(app.getRoot());
        stage.setScene(scene);
        stage.show();
    }

}
