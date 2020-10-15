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
package jfx.scene.control.menubox;

import jfx.utils.app.ContentBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ColorPickerApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "MenuBox - ColorPicker";

    @Override
    public void index() {
        this.setStyle("-fx-background-color:#ccff66");
        baseDemo();
        cpDemo();
    }

    public void baseDemo() {
        ColorPicker cp = new ColorPicker();

        cp.valueProperty().addListener(new ChangeListener<Color>() {
            @Override
            public void changed(ObservableValue<? extends Color> ov, Color t, Color t1) {
                ol("ColorPicker监听器t1：" + t1);
                ol("ColorPicker监听器t1.getGreen()：" + t1.getGreen());
                ol("ColorPicker监听器t1.toString()：" + t1.toString());
                String value = t1.toString().substring(2);
                ol("ColorPicker监听器t1.toString().substring(2)：" + value);
                ColorPickerApp.this.getParent().setStyle("-fx-background-color:#" + value);
                ColorPickerApp.this.setStyle("-fx-background-color:#" + value);
            }
        });
        this.getChildren().add(cp);
    }

    public void cpDemo() {
        ColorPicker cp = new ColorPicker(Color.valueOf("#ffff55"));
        setTopAnchor(cp, 300.0);
        this.getChildren().add(cp);
    }
}
