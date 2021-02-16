/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.geometry;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import jfx.core.app.ContentBox;

/**
 * 矩形区域的4条边的一组内部偏移
 *
 * 用例：
 * - 布局组件的内边距：setPadding(new Insets(...))；
 * - 布局组件的某个子组件的外边距（静态方法）：setMargin(btn, new Insets(...))；
 * @author passpos <paiap@outlook.com>
 */
public class InsetsApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Geometry - Insets";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HBox hBox = new HBox();
        Button b1 = new Button("按钮");
        hBox.getChildren().add(b1);

        // hBox.setPadding(new Insets(3));
        hBox.setPadding(new Insets(3, 5, 4, 7));
        HBox.setMargin(b1, new Insets(3.0));
    }

}
