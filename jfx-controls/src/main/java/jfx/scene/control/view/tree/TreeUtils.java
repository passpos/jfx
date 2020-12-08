/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.view.tree;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 *
 * @author passpos <paiap@outlook.com>
 */
public class TreeUtils {

    private TreeView<String> tv;
    private TreeItem<String> root;

    public TreeUtils() {
        setTreeItem();
        setTreeView();
    }

    public TreeView<String> getTreeView() {
        return tv;
    }

    private void setTreeView() {
        tv = new TreeView<>();
        tv.setRoot(root);
    }

    public TreeItem<String> getTreeItem() {
        return root;
    }

    @SuppressWarnings("unchecked")
    private void setTreeItem() {
        TreeItem<String> ti1 = new TreeItem<>("黑龙江省");
        TreeItem<String> leaf1 = new TreeItem<>("哈尔滨市");
        TreeItem<String> leaf2 = new TreeItem<>("佳木斯市");
        TreeItem<String> leaf3 = new TreeItem<>("大庆市");
        ti1.getChildren().addAll(leaf1, leaf2, leaf3);

        TreeItem<String> ti2 = new TreeItem<>("福建省");
        TreeItem<String> ti3 = new TreeItem<>("北京市");
        TreeItem<String> ti4 = new TreeItem<>("广东省");

        root = new TreeItem<>("中国");
        root.setExpanded(true);
        root.setValue("China");
        root.getChildren().addAll(ti1, ti2, ti3, ti4);
    }
}
