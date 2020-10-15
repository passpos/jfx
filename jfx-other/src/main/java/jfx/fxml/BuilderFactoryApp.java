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
import jfx.fxml.builder.PersonBuilderFactory;
import jfx.fxml.builder.PersonBuilderMapFactory;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class BuilderFactoryApp extends ContentBox {

    public final static boolean SHOWING = false;
    public final static String TITLE = "FXML - BuilderFactory";

    @Override
    public void index() {
        try {
            baseDemo();
        } catch (IOException ex) {
            Logger.getLogger(BuilderFactoryApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void baseDemo() throws IOException {
        FXMLLoader fl = new FXMLLoader();
        URL resource = fl.getClassLoader().getResource("fxml/BuilderFactoryDemo.fxml");
        fl.setLocation(resource);
        fl.setBuilderFactory(new PersonBuilderFactory());
        fl.setBuilderFactory(new PersonBuilderMapFactory());

        AnchorPane ap = (AnchorPane) fl.load();
//        Person person = (Person) fl.load();
//        ob(person.getName() + " - " + person.getAge());
    }
}
