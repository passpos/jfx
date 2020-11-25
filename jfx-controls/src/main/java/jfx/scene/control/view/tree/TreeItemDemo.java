/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.view.tree;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import jfx.core.app.ContentBox;

/**
 *
 * @author passpos <paiap@outlook.com>
 */
public class TreeItemDemo extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Tree - TreeItem";
    public TreeView<String> tv;
    private TreeItem<String> root;

    @Override
    public void index() {
        baseDemo();

    }

    public void baseDemo() {
        root = new TreeItem<>("中国");
        root.setExpanded(true);
        root.setValue("China");

        tv = new TreeView<>();
        tv.setRoot(root);
        tv.setPrefHeight(50);
        getChildren().add(tv);
    }
}
