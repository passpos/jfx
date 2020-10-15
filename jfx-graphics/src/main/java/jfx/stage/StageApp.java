/*
 * Copyright (C) 2020 realpai <paiap@outlook.com>
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
package jfx.stage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class StageApp extends ContentBox {

    public static final boolean SHOWING = true;
    public static final String TITLE = "Stage - Stage";
    public Stage stage;

    @Override
    public void index() {
        this.stage = new Stage();
        baseDemo();
        listenerDemo();
        stageControlDemo();
    }

    public void baseDemo() {
        // 设置窗口标题：
        stage.setTitle(Thread.currentThread().getName());

        // 设置程序装饰图标：
        stage.getIcons().add(new Image("file:///icon/fav.jpg"));

        // 设置窗口大小
        stage.setWidth(700);
        stage.setHeight(400);

        // 设置窗口不透明度：
        stage.setOpacity(0.5);

        // 设置窗口置顶：
        stage.setAlwaysOnTop(true);

        // 设置窗口在屏幕的固定位置（从左上角开始计算）
        // 设置后，窗口的默认大小（如果没有设置）会被置为0；
        // stage.setX(100);
        // stage.setY(100);

        // 以最小化方式运行：
        stage.setIconified(true);

        // 以最大化窗口运行：
        // stage.setMaximized(true);

        // 设置全屏，这里不会有效果，需要设置Scene：
        // stage.setFullScreen(true);

        // 指定窗口要使用的场景：
        stage.setScene(new Scene(new Group()));

        // 设置固定大小：
        stage.setResizable(true);
    }

    public void listenerDemo() {
        // 监听窗口的宽度属性变化
        stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                ol("当前宽度：" + t1.intValue());
            }
        });

        // 监听窗口的高度属性变化
        stage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                ol("当前高度：" + t1.intValue());
            }
        });
    }

    public void stageControlDemo() {
        Button b1 = new Button("打开");
        Button b2 = new Button("关闭");
        setLeftAnchor(b2, 50.0);
        getChildren().addAll(b1, b2);

        // 显示窗口;
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                stage.show();
            }
        });
        // 关闭窗口：
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                stage.close();
            }
        });
    }
}
