/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.view.tree;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.util.Callback;
import jfx.core.app.ContentBox;

/**
 *
 * @author passpos <paiap@outlook.com>
 */
public class TreeViewDemo extends ContentBox {

    public static final boolean SHOWING = true;
    public static final String TITLE = "Tree - TreeView 动态TreeItem";
    public TreeView<String> tv;
    private TreeItem<String> root;
    private int temp = 0;

    @Override
    public void index() {
        baseDemo();
        changeAction();
    }

    public void baseDemo() {
        String str = "China";
        root = new TreeItem<>(str);
        root.setExpanded(true);

        root.setValue("中国");
        root.setGraphic(new Button(str));

        tv = new TreeView<>();
        getChildren().add(tv);

        tv.setRoot(root);
        tv.setPrefHeight(300);
        setTopAnchor(tv, 50.0);

        Callback<TreeView<String>, TreeCell<String>> cb = new Callback<>() {
            @Override
            public TreeCell<String> call(TreeView<String> param) {
                TreeCell<String> tc = new TreeCell<>() {

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        this.setGraphic(new Label(item));
                    }

                };

                return tc;
            }
        };

        tv.setCellFactory(cb);
    }

    public void changeAction() {
        Button button = new Button("点击");
        getChildren().add(button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                temp++;
                switch (temp) {
                    case 1:
                        addItem("河北省");
                        break;
                    case 2:
                        addItem("北京市");
                        break;
                    case 3:
                        addItem("广西壮族自治区");
                        break;
                    case 4:
                        addItem("陕西省");
                        break;
                    case 5:
                        addItem("海南省");
                        break;
                    default:
                        ol("可以了");
                        break;
                }
            }
        });
    }

    private void addItem(String title) {
        TreeItem<String> ti1 = new TreeItem<>(title);
        root.getChildren().add(ti1);
    }
}
