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
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollToEvent;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;

/**
 * B101-B105
 * @author realpai <paiap@outlook.com>
 */
public class TreeViewApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "View - TreeView";
    private TreeView<String> tv;
    private TreeItem<String> root;

    @Override
    public void index() {
        fillData();
        baseDemo();
//        listenerDemo();
//        eventDemo();
//        editDemo();
//        customTreeCell();
        dragDemo();
    }

    /**
     * B101 TreeView 本身不会包含标题等文本信息，只是作为一个视图容器存在。
     */
    public void fillData() {
        tv = new TreeView<>();

        root = new TreeItem<>("中国");
        root.setExpanded(true);
        root.setValue("China");
        root.isLeaf();    // 节点下是否为空；
        // 当前节点的上下节点（根节点获取不到上一个节点）
        // root.previousSibling().getValue();
        // root.nextSibling().getValue();
        // 父节点
        // root.getParent().getValue();
        // 子节点
        // root.getChildren();

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
     * B101
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

    public void listenerDemo() {
        tv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> ov, TreeItem<String> t, TreeItem<String> t1) {
                ol(t1.getValue());
            }
        });

        tv.getFocusModel().focus(3);
        tv.getFocusModel().focusedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> ov, TreeItem<String> t, TreeItem<String> t1) {
                ol(t1.getValue());
            }
        });
        tv.requestFocus();
    }

    /**
     * 这里直接对根节点设置事件处理，那么对子节点的操作，会将事件传递到根节
     * 点，从而得到事件的响应；
     */
    public void eventDemo() {
        Button b = new Button("eventDemo - 点击");
        getChildren().add(b);
        setLeftAnchor(b, 300.0);
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                root.setValue("newName");
            }
        });
        root.addEventHandler(TreeItem.<String>valueChangedEvent(), new EventHandler<TreeItem.TreeModificationEvent<String>>() {
            @Override
            public void handle(TreeItem.TreeModificationEvent<String> t) {
                ol(t.getNewValue());
            }
        });
        root.addEventHandler(TreeItem.expandedItemCountChangeEvent(), new EventHandler<TreeItem.TreeModificationEvent<String>>() {
            @Override
            public void handle(TreeItem.TreeModificationEvent<String> t) {
            }
        });
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

    public void dragDemo() {
        tv.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            public TreeCell<String> temp;

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

                tc.setOnDragDetected(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        Dragboard sdd = tc.startDragAndDrop(TransferMode.COPY_OR_MOVE);
                        ClipboardContent cc = new ClipboardContent();
                        cc.putString(tc.getItem());

                        Text text = new Text(tc.getItem());
                        text.setFont(new Font(16));
                        WritableImage wi = new WritableImage((int) tc.getWidth(), 20);
                        text.snapshot(new SnapshotParameters(), wi);

                        sdd.setDragView(wi);
                        sdd.setContent(cc);
                    }
                });

                tc.setOnDragOver(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent t) {
                        t.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        if (temp != null) {
                            temp.setBorder(null);
                        }
                        temp = tc;
                        if (t.getY() >= 0 && t.getY() <= tc.getHeight() - 10) {

                        } else if (t.getY() > tc.getHeight() - 10 && t.getY() <= tc.getHeight()) {
                            BorderStroke bs = new BorderStroke(
                                    null,
                                    null,
                                    Paint.valueOf("#71C671"),
                                    null,
                                    BorderStrokeStyle.SOLID,
                                    null,
                                    null,
                                    null,
                                    null,
                                    new BorderWidths(0, 0, 2, 0),
                                    null
                            );
                            Border border = new Border(bs);
                            tc.setBorder(border);
                        }
                    }
                });

                tc.setOnDragDropped(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent t) {
                        String value = t.getDragboard().getString();
                        tc.getTreeItem().isLeaf();
                        if (tc.getTreeItem().getParent() != null) {
                            int index = tc.getTreeItem().getParent().getChildren().indexOf(tc.getTreeItem());
                            tc.getTreeItem().getParent().getChildren().add(index + 1, new TreeItem<>(value));
                        }
                    }
                });

                return tc;
            }
        });
    }
}
