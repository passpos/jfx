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

import java.util.Comparator;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollToEvent;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.ScrollEvent;
import jfx.core.app.ContentBox;

/**
 * B82-88课
 * @author realpai <paiap@outlook.com>
 */
public class ListViewApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "List - ListView";
    private ListView<String> lv;
    private ObservableList<String> oal;

    @Override
    public void index() {
        base();

        focusModelDemo();
        selectModelDemo();

        focusEventDemo();
        selectEventDemo();
        scrollEventDemo();
        editEventDemo();

        modifyDataDemo();
    }

    public void base() {
        oal = FXCollections.observableArrayList();
        oal.add("data - a");
        oal.add("data - b");
        oal.add("data - c");
        oal.add("data - d");
        oal.add("data - e");

        lv = new ListView<>();
        // lv1 = new ListView<>(oal1);
        lv.setItems(oal);
        // lv.setPlaceholder(new Label("没有数据"));
        // lv.setOrientation(Orientation.HORIZONTAL);

        // 小尺寸下，会自动出现滚动条；
        lv.setPrefWidth(300.0);
        lv.setPrefHeight(200.0);

        // 设置列表单元的尺寸
        lv.setFixedCellSize(50);

        ol(lv.getSelectionModel().getSelectedIndex());

        getChildren().add(lv);
    }

    /**
     * 焦点与选中是两种不同的效果，允许出现焦点所在未被选中的情况。
     *
     * 焦点用于光标导航或内容输入；
     */
    public void focusModelDemo() {
        // 是否转移焦点
        lv.setFocusTraversable(true);

        lv.getFocusModel().getFocusedIndex();
        lv.getFocusModel().getFocusedItem();

        // 设置焦点
        lv.getFocusModel().focus(1);

        // 由于焦点转移，选中项实际是灰色的
        lv.requestFocus();
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
        // 返回所有选中项的索引
        lv.getSelectionModel().getSelectedIndices();
        // 返回所有选中项
        lv.getSelectionModel().getSelectedItems();
        // 选中第0和3项
        // lv.getSelectionModel().selectIndices(0, 3);
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

    /**
     * 编辑事件
     *
     * 对编辑动作进行监听；
     */
    public void editEventDemo() {
        lv.setOnEditStart(new EventHandler<ListView.EditEvent<String>>() {
            @Override
            public void handle(ListView.EditEvent<String> t) {
                ol(t.getIndex());
                ol(t.getNewValue());
            }
        });
        lv.setOnEditCancel(new EventHandler<ListView.EditEvent<String>>() {
            @Override
            public void handle(ListView.EditEvent<String> t) {
            }
        });
        lv.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() {
            @Override
            public void handle(ListView.EditEvent<String> t) {
            }
        });
    }

    /**
     * 操作列表中的数据
     *
     * 实际是通过操作数据源实现CRUD；
     */
    public void modifyDataDemo() {
        Button b = new Button("点击查看效果");
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                oal.add("data - x");
                oal.add(2, "data - x");
                oal.set(0, "data - modify");
                oal.remove(4);
                oal.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o2.compareTo(o1);
                    }
                });

                lv.scrollTo(4);
                lv.edit(2);
            }
        });

        oal.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable o) {
                @SuppressWarnings("unchecked")
                ObservableList<String> ol = (ObservableList) o;
                ol(ol);
            }
        });

        /*
         * 替换动作被拆解为两步，先添加、再删除。所以，执行替换动作会：
         * 先打印“添加动作”、“删除动作”，再打印“替换动作”；
         *
         * 更新动作的结果一般不会被打印，原因见 ListViewDemo1 的 dataOperatorAndUIUpdateDemo()
         * 方法；
         */
        oal.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends String> c) {
                if (c.next()) {
                    if (c.wasAdded()) {
                        ol("添加动作");
                    }
                    if (c.wasPermutated()) {
                        ol("排序操作");
                    }
                    if (c.wasRemoved()) {
                        ol("删除动作");
                    }
                    if (c.wasReplaced()) {
                        ol("替换动作");
                    }
                    if (c.wasUpdated()) {
                        ol("更新动作");
                    }
                }
            }
        });
        getChildren().add(b);
        setTopAnchor(b, 300.0);
    }

}
