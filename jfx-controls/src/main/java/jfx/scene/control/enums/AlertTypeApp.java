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

package jfx.scene.control.enums;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class AlertTypeApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Dialog - AlertType";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        Alert a1 = new Alert(Alert.AlertType.ERROR);
        Alert a2 = new Alert(Alert.AlertType.WARNING);
        Alert a3 = new Alert(Alert.AlertType.CONFIRMATION);
        Alert a4 = new Alert(Alert.AlertType.INFORMATION);
        Alert a5 = new Alert(Alert.AlertType.NONE);

        Button b1 = new Button("点击");
        getChildren().add(b1);

        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                a1.show();
                a2.show();
                a3.show();
                a4.show();
                // NONE类型窗口无法关闭；
                // a5.show();
            }
        });
    }
}
