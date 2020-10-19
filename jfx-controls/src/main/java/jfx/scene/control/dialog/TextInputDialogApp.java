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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import jfx.core.app.ContentBox;

/**
 * TextInputDialog使用了DialogPane
 * @author realpai <paiap@outlook.com>
 */
public class TextInputDialogApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Dialog - TextInputDialog";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        TextInputDialog tid = new TextInputDialog("默认文本");
        tid.resultProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                ol(t1);
            }
        });

        Button lb = (Button) tid.getDialogPane().lookupButton(ButtonType.OK);
        lb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                String commitString = tid.getEditor().getText();
                ol(commitString);
            }
        });

        Button b = new Button("点击");
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                tid.show();
                // tid.showAndWait();
            }
        });
        getChildren().add(b);
    }
}
