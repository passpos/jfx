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
package jfx.animation.demo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class PhotoSwitcher extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Animation - Demo 走马灯";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        Button b1 = new Button("按钮1");
        Button b2 = new Button("按钮2");

        StackPane sp = new StackPane();
        sp.setPrefWidth(400);
        sp.setPrefHeight(400);
        sp.setBorder(new Border(new BorderStroke(Color.CHOCOLATE, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));

        HBox hBox1 = new HBox();
        hBox1.setBackground(new Background(new BackgroundFill(Color.AQUA, new CornerRadii(3), new Insets(3))));
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().add(new Button("按钮"));

        HBox hBox2 = new HBox();
        hBox2.setBackground(new Background(new BackgroundFill(Color.AQUA, new CornerRadii(3), new Insets(3))));
        hBox2.setAlignment(Pos.CENTER);
        hBox2.getChildren().add(new Button("按钮"));

        sp.getChildren().addAll(hBox1, hBox2);
        setTopAnchor(sp, 100.0);
        setLeftAnchor(sp, 100.0);
        getChildren().addAll(b1, b2, sp);

    }
}
