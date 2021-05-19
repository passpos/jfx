/*
 * Copyright (C) 2020 realpai <paiap@outlook.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY;
 * without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jfx.core.app;

import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public abstract class AbstractSideBox extends AnchorPane {

    private Switcher switcher;
    private AbstractAppBox appBox;
    private ScrollPane scrollPane;
    private VBox btnBox;
    protected ButtonWrapper defaultBtn;

    /**
     * step-1
     */
    public AbstractSideBox() {
        btnBox = new VBox();
        scrollPane = new ScrollPane();
        scrollPane.setContent(btnBox);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        this.getChildren().add(scrollPane);
    }

    /**
     * 将演示App的Class对象，通过遍历绑定到按钮，同时添加到侧边栏；
     * 设置默认按钮和默认应用；
     * 最后，设置滚动栏。
     *
     * step-8 by Switcher-init()
     */
    public final void init() {
        initStyle();

        // App列表
        ArrayList<Class<? extends ContentBox>> appList = appBox.getAppList();

        // 创建App对应的按钮列表
        appList.forEach((appClass) -> {
            // 创建单个按钮
            ButtonWrapper btn = new ButtonWrapper(appBox, appClass);
            btn.init();
            btn.setMinWidth(240);
            if (btn.isShowing()) {
                this.defaultBtn = btn;
                ol("isShowing - " + btn.getTitle());
            }

            // 将按钮添加到边栏
            btnBox.getChildren().add(btn);

            // 设置按钮样式
            setButtonStyle(btn);
        });

        setDefaultBtn();  // step-21
    }

    private void initStyle() {
        this.setPrefWidth(350.0);
        //this.setPrefHeight(50);

        scrollPane.setPannable(true);
        scrollPane.prefWidthProperty().bind(this.widthProperty());
        this.prefHeightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                scrollPane.setPrefHeight(t1.doubleValue());
            }
        });

        btnBox.setFillWidth(true);
        btnBox.setPadding(new Insets(5.0));
        btnBox.setSpacing(5);
        btnBox.setAlignment(Pos.CENTER_LEFT);
        btnBox.setStyle("-fx-background-color:#778899");
    }

    public Switcher getSwitcher() {
        return switcher;
    }

    public void setSwitcher(Switcher switcher) {
        this.switcher = switcher;
    }

    public VBox getBtnBox() {
        return btnBox;
    }

    public AbstractAppBox getAppBox() {
        return appBox;
    }

    public void setAppBox(AbstractAppBox appBox) {
        this.appBox = appBox;
    }

    public ButtonWrapper getDefaultBtn() {
        return defaultBtn;
    }

    /**
     * 如果 defaultBtn 仍为空，则设置btnBox的第一个元素为默认显示的应用；
     *
     * step-21
     */
    private void setDefaultBtn() {
        if (defaultBtn == null) {
            this.defaultBtn = (ButtonWrapper) btnBox.getChildren().get(0);
        }
        if (btnBox.getChildren().isEmpty()) {
            ol("SideBox - btnBox：没有设置任何内容");
            return;
        }
        ContentBox.getPrimaryStage().setTitle(defaultBtn.getTitle());
        defaultBtn.setApp();    // step-25
        appBox.getChildren().clear();
        appBox.getChildren().add(defaultBtn.getApp());
    }

    public abstract void setButtonStyle(ButtonWrapper btn);

    public static <T> void ol(T arg) {
        System.out.println(arg);
    }
}
