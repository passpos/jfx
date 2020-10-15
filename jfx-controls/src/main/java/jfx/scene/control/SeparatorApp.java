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
package jfx.scene.control;

import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class SeparatorApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Control - SeparatorApp 分隔线";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        Separator sp1 = new Separator();
        sp1.setPrefWidth(100);
        sp1.setPrefHeight(20);
        sp1.setValignment(VPos.CENTER);

        Separator sp2 = new Separator();
        sp2.setOrientation(Orientation.VERTICAL);

        Button btn1 = new Button("按钮1");
        Button btn2 = new Button("按钮2");
        Button btn3 = new Button("按钮3");

        HBox hb = new HBox();
        hb.getChildren().addAll(btn1, sp1, btn2, sp2, btn3);

        this.getChildren().add(hb);
    }

}
