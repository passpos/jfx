/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.view.tree;

import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private TreeView<File> buildFileSystemBrowser() {
        TreeItem<File> fr = createNode(new File("/"));
        return new TreeView<>(fr);
    }

    /**
     * 此方法创建一个TreeItem来表示给定的File。它通过匿名重写
     * TreeItem.getChildren（）和TreeItem.isLeaf（）方法来做到这一点，但最好通过
     * 创建TreeItem的“ FileTreeItem”子类来抽象该方法。
     * 但是，这留给用户作练习；
     */
    private TreeItem<File> createNode(final File f) {
        TreeItem<File> ti = new TreeItem<>(f) {
            /**
             * 我们缓存文件是否为叶。 如果文件不是目录并且其中不包含任何文件，则
             * 它是叶。
             * 我们经常调用isLeaf（）来缓存它，因此对File进行实际检查非常昂贵。
             */
            private boolean isLeaf;

            /**
             * 我们对子项和叶子仅进行一次测试，然后将其设置为false，以便在此运行
             * 期间不再进行检查。
             * 可能需要更完整的实现来处理更多动态文件系统情况（例如，在显示
             * TreeView之后，文件夹中添加了文件）。再次，这留给读者作为练习。
             */
            private boolean isFirstTimeChildren = true;
            private boolean isFirstTimeLeaf = true;

            @Override
            public ObservableList<TreeItem<File>> getChildren() {
                if (isFirstTimeChildren) {
                    isFirstTimeChildren = false;

                    // First getChildren() call, so we actually go off and
                    // determine the children of the File contained in this TreeItem.
                    super.getChildren().setAll(buildChildren(this));
                }
                return super.getChildren();
            }

            /**
             * 若是文件，则为叶节点；
             *
             * @return
             */
            @Override
            public boolean isLeaf() {
                if (isFirstTimeLeaf) {
                    isFirstTimeLeaf = false;
                    File f = (File) getValue();
                    isLeaf = f.isFile();
                }
                return isLeaf;
            }

            /**
             * 构建子节点
             * @param TreeItem
             * @return
             */
            private ObservableList<TreeItem<File>> buildChildren(TreeItem<File> TreeItem) {
                File f = TreeItem.getValue();
                // 若文件是一个目录，则取得其下所有的子；
                if (f != null && f.isDirectory()) {
                    // 目录下的所有文件
                    File[] files = f.listFiles();
                    ObservableList<TreeItem<File>> children = FXCollections.observableArrayList();

                    // 判断目录是否为空
                    if (files != null) {
                        for (File childFile : files) {
                            children.add(createNode(childFile));
                        }

                        return children;
                    }
                }

                return FXCollections.emptyObservableList();
            }
        };
        return ti;
    }
}
