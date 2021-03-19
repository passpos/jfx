/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.cell.tree;

import javafx.scene.control.Button;
import javafx.scene.control.TreeCell;
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

    @Override
    public void index() {
        TreeUtils tu = new TreeUtils();
        tv = tu.getTreeView();
        getChildren().add(tv);

        forTreeViewDemo1();
    }

    /* -------------------------------------------------------------------------
     * TextFieldTreeCell.forTreeView()
     * TextFieldTreeCell.forTreeView(StringConverter)
     *
     * 无参forTreeView()，使用的是一个内部默认的StringConverter；
     * 当然，它也支持用户自定义的字符串转换器。
     * 这一点，从TextFieldTreeCell的两个构造函数及其参数，就能看出它们的特性；
     * ---------------------------------------------------------------------- */
    public void forTreeViewDemo1() {
        tv.setEditable(true);
        tv.setCellFactory(TextFieldTreeCell.forTreeView());
    }

    public void forTreeViewDemo2() {
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

    /* -------------------------------------------------------------------------
     * forTreeView()的底层实现；
     *
     * 当用户选择通过自定义回调“new”一个 TextFieldTreeCell 实例时，就没必要使
     * 用 textFieldTreeCellDemo1() 中的演示代码了，因为这可以直接通过
     * forTreeView() 取得同样功能的TextFieldTreeCell实例；
     *
     * 而一般这么做时，是要自定义节点的样式以及其他特性；
     *
     * 由于无论是显式还是隐式， TextFieldTreeCell 必然会使用一个 StringConverter，
     * 所以在 updateItem(String item, boolean empty) 中，不必调用setText(item)；
     * ---------------------------------------------------------------------- */
    public void textFieldTreeCellDemo1() {
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
        callback = new Callback<>() {
            @Override
            public TreeCell<String> call(TreeView<String> param) {
                // TextFieldTreeCell<String> tfCell1 = new TextFieldTreeCell<>();
                TextFieldTreeCell<String> tfCell2 = new TextFieldTreeCell<>(sc);
                return tfCell2;
            }
        };

        tv.setEditable(true);
        tv.setCellFactory(callback);
    }

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
        callback = new Callback<>() {
            @Override
            public TreeCell<String> call(TreeView<String> param) {
                TextFieldTreeCell<String> tfCell;
                tfCell = new TextFieldTreeCell<>(sc) {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            setGraphic(new Button(item));
                            setText(item);
                        }
                    }

                };
                return tfCell;
            }
        };

        tv.setEditable(true);
        tv.setCellFactory(callback);
    }

    /* -------------------------------------------------------------------------
     * TreeCell 支持编辑功能，但需要重写 TreeCell 的三个接口方法；
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
