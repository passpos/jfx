/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.cell.tree;

import javafx.scene.control.Button;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;
import jfx.core.app.ContentBox;
import jfx.core.utils.TreeUtils;

/**
 * 自定义编辑
 *
 * @author passpos <paiap@outlook.com>
 */
public class TreeCellDemo2 extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Tree - TreeCell 自定义编辑";
    private TreeView<String> tv;

    @Override
    public void index() {
        fillData();
        treeCellEditDemo();
    }

    public void fillData() {
        TreeUtils tu = new TreeUtils();
        tv = tu.getTreeView();

        getChildren().add(tv);
    }

    /* -------------------------------------------------------------------------
     * TreeCell 不同于 TextFieldTreeCell ，它本身不支持转换器，也不直接支持对节
     * 点的文本进行编辑。
     *
     * 所以，在重写 updateItem(String item, boolean empty) 方法时，必须显式调用
     * setText(str)，否则节点无法正常显示；
     *
     * 要使其支持编辑功能，就需要重写 TreeCell 的三个接口方法；
     * - startEdit()
     * - commitEdit(String newValue)
     * - cancelEdit()
     * TextFieldTreeCell 的文本编辑功能就是使用的这三个接口方法；
     * ---------------------------------------------------------------------- */
    public void treeCellEditDemo() {
        Callback<TreeView<String>, TreeCell<String>> callback;
        callback = new Callback<>() {
            @Override
            public TreeCell<String> call(TreeView<String> param) {
                TreeCell<String> treeCell = new TreeCell<>() {

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            setGraphic(new Button(item));
                            // 必须执行下列代码；
                            setText(item);
                        }
                    }

                    @Override
                    public void startEdit() {
                        super.startEdit();
                    }

                    @Override
                    public void commitEdit(String newValue) {
                        super.commitEdit(newValue); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void cancelEdit() {
                        super.cancelEdit();
                    }

                };
                return treeCell;
            }

        };

        tv.setEditable(true);
        tv.setCellFactory(callback);
    }
}
