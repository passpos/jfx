/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.layout.pane;

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

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        Button btn1 = new Button("btn1");
        Button btn2 = new Button("btn2");

        AnchorPane ap = new AnchorPane();
        ap.setStyle("-fx-background-color: #333333");
        ap.setPrefHeight(200);
        ap.setPrefWidth(400);
        ap.getChildren().add(btn1);

        HBox hb1 = new HBox();
        hb1.setPrefHeight(100);
        hb1.setPrefWidth(400);
        hb1.setStyle("-fx-background-color: #999999");

        HBox hb2 = new HBox();
        hb2.setPrefHeight(100);
        hb2.setPrefWidth(400);
        hb2.setStyle("-fx-background-color: #999999");

        VBox vb1 = new VBox();
        vb1.getChildren().addAll(btn2);
        vb1.setPrefHeight(200);
        vb1.setPrefWidth(100);
        vb1.setStyle("-fx-background-color: #225500");

        VBox vb2 = new VBox();
        vb2.setPrefHeight(200);
        vb2.setPrefWidth(100);
        vb2.setStyle("-fx-background-color: #007755");

        // 最终显示的是 上-中-下 结构的视图布局；
        // 如果要显示类似 左-中-右 的布局,需要
        BorderPane bp = new BorderPane();
        bp.setTop(hb1);
        bp.setLeft(vb1);
        bp.setRight(vb2);
        bp.setBottom(hb2);
        bp.setCenter(ap);

        this.getChildren().add(bp);
    }
}
