/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.view;

import java.util.function.Consumer;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;
import jfx.core.entity.FxPerson;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ListViewDemo1App extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "View - ListView Demo1";
    private ListView<SimpleStringProperty> lv;

    @Override
    public void index() {
        dataOperatorAndUIUpdateDemo();
        setCustomeStyleDemo();
    }

    /**
     * setCellFactory() | Callback - updateUI
     * B85 - 关于五种类型数据的加载和更新删除等操作；
     *
     * ObservableList oal维护的是“对象类型”的基本数据。能够被展示出来，是因为
     * 我们进行了转换（StringConverter）。这种转换通常是通过设置 “Cell”的工厂
     * 方法实现的；
     *
     * 如果只设置对象的（用于显示的）属性，是不会更新到界面上的，必须执行refrsh()方法；
     */
    public void dataOperatorAndUIUpdateDemo() {
        lv = new ListView<>();
        lv.setPrefHeight(250.0);
        getChildren().add(lv);

        // 创建“选项列”对象，并将数据加入选项列
        ObservableList<SimpleStringProperty> oal1 = lv.getItems();

        SimpleStringProperty ssp1 = new SimpleStringProperty("A");
        SimpleStringProperty ssp2 = new SimpleStringProperty("B");
        SimpleStringProperty ssp3 = new SimpleStringProperty("C");
        SimpleStringProperty ssp4 = new SimpleStringProperty("D");
        oal1.addAll(ssp1, ssp2, ssp3, ssp4);
        // 监听列表变化
        oal1.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable o) {
                @SuppressWarnings("unchecked")
                ObservableList<SimpleStringProperty> data = (ObservableList) o;
                data.forEach(new Consumer<SimpleStringProperty>() {
                    @Override
                    public void accept(SimpleStringProperty t) {
                        ol(t.get());
                    }
                });
            }
        });

        /*
         * 设置选项列对象的展示方式（字符串转换）
         *
         * 将ListCell中数据的属性类型，转换为普通的字符串类型；
         */
        StringConverter<SimpleStringProperty> sc = new StringConverter<SimpleStringProperty>() {
            @Override
            public String toString(SimpleStringProperty t) {
                return t.get();
            }

            @Override
            public SimpleStringProperty fromString(String string) {
                return new SimpleStringProperty(string);
            }

        };
        lv.setCellFactory(TextFieldListCell.forListView(sc));

        Button b = new Button("按钮");
        // 操作数据源（列表），观察结果；
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                oal1.add(new SimpleStringProperty("add - 列表元素对象到可观察列表"));
                oal1.set(0, new SimpleStringProperty("set - 列表元素对象到列表的指定键"));

                // 只执行下面方法不生效（不会更新到界面）；
                ssp2.set("set - 直接设置列表元素的属性");

                // 执行这个方法后，才会更新的界面；
                lv.refresh();
            }
        });

        getChildren().add(b);
        setTopAnchor(b, 300.0);
    }

    /**
     * 通过 FXCollections.observableArrayList() 获取的列表，不必更新，就能刷新到
     * 界面。 此时会激发“更新动作”；
     *
     * 如果对象的属性既有Property类型，又有基本类型，在这里操作数据时，将Property
     * 类型的属性放在最后进行操作，界面上就会更新所有的属性；
     */
    public void autoUpdateDemo1() {
        Callback<SimpleStringProperty, Observable[]> callback = new Callback<SimpleStringProperty, Observable[]>() {
            @Override
            public Observable[] call(SimpleStringProperty param) {
                SimpleStringProperty[] ssps = new SimpleStringProperty[]{param};
                return ssps;
            }
        };
        ObservableList<SimpleStringProperty> oal = FXCollections.observableArrayList(callback);
    }

    public void autoUpdateDemo2() {
        Callback<FxPerson, Observable[]> callback = new Callback<FxPerson, Observable[]>() {
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
        ObservableList<FxPerson> oal = FXCollections.observableArrayList(callback);
    }

    /**
     * B86
     * setCellFactory() | Callback - 自定义选项样式（实现自定义的Callback对象）
     *
     * 通过回调，创建自定义的ListCell；
     */
    public void setCustomeStyleDemo() {
        FxPerson m1 = new FxPerson("独树花发自分明", "54");
        FxPerson m2 = new FxPerson("简繁", "32");
        FxPerson m3 = new FxPerson("甫和", "85");
        FxPerson m4 = new FxPerson("桃仁", "6");

        ListView<FxPerson> llv = new ListView<>();
        ObservableList<FxPerson> oval = llv.getItems();
        oval.add(m1);
        oval.add(m2);
        oval.add(m3);
        oval.add(m4);

        llv.setEditable(true);
        Callback<ListView<FxPerson>, ListCell<FxPerson>> callback = new Callback<ListView<FxPerson>, ListCell<FxPerson>>() {
            public int index;
            public FxPerson tmp;

            @Override
            public ListCell<FxPerson> call(ListView<FxPerson> param) {
                ListCell<FxPerson> lc = new ListCell<>() {
                    @Override
                    public void startEdit() {
                        super.startEdit();
                        HBox hb = new HBox(10);
                        hb.setAlignment(Pos.CENTER_LEFT);
                        TextField name = new TextField("");
                        TextField age = new TextField("");
                        ol("vvv");
                    }

                    @Override
                    public void cancelEdit() {
                        super.cancelEdit();
                    }

                    @Override
                    public void commitEdit(FxPerson newValue) {
                        super.commitEdit(newValue);
                    }

                    @Override
                    protected void updateItem(FxPerson item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty == false) {
                            HBox hb = new HBox(10);
                            hb.setAlignment(Pos.CENTER_LEFT);
                            ImageView iv = new ImageView(loadResource("icon/fav.jpg").toExternalForm());
                            Button btn = new Button(item.getName());
                            Label nameLabel = new Label(item.getName());
                            Label ageLabel = new Label(item.getAge());
                            hb.getChildren().addAll(iv, btn, nameLabel, ageLabel);
                            this.setGraphic(hb);
                        }
                    }
                };
                return lc;
            }
        };
        llv.setCellFactory(callback);

        getChildren().add(llv);
    }

}
