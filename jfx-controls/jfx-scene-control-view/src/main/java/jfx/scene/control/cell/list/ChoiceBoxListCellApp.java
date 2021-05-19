/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.cell.list;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ChoiceBoxListCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;
import jfx.core.common.Data;
import jfx.core.entity.FxPerson;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ChoiceBoxListCellApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "ListCell - ChoiceBoxListCell";
    private ListView<FxPerson> lv;

    @Override
    public void index() {
        base();
        choiceBoxListCellDemo();
    }

    public void base() {
        lv = new ListView<>(Data.getFxPersonList());
        lv.setPrefHeight(200.0);
        getChildren().add(lv);
    }

    /**
     * setCellFactory | Callback - ChoiceBoxListCell
     *
     */
    public void choiceBoxListCellDemo() {
        lv.setEditable(true);
        Callback<ListView<FxPerson>, ListCell<FxPerson>> callback = ChoiceBoxListCell.forListView(
                new StringConverter<FxPerson>() {
            @Override
            public String toString(FxPerson t) {
                return t.getName() + " - 双击查看效果";
            }

            @Override
            public FxPerson fromString(String string) {
                return new FxPerson();
            }
        },
                // choiceBox选项列表
                new FxPerson("回复", "12"),
                new FxPerson("持久化", "12")
        );

        lv.setCellFactory(callback);
    }

}
