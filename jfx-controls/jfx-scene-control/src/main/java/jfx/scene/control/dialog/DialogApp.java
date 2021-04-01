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
import javafx.scene.control.DialogPane;
import javafx.util.Callback;
import jfx.core.app.ContentBox;

/**
 * Dialog中封装了Stage与DialogPane，所以不必添加到父节点，就可以展示；
 *
 * @author realpai <paiap@outlook.com>
 */
public class DialogApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Dialog - Dialog";
    private Dialog<Button> d;
    private DialogPane dp;

    @Override
    public void index() {
        baseDemo();
        // eventDemo();
    }

    /**
     *
     */
    public void baseDemo() {
        d = new Dialog<>();
        d.setTitle("Title - 错误！");
        d.setContentText("Content - 不要胡乱点击！");
        d.setHeaderText("Header");
        d.setGraphic(new Button("Graphic"));

        dp = new DialogPane();
        dp.getButtonTypes().add(ButtonType.OK);
        d.setDialogPane(dp);

        // 这两条不起作用
        d.setWidth(300);
        d.setHeight(300);
        // 这条起作用
        d.getDialogPane().setPrefSize(300, 300);
        // dp.

        // 激发dialog
        Button btn = new Button("点击");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                showDemo();
            }
        });
        getChildren().add(btn);
    }

    /**
     * 当运行show()后，后面的代码仍会继续执行，不会等待用户操作；
     * 当运行showAndWait()后，必须等待用户操作，后面的代码才会继续执行；
     *
     * 对于showAndWait()，用户要获取用户输入的值，就必须在showAndWait()之前，完
     * 成输入组件的动作事件设定，否则，当用户输入时，输入组件是不包含动作监听的。
     * 而当showAndWait()执行提交后，窗口关闭，在那时设定的动作监听就不会起作用；
     */
    private void showDemo() {
        d.show();
        // d.showAndWait();
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
