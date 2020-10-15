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
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class StylesheetApp extends ContentBox {

    public final static boolean SHOWING = false;
    public final static String TITLE = "FXML - Stylesheet";

    @Override
    public void index() {
        try {
            baseDemo();
        } catch (IOException ex) {
            Logger.getLogger(StylesheetApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void baseDemo() throws IOException {
        Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);

        FXMLLoader fl = new FXMLLoader();

        URL fxmlUrl = fl.getClassLoader().getResource("fxml/Stylesheet.fxml");
        fl.setLocation(fxmlUrl);

        URL cssUrl = getClass().getClassLoader().getResource("styles/stylesheet.css");
        getBaseScene().getStylesheets().add(cssUrl.toExternalForm());

        AnchorPane ap = (AnchorPane) fl.load();
        ap.setId("root");
        getChildren().add(ap);
    }

    public void setStyleDemo() {
        Button btn = new Button();
        btn.getStyleClass().add("newGroup");
    }
}
