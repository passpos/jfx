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
package jfx.scene.control.view.tree;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollToEvent;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;
import utils.entity.demo.sample.Person;

/**
 * TreeView 节点类型，是一个视图容器；
 * TreeItem 非节点类型，但可以视为一个树节点的容器，包含了以下信息：
 * - 树节点的组织关系、
 * - 展开状态、
 * - 组成外观（图标）的节点对象、
 * - 用于展示的数据对象；
 * - 事件状态信息；
 * TreeCell 节点类型，树节点。包含一个用于表示节点展开状态的节点对象（节点前的三角）；
 *
 * B101-B105
 * @author realpai <paiap@outlook.com>
 */
public class TreeViewApp extends ContentBox {

    public static final boolean SHOWING = true;
    public static final String TITLE = "Tree - TreeView";
    private TreeView<String> tv;
    private TreeItem<String> root;

    @Override
    public void index() {
        fillData();
        baseDemo();
        onScrollToDemo();
        selectionModeDemo();
        focusChangeDemo();
        //cellFactoryDemo1();
        //cellFactoryDemo2();
        //cellFactoryDemo3();
    }

    /**
     * B101 TreeView 本身不会包含标题等文本信息，只是作为一个视图容器存在。
     */
    public void fillData() {
        TreeUtils tu = new TreeUtils();
        tv = tu.getTreeView();
        root = tu.getTreeItem();

        getChildren().add(tv);
    }

    /**
     * B101 滚动事件
     */
    public void baseDemo() {
        tv.setPrefWidth(150.0);

        // 可见项的数量
        tv.getExpandedItemCount();

        // 设置子项的固定高度
        // tv.setFixedCellSize(32);
        tv.setShowRoot(true);
        int til = tv.getTreeItemLevel(root);

    }

    public void onScrollToDemo() {
        tv.scrollTo(6);
        tv.setOnScrollTo(new EventHandler<ScrollToEvent<Integer>>() {
            @Override
            public void handle(ScrollToEvent<Integer> t) {
                ol("onScrollTo - " + t.toString());
            }
        });
    }

    /**
     * 选择模式监听
     */
    public void selectionModeDemo() {
        tv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> ov, TreeItem<String> t, TreeItem<String> t1) {
                ol(t1.getValue());
            }
        });
    }

    /**
     * 焦点模式监听
     */
    public void focusChangeDemo() {
        tv.getFocusModel().focus(3);
        tv.getFocusModel().focusedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> ov, TreeItem<String> t, TreeItem<String> t1) {
                ol(t1.getValue());
            }
        });
        tv.requestFocus();
    }

    /*
     * TreeView通过CellFactory，对其下的所有子项（TreeItem）设置视觉事件；
     * 这些视觉事件包括：
     * - 自定义的节点结构；
     * - 可编辑的节点；
     * -
     * 细节参考TreeCell及其Demo；
     */
    /**
     * 下面，String类型的数据，不必使用 StringConverter 进行转换；
     *
     * 转换过程是通过一个回调完成的。
     * 既可以自己创建一个，也可以使用 TextFieldTreeCell 的forTreeView()方法获取；
     * forTreeView()还支持传递一个 StringConverter 类型的参数，十分方便；
     */
    public void cellFactoryDemo1() {
        TreeView<String> trv = new TreeView<>();

        Callback<TreeView<String>, TreeCell<String>> cb;
        cb = TextFieldTreeCell.forTreeView();

        trv.setCellFactory(cb);
    }

    /**
     * 其他类型的数据需要通过 StringConverter 进行数据转换；
     */
    public void cellFactoryDemo2() {
        TreeView<Person> trv = new TreeView<>();

        StringConverter<Person> strc;
        Callback<TreeView<Person>, TreeCell<Person>> cb;

        strc = new StringConverter<Person>() {
            @Override
            public String toString(Person t) {
                return t.getName() + t.getAge();
            }

            @Override
            public Person fromString(String string) {
                Person p = new Person(string, 0);
                return p;
            }
        };
        cb = TextFieldTreeCell.forTreeView(strc);

        trv.setCellFactory(cb);
    }

    /**
     * 创建自定义的Callback，用于处理用户指定的TreeCell
     */
    public void cellFactoryDemo3() {
        TreeCell<String> tc = new TreeCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    HBox hBox = new HBox();
                    hBox.getChildren().add(new Label(item));
                    this.setGraphic(hBox);
                } else if (empty) {
                    this.setGraphic(null);
                }
            }
        };

        Callback<TreeView<String>, TreeCell<String>> cb = new Callback<>() {

            @Override
            public TreeCell<String> call(TreeView<String> param) {
                return tc;
            }
        };
        tv.setCellFactory(cb);
    }
}
