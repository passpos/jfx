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
package jfx.scene.control.button;

import jfx.utils.app.ContentBox;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class MenuButtonApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Button - MenuButton 菜单按钮";
    public MenuButton menuButton;

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        menuButton = new MenuButton("菜单按钮");
        AnchorPane.setTopAnchor(menuButton, 100.0);
        AnchorPane.setLeftAnchor(menuButton, 20.0);

        MenuItem itm1 = new MenuItem("itm1");
        MenuItem itm2 = new MenuItem("itm2");
        itm2.setAccelerator(KeyCombination.valueOf("ctrl+j"));

        // 用于响应快捷键
        itm2.setOnMenuValidation(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                menuButton.setText("变化的名称");
            }
        });
        menuButton.getItems().addAll(itm1, itm2);

        // 点击事件无效：
        menuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                menuButton.setText("变化的名称");
            }
        });

        this.getChildren().add(menuButton);
    }

}
