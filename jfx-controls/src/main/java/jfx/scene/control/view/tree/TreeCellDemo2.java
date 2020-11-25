/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.view.tree;

import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import jfx.core.app.ContentBox;

/**
 * 自定义节点样式
 *
 * @author passpos <paiap@outlook.com>
 */
public class TreeCellDemo2 extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Tree - TreeCell Demo2 自定义节点";
    private TreeView<String> tv;
    private TreeItem<String> root;

    @Override
    public void index() {
        fillData();
        customTreeCell();
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
     * 自定义的节点样式
     *
     * 默认的样式只有文本内容；
     * 通过自定义可以设置节点的：展开状态指示箭头、图标，以及其他复杂内容；
     */
    public void customTreeCell() {
        Callback<TreeView<String>, TreeCell<String>> callBack = new Callback<>() {
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
        };
        tv.setCellFactory(callBack);
    }
}
