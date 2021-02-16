/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.layout.pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class BorderPaneApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Layout - BorderPane";
    public BorderPane bp;
    public HBox top;
    public HBox bottom;
    public VBox left;
    public Button right;
    public AnchorPane center;

    @Override
    public void index() {
        bp = new BorderPane();
        baseDemo();
        setBaseStyle();
    }

    public void baseDemo() {
        Button btn1 = new Button("btn1");
        Button btn2 = new Button("btn2");

        top = new HBox();
        top.setPrefHeight(100);
        top.setPrefWidth(400);
        top.setStyle("-fx-background-color: #999999");

        bottom = new HBox();
        bottom.setPrefHeight(100);
        bottom.setPrefWidth(400);
        bottom.setStyle("-fx-background-color: #999999");

        left = new VBox();
        left.getChildren().addAll(btn2);
        left.setPrefHeight(200);
        left.setPrefWidth(100);
        left.setStyle("-fx-background-color: #225500");

        right = new Button("右居中对齐");
        right.setStyle("-fx-background-color: #007755");

        center = new AnchorPane();
        center.setStyle("-fx-background-color: #333333");
        center.setPrefHeight(200);
        center.setPrefWidth(400);
        center.getChildren().add(btn1);

        /*
         * 最终显示的是 上-中-下 结构的视图布局；
         * 如果要显示类似 左-中-右 的布局,需要将上、下两部分留空。此时，只剩下三
         * 个部分；
         *
         */
        bp.setTop(top);
        bp.setLeft(left);
        bp.setRight(right);
        bp.setBottom(bottom);
        bp.setCenter(center);

        getChildren().add(bp);
    }

    public void setBaseStyle() {
        BorderPane.setAlignment(right, Pos.CENTER_RIGHT);
        BorderPane.setMargin(center, new Insets(5.0));
    }
}
