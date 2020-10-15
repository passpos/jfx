/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class LocalizationApp extends ContentBox {

    public final static boolean SHOWING = false;
    public final static String TITLE = "FXML - Localization";

    @Override
    public void index() {
        try {
            baseDemo();
        } catch (IOException ex) {
            Logger.getLogger(LocalizationApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void baseDemo() throws IOException {
        Locale dl = Locale.getDefault();
        ol(dl.toString());

        String country = dl.getCountry();
        ol(country);

        FXMLLoader fl = new FXMLLoader();

        URL url = fl.getClassLoader().getResource("fxml/Localization.fxml");
        fl.setLocation(url);

        ResourceBundle rb = ResourceBundle.getBundle("langs/lang");
        fl.setResources(rb);

        AnchorPane ap = (AnchorPane) fl.load();
        getChildren().add(ap);

    }

}
