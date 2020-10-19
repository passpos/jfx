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

import jfx.core.app.ContentBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class RadioButtonApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Button - RadioButton 单选按钮";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        RadioButton rb1 = new RadioButton("rb1");
        RadioButton rb2 = new RadioButton("rb2");
        RadioButton rb3 = new RadioButton("rb3");
        RadioButton rb4 = new RadioButton("rb4");

        ToggleGroup tg = new ToggleGroup();
        rb1.setToggleGroup(tg);
        rb1.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) -> {
            ol("rb1：" + t1.toString() + "竟然选了我");
        });
        rb2.setToggleGroup(tg);
        rb3.setToggleGroup(tg);
        rb4.setToggleGroup(tg);

        // 设置默认选中：
        rb3.setSelected(true);

        // 子选项的选中状态监听
        // tg.selectToggle(rb4);
        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
                RadioButton rb = (RadioButton) t1;
                ol(rb.getText());
            }
        });

        VBox vb = new VBox();
        vb.getChildren().addAll(rb1, rb2, rb3, rb4);
        this.getChildren().add(vb);
    }
}
