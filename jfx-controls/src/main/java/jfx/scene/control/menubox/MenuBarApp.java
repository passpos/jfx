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
package jfx.scene.control.menubox;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class MenuBarApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "MenuBox - MenuBar 菜单栏";
    public ContentBox currentInstance;
    public MenuBar mb;

    public void decrease(FadeTransition ft) {
        ft.setFromValue(0.97);
        ft.setToValue(0.0);
        ft.play();
        ft.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                increase(ft);
            }
        });
    }

    public void increase(FadeTransition ft) {
        ft.setFromValue(0.0);
        ft.setToValue(0.97);
        ft.play();
        ft.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                decrease(ft);
            }
        });
    }

    @Override
    public void index() {
        currentInstance = this;
        // 菜单栏
        mb = new MenuBar();

        // 添加菜单，并为菜单项添加事件；
        baseDemo();

        // 快捷键
        shortcutDemo();

        // 菜单项图标
        iconDemo();

        // 分割线
        seperatorDemo();

        // 子菜单
        subMenuDemo();

        // 单选项
        radioMenuItemDemo();

        // 多选项
        checkMenuItemDemo();

        // 自定义菜单
        customMenuItemDemo();

        Menu mn = new Menu("菜单按钮");
        mb.getMenus().add(mn);

        // 设置自定义样式
        setCustomStyle();

        this.getChildren().addAll(mb);
    }

    /**
     * 添加菜单，并为菜单项添加事件；
     *
     * 点击菜单子项，也会触发菜单的setOnAction()；
     * @TODO 匿名对象中访问当前对象
     */
    public void baseDemo() {
        Menu mn = new Menu("File");
        MenuItem itm1 = new MenuItem("点击菜单项");
        MenuItem itm2 = new MenuItem("退出");
        itm1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ol("这是菜单项事件输出。触发菜单点击事件！");
            }
        });
        itm2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                getPrimaryStage().close();
            }
        });
        mn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                FadeTransition ft = new FadeTransition();
                ft.setDuration(Duration.seconds(4.0));
                ft.setNode(MenuBarApp.this);     // 设置“节点”变换动画；
                decrease(ft);
                ol("菜单点击事件");
            }
        });
        mn.getItems().addAll(itm1, itm2);
        mb.getMenus().add(mn);
    }

    /**
     * 快捷键，并添加点击事件；
     */
    public void shortcutDemo() {
        Menu mn = new Menu("快捷键");
        MenuItem itm = new MenuItem("打印 + 快捷键");
        itm.setAccelerator(KeyCombination.valueOf("alt+p"));
        itm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ol("快捷键 OK!");
            }
        });
        mn.getItems().addAll(itm);
        mb.getMenus().add(mn);
    }

    /**
     * 菜单项图标
     */
    public void iconDemo() {
        Menu mn = new Menu("菜单项图标");
        MenuItem itm = new MenuItem("图标", new ImageView("file:src/main/java/icon/fav.jpg"));
        mn.getItems().add(itm);
        mb.getMenus().add(mn);
    }

    /**
     * 分割线
     *
     * 只有菜单下有子项时，才会触发菜单的setOnAction()；
     */
    public void seperatorDemo() {
        Menu mn = new Menu("分割线");
        mn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                getPrimaryStage().close();
            }
        });
        MenuItem itm4 = new MenuItem("上");
        MenuItem itm5 = new MenuItem("下");
        SeparatorMenuItem smi = new SeparatorMenuItem();

        mn.getItems().addAll(itm4, smi, itm5);
        mb.getMenus().add(mn);
    }

    /**
     * 子菜单
     */
    public void subMenuDemo() {
        Menu mn1 = new Menu("菜单");
        Menu mn2 = new Menu("子菜单");

        MenuItem itm1 = new MenuItem("it6");
        MenuItem itm2 = new MenuItem("子菜单项");

        SeparatorMenuItem smi2 = new SeparatorMenuItem();
        mn2.getItems().addAll(itm2);
        mn1.getItems().addAll(itm1, smi2, mn2);

        mb.getMenus().add(mn1);
    }

    /**
     * 单选项 单选组，几个单选项在一起，要形成单选效果，需要设置关联（使用ToggleGroup）；
     */
    public void radioMenuItemDemo() {
        Menu mn = new Menu("单选按钮");
        RadioMenuItem rmi1 = new RadioMenuItem("单选1");
        RadioMenuItem rmi2 = new RadioMenuItem("单选2");
        RadioMenuItem rmi3 = new RadioMenuItem("单选3");

        ToggleGroup tg = new ToggleGroup();
        rmi1.setToggleGroup(tg);
        rmi1.setSelected(true);     //默认选中
        rmi2.setToggleGroup(tg);
        rmi2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                RadioMenuItem r = (RadioMenuItem) t.getSource();
                ol(r.isSelected());
            }
        });

        rmi3.setToggleGroup(tg);
        mn.getItems().addAll(rmi1, rmi2, rmi3);
        mb.getMenus().add(mn);
    }

    /**
     * 多选项
     */
    public void checkMenuItemDemo() {
        Menu mn = new Menu("多选项");
        CheckMenuItem cmi1 = new CheckMenuItem("多选1");
        CheckMenuItem cmi2 = new CheckMenuItem("多选2");
        CheckMenuItem cmi3 = new CheckMenuItem("多选3");

        mn.getItems().addAll(cmi1, cmi2, cmi3);
        mb.getMenus().add(mn);
    }

    // 自定义菜单
    public void customMenuItemDemo() {
        Menu mn = new Menu("自定义菜单");

        // 按钮菜单
        Button btn1 = new Button("按钮1");
        CustomMenuItem csmi1 = new CustomMenuItem();
        csmi1.setContent(btn1);

        // 进度条菜单
        ProgressBar progressBar = new ProgressBar(0.3);
        CustomMenuItem csmi2 = new CustomMenuItem(progressBar);

        // 布局节点
        HBox hb = new HBox();
        hb.getChildren().addAll(new Button("btnA"), new Button("btnB"), new Button("btnC"));
        hb.setStyle("-fx-background-color:#993322");
        hb.setPrefWidth(150);
        hb.setPrefHeight(30);
        hb.setPadding(new Insets(4.0));
        CustomMenuItem csmi3 = new CustomMenuItem(hb);

        mn.getItems().addAll(csmi1, csmi2, csmi3);
        mb.getMenus().add(mn);
    }

    /**
     * 设置自定义样式
     *
     * @TODO 匿名对象中访问当前对象
     */
    public void setCustomStyle() {
        mb.prefWidthProperty().bind(widthProperty());
    }

}
