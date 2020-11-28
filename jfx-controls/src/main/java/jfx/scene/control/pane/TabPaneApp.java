/*
 * Copyright (C) 2019 realpai <paiap@outlook.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jfx.scene.control.pane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import jfx.core.app.ContentBox;

/**
 * TabPane是Control类型
 * Tab则是直接继承的Object，必须装入TabPane中；
 * @author realpai <paiap@outlook.com>
 */
public class TabPaneApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Pane - TabPane 标签窗口";
    private TabPane tp;

    @Override
    public void index() {
        baseDemo();
        sideDemo();
        selectDemo();
    }

    public void baseDemo() {
        // Tab Content
        HBox hb = new HBox();
        hb.getChildren().addAll(new Button("btn1"), new Button("btn2"));

        // Tab
        Tab tab = new Tab("tab1");
        tab.setContent(hb);

        // TabPane
        tp = new TabPane();
        tp.getTabs().add(tab);
        getChildren().add(tp);

        // 默认选中；
        tp.getSelectionModel().select(tab);

        // 设置标签位置（默认是在顶部）；
        tp.setSide(Side.TOP);

        /* 设置标签图标保持水平方向；
         * 要使此设置起作用，要求图标加载和标签方向设置在窗体显示前完成；
         */
        tp.setRotateGraphic(false);

        tp.setPrefWidth(400);
        tp.setPrefHeight(200);
        tp.setStyle("-fx-background-color:#999999");

    }

    public void sideDemo() {
        TabPane tabPane = new TabPane();

        tabPane.setPrefWidth(400);
        tabPane.setPrefHeight(200);

        tabPane.setSide(Side.LEFT);

        getChildren().add(tabPane);
        setTopAnchor(tabPane, 210.0);
    }

    /**
     * TabPane上通常有多个Tab，某个Tab可以被设置为选中；
     * 也可以设置选中状态监听；
     */
    public void selectDemo() {
        tp.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                ol("之前选中的标签是：" + t.getText());
                ol("现在选中的标签是：" + t1.getText());
            }

        });
    }

}
