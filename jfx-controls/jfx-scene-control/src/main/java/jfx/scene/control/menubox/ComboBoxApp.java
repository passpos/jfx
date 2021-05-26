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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ComboBoxApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "MenuBox - ComboBox 下拉菜单";
    private ComboBox<String> cb;


    @Override
    public void index() {
        // 基本使用演示
        base();

        // 菜单选择事件
        selectEvent();

        // 点击事件
        onClickDemo();
    }

    /**
     * ComboBox 基本使用演示
     */
    public void base() {
        cb = new ComboBox<>();
        cb.getItems().addAll("bt1", "bt2", "bt3", "bt4", "bt5", "bt6");
        getChildren().add(cb);

        /*
         * 设置可以编辑
         *
         * 注意：如果设置为false，
         * setPromptText()设置的内容就会展示到ComboBox上（类似于标题）；
         * 若setPromptText()未设置，当setConverter()的toString()方法，在处理null
         * 时，返回的字符串会展示在同一个地方；
         */
        cb.setEditable(true);

        // 文本提示
        cb.setPromptText("请输入数据");

        // 占位符
        cb.setPlaceholder(new Button("占位后，其他元素不可见"));

        // 可见的行数（滚动条）
        cb.setVisibleRowCount(4);
        cb.setPrefWidth(200.0);

    }

    /**
     * 菜单选择事件
     */
    public void selectEvent() {
        cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                ol("菜单选择事件 - " + t1);
            }
        });
    }

    /**
     * 点击事件
     */
    public void onClickDemo() {
        cb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ol("点击事件");
            }
        });
    }

}
