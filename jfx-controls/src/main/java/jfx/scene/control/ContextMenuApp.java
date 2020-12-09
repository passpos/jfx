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
package jfx.scene.control;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import jfx.core.app.ContentBox;

/**
 * contextMenu是 javafx.scene.control.Control 的一个属性；
 *
 * 继承关系：
 * java.lang.Object
 * |- javafx.stage.Window
 * |---- javafx.stage.PopupWindow
 * |------- javafx.scene.control.PopupControl
 * |---------- javafx.scene.control.ContextMenu
 *
 * 属性：
 * - onAction
 *
 * 方法：
 * - getItems()
 * 获取右键菜单中的所有菜单项；
 *
 * - hide()
 * 隐藏此ContextMenu和任何可见子菜单，假设调用此函数时ContextMenu正在显示；
 *
 * - show​(Node anchor, double screenX, double screenY)
 * 在指定的屏幕坐标处显示；
 *
 * - show​(Node anchor, Side side, double dx, double dy)，
 * 在hpos和vpos参数指定的一侧显示相对于给定锚点的ContextMenu，并分别在x轴和y轴上
 * 显示给定的dx和dy值偏移；
 *
 * @author realpai <paiap@outlook.com>
 */
public class ContextMenuApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Control - ContextMenu 右键菜单";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("itm1");
        MenuItem mi2 = new MenuItem("itm2");
        cm.getItems().addAll(mi1, mi2);

        Button btn = new Button("在此处右键");
        btn.setContextMenu(cm);
        btn.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent t) {
                ol("右键激发信息");
            }
        });

        this.getChildren().add(btn);
    }

}
