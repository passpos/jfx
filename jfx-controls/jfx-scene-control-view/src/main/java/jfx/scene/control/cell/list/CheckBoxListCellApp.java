/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.cell.list;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.util.Callback;
import jfx.core.app.ContentBox;
import jfx.core.common.Data;
import jfx.core.entity.FxPerson;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class CheckBoxListCellApp extends ContentBox {

    public static final boolean SHOWING = true;
    public static final String TITLE = "ListCell - CheckBoxListCell - FxPerson";
    private ListView<FxPerson> lv;

    @Override
    public void index() {
        lv = new ListView<>(Data.getPersonList());
        lv.setPrefHeight(200.0);
        getChildren().add(lv);
        base();
    }

    /**
     * setCellFactory | Callback - CheckBoxListCell
     *
     */
    public void base() {
        Callback<ListView<FxPerson>, ListCell<FxPerson>> callback;
        callback = CheckBoxListCell.forListView(
                getCallback(),
                TextFieldListCellDemo.getStringConverter()
        );

        lv.setCellFactory(callback);
    }

    /**
     * call()返回true，表示默认选中。允许多选；
     * 调用call()后，会将对象的 GenderProperty 绑定到视图中的复选框，复选框的选
     * 择状态会被自动设定到该 GenderProperty 上；
     *
     * @return
     */
    public Callback<FxPerson, ObservableValue<Boolean>> getCallback() {
        Callback<FxPerson, ObservableValue<Boolean>> c = new Callback<>() {

            @Override
            public ObservableValue<Boolean> call(FxPerson param) {
                return param.getGenderProperty();
            }
        };

        return c;
    }
}
