/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.demo.tray;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class Launcher extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        /*
         * 退出时，当当前窗口为最后一个（唯一的）窗口时;
         * 1. true，则关闭这个窗口时，默认退出；
         * 2. false，则关闭这个窗口时，窗体会被关闭，但程序任然运行；
         */
        Platform.setImplicitExit(false);

        stage.setTitle("图片查看器");
        stage.setWidth(800);
        stage.setHeight(600);

        App app = new App(stage);

        Scene scene = new Scene(app.getRoot());
        stage.setScene(scene);
        stage.show();
    }

}
