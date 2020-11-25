/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jfx.scene.control.view.list;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;
import jfx.core.entity.FxPerson;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class CheckBoxListCellApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "List - CheckBoxListCell";
    private ListView<FxPerson> lv;
    private ObservableList<FxPerson> oal;

    @Override
    public void index() {
        base();
        checkBoxListCellDemo();
    }

    public void base() {
        lv = new ListView<>();
        lv.setPrefHeight(200.0);

        // 用于list选项
        oal = lv.getItems();
        FxPerson pe1 = new FxPerson("教化", "15");
        FxPerson pe2 = new FxPerson("北京饭店", "56");
        FxPerson pe3 = new FxPerson("ggu", "69");
        oal.add(pe1);
        oal.add(pe2);
        oal.add(pe3);

        getChildren().add(lv);
    }

    /**
     * setCellFactory | Callback - CheckBoxListCell
     *
     */
    public void checkBoxListCellDemo() {
        Callback<ListView<FxPerson>, ListCell<FxPerson>> callback = CheckBoxListCell.forListView(new Callback<FxPerson, ObservableValue<Boolean>>() {
            /**
             * 返回true，表示默认选中。允许多选；
             *
             */
            @Override
            public ObservableValue<Boolean> call(FxPerson param) {
                if (param.getName().equals("教化")) {
                    return new SimpleBooleanProperty(true);
                } else {
                    return new SimpleBooleanProperty(false);
                }
            }
        },
                new StringConverter<FxPerson>() {
            @Override
            public String toString(FxPerson t) {
                return t.getName();
            }

            @Override
            public FxPerson fromString(String string) {
                return null;
            }
        });

        lv.setCellFactory(callback);
    }

}
