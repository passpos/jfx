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

/**
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
