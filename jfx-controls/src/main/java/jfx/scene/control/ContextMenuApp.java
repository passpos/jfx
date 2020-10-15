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
import jfx.utils.app.ContentBox;

/**
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
