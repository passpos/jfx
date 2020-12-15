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
 * 定义四个方向（顶部，右侧，底部和左侧）的宽度；
 * 每个宽度均定义为非负值；
 * 此值可以解释为文字值，也可以解释为Region宽度或高度的百分比，具体取决于
 * topAsPercentage，rightAsPercentage，bottomAsPercentage，leftAsPercentage的值；
 * 顶部，右侧，底部和左侧唯一允许的负值是AUTO；
 *
 * @author realpai <paiap@outlook.com>
 */
public class BorderWidthsApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Border - BorderWidths";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HBox h1 = new HBox();
        BorderWidths bw1 = BorderWidths.EMPTY;
        setVBox(h1, bw1);

        HBox h2 = new HBox();
        BorderWidths bw2 = BorderWidths.DEFAULT;
        setVBox(h2, bw2);
        setLeftAnchor(h2, 100.0);

        HBox h3 = new HBox();
        BorderWidths bw3 = BorderWidths.FULL;
        setVBox(h3, bw3);
        setLeftAnchor(h3, 200.0);

        HBox h4 = new HBox();
        BorderWidths bw4 = new BorderWidths(5.0);
        setVBox(h4, bw4);
        setLeftAnchor(h4, 300.0);

        // 自上开始，顺时针设置；
        HBox h5 = new HBox();
        BorderWidths bw5 = new BorderWidths(15, 15, 3, 3);
        setVBox(h5, bw5);
        setTopAnchor(h5, 100.0);

        HBox h6 = new HBox();
        BorderWidths bw6 = new BorderWidths(15, 15, 3, 3, false, false, false, false);
        setVBox(h6, bw6);
        setLeftAnchor(h6, 100.0);
        setTopAnchor(h6, 100.0);

        HBox h7 = new HBox();
        BorderWidths bw7 = new BorderWidths(15, 15, 3, 3, true, true, true, true);
        setVBox(h7, bw7);
        setLeftAnchor(h7, 200.0);
        setTopAnchor(h7, 100.0);
    }

    private void setVBox(HBox h, BorderWidths bw) {
        h.setPrefWidth(40.0);
        h.setPrefHeight(40.0);
        h.setStyle("-fx-background-color:#55aa55");

        BorderStroke bs = new BorderStroke(
                Paint.valueOf("#ee5533"),
                BorderStrokeStyle.SOLID,
                new CornerRadii(5.0),
                bw
        );
        Border b = new Border(bs);

        h.setBorder(b);
        getChildren().add(h);
    }
}
