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
package jfx.utils.app;

import java.net.URL;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public abstract class ContentBox extends AnchorPane {

    private static Application application;
    private static Stage primaryStage;
    private static Switcher switcher;
    private static Scene baseScene;
    private static HBox root;
    private static AbstractAppBox appBox;
    private static AbstractSideBox sideBox;
//    public
    private Console console;

    public ContentBox() {
        start();        // step-6
    }

    /**
     * 子应用的内容
     */
    private void start() {
        index();
        initStyle();
    }

    public static Application getApplication() {
        return application;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static Switcher getSwitcher() {
        return switcher;
    }

    public static Scene getBaseScene() {
        return baseScene;
    }

    public static HBox getRoot() {
        return root;
    }

    public static AbstractAppBox getAppBox() {
        return appBox;
    }

    public static AbstractSideBox getSideBox() {
        return sideBox;
    }

    public Console getConsole() {
        return console;
    }

    public final void setConsole() {
        if (console == null) {
            this.console = new Console();
            this.getChildren().add(console);
        }

        AnchorPane.setTopAnchor(getConsole(), 200.0);
        getConsole().setDefaultStyle();
    }

    /**
     * 设置控制台的自定义样式。
     *
     * 用户可以在子类中重写此方法，用于实现特定的控制台样式；
     */
    public void setConsoleStyle() {
        setConsole();
    }

    /**
     * step-6
     * @param switcher
     */
    public static void init(Switcher switcher) {
        ContentBox.switcher = switcher;
        application = switcher.getLauncher();
        primaryStage = switcher.getPrimaryStage();
        baseScene = switcher.getBaseScene();
        root = switcher.getRoot();
        appBox = switcher.getAppBox();
        sideBox = switcher.getSideBox();
    }

    private void initStyle() {
        this.setMinWidth(50);
        this.setMinHeight(50);
        this.setPrefWidth(appBox.getWidth() - 2);
        this.setPrefHeight(appBox.getHeight() - 2);
        this.setStyle("-fx-background-color:#444039");

        this.prefWidthProperty().bind(appBox.widthProperty().subtract(3));
        this.prefHeightProperty().bind(appBox.heightProperty().subtract(3));
    }

    /**
     * 打印内容到控制台（不换行）
     * @param <T> 输出内容
     * @param arg 要打印的内容
     */
    public static <T> void o(T arg) {
        System.out.print(arg);
    }

    /**
     * 打印内容到控制台（自动换行）
     * @param <T> 输出内容
     * @param arg 要打印的内容
     */
    public static <T> void ol(T arg) {
        System.out.println(arg);
    }

    /**
     * 使用默认的控制台样式，将内容输出到界面中；
     * @param <T> 输出字符串
     * @param arg
     */
    public <T> void ob(T arg) {
        setConsole();
        Button btn = new Button(arg.toString());
        btn.setPrefWidth(100);
        setObStyle(btn);
        console.getChildren().add(btn);
    }

    /**
     * 使用默认的控制台样式，将内容输出到界面中。用户可以自定义按钮的宽度；
     * @param <T>   输出字符串
     * @param width 按钮宽度
     * @param arg   打印参数
     */
    public <T> void ob(T arg, double width) {
        setConsole();
        Button btn = new Button(arg.toString());
        btn.setPrefWidth(width);
        setObStyle(btn);
        console.getChildren().add(btn);
    }

    public URL loadResource(String path) {
        URL resource = getClass().getClassLoader().getResource(path);
        return resource;
    }

    private void setObStyle(Button btn) {
        btn.setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * 设置子应用的内容
     */
    public abstract void index();

}
