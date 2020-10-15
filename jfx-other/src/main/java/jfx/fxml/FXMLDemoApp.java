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
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class FXMLDemoApp extends ContentBox {

    public final static boolean SHOWING = false;
    public final static String TITLE = "FXML - Demo";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        FXMLLoader fl = new FXMLLoader();

//       URL url = new URL("file:src/main/resources/fxml/First.fxml");
//       URL url = getClass().getClassLoader().getResource("fxml/First.fxml");
        URL url = fl.getClassLoader().getResource("fxml/First.fxml");

        // AnchorPane ap1 = (AnchorPane) fl.load(new FileInputStream(new File("src/main/java/resources/fxml/First.fxml")));
        fl.setLocation(url);
        AnchorPane ap2 = null;
        try {
            ap2 = (AnchorPane) fl.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDemoApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        Node lp = ap2.lookup("#btn1");
        ol(lp.getClass().toString());

        fl.getController();

        getChildren().add(ap2);
    }
}
