/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.view.list;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;
import jfx.core.common.Converter;
import jfx.core.entity.FxPerson;

/**
 * B-85 获取可以监听属性变化的列表；
 *
 * FXCollections.observableArrayList(callback)
 * 哪些属性需要监听，这在 callback 中定义；
 *
 * 如果对象的成员既有Property类型，又有基本类型，若要全部更新到界面上，可以在操
 * 作数据时，将Property类型的成员放在最后操作，界面上就会更新所有类型的成员；
 *
 * @author passpos <paiap@outlook.com>
 */
public class ListViewDemo4 extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "ListView - Demo4 仅更新属性";
    private ListView<SimpleStringProperty> lv;
    private ObservableList<SimpleStringProperty> list;

    @Override
    public void index() {
        specialData();
        base();
        modifyDataDemo();
    }

    /**
     * Callback.call()中返回的“对象”数组，表示对象中需要监听变化的所有“属性”；
     *
     * 只有这样获取的列表，才能不必执行更新，就能刷新到界面。
     * 而此时，就会激发 Demo2 中涉及到的 “更新动作”；
     */
    private ObservableList<SimpleStringProperty> specialData() {
        Callback<SimpleStringProperty, Observable[]> callback = new Callback<>() {
            @Override
            public Observable[] call(SimpleStringProperty param) {
                SimpleStringProperty[] ssps = new SimpleStringProperty[]{param};
                return ssps;
            }
        };

        list = FXCollections.observableArrayList(callback);
        SimpleStringProperty ssp1 = new SimpleStringProperty("A");
        SimpleStringProperty ssp2 = new SimpleStringProperty("B");
        SimpleStringProperty ssp3 = new SimpleStringProperty("C");
        SimpleStringProperty ssp4 = new SimpleStringProperty("D");
        list.addAll(ssp1, ssp2, ssp3, ssp4);
        return list;
    }

    /**
     * 监听对象类型数据的属性变化
     *
     * @return
     */
    public ObservableList<FxPerson> specialData2() {
        Callback<FxPerson, Observable[]> callback = new Callback<>() {
            @Override
            public Observable[] call(FxPerson param) {
                // 之所以返回的是数组，是因为要监听的对象的属性可能有多个，如下面这样
                SimpleStringProperty[] ssps = new SimpleStringProperty[]{
                    param.getNameProperty(),
                    param.getAgeProperty()
                };
                return ssps;
            }
        };
        ObservableList<FxPerson> list2 = FXCollections.observableArrayList(callback);
        return list2;
    }

    public void base() {
        lv = new ListView<>(list);
        lv.setPrefWidth(300.0);
        lv.setPrefHeight(200.0);
        StringConverter<SimpleStringProperty> sc = Converter.forStringProperty();
        lv.setCellFactory(TextFieldListCell.forListView(sc));
        getChildren().add(lv);
    }

    public void modifyDataDemo() {
        Button b1 = new Button("点击修改并更新");
        getChildren().add(b1);
        setTopAnchor(b1, 300.0);
        setLeftAnchor(b1, 250.0);

        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                // 只执行下面方法不生效（不会更新到界面）；
                list.get(1).set("set - 仅设置元素的属性");

                // 不执行更新；
                // lv.refresh();
            }
        });
    }
}
