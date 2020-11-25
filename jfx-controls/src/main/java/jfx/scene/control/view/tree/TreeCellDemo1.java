/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jfx.scene.control.view.tree;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import static javafx.scene.layout.AnchorPane.setLeftAnchor;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;

/**
 * 可编辑节点
 *
 * @author passpos <paiap@outlook.com>
 */
public class TreeCellDemo1 extends ContentBox {
    public static final boolean SHOWING = false;
    public static final String TITLE = "Tree - TreeCell Demo1 可编辑节点";
    private TreeView<String> tv;
    private TreeItem<String> root;

    @Override
    public void index() {
        fillData();
        editDemo();
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
}
