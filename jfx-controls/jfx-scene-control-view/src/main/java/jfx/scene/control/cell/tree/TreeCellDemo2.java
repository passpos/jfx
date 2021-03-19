/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.cell.tree;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import jfx.core.app.ContentBox;
import jfx.core.utils.TreeUtils;

/**
 * 自定义节点样式
 *
 * @author passpos <paiap@outlook.com>
 */
public class TreeCellDemo2 extends ContentBox {

    public static final boolean SHOWING = true;
    public static final String TITLE = "Tree - TreeCell 自定义可编辑节点";
    private TreeView<String> tv;

    @Override
    public void index() {
        TreeUtils tu = new TreeUtils();
        tv = tu.getTreeView();

        Callback<TreeView<String>, TreeCell<String>> cb = new Callback<>() {
            @Override
            public TreeCell<String> call(TreeView<String> param) {
                return getTreeCell();
            }
        };
        tv.setEditable(true);
        tv.setCellFactory(cb);

        getChildren().add(tv);
    }

    /* -------------------------------------------------------------------------
     * TreeCell 默认的样式只有文本内容；
     * 通过自定义可以设置节点的：展开状态指示箭头、图标，以及其他复杂内容；
     *
     * TreeCell 不同于 TextFieldTreeCell ，它本身不支持转换器，也不直接支持对节
     * 点的文本进行编辑；
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
    public TreeCell<String> getTreeCell() {
        TreeCell<String> tc = new TreeCell<String>() {

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Button btn = new Button(item);
                    this.setGraphic(btn);

                    // 必须执行下列代码；
                    this.setText(item);

                    this.setEditable(true);
                }
            }

            @Override
            public void startEdit() {
                if (!isEditable() || !getTreeView().isEditable()) {
                    return;
                }
                super.startEdit();

                TreeCell<String> cell = this;

                Button button = new Button(this.getText());

                TextField textField = new TextField();
                textField.setText(this.getText());

                this.setText(null);

                HBox hBox = new HBox();
                hBox.getChildren().addAll(button, textField);
                this.setGraphic(hBox);

                textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent t) {
                        // 按下 ENTER 键时提交；
                        if (t.getCode() == KeyCode.ENTER) {
                            cell.commitEdit(textField.getText());
                        } else if (t.getCode() == KeyCode.ESCAPE) {
                            cell.cancelEdit();
                        }
                    }
                });

                // 取得焦点；
                textField.requestFocus();
                // 失去焦点时提交；
                textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                        if (t1) {

                        } else {
                            String text = textField.getText();
                            cell.commitEdit(text);
                        }
                    }
                });
            }

            @Override
            public void commitEdit(String newValue) {
                // 在这里进行数据校验、过滤、持久化操作；
                // 如果用户提交的数据不符合要求，就不执行下列操作，数据将保持原样；
                super.commitEdit(newValue);
            }

            @Override
            public void cancelEdit() {
                super.cancelEdit();
            }

        };
        return tc;
    }
}
