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
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class SplitMenuButtonApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Button - SplitMenuButton 分离式下拉";
    public SplitMenuButton smb;

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        smb = new SplitMenuButton();
        MenuItem mi1 = new MenuItem("itm1");
        MenuItem mi2 = new MenuItem("itm2");
        smb.getItems().addAll(mi1, mi2);
        smb.setText("分离式下拉菜单");

        // 点击事件有效：
        smb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                smb.setText("变化的名称");
            }
        });

        this.getChildren().addAll(smb);
    }

}
