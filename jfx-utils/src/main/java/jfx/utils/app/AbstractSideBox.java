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
package jfx.utils.app;

import java.util.ArrayList;
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
    private ScrollPane btnPane;
    private VBox btnBox;
    protected ButtonWrapper defaultBtn;
    private double width = 250.0;

    /**
     * step-1
     */
    public AbstractSideBox() {
        btnPane = new ScrollPane();
        btnBox = new VBox();
        btnPane.setContent(btnBox);
    }

    /**
     * 将演示App的Class对象，通过遍历绑定到按钮，同时添加到侧边栏；
     * 设置默认按钮和默认应用；
     * 最后，设置滚动栏。
     *
     * step-8 by Switcher-init()
     */
    public final void init() {
        this.getChildren().add(btnPane);
        initStyle();

        ArrayList<Class<? extends ContentBox>> appList = appBox.getAppList();
        appList.forEach((appClass) -> {
            // 创建按钮
            ButtonWrapper btn = new ButtonWrapper(appBox, appClass);
            btn.init();
            if (btn.isShowing()) {
                this.defaultBtn = btn;
                ol("isShowing - " + btn.getTitle());
            }

            // 将按钮添加到边栏
            btnBox.getChildren().add(btn);

            // 设置按钮样式
            setButtonStyle(btn);
        });

        setDefaultBtn();
        setFinalDefault();  // step-21
    }

    private void initStyle() {
        this.setPrefWidth(width);
        this.setMaxWidth(width);

        // 设置侧边栏背景色
        this.setStyle("-fx-background-color:#778899");

        btnBox.setPrefWidth(width);
        btnBox.setMaxWidth(250);
        btnBox.setMinWidth(250);
        btnBox.setPadding(new Insets(5.0));
        btnBox.setSpacing(5);
        btnBox.setAlignment(Pos.TOP_CENTER);
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

    public abstract void setDefaultBtn();

    public abstract void setButtonStyle(ButtonWrapper btn);

    /**
     * 在用户实现 setDefaultBtn() 方法后，如果 defaultBtn 仍为空，则设置btnBox的
     * 第一个元素为默认显示的应用；
     *
     * step-21
     */
    private void setFinalDefault() {
        if (defaultBtn == null) {
            if (btnBox.getChildren().isEmpty()) {
                ol("SideBox - btnBox：没有设置任何内容");
                return;
            }
            defaultBtn = (ButtonWrapper) btnBox.getChildren().get(0);
        }
        defaultBtn.setApp();    // step-25
        appBox.getChildren().clear();
        appBox.getChildren().add(defaultBtn.getApp());
    }

    public double getSideWidth() {
        return width;
    }

    public static <T> void ol(T arg) {
        System.out.println(arg);
    }
}
