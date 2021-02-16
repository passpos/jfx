/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class GroupApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Scene - Group 组";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        Group g = new Group();
        for (int i = 0; i < 5; i++) {
            Rectangle r = new Rectangle();
            r.setY(i * 20);
            r.setWidth(100);
            r.setHeight(10);
            r.setFill(Color.RED);
            g.getChildren().add(r);
        }

        getChildren().add(g);
    }
}
