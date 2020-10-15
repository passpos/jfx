/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.transform;

import javafx.scene.control.Button;
import javafx.scene.transform.Shear;
import jfx.utils.app.ContentBox;

/**
 * 剪切变换
 * @author realpai <paiap@outlook.com>
 */
public class ShearApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Transform - Shear 剪切";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        Shear sh = new Shear(0.3, 0.3, 0, 0);

        Button btn = new Button("按钮");
        btn.setPrefWidth(40.0);
        btn.setPrefHeight(40.0);
        btn.getTransforms().add(sh);

        getChildren().add(btn);
        setLeftAnchor(btn, 100.0);
        setTopAnchor(btn, 100.0);
    }
}
