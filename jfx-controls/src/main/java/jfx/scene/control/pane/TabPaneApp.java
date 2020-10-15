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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class TabPaneApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Pane - TabPane 标签窗口";
    public TabPane tp;

    @Override
    public void index() {
        tp = new TabPane();     // 节点类型
        baseDemo();

        // 标签样式
        tabStyleDemo();

        // 标签事件
        eventDemo();
    }

    public void baseDemo() {
        Tab tab = new Tab("tab1");     // 直接继承Object
        HBox hb = new HBox();
        hb.getChildren().addAll(new Button("btn1"), new Button("btn2"));
        tab.setContent(hb);

        tp.getTabs().add(tab);

        tp.getSelectionModel().select(tab);    // 默认选中；
        tp.setSide(Side.TOP);                   // 设置标签位置（默认是在顶部）；
        // 设置标签图标保持水平方向；
        // 要使此设置起作用，要求图标加载和标签方向设置在窗体显示前完成；
        tp.setRotateGraphic(false);

        tp.setPrefWidth(700);
        tp.setPrefHeight(400);
        tp.setStyle("-fx-background-color:#999999");
        tp.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                ol("之前选中的标签是：" + t.getText());
                ol("现在选中的标签是：" + t1.getText());
            }

        });

        this.getChildren().add(tp);
    }

    public void tabStyleDemo() {
        Tab tab = new Tab();
        tab.setText("tab2");
        ImageView iv = new ImageView("file:D:\\Projects\\JavaFX\\fx-control\\src\\main\\java\\icon\\fav.jpg");
        iv.setFitWidth(15);
        iv.setFitHeight(15);
        tab.setGraphic(iv);
        // 标签不可关闭，关闭按钮也会消失；
        tab.setClosable(false);
        // 所有标签不可关闭；
        // tp.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        tp.getTabs().add(tab);
    }

    public void eventDemo() {

        Tab tab = new Tab("tab3");
        // 禁用标签；
//        tab3.setDisable(true);
        // 选中改变事件。由于涉及到两种状态（选中/未选中），所以（切入/切离tab3）都会运行下列事件处理；
        tab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                Tab tb = (Tab) t.getSource();
                ol("选中了" + tb.getText());
            }
        });
        tab.setOnClosed(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                ol("啊！你真的把我关闭了！");
            }
        });
        tab.setOnCloseRequest(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                ol("你竟然要关闭我？！哼！");
            }
        });
        tp.getTabs().addAll(tab);
    }
}
