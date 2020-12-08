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
    public static final String TITLE = "Tree - TreeItem Demo 文件系统浏览器";
    public TreeView<String> tv;

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        TreeView<File> ftv = buildFileSystemBrowser();
        getChildren().add(ftv);
    }

    /**
     * 该方法对一个文件根创建TreeView，其root节点就是这个文件根；
     *
     * @return
     */
    private TreeView<File> buildFileSystemBrowser() {
        TreeItem<File> fr = createNode(new File("/"));
        return new TreeView<>(fr);
    }

    /**
     * 此方法创建一个TreeItem来表示给定的File。
     * 它通过匿名重写TreeItem的getChildren()和TreeItem.isLeaf()方法来做到这一点，
     * 但最好通过创建TreeItem的“ FileTreeItem”子类来抽象该方法。这留给用户作练习；
     */
    private TreeItem<File> createNode(final File f) {
        /**
         * 我们对子项（children）和叶子（leaf）仅进行一次测试，然后将其设置为false，
         * 以便在此运行期间不再进行检查。这通过缓存下面这两个布尔变量实现：
         * - isFirstTimeLeaf
         * - isFirstTimeChildren
         * 可能需要更完整的实现来处理更多动态文件系统情况（例如，在显示
         * TreeView之后，文件夹中添加了文件）。再次，这留给读者作为练习。
         */
        return new TreeItem<File>(f) {
            /**
             * 文件是否为叶（是目录还是文件）；
             * isLeaf()会被经常调用，而对File进行实际检查非常昂贵，所以缓存它。
             */
            private boolean isLeaf;

            /**
             * 记录是否为第一次判断节点为leaf；
             */
            private boolean isFirstTimeLeaf = true;

            /**
             * 记录是否为第一次获取子项数据；
             */
            private boolean isFirstTimeChildren = true;

            /**
             * 若是文件，则为叶节点；
             *
             * @return
             */
            @Override
            public boolean isLeaf() {
                if (isFirstTimeLeaf) {
                    isFirstTimeLeaf = false;
                    File f = getValue();
                    isLeaf = f.isFile();
                }
                return isLeaf;
            }

            /**
             * 获取子节点的集合
             * @return
             */
            @Override
            public ObservableList<TreeItem<File>> getChildren() {
                if (isFirstTimeChildren) {
                    isFirstTimeChildren = false;

                    /* 首先调用getChildren()，这样我们就能实际上确定此TreeItem中
                     * 包含的File的子级；
                     * 初次运行getChildren()，下面的语句会导致再调用一次getChildren()；
                     */
                    super.getChildren().setAll(buildChildren(this));
                }
                return super.getChildren();
            }

            /**
             * 构建子节点
             * @param ti
             * @return
             */
            private ObservableList<TreeItem<File>> buildChildren(TreeItem<File> ti) {
                File f = ti.getValue();
                // 若是目录，则取得其下所有的子；
                if (f != null && f.isDirectory()) {
                    // 目录下的所有子
                    File[] files = f.listFiles();

                    // 若目录非空
                    if (files != null) {
                        ObservableList<TreeItem<File>> children;
                        children = FXCollections.observableArrayList();
                        for (File childFile : files) {
                            children.add(createNode(childFile));
                        }

                        return children;
                    }
                }

                return FXCollections.emptyObservableList();
            }
        };
    }
}
