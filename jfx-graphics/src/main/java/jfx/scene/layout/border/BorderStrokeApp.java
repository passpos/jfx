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
 * BorderStroke extends Object
 *
 * 定义用于设置Region样式的Border上的笔划/stroke。笔划是一种基于矢量的渲染，它勾
 * 勒出边界区域。它可以从区域的边缘插入（或开始），并且在计算区域的插入（用于定
 * 义内容区域）时考虑笔划的值。当使用任何边界图像时，不使用笔划视觉效果。
 *
 * 当将其应用于具有定义形状的区域时，将使用顶部的边框宽度和描边信息，而忽略其他
 * 属性。
 *
 * Fields
 * BorderWidths BorderStroke.DEFAULT_WIDTHS
 * BorderWidths BorderStroke.MEDIUM
 * BorderWidths BorderStroke.THICK
 * BorderWidths BorderStroke.THIN
 *
 * @author realpai <paiap@outlook.com>
 */
public class BorderStrokeApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Border - BorderStroke";
    private HBox hBox;

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        hBox = new HBox();
        hBox.setPrefWidth(200.0);
        hBox.setPrefHeight(250.0);
        hBox.setStyle("-fx-background-color:#556699");
        getChildren().add(hBox);

        BorderStroke bs = new BorderStroke(
                Paint.valueOf("#ff0000"),
                BorderStrokeStyle.SOLID,
                new CornerRadii(0),
                new BorderWidths(3.0)
        );
        Border b = new Border(bs);

        hBox.setBorder(b);
    }

    /**
     * BorderStroke​的构建至少需要4个参数，至多需要5个参数，分别是：
     * - 颜色，Paint；
     * - 样式，BorderStrokeStyle；
     * - 圆角，CornerRadii；
     * - 宽度，BorderWidths；
     * - Insets（可选），矩形区域的4条边的一组内部偏移；
     *
     * BorderStroke​(
     * Paint stroke, BorderStrokeStyle style,
     * CornerRadii radii, BorderWidths widths);
     *
     * BorderStroke​(
     * Paint stroke, BorderStrokeStyle style,
     * CornerRadii radii, BorderWidths widths,
     * Insets insets) ;
     *
     * BorderStroke​(
     * Paint topStroke, Paint rightStroke, Paint bottomStroke, Paint leftStroke,
     * BorderStrokeStyle topStyle, BorderStrokeStyle rightStyle,
     * BorderStrokeStyle bottomStyle, BorderStrokeStyle leftStyle,
     * CornerRadii radii, BorderWidths widths, Insets insets);
     */
    public void constructDemo() {
    }
}
