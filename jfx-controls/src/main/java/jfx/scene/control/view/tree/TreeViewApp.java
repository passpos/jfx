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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
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

/**
 * B101-B105
 * @author realpai <paiap@outlook.com>
 */
public class TreeViewApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Tree - TreeView";
    private TreeView<String> tv;
    private TreeItem<String> root;

    @Override
    public void index() {
        fillData();
        baseDemo();
//        listenerDemo();
//        editDemo();
//        customTreeCell();
        cellFactoryDemo();
    }

    /**
     * B101 TreeView 本身不会包含标题等文本信息，只是作为一个视图容器存在。
     */
    public void fillData() {
        tv = new TreeView<>();

        root = new TreeItem<>("中国");
        root.setExpanded(true);
        root.setValue("China");

        TreeItem<String> ti1 = new TreeItem<>("黑龙江省");
        TreeItem<String> leaf1 = new TreeItem<>("哈尔滨市");
        TreeItem<String> leaf2 = new TreeItem<>("佳木斯市");
        TreeItem<String> leaf3 = new TreeItem<>("大庆市");
        ti1.getChildren().addAll(leaf1, leaf2, leaf3);

        TreeItem<String> ti2 = new TreeItem<>("福建省");
        TreeItem<String> ti3 = new TreeItem<>("北京市");
        TreeItem<String> ti4 = new TreeItem<>("广东省");

        tv.setRoot(root);
        root.getChildren().addAll(ti1, ti2, ti3, ti4);

        getChildren().add(tv);
    }

    /**
     * B101 滚动事件
     */
    public void baseDemo() {
        tv.setPrefWidth(150.0);
        tv.scrollTo(6);
        // 设置树项的固定高度
        // tv.setFixedCellSize(32);
        tv.setShowRoot(true);
        int til = tv.getTreeItemLevel(root);

        tv.setOnScrollTo(new EventHandler<ScrollToEvent<Integer>>() {
            @Override
            public void handle(ScrollToEvent<Integer> t) {

            }
        });
    }

    /**
     * 选择事件
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

    public void editDemo() {
        tv.setEditable(true);
        tv.setCellFactory(TextFieldTreeCell.forTreeView());
        tv.setCellFactory(TextFieldTreeCell.forTreeView(new StringConverter<String>() {
            @Override
            public String toString(String t) {
                return t + " - 这个好";
            }

            @Override
            public String fromString(String string) {
                return string;
            }

        }));

        Button b = new Button("editDemo - 点击");
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                root.setValue("中国");
                root.setGraphic(new Button(root.getValue()));
            }
        });
        getChildren().add(b);
        setLeftAnchor(b, 160.0);
    }

    public void customTreeCell() {
        tv.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> param) {
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
                return tc;
            }
        });
    }

    /**
     * TreeView通过CellFactory，对其下的所有子项（TreeItem）设置视觉事件；
     * 这些视觉事件包括：
     * - 自定义的节点结构；
     * - 可编辑的节点；
     * -
     * 细节参考TreeCell；
     */
    public void cellFactoryDemo() {
        tv.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {

            @Override
            public TreeCell<String> call(TreeView<String> param) {
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
                return tc;
            }
        });
    }
}
