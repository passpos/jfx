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
package jfx.scene.layout.border;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class BorderApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Border - Border";
    private HBox hBox;

    @Override
    public void index() {
    }

    public void baseDemo() {
        hBox = new HBox();
        hBox.setPrefWidth(200.0);
        hBox.setPrefHeight(250.0);
        hBox.setStyle("-fx-background-color:#556699");

        hBox.setBorder(new Border(new BorderStroke(
                Paint.valueOf("#ff0000"),
                BorderStrokeStyle.SOLID,
                new CornerRadii(0),
                new BorderWidths(3.0)
        )));
    }
}
