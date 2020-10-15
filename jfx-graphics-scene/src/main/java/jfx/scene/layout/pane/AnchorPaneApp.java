/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.layout.pane;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class AnchorPaneApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Layout - AnchorPane";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        AnchorPane ap = new AnchorPane();
        ap.setStyle("-fx-background-color: #333333");
        ap.setPrefHeight(100);
        ap.setPrefWidth(700);

        // 添加按钮：
        Button btn = new Button("确认");
        btn.setPrefWidth(50);
        btn.setPrefHeight(20);

        // 这里的布局设置无效；
        btn.setLayoutX(300);
        btn.setLayoutY(20);

        // AnchorPane管理布局优先生效；
        AnchorPane.setTopAnchor(btn, 50.0);
        AnchorPane.setLeftAnchor(btn, 100.0);

        ap.getChildren().add(btn);
        this.getChildren().add(ap);

    }

}
