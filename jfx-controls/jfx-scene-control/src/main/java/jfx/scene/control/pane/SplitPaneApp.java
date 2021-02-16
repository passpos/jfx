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
package jfx.scene.control.pane;

import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class SplitPaneApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Pane - SplitPane 分离式面板";
    public SplitPane sp;

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        Button btn1 = new Button("按钮1");
        Button btn2 = new Button("按钮2");
        Button btn3 = new Button("按钮3");
        Button btn4 = new Button("按钮4");

        StackPane stp1 = new StackPane();
        stp1.getChildren().add(btn1);

        StackPane stp2 = new StackPane();
        stp2.getChildren().add(btn2);

        StackPane stp3 = new StackPane();
        stp3.getChildren().add(btn3);

        StackPane stp4 = new StackPane();
        stp4.getChildren().add(btn4);

        sp = new SplitPane();
        sp.getItems().addAll(stp1, stp2, stp3, stp4);
        sp.setPrefWidth(600);
        sp.setPrefHeight(500);
        // 设置为垂直方向
        // sp.setOrientation(Orientation.VERTICAL);
        sp.setDividerPosition(0, 0.25);
        sp.setDividerPosition(1, 0.5);
        sp.setDividerPosition(2, 0.75);
        sp.setDividerPosition(3, 1);
        this.getChildren().add(sp);
    }
}
