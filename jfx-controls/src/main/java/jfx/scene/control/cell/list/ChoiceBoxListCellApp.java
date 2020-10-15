/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.cell.list;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ChoiceBoxListCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import jfx.utils.app.ContentBox;
import jfx.utils.entity.FxPerson;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ChoiceBoxListCellApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "ListCell - ChoiceBoxListCell";
    private ListView<FxPerson> lv;
    private ObservableList<FxPerson> oal;

    @Override
    public void index() {
        base();
        choiceBoxListCellDemo();
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
