/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.view.tree;

import javafx.scene.control.Button;
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
        //customTreeCell();
    }

    public void fillData() {
        TreeUtils tu = new TreeUtils();
        tv = tu.getTreeView();
        root = tu.getTreeItem();

        getChildren().add(tv);
    }

    /**
     * 自定义的节点样式
     *
     * 默认的样式只有文本内容；
     * 通过自定义可以设置节点的：展开状态指示箭头、图标，以及其他复杂内容；
     */
    public void customTreeCell() {
        TreeCell<String> tc = new TreeCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    // 添加一个布局组件；
                    HBox hBox = new HBox();

                    // 在这个布局组件中放置一个Label和一个Button；
                    Label l = new Label(item);
                    Button b = new Button(item);
                    hBox.getChildren().addAll(l, b);

                    // 将整个布局组件设置为节点图标；
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
