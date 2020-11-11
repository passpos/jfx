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
package jfx.core.app;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ButtonWrapper extends Button {

    private AbstractAppBox appBox;
    private Class<? extends ContentBox> appClass;
    private ContentBox app;
    private String title;
    private boolean showing;

    public ButtonWrapper() {
    }

    public ButtonWrapper(AbstractAppBox appBox, Class<? extends ContentBox> appClass) {
        this.appBox = appBox;
        this.appClass = appClass;
    }

    /**
     * step-15
     */
    public void init() {
        setTitle();
        setShowing();
        setAction();
    }

    public Class<? extends ContentBox> getAppClass() {
        return appClass;
    }

    public void setAppClass(Class<? extends ContentBox> appClass) {
        this.appClass = appClass;
    }

    /**
     * Get the value of app
     *
     * @return the value of app
     */
    public ContentBox getApp() {
        return app;
    }

    /**
     * Set the value of app（获取App实例）
     * step-15（遍历时/AbsSideBox）
     * step-25（设置默认时/AbsSideBox）
     */
    public void setApp() {
        if (app == null) {
            try {
                app = appClass.getConstructor().newInstance();
            } catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException ex) {
                ex.printStackTrace();
            }
        }

        if (app == null) {
            ol("App初始化失败");
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle() {
        try {
            // 获取App字段属性，设置按钮标题.
            Field titleField = appClass.getDeclaredField("TITLE");
            titleField.setAccessible​(true);
            title = titleField.get(appClass).toString();
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            System.out.println(ex.getMessage());
        }
        if (title == null) {
            title = "空";
        }
        this.setText(title);
    }

    /**
     * 在遍历所有的应用时，若应用的“SHOWING”字段为 TRUE，将设置默认显示的应用
     * 为该应用；
     * @return boolean 是否是默认展示的按钮和应用.
     */
    public boolean isShowing() {
        return showing;
    }

    /**
     * 设置默认显示状态到按钮
     */
    public void setShowing() {
        try {
            Field showingField = appClass.getDeclaredField("SHOWING");
            showingField.setAccessible(true);
            this.showing = Boolean.parseBoolean(showingField.get(appClass).toString());
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * step-15
     *
     * 下面的代码除了用于初始化，还会在按钮切换时，多次执行。
     * 所以，注意这两种逻辑流程的区别。
     */
    public void setAction() {
        // 下面的代码除了在初始化时执行，在按钮被点击时，会反复执行；
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                setApp();
                app.setConsoleStyle();

                appBox.getChildren().clear();
                appBox.getChildren().add(app);

                ContentBox.getPrimaryStage().setTitle(title);
            }
        });

        // 下面的代码只会在初始化时，执行一次
    }

    @Override
    public String toString() {
        return "ButtonWrapper{\n"
                + "\tappClass=" + appClass
                + ", \n\tapp=" + app
                + ", \n\ttitle=" + title
                + ", \n\tshowing=" + showing
                + "\n}";
    }

    public static <T> void ol(T arg) {
        System.out.println(arg);
    }
}
