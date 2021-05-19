/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.view.list;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
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
import jfx.core.common.Converter;
import jfx.core.common.Data;
import jfx.core.entity.FxPerson;

/**
 * B85 - 关于五种类型数据的加载和更新删除等操作；
 * B86 setCellFactory() | Callback - 自定义选项样式（实现自定义的Callback对象）
 *
 * @author realpai <paiap@outlook.com>
 */
public class ListViewDemo11 extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "ListView - Demo11";
    private ListView<SimpleStringProperty> lv;
    private ObservableList<SimpleStringProperty> list;

    @Override
    public void index() {
        base();
        setCustomeStyleDemo();
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
        Callback<ListView<FxPerson>, ListCell<FxPerson>> callback = new Callback<>() {
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
