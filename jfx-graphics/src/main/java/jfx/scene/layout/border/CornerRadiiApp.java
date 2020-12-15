/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.layout.border;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import jfx.core.app.ContentBox;

/**
 * CornerRadii extends Object
 *
 * 定义BorderStroke的四个角中的每个角的半径；
 * CornerRadii类是不可变的，因此可以在多个BorderStrokes上重用；
 * 此类定义了8个不同的值，分别对应于四个四分之一椭圆的水平和垂直分量，这些值又定
 * 义了BorderStroke角的曲率。
 *
 * 我们又是不想按照固定值设置圆角，而是想根据百分比设定，构造方法中有一个
 * asPercent 布尔参数，用于决定是否将输入的半径数值理解为百分比数值（0-100）；
 *
 * @author realpai <paiap@outlook.com>
 */
public class CornerRadiiApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Border - CornerRadii";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HBox h1 = new HBox();
        CornerRadii cr1 = CornerRadii.EMPTY;
        setVBox(h1, cr1);

        HBox h2 = new HBox();
        CornerRadii cr2 = new CornerRadii(5.0);
        setVBox(h2, cr2);
        setLeftAnchor(h2, 100.0);

        HBox h3 = new HBox();
        CornerRadii cr3 = new CornerRadii(5, true);
        setVBox(h3, cr3);
        setLeftAnchor(h3, 200.0);

        HBox h4 = new HBox();
        CornerRadii cr4 = new CornerRadii(5, false);
        setVBox(h4, cr4);
        setLeftAnchor(h4, 300.0);

        // 自左上角开始，顺时针设置；
        HBox h5 = new HBox();
        CornerRadii cr5 = new CornerRadii(15, 15, 3, 3, true);
        setVBox(h5, cr5);
        setTopAnchor(h5, 100.0);

        HBox h6 = new HBox();
        CornerRadii cr6 = new CornerRadii(15, 15, 3, 3, false);
        setVBox(h6, cr6);
        setLeftAnchor(h6, 100.0);
        setTopAnchor(h6, 100.0);

        HBox h7 = new HBox();
        CornerRadii cr7 = new CornerRadii(15, 3, 15, 3, true);
        setVBox(h7, cr7);
        setLeftAnchor(h7, 200.0);
        setTopAnchor(h7, 100.0);

        HBox h8 = new HBox();
        CornerRadii cr8 = new CornerRadii(15, 3, 15, 3, false);
        setVBox(h8, cr8);
        setLeftAnchor(h8, 300.0);
        setTopAnchor(h8, 100.0);
    }

    private void setVBox(HBox h, CornerRadii cr) {
        h.setPrefWidth(90.0);
        h.setPrefHeight(90.0);
        h.setStyle("-fx-background-color:#55aa55");

        BorderStroke bs = new BorderStroke(
                Paint.valueOf("#ee5533"),
                BorderStrokeStyle.SOLID,
                cr,
                new BorderWidths(5.0)
        );
        Border b = new Border(bs);

        h.setBorder(b);
        getChildren().add(h);
    }
}
