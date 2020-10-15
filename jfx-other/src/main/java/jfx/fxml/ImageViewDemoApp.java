/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jfx.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ImageViewDemoApp extends ContentBox {

    public final static boolean SHOWING = false;
    public final static String TITLE = "FXML - Demo ImageView";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        FXMLLoader fl = new FXMLLoader();
        URL url = fl.getClassLoader().getResource("fxml/ImageViewDemo.fxml");
        fl.setLocation(url);
        AnchorPane ap = null;
        try {
            ap = (AnchorPane) fl.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDemoApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        getChildren().add(ap);
    }
}
