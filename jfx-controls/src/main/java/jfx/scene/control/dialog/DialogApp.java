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

import java.util.Optional;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogEvent;
import javafx.util.Callback;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class DialogApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Dialog - Dialog";
    private Dialog<Button> d;

    @Override
    public void index() {
        baseDemo();
        eventDemo();
    }

    /**
     * Dialog不必添加到父节点，就可以展示；
     */
    public void baseDemo() {
        d = new Dialog<>();
        d.setTitle("Title - 错误！");
        d.setContentText("Content - 不要胡乱点击！");
        d.setHeaderText("Header");
        d.setGraphic(new Button("Graphic"));

        // 这两条不起作用
        d.setWidth(300);
        d.setHeight(300);
        // 这条起作用
        d.getDialogPane().setPrefSize(300, 300);


        // 向dialog添加按钮
        d.getDialogPane().getButtonTypes().add(ButtonType.OK);
        d.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        d.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        // 从dialog对象中获取相应的按钮，并设置单击事件。如果dialog不具有该按钮，运行错误；
        Button b1 = (Button) d.getDialogPane().lookupButton(ButtonType.CLOSE);
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ol(t.toString());
            }
        });

        // 激发dialog
        Button btn = new Button("点击");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                d.show();
            }
        });


        getChildren().add(btn);
    }

    /**
     * 事件监听
     */
    public void eventDemo() {
        d.setOnCloseRequest(new EventHandler<DialogEvent>() {
            @Override
            public void handle(DialogEvent t) {
                ol("OnCloseRequest");
            }
        });
        d.setResultConverter(new Callback<ButtonType, Button>() {
            @Override
            public Button call(ButtonType param) {
                ol(param);
                return null;
            }
        });

        Optional<Button> saw = d.showAndWait();
        saw.ifPresent(new Consumer<Button>() {
            @Override
            public void accept(Button t) {
            }
        });
    }
}
