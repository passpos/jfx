/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.view.tree;

import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
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
import jfx.core.app.ContentBox;

/**
 *
 * @author passpos <paiap@outlook.com>
 */
public class TreeCellApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Tree - TreeCell";
    private TreeView<String> tv;
    private TreeItem<String> root;

    @Override
    public void index() {
        fillData();
        dragDemo();
    }

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
     * 拖拽操作
     */
    public void dragDemo() {
        TreeCell<String> tc = new TreeCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    HBox hBox = new HBox();
                    hBox.getChildren().add(new Label(item));
                    setGraphic(hBox);
                } else if (empty) {
                    setGraphic(null);
                }
            }
        };

        tv.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            public TreeCell<String> temp;

            @Override
            public TreeCell<String> call(TreeView<String> param) {
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
