/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.features.other;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.control.Button;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ReflectionApp extends ContentBox {

    public final static boolean SHOWING = false;
    public final static String TITLE = "Reflection";

    @Override
    public void index() {
        try {
            baseDemo();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ReflectionApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void baseDemo() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Node child = new Button("按钮1");

        String className = child.getClass().getName();
        Class<?> childClass = Class.forName(className);
        Node child2 = (Node) childClass.getConstructor(String.class).newInstance("按钮2");

        setLeftAnchor(child2, 50.0);
        getChildren().addAll(child, child2);
    }
}
