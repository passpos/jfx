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
package jfx.scene.control.dialog;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import jfx.core.app.ContentBox;

/**
 * Alert 是 Dialog 的子类。
 * @author realpai <paiap@outlook.com>
 */
public class AlertApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Dialog - Alert";

    @Override
    public void index() {
        baseDemo();
    }

    /**
     * ERROR 默认已经设置了 OK 类型的按键；
     */
    public void baseDemo() {
        Alert a1 = new Alert(Alert.AlertType.ERROR);
        Alert a2 = new Alert(Alert.AlertType.WARNING);
        Alert a3 = new Alert(Alert.AlertType.CONFIRMATION);

        a1.setAlertType(Alert.AlertType.ERROR);

        // a1.getDialogPane().getButtonTypes().add(ButtonType.OK);
        a1.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
        a1.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        a1.getDialogPane().getButtonTypes().add(0, ButtonType.CLOSE);
        a1.getDialogPane().getButtonTypes().remove(0);
        a1.getDialogPane().getButtonTypes().remove(ButtonType.CANCEL);

        // 设置图标
        a1.setGraphic(new Button("这里原来是图标！"));

        // 设置窗口模态
        // a1.initModality(Modality.NONE);

        Button b1 = new Button("点击");
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                a1.show();
            }
        });
        getChildren().add(b1);
    }
}
