/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.view.list;

import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.Callback;
import jfx.core.app.ContentBox;
import jfx.core.data.Data;

/**
 * 编辑事件
 * 对编辑动作进行监听；
 *
 * @author passpos <paiap@outlook.com>
 */
public class ListViewDemo1 extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "ListView - Demo2 编辑";
    private ListView<String> lv;

    @Override
    public void index() {
        base();
        editEventDemo();
    }

    public void base() {
        lv = new ListView<>(Data.getStringList());
        lv.setPrefWidth(300.0);
        lv.setPrefHeight(200.0);
        getChildren().add(lv);

        lv.setEditable(true);
        Callback<ListView<String>, ListCell<String>> call1 = TextFieldListCell.forListView();

        // 可以接收一个StringConverter类型参数（StringConverter参考ListCell）
        // Callback<ListView<String>, ListCell<String>> call2 = TextFieldListCell.forListView(sc);
        lv.setCellFactory(call1);
    }

    public void editEventDemo() {
        lv.setOnEditStart(new EventHandler<ListView.EditEvent<String>>() {
            @Override
            public void handle(ListView.EditEvent<String> t) {
                ol(t.getIndex());

                // 此时返回null
                ol(t.getNewValue());
            }
        });
        lv.setOnEditCancel(new EventHandler<ListView.EditEvent<String>>() {
            @Override
            public void handle(ListView.EditEvent<String> t) {
            }
        });
        lv.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() {
            @Override
            public void handle(ListView.EditEvent<String> t) {
                ol(t.getNewValue());
            }
        });

    }
}
