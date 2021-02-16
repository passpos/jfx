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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ChoiceDialogApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Dialog - ChoiceDialog";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");

        ChoiceDialog<String> cd1 = new ChoiceDialog<>("C", list);
        ChoiceDialog<String> cd2 = new ChoiceDialog<>("C", "A", "B", "C", "D");

        cd1.selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                ol("这里返回的不是提交结果！ - " + t1);
            }
        });


        Button b = new Button("点击");
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                cd1.show();
                // cd.showAndWait();
            }
        });
        getChildren().add(b);
    }
}
