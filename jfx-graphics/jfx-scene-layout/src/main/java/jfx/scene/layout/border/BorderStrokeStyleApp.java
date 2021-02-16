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
 * BorderStrokeStyle​(
 * StrokeType type,
 * StrokeLineJoin lineJoin,
 * StrokeLineCap lineCap,
 * double miterLimit, double dashOffset,
 * List<Double> dashArray)
 *
 * StrokeType、StrokeLineJoin、StrokeLineCap位于 javafx.scene.shape 包内，用于绘
 * 制线段、线段相交、线段的端点样式；
 *
 * BorderStrokeStyle 本身包含了数个枚举字段类型，用户一般不必构建 BSS 实例；
 *
 * Fields
 * - BorderStrokeStyle.NONE
 * - BorderStrokeStyle.DASHED
 * - BorderStrokeStyle.DOTTED
 * - BorderStrokeStyle.SOLID
 *
 * @author realpai <paiap@outlook.com>
 */
public class BorderStrokeStyleApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Border - BorderStrokeStyle";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HBox h1 = new HBox();
        setVBox(h1, BorderStrokeStyle.NONE);

        HBox h2 = new HBox();
        setVBox(h2, BorderStrokeStyle.DASHED);
        setLeftAnchor(h2, 100.0);

        HBox h3 = new HBox();
        setVBox(h3, BorderStrokeStyle.DOTTED);
        setLeftAnchor(h3, 200.0);

        HBox h4 = new HBox();
        setVBox(h4, BorderStrokeStyle.SOLID);
        setLeftAnchor(h4, 300.0);
    }

    private void setVBox(HBox h, BorderStrokeStyle bss) {
        h.setPrefWidth(90.0);
        h.setPrefHeight(90.0);
        h.setStyle("-fx-background-color:#55aa55");

        BorderStroke bs = new BorderStroke(
                Paint.valueOf("#ee5533"),
                bss,
                new CornerRadii(0),
                new BorderWidths(5.0)
        );
        Border b = new Border(bs);

        h.setBorder(b);
        getChildren().add(h);
    }
}
