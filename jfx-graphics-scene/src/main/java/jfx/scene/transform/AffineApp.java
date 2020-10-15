/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.transform;

import javafx.scene.control.Button;
import static javafx.scene.layout.AnchorPane.setLeftAnchor;
import static javafx.scene.layout.AnchorPane.setTopAnchor;
import javafx.scene.transform.Affine;
import jfx.utils.app.ContentBox;

/**
 * 仿射转换
 * @author realpai <paiap@outlook.com>
 */
public class AffineApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Transform - Affine 仿射";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        Affine af = new Affine();

        Button btn = new Button("按钮");
        btn.setPrefWidth(40.0);
        btn.setPrefHeight(40.0);
        btn.getTransforms().add(af);

        getChildren().add(btn);
        setLeftAnchor(btn, 100.0);
        setTopAnchor(btn, 100.0);

    }
}
