/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.features.other;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import jfx.core.app.ContentBox;

/**
 * 这里的克隆都是浅克隆
 * @author realpai <paiap@outlook.com>
 */
public class CloneApp extends ContentBox {

    public final static boolean SHOWING = true;
    public final static String TITLE = "Clone";

    @Override
    public void index() {
        try {
            baseDemo();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(CloneApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void baseDemo() throws CloneNotSupportedException {
        MyButton mb = new MyButton("按钮1");
        ol(mb);
        mb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ol(mb);
            }
        });

        MyButton nb = (MyButton) mb.clone();
        nb.setText("按钮2");
        ol(nb);

        HBox hBox = new HBox();
        hBox.getChildren().add(nb);
        hBox.setPrefWidth(100);
        hBox.setPrefHeight(100);
        setTopAnchor(hBox, 100.0);
        setLeftAnchor(hBox, 100.0);
        hBox.setStyle("-fx-background-color:#559955");

        getChildren().addAll(mb, hBox);
    }
}

class MyButton extends Button implements Cloneable {

    public MyButton() {
    }

    public MyButton(String string) {
        super(string);
    }

    public MyButton(String string, Node node) {
        super(string, node);
    }

    @Override
    public Node clone() throws CloneNotSupportedException {
        return (Node) super.clone();
    }

}
