/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.view.tree;

import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;
import jfx.core.utils.TreeUtils;

/**
 * TreeCell - 节点类型，用于填充并传染一个树单元格视图；
 *
 * TreeCell 本身虽然包含了用于展示的数据对象和一个TreeItem对象，但平常情况下，直
 * 接访问它们，将会是null。仅当TreeView渲染完毕后，用户直接操作TreeCell，在触发
 * 的事件动作中，可以访问到它们。
 *
 * 例如，在 tv.setCellFactory(callback) 中，callback.call() 方法要求返回一个
 * TreeCell，这个TreeCell一般由用户进行实例化，之后，通过该 TreeCell 是访问不到
 * TreeItem 和 数据 的，但在类似 TreeCell.setOnDragDetected()方法内，就可以了；
 *
 *
 * @author passpos <paiap@outlook.com>
 */
public class TreeCellApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Tree - TreeCell";
    private TreeView<String> tv;

    @Override
    public void index() {
        fillData();
        textFieldTreeCellDemo();
    }

    public void fillData() {
        TreeUtils tu = new TreeUtils();
        tv = tu.getTreeView();

        getChildren().add(tv);
    }

    /**
     * TextFieldTreeCell
     */
    public void textFieldTreeCellDemo() {
        StringConverter<String> sc = new StringConverter<String>() {
            @Override
            public String toString(String t) {
                return t + " - 这个好";
            }

            @Override
            public String fromString(String string) {
                return string;
            }

        };
        Callback<TreeView<String>, TreeCell<String>> cb = TextFieldTreeCell.forTreeView(sc);

        tv.setCellFactory(cb);
    }

    /**
     * CheckBoxTreeCell
     * ChoiceBoxTreeCell
     * ComboBoxTreeCell
     * TextFieldTreeCell
     */
    public void treeCell() {

    }
}
