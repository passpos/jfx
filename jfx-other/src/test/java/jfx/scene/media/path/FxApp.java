/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdm.samples.fx.platform;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfx.scene.media.path.PathDemo;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class FxApp extends Application {

    private static Scene scene;
    private static Parent root;

    @Override
    public void start(Stage stage) throws Exception {
        root = new PathDemo();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(800);
        stage.setHeight(600);
        stage.show();
        PathDemo pd = (PathDemo) root;
        pd.index();
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Scene scene) {
        FxApp.scene = scene;
    }

    public static Parent getRoot() {
        return root;
    }

    public static void setRoot(Parent root) {
        if (root != null) {
            FxApp.root = root;
        } else {
            System.out.println("未设置根节点！");
            Platform.exit();
        }
    }
}
