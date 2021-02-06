/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.layout.pane;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import jfx.core.app.ContentBox;

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
        // setAnchorDemo();
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

        /* - AnchorPane.setXXXAnchor()管理布局时，相对于子组件的setLayout()方法
         * 优先生效；
         * - 当父组件为AnchorPane时，可以这样布局子组件。
         * - 下面设置子组件 btn 距离父组件的顶部与左侧分别为50、100；
         * - 如果 AnchorPane 自身尺寸较小，而设置的锚点位置超出了AnchorPane的默
         * 认范围，AnchorPane的尺寸会被撑开到适当大小，以正常包容子组件。如，下
         * 列的高度锚点（距AP上部 200 ）超出了AP默认设置的 100 ，实际显示时，AP
         * 的高度就会大于200（子组件上部距AP顶部的200，加上子组件本身的尺寸）；
         * -
         */
        AnchorPane.setTopAnchor(btn, 200.0);
        AnchorPane.setLeftAnchor(btn, 100.0);

        // AnchorPane中，子组件自身的布局设置无效；
        btn.setLayoutX(300);
        btn.setLayoutY(20);

        ap.getChildren().add(btn);
        getChildren().add(ap);

    }

    public void setAnchorDemo() {
        AnchorPane ap = new AnchorPane();
        ap.setStyle("-fx-background-color: #338833");
        ap.setPrefWidth(100);
        ap.setPrefHeight(100);
        AnchorPane.setTopAnchor(ap, 350.0);
        getChildren().add(ap);

        Button b = new Button("setLeftAnchor - 50");
        ap.getChildren().add(b);
        AnchorPane.setTopAnchor(b, 200.0);
        AnchorPane.setLeftAnchor(b, 100.0);

    }

}
