/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.cell.tree;

import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;
import jfx.core.utils.TreeUtils;

/**
 * 节点文本编辑
 *
 * @author passpos <paiap@outlook.com>
 */
public class TreeCellDemo1 extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Tree - TreeCell 节点文本编辑";
    private TreeView<String> tv;
    private TreeItem<String> root;

    @Override
    public void index() {
        TreeUtils tu = new TreeUtils();
        tv = tu.getTreeView();
        root = tu.getTreeItem();

        getChildren().add(tv);

        textFieldTreeCellDemo1();
    }

    /**
     * TextFieldTreeCell.forTreeView()
     *
     * 静态方法forTreeView()，既支持无参数，也支持用户定义的转换器参数；
     */
    public void textFieldTreeCellDemo1() {
        tv.setEditable(true);
        tv.setCellFactory(TextFieldTreeCell.forTreeView());
    }

    /**
     * TextFieldTreeCell.forTreeView(StringConverter)
     *
     * StringConverter定义数据对象与节点文本之间的转换策略；
     */
    public void textFieldTreeCellDemo2() {
        StringConverter<String> sc = new StringConverter<>() {
            @Override
            public String toString(String t) {
                return t + " - 这个好";
            }

            @Override
            public String fromString(String string) {
                return string;
            }

        };

        Callback<TreeView<String>, TreeCell<String>> callback;
        callback = TextFieldTreeCell.forTreeView(sc);

        tv.setEditable(true);
        tv.setCellFactory(callback);
    }

    public void treeCellEditDemo() {
        Callback<TreeView<String>, TreeCell<String>> callback;
        callback = new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> param) {
                return null;
            }

        };

        tv.setEditable(true);
        tv.setCellFactory(callback);
    }
}
