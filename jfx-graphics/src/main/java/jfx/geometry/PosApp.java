/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.geometry;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import jfx.core.app.ContentBox;

/**
 * 用于描述垂直和水平定位和对齐的一组值。
 *
 * 用例：
 * - 设置布局组件的子组件的对齐方式
 * @author passpos <paiap@outlook.com>
 */
public class PosApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Geometry - Enum Pos";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HBox hBox = new HBox();
        Button b1 = new Button("按钮");
        hBox.getChildren().add(b1);

        hBox.setAlignment(Pos.CENTER);
    }
}
