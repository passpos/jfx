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
package jfx.scene.control.button;

import java.util.function.Consumer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ToggleButtonApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Button - ToggleButton";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        ToggleButton tb1 = new ToggleButton("tb1");
        ToggleButton tb2 = new ToggleButton("tb2");
        ToggleButton tb3 = new ToggleButton("tb3");
        ToggleButton tb4 = new ToggleButton("tb4");

        ToggleGroup tg = new ToggleGroup();
        tb1.setToggleGroup(tg);
        tb2.setToggleGroup(tg);
        tb3.setToggleGroup(tg);
        tb4.setToggleGroup(tg);

        // 选中状态监听
        tb1.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) -> {
            ol("tb1：" + t1.toString() + " - 竟然选了我");
        });

        tb2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                tg.getToggles().forEach(new Consumer<Toggle>() {
                    @Override
                    public void accept(Toggle t) {
                        ol(t.isSelected());
                    }
                });
            }
        });
        // 设置默认选中：
        tb3.setSelected(true);

        // 子选项的选中状态监听
        tg.selectToggle(tb4);
        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
                ToggleButton rb = (ToggleButton) t1;
                ol(rb.getText());
            }
        });

        VBox vb = new VBox();
        vb.setSpacing(5);
        vb.getChildren().addAll(tb1, tb2, tb3, tb4);
        getChildren().add(vb);
    }

}
