/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.view.list;

import java.util.Comparator;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import jfx.core.app.ContentBox;
import jfx.core.data.Data;

/**
 * 操作列表中的数据
 * 实际是通过操作数据源实现CRUD；
 *
 * @author passpos <paiap@outlook.com>
 */
public class ListViewDemo2 extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "ListView - Demo1 编辑数据源List";
    private ListView<String> lv;
    private ObservableList<String> oList;

    @Override
    public void index() {
        base();
        modifyDataDemo();
    }

    public void base() {
        oList = Data.getStringList();

        lv = new ListView<>(oList);
        lv.setPrefWidth(300.0);
        lv.setPrefHeight(200.0);
        getChildren().add(lv);
    }

    public void modifyDataDemo() {
        Button b = new Button("点击查看效果");
        getChildren().add(b);
        setTopAnchor(b, 300.0);
        setLeftAnchor(b, 400.0);
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                oList.add("data - x");
                oList.add(2, "data - x");
                oList.set(0, "data - modify");
                oList.remove(4);
                oList.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o2.compareTo(o1);
                    }
                });

                lv.scrollTo(4);
                lv.edit(2);
            }
        });

        oList.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable o) {
                @SuppressWarnings("unchecked")
                ObservableList<String> olist = (ObservableList) o;
                ol(olist);
            }
        });

        /*
         * 替换动作被拆解为两步，先添加、再删除。所以，执行替换动作会：
         * 先打印“添加动作”、“删除动作”，再打印“替换动作”；
         *
         * 更新动作的结果一般不会被打印，原因见 ListViewDemo2 的 dataOperatorAndUIUpdateDemo()
         * 方法；
         */
        oList.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends String> c) {
                if (c.next()) {
                    if (c.wasAdded()) {
                        ol("添加动作");
                    }
                    if (c.wasPermutated()) {
                        ol("排序操作");
                    }
                    if (c.wasRemoved()) {
                        ol("删除动作");
                    }
                    if (c.wasReplaced()) {
                        ol("替换动作");
                    }
                    if (c.wasUpdated()) {
                        ol("更新动作");
                    }
                }
            }
        });
    }
}