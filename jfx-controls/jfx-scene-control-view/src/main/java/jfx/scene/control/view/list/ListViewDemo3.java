/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.view.list;

import java.util.function.Consumer;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import static javafx.scene.layout.AnchorPane.setTopAnchor;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;
import static jfx.core.app.ContentBox.ol;
import jfx.core.common.Converter;
import jfx.core.common.Data;

/**
 * B85 - updateUI 关于五种类型数据的加载和更新删除等操作；
 *
 * ObservableList oal维护的是“对象类型”的基本数据。能够被展示出来，是因为
 * 我们进行了转换（StringConverter）。这种转换通常是通过设置 “Cell”的工厂
 * 方法实现的；
 *
 * @author passpos <paiap@outlook.com>
 */
public class ListViewDemo3 extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "ListView - Demo3 操作SimpleStringProperty类型";
    private ListView<SimpleStringProperty> lv;
    private ObservableList<SimpleStringProperty> list;

    @Override
    public void index() {
        base();
        updateUIDemo();
        modifyDataDemo();
    }

    public void base() {
        list = Data.getStringPropertyList();
        lv = new ListView<>(list);
        lv.setPrefHeight(250.0);
        StringConverter<SimpleStringProperty> sc = Converter.forStringProperty();
        lv.setCellFactory(TextFieldListCell.forListView(sc));
        getChildren().add(lv);
    }

    /**
     * 操作数据源（列表），观察结果；
     *
     * 修改数据时只设置对象的（用于显示的）属性，而界面组件绑定的数据仅仅只是对
     * 象，而不是对象的属性，所以是不会更新到界面上的，必须执行refresh()方法；
     *
     * lv.refresh() 会重新加载一次“所有”数据；
     */
    public void modifyDataDemo() {
        Button b1 = new Button("点击修改");
        getChildren().add(b1);
        setTopAnchor(b1, 300.0);
        setLeftAnchor(b1, 300.0);

        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                // 添加
                list.add(new SimpleStringProperty("add - 列表元素对象到可观察列表"));
                // 替换（这个Property对象）
                list.set(0, new SimpleStringProperty("set - 列表元素对象到列表的指定键"));

                // 只执行下面方法不生效（不会更新到界面）；
                list.get(1).set("set - 直接设置列表元素的属性");

                // 执行这个方法后，才会更新到界面；
                // lv.refresh();
            }
        });

        Button b2 = new Button("点击更新");
        getChildren().add(b2);
        setTopAnchor(b2, 300.0);
        setLeftAnchor(b2, 350.0);

        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                // 执行这个方法后，仅更新了属性的操作，才会更新到界面；
                lv.refresh();
            }
        });
    }

    public void updateUIDemo() {
        // 监听列表变化
        list.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable o) {
                @SuppressWarnings("unchecked")
                ObservableList<SimpleStringProperty> olist = (ObservableList) o;
                olist.forEach(new Consumer<SimpleStringProperty>() {
                    @Override
                    public void accept(SimpleStringProperty t) {
                        ol(t.get());
                    }
                });
            }
        });

    }
}
