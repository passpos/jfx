/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jfx.scene.control.view.list;

import java.util.Comparator;
import java.util.function.Predicate;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;
import jfx.core.entity.FxPerson;

/**
 * B87
 * @author realpai <paiap@outlook.com>
 */
public class ListViewDemo2App extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "List - ListView Demo2";
    private ListView<FxPerson> lv;
    private ObservableList<FxPerson> oal;

    @Override
    public void index() {
        base();
        filterListDemo();
        sortListDemo();
    }

    public void base() {
        FxPerson m1 = new FxPerson("A", "54");
        FxPerson m2 = new FxPerson("B", "5");
        FxPerson m3 = new FxPerson("C", "45");
        FxPerson m4 = new FxPerson("D", "152");
        FxPerson m5 = new FxPerson("E", "415");
        FxPerson m6 = new FxPerson("F", "58");
        FxPerson m7 = new FxPerson("G", "13");

        oal = FXCollections.observableArrayList(new Callback<FxPerson, Observable[]>() {
            @Override
            public Observable[] call(FxPerson param) {
                SimpleStringProperty[] arr = new SimpleStringProperty[]{param.getNameProperty(), param.getAgeProperty()};
                return arr;
            }
        });
        oal.addAll(m1, m2, m3, m4, m5, m6, m7);

        lv = new ListView<>(oal);
        lv.setCellFactory(TextFieldListCell.forListView(new StringConverter<FxPerson>() {
            @Override
            public String toString(FxPerson t) {
                return t.getName() + " - " + t.getAge();
            }

            @Override
            public FxPerson fromString(String string) {
                return null;
            }
        }));
        lv.setPrefHeight(200.0);

        getChildren().addAll(lv);
        setTopAnchor(lv, 300.0);
    }

    /**
     * 列表过滤 javafx.collections.transformation.FilteredList
     *
     * 只有符合指定条件的数据项，才会展示到界面；
     * 实际实现时，通过过滤数据源来实现预定目标。这里使用到了下面的类：
     *
     */
    public void filterListDemo() {
        // 过滤条件通过 TextField 获取得到；
        TextField tf = new TextField();
        getChildren().addAll(tf);
        setTopAnchor(tf, 270.0);

        // 通过监听输入字符串的变化，动态过滤数据源；
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                FilteredList<FxPerson> flt = oal.filtered(new Predicate<FxPerson>() {
                    @Override
                    public boolean test(FxPerson m) {
                        return m.getName().contains(t1);
                    }
                });
                lv.setItems(flt);
            }
        });
    }

    /**
     * 列表排序 javafx.collections.transformation.SortedList
     *
     * oal3.sort()不返回被排序的列表，但被排序的的列表本身会发生改变；
     * oal3.sorted()返回列表排序的结果，被排序的的列表本身 没有 改变；
     */
    public void sortListDemo() {
        Button btn = new Button("排序");
        getChildren().add(btn);
        setTopAnchor(btn, 270.0);

        // 从小到大的排序；
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                SortedList<FxPerson> sorted = oal.sorted(new Comparator<FxPerson>() {
                    @Override
                    public int compare(FxPerson o1, FxPerson o2) {
                        Integer a = Integer.valueOf(o1.getAge());
                        Integer b = Integer.valueOf(o2.getAge());
                        return a - b;
                    }
                });
                lv.setItems(sorted);
            }
        });
    }
}
