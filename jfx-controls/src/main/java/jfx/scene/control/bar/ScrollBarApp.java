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
package jfx.scene.control.bar;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.VBox;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ScrollBarApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Bar - ScrollBar 滚动条";
    private VBox vb;
    private ScrollBar sb;

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        vb = new VBox();
        for (int i = 0; i < 10; i++) {
            vb.getChildren().add(new Button("按钮" + i));
        }

        sb = new ScrollBar();
        sb.setOrientation(Orientation.VERTICAL);

        // 滚动条滑块的长度（0-100之间）
        sb.setVisibleAmount(30);

        // 滚动条的默认位置（0-100之间）
        sb.setValue(20);

        sb.setMax(vb.getHeight());
        sb.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                vb.setLayoutY(-t1.doubleValue());
            }
        });
        setRightAnchor(sb, 0.0);
        this.getChildren().addAll(vb, sb);
        this.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                sb.setPrefHeight(getBaseScene().getHeight());
            }
        });
    }
}
