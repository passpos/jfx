/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.cell.tree;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import jfx.core.app.ContentBox;
import jfx.core.utils.TreeUtils;

/**
 * 可编辑节点
 *
 * @author passpos <paiap@outlook.com>
 */
public class TreeCellDemo1 extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Tree - TreeCell 可编辑节点";
    private TreeView<String> tv;
    private TreeItem<String> root;

    @Override
    public void index() {
        fillData();
        editDemo();
    }

    public void fillData() {
        TreeUtils tu = new TreeUtils();
        tv = tu.getTreeView();
        root = tu.getTreeItem();

        getChildren().add(tv);
    }

    /**
     * 可编辑的节点
     */
    public void editDemo() {
        tv.setEditable(true);
        tv.setCellFactory(TextFieldTreeCell.forTreeView());

        /*
         * Button b = new Button("editDemo - 点击");
         * b.setOnAction(new EventHandler<ActionEvent>() {
         * @Override
         * public void handle(ActionEvent t) {
         * root.setValue("中国");
         * root.setGraphic(new Button(root.getValue()));
         * }
         * });
         * getChildren().add(b);
         * setLeftAnchor(b, 160.0);
         */
    }
}
