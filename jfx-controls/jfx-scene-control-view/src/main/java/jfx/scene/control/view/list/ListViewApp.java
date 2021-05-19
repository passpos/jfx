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
package jfx.scene.control.view.list;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollToEvent;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.ScrollEvent;
import jfx.core.app.ContentBox;
import jfx.core.common.Data;

/**
 * B82-88课 当前 B82
 *
 * StringConverter参考ListCell
 *
 * @author realpai <paiap@outlook.com>
 */
public class ListViewApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "ListView - ListView";
    private ListView<String> lv;
    private ObservableList<String> oList;

    @Override
    public void index() {
        base();

        focusModelDemo();
        selectModelDemo();

        focusEventDemo();
        selectEventDemo();
        scrollEventDemo();

    }

    public void base() {
        oList = Data.getStringList();

        lv = new ListView<>();
        // lv1 = new ListView<>(oal1);

        // lv.getItems().add("");
        lv.setItems(oList);

        // lv.setPlaceholder(new Label("没有数据"));
        // 默认为列表垂直
        // lv.setOrientation(Orientation.HORIZONTAL);
        // 小尺寸下，会自动出现滚动条；
        lv.setPrefWidth(300.0);
        lv.setPrefHeight(200.0);

        // 设置列表单元的尺寸
        lv.setFixedCellSize(50);

        lv.scrollTo("data - c");
        lv.scrollTo(3);

        getChildren().add(lv);
    }

    /**
     * 焦点与选中是两种不同的效果，允许出现焦点所在未被选中的情况。
     *
     * 焦点用于光标导航或内容输入；
     */
    public void focusModelDemo() {
        // 是否可以将焦点转移到这里；
        lv.setFocusTraversable(true);

        lv.getFocusModel().getFocusedIndex();
        lv.getFocusModel().getFocusedItem();

        // 设置焦点
        lv.getFocusModel().focus(1);

        // 由于焦点转移，选中项实际是灰色的；
        lv.requestFocus();
    }

    /**
     * 焦点事件
     */
    public void focusEventDemo() {
        lv.getFocusModel().focusedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
            }
        });
        lv.getFocusModel().focusedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
            }
        });
    }

    /**
     * 默认为单选模式
     */
    public void selectModelDemo() {
        lv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // 设置默认选中
        lv.getSelectionModel().select(0);
        // lv.getSelectionModel().clearAndSelect(0);
        // lv.getSelectionModel().clearSelection();
        // lv.getSelectionModel().clearSelection(1);

        // 获取被选中项的索引，没有选中项时为-1
        lv.getSelectionModel().getSelectedIndex();

        // 返回所有选中项的索引
        lv.getSelectionModel().getSelectedIndices();

        // 返回所有选中项
        lv.getSelectionModel().getSelectedItems();

        // 选中第0和3项
        // lv.getSelectionModel().selectIndices(0, 3);
    }

    /**
     * 选择事件
     */
    public void selectEventDemo() {
        lv.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
            }
        });
        lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
            }
        });
    }

    /**
     * 滚动事件
     */
    public void scrollEventDemo() {
        // 仅对lv.scrollTo()动作生效
        lv.setOnScrollTo(new EventHandler<ScrollToEvent<Integer>>() {
            @Override
            public void handle(ScrollToEvent<Integer> t) {
                ol(t.getScrollTarget());
            }
        });
        // 滚轮滚动事件
        lv.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent t) {
                ol(t.getDeltaX());
                ol(t.getDeltaY());
            }
        });
    }


}
